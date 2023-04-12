let today = new Date();
let date = today.getDate();

for(let i=0 ; i < 7; i++){
	if(i === 0){
		fetchRequest();
	}else{
		document.querySelector(`#but${i}`).addEventListener("click", () => {
		fetchRequest();
		});
	}
}


function fetchRequest(){
  fetch("http://localhost/runlist")
  .then(response => response.text())
  .then(message =>  showMessage(message)) 
  .catch(error => {
    showMessage(error)
  })
}

function showMessage(message){
  document.querySelector("#message").innerHTML = message;
}

