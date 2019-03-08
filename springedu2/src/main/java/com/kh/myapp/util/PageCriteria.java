package com.kh.myapp.util;

/*input  : RecordCriteria(요청페이지,한페이지에 보여줄 레코드 수), totalRec(총레코드수), 한페이지에 보여줄 페이지 수
 *output : 시작페이지, 종료페이지, 최종마지막페이지, 이전페이지버튼표시유무, 다음페이지버튼표시유무
 * */

public class PageCriteria {

	private int pageNumberPerPage; // 한페이지에 보여줄 페이지수
	private int startPage; // 한페이지의 시작페이지
	private int endPage; // 한페이지의 종료페이지

	private int totalRec; // 전체 레코드수
	private int finalEndPage; // 최종 페이지

	private boolean prev; // 이전페이지
	private boolean next; // 다음페이지

	private RecordCriteria recordCriteria; // 한페이지에 보여줄 레코드수, 요청페이지

	private PageCriteria(RecordCriteria recordCriteria) {
		this.recordCriteria = recordCriteria;
	}

	public PageCriteria(RecordCriteria recordCriteria, int totalRec) {
		this(recordCriteria);
		this.totalRec = totalRec;
	}

	public PageCriteria(RecordCriteria recordCriteria, int totalRec, int pageNumPerPage) {
		this(recordCriteria, totalRec);
		this.pageNumberPerPage = pageNumPerPage;
		init();
	}

	private void init() {

		// 1) endPage 계산 : 올림(요청페이지/한페이지에 보여줄 페이지 수) * 한페이지에 보여줄 페이지 수
		this.endPage = (int)Math.ceil(this.recordCriteria.getReqPage() / (double)this.pageNumberPerPage)
				* this.pageNumberPerPage;

		// 2) startPage 계산 : 종료페이지-한페이지에 보여줄 페이지수 + 1
		this.startPage = this.endPage - this.pageNumberPerPage + 1;

		// 3) finalEndPage 계산 : 올림(전체 레코드수/한페이지에 보여줄 레코드 수)
		this.finalEndPage = (int) Math.ceil(this.totalRec / (double)this.recordCriteria.getNumPerPage());
		// 종료페이지가 최종페이지보다 크면 종료페이지를 최종페이지로 같게한다.
		if(endPage > finalEndPage) {
			endPage = finalEndPage;
		}

		// 4) prev 계산 : 현재페이지의 시작페이지가 1이면 이전버튼 안보이기 , 1이 아니면 버튼 보이기
		this.prev = this.startPage != 1 ? true : false;

		// 5) next 계산 : 현재페이지의 종료페이지 * 한페이지에 보여지는 레코드 수가 전체 레코드수보다 작으면 다음버튼 보이기
		this.next = this.totalRec > (this.endPage * this.recordCriteria.getNumPerPage()) ? true : false;

	}

	// 파라미터 설정
	public String makeURL(int reqPage) { // list쪽에서 makeURL "reqPage=" +recordCriteria.getReqPage() 식으로 쓸것이기때문에
		StringBuffer str = new StringBuffer();

		if (recordCriteria.getReqPage() != 0) {
			str.append("reqPage=" + recordCriteria.getReqPage());
		}

		return str.toString();
	}

	// 검색용파라미터 설정
	public String makeSearchURL(int reqPage) { // list쪽에서 makeURL "reqPage=" +recordCriteria.getReqPage() 식으로 쓸것이기때문에
		StringBuffer str = new StringBuffer();

		str.append("reqPage=" + reqPage);

		return str.toString();
	}

	public int getPageNumberPerPage() {
		return pageNumberPerPage;
	}

	public void setPageNumberPerPage(int pageNumberPerPage) {
		this.pageNumberPerPage = pageNumberPerPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
	}

	public int getFinalEndPage() {
		return finalEndPage;
	}

	public void setFinalEndPage(int finalEndPage) {
		this.finalEndPage = finalEndPage;
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

	public RecordCriteria getRecordCriteria() {
		return recordCriteria;
	}

	public void setRecordCriteria(RecordCriteria recordCriteria) {
		this.recordCriteria = recordCriteria;
	}
	
	public String getKeyword() {
		String keyword = null;
		if(recordCriteria instanceof FindCriteria ) {
			keyword = ((FindCriteria)recordCriteria).getKeyword();
		}
		return keyword;
	}
	public String getSearchType() {
		String searchType = null;
		if(recordCriteria instanceof FindCriteria ) {
			searchType = ((FindCriteria)recordCriteria).getSearchType();
		}		
		return searchType;
	}
	
	@Override
	public String toString() {
		return "PageCriteria [pageNumberPerPage=" + pageNumberPerPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", totalRec=" + totalRec + ", finalEndPage=" + finalEndPage + ", prev=" + prev + ", next="
				+ next + ", recordCriteria=" + recordCriteria + "]";
	}

}
