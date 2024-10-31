package com.contractor.app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.CommentsVO;
import com.contractor.app.board.service.PagingVO;
import com.contractor.app.board.service.PostsVO;
import com.contractor.app.common.service.CommonCodeService;

import lombok.RequiredArgsConstructor;

/* 테스트용 */
@RequiredArgsConstructor
@Controller
public class HomeController {
	private final BoardService boardService;

	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/home2")
	public String homePage2() {
		return "home2";
	}
	
	
	// 메인페이지 : URI - main / RETURN - /main
	//@GetMapping("/main")
	public String main(Model model, PagingVO pagingVO, PostsVO postsVO) {
		List<PostsVO> posts = boardService.postListBoardNo1(postsVO);
		model.addAttribute("posts", posts);
		return "main";
	}
	
	@GetMapping("/main")
	public String postList(Model model, PostsVO postsVO, BoardsVO boardsVO, PagingVO pagingVO,
			@RequestParam(value = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", defaultValue = "10", required = false) String cntPerPage,
			@RequestParam(required = false) String searchWord) {
		boardsVO.setBoardsNo(1);
		// 게시글 총 개수 가져오기
		int total = boardService.countPost(postsVO);

		// 페이징 및 검색어 설정
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		if (searchWord != null && !searchWord.isEmpty()) {
			pagingVO.setSearchWord(searchWord.trim());
		}

		// DB 쿼리에서 사용할 start, end값 계산 (총 개수 추가)
		pagingVO.calcStartEnd(Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), total);

		// 게시글과 게시판 정보 가져오기

		BoardsVO board = boardService.selectBoard(boardsVO);
		List<PostsVO> list = boardService.postListBoard(pagingVO, postsVO);

		// 페이지에 전달
		model.addAttribute("paging", pagingVO);
		model.addAttribute("posts", list);
		model.addAttribute("board", board);

		return "main";
	}
	
}
