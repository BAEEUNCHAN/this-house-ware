package com.contractor.app.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contractor.app.board.service.BoardService;
import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.CommentsVO;
import com.contractor.app.board.service.PagingVO;
import com.contractor.app.board.service.PostsVO;
import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.security.service.LoginUserVO;
import com.contractor.app.util.EmpAuthUtil;

import lombok.RequiredArgsConstructor;

/**
 * 게시판 관리
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	private final CommonCodeService commonCodeService;
	private final EmployeeService employeeService;
	private final EmpAuthUtil empAuthUtil;

//	@Autowired
//	public BoardController(BoardService boardService, CommonCodeService commonCodeService) {
//		this.boardService = boardService;
//		this.commonCodeService = commonCodeService;
//	}

	// 메인페이지 : URI - boardMainPage / RETURN - board/boardMainPage
	@GetMapping("/boardMainPage")
	public String boardMainPage(Model model, BoardsVO boardsVO, PostsVO postsVO, Authentication authentication) {
		// 로그인 사용자 정보 가져오기
		LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
		int departmentNo = loginUserVO.getEmpVO().getDepartmentNo();
		String positionCode = loginUserVO.getEmpVO().getPositionCode();
		List<DepartmentVO> upperDepartmentNo = employeeService.selectUpperDepartmentNo(departmentNo);
		DepartmentVO departments = employeeService.getDepartmentBydeptNo(departmentNo);

		// 포지션 코드가 a1(사장) 또는 a2(관리자)일 경우 전체 boards를 조회
		if (positionCode.equals("a1") || positionCode.equals("a2")) {
			List<BoardsVO> boards = boardService.selectBoardMain(boardsVO);
			model.addAttribute("boards", boards);
		} else if (positionCode.equals("a3")) { // 포지션 코드가 a3(본부장)일 경우
			List<BoardsVO> boards = boardService.selectBoardMain(boardsVO);

			// 공지사항, 전체 게시판, 해당 본부 부서 게시판만 필터링
			List<BoardsVO> filteredBoards = boards.stream()
					.filter(board -> board.getBoardsType().equals("공지사항") || board.getBoardsType().equals("전체게시판")
							|| (upperDepartmentNo.stream()
									.anyMatch(dept -> dept.getDepartmentName().equals(board.getTitle()))))
					.collect(Collectors.toList());

			model.addAttribute("boards", filteredBoards);
		} else if (positionCode.equals("a4") || positionCode.equals("a5")) { // 포지션 코드가 a4(팀장) 또는 a5(사원)일 경우
			List<BoardsVO> boards = boardService.selectBoardMain(boardsVO);

			// 공지사항, 전체 게시판, 해당 부서 게시판만 필터링
			List<BoardsVO> filteredBoards = boards.stream()
					.filter(board -> board.getBoardsType().equals("공지사항") || board.getBoardsType().equals("전체게시판")
							|| (departments.getDepartmentName().equals(board.getTitle())))
					.collect(Collectors.toList());

			model.addAttribute("boards", filteredBoards);
		}

		// 페이지에 전달
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

	// 게시글 등록 - 처리 : URI - postInsert / PARAMETER - PostsVO(QueryString)
	// RETURN - 단건조회 다시 호출
	@PostMapping("/postInsert")
	public String postInsertProcess(PostsVO postsVO) { // <form/> 활용한 submit
		int postsNo = boardService.insertPost(postsVO);
		return "redirect:postInfo?postsNo=" + postsNo;
	}

	// 게시글 등록 - 페이지 : URI - postInsert / RETURN - board/postInsert
	@GetMapping("/postInsert")
	public String postInsertForm(@RequestParam(value = "boardsNo", required = false) Integer boardsNo, Model model,
			PostsVO postsVO, Authentication authentication) {
		// 게시판 목록 조회 - title
		List<BoardsVO> list = boardService.boardList(null);

		// 로그인 사용자 정보 가져오기
		LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
		int departmentNo = loginUserVO.getEmpVO().getDepartmentNo();
		String positionCode = loginUserVO.getEmpVO().getPositionCode();
		List<DepartmentVO> upperDepartmentNo = employeeService.selectUpperDepartmentNo(departmentNo);
		DepartmentVO departments = employeeService.getDepartmentBydeptNo(departmentNo);

		// 게시판 유형 조회 - boardsType
		List<CommonCodeVO> boardsType = commonCodeService.selectCommonCode("0O");

		// 사장, 또는 관리자
		if (positionCode.equals("a1") || positionCode.equals("a2")) {			
			List<BoardsVO> filteredBoards = list;
			model.addAttribute("boards", filteredBoards);
		}
		// 로그인 한 계정의 포지션 코드를 체크한후 a3(본부장) 혹은 a4(팀장) 등급이면
		else if (positionCode.equals("a3")) {
			boardsType.remove(0);
			// 전체 게시판, 해당 본부 부서 게시판만 필터링
			List<BoardsVO> filteredBoards = list.stream().filter(board -> board.getBoardsType().equals("전체게시판")
					|| (upperDepartmentNo.stream().anyMatch(dept -> dept.getDepartmentName().equals(board.getTitle()))))
					.collect(Collectors.toList());
			model.addAttribute("boards", filteredBoards);
		} /*
			 * else if (positionCode.equals("a4")) { boardsType.remove(0); // 전체 게시판, 해당 부서
			 * 게시판만 필터링 List<BoardsVO> filteredBoards = list.stream().filter(board ->
			 * board.getBoardsType().equals("전체게시판") ||
			 * (departments.getDepartmentName().equals(board.getTitle()))).collect(
			 * Collectors.toList());
			 * 
			 * model.addAttribute("boards", filteredBoards); }
			 */
		else if (positionCode.equals("a4") || positionCode.equals("a5")) {
			boardsType.remove(0);			
			// 전체 게시판, 해당 본부 부서 게시판만 필터링
			List<BoardsVO> filteredBoards = list.stream().filter(board -> board.getBoardsType().equals("전체게시판")
					|| departments.getDepartmentName().equals(board.getTitle()))
					.collect(Collectors.toList());

			model.addAttribute("boards", filteredBoards);
		}
		
		// 머리글 유형 조회 - postsType
		// List<CommonCodeVO> postsType = commonCodeService.selectCommonCode("0P");
 
		Map<String, String> postsType = new HashMap<>();
		// 로그인 한 계정의 포지션 코드를 체크한후 사장, 관리자 또는 a3(본부장)이면		
		if (positionCode.equals("a1") || positionCode.equals("a2") || positionCode.equals("a3")) {
			// 공지, 필독, 일반 쓰기 권한
			postsType.put("공지", "p1");
			postsType.put("필독", "p2");
			postsType.put("일반", "p3");						
			
		} else if (positionCode.equals("a4")) {			
			postsType.put("필독", "p2");
			postsType.put("일반", "p3");	
			
		} else if (positionCode.equals("a5")) {
			postsType.put("일반", "p3");
		}

		// 게시 기간여부
		List<CommonCodeVO> postSetting = commonCodeService.selectCommonCode("0Q");

		// 페이지에 전달
		model.addAttribute("selectedBoardsNo", boardsNo);
		model.addAttribute("boardsType", boardsType);
		// model.addAttribute("boards", list);
		model.addAttribute("postsType", postsType);
		model.addAttribute("postSetting", postSetting);
		model.addAttribute("positionCode", positionCode);
		return "board/postInsert";
		// classpath:/templates/board/postInsert.html => 실제 경로
	}

	// 게시판별 게시글 전체조회 : URI - postList / RETURN - board/postList
	@GetMapping("/postList")
	public String postList(Model model, PostsVO postsVO, BoardsVO boardsVO, PagingVO pagingVO,
			@RequestParam(value = "nowPage", defaultValue = "1", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", defaultValue = "10", required = false) String cntPerPage,
			@RequestParam(required = false) String searchWord) {

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

		return "board/postList";
	}

	// 게시글 삭제 - 처리 : URI - postDelete / PARAMETER - Integer
	// RETURN - 전체조회 다시 호출
	@GetMapping("/postDelete") // QueryString : @RequestParam
	public String postDelete(@RequestParam Integer postsNo, @RequestParam Integer boardsNo, PostsVO postsVO) {
		boardService.deletePost(postsNo);
		return "redirect:postList?boardsNo=" + boardsNo;
	}

	// 게시글 단건조회 + 댓글 : URI - postInfo / PARAMETER - PostsVO(QueryString)
	// RETURN - board/postInfo
	@GetMapping("postInfo") // postInfo?key=value
	public String postInfo(PostsVO postsVO, Model model, CommentsVO commentsVO) {
		PostsVO findVO = boardService.postInfo(postsVO);
		List<CommentsVO> comments = boardService.selectCommentsPost(commentsVO);
		model.addAttribute("post", findVO);
		model.addAttribute("comments", comments);

		return "board/postInfo";
		// classpath:/templates/board/postInfo.html => 실제 경로
	}

	// 게시글 단건조회 + 댓글 : URI - postInfo / PARAMETER - PostsVO(QueryString)
	// RETURN - board/postInfo
	@PostMapping("/postInfo")
	public String postInfo(PostsVO postsVO, CommentsVO commentsVO) {
		boardService.insertCommentInfo(commentsVO);
		return "redirect:postInfo?postsNo=" + commentsVO.getPostsNo();
		// classpath:/templates/board/postInfo.html => 실제 경로
	}

	// 댓글 삭제 - 처리 : URI - commentDelete / PARAMETER - Integer
	// RETURN - 전체조회 다시 호출
	@GetMapping("/commentDelete") // QueryString : @RequestParam
	public String commentDelete(@RequestParam Integer commentsNo, CommentsVO commentsVO) {
		boardService.deleteComment(commentsNo);
		return "redirect:postInfo?postsNo=" + commentsVO.getPostsNo();
	}

	// 게시글 수정 - 페이지 : URI - postUpdate / PARAMETER - PostsVO(QueryString)
	// Return - board/postUpdate
	// => 단건조회에서 수정
	@GetMapping("postUpdate")
	public String postUpdate(PostsVO postsVO, Model model, CommentsVO commentsVO) {
		PostsVO findVO = boardService.postInfo(postsVO);
		List<CommentsVO> comments = boardService.selectCommentsPost(commentsVO);

		model.addAttribute("post", findVO);
		model.addAttribute("comments", comments);

		return "board/postUpdate";
	}

	// 게시글 수정 - 처리 : URI - postUpdate / PARAMETER - PostsVO(JSON) =>
	// @ResponseBody 써야함
	// Return - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("postUpdate")
	public String postUpdate(PostsVO postsVO) {
		boardService.updatePostInfo(postsVO);
		return "redirect:postInfo?postsNo=" + postsVO.getPostsNo();
	}

	// 댓글 수정 - 페이지 : URI - commentUpdate / PARAMETER - CommentsVO(QueryString)
	// Return - board/commentUpdate
	// => 단건조회에서 수정
	@GetMapping("commentUpdate")
	public String commentUpdate(PostsVO postsVO, Model model, CommentsVO commentsVO,
			@RequestParam("commentsNo") int commentsNo) {
		PostsVO findVO = boardService.postInfo(postsVO);
		List<CommentsVO> comments = boardService.selectCommentsPost(commentsVO);
		CommentsVO comment = boardService.selectCommentNo(commentsNo);

		model.addAttribute("post", findVO);
		model.addAttribute("comments", comments);
		model.addAttribute("commentNo", comment.getCommentsNo());

		return "board/commentUpdate";
	}

	// 댓글 수정 - 처리 : URI - commentUpdate / PARAMETER - CommentsVO(JSON) =>
	// @ResponseBody 써야함
	// Return - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("commentUpdate")
	public String commentUpdate(PostsVO postsVO, CommentsVO commentsVO) {
		boardService.updateCommentInfo(commentsVO);

		return "redirect:postInfo?postsNo=" + commentsVO.getPostsNo();
	}

}