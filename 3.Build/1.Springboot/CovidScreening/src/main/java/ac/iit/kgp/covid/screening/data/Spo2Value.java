package ac.iit.kgp.covid.screening.data;

public class Spo2Value 
{
	private String patientId="";
	private String spo2Value="";
	private String puslseRate="";
	private String consentText="";
	
	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
	}

	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getSpo2Value() {
		return spo2Value;
	}
	
	public void setSpo2Value(String spo2Value) {
		this.spo2Value = spo2Value;
	}
	
	public String getPuslseRate() {
		return puslseRate;
	}
	
	public void setPuslseRate(String puslseRate) {
		this.puslseRate = puslseRate;
	}
}