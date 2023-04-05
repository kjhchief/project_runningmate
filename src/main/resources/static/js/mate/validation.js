/**
 * 러닝메이트 회원가입 도시선택에 따라 동적으로 변하는 지역 설정 스크립트
 */




document.querySelector("#email").addEventListener("keyup", (event) => {
	
	let inputEmail = event.target.value;
	
	// 이메일 정규표현식(A-Za-z0-9._%+-,알파벳대소문자,숫자가능)
	const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	
	let messageDiv = document.getElementById("emailMessage");
	
	//test는 기본내장객체,인자가 정규표현식의 부합한지 확인하고, true,false반환
	if(emailRegex.test(inputEmail)){
		
		sendEmail(inputEmail);
		
	} else if(inputEmail.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 이메일 형식 입니다.</span>";
		return;
	} else if(inputEmail.length < 6){
		messageDiv.innerHTML = "";
		return;
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
	fetch("/mate/email-check", option)
		.then(respose => respose.json())
		.then(result => emailResultMessage(result))
		.catch(error => console.log(error));
}

function emailResultMessage(result){
	
	let messageDiv = document.getElementById("emailMessage");
	
	if(result === false){
		messageDiv.innerHTML = "<span style='color:red'>이미 존재하는 이메일입니다.</span>";
		return;
	}else if(result === true){
		messageDiv.innerHTML = "<span>사용 가능한 이메일입니다.</span>";
		
	}
	
}



let inputPassword;
document.querySelector("#password").addEventListener("keyup", (event) => {
	
	inputPassword = event.target.value;
	
	// 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
	
	let messageDiv = document.getElementById("password-Message");
	
	if (passwordRegex.test(inputPassword)){
		messageDiv.innerHTML = "사용 가능한 비밀번호 입니다.";	
			return true;
	} else if(inputPassword.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 비밀번호 형식 입니다.</span>";
		
	}else if(inputPassword.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

document.querySelector("#password-check").addEventListener("keyup", (event) => {
	
	let inputPasswordCheck = event.target.value;
	
	let messageDiv = document.getElementById("password-check-message");
	
	if (inputPassword === inputPasswordCheck) {
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
		
	} else if(inputPasswordCheck.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(inputPasswordCheck.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

document.querySelector("#phoneNumber-verify-button").addEventListener("click", () => { //클릭은 인자없음
	
	let inputPhoneNumber = document.querySelector("#phoneNumber").value;
	
	// 010으로 시작하고 뒤에8자리만 입력가능,(부트스트랩)input타입의 tel속성이 -(하이픈) 자동 생성해서 DB에 저장해주기때문에 숫자만입력하게 구현
	const phoneNumberRegex = /^010\d{8}$/;
	
	let messageDiv = document.getElementById("phoneNumber-check-message");
	
	if (phoneNumberRegex.test(inputPhoneNumber)){
		messageDiv.innerHTML = "인증완료.";		
		
	} else {
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
		
	}
});
document.querySelector("#registForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼 제출 기본 동작 막기
    
    document.querySelector("#registForm").submit();//폼 제출

});