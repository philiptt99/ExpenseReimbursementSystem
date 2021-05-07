/**
 * 
 */

window.onload = function () {
	getSessManager();
}

let allReimb = document.getElementById("allReimb1");

function getSessManager() {

	allReimb.style.display = 'none';
	processReimb1.style.display = 'none';

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function () {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let manager = JSON.parse(xhttp.responseText);
			//console.log(manager);

			document.getElementById("manID").innerText = `${manager.userID}`;
			document.getElementById("manFirst").innerText = `${manager.firstName}`;
			document.getElementById("manLast").innerText = `${manager.lastName}`;
			document.getElementById("manEmail").innerText = `${manager.email}`;
			document.getElementById("manUserName").innerText = `${manager.userName}`;

		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1_ERS/manager.json");
	xhttp.send();
}

//click event for id=profile
let profile1 = document.getElementById("profile");
profile1.addEventListener('click', getSessManager);


//click event for id=managerView
var viewAll = document.getElementById("managerview");

function getAllReimb() {
	allReimb.style.display = 'block';
	processReimb1.style.display = 'none';
	let allTable = document.getElementById("allTable");
	allTable.innerHTML = "";

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
				th.innerHTML = '<th scope="col">Reimbursment ID</th>' +
					'<th scope="col">Amount($)</th>' +
					'<th scope="col">Summission Date</th>' +
					'<th scope="col">Resolved Date</th>' +
					'<th scope="col">Description</th>' +
					'<th scope="col">Employee ID</th>' +
					'<th scope="col">Status</th>' +
					'<th scope="col">Type</th>';
				allTable.appendChild(th);

				reimb.forEach(object => {
					var tr = document.createElement('tr');
					let stat = object.statusID;
					let type = object.typeID;

					if (stat == 1) {
						stat = 'Pending';
					}
					else if (stat == 2) {
						stat = 'Approved';
					}
					else {
						stat = 'Denied';
					}

					if (type == 10) {
						type = 'Lodging';
					}
					else if (stat == 20) {
						type = 'Travel';
					}
					else if (type == 30) {
						type = 'Food';
					}
					else {
						type = 'Other';
					}

					tr.innerHTML = '<td>' + object.reimbID + '</td>' +
						'<td>' + object.reimAmount + '</td>' +
						'<td>' + object.reimSubmit + '</td>' +
						'<td>' + object.reimResolve + '</td>' +
						'<td>' + object.description + '</td>' +
						'<td>' + object.authorID + '</td>' +
						'<td>' + stat + '</td>' +
						'<td>' + type + '</td>';

					allTable.appendChild(tr);
				});




			}

		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1_ERS/allReimb.json");
	xhttp.send();
}

viewAll.addEventListener('click', getAllReimb);


//Process a pending reimbursement: div form processReimb

let processReimb1 = document.getElementById('processReimb');
//add nav element
let manProcess = document.getElementById('managerprocess');
manProcess.addEventListener('click',processReimb);

function processReimb(){
	processReimb1.style.display = 'block';
	allReimb.style.display = 'none';

}



function funcEmpID(table) {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("inputEmpID");
	filter = input.value.toUpperCase();
	table = document.getElementById("allTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[5];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function funcReimbID(table) {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("inputReimbID");
	filter = input.value.toUpperCase();
	table = document.getElementById("allTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

function funcStatus(table) {
	// Declare variables
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("statusList");
	filter = input.value.toUpperCase();
	table = document.getElementById("allTable");
	tr = table.getElementsByTagName("tr");

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[6];
		if (td) {
			txtValue = td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

