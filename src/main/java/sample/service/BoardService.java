package sample.service;

import java.util.List;

import sample.board.dto.BoardDto;


public interface BoardService {
	/* 서비스영역은 Service 인터페이스와 ServiceImpl 클래스로 구성된다.
	 * 이렇게 인터페이스와 인터페이스를 실재로 구현하는 클래스로 나눈것에는 장점이 여러가지 있다.
	 * 1) 느슨한 결합으로 서로의 의존관계를 최소화한다.
	 * 2) 기능의 변화에도 최소한의 수정으로 개발할 수 있다.
	 * 3) 모듈화로 재사용성을 높인다.
	 * 4) 스프링의 IOC , DI를 사용할 수 있다.
	 * */

	
	 List<BoardDto> selectBoardList() throws Exception;
	 void insertBoard(BoardDto board)throws Exception;
	 BoardDto selectBoardDetail(int boardIdx) throws Exception;
	

}
