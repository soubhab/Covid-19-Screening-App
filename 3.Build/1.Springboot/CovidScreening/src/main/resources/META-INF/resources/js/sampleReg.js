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

function collectSampleRegData()
{
	var phoneModel = document.getElementById("phoneModel").value.trim();
	var phoneManufacturer = document.getElementById("phoneManufacturer").value.trim();
	var phoneOs = document.getElementById("phoneOs").value.trim();
	var phoneOsVersion = document.getElementById("phoneOsVersion").value.trim();
	var spo2DeviceName = document.getElementById("spo2DeviceName").value.trim();
	var spo2DeviceModelNumber = document.getElementById("spo2DeviceModelNumber").value.trim();
	var spo2Value = document.getElementById("spo2Value").value.trim();
	var pulseRate = document.getElementById("pulseRate").value.trim();
	
	var errorMessage = "";
	
	if(phoneModel == "")
	{
		errorMessage= "*Phone model field cannot be empty.";
		displayModal(errorMessage);
	}
	else
	{
		if(phoneManufacturer == "")
		{
			errorMessage= "*Phone manufacturer field cannot be empty.";
			displayModal(errorMessage);
		}
		else
		{
			if(phoneOs == "")
			{
				errorMessage= "*Phone os field cannot be empty.";
				displayModal(errorMessage);
			}
			else
			{
				if(phoneOsVersion == "")
				{
					errorMessage= "*Phone os version field cannot be empty.";
					displayModal(errorMessage);
				}
				else
				{
					if(spo2DeviceName == "")
					{
						errorMessage= "*SPO2 device field cannot be empty.";
						displayModal(errorMessage);
					}
					else
					{
						if(spo2DeviceModelNumber == "")
						{
							errorMessage= "*SPO2 device name model number field cannot be empty.";
							displayModal(errorMessage);
						}
						else
						{
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
										
										var SampleDataRegJsonObject = {"patientId":patientId,"phoneName":phoneModel,"phoneManufacturer":phoneManufacturer,"phoneOsName":phoneOs,"phoneOsVersion":phoneOsVersion,"spo2DeviceName":spo2DeviceName,"spo2DeviceModelNumber":spo2DeviceModelNumber,"spo2Value":spo2Value,"puslseRate":pulseRate,"consentText":consentText};
										var sampleDataRegJson = JSON.stringify(SampleDataRegJsonObject);
										
										fd.append('sampleData', sampleDataRegJson);
										
										var sampleRegDataUrl = "patientdata/uploadSPO2infoReg";
	
										$.ajax({
								              url: returnBaseUrl() +sampleRegDataUrl,
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
}