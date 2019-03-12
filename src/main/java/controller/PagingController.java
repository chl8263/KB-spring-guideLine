package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import neo.common.data.DataMap;
import service.PagingService;
import vo.PageMaker;

@Controller
@RequestMapping(value = "/paging")
public class PagingController {

	@Autowired
	private PagingService service;

	@ResponseBody
	@RequestMapping(value = "/setDataTable" , method = RequestMethod.GET)
	public String setDataTable(HttpServletRequest request) {
		
		PageMaker maker = new PageMaker();
		
		int displayPageNum =  Integer.parseInt(request.getParameter("length"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		try{
			
			maker.setTotalcount(service.getTotalCount());	//전체 게시글 개수 지정
			maker.setCurrentPage(currentPage-1);	// 현재 페이지를 페이지 객체에 저장 쿼리사용때문에 -1
			maker.setDisplayPageNum(displayPageNum);	//한 페이지에 볓개씩 게시글 보여줄것인지
			maker.setCurrentblock(currentPage);		// 현재 페이지 블록이 몇번인지 페이지 번호를 통해서 지정
			maker.setLastblock(maker.getTotalcount());	//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			
			maker.prevNext(currentPage);	//현재 번호로 화살표를 나타낼지 정한다,
			maker.setStartPage(maker.getCurrentblock());	//시작 페이지를 페이지 블록 번호로 정한다,
			maker.setEndPage(maker.getLastblock(), maker.getCurrentblock());	//마지막 페이지를 
			
		}catch (NullPointerException e) {
			// null일때 체크하자 
		}
		
		DataMap map = new DataMap();
		map.put("start", maker.getCurrentPage());
		map.put("end", maker.getDisplayPageNum()+1);
		
		System.out.println(map.get("start") + "  ^^^  " + map.get("end"));
		
		request.setAttribute("list" ,service.getPagingData(map));
		request.setAttribute("page" ,maker);
		
		
		return "";
	}

}
