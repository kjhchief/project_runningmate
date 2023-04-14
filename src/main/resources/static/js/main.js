let today = new Date();
let month = String(today.getMonth() + 1).padStart(2, '0');
let date = String(today.getDate()).padStart(2, '0');
// 추출한 월과 일을 '_'로 연결하여 문자열을 생성
let formattedDate = `${month}_${date}`;

fetchRequest1(formattedDate);

function handleSearch(event) {
  event.preventDefault(); // 폼의 기본 동작 중단
  const formData = new FormData(event.target); // 폼 데이터 가져오기
  const searchWord = formData.get("searchWord"); // 입력된 검색어 가져오기
  fetchRequest2(searchWord);
  
}	
// 버튼 클릭하면 날짜 받는 ajax
function fetchRequest1(buttonId){
  
	fetch(`http://localhost/crew/runlist/${buttonId}`)
	.then(response => response.text())
	.then(message =>  showMessage(message)) 
	.catch(error => {
	  showMessage(error)
	  
	});  
  }

// 지역 검색하면 지역명 받는 ajax
function fetchRequest2(para){

	fetch(`http://localhost/crew/runlist2/${para}`)
	.then(response => response.text())
	.then(message =>  showMessage(message)) 
	.catch(error => {
	  showMessage(error)
	});  
}

function showMessage(message){
  document.querySelector("#message").innerHTML = message;
}

