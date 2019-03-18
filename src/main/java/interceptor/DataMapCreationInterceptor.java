package interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import neo.common.data.DataMap;
import neo.core.property.PropertyManager;
import neo.web.constants.ExceptionConstants;
import neo.web.exception.NeoException;

public class DataMapCreationInterceptor extends HandlerInterceptorAdapter {
	private final static String xssCheck = "script|html|head|body|iframe|javascript|";

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		System.out.println("pre !!! interceptor");
		getParameterMap(req);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView mv)
			throws Exception {
		resultMapDebug(req, res);
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
	}

	@SuppressWarnings("unchecked")
	public void getParameterMap(HttpServletRequest request) throws NeoException {
		DataMap parameterMap = new DataMap();
		try {

			Enumeration<?> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				String[] values = request.getParameterValues(key);
				System.out.println(" 뀨 interceptor key ->> " + key + "value --> " + values);

				if (values != null) {
					parameterMap.put(key, values);
					System.out.println("interceptor key ->> " + key + "value --> " + values);
					/*if (values.length > 1) {
						parameterMap.put(key + "Array", values);
						parameterMap.put(key, values);
						System.out.println("interceptor key ->> " + key + "value --> " + values);
					} else {
						// 이벤트 관련 항목(insert, update)은 제외한다
						if (request.getRequestURI().indexOf("/event/") > -1) {
							parameterMap.put(key, values[0]);
						} else {
							parameterMap.put(key, escapeXml(values[0]));
						}
					}*/
				} else {
					System.out.println("안들어옴");
				}
			}
			parameterMap.setRequestURI(request.getRequestURI());
		} catch (Exception ex) {
			parameterMap.clear();
		}
		
		System.out.println("인터셉터 데이터맵!" + parameterMap);

		/*try {
			if (SessionManager.isLogin(request)) {
				if (SessionManager.getLoginSession(request) != null
						&& SessionManager.getLoginSession(request).getUserInfo() != null) {
					// 현재 로그인한 사용자ID를 dataMap에 항상 셋팅 해 놓는다... 로그인 세션을 잘 못 만들어서 값이 안들어감...
					parameterMap.put("$CURRENT_USER_ID",
							SessionManager.getLoginSession(request).getUserInfo().getUserName());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/

		request.setAttribute("dataMap", parameterMap);
	}

	public void resultMapDebug(HttpServletRequest request, HttpServletResponse response) {
		DataMap resultMap = (DataMap) request.getAttribute("dataMap");
	}

	public static String escapeXml(String str) throws NeoException {
		String tempStr = str.toLowerCase();
		String xssCheckArr[] = PropertyManager.getProperty("default", "XSS_CHECK", xssCheck).toLowerCase().split("\\|");
		for (int i = 0; i < xssCheckArr.length; i++) {
			if (tempStr.contains(xssCheckArr[i])) {
				if ("javascript".equals(xssCheckArr[i].toLowerCase())) {
					String tempStr1 = tempStr
							.substring(0, tempStr.toLowerCase().indexOf(xssCheckArr[i]) + xssCheckArr[i].length())
							.trim();
					String tempStr2 = tempStr
							.substring(tempStr.toLowerCase().indexOf(xssCheckArr[i]) + xssCheckArr[i].length()).trim();
					if ((tempStr1 + tempStr2).contains(xssCheckArr[i] + ":")) {
						throw new NeoException(ExceptionConstants.DEFAULT_ERROR_CODE,
								"보안상 사용 할 수 없는 문자가 포함되어 있습니다.\n[" + xssCheckArr[i] + "]");
					}
				} else {
					String tempStr1 = tempStr.substring(0, tempStr.toLowerCase().indexOf(xssCheckArr[i])).trim();
					String tempStr2 = tempStr.substring(tempStr.toLowerCase().indexOf(xssCheckArr[i])).trim();
					if ((tempStr1 + tempStr2).contains("<" + xssCheckArr[i])
							|| (tempStr1 + tempStr2).contains("</" + xssCheckArr[i])
							|| (tempStr1 + tempStr2).contains("&lt;" + xssCheckArr[i])
							|| (tempStr1 + tempStr2).contains("&lt;/" + xssCheckArr[i])) {
						throw new NeoException(ExceptionConstants.DEFAULT_ERROR_CODE,
								"보안상 사용 할 수 없는 문자가 포함되어 있습니다.\n[" + xssCheckArr[i] + "]");
					}
				}
			}
		}

		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}
}