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

