package com.contractor.app.board.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.board.mapper.BoardMapper;
import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	
	@Autowired // 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<BoardsVO> boardList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public int insertBoard(BoardsVO boardsVO) {
		return boardMapper.insertBoardInfo(boardsVO);
	}
	
}
