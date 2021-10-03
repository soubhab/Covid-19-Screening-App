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

function collectCovidLogData()
{
	var fever = document.getElementById("fever").value.trim();
	var sore = document.querySelector('input[name="sore"]:checked').value;
	var bodyache = document.querySelector('input[name="bodyache"]:checked').value;
	var smell = document.querySelector('input[name="smell"]:checked').value;
	var taste = document.querySelector('input[name="taste"]:checked').value;
	var diarrhea = document.querySelector('input[name="diarrhea"]:checked').value;
	
	var errorMessage = "";
	
	if(fever == "")
	{
		errorMessage= "*Fever field cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		var xRayFile = $("#xRayFile")[0].files;
		
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
			fd.append('chestXrayFile',xRayFile[0]);
			
			var covidDataLogJsonObject = {"patientId":patientId,"feverTemparature":fever,"soreThroatStatus":sore,"bodyAcheStatus":bodyache,"smellStatus":smell,"tasteStatus":taste,"diarrheaStatus":diarrhea,"consentText":consentText};
			var covidDataLogJson = JSON.stringify(covidDataLogJsonObject);
			
			fd.append('patientData', covidDataLogJson);
			
			var covidLogDataUrl = "patientdata/uploadCovidSymtoms";

			$.ajax({
	              url: returnBaseUrl() + covidLogDataUrl,
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