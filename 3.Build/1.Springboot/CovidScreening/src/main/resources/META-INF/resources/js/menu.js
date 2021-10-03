$(document).ready ( function() {
	
	getCurrentYear();
	sessionStorage.setItem("statusMessage", "");
	
	var modal = document.getElementById("appModalodal");
	//modal.style.display = "block";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];


	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
		modal.style.display = "none";
	  }
	}
	
	var loginStatus = sessionStorage.getItem("status");
	
	if(loginStatus == 'loggedin')
	{
		var firstName = sessionStorage.getItem("firstName");
		var lastName = sessionStorage.getItem("lastName");
		
		document.getElementById("patientName").innerHTML  = lastName +", " +firstName;
		
		var patientId = sessionStorage.getItem("patientId");
		var statusUrl = "patientdata/patientDataInfo/";
		
		var getUrl = returnBaseUrl() + statusUrl + patientId;
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : getUrl,
			//data : JSON.stringify(registrationJSONObject),
			dataType : 'json',
			success : function(result) {
				
				createBody(result.isPatientDataAvailable, result.isDeviceRegistered);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				displayModal("* Some server error occurred. Contact administrator.");
			}
		});
	}
	
	var statusMsg = sessionStorage.getItem("menuStatusMessage");
	
	if(statusMsg != "")
	{
		displayModal(statusMsg);
	}
	
});

function displayModal(medalMessage)
{
	var modal = document.getElementById("appModalodal");
	
	document.getElementById("modalText").innerHTML  = medalMessage;
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	modal.style.display = "block";
}

function closeModal()
{
	var modal = document.getElementById("appModalodal");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	
	modal.style.display = "none";
}

function logout()
{
	sessionStorage.setItem("status", "");
	sessionStorage.setItem("patientId", "");
	sessionStorage.setItem("firstName", "");
	sessionStorage.setItem("lastName", "");
	
	window.location.replace("login.html");
}

function createBody(isPatientDataAvailable, isDeviceRegistered)
{
	var covidReg = document.getElementById("covidReg");
	var covidLog = document.getElementById("covidLog");
	var sampleReg = document.getElementById("sampleReg");
	var sampleLog = document.getElementById("sampleLog");
	
	if(isPatientDataAvailable == "no")
	{
		covidReg.style.display = "block";
		covidLog.style.display = "none";
	}
	else
	{
		covidReg.style.display = "none";
		covidLog.style.display = "block";
	}
	
	if(isDeviceRegistered == "no")
	{
		sampleReg.style.display = "block";
		sampleLog.style.display = "none";
	}
	else
	{
		sampleReg.style.display = "none";
		sampleLog.style.display = "block";
	}
}