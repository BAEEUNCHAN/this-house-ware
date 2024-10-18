package com.contractor.app.board.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.board.mapper.BoardMapper;
import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
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
	public List<PostsVO> postList(PostsVO postsVO) {
		return boardMapper.selectPostAll(postsVO);
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

	
}
