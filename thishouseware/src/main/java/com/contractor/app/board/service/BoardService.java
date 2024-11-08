package com.contractor.app.board.service;

import java.util.List;

public interface BoardService {
	// 게시판 전체 조회
	public List<BoardsVO> boardList(BoardsVO boardsVO);

	// 게시글 단건 조회
	public PostsVO postInfo(PostsVO postsVO);

	// 게시글 등록
	public int insertPost(PostsVO postsVO);

	// 게시글 수정
	public int updatePostInfo(PostsVO postsVO);

	// 게시글 삭제
	public int deletePost(int postsNo);

	// 메인페이지 게시판
	public List<BoardsVO> selectBoardMain(BoardsVO boardsVO);

	// 게시판 단건조회
	public BoardsVO selectBoard(BoardsVO boardsVO);

	// 게시글별 댓글 전체조회
	public List<CommentsVO> selectCommentsPost(CommentsVO commentsVO);

	// 댓글 등록
	public int insertCommentInfo(CommentsVO commentsVO);

	// 댓글 수정
	public int updateCommentInfo(CommentsVO commentsVO);

	// 댓글 삭제
	public int deleteComment(int commentsNo);

	// 게시물 총 갯수
	public int countPost(PostsVO postsVO);

	// 게시판별 게시글 전체 조회
	public List<PostsVO> postListBoard(PagingVO pagingVO, PostsVO postsVO);
	
	// 공지사항만 가져오기
	public List<PostsVO> postListBoardNo1(PostsVO postsVO);

	// 댓글 번호 가져오기
	public CommentsVO selectCommentNo(int commentsNo);

	// 공지사항리스트만 가져오기
	public List<PostsVO> getAnnouncements();
}
