<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>ajax 테스트</H1>
	<hr>

	<div id="demo">
		1
		<button type="button" onclick="loadDoc()">Change Content</button>
	</div>



	<script>
		var reply = {
			id : null,
			boardId : 1,
			userId : 3,
			content : 'input태그에 적힌 값을 들고 온다',
			createDate : null
		}
		console.log(reply);

		var replyString = JSON.stringify(reply);
		console.log(replyString);

		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					console.log(this.responseText);
					var jsonData = JSON.parse(this.responseText);
					console.log(jsonData)
					document.querySelector("#demo").innerHTML = jsonData.name
							+ " " + jsonData.sal;
				}
			};
			xhttp.open("POST", "http://localhost:8000/blog/test", true);
			xhttp.setRequestHeader("Content-type", "application/json");
			xhttp.send(replyString);
		}
	</script>
</body>
</html>