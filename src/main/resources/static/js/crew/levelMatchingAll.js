console.log("자바스크립트 잘 읽어오나");

fetchRequest3();

// 레벨별 매칭 페이지용...
function fetchRequest3(){

	fetch(`http://localhost/crew/runlist3`)
	.then(response => response.text())
	.then(message =>  showMessage(message)) 
	.catch(error => {
	  showMessage(error)
	});  
}

function showMessage(message){
  document.querySelector("#runlist3_message").innerHTML = message;
}