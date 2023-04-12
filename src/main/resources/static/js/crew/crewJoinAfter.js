//코멘트 저장
function saveComment(count) {
	let comment = {
		author: sessionStorage.getItem('mate'),
		email: document.querySelector(`#email${count}`).textContent,
		comment: document.querySelector(`#collapse${count} #commentTextarea`).value
	};

	console.log(comment);

	fetch('/comment/save', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(comment)
	}).then(response => {

	});
}