<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/include/nav.jsp"%>

<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">
		<div class="row">

			<div class="col-lg-12">
				<form class="row contact_form" action="/blog/user?cmd=join" method="post" onsubmit="return vaildateCheck()">
					<div class="col-md-10">
						<div class="form-group">
							<input type="text" class="form-control" id="username" name="username" placeholder="아이디를 입력하세요" required="required" maxlength="20">
						</div>
						<span id="username_input"></span>
					</div>
					
					<div class="col-md-2">
						<div class="form-group float-right">
							<a style="cursor: pointer;" class="blog_btn" onClick="usernameCheck()">중복확인</a>
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요" required="required" maxlength="20">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="비밀번호를 한번 더 입력하세요" required="required" maxlength="20">
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<input type="email" class="form-control" name="email" placeholder="이메일을 입력하세요" required="required" maxlength="40">
						</div>
					</div>
					<!-- 도로명 주소 -->
					
					<div class="col-md-10">
						<div class="form-group">
							<input type="text" class="form-control" id="roadFullAddr" name="address" placeholder="도로명 주소" required="required" readonly>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-group float-right">
						<a style="cursor:pointer;" class="blog_btn" onClick="goPopup()">주소 찾기</a>
						</div>
						</div>
					<!-- 도로명 주소 끝 -->
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Finish</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<!--================Contact Area =================-->
<br>
<br>

<script>

var usernameDuplicateCheck = false;
//아이디 중복 확인
function usernameCheck() {
	var username = document.querySelector("#username").value;
	
	fetch("/blog/api/user?username="+username).then(function(r){
		return r.text();
	}).then(function(r){
		var status = r; //ok 중복되지 않음
		var et = document.querySelector("#username_input");
		
		if(status === "ok") {
			et.innerHTML = "<font style ='color:green; font-weight: bold; font-size: 15px;'>사용할 수 있는 아이디 입니다.</font>";
			usernameDuplicateCheck = true;
		}else {
			et.innerHTML = "<font style ='color: red; font-weight: bold; font-size: 15px;'>중복된 아이디 입니다.</font>";
			usernameDuplicateCheck = false;
			
		}
	});
}

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/blog/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
}

//주소입력 버튼을 눌리면 콜백된다.
function jusoCallBack(roadFullAddr){
		var juso = document.querySelector('#roadFullAddr');
		juso.value = roadFullAddr;
}
	function vaildateCheck() {
		var password = document.querySelector('#password').value;
		var passwordCheck = document.querySelector('#passwordCheck').value;
		var roadFullAddr = document.querySelector('#roadFullAddr').value;
		
		if(roadFullAddr == '') {
			alert('주소를 입력하세요.')
			return false;	
		}
		
		if (password === passwordCheck) {
			console.log('비밀번호가 동일합니다.')
			return true;
		} else {
			console.log('비밀번호가 틀렸습니다.')
			alert('비밀번호가 동일하지 않습니다. 다시 입력해주세요.')
			return false;
		}
	}
</script>
<%@ include file="/include/footer.jsp"%>