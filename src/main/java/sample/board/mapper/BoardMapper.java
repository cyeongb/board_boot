package sample.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import sample.board.dto.BoardDto;

@Mapper  // 마이바티스의 매퍼 인터페이스 임을 선언한다.
public interface BoardMapper {
	
	// 인터페이스 이기 때문에 메서드의 이름과 반환 형식만 지정 가능하다.
	// 여기서 지정한 메서드 이름이 SQL의 이름과 동일해야 한다.
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	void updateHitCount(int boardIdx) throws Exception;

}
