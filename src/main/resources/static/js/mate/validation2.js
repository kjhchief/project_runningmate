/**
 * 러닝메이트 회원가입 도시선택에 따라 동적으로 변하는 지역 설정 스크립트
 */



document.querySelector("#registForm").addEventListener("submit", function(event) {
  event.preventDefault(); // 폼 제출 기본 동작 막기
  const emailValid = checkEmail();
  const passwordValid = checkPassword();
  const phoneNumberValid = checkPhoneNumber();

  if ((emailValid ===true) && (passwordValid ===true) && (phoneNumberValid ===true)){
    document.querySelector("#registForm").submit(); // 폼 제출
  }else if((emailValid ===false) && (passwordValid ===false) && (phoneNumberValid ===false)){
	  event.preventDefault();
  }
});

function checkEmail() {
  let inputEmail = document.querySelector("#email").value;
  let messageDiv = document.getElementById("emailMessage");

  // 이메일 정규표현식(A-Za-z0-9._%+-,알파벳대소문자,숫자가능)
  const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

  if(emailRegex.test(inputEmail)){

    let option = {
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      body: `email=${inputEmail}` //서버로 전송할 데이터
    };

    fetch("/mate/email-check", option)
      .then(respose => respose.json())
      .then(result => {
        if(result === false){
          messageDiv.innerHTML = "<span style='color:red'>이미 존재하는 이메일입니다.</span>";
          return false;
        }else if(result === true){
		  messageDiv.innerHTML = "<span>사용 가능한 이메일입니다.</span>";
           return true;
        }
      })
      .catch(error => console.log(error));

  } else if(inputEmail.length >= 6){
    messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 이메일 형식 입니다.</span>";
          return false;
  } else if(inputEmail.length < 6){
    messageDiv.innerHTML = "";
  }
}

function checkPassword() {
  let inputPassword = document.querySelector("#password").value;
  let inputPasswordCheck = document.querySelector("#password-check").value;

  // 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;

  let messageDiv = document.getElementById("password-Message");

  if (passwordRegex.test(inputPassword)){
    messageDiv.innerHTML = "사용 가능한 비밀번호 입니다.";  
    if (inputPassword === inputPasswordCheck) {
      document.getElementById("password-check-message").innerHTML = "비밀번호가 일치합니다.";
      	return true;
    } else if(inputPasswordCheck.length >= 6){
      document.getElementById("password-check-message").innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
      	return false;
    }else if(inputPasswordCheck.length < 6){
      document.getElementById("password-check-message").innerHTML = "";
    }  
  } else if(inputPassword.length >= 6){
    messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 비밀번호 형식 입니다.</span>";
      	return false;
  }else if(inputPassword.length < 6){
    messageDiv.innerHTML = "";
  }
}

function checkPhoneNumber() { 
	
	let inputPhoneNumber = document.querySelector("#phoneNumber").value;
	// 010으로 시작하고 뒤에8자리만 입력가능,(부트스트랩)input타입의 tel속성이 -(하이픈) 자동 생성해서 DB에 저장해주기때문에 숫자만입력하게 구현
	const phoneNumberRegex = /^010\d{8}$/;
	
	let messageDiv = document.getElementById("phoneNumber-check-message");
	
	if (phoneNumberRegex.test(inputPhoneNumber)){
		messageDiv.innerHTML = "인증완료.";		
		return true;
	} else {
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
		return false;
	}
}


