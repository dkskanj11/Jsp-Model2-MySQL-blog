<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Fetch API 사용하기 - promise</h1>
	<button onclick="getNum1()">데이터 가져오기</button>
	<script>
		let num1 = 0;
		let num2 = 0;
		let sum = 0;

		function getNum1() {

			fetch("http://localhost:8000/blog/test2", {
				method : "POST",
				body : "안녕" //json, String, object, number
			}).then(function(data) {
				return data.text();
				//return data.json();
			}).then(function(data) {
				num1 = data; //100
				getNum2();
			});

		}
		function getNum2() {

			fetch("http://localhost:8000/blog/test3", {
				method : "POST"
			}).then(function(data) {
				return data.text();
				//return data.json();
			}).then(function(data) {
				num2 = data; //50

				sum = Number(num1) + Number(num2); // Number >> string 을 숫자로 변환
				console.log(sum);
			});

		}
	</script>
</body>
</html>