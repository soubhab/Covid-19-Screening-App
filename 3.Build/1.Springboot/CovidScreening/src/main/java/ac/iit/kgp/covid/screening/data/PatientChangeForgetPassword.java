package ac.iit.kgp.covid.screening.data;

public class PatientChangeForgetPassword 
{
	private String phoneNo;
	private String secretQuestion;
	private String secretAnswer;
	private String passwordText;
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}
	
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	public String getSecretAnswer() {
		return secretAnswer;
	}
	
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	
	public String getPasswordText() {
		return passwordText;
	}
	
	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}
}
