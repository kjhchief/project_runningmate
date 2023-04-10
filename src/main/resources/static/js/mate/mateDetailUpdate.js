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
const emailInput = document.querySelector('#email'); 
const nameInput = document.querySelector('#name'); 
const passwordInput = document.querySelector('#password'); 
const passwordCkInput = document.querySelector('#password-check'); 
const genderElement = document.querySelector('#gender'); 
const birthdateElement = document.querySelector('#birthdate');
const phoneNumberElement = document.querySelector('#phoneNumber');
const locationElement = document.querySelector('#location');
const updateButton = document.querySelector('#update-button');
	let messageDiv = document.getElementById("password-check-message");

let passwordChecked = false;

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




