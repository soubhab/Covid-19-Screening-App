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

function registerPatient()
{
	sessionStorage.setItem("statusMessage", "");
	
	var firstName = document.getElementById("fName").value.trim();
	var lastName = document.getElementById("lName").value.trim();
	var dob = document.getElementById("dobText").value;
	var gender = document.querySelector('input[name="gender"]:checked').value;
	
	var address1 = document.getElementById("address1").value.trim();
	var address2 = document.getElementById("address2").value.trim();
	var address3 = document.getElementById("address3").value.trim();
	var pinCode = document.getElementById("pinCode").value.trim();
	
	var phoneNo = document.getElementById("phoneNo").value.trim();
	var emailID = document.getElementById("emailID").value.trim();
	var adharNo = document.getElementById("adharNo").value.trim();
	var height = document.getElementById("height").value.trim();
	var weight = document.getElementById("weight").value.trim();
	var secretQuestionDropdown = document.getElementById("inputSecretQuestion").value.trim();
	//var secretQuestionText = secretQuestionDropdown.value;//secretQuestionDropdown.options[secretQuestionDropdown.selectedIndex];
	var inputSecretAnswer = document.getElementById("inputSecretAnswer").value.trim();
	var userPass = document.getElementById("userPass").value.trim();
	var inputConPassword = document.getElementById("inputConPassword").value.trim();
	
	var errorMessage = "";
	
	if (firstName == "") 
	{
		errorMessage= "*First Name cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		if (lastName == "") 
		{
			errorMessage= "*Last Name cannot be empty.";
			displayModal(errorMessage);
		}
		else
		{
			if (dob == "") 
			{
				errorMessage= "*Date of Birth cannot be empty.";
				displayModal(errorMessage);
			}
			else
			{
				if (gender == "") 
				{
					errorMessage= "*Gender cannot be empty.";
					displayModal(errorMessage);
				}
				else
				{
					if (phoneNo == "") 
					{
						errorMessage= "*Phone no cannot be empty.";
						displayModal(errorMessage);
					}
					else
					{
						if (emailID == "") 
						{
							errorMessage= "*emailID cannot be empty.";
							displayModal(errorMessage);
						}
						else
						{
							if (adharNo == "") 
							{
								errorMessage= "*adharNo cannot be empty.";
								displayModal(errorMessage);
							}
							else
							{
								if (height == "") 
								{
									errorMessage= "*height cannot be empty.";
									displayModal(errorMessage);
								}
								else
								{
									if (weight == "") 
									{
										errorMessage= "*weight cannot be empty.";
										displayModal(errorMessage);
									}
									else
									{
										if (secretQuestionDropdown == "") 
										{
											errorMessage= "*Select the secret question.";
											displayModal(errorMessage);
										}
										else
										{
											if (inputSecretAnswer == "") 
											{
												errorMessage= "*Secrec Answer field cannot be empty.";
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
														if (userPass.trim() != inputConPassword.trim()) 
														{
															errorMessage= "*Password field and Confirm password fields are not matchong..";
															displayModal(errorMessage);
														}
														else
														{
															var consentText = sessionStorage.getItem("consentText");
															//alert(consentText);
															
															var registrationJSONObject = {"firstName":firstName,"lastName":lastName,"dob":dob,"gender":gender,"addressLine1":address1,"addressLine2":address2,"addressLine3":address3,"pinNumber":pinCode,"phoneNo":phoneNo,"emailId":emailID,"adharCardNo":adharNo,"height":height,"weight":weight,"passwordText":userPass,"secretQuestion":secretQuestionDropdown,"secretAnswer":inputSecretAnswer,"consentText":consentText};
															//var registrationJson = JSON.stringify(registrationJSONObject);
														
															var registrationUrl = "patientinfo/registration";
															
															$.ajax({
																type : "POST",
																contentType : "application/json",
																url : returnBaseUrl() + registrationUrl,
																data : JSON.stringify(registrationJSONObject),
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
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}