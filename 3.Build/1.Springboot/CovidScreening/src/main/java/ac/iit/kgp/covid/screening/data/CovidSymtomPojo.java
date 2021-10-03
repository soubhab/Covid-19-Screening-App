package ac.iit.kgp.covid.screening.data;

import java.util.Date;

public class CovidSymtomPojo 
{
	private int patientId;
	private Date symptomDate;
	private String feverTemparature;
	private String soreThroatStatus;
	private String bodyAcheStatus;
	private String smellStatus;
	private String tasteStatus;
	private String diarrheaStatus;
	private String consultantDoctor;
	private String doctorSuggestion;
	private Date covidTestDate;
	private String medicalHistory;
	private String currentMedication;
	private String smokingHabit;
	private String consentText;
	
	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
	}

	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public Date getSymptomDate() {
		return symptomDate;
	}
	
	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}
	
	public String getFeverTemparature() {
		return feverTemparature;
	}
	
	public void setFeverTemparature(String feverTemparature) {
		this.feverTemparature = feverTemparature;
	}
	
	public String getSoreThroatStatus() {
		return soreThroatStatus;
	}
	
	public void setSoreThroatStatus(String soreThroatStatus) {
		this.soreThroatStatus = soreThroatStatus;
	}
	
	public String getBodyAcheStatus() {
		return bodyAcheStatus;
	}
	
	public void setBodyAcheStatus(String bodyAcheStatus) {
		this.bodyAcheStatus = bodyAcheStatus;
	}
	
	public String getSmellStatus() {
		return smellStatus;
	}
	
	public void setSmellStatus(String smellStatus) {
		this.smellStatus = smellStatus;
	}
	
	public String getTasteStatus() {
		return tasteStatus;
	}
	
	public void setTasteStatus(String tasteStatus) {
		this.tasteStatus = tasteStatus;
	}
	
	public String getDiarrheaStatus() {
		return diarrheaStatus;
	}
	
	public void setDiarrheaStatus(String diarrheaStatus) {
		this.diarrheaStatus = diarrheaStatus;
	}
	
	public String getConsultantDoctor() {
		return consultantDoctor;
	}

	public void setConsultantDoctor(String consultantDoctor) {
		this.consultantDoctor = consultantDoctor;
	}

	public String getDoctorSuggestion() {
		return doctorSuggestion;
	}
	
	public void setDoctorSuggestion(String doctorSuggestion) {
		this.doctorSuggestion = doctorSuggestion;
	}
	
	public Date getCovidTestDate() {
		return covidTestDate;
	}
	
	public void setCovidTestDate(Date covidTestDate) {
		this.covidTestDate = covidTestDate;
	}
	
	public String getMedicalHistory() {
		return medicalHistory;
	}
	
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	public String getCurrentMedication() {
		return currentMedication;
	}
	
	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}
	
	public String getSmokingHabit() {
		return smokingHabit;
	}
	
	public void setSmokingHabit(String smokingHabit) {
		this.smokingHabit = smokingHabit;
	}
}
