$(document).ready ( function() {
   //alert('ok');
   
    var modal = document.getElementById("appModalodal");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	var statusMessage = sessionStorage.getItem("statusMessage");
	
	if(statusMessage != "")
	{
		displayModal(statusMessage);
	}
	
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
	
	getCurrentYear();
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

function loginFunctionality()
{
	var phoneNumber = document.getElementById("phoneNo").value.trim();
	var inputPassword = document.getElementById("inputPassword").value.trim();
	
	var errorMessage = "";
	
	if (phoneNumber == "") 
	{
		errorMessage= "*Phone number cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		if(inputPassword == "")
		{
			errorMessage = "*Password cannot be empty.";
			displayModal(errorMessage);
		}
		else
		{
			var loginJsonObject = {"phoneNo":phoneNumber,"passwordText":inputPassword};
			
			var loginJson = JSON.stringify(loginJsonObject);
			
			var loginUrl = "patientinfo/login";
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : returnBaseUrl() + loginUrl,
				data : JSON.stringify(loginJsonObject),
				dataType : 'json',
				success : function(result) {
					
					if(result.statusMessage == 'Error')
					{
						displayModal(result.message);
					}
					else
					{
						/*if (typeof(Storage) !== "undefined") 
						{ */
						  // Store
						  sessionStorage.setItem("patientId", result.patientId);
						  sessionStorage.setItem("firstName", result.firstName);
						  sessionStorage.setItem("lastName", result.lastName);
						  sessionStorage.setItem("status", "loggedin");
						  sessionStorage.setItem("menuStatusMessage", "");
						  
						  // Redirect
						  window.location.replace("menu.html");
						/*} else {
							displayModal("* Sorry, your browser does not support this web application.";
						}*/
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					displayModal("* Some server error occurred. Contact administrator.");
				}
			});
		}
	}
}