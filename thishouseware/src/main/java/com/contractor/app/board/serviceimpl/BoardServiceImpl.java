package com.contractor.app.board.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		// 현재 날짜에서 시간 제거 후 날짜만 남기기
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = null;
		try {
			nowDate = dateFormat.parse(dateFormat.format(now));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (postsVO.getPostSetting() == "q1") {
			if (postsVO.getPostStartTime().compareTo(nowDate) <= 0
					&& postsVO.getPostEndTime().compareTo(nowDate) >= 0) {
				postsVO.setDisplay("q1"); // 게시
			} else if (postsVO.getPostStartTime().compareTo(nowDate) > 0) {
				postsVO.setDisplay("q2"); // 비게시
			}
		} else {
			postsVO.setDisplay("q1"); // 기본적으로 게시 상태로 설정
		}

		boardMapper.insertPostInfo(postsVO);
		return postsVO.getPostsNo();
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
	public List<PostsVO> postListBoardNo1(PostsVO postsVO) {
		return boardMapper.selectPostBoardNo1(postsVO);
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
