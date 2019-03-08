<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>

//공지사항 
var nnum = 1;		// 원글 번호
var rereqPage = 1;	// 요청페이지


//게시판
var bnum = 1;		// 원글 번호
var rereqPage = 1;	// 요청페이지

console.log("asgasg");

$(function(){ 
	
	//글목록 보내기
	mainList(rereqPage);
	mainListtwo(rereqPage);
	
});

//요청페이지에 대한 메인 목록 가져오기
function mainList(rereqPage){ 
	var str="";
	console.log("abcde");
	$.ajax({ 
		type: "GET",
		url: "/main/posts/list/"+nnum+"/"+rereqPage,
		dataType:"json",
		
		success: function(data,status,xhr){ 
			
			console.log(data);
			console.log(data.item);
			str += "<table class='table table-hover table-borderless table-sm' summary='게시글 목록'>";
			str += "<colgroup>";
			str += "<col width='80%'>";
			str += "<col width='20%'>";
			str += "</colgroup>";
			str += "<thead>";
			str += "<tr>";
			str += "<th scope='col' class='table-info' colspan='2'>&nbsp;공지사항 최신글</th>";
			str += "</tr>";
			str += "</thead>";
			
			$.each(data,function(idx,rec){
				
/* 			console.log("rec: "+rec.ntitle); 
			console.log("abcdef"); */
			
			str += "<tbody>"; 
			str += "<tr>";		    
			str += "<td>";
			str += "<a href='/notice/view?nnum="+rec.nnum+"&reqPage=1 'class='text-decoration-none text-reset text-danger' >"+rec.ntitle+"</td></a>"; 
			str += "<td>관리자</td>";
			str += "</tr>";
			str += "</tbody>";
			
			});
			
			str += "</table>";	
			 $("#mainList").html(str); 
			console.log(str);
			
		},
		error: function(xhr,status,err){
			console.log("xhr"+xhr);
			console.log("status"+status);
			console.log("err"+err);
		}
		

		
	});
	
	
	
	
}





</script>


<div class="container">
<div class="table-responsive">


	<!-- 공지사항 글 목록   -->
	<div id="mainList"></div>	


</div>
</div>