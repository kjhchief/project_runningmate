
const nameInput1 = document.querySelector('#find-email-name'); 
const nameInput2 = document.querySelector('#find-password-name'); 
const emailInput = document.querySelector('#email'); 
const passwordInput = document.querySelector('#password'); 
const phoneNumberElement = document.querySelector('#phoneNumber');
const findButton1 = document.querySelector('#find-email-button');
const findButton2 = document.querySelector('#find-password-button');
const findEmailForm = document.querySelector('#find-email-form');
const findPasswordForm = document.querySelector('#find-password-form');

let emailPasswordChecked = false;

//아이디 비밀번호 찾기
function sendEmailPassword(name, email=null, password=null) {
	 let body = `name=${name}`;
    if (email !== null) {
        body += `&email=${email}`;
    }
	if (password !== null) {
        body += `&password=${password}`;
    }
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: body
	};
	
	return fetch("/mate/findEmailPassword", option)
		.then(respose => respose.json())
		.then(result => findResultMessage(result))
		.catch(error => console.log(error));
		
}

function findResultMessage(result){
	
	if(result){
			alert("true받음");
			passwordValueDiv.innerHTML="emailResult"
		emailPasswordChecked = true;
		//location.href='/mate/findEmailPassword';
		//return true;
	}else if(result === false){
			//alert("false받음");
		location.href='/mate/findEmailPassword';
		emailPasswordChecked = false;
		return false;
	}
	/*
		if(result.result){
			alert("true받음");
	}else{
			alert("false받음");
	}
		return result.result;
	*/
}


findButton1.addEventListener("click", (event) =>{
		event.preventDefault();
		
		// 서버 결과 화면
		let emailValueDiv = document.querySelector('#email-value');
		
		sendEmailPassword(nameInput1.value, passwordInput.value);
		/*
		if(result){
			alert("존재함");
			emailValueDiv.innerHTML ="존재함."
		}else{
			alert("존재안함");
			emailValueDiv.innerHTML ="<span style='color:red'>일치하는 이름과, 비밀번호가 없습니다.</span>"
		}
		*/
});


findPasswordForm.addEventListener("submit", (event) =>{
		event.preventDefault();	
		
		let passwordValueDiv = document.querySelector('#password-value');
		sendEmailPassword(nameInput2.value, emailInput.value);
			//location.href='/mate/findEmailPassword';
		if(result){
			alert("존재함");
		}else{
			alert("존재안함");
			passwordValueDiv.innerHTML="<span style='color:red'>일치하는 이름과, 이메일이 없습니다.</span>"
		}
});