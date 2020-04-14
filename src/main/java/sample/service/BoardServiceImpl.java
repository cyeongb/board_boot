package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.board.dto.BoardDto;
import sample.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	// 디비에 접근하는 DAO 빈을 선언한다.
	@Autowired
	private BoardMapper boardMapper;

	// 사용자 요청을 처리하기 위한 비즈니스 로직을 구현한다.
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		System.out.println("selectBoardList 서비스 옴");
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx); // 조회수 +1 해주는거
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		return board;
	}



}
