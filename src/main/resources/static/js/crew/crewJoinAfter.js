//코멘트 저장
function saveComment(count) {
	let comment = {
		author: sessionStorage.getItem('mate'),
		email: document.querySelector(`#email${count}`).textContent,
		comment: document.querySelector(`#collapse${count} .commentTextarea`).value
	};

	console.log(comment);

	fetch('/comment/save', {
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
      let email = document.getElementById(emailId).textContent.trim();
      let Ev = radioGroups[i].value;
      userData.push({email:email, value:Ev});
    }
    
  fetch('/modal/save',{
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
