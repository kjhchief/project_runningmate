let today = new Date();
let month = String(today.getMonth() + 1).padStart(2, '0');
let date = String(today.getDate()).padStart(2, '0');
// 추출한 월과 일을 '_'로 연결하여 문자열을 생성
let formattedDate = `${month}_${date}`;

fetchRequest(`b${formattedDate}`);

function fetchRequest(buttonId){

	
  fetch(`http://localhost/runlist/${buttonId}`)
  .then(response => response.text())
  .then(message =>  showMessage(message)) 
  .catch(error => {
    showMessage(error)
  })
}

function showMessage(message){
  document.querySelector("#message").innerHTML = message;
}

