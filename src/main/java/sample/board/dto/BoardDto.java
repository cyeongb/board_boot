package sample.board.dto;


import lombok.Data;


// @Data
// 롬복으로 모든 필드의 게터 , 세터를 생성하고 toString, hashCode, equals 메서드도 생성한다.
@Data
public class BoardDto {  // 디비의 테이블 컬럼과 매칭된다. 자바로는 카멜 케이스를 사용한다.
						 // 디비에서는 스네이크 표기법을 사용하기 때문에 application.properties에서 설정을 해줘서 매핑시켜준다.
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdTime;
	private String creatorId;
	private String updatedTime;
	private String updaterId;
	
}
