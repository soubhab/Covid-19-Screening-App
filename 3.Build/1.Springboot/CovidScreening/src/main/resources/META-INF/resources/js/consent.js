$(document).ready ( function() {
   //alert('ok');
   
    var modal = document.getElementById("appModalodal");

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
	
	getCurrentYear();
});

function displayModal(medalMessage)
{
	var modal = document.getElementById("appModalodal");
	
	document.getElementById("modalText").innerHTML  = medalMessage;
	
	modal.style.display = "block";
}

function closeModal()
{
	var modal = document.getElementById("appModalodal");
	
	modal.style.display = "none";
}


function checkConsentAndProceed()
{
	var radioConsentVal = document.getElementById("radioConsent").checked;
	if(radioConsentVal == true) {
		sessionStorage.setItem("statusMessage", "");
		sessionStorage.setItem("hasConsent", "yes");
		sessionStorage.setItem("consentText", "I hereby given consent to provide data voluntarily.");
		window.location.replace("login.html");
	} else {
		displayModal("* You must have to select the radio button before proceed.");
	}
}
