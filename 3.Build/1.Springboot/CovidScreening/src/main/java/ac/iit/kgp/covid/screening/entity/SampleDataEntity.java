package ac.iit.kgp.covid.screening.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sample_table")
public class SampleDataEntity 
{
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="patientId")
	private String patientId;
	
	@Column(name="phoneId")
	private int phoneId;
	
	@Column(name="coughSoundFile")
	private String coughSoundFile;
	
	@Column(name="spo2deviceId")
	private int spo2deviceId;
	
	@Column(name="spo2Value")
	private String spo2Value;
	
	@Column(name="puslseRate")
	private String puslseRate;
	
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

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getCoughSoundFile() {
		return coughSoundFile;
	}

	public void setCoughSoundFile(String coughSoundFile) {
		this.coughSoundFile = coughSoundFile;
	}

	public int getSpo2deviceId() {
		return spo2deviceId;
	}

	public void setSpo2deviceId(int spo2deviceId) {
		this.spo2deviceId = spo2deviceId;
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
