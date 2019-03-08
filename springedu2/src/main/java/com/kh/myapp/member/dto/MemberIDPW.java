package com.kh.myapp.member.dto;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class MemberIDPW {
	
	@Pattern(regexp="^\\w+@\\w+\\.\\w+(\\.\\w+)?$", message="ex)aaa@bbb.com")
	private String id; //회원아이디
	private String tel;
	private String birth;

}
