
//현재 비밀번호 일치하는지 여부
presentPassword.addEventListener("keyup", () => {
sendPassword(emailInput.value, presentPassword.value)

});

//비밀번호 확인
function sendPassword(email, password) {
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${email}&password=${password}` //서버로 전송할 데이터
	};
	fetch("/mate/password-check", option)
		.then(respose => respose.json())
		.then(result => presentPasswordResultMessage(result))
		.catch(error => console.log(error));
}

function presentPasswordResultMessage(result){
		let messageDiv = document.getElementById("present-password-message");
	if(result === true){
		passwordChecked1 = true;
		messageDiv.innerHTML = "비밀번호가 확인되었습니다.";
	}else if(result === false){
		passwordChecked1 = false;
		messageDiv.innerHTML = "<span style='color:red'>존재하지 않는 비밀번호 입니다.</span>";
	}
	
}



newPassword.addEventListener("keyup", () => {
	
	// 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
	
	let messageDiv = document.getElementById("new-password-message");
	
	if (passwordRegex.test(newPassword.value)){
		if(newPassword.value !== presentPassword.value){
			messageDiv.innerHTML = "사용 가능한 비밀번호 입니다.";	
			newPasswordChecked2 = true;
		}else{
			messageDiv.innerHTML = "<span style='color:blue'>현재 비밀번호랑 동일합니다.</span>";	
		}
	} else if(newPassword.value.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 비밀번호 형식 입니다.</span>";
		
	}else if(newPassword.value.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

newPasswordCheck.addEventListener("keyup", () => {
	
	let messageDiv = document.getElementById("new-password-check-message");
	
	if (newPassword.value === newPasswordCheck.value) {
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
			newPasswordChecked3 = true;
		
	} else if(newPasswordCheck.value.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(newPasswordCheck.value.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

passwordForm.addEventListener("submit", (event) =>{
	if(newPasswordChecked1&& newPasswordChecked2 && newPasswordChecked3){
		passwordForm.submit();
		
	}else{
		alert('다시 입력 해주세요.');
		event.preventDefault();
	}
});

