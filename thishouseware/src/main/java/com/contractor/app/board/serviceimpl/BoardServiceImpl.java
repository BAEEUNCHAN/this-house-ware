package com.contractor.app.board.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.board.mapper.BoardMapper;
import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.CommentsVO;
import com.contractor.app.board.service.PagingVO;
import com.contractor.app.board.service.PostsVO;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;

	@Autowired // 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardsVO> boardList(BoardsVO boardsVO) {
		return boardMapper.selectBoardAll(boardsVO);
	}


	@Override
	public PostsVO postInfo(PostsVO postsVO) {
		return boardMapper.selectPostInfo(postsVO);
	}

	@Override
	public int insertPost(PostsVO postsVO) {
		return boardMapper.insertPostInfo(postsVO);
	}

	@Override
	public int deletePost(int postsNo) {
		return boardMapper.deleteBoradInfo(postsNo);
	}

	@Override
	public List<BoardsVO> selectBoardMain(BoardsVO boardsVO) {
		return boardMapper.selectBoardMain(boardsVO);
	}

	@Override
	public BoardsVO selectBoard(BoardsVO boardsVO) {
		return boardMapper.selectBoard(boardsVO);
	}


	@Override
	public int insertCommentInfo(CommentsVO commentsVO) {
		return boardMapper.insertCommentInfo(commentsVO);
	}

	@Override
	public int countPost(PostsVO postsVO) {
		return boardMapper.countPost(postsVO);
	}


	@Override
	public List<PostsVO> postListBoard(PagingVO pagingVO, PostsVO postsVO) {
		return boardMapper.selectPostBoard(pagingVO, postsVO);
	}

	@Override
	public List<CommentsVO> selectCommentsPost(CommentsVO commentsVO) {
		return boardMapper.selectCommentsPost(commentsVO);
	}

	@Override
	public int deleteComment(int commentsNo) {
		return boardMapper.deleteComment(commentsNo);
	}

	@Override
	public int updatePostInfo(PostsVO postsVO) {
		return boardMapper.updatePostInfo(postsVO);
	}

	@Override
	public int updateCommentInfo(CommentsVO commentsVO) {
		return boardMapper.updateCommentInfo(commentsVO);
	}

	@Override
	public CommentsVO selectCommentNo(int commentsNo) {
		return boardMapper.selectCommentNo(commentsNo);
	}

}
