
let address_check_val = false;
let birth_check_val = false;
let idnumber_check_val = false;

let pwShown = 0;
document.addEventListener("DOMContentLoaded", function () {


	document.getElementById("alladdress").addEventListener("blur", check_address);
	document.getElementById("alladdress").addEventListener("blur", opencreate);

	document.getElementById("idnumber").addEventListener("blur", check_idnumber);
	document.getElementById("idnumber").addEventListener("blur", opencreate);

	document.getElementById("date").addEventListener("blur", check_birth);
	document.getElementById("date").addEventListener("blur", opencreate);

	

});
function check_idnumber() {
	if (document.getElementById("idnumber").value != "") {
		idnumber_check_val = true;
		document.getElementById("idnumber_password").innerHTML = "<img src='../Images/yes.jpg'>格式符合"
	} else {
		document.getElementById("idnumber_password").innerHTML = "<img src='../Images/noway.jpg'>不可空白"

	}
}

function check_address() {
	if (document.getElementById("alladdress").value != "") {
		address_check_val = true;
		document.getElementById("address_password").innerHTML = "<img src='../Images/yes.jpg'>格式符合"
	} else {
		document.getElementById("address_password").innerHTML = "<img src='../Images/noway.jpg'>不可空白"

	}
}
function check_birth() {
	if (document.getElementById("date").value != "") {
		birth_check_val = true;
		document.getElementById("date_password").innerHTML = "<img src='../Images/yes.jpg'>格式符合"
	} else {
		document.getElementById("date_password").innerHTML = "<img src='../Images/noway.jpg'>不可空白"

	}

}

function opencreate() {

	if (address_check_val && birth_check_val && idnumber_check_val) {
		document.getElementById("submit").innerHTML = "<button type='submit'>新增</button>";
	}
	

	
	
}


