//스페이스바 입력못하게 하는 코드
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 32) {
    event.preventDefault();
  }
});

//let emailChecked1 = false;
let emailChecked = false;
let passswordChecked1 = false;
let passswordChecked2 = false;
let phonenumberChecked = false;

//이메일 유효성검사(이메일형식+중복체크)
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
	} else if(inputEmail.length < 6){
		messageDiv.innerHTML = "";
	}
});


//이메일 형식 일치하면 서버로 보냄
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
		emailChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>이미 존재하는 이메일입니다.</span>";
	}else if(result === true){
		emailChecked = true;
		messageDiv.innerHTML = "<span>사용 가능한 이메일입니다.</span>";
		
	}
	
}

//비밀번호 유효성검사(비밀번호 형식 일치 여부)
let inputPassword; //비밀번호 확인하는 이벤트에 한 번 더 쓰여서 로컬영역에 선언
document.querySelector("#password").addEventListener("keyup", (event) => {
	
	inputPassword = event.target.value;
	
	// 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
	
	let messageDiv = document.getElementById("password-message");
	
	if (passwordRegex.test(inputPassword)){
		 passswordChecked1 = true;
		messageDiv.innerHTML = "사용 가능한 비밀번호 입니다.";	
	} else if(inputPassword.length >= 6){
		 passswordChecked1 = false;
		messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 비밀번호 형식 입니다.</span>";
		
	}else if(inputPassword.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

//비밀번호 일치하는지 여부
document.querySelector("#password-check").addEventListener("keyup", (event) => {
	
	let inputPasswordCheck = event.target.value;
	
	let messageDiv = document.getElementById("password-check-message");
	
	if (inputPassword === inputPasswordCheck) {
		 passswordChecked2 = true;
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
		
	} else if(inputPasswordCheck.length >= 6){
		 passswordChecked2 = false;
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(inputPasswordCheck.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

//휴대폰번호형식 일치하는지
document.querySelector("#phoneNumber-verify-button").addEventListener("click", () => { //클릭은 인자없음
	
	let inputPhoneNumber = document.querySelector("#phoneNumber").value;
	
	// 010으로 시작하고 뒤에8자리만 입력가능
	const phoneNumberRegex = /^010\d{8}$/;
	
	let messageDiv = document.getElementById("phoneNumber-check-message");
	
	if (phoneNumberRegex.test(inputPhoneNumber)){
		 phonenumberChecked = true;
		messageDiv.innerHTML = "인증완료.";		
		
	} else {
		 phonenumberChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
		
	}
});

//모든 유효성 통과할때만 form으로 서버에 전송
document.querySelector("#registForm").addEventListener("submit", (event) => {
 
    if(emailChecked && passswordChecked1 && passswordChecked2 && phonenumberChecked){
		alert("회원 가입이 정상적으로 처리 되었습니다.");
		document.querySelector("#registForm").submit();
	}else if((!emailChecked) || (!passswordChecked1) || (!passswordChecked2) || (!phonenumberChecked)){
		alert("가입 정보를 올바르게 입력해주세요");
		event.preventDefault();
	}
	
});