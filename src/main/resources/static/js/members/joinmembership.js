/**
 * 러닝메이트 회원가입 도시선택에 따라 동적으로 변하는 지역 설정 스크립트
 */
 window.onload = function(){
      document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
          //카카오 지도 발생
          new daum.Postcode({
              oncomplete: function(data) { //선택시 입력값 세팅
                  document.getElementById("address_kakao").value = data.address; // 주소 넣기
                  document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
                  this.close(); // 창 닫기
              }
          }).open();
      });
  }
  