//스페이스바 입력못하게 하는 코드
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 32) {
    event.preventDefault();
  }
});

const updateButtonForm = document.querySelector('#detail-update-list');
const saveButtonForm = document.querySelector('#detail-save-cancel-delete');

saveButtonForm.style.display = 'none';

const form = document.querySelector('#detail-form');
const passwordForm = document.querySelector('#new-password-form');
const emailInput = document.querySelector('#email'); 
const nameInput = document.querySelector('#name'); 
const passwordInput = document.querySelector('#password'); 
const passwordCkInput = document.querySelector('#password-check'); 
const genderElement = document.querySelector('#gender'); 
const birthdateElement = document.querySelector('#birthdate');
const phoneNumberElement = document.querySelector('#phoneNumber');
const addressElement = document.querySelector('#address');
const addressDetailElement = document.querySelector('#address-detail');
const updateButton = document.querySelector('#update-button');
const updateFinshButton = document.querySelector('#update-finsh-button');


const newPasswordInput = document.querySelector('#new-password'); 
const newPasswordCKInput = document.querySelector('#new-password-check'); 


let passwordChecked = false;
let newPasswordChecked1 = false;
let newPasswordChecked2 = false;
let genderChecked = '';


passwordInput.disabled=false;
passwordCkInput.disabled=false;



//비밀번호 일치하는지 여부
 newPasswordInput.addEventListener("keyup", () => {
	let messageDiv = document.getElementById("new-password-message");
	
	// 최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자 
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!%*#?&])[A-Za-z\d@!%*#?&]{8,}$/;
	
	if (passwordRegex.test(newPasswordInput.value)) {
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
				
	} else if(newPasswordInput.length >= 6){
		// newPasswordChecked1 = false;
		messageDiv.innerHTML = "<span style='color:red'>유효하지 않은 비밀번호 형식 입니다.</span>";
		
	}else if(newPasswordInput.length < 6){
		messageDiv.innerHTML = "";
		}
});

//비밀번호 일치하는지 여부
newPasswordCKInput.addEventListener("keyup", () => {
	
	let messageDiv = document.getElementById("new-password-check-message");
	
	if (newPasswordInput.value === newPasswordCKInput.value) {
		// newPassswordChecked2 = true;
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
		
	} else if(newPasswordCKInput.length >= 6){
		// newPassswordChecked2 = false;
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(newPasswordCKInput.length < 6){
		messageDiv.innerHTML = "";
		
	}
});

passwordForm.addEventListener("submit", () =>{
	
	if(newPasswordChecked1 && newPasswordChecked2){
		passwordForm.submit();
	}
	
});



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
		.then(result => passwordResultMessage(result))
		.catch(error => console.log(error));
}

function passwordResultMessage(result){
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
      	
      
      	passwordCkInput.value='';
      	let messageDiv = document.getElementById("password-check-message");
		messageDiv.innerHTML = "";
    }else{
		alert('비밀번호를 다시 입력해주세요.');
	}
	
});

//수정사항 서버로 제출
updateFinshButton.addEventListener("click", () => {
	genderElement.value = genderChecked;
     form.submit();
     
     updateButtonForm.style.display = 'flex';
	 saveButtonForm.style.display = 'none';
	
});

//성별 바꾸기
genderElement.addEventListener("keyup", () => { 
	let messageDiv = document.getElementById("gender-check-message");
	
	//남, 여만 입력 할 수 있는 정규표현식
	const genderRegex = /^[남여]$/;
	if(genderRegex.test(genderElement.value)){
		if(genderElement.value === '남'){
			genderChecked ='M';
		}else if(genderElement.value === '여'){
			genderChecked ='F';
		}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요1.</span>";
			
		}
	}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요2.</span>";
	}
});

//생년월일 수정
birthdateElement.addEventListener("keyup", () => { 
	//생년월일 유효성 검사하는 정규표현석
	const birthRegex = /^(19\d{2}|20\d{2})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
	
	
	let messageDiv = document.getElementById("birthdate-check-message");
		
		
	 if(birthRegex.test(birthdateElement.value)){
		 const formattedBirthdate = `${birthdateElement.value.slice(0, 4)}-${birthdateElement.value.slice(4, 6)}-${birthdateElement.value.slice(6)}`;
  		 birthdateElement.value = formattedBirthdate;
	}else if(birthdateElement.value.length >= 10){
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
  		 phoneNumberElement.value = formattedPhoneNumber;
	}else if(phoneNumberElement.value.length >= 13){
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}else if(phoneNumberElement.value.length < 13){
		messageDiv.innerHTML = "";
	}
});



