package vo;

public class PageMaker {
	
	private int startPage = 1;	// 현재 페이지 블록의 시작 페이지
	private int endPage = this.getListCount();	// 연재 페이지 블록의 마지막 페이지
	private boolean prev = false;	// 이전 페이지로 가는 화살표
	private boolean next = false;	// 다음 페이지로 가는 화살표
	
	private int displayPageNum = 10;	// 한 페이지에 몇개 표시할지
	
	private int currentblock;
	private int lastblock;
	
	/*
	 * DB 에서 조회 및 외부 주입 맴버
	 * */
	private int totalcount;	//전체 개시물개수
	private int currentPage;	//현재 페이지번호
	private int listCount = 5;	// 게시글 몇페이지씩 보여줄것인지	
	
	public void prevNext(int pageNum) {
		if(pageNum>0 && pageNum<listCount+1) {
			setPrev(false);
			setNext(true);
		}else if(getLastblock() == getCurrentblock()) {
			setPrev(true);
			setNext(false);
		}else {
			setPrev(true);
			setNext(true);	
		}
	}
	

	
	public int calcPage(int totalCount , int displayPageNum) {	//전체 페이지 수를 계산하는 함수
		
		int totalPage = totalCount / displayPageNum;
		
		if(totalCount%displayPageNum > 0 ) {
			totalPage++;
		}
		
		return totalPage;
	}
	
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}



	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}



	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int currentBlock) {
		this.startPage = (currentBlock*listCount)-(listCount-1);	// 첫 페이지 블록 구하는 식
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int getlastBlock, int getCurrentBlock) {
		
		if(getlastBlock == getCurrentBlock) {
			this.endPage = calcPage(getTotalcount(), displayPageNum);
		}else {
			this.endPage = getStartPage()+(listCount-1);
		}
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(int pageNum) {	//페이지 번호를 통해서 구함
		this.currentblock = pageNum/listCount;
		if(pageNum%listCount > 0) {
			this.currentblock++;
		}
	}
	public int getLastblock() {
		return lastblock;
	}
	public void setLastblock(int totalcount) {
		
		this.lastblock = totalcount/(listCount*this.displayPageNum);
		
		if(totalcount%(listCount*this.displayPageNum)>0) {
			this.lastblock++;
		}
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}		
	
	
	
	
	
	
	/*public PageMaker(int totalcount , int currentPage , int listCount) {
		this.totalcount = totalcount;
		this.currentPage = currentPage;
		this.listCount = listCount;
	}*/
	
	/*public void setTotalCount(int totalcount) {
		this.totalcount = totalcount;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	private void calcData() {
		endPage = (int)(Math.ceil(currentPage/(double)displayPageNum)*displayPageNum);
		
		startPage = (endPage - displayPageNum) +1 ;
		
		int tempEndPage = (int)(Math.ceil(totalcount/(double)listCount));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * listCount >= totalcount ? false : true ;
	}*/

}
