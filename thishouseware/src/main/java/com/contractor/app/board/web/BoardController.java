package com.contractor.app.board.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.PostsVO;
import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private CommonCodeService commonCodeService;
	
	@Autowired
	public BoardController(BoardService boardService, CommonCodeService commonCodeService) {
		this.boardService = boardService;
		this.commonCodeService = commonCodeService;
	}

	// 메인페이지 : URI - boardMainPage / RETURN - board/boardMainPage
	@GetMapping("/boardMainPage")
	public String boardMainPage() {
		return "board/boardMainPage";
	}

	// 게시판 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("/boardList")
	public String boardList(Model model,BoardsVO boardsVO) {
		List<BoardsVO> list = boardService.boardList(boardsVO);
		// 페이지에 전달
		model.addAttribute("boards", list);
		return "board/boardList";
	}
	
	// 게시글 전체조회 : URI - postList / RETURN - board/postList
	@GetMapping("/postList")
	public String postList(Model model, PostsVO postsVO) {
		List<PostsVO> list = boardService.postList(postsVO);
		// 페이지에 전달
		model.addAttribute("posts", list);
		return "board/postList";
	}

	// 등록 - 페이지 : URI - postInsert / RETURN - board/postInsert
	@GetMapping("/postInsert")
	public String postInsertForm(Model model, PostsVO postsVO) {
		// 게시판 목록 조회
		List<BoardsVO> list = boardService.boardList(null);
		
		// 게시판 유형 조회
		List<CommonCodeVO> boardsType = commonCodeService.selectCommonCode("0O");
		
		// 머리글 유형 조회
		List<CommonCodeVO> postsType = commonCodeService.selectCommonCode("0P");
		
		// 페이지에 전달
		model.addAttribute("boardsType", boardsType);
		model.addAttribute("boards", list);
		model.addAttribute("postsType", postsType);
		
		return "board/postInsert";
		// classpath:/templates/board/postInsert.html => 실제 경로
	}

	// 등록 - 처리 : URI - postInsert / PARAMETER - PostsVO(QueryString)
	// RETURN - 단건조회 다시 호출
	@PostMapping("/postInsert")
	public String postInsertProcess(PostsVO postsVO) { // <form/> 활용한 submit
		// int eid = boardService.insertPost(postsVO);
		return "board/postInsert";
		// return "redirect:postInfo?pno=" + eid;
	}
}
