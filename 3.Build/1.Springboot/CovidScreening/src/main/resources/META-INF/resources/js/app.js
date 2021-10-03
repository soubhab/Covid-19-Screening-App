function getCurrentYear() {
  var d = new Date();
  var n = d.getFullYear();
  document.getElementById("lblYear").innerHTML = n;
}

function returnBaseUrl()
{
	var url = "http://localhost:8050/";
	return url;
}