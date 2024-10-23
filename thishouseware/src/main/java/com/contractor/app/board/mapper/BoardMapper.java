package com.contractor.app.board.mapper;

import java.util.List;

import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.CommentsVO;
import com.contractor.app.board.service.PagingVO;
import com.contractor.app.board.service.PostsVO;

public interface BoardMapper {
	// 게시판 전체조회
	public List<BoardsVO> selectBoardAll(BoardsVO boardsVO);

	// 게시글 전체조회
	public List<PostsVO> selectPostAll(PostsVO postsVO);
	
	// 게시판별 게시글 전체조회
	public List<PostsVO> selectPostBoard(PostsVO postsVO);

	// 게시글 단건조회 : 조건 - postsNo
	public PostsVO selectPostInfo(PostsVO postsVO);

	// 게시글 등록
	public int insertPostInfo(PostsVO postsVO);

	
	// 게시글 삭제
	public int deleteBoradInfo(int postsNo);
	
	// 메인페이지 게시판
	public List<BoardsVO> selectBoardMain(BoardsVO boardsVO);
	
	// 게시판 단건조회
	public BoardsVO selectBoard(BoardsVO boardsVO);
	
	// 게시글별 댓글 전체조회
	public List<CommentsVO> selectCommentsBoard(CommentsVO commentsVO);
	
	// 댓글 등록
	public int insertCommentInfo(CommentsVO commentsVO);
	
	// 게시물 총 갯수
	public int countPost(PostsVO postsVO);

	// 페이징 처리 게시글 조회
	public List<PostsVO> selectPostsPaging(PagingVO pagingVO);

}
