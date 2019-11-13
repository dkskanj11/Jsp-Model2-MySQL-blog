<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 변경</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="/blog/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<form action="/blog/user?cmd=profile" method="POST" enctype="multipart/form-data">
<div class="container" style="right: 100px; margin-left: 700px; margin-top:100px;">
  <h2>프로필 사진 변경</h2>
  <div class="card" style="width:400px; right: 50px;">
    <img id="img__wrap" class="card-img-top" src="${user.userProfile}" style="width:100%">
    <div class="card-body">
      <h4 class="card-title">${sessionScope.user.username}</h4>
      <input id="img__input" type= "file" name="userProfile" value="프로필 변경하기">
      <input type="submit"class="btn btn-primary"  value="확인" style="margin-left: 300px;">
      
    </div>
  </div>
  </div>
  </form>
  <script>
  $("#img__input").on("change", handleImgFile);

  function handleImgFile(e){
  	console.log(e);
  	console.log(e.target.files);
  	console.log(e.target.files[0]);
  	var f = e.target.files[0];
  	
  	if(!f.type.match("image.*")) {
  		alert("이미지 타입이 아닙니다.");
  		return;
  	}
  	
  	var reader = new FileReader();
  	
  	reader.onload = function(e) {
  		console.log("==============")
  		console.log(e.target);
  		console.log(e.target.result); //파일 로딩이 성공한 결과
  		$("#img__wrap").attr("src", e.target.result);
  	}
  	
  	reader.readAsDataURL(f);
  	}
  
  </script>
</body>
</html>