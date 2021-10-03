$(document).ready ( function() {
	getCurrentYear();

	sessionStorage.setItem("menuStatusMessage", "");
	
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

function logout()
{
	sessionStorage.setItem("status", "");
	sessionStorage.setItem("patientId", "");
	sessionStorage.setItem("firstName", "");
	sessionStorage.setItem("lastName", "");
	
	window.location.replace("login.html");
}

function collectCovidRegData()
{
	var symtomDate = document.getElementById("symtomDate").value.trim();
	var fever = document.getElementById("fever").value.trim();
	var sore = document.querySelector('input[name="sore"]:checked').value;
	var bodyache = document.querySelector('input[name="bodyache"]:checked').value;
	var smell = document.querySelector('input[name="smell"]:checked').value;
	var taste = document.querySelector('input[name="taste"]:checked').value;
	var diarrhea = document.querySelector('input[name="diarrhea"]:checked').value;
	var consultantDoctor = document.getElementById("consultantDoctor").value.trim();
	var doctorSuggestion = document.getElementById("sug").value.trim();
	var covidTestDate = document.getElementById("covidTestDate").value.trim();
	var medicalHistory = document.querySelector('input[name="medh"]:checked').value;
	var currentMedication = document.getElementById("currentMedication").value.trim();
	var smokingHabit = document.querySelector('input[name="medh2"]:checked').value;
	
	var errorMessage = "";
	
	if(symtomDate == "")
	{
		errorMessage= "*Please select the symtom date.";
		displayModal(errorMessage);
	}
	else
	{
		if(fever == "")
		{
			errorMessage= "*Fever field cannot be empty.";
			displayModal(errorMessage);
		}
		else
		{
			if(consultantDoctor == "")
			{
				errorMessage= "*Consultant doctor field cannot be empty.";
				displayModal(errorMessage);
			}
			else
			{
				if(doctorSuggestion == "")
				{
					errorMessage= "*Doctor suggestion field cannot be empty.";
					displayModal(errorMessage);
				}
				else
				{
					if(covidTestDate == "")
					{
						errorMessage= "*Please select the covid test date.";
						displayModal(errorMessage);
					}
					else
					{
						if(currentMedication == "")
						{
							errorMessage= "*Current medication field cannot be empty.";
							displayModal(errorMessage);
						}
						else
						{
							var covidTestResultFile = $("#covidTestResult")[0].files;
							var xRayFile = $("#xRayFile")[0].files;
							
							if(covidTestResultFile.length <= 0)
							{
								errorMessage= "*Please select the covid test result file.";
								displayModal(errorMessage);
							}
							else
							{
								if(xRayFile.length <= 0)
								{
									errorMessage= "*Please select the chest X-Ray file.";
									displayModal(errorMessage);
								}
								else
								{
									var patientId = sessionStorage.getItem("patientId");
									var consentText = sessionStorage.getItem("consentText");
										
									var fd = new FormData();
									
									fd.append('covidDataFile',covidTestResultFile[0]);
									fd.append('chestXrayFile',xRayFile[0]);
									
									var covidDataRegJsonObject = {"patientId":patientId,"feverTemparature":fever,"soreThroatStatus":sore,"bodyAcheStatus":bodyache,"smellStatus":smell,"tasteStatus":taste,"diarrheaStatus":diarrhea,"consultantDoctor":consultantDoctor,"doctorSuggestion":doctorSuggestion,"symptomDate":symtomDate,"covidTestDate":covidTestDate,"medicalHistory":medicalHistory,"currentMedication":currentMedication,"smokingHabit":smokingHabit,"consentText":consentText};
									var covidDataRegJson = JSON.stringify(covidDataRegJsonObject);
									
									fd.append('patientData', covidDataRegJson);
									
									var covidRegDataUrl = "patientdata/uploadCovidSymtomsReg";

									$.ajax({
							              url: returnBaseUrl() +covidRegDataUrl,
							              type: 'POST',
							              data: fd,
							              contentType: false,
							              processData: false,
							              success: function(result){
							            	  
							            	if(result.statusMessage == 'Error')
											{
												displayModal(result.message);
											}
											else
											{
											    // Store
											    sessionStorage.setItem("menuStatusMessage", "*Record successfully inserted.");
											    // Redirect
											    window.location.replace("menu.html");
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