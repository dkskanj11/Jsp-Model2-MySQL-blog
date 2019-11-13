<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
let replyJS = {
		id: 1,
		boardId: 5,
		userId: 3,
		content: "글이 참 좋네요",
		createDate: null
}

function send(){
	//replyJS를 json으로 변환 JSON.stringify()
	var replyString = JSON.stringify(replyJS);
	console.log(replyString);
	// ajax호출 /blog/test/reply > POST > application/json, utf-8
	fetch("http://localhost:8000/blog/test/reply", {
		method : "POST",
		body: replyString,
		headers: {
			'Content-Type': 'application/json; charset=UTF-8'
		}
	}).then(function(response) {
		return response.text();
		
	}).then(function(response) {
		replyJS = response;
	});
}
</script>
</head>
<body>
<button onClick="send()">얍얍</button>
</body>
</html>