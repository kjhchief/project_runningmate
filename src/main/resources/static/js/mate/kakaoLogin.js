Kakao.init("aa8199074e37d9bc5cdfa4c9113b2091");
  
  function kakaoLogin(){
	   Kakao.Auth.login({
		   scope:'account_email, gender, birthday',
		      success: function () {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
					  const email = response.kakao_account.email;
					  const gender = response.kakao_account.gender;
					  const birthday = response.kakao_account.birthday;
		        	  console.log(email); // 이메일 정보 출력
          			  console.log(gender); // 성별 정보 출력
          			  console.log(birthday); // 생일 정보 출력
          			sendkakaoAccount(email, gender, birthday);
		          },
		          fail: function (error) {
		            console.log(error);
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error);
		      },
		    })
		  }
	
function sendkakaoAccount(email, gender, birthday){
	let option = {
		method: "post",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: `email=${email}&gender=${gender}&birthdate=${birthday}`
	};
	
	fetch("/mate/kakaoLogin", option)
		.then(respose => respose.json())
		.then(result => kakaoLoginMessage(result))
		.catch(error => console.log(error));
}

function kakaoLoginMessage(result){
		if(result){
		
		location.href='/';
		
	}else{
		alert("카카오로그인아님.");
	}
	
}