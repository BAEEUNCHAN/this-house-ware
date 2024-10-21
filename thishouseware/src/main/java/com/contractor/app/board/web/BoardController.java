package com.contractor.app.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String boardList(Model model, BoardsVO boardsVO) {
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

	// 게시글 단건조회 : URI - postInfo / PARAMETER - PostsVO(QueryString)
	// RETURN - board/postInfo
	@GetMapping("postInfo") // postInfo?key=value
	public String postInfo(PostsVO postsVO, Model model) {
		PostsVO findVO = boardService.postInfo(postsVO);
		
		model.addAttribute("post", findVO);
		
		return "board/postInfo";
		// classpath:/templates/board/postInfo.html => 실제 경로
	}

	// 게시글 등록 - 페이지 : URI - postInsert / RETURN - board/postInsert
	@GetMapping("/postInsert")
	public String postInsertForm(Model model, PostsVO postsVO) {
		// 게시판 목록 조회
		List<BoardsVO> list = boardService.boardList(null);

		// 게시판 유형 조회
		List<CommonCodeVO> boardsType = commonCodeService.selectCommonCode("0O");

		// 머리글 유형 조회
		List<CommonCodeVO> postsType = commonCodeService.selectCommonCode("0P");

		// 게시 기간여부
		List<CommonCodeVO> postSetting = commonCodeService.selectCommonCode("0Q");

		// 페이지에 전달
		model.addAttribute("boardsType", boardsType);
		model.addAttribute("boards", list);
		model.addAttribute("postsType", postsType);
		model.addAttribute("postSetting", postSetting);

		return "board/postInsert";
		// classpath:/templates/board/postInsert.html => 실제 경로
	}

	// 게시글 등록 - 처리 : URI - postInsert / PARAMETER - PostsVO(QueryString)
	// RETURN - 단건조회 다시 호출
	@PostMapping("/postInsert")
	public String postInsertProcess(PostsVO postsVO) { // <form/> 활용한 submit
		int postsNo = boardService.insertPost(postsVO);
		return "redirect:postInfo?postsNo=" + postsNo;
	}
	
	// 게시글 삭제 - 처리 : URI - postDelete / PARAMETER - Integer
    //             RETURN - 전체조회 다시 호출
	@GetMapping("/postDelete") // QueryString : @RequestParam
	public String postDelete(@RequestParam Integer postsNo) {
		boardService.deletePost(postsNo);
		return "redirect:postList";
	}
}
