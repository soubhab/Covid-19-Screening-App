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

function forgetPassword()
{	
	var phoneNo = document.getElementById("phoneNo").value.trim();
	var secretQuestionDropdown = document.getElementById("inputSecretQuestion").value.trim();
	var inputSecretAnswer = document.getElementById("inputSecretAnswer").value.trim();
	var userPass = document.getElementById("userPass").value.trim();
	var inputConPassword = document.getElementById("inputConPassword").value.trim();
	
	if (phoneNo == "") 
	{
		errorMessage= "*Phone number cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		if (secretQuestionDropdown == "") 
		{
			errorMessage= "*Please select proper secret question.";
			displayModal(errorMessage);
		}
		else
		{
			if (inputSecretAnswer == "") 
			{
				errorMessage= "*Answer cannot be empty.";
				displayModal(errorMessage);
			}
			else
			{
				if (userPass == "") 
				{
					errorMessage= "*Password field cannot be empty.";
					displayModal(errorMessage);
				}
				else
				{
					if (inputConPassword == "") 
					{
						errorMessage= "*Confirm Password field cannot be empty.";
						displayModal(errorMessage);
					}
					else
					{
						if(userPass != inputConPassword)
						{
							errorMessage= "*Password field and Confirm Password field are not same.";
							displayModal(errorMessage);
						}
						else
						{
							var forgetPasswordJsonObject = {"phoneNo":phoneNo,"passwordText":userPass,"secretQuestion":secretQuestionDropdown,"secretAnswer":inputSecretAnswer};
							
						//	var forgetPasswordJson = JSON.stringify(forgetPasswordJsonObject);
							
							var forgetPasswordUrl = "patientinfo/forget/password";
							
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : returnBaseUrl() + forgetPasswordUrl,
								data : JSON.stringify(forgetPasswordJsonObject),
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
										  //sessionStorage.setItem("patientId", result.patientId);
										  //sessionStorage.setItem("firstName", result.firstName);
										  //sessionStorage.setItem("lastName", result.lastName);
										  sessionStorage.setItem("statusMessage", "*Password Changed successfully.");
										  
										  // Redirect
										  window.location.replace("login.html");
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
			}
		}
	}
}