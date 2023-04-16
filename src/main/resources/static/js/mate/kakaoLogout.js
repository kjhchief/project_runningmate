	//카카오로그아웃  
		function kakaoLogout() {
		    if (Kakao.Auth.getAccessToken()) {
		      Kakao.API.request({
		        url: '/v1/user/unlink',
		        success: function (response) {
		        	console.log(response);
		        },
		        fail: function (error) {
		          console.log(error);
		        },
		      })
		      Kakao.Auth.setAccessToken(undefined);
		    }
		  }  
