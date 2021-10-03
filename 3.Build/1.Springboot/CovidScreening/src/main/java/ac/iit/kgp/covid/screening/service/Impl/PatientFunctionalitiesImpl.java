package ac.iit.kgp.covid.screening.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.iit.kgp.covid.screening.data.PatientChangeForgetPassword;
import ac.iit.kgp.covid.screening.data.PatientLoginInput;
import ac.iit.kgp.covid.screening.data.PatientOutput;
import ac.iit.kgp.covid.screening.data.PatientRegistrationInput;
import ac.iit.kgp.covid.screening.entity.PatientInfoEntity;
import ac.iit.kgp.covid.screening.repo.PatientInfoRepository;

@Service(value="patientFunctionalitiesImpl")
public class PatientFunctionalitiesImpl
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String SALT = "covid19";
	
	@Autowired
	private PatientInfoRepository patientRepo;
	
	public PatientOutput patientLogin(PatientLoginInput patientLoginInput) 
	{
		PatientOutput patientOutput = new PatientOutput();
		
		PatientInfoEntity patient = patientRepo.findByPhoneNoAndPasswordText(patientLoginInput.getPhoneNo().trim(), DigestUtils.sha256Hex(patientLoginInput.getPasswordText().trim() + SALT));
		
		if(patient != null)
		{
			logger.info("+++++++++ Patient First Name: " +patient.getFirstName()); 
			logger.info("+++++++++ Patient Last Name: " +patient.getLastName());
			
			patientOutput.setActionPerformed("Login");
			patientOutput.setMessage("Login Successful");
			patientOutput.setPatientId(patient.getId() +"");
			patientOutput.setStatusCode("200");
			patientOutput.setStatusMessage("Success");
			patientOutput.setFirstName(patient.getFirstName());
			patientOutput.setLastName(patient.getLastName());
		}
		else
		{
			patientOutput.setActionPerformed("Login");
			patientOutput.setMessage("* Incorrect phone number or password.");
			patientOutput.setPatientId("");
			patientOutput.setStatusCode("200");
			patientOutput.setStatusMessage("Error");
		}
		
		return patientOutput;
	}
	
	public PatientOutput forgetPasswordAction(PatientChangeForgetPassword patientInput)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date currentDate = new Date();  
	    
		PatientOutput patientOutput = new PatientOutput();
		
		PatientInfoEntity patient = patientRepo.findByPhoneNo(patientInput.getPhoneNo().trim());
		
		if(patient == null)
		{
			patientOutput.setActionPerformed("Forget Password");
			patientOutput.setMessage("*Patient does not exists");
			patientOutput.setPatientId("");
			patientOutput.setStatusCode("200");
			patientOutput.setStatusMessage("Error");
		}
		else
		{
			if((patientInput.getSecretQuestion().trim().equalsIgnoreCase(patient.getSecretQuestion()) && (patientInput.getSecretAnswer().trim().equalsIgnoreCase(patient.getSecretAnswer()))))
			{
				String newPasswordText = DigestUtils.sha256Hex(patientInput.getPasswordText().trim() + SALT);
				patient.setPasswordText(newPasswordText);
				patient.setLastUpdatedDateTime(sdf.format(currentDate));
				
				patient = patientRepo.save(patient);
				
				if(patient != null)
				{
					patientOutput.setActionPerformed("Forget Password");
					patientOutput.setMessage("Password changed successfully.");
					patientOutput.setPatientId(patient.getId() +"");
					patientOutput.setStatusCode("200");
					patientOutput.setStatusMessage("Success");
				}
				else
				{
					patientOutput.setActionPerformed("Forget Password");
					patientOutput.setMessage("*Internal error occurred");
					patientOutput.setPatientId("");
					patientOutput.setStatusCode("200");
					patientOutput.setStatusMessage("Error");
				}
			}
			else
			{
				patientOutput.setActionPerformed("Forget Password");
				patientOutput.setMessage("*Secret Question/Answer is not correct.");
				patientOutput.setPatientId("");
				patientOutput.setStatusCode("200");
				patientOutput.setStatusMessage("Error");
			}
		}
				
		return patientOutput;
	}

	public PatientOutput executeRegistration(PatientRegistrationInput patientRegistrationInput) 
	{
		logger.info("+++++++++ Inside execute() ++++++++++++"); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date date = new Date();  
		
		PatientOutput patientRegistrationOutput = new PatientOutput();
		
		if(validate(patientRegistrationInput))
		{
			PatientInfoEntity patient = new PatientInfoEntity();
			patient.setFirstName(patientRegistrationInput.getFirstName().trim());
			patient.setLastName(patientRegistrationInput.getLastName().trim());
			patient.setDob(patientRegistrationInput.getDob());
			patient.setGender(patientRegistrationInput.getGender().trim());
			patient.setAddressLine1(patientRegistrationInput.getAddressLine1().trim());
			patient.setAddressLine2(patientRegistrationInput.getAddressLine2().trim());
			patient.setAddressLine3(patientRegistrationInput.getAddressLine3().trim());
			patient.setPinNumber(patientRegistrationInput.getPinNumber().trim());
			patient.setPhoneNo(patientRegistrationInput.getPhoneNo().trim());
			patient.setEmailId(patientRegistrationInput.getEmailId().trim());
			patient.setAdharCardNo(patientRegistrationInput.getAdharCardNo().trim());
			patient.setHeight(patientRegistrationInput.getHeight().trim());
			patient.setWeight(patientRegistrationInput.getWeight().trim());
			patient.setPasswordText(DigestUtils.sha256Hex(patientRegistrationInput.getPasswordText().trim() + SALT));
			patient.setSecretQuestion(patientRegistrationInput.getSecretQuestion().trim());
			patient.setSecretAnswer(patientRegistrationInput.getSecretAnswer().trim());
			patient.setCreateDateTime(sdf.format(date));
			patient.setLastUpdatedDateTime(sdf.format(date));
			patient.setConsentText(patientRegistrationInput.getConsentText());
			
			PatientInfoEntity outputPatient = patientRepo.save(patient);
			
			if (outputPatient != null) {
				patientRegistrationOutput.setActionPerformed("Registration");
				patientRegistrationOutput.setMessage("Registration successful");
				patientRegistrationOutput.setPatientId(outputPatient.getId() +"");
				patientRegistrationOutput.setStatusCode("200");
				patientRegistrationOutput.setStatusMessage("Success");
				patientRegistrationOutput.setFirstName(patientRegistrationInput.getFirstName().trim());
				patientRegistrationOutput.setLastName(patientRegistrationInput.getLastName().trim());
			} else {
				patientRegistrationOutput.setActionPerformed("Registration");
				patientRegistrationOutput.setMessage("*Registration not successful");
				patientRegistrationOutput.setPatientId("");
				patientRegistrationOutput.setStatusCode("200");
				patientRegistrationOutput.setStatusMessage("Error");
			}
		}
		else
		{
			patientRegistrationOutput.setActionPerformed("Registration");
			patientRegistrationOutput.setMessage("*Adhar card no, email id and phone numbere nust be unique.");
			patientRegistrationOutput.setPatientId("");
			patientRegistrationOutput.setStatusCode("200");
			patientRegistrationOutput.setStatusMessage("Error");
		}
		
		logger.info("+++++++++ end of execute() ++++++++++++"); 
		
		return patientRegistrationOutput;
	}

	public boolean validate(PatientRegistrationInput patientRegistrationInput) 
	{
		PatientInfoEntity p1 = patientRepo.findByAdharCardNo(patientRegistrationInput.getAdharCardNo().trim());
		PatientInfoEntity p2 = patientRepo.findByPhoneNo(patientRegistrationInput.getPhoneNo().trim());
		PatientInfoEntity p3 = patientRepo.findByEmailId(patientRegistrationInput.getEmailId().trim());
		
		if(p1 != null) {
			logger.info("First Name (p1): " +p1.getFirstName());
		}
		
		if(p2 != null) {
			logger.info("First Name (p2): " +p2.getFirstName());
		}
		
		if(p3 != null) {
			logger.info("First Name (p3): " +p3.getFirstName());
		}
		
		if((p1 != null) || (p2 != null) || (p3 != null))
		{
			return false;
		}
		
		return true;
	}

	public PatientInfoEntity getPatient(int patientId) 
	{
		PatientInfoEntity patientInfoEntity = patientRepo.findById(patientId);
		return patientInfoEntity;
	}
}
