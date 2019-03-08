package com.kh.myapp.notice;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class NoticeDTO {
	

	private int nnum;         //게시글번호
	@Size(min=4, max=30, message="제목은 4~30자 입력가능합니다!")
	private String ntitle;    //제목
	private Date ncdate;      //작성일
	private Date nudate;      //수정일
	private int nhit;         //조회수
	@Size(min=4, max=300, message="내용은 4~100자 입력가능합니다!")
	private String ncontent;  //본문내용
	private int ngroup;       //답변그룹
	private int nstep;        //답변글의 단계
	private int nindent;      //답변글의 들여쓰기
	

}
