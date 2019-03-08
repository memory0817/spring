package com.kh.myapp.login;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class LoginCmd {
	
	@Pattern(regexp="^\\w+@\\w+\\.\\w+(\\.\\w+)?$", message="ex)aaa@bbb.com")
	private String id; //회원아이디
	@Size(min=4,max=10,message="비밀번호는 4~30자리로 입력해야합니다.")
	private String pw;
	private String tel;
	private String birth;
	
}
