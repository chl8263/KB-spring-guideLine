package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neo.common.data.DataMap;
import service.PagingService;
import vo.PageMaker;

@Controller
@RequestMapping(value = "/paging")
public class PagingController {

	@Autowired
	private PagingService service;
	
	
	@RequestMapping(value = "/setDataTable" , method = RequestMethod.GET)
	public String setDataTable(HttpServletRequest request) {
		
		
		int displayPageNum =  Integer.parseInt(request.getParameter("length"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		System.out.println("displayPageNum :: "+displayPageNum);
		System.out.println("currentPage :: "+displayPageNum);
		
		PageMaker maker = getPagingLogic(displayPageNum,currentPage);

		int displayNum = maker.getDisplayPageNum();
		int currentNum = maker.getCurrentPage();
		
		DataMap dbPagingMap = new DataMap();
		dbPagingMap.put("start", (currentNum*displayNum));
		dbPagingMap.put("end", (displayNum*(currentNum+1))+1);
		
		System.out.println(dbPagingMap.get("start") + "  ^^^  " + dbPagingMap.get("end"));
		
		request.setAttribute("list",service.getPagingData(dbPagingMap));
		request.setAttribute("page",maker);
		request.setAttribute("length" ,maker.getDisplayPageNum());
		request.setAttribute("current" ,maker.getCurrentPage());
		
		
		
		return "home";
	}
	
	private PageMaker getPagingLogic(int displayPageNum , int currentPage) {
		
		PageMaker maker = new PageMaker();
		
		try{
			maker.setTotalcount(service.getTotalCount());	//전체 게시글 개수 지정
			maker.setCurrentPage(currentPage-1);	// 현재 페이지를 페이지 객체에 저장 쿼리사용때문에 -1
			maker.setDisplayPageNum(displayPageNum);	//한 페이지에 몇개씩 게시글 보여줄것인지
			maker.setCurrentblock(currentPage);		// 현재 페이지 블록이 몇번인지 페이지 번호를 통해서 지정
			maker.setLastblock(maker.getTotalcount());	//마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
			
			maker.prevNext(currentPage);	//현재 번호로 화살표를 나타낼지 정한다,
			maker.setStartPage(maker.getCurrentblock());	//시작 페이지를 페이지 블록 번호로 정한다,
			maker.setEndPage(maker.getLastblock(), maker.getCurrentblock());	//마지막 페이지를 
			maker.setTotalEndPage();
			System.out.println(maker.getTotalEndPage()+ " &&&&&&&&&&&&&&");
			
		}catch (NullPointerException e) {
			// null일때 체크하자 
		}
			
		/*if(currentPage > maker.getTotalEndPage()) {		//페이징 잘못된 번호 url 조작방지
			return getPagingLogic(displayPageNum,maker.getTotalEndPage());
		}else if (currentPage < 1) {
			return getPagingLogic(displayPageNum,1);
		}*/
		return maker;
	}

}
