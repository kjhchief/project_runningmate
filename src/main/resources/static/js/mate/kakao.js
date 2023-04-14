Kakao.init("aa8199074e37d9bc5cdfa4c9113b2091");
  
  function kakaoLogin(){
	   Kakao.Auth.login({
		   scope:'account_email, gender, birthday',
		      success: function (response) {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
		        	  console.log(response)
		        	   console.log(response.kakao_account.email); // 이메일 정보 출력
          console.log(response.kakao_account.gender); // 성별 정보 출력
          console.log(response.kakao_account.birthday); // 생일 정보 출력
		          },
		          fail: function (error) {
		            console.log(error)
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error)
		      },
		    })
		  }
		//카카오로그아웃  
		function kakaoLogout() {
		    if (Kakao.Auth.getAccessToken()) {
		      Kakao.API.request({
		        url: '/v1/user/unlink',
		        success: function (response) {
		        	console.log(response)
		        },
		        fail: function (error) {
		          console.log(error)
		        },
		      })
		      Kakao.Auth.setAccessToken(undefined)
		    }
		  }  
