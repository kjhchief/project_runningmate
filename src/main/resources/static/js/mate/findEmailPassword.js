
const nameInput1 = document.querySelector('#find-email-name'); 
const nameInput2 = document.querySelector('#find-password-name'); 
const emailInput = document.querySelector('#email'); 
const passwordInput = document.querySelector('#password'); 
const findButton1 = document.querySelector('#find-email-button');
const findButton2 = document.querySelector('#find-password-button');

let emailValueDiv = document.querySelector('#email-value');
let passwordValueDiv = document.querySelector('#password-value');

//아이디 비밀번호 찾기
function findEmailOrPassword(searchType, ...values) {
	let body = `name=${values[0]}`;
   
    // 이메일 찾기
    if(searchType === 'email'){
		body += `&password=${values[1]}`;
	}else{ // 비번찾기 경우
		 body += `&email=${values[1]}`;
	}
		
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: body
	};
	
	fetch("/mate/findEmailPassword", option)
		.then(respose => respose.json())
		.then(result => resultMessage(result) )
		.catch(error => console.log(error));
		
}

function resultMessage(result){
	console.log(result);
	
	if(result.type === 'email'){
		
		if(result.result){
			emailValueDiv.innerHTML = result.email;
		}else{
			emailValueDiv.innerHTML="일치하는 이름과, 비밀번호가 없습니다.";	
		}		
		
	}else if(result.type === 'password'){
		
		if(result.result){
			passwordValueDiv.innerHTML=result.password;
			}else{	
			passwordValueDiv.innerHTML="일치하는 이메일과, 이름이 없습니다.";
			}
	}

}


findButton1.addEventListener("click", () =>{
	findEmailOrPassword("email", nameInput1.value, passwordInput.value);
});

findButton2.addEventListener("click", () =>{
	findEmailOrPassword("password", nameInput2.value, emailInput.value);
		
});



