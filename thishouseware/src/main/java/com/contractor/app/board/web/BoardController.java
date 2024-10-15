package com.contractor.app.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;

@Controller
public class BoardController {
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardsVO> list = boardService.boardList();
		// 페이지에 전달
		model.addAttribute("boards", list);
		return "board/boardList";
	}
}
