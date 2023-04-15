let form = document.querySelector("#submit_form");

 form.addEventListener("submit", (event)=>{
    if(event.submitter.value ==='y'){
        alert(`신청 완료`);
            
    }else{
        alert(`신청 취소 완료`);
    }
 });


// document.querySelector("#join_button").addEventListener("click", () => {
//     alert(`신청 완료`);
// });

// document.querySelector("#cancel_button").addEventListener("click", () => {
// 	alert(`취소 완료`);
// });


//민영 

//코멘트 저장
function saveComment(count) {
  let author = sessionStorage.getItem('mate');
  let emailD = document.querySelector(`#email${count}`);
  let emailTN = Array.from(emailD.childNodes).filter(node => node.nodeType === Node.TEXT_NODE);
  let email = emailTN[0].textContent.trim();
  let comment = {
    author : sessionStorage.getItem('mate'),
    email : email,
    comment : document.querySelector(`#collapse${count} .commentTextarea`).value
  };

	console.log(comment);

	fetch(`/${crewId}/modal/comment/save`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(comment)
	}).then(response => response.json())
		.then(response => console.log(response))
		.catch(error => console.log(error));
}

//평가 json 저장
  function getMemberEv() {
    let radioGroups = document.querySelectorAll('input[type="radio"]:checked');
    let userData = [];
    let prefix = "email";
    for (var i = 0; i < radioGroups.length; i++) {
      let emailId = prefix + (i+1);
      let emailDiv = document.getElementById(emailId);
      let emailTextNode = Array.from(emailDiv.childNodes).filter(node => node.nodeType === Node.TEXT_NODE);
      let email = emailTextNode[0].textContent.trim();
      let Ev = radioGroups[i].value;
      userData.push({email:email, value:Ev});
    }
    
  fetch(`/${crewId}/modal/save`,{
    method:'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body:JSON.stringify(userData)
  })
  .then(response => response.json())
    .then(response => console.log(response))
    .catch(error => console.log(error));
}
