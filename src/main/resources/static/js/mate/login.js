const form = document.getElementById('loginForm');
const email = document.querySelector('#email');
const password = document.querySelector('#password');

form.addEventListener('submit', (event) =>{
    //const checkedSave = document.querySelector('#saveEmail');

	 // 폼 유효성 검사
    if ((!form.checkValidity())){
      // 검증 실패시 폼 제출 안되게 비활성화
      event.preventDefault();
      // 클릭 이벤트가 상위 요소로 전파되지 않도록 중단시킴
      event.stopPropagation();
    form.classList.add('was-validated');
    }else{
		sendEmailPassword(email.value, password.value);
	}
    
    //if(checkedSave.checked){}	
});


function sendEmailPassword(email, password){
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
			
		},
		body: `email=${email}&password=${password}` //서버로 전송할 데이터
	};
	fetch("/mate/login-check", option)
		.then(respose => respose.text())
		.then(result => loginResultMessage(result))
		.catch(error => console.log(error));
}

function loginResultMessage(result){
	if(result === 'success'){
		location.href='/mate/main';
	}else if(result === 'failure'){
		alert("이메일, 비밀번호를 다시 입력 해주세요.");
	}
	
}

/*
// "saveEmail" 쿠키 값 읽어오기
var savedEmail = getCookie("saveEmail");

// "이메일" 입력란에 쿠키 값 채우기
if (savedEmail) {
  document.getElementById("email").value = savedEmail;
}

// 쿠키 값을 읽어오는 함수 구현
function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length === 2) {
    return parts.pop().split(";").shift();
  }
}
*/
