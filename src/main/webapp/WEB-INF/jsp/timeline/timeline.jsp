<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 나의 마크업 --%>

<%-- <div>
	<div class="d-flex justify-content-center">
		<!-- 세션정보 없을때 글쓰기 항목 안보임 -->
		<c:if test="${not empty userId}">
		<div class="border">
			<textarea style="border:none" placeholder="내용을 입력해주세요." rows="5" cols="50"></textarea>
			<div class="d-flex justify-content-between">
				<div></div>
				<button type="submit" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
	</div>
	
	<div class="">
		<div class="d-flex justify-content-center mt-3">
			<div class="d-flex justify-content-between w-50 border">
				<div class="font-weight-bold">${userId}</div>
				<div>
					<img src="/static/img/timeline/more-icon.png" alt="더보기" width="40">
				</div>
			</div>
		</div>
		
		<div class="d-flex justify-content-center">
			<div>
				<img src="https://cdn.pixabay.com/photo/2023/10/28/18/02/songbird-8348139_1280.png" alt="새" width="532" height="600">
			</div>
		</div>
	</div>
</div> --%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
	
	
		<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
				
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 효과 --%>
					<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none">
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일명 노출 --%>
					<div id="fileName" class="ml-2"></div>
					
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div> <%--// 글쓰기 영역 끝 --%>
		
		
		
		
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			
			<c:forEach items="${posts}" var="post">
			<%-- 카드 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${post.userId}</span>
					
					<a href="#" class="more-btn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${post.imagePath}" class="w-100" height="600" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn">
						<img id="filledHeart" src="/static/img/timeline/heart-icon-filled.png" width="18" height="18" alt="filled heart" class="">
						<img id="emptyHeart" src="/static/img/timeline/heart-icon.png" width="18" height="18" alt="filled heart" class="d-none">
					</a>
				좋아요 156개
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${post.userId}</span>
					<span>${post.contents}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
				
					<c:forEach items="${comments}" var="comment">
					<c:if test="${comment.postId eq post.id}">
					<%-- 댓글 내용들 --%>
					<div class="card-comment m-1">
						<span class="font-weight-bold">${comment.userId}</span>
						<span>${comment.comments}</span>
						
						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="comment-del-btn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
						</a>
					</div>
					</c:if>
					</c:forEach>
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${post.id}">게시</button>
					</div>
				</div> <%--// 댓글 목록 끝 --%>
			</div> <%--// 카드1 끝 --%>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>

<script>
	$(document).ready(function() {
		
		
		$(".comment-btn").on("click", function() {
			
			let postId = $(this).data("post-id");
			let comment = $(this).prev().val();
			
			if(!comment) {
				alert("댓글을 입력해주세요.");
				return;
			}
			
			/* $.ajax({
				//request
				type:"GET"
				, url:"/comment/create"
				, data:{"postId":postId, "comment":comment}
			
				//response
				, success:function(data){
					if(data.code == 200) {
						
						location.reload();       // 위치 그대로?
						return;
					} else {
						alert(data.errorMessage);
					}
				} 
				
				, error(request,status,error) {
					alert("등록 실패");
				}				
			}); */
			
			let url = "/comment/create";
			let param = {"postId":postId, "comment":comment}
			
			$.post(url, param)
			.done(function(data) {
				if(data.code == 200) {
					
					location.reload();       // 위치 그대로?
					return;
				} else {
					alert(data.errorMessage);
				}
			});
				
		
		})
		
		
		
		// 파일이미지 클릭 => 숨겨져 있던 type="file"을 동작시킨다.
		$("#fileUploadBtn").on("click", function(e) {
			e.preventDefault();	// a 태그의 올라가는 현상 방지
			$("#file").click();
		});
		
		// 이미지를 선택하는 순간 -> 유효성 확인 및 업로드 된 파일명 노출
		$("#file").on("change", function(e) {
			let fileName = e.target.files[0].name;
			//console.log(fileName);
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			console.log(ext);
			
			if(ext != "jpg" && ext != "jpeg" && ext != "png" && ext != "gif"){
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#file").val("");       // 이미지 파일 등록안했으면 파일을 비운다.
				return;
			}
			
			// 유효성 통과한 이미지 이름   =>   노출
			// text function으로 태그사이에 파일명을 껴 넣는다.
			$("#fileName").text(fileName);
		});
		
		
		// 글쓰기 버튼 클릭
		$("#writeBtn").on("click", function() {
			
			let contents = $("#writeTextArea").val();
			let fileName = $("#fileName").text();
			
			// 내용 유효성 검사
			if (!contents) {
				alert("게시할 내용을 입력해주십시오.");
				return;
			}
			if (!fileName) {
				alert("사진을 등록해주십시오.");
				return;
			}
			
			let formData = new FormData();
			formData.append("contents", contents);
			formData.append("file",$("#file")[0].files[0])
			
			$.ajax({
				
				// request
				type:"POST"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"   // 약속된 정의어  =>  파일 업로드를 위한 필수 설정!!!!!!!!!!!
				, processData:false // 파일 업로드를 위한 필수 설정!!!!!!!!!!!
				, contentType:false // 파일 업로드를 위한 필수 설정!!!!!!!!!!!
				
				//response
				, success:function(data) {
					if (data.code == 200) {
						alert("저장되었습니다.");
						location.href="/timeline/timeline-view";
					} else {
						// 로직 실패
						
					}
				}
				
			});
			
			
		});
		
	});


</script>



