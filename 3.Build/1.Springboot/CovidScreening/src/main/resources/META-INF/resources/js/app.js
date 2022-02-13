function getCurrentYear() {
  var d = new Date();
  var n = d.getFullYear();
  document.getElementById("lblYear").innerHTML = n;
}

function returnBaseUrl()
{
	var url = "http://localhost:8080/CovidScreening-1.0.4.RELEASE/";
	return url;
}