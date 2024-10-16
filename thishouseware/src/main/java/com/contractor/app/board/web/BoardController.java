package com.contractor.app.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.PostsVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 메인페이지 : URI - boardMainPage / RETURN - board/boardMainPage
	@GetMapping("/boardMainPage")
	public String boardMainPage() {
		return "board/boardMainPage";
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<BoardsVO> list = boardService.boardList();
		// 페이지에 전달
		model.addAttribute("boards", list);
		return "board/boardList";
	}
	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("/boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
		// classpath:/templates/board/boardInsert.html => 실제 경로
	}   
	    
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("/boardInsert")
	public String boardInsertProcess(BoardsVO boardsVO) { // <form/> 활용한 submit
		//int eid = boardService.insertBoard(boardsVO);
		return "board/boardList";
		//return "redirect:boardInfo?bno=" + eid;
	}

	// 등록 - 페이지 : URI - postInsert / RETURN - board/postInsert
	@GetMapping("/postInsert")
	public String postInsertForm(Model model) {
		// 게시판 목록 조회
		List<BoardsVO> list = boardService.boardList();
		// 페이지에 전달
		model.addAttribute("boards", list);
		return "board/postInsert";
		// classpath:/templates/board/postInsert.html => 실제 경로
	}   
	
	// 등록 - 처리 : URI - postInsert / PARAMETER - PostsVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("/postInsert")
	public String postInsertProcess(PostsVO postsVO) { // <form/> 활용한 submit
		//int eid = boardService.insertPost(postsVO);
		return "board/postInsert";
		//return "redirect:postInfo?pno=" + eid;
	}
}
