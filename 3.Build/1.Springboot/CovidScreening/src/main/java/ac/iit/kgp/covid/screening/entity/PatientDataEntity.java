package ac.iit.kgp.covid.screening.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient_data")
public class PatientDataEntity 
{
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="patientId")
	private int patientId;
	
	@Column(name="doctorId")
	private String doctorId;
	
	@Column(name="symptomDate")
	private Date symptomDate;
	
	@Column(name="doctorConsultation")
	private String doctorConsultation;
	
	@Column(name="doctorSuggestion")
	private String doctorSuggestion;
	
	@Column(name="covidTestDate")
	private Date covidTestDate;
	
	@Column(name="covidTestFileName")
	private String covidTestFileName;
	
	@Column(name="medicalHistory")
	private String medicalHistory;
	
	@Column(name="currentMedication")
	private String currentMedication;
	
	@Column(name="smokingHabit")
	private String smokingHabit;
	
	@Column(name="createDateTime")
	private String createDateTime;
	
	@Column(name="lastUpdatedDateTime")
	private String lastUpdatedDateTime;
	
	@Column(name="consentText")
	private String consentText;

	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public Date getSymptomDate() {
		return symptomDate;
	}

	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}

	public String getDoctorConsultation() {
		return doctorConsultation;
	}

	public void setDoctorConsultation(String doctorConsultation) {
		this.doctorConsultation = doctorConsultation;
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

	public String getCovidTestFileName() {
		return covidTestFileName;
	}

	public void setCovidTestFileName(String covidTestFileName) {
		this.covidTestFileName = covidTestFileName;
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

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}
}
