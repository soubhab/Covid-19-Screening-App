package ac.iit.kgp.covid.screening.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symptom_data")
public class SymptomEntity
{
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="patientId")
	private int patientId;
	
	@Column(name="patientData")
	private int patientData;
	
	@Column(name="feverTemparature")
	private String feverTemparature;
	
	@Column(name="soreThroatStatus")
	private String soreThroatStatus;
	
	@Column(name="bodyAcheStatus")
	private String bodyAcheStatus;
	
	@Column(name="smellStatus")
	private String smellStatus;
	
	@Column(name="tasteStatus")
	private String tasteStatus;
	
	@Column(name="diarrheaStatus")
	private String diarrheaStatus;
	
	@Column(name="chestXrayFileName")
	private String chestXrayFileName;
	
	@Column(name="lastUpdatedDateTime")
	private String lastUpdatedDateTime;
	
	@Column(name="createDateTime")
	private String createDateTime;
	
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

	public int getPatientData() {
		return patientData;
	}

	public void setPatientData(int patientData) {
		this.patientData = patientData;
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

	public String getChestXrayFileName() {
		return chestXrayFileName;
	}

	public void setChestXrayFileName(String chestXrayFileName) {
		this.chestXrayFileName = chestXrayFileName;
	}

	public String getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
}
