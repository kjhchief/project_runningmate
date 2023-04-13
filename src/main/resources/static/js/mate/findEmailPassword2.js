
let nameInput1 = document.querySelector('#find-email-name'); 
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
async function sendEmailPassword(name, email=null, password=null) {
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
	
	if(result === true){
			alert("true받음");
		emailPasswordChecked = true;
		location.href='/mate/findEmailPassword';
		return true;
	}else if(result === false){
			alert("false받음");
		location.href='/mate/findEmailPassword';
		emailPasswordChecked = false;
		return false;
	}
}


	//let name2 = nameInput1.value
findButton1.addEventListener("click", (event) =>{
		event.preventDefault();
	
	findEmailForm.append('name', nameInput1.value);
	findEmailForm.append('password', passwordInput.value);
	findEmailForm.submit();
	alert(nameInput1.value + " : " + passwordInput.value);
		
});
/*
findButton2.addEventListener("click", () =>{
	
	findPasswordForm.append('name', nameInput2.value);
	findPasswordForm.append('email',  emailInput.value);
	alert(nameInput2.value + " : " + emailInput.value);
	findPasswordForm.submit();
	
		//event.preventDefault();	
	
});
*/