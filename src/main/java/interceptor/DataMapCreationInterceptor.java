package interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import neo.common.data.DataMap;
import neo.web.exception.NeoException;

public class DataMapCreationInterceptor extends HandlerInterceptorAdapter {

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

				if (values != null) {
					parameterMap.put(key, values[0]);	//배열 처리
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

		request.setAttribute("dataMap", parameterMap);
	}

	public void resultMapDebug(HttpServletRequest request, HttpServletResponse response) {
		DataMap resultMap = (DataMap) request.getAttribute("dataMap");
	}

	
}