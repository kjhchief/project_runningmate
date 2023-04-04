/**
 * 러닝메이트 회원가입 도시선택에 따라 동적으로 변하는 지역 설정 스크립트
 */

document.querySelector("#email").addEventListener("keyup", (event) => {
	
	let inputEmail = event.target.value;
	
	// 이메일 정규표현식(A-Za-z0-9._%+-,알파벳대소문자,숫자가능)
	const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	
	let messageDiv = document.getElementById("emailMessage");
	
	//test는 기본내장객체,인자가 정규표현식의 부합한지 확인하고, true,false반환
	if (inputEmail === "") {
		messageDiv.innerHTML = "";
	}else if (emailRegex.test(inputEmail)){
		messageDiv.innerHTML = "";
		sendEmail(inputEmail);
		
	} else {
		messageDiv.innerHTML = "<span style='color:red'>이메일 형식이 틀렸습니다.</span>";
	}
});

function sendEmail(email) {
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${email}` //서버로 전송할 데이터
	};
	fetch("/members/email-check", option)
		.then(respose => respose.json())
		.then(result => console.dir(result))
		.catch(error => console.log(error));
}



