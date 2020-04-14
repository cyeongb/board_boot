package sample.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.board.dto.BoardDto;
import sample.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 비지니스 로직을 처리하는 서비스 빈을 연결한다.

	// RequestMapping 으로 저 주소를 호출하면 , 호출된 주소와 어노테이션의 값이 동일한 메서드를 찾아서 실행한다.
	// 즉 클라이언트에서 요청한 주소와 그것을 수행할 메서드를 연결시킨다.
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {

		// 모델엔 뷰
		// 호출된 요청의 결과를 보여줄 뷰를 지정한다.(= "/templates/board/boardList.html")
		// thymeleaf 와같은 템플릿을 사용할 경우엔 스프링 부트의 자동 설정 기능으로 .html과같은 접미사를 생략할수 있다.
		ModelAndView mav = new ModelAndView("/board/boardList");
		System.out.println("컨트롤러 - 모델앤뷰::" + mav);

		// List 인터페이스를 이용해서 boardservice의 비즈니스 로직을 수행한다.
		List<BoardDto> list = boardService.selectBoardList();
		System.out.println("컨트롤러 _ 서비스에서 가져온 리스트 ::" + list);
		mav.addObject("list", list);
		return mav;

	}

	// ------------------------------------------------ 게시글 등록화면 호출
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board/boardWrite";

	}

	// ------------------------------------------------------ 게시글 등록
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	// ------------------------------------------------ 게시글 상세화면
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		ModelAndView mav = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mav.addObject("board",board);
		return mav;

	}
	

}
