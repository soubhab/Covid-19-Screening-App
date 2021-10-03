package ac.iit.kgp.covid.screening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.iit.kgp.covid.screening.data.PatientChangeForgetPassword;
import ac.iit.kgp.covid.screening.data.PatientLoginInput;
import ac.iit.kgp.covid.screening.data.PatientOutput;
import ac.iit.kgp.covid.screening.data.PatientRegistrationInput;
import ac.iit.kgp.covid.screening.entity.PatientInfoEntity;
import ac.iit.kgp.covid.screening.service.Impl.PatientFunctionalitiesImpl;

@RestController
@RequestMapping("/patientinfo")
public class PatientInfoController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PatientFunctionalitiesImpl patientFunctionalitiesImpl;
	
	@GetMapping("/health/status")
	public String healthStatusCall()
	{
		return "App is up and running from patient info controller.";
	}
	
	@PostMapping("/registration")
	public PatientOutput registerPatient(@RequestBody PatientRegistrationInput patientRegistrationInput)
	{
		logger.info("++++++++++ Inside PatientController +++++++ Inside registerPatient() ++++++++++++++");
		
		return patientFunctionalitiesImpl.executeRegistration(patientRegistrationInput);
	}
	
	@PostMapping(value = "/login"/*, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE*/)
	public PatientOutput loginPatient(@RequestBody PatientLoginInput patientLoginInput)
	{
		logger.info("++++++++++ Inside PatientController +++++++ Inside loginPatient() ++++++++++++++");
		
		return patientFunctionalitiesImpl.patientLogin(patientLoginInput);
	}
	
	@PostMapping("/forget/password")
	public PatientOutput changeForgetPassword(@RequestBody PatientChangeForgetPassword patientInput)
	{
		logger.info("++++++++++ Inside PatientController +++++++ Inside changeForgetPassword() ++++++++++++++");
		
		return patientFunctionalitiesImpl.forgetPasswordAction(patientInput);
	}
	
	@GetMapping(value="/patient/{patientId}")
	public PatientInfoEntity getPatientDetails(@PathVariable String patientId)
	{
		 return patientFunctionalitiesImpl.getPatient(Integer.parseInt(patientId));
	}
}