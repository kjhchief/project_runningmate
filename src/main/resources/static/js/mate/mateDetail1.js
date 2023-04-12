/*
//스페이스바 입력못하게 하는 코드
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 32) {
    event.preventDefault();
  }
});
*/
let updateButtonForm = document.querySelector('#detail-update-list');
let saveButtonForm = document.querySelector('#detail-save-cancel-delete');

saveButtonForm.style.display = 'none';

let form = document.querySelector('#detail-form');
let passwordForm = document.querySelector('#new-password-form');

let emailInput = document.querySelector('#email'); 
let nameInput = document.querySelector('#name'); 
let passwordInput = document.querySelector('#password'); 
let passwordCkInput = document.querySelector('#password-check'); 
let genderVisibility = document.querySelector('#visibility-gender');
let genderHidden = document.querySelector('#hidden-gender');
let birthdateElement = document.querySelector('#birthdate');
let phoneNumberElement = document.querySelector('#phoneNumber');
let addressElement = document.querySelector('#address');
let addressDetailElement = document.querySelector('#addressDetail');

let updateButton = document.querySelector('#update-button');
let updateFinshButton = document.querySelector('#update-finsh-button');
let deleteButton = document.querySelector('#leave-button');


let presentPassword = document.querySelector('#present-password'); 
let newPassword = document.querySelector('#new-password'); 
let newPasswordCheck = document.querySelector('#new-password-check'); 


const nameBeforeUpdate = nameInput.value;
const genderBeforeUpdate = genderVisibility.value;
const birthdateBeforeUpdate = birthdateElement.value;
const phoneNumberBeforeUpdate = phoneNumberElement.value;
const addressBeforeUpdate = addressElement.value;
const addressDetailBeforeUpdate = addressDetailElement.value;

let passwordChecked = false;

passwordInput.disabled=false;
passwordCkInput.disabled=false;


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
      	genderVisibility.disabled=false; 
      	phoneNumberElement.disabled=false;
      	addressElement.disabled=false;
      	addressDetailElement.disabled=false;
      	passwordInput.disabled=true;
      	passwordCkInput.disabled=true;
      	
      	presentPassword.disabled=false; 
      	newPassword.disabled=false; 
      	newPasswordCheck.disabled=false; 
      	
      
      	passwordInput.value='';
      	passwordCkInput.value='';
      	let messageDiv = document.getElementById("password-check-message");
		messageDiv.innerHTML = "";
    }else{
		alert('비밀번호를 다시 입력해주세요.');
	}
	
});






