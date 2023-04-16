//스페이스바 입력못하게 하는 코드
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 32) {
    event.preventDefault();
  }
});

const form = document.querySelector('#loginForm');
const email = document.querySelector('#email');
const password = document.querySelector('#password');
const saveEmail = document.getElementById('saveEmail'); 

//checkbox가 체크되었을 때 value 값 또는 "on" 문자열이 반환, 체크되지 않았을 때는 빈 문자열("")이 반환
//만약 checkbox의 value 속성이 명시되지 않았다면, 체크박스가 체크되었을 때와 체크되지 않았을 때 모두 "on" 문자열이 반환

form.addEventListener('submit', (event) =>{
    //const checkedSave = document.querySelector('#saveEmail');
	event.preventDefault();
    // 클릭 이벤트가 상위 요소로 전파되지 않도록 중단시킴 
      
	 // 폼 유효성 검사 
    if ((!form.checkValidity())){
      form.classList.add('was-validated');
    }else{
		  saveEmail.value = saveEmail.checked ? 'on':'';
		  sendEmailPassword(email.value, password.value, saveEmail.value);
		}
});


function sendEmailPassword(email, password, saveEmail){
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${email}&password=${password}&saveEmail=${saveEmail}` //서버로 전송할 데이터
	};
	
	fetch("/mate/login", option)
		.then(respose => respose.json())
		.then(result => loginResultMessage(result))
		.catch(error => console.log(error));
}

function loginResultMessage(result){
	if(result){
		location.href='/';
	}else{
		alert("이메일, 비밀번호를 다시 입력 해주세요.");
	}
	
}
