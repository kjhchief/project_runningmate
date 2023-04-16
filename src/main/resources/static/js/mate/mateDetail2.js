
/*
let newPasswordChecked1 = false;
let newPasswordChecked2 = false;
*/
let genderChecked = '';
let birthdateChecked = true;
let phonenumberChecked = true;

const emailBeforeUpdate = emailInput.value;

let nameAfterUpdate ='';
let genderAfterUpdate ='';
let phonenumberAfterUpdate ='';
let birthdateAfterUpdate='';
let addressAfterUpdate='';
let addressDetailAfterUpdate='';

nameInput.addEventListener("change", () =>{
	nameAfterUpdate= nameInput.value;
});

addressElement.addEventListener("change", () =>{
	addressAfterUpdate= addressElement.value;
});

addressDetailElement.addEventListener("change", () =>{
	addressDetailAfterUpdate= addressDetailElement.value;
});


//성별 바꾸기
genderVisibility.addEventListener("change", () => { 
	let messageDiv = document.getElementById("gender-check-message");
	genderAfterUpdate = genderVisibility.value;
	//남, 여만 입력 할 수 있는 정규표현식
	const genderRegex = /^[남여]$/;
	if(genderRegex.test(genderAfterUpdate)){
		if(genderAfterUpdate === '남'){
			genderHidden.value ='M';
		messageDiv.innerHTML = "";
		}else if(genderAfterUpdate === '여'){
			genderHidden.value ='F';
			messageDiv.innerHTML = "";
		}
		messageDiv.innerHTML = "";
	}else{
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
			genderHidden.value ='N';
	}
});

//생년월일 수정
birthdateElement.addEventListener("change", () => { 
	//생년월일 유효성 검사하는 정규표현석
	const birthRegex = /^(19\d{2}|20\d{2})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
	
	birthdateAfterUpdate = birthdateElement.value;
	let messageDiv = document.getElementById("birthdate-check-message");
		
	 if(birthRegex.test(birthdateAfterUpdate)){
		 birthdateChecked = true;
		messageDiv.innerHTML = "";
	}else if(birthdateAfterUpdate.length >= 8){
		birthdateChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}else if(birthdateAfterUpdate.length < 8){
		messageDiv.innerHTML = "";
	}
});



//휴대폰번호 수정
phoneNumberElement.addEventListener("change", () => { 
	
	// 010으로 시작하고 뒤에8자리만 입력가능
	const phoneNumberRegex = /^010\d{8}$/;
	
	phonenumberAfterUpdate = phoneNumberElement.value;
	let messageDiv = document.getElementById("phoneNumber-check-message");
	
	 if(phoneNumberRegex.test(phonenumberAfterUpdate)){
		 phonenumberChecked = true;
		messageDiv.innerHTML = "";
	}else if(phonenumberAfterUpdate.length >= 13){
		phonenumberChecked = false;
		messageDiv.innerHTML = "<span style='color:red'>형식에 맞게 입력해주세요.</span>";
	}else if(phonenumberAfterUpdate.length < 13){
		messageDiv.innerHTML = "";
	}
});

//수정사항 서버로 제출
updateFinshButton.addEventListener("click", () => {
   
   if(!(genderHidden.value ==='M' || genderHidden.value ==='F') || !birthdateChecked || !phonenumberChecked){		
		 alert('입력 형식을 확인해주세요.');
     }else{
		 
	  		// 변경된 값만 서버로 전송
			if (nameAfterUpdate !== nameBeforeUpdate) {
			   form.append('name', nameAfterUpdate);
			 }   
			if (genderAfterUpdate !== genderBeforeUpdate) {
			   form.append('gender', genderHidden.value);
			 }
			if (birthdateAfterUpdate !== birthdateBeforeUpdate) {
			  form.append('birthdate', birthdateAfterUpdate);
			}
			if (phonenumberAfterUpdate !== phoneNumberBeforeUpdate) {
			  form.append('phonenumber', phonenumberAfterUpdate);
			}
			if (addressAfterUpdate !== addressBeforeUpdate) {
			  form.append('address', addressAfterUpdate);
			}
			if (addressDetailAfterUpdate !== addressDetailBeforeUpdate) {
			  form.append('addressDetail', addressDetailAfterUpdate);
			}
			//이메일값 무조건 전송
			form.append('email', emailBeforeUpdate);
			// 서버로 전송
			form.submit();
  
   }  
     
});

deleteButton.addEventListener("click", () =>{
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${emailInput.value}` //서버로 전송할 데이터
	};
	fetch("/mate/delete", option)
		.then(respose => respose.json())
		.then(result => deleteMessage(result))
		.catch(error => console.log(error));

	function deleteMessage(result){
	if(result === true){
		alert('탈퇴가 정상적으로 이루어졌습니다.');
		location.href='/mate/newLogin';
	}else if(result === false){
		alert('탈퇴 실패.');
		
	}
	
}	
	
});



