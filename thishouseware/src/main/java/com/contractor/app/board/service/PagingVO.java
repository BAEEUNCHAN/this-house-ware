package com.contractor.app.board.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PagingVO {
	private int nowPage; // 현재페이지
	private int startPage; // 시작페이지
	private int endPage; // 끝페이지
	private int total; // 게시글 총 갯수
	private int cntPerPage; // 페이지당 글 갯수
	private int lastPage; // 마지막페이지
	private int start; // SQL쿼리에 쓸 start
	private int end; // SQL쿼리에 쓸 end
	private int cntPage = 10;

	private String searchWord; // 검색

	public PagingVO() {
	}

	public PagingVO(int total, int nowPage, int cntPerPage) {
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage(), getTotal());
	}

	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double) total / (double) cntPerPage));
	}

	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int) Math.ceil((double) nowPage / (double) cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
	}

	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage, int total) {
		setEnd(nowPage * cntPerPage);

		// 총 게시글 수를 넘지 않도록 설정
		if (getEnd() > total) {
			setEnd(total);
		}

		setStart(getEnd() - cntPerPage + 1);

		// start가 1보다 작을 수 없도록 설정
		if (getStart() < 1) {
			setStart(1);
		}
	}

	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
				+ ", cntPage=" + cntPage + "]";
	}
}