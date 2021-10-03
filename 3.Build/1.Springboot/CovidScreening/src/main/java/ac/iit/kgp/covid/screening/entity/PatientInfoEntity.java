package ac.iit.kgp.covid.screening.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="patient_info")
public class PatientInfoEntity
{
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@JsonIgnore
	@Column(name="dob")
	private Date dob;

	@JsonIgnore
	@Column(name="gender")
	private String gender;
	
	@JsonIgnore
	@Column(name="addressLine1")
	private String addressLine1;
	
	@JsonIgnore
	@Column(name="addressLine2")
	private String addressLine2;
	
	@JsonIgnore
	@Column(name="addressLine3")
	private String addressLine3;
	
	@JsonIgnore
	@Column(name="pinNumber")
	private String pinNumber;
	
	@JsonIgnore
	@Column(name="phoneNo")
	private String phoneNo;
	
	@JsonIgnore
	@Column(name="emailId")
	private String emailId;
	
	@JsonIgnore
	@Column(name="adharCardNo")
	private String adharCardNo;
	
	@JsonIgnore
	@Column(name="height")
	private String height;
	
	@JsonIgnore
	@Column(name="weight")
	private String weight;
	
	@JsonIgnore
	@Column(name="password")
	private String passwordText;
	
	@Column(name="secretQuestion")
	private String secretQuestion;
	
	@JsonIgnore
	@Column(name="secretAnswer")
	private String secretAnswer;
	
	@JsonIgnore
	@Column(name="createDateTime")
	private String createDateTime;
	
	@JsonIgnore
	@Column(name="lastUpdatedDateTime")
	private String lastUpdatedDateTime;
	
	@JsonIgnore
	@Column(name="consentText")
	private String consentText;
	
	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAdharCardNo() {
		return adharCardNo;
	}

	public void setAdharCardNo(String adharCardNo) {
		this.adharCardNo = adharCardNo;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}
}