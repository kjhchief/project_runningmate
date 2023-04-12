//스페이스바 입력못하게 하는 코드
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 32) {
    event.preventDefault();
  }
});



const emailInput1 = document.querySelector('#find-email-name'); 
const emailInput2 = document.querySelector('#find-password-name'); 
const nameInput = document.querySelector('#name'); 
const passwordInput = document.querySelector('#password'); 
const phoneNumberElement = document.querySelector('#phoneNumber');
const findButton1 = document.querySelector('#find-email-button');
const findButton2= document.querySelector('#find-password-button');

//비밀번호 일치하는지 여부
passwordCkInput.addEventListener("keyup", () => {
	let messageDiv = document.getElementById("password-check-message");
	
	if (passwordInput.value === passwordCkInput.value) {
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
		sendPassword(emailInput.value, passwordInput.value);
		
		
	} else if(passwordCkInput.value.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(passwordCkInput.value.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

//아이디 비밀번호 찾기
function sendPassword(email, name, password) {
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/json"
		},
		body: `email=${email}&name=${name}&password=${password}`
	};
	fetch("/mate/password-check", option)
		.then(respose => respose.json())
		.then(result => findResultMessage(result))
		.catch(error => console.log(error));
}

function findResultMessage(result){
	if(result === true){
		passwordChecked = true;
	}else if(result === false){
		passwordChecked = false;
	}
	
}

 //수정가능한 상태로 됨 
form.addEventListener("submit", (event) => {
       event.preventDefault();
       const isValid = form.checkValidity();

    if (!isValid) {
         form.classList.add('was-validated');
    } else if(isValid && passwordChecked){
		updateButtonForm.style.display = 'none';
		saveButtonForm.style.display = 'flex';
		
		nameInput.disabled=false;
      	birthdateElement.disabled=false; 
      	genderElement.disabled=false; 
      	phoneNumberElement.disabled=false;
      	addressElement.disabled=false;
      	addressDetailElement.disabled=false;
      	passwordInput.disabled=true;
      	passwordCkInput.disabled=true;
      	
      
      	passwordInput.value='';
      	passwordCkInput.value='';
      	let messageDiv = document.getElementById("password-check-message");
		messageDiv.innerHTML = "";
    }else{
		alert('비밀번호를 다시 입력해주세요.');
	}
	
});



//성별 바꾸기
genderElement.addEventListener("keyup", () => { 
	let messageDiv = document.getElementById("gender-check-message");
	
	//남, 여만 입력 할 수 있는 정규표현식
	const genderRegex = /^[남여]$/;
	if(genderRegex.test(genderElement.value)){
		if(genderElement.value === '남'){
			genderChecked ='M';
		messageDiv.innerHTML = "";
		}else if(genderElement.value === '여'){
			genderChecked ='F';
		messageDiv.innerHTML = "";
		}
	}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
			genderChecked ='N';
	}
});

//생년월일 수정
birthdateElement.addEventListener("keyup", () => { 
	//생년월일 유효성 검사하는 정규표현석
	const birthRegex = /^(19\d{2}|20\d{2})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
	
	
	let messageDiv = document.getElementById("birthdate-check-message");
		
		
	 if(birthRegex.test(birthdateElement.value)){
		 const formattedBirthdate = `${birthdateElement.value.slice(0, 4)}-${birthdateElement.value.slice(4, 6)}-${birthdateElement.value.slice(6)}`;
		 birthdateChecked = true;
  		 birthdateElement.value = formattedBirthdate;
		messageDiv.innerHTML = "";
	}else if(!(birthRegex.test(birthdateElement.value)) && birthdateElement.value.length >= 10){
		birthdateChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}else if(birthdateElement.value.length < 10){
		messageDiv.innerHTML = "";
	}
});



//휴대폰번호 수정
phoneNumberElement.addEventListener("keyup", () => { 
	
	// 010으로 시작하고 뒤에8자리만 입력가능
	const phoneNumberRegex = /^010\d{8}$/;
	
	let messageDiv = document.getElementById("phoneNumber-check-message");
	
		
	 if(phoneNumberRegex.test(phoneNumberElement.value)){
		 const formattedPhoneNumber = `${phoneNumberElement.value.slice(0, 3)}-${phoneNumberElement.value.slice(3, 7)}-${phoneNumberElement.value.slice(7)}`;
		 phonenumberChecked = true;
  		 phoneNumberElement.value = formattedPhoneNumber;
	}else if(!(phoneNumberRegex.test(phoneNumberElement.value)) && phoneNumberElement.value.length >= 13){
		phonenumberChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}else if(phoneNumberElement.value.length < 13){
		messageDiv.innerHTML = "";
	}
});

//수정사항 서버로 제출
updateFinshButton.addEventListener("click", () => {
	genderElement.value = genderChecked;
     
     
     if(genderChecked && birthdateChecked && phonenumberChecked){
		form.submit(); 
	 }else{
		 alert('입력 형식을 확인해주세요.');
		 
	 }
     
});



