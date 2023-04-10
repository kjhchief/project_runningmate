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

genderElement.addEventListener("keyup", () => { 
	
	let messageDiv = document.getElementById("gender-check-message");
	
	//남, 여만 입력 할 수 있는 정규표현식
	const genderRegex = /^[남여]$/;
	if(genderRegex(genderElement.value)){
		if(genderElement.value === '남'){
			genderElement.value ==='M';
		}else{
			genderElement.value ==='F';
			
		}
	}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}
});


//휴대폰번호형식 일치하는지
phoneNumberElement.addEventListener("keyup", () => { 
	
	
	// 010으로 시작하고 뒤에8자리만 입력가능
	const phoneNumberRegex = /^010\d{8}$/;
	
	let messageDiv = document.getElementById("phoneNumber-check-message");
		
		
	 if(phoneNumberRegex.test(inputPhoneNumber)){
		 const formattedPhoneNumber = `${phoneNumberElement.value.slice(0, 3)}-${phoneNumberElement.value.slice(3, 7)}-${phoneNumberElement.value.slice(7)}`;
  		 phoneNumberElement.value = formattedPhoneNumber;
	}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}
});


//비밀번호 일치하는지 여부
passwordCkInput.addEventListener("keyup", () => {
	
	if (passwordInput.value === passwordCkInput.value) {
		messageDiv.innerHTML = "비밀번호가 일치합니다.";
		sendPassword(emailInput.value, passwordInput.value);
		
		
	} else if(passwordCkInput.value.length >= 6){
		messageDiv.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
		
	}else if(passwordCkInput.value.length < 6){
		messageDiv.innerHTML = "";
		
	}
});


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

  
form.addEventListener("submit", (event) => {
       event.preventDefault();
       const isValid = form.checkValidity();

    if (!isValid) {
         form.classList.add('was-validated');
    } else if(isValid && passwordChecked){
		updateButtonForm.style.display = 'none';
		saveButtonForm.style.display = 'block';
		
		nameInput.disabled=false;
      	birthdateElement.disabled=false; 
      	genderElement.disabled=false; 
      	phoneNumberElement.disabled=false;
      	locationElement.disabled=false;
      	
      	passwordInput.value='';
      	passwordCkInput.value='';
		messageDiv.innerHTML = "";
    }else{
		alert('비밀번호를 다시 입력해주세요.');
	}
	
});



