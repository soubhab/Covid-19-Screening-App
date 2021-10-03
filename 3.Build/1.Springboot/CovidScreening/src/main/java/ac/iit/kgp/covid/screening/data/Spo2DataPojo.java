package ac.iit.kgp.covid.screening.data;

public class Spo2DataPojo 
{
	private String patientId="";
	private String phoneName="";
	private String phoneManufacturer="";
	private String phoneOsName="";
	private String phoneOsVersion="";
	private String spo2DeviceName="";
	private String spo2DeviceModelNumber="";
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
	
	public String getPhoneName() {
		return phoneName;
	}
	
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	
	public String getPhoneManufacturer() {
		return phoneManufacturer;
	}
	
	public void setPhoneManufacturer(String phoneManufacturer) {
		this.phoneManufacturer = phoneManufacturer;
	}
	
	public String getPhoneOsName() {
		return phoneOsName;
	}
	
	public void setPhoneOsName(String phoneOsName) {
		this.phoneOsName = phoneOsName;
	}
	
	public String getPhoneOsVersion() {
		return phoneOsVersion;
	}
	
	public void setPhoneOsVersion(String phoneOsVersion) {
		this.phoneOsVersion = phoneOsVersion;
	}
	
	public String getSpo2DeviceName() {
		return spo2DeviceName;
	}
	
	public void setSpo2DeviceName(String spo2DeviceName) {
		this.spo2DeviceName = spo2DeviceName;
	}
	
	public String getSpo2DeviceModelNumber() {
		return spo2DeviceModelNumber;
	}
	
	public void setSpo2DeviceModelNumber(String spo2DeviceModelNumber) {
		this.spo2DeviceModelNumber = spo2DeviceModelNumber;
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
