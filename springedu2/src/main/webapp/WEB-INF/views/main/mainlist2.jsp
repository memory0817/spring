<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

 var bnum = 1;		// 원글 번호
 var reqPage = 1;	// 요청페이지



$(function(){ 
	
	//글목록 보내기
	mainList(rereqPage);
	mainList2(reqPage);
	
	
});


//요청페이지에 대한 메인 목록 가져오기
function mainList2(reqPage){ 
	var str="";
	console.log("abcde");
	$.ajax({ 
		type: "GET",
		url: "/main/posts/list2/"+bnum+"/"+reqPage,
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
			str += "<th scope='col' class='table-info' colspan='2'>&nbsp;게시판 최신글</th>";
			str += "</tr>";
			str += "</thead>";
			
			$.each(data,function(idx,rec){
							
			str += "<tbody>"; 
			str += "<tr>";		    
			str += "<td>";
			str += "<a href='/bbs/view?bnum="+rec.bnum+"&reqPage=1 'class='text-decoration-none text-reset text-danger' >"+rec.btitle+"</td></a>"; 
			str += "<td>"+rec.bnickname+"</td>";
			str += "</tr>";
			str += "</tbody>";
			
			});
			
			str += "</table>";	
			$("#mainList2").html(str);
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
		<div id="mainList2"></div>	
	</div>
</div>