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

function collectSampleLogData()
{
	var spo2Value = document.getElementById("spo2Value").value.trim();
	var pulseRate = document.getElementById("pulseRate").value.trim();
	
	var errorMessage = "";
	
	if(spo2Value == "")
	{
		errorMessage= "*SPO2 value field cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		if(pulseRate == "")
		{
			errorMessage= "*Pulse rate field cannot be empty.";
			displayModal(errorMessage);
		}
		else
		{
			var coughSoundFile = $("#coughSoundFile")[0].files;
			
			if(coughSoundFile.length <= 0)
			{
				errorMessage= "*Please select the cough sound file.";
				displayModal(errorMessage);
			}
			else
			{
				var patientId = sessionStorage.getItem("patientId");
				var consentText = sessionStorage.getItem("consentText");
					
				var fd = new FormData();
				
				fd.append('coughSoundFile',coughSoundFile[0]);
				
				var sampleLogJsonObject = {"patientId":patientId,"spo2Value":spo2Value,"puslseRate":pulseRate,"consentText":consentText};
				var sampleLogJson = JSON.stringify(sampleLogJsonObject);
				
				fd.append('sampleData', sampleLogJson);
				
				var sampleLogDataUrl = "patientdata/uploadSPO2Data";
				
				$.ajax({
		              url: returnBaseUrl() +sampleLogDataUrl,
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