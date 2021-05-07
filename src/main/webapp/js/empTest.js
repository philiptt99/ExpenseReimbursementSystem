/**
 * 
 */

window.onload = function () {
	getSessUser();
}

let pastreimb = document.getElementById("pastReimb1");

function getSessUser() {
	
	pastreimb.style.display = 'none';
	addReimb.style.display = 'none';

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let vill = JSON.parse(xhttp.responseText);
			//console.log(vill);

			document.getElementById("empID").innerText = `${vill.userID}`;
			document.getElementById("empFirst").innerText = `${vill.firstName}`;
			document.getElementById("empLast").innerText = `${vill.lastName}`;
			document.getElementById("empEmail").innerText = `${vill.email}`;
			document.getElementById("empUserName").innerText = `${vill.userName}`;

		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1_ERS/employee.json");
	xhttp.send();
}

//click event for id=profile
let profile1 = document.getElementById("profile");
profile1.addEventListener('click', getSessUser);


//click event for id=pass
var past = document.getElementById("past");

function getPastReimb() {
	addReimb.style.display = 'none';
	pastreimb.style.display = 'block';
	let pTable = document.getElementById("pastTable");
	pTable.innerHTML="";

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let reimb = JSON.parse(xhttp.responseText);
			//console.log(reimb);
			//console.log(reimb.length);

			if (Object.keys(reimb).length == 0) { //No reimbursement
				console.log("empty JSON");
			}

			else { //reimbursement

				let th = document.createElement('thead');
				th.innerHTML = '<th scope="col">Reimbursment ID</th>'+
					'<th scope="col">Amount($)</th>'+
					'<th scope="col">Summission Date</th>'+
					'<th scope="col">Resolved Date</th>' +
					'<th scope="col">Description</th>'+
					'<th scope="col">Manager ID</th>' +
					'<th scope="col">Status</th>'+
					'<th scope="col">Type</th>';
				pTable.appendChild(th);
				
				reimb.forEach(object => {
					var tr = document.createElement('tr');
					let stat = object.statusID;
					let type = object.typeID;

					if (stat == 1 ){
						stat = 'Pending';
					}
					else if (stat == 2){
						stat = 'Approved';
					}
					else{
						stat = 'Denied';
					}

					if (type == 10 ){
						type = 'Lodging';
					}
					else if (stat == 20){
						type = 'Travel';
					}
					else if (type == 30){
						type = 'Food';
					}
					else{
						type = 'Other';
					}

					tr.innerHTML = '<td>' + object.reimbID + '</td>' +
						'<td>' + object.reimAmount + '</td>' +
						'<td>' + object.reimSubmit + '</td>' +
						'<td>' + object.reimResolve + '</td>'+
						'<td>' + object.description + '</td>'+
						'<td>' + object.resolverID + '</td>'+
						'<td>' + stat + '</td>'+
						'<td>' + type + '</td>';

					pTable.appendChild(tr);
				});

				


			}
			
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1_ERS/pastreimb.json");
	xhttp.send();
}

past.addEventListener('click', getPastReimb);


//Add a new Reimbursement 
let addReimb = document.getElementById('addReimb');
let add = document.getElementById('add');
add.addEventListener('click',insertReimb);

function insertReimb(){
	addReimb.style.display = 'block';
	pastreimb.style.display = 'none';


}
