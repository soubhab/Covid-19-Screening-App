package ac.iit.kgp.covid.screening.data;

public class PatientLoginInput
{
	private String phoneNo;
	private String passwordText;
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPasswordText() {
		return passwordText;
	}
	
	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}
}