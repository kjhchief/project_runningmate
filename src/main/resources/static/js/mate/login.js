const form = document.getElementById('loginForm');
form.addEventListener('submit', (event) =>{
	 // 폼 유효성 검사
    if (!form.checkValidity()) {
		
      // 검증 실패시 폼 제출 안되게 비활성화
      event.preventDefault();
      // 클릭 이벤트가 상위 요소로 전파되지 않도록 중단시킴
      event.stopPropagation();
    }

    form.classList.add('was-validated');
});

const email = document.querySelector('#email');
const password = document.querySelector('#password');

function sendEmailPassword(email, password){
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${email}&password=${password}` //서버로 전송할 데이터
	};
	fetch("/mate/login-check", option)
		.then(respose => respose.json())
		.then(result => loginResultMessage(result))
		.catch(error => console.log(error));
}
