package ac.iit.kgp.covid.screening.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.iit.kgp.covid.screening.data.AlreadyRegisterPojo;
import ac.iit.kgp.covid.screening.data.CovidSymtomPojo;
import ac.iit.kgp.covid.screening.data.FileUploadResponse;
import ac.iit.kgp.covid.screening.data.PatientDataResponse;
import ac.iit.kgp.covid.screening.data.Spo2DataPojo;
import ac.iit.kgp.covid.screening.data.Spo2Value;
import ac.iit.kgp.covid.screening.entity.SymptomEntity;
import ac.iit.kgp.covid.screening.entity.PatientDataEntity;
import ac.iit.kgp.covid.screening.entity.PhoneEntity;
import ac.iit.kgp.covid.screening.entity.SampleDataEntity;
import ac.iit.kgp.covid.screening.entity.Spo2DeviceEntity;
import ac.iit.kgp.covid.screening.exception.FileStorageException;
import ac.iit.kgp.covid.screening.properties.FileStorageProperties;
import ac.iit.kgp.covid.screening.repo.PatientDataRepository;
import ac.iit.kgp.covid.screening.repo.PhoneRepository;
import ac.iit.kgp.covid.screening.repo.SampleDataRepository;
import ac.iit.kgp.covid.screening.repo.Spo2DeviceRepository;
import ac.iit.kgp.covid.screening.repo.SymptomRepository;

@Service
public class PatientDataService 
{
	private final Path fileStorageLocation;
	
	@Autowired
	private PatientDataRepository patientDataRepository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private PhoneRepository phoneRepositoty;
	
	@Autowired
	private Spo2DeviceRepository spo2DeviceRepository;
	
	@Autowired
	private SampleDataRepository sampleDataRepository;

    @Autowired
    public PatientDataService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public PatientDataResponse covidDataCollectionReg(String patientDataJson, MultipartFile covidTestFile, MultipartFile chestXraytFile)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date currentDate = new Date();  
	    
    	FileUploadResponse covidTestFileResponse = storeFile(covidTestFile);
    	FileUploadResponse chestXraytFileResponse = storeFile(chestXraytFile);
    	
    	CovidSymtomPojo covidSymtomPojo = getCovidSymtomPojo(patientDataJson);
    	
    	PatientDataEntity patientDataEntity = new PatientDataEntity();
    	patientDataEntity.setPatientId(covidSymtomPojo.getPatientId());
    	patientDataEntity.setCovidTestDate(covidSymtomPojo.getCovidTestDate());
    	patientDataEntity.setCovidTestFileName(covidTestFileResponse.getFileName());
    	patientDataEntity.setCurrentMedication(covidSymtomPojo.getCurrentMedication());
    	patientDataEntity.setDoctorId(null);
    	patientDataEntity.setDoctorConsultation(covidSymtomPojo.getConsultantDoctor());
    	patientDataEntity.setDoctorSuggestion(covidSymtomPojo.getDoctorSuggestion());
    	patientDataEntity.setMedicalHistory(covidSymtomPojo.getMedicalHistory());
    	patientDataEntity.setSmokingHabit(covidSymtomPojo.getSmokingHabit());
    	patientDataEntity.setSymptomDate(covidSymtomPojo.getSymptomDate());
    	patientDataEntity.setCreateDateTime(sdf.format(currentDate));
    	patientDataEntity.setLastUpdatedDateTime(sdf.format(currentDate));
    	patientDataEntity.setConsentText(covidSymtomPojo.getConsentText());
    	
    	patientDataEntity = patientDataRepository.save(patientDataEntity);
    	
    	SymptomEntity symptomEntity = new SymptomEntity();
    	symptomEntity.setPatientId(covidSymtomPojo.getPatientId());
    	symptomEntity.setPatientData(patientDataEntity.getId());
    	symptomEntity.setFeverTemparature(covidSymtomPojo.getFeverTemparature());
    	symptomEntity.setSoreThroatStatus(covidSymtomPojo.getSoreThroatStatus());
    	symptomEntity.setBodyAcheStatus(covidSymtomPojo.getBodyAcheStatus());
    	symptomEntity.setSmellStatus(covidSymtomPojo.getSmellStatus());
    	symptomEntity.setTasteStatus(covidSymtomPojo.getTasteStatus());
    	symptomEntity.setDiarrheaStatus(covidSymtomPojo.getDiarrheaStatus());
    	symptomEntity.setChestXrayFileName(chestXraytFileResponse.getFileName());
    	symptomEntity.setCreateDateTime(sdf.format(currentDate));
    	symptomEntity.setLastUpdatedDateTime(sdf.format(currentDate));
    	symptomEntity.setConsentText(covidSymtomPojo.getConsentText());
    	
    	symptomEntity = symptomRepository.save(symptomEntity);
    	
    	PatientDataResponse patientDataResponse = new PatientDataResponse();
    	
    	List<FileUploadResponse> fileUploadResponseList = new ArrayList<FileUploadResponse>();
    	fileUploadResponseList.add(chestXraytFileResponse);
		fileUploadResponseList.add(covidTestFileResponse);
    	
    	if((patientDataEntity.getId() != null) && (symptomEntity.getId() != null)) 
    	{
    		patientDataResponse.setPatientId(patientDataEntity.getPatientId() +"");
    		patientDataResponse.setActionPerformed("Covid data collection");
    		patientDataResponse.setMessage("Success");
    		patientDataResponse.setStatusCode("200");
    		patientDataResponse.setStatusMessage("OK");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}	
    	else 
    	{
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    		patientDataResponse.setPatientId(null);
    		patientDataResponse.setActionPerformed("Covid data collection");
    		patientDataResponse.setMessage("Failure");
    		patientDataResponse.setStatusCode("200");
    		patientDataResponse.setStatusMessage("Error");
    	}
    	
    	return patientDataResponse;
    }
    
    private CovidSymtomPojo getCovidSymtomPojo(String patientDataJson) 
    {
    	CovidSymtomPojo covidSymtomPojo = new CovidSymtomPojo();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
			covidSymtomPojo = objectMapper.readValue(patientDataJson, CovidSymtomPojo.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
    	return covidSymtomPojo;
	}
    
    public PatientDataResponse spo2DataCollectionReg(String sampleDataJson, MultipartFile coughSoundFile)
    {
    	PatientDataResponse patientDataResponse = new PatientDataResponse();
	    
	    Spo2DataPojo spo2DataPojo = getSPO2DataPojo(sampleDataJson);
	    
	    FileUploadResponse coughSoundFileResponse = storeFile(coughSoundFile);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date currentDate = new Date(); 
	    
	    PhoneEntity phoneEntity = new PhoneEntity();
	    phoneEntity.setPhoneName(spo2DataPojo.getPhoneName());
    	phoneEntity.setDeviceManufacturer(spo2DataPojo.getPhoneManufacturer());
    	phoneEntity.setOsName(spo2DataPojo.getPhoneOsName());
    	phoneEntity.setOsVersion(spo2DataPojo.getPhoneOsVersion());
    	phoneEntity.setPatientId(spo2DataPojo.getPatientId());
    	phoneEntity.setCreateDateTime(sdf.format(currentDate));
    	
    	phoneEntity = phoneRepositoty.save(phoneEntity);
    	
    	Spo2DeviceEntity spo2DeviceEntity = new Spo2DeviceEntity();
    	spo2DeviceEntity.setDeviceName(spo2DataPojo.getSpo2DeviceName());
    	spo2DeviceEntity.setModelNumber(spo2DataPojo.getSpo2DeviceModelNumber());
    	spo2DeviceEntity.setPatientId(spo2DataPojo.getPatientId());
    	spo2DeviceEntity.setCreateDateTime(sdf.format(currentDate));
    	
    	spo2DeviceEntity = spo2DeviceRepository.save(spo2DeviceEntity);
    	
    	SampleDataEntity sampleDataEntity = new SampleDataEntity();
    	sampleDataEntity.setPatientId(spo2DataPojo.getPatientId());
    	sampleDataEntity.setPhoneId(phoneEntity.getId());
    	sampleDataEntity.setCoughSoundFile(coughSoundFileResponse.getFileName());
    	sampleDataEntity.setSpo2deviceId(spo2DeviceEntity.getId());
    	sampleDataEntity.setSpo2Value(spo2DataPojo.getSpo2Value());
    	sampleDataEntity.setPuslseRate(spo2DataPojo.getPuslseRate());
    	sampleDataEntity.setCreateDateTime(sdf.format(currentDate));
    	sampleDataEntity.setLastUpdatedDateTime(sdf.format(currentDate));
    	sampleDataEntity.setConsentText(spo2DataPojo.getConsentText());
    	
    	sampleDataEntity = sampleDataRepository.save(sampleDataEntity);
    	
    	List<FileUploadResponse> fileUploadResponseList = new ArrayList<FileUploadResponse>();
    	fileUploadResponseList.add(coughSoundFileResponse);
    	
    	if(sampleDataEntity.getId() != null)
    	{
    		patientDataResponse.setStatusCode(200 +"");
    		patientDataResponse.setStatusMessage("Success");
    		patientDataResponse.setActionPerformed("Cough and SPO2 Data Collection");
    		patientDataResponse.setPatientId(spo2DataPojo.getPatientId());
    		patientDataResponse.setMessage("Cough and SPO2 Data Collection Successful");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}
    	else
    	{
    		patientDataResponse.setStatusCode(200 +"");
    		patientDataResponse.setStatusMessage("Failure");
    		patientDataResponse.setActionPerformed("Cough and SPO2 Data Collection");
    		patientDataResponse.setPatientId(spo2DataPojo.getPatientId());
    		patientDataResponse.setMessage("Error");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}
    	
    	return patientDataResponse;
    }

	private Spo2DataPojo getSPO2DataPojo(String sampleDataJson) 
	{
		Spo2DataPojo spo2DataPojo = new Spo2DataPojo();
		
		ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		spo2DataPojo = objectMapper.readValue(sampleDataJson, Spo2DataPojo.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return spo2DataPojo;
	}

	private FileUploadResponse storeFile(MultipartFile file) 
	{
        // Normalize file name

		long timeStamp = System.currentTimeMillis();
		String fileExtenStion = FilenameUtils.getExtension(file.getOriginalFilename());
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = timeStamp +"." +fileExtenStion;

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            
            FileUploadResponse fileUploadResponse = new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());

            return fileUploadResponse;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) throws FileNotFoundException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

	public PhoneEntity getPhoneDeviceDetailsInfo(String patientId) 
	{
		PhoneEntity phoneEntity = phoneRepositoty.findByPatientId(patientId);
		return phoneEntity;
	}

	public Spo2DeviceEntity getSPO2DeviceDetailsInfo(String patientId) 
	{
		Spo2DeviceEntity spo2DeviceEntity = spo2DeviceRepository.findByPatientId(patientId);
		return spo2DeviceEntity;
	}
	
	private Spo2Value getSpo2Value(String sampleDataJson) 
	{
		Spo2Value spo2Value = new Spo2Value();
		
		ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		spo2Value = objectMapper.readValue(sampleDataJson, Spo2Value.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return spo2Value;
	}

	public PatientDataResponse spo2DataCollection(String sampleDataJson, MultipartFile coughSoundFile) 
	{
		PatientDataResponse patientDataResponse = new PatientDataResponse();
	    
	    Spo2Value spo2Value = getSpo2Value(sampleDataJson);
	    
	    FileUploadResponse coughSoundFileResponse = storeFile(coughSoundFile);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date currentDate = new Date(); 
	    
	    List<FileUploadResponse> fileUploadResponseList = new ArrayList<FileUploadResponse>();
    	fileUploadResponseList.add(coughSoundFileResponse);
    	
    	PhoneEntity phoneEntity = phoneRepositoty.findByPatientId(spo2Value.getPatientId());
	    Spo2DeviceEntity spo2DeviceEntity = spo2DeviceRepository.findByPatientId(spo2Value.getPatientId());
	    
	    SampleDataEntity sampleDataEntity = new SampleDataEntity();
    	sampleDataEntity.setPatientId(spo2Value.getPatientId());
    	sampleDataEntity.setPhoneId(phoneEntity.getId());
    	sampleDataEntity.setCoughSoundFile(coughSoundFileResponse.getFileName());
    	sampleDataEntity.setSpo2deviceId(spo2DeviceEntity.getId());
    	sampleDataEntity.setSpo2Value(spo2Value.getSpo2Value());
    	sampleDataEntity.setPuslseRate(spo2Value.getPuslseRate());
    	sampleDataEntity.setCreateDateTime(sdf.format(currentDate));
    	sampleDataEntity.setLastUpdatedDateTime(sdf.format(currentDate));
    	sampleDataEntity.setConsentText(spo2Value.getConsentText());
    	
    	sampleDataEntity = sampleDataRepository.save(sampleDataEntity);
    	
    	if(sampleDataEntity.getId() != null)
    	{
    		patientDataResponse.setStatusCode(200 +"");
    		patientDataResponse.setStatusMessage("Success");
    		patientDataResponse.setActionPerformed("Cough and SPO2 Data Collection");
    		patientDataResponse.setPatientId(spo2Value.getPatientId());
    		patientDataResponse.setMessage("Cough and SPO2 Data Collection Successful");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}
    	else
    	{
    		patientDataResponse.setStatusCode(200 +"");
    		patientDataResponse.setStatusMessage("Failure");
    		patientDataResponse.setActionPerformed("Cough and SPO2 Data Collection");
    		patientDataResponse.setPatientId(spo2Value.getPatientId());
    		patientDataResponse.setMessage("Error");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}
	    
	    
		return patientDataResponse;
	}

	public PatientDataResponse covidDataCollection(String patientDataJson, MultipartFile chestXrayFile) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    Date currentDate = new Date();  
	    
    	FileUploadResponse chestXraytFileResponse = storeFile(chestXrayFile);
    	
    	CovidSymtomPojo covidSymtomPojo = getCovidSymtomPojo(patientDataJson);
    	
    	PatientDataEntity patientDataEntity = patientDataRepository.findByPatientId(covidSymtomPojo.getPatientId());
    	
    	SymptomEntity symptomEntity = new SymptomEntity();
    	symptomEntity.setPatientId(covidSymtomPojo.getPatientId());
    	symptomEntity.setPatientData(patientDataEntity.getId());
    	symptomEntity.setFeverTemparature(covidSymtomPojo.getFeverTemparature());
    	symptomEntity.setSoreThroatStatus(covidSymtomPojo.getSoreThroatStatus());
    	symptomEntity.setBodyAcheStatus(covidSymtomPojo.getBodyAcheStatus());
    	symptomEntity.setTasteStatus(covidSymtomPojo.getTasteStatus());
    	symptomEntity.setSmellStatus(covidSymtomPojo.getSmellStatus());
    	symptomEntity.setDiarrheaStatus(covidSymtomPojo.getDiarrheaStatus());
    	symptomEntity.setChestXrayFileName(chestXraytFileResponse.getFileName());
    	symptomEntity.setCreateDateTime(sdf.format(currentDate));
    	symptomEntity.setLastUpdatedDateTime(sdf.format(currentDate));
    	symptomEntity.setConsentText(covidSymtomPojo.getConsentText());
    	
    	symptomEntity = symptomRepository.save(symptomEntity);
    	
    	PatientDataResponse patientDataResponse = new PatientDataResponse();
    	
    	List<FileUploadResponse> fileUploadResponseList = new ArrayList<FileUploadResponse>();
    	fileUploadResponseList.add(chestXraytFileResponse);
    	
    	if(symptomEntity.getId() != null) 
    	{
    		patientDataResponse.setPatientId(symptomEntity.getPatientId() +"");
    		patientDataResponse.setActionPerformed("Covid data collection");
    		patientDataResponse.setMessage("Success");
    		patientDataResponse.setStatusCode("200");
    		patientDataResponse.setStatusMessage("OK");
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    	}	
    	else 
    	{
    		patientDataResponse.setUploadFileStatus(fileUploadResponseList);
    		patientDataResponse.setPatientId(null);
    		patientDataResponse.setActionPerformed("Covid data collection");
    		patientDataResponse.setMessage("Failure");
    		patientDataResponse.setStatusCode("200");
    		patientDataResponse.setStatusMessage("Error");
    	}
    	
    	return patientDataResponse;
	}

	public AlreadyRegisterPojo getPatientDataInfoDetails(int patientId)
	{
		AlreadyRegisterPojo alreadyRegisterPojo = new AlreadyRegisterPojo();
		
		PatientDataEntity patientDataEntity = patientDataRepository.findByPatientId(patientId);
		
		if((patientDataEntity != null) && (patientDataEntity.getId() != null))
		{
			alreadyRegisterPojo.setIsPatientDataAvailable("yes");
		}
		else
		{
			alreadyRegisterPojo.setIsPatientDataAvailable("no");
		}
		
		PhoneEntity phoneEntity = phoneRepositoty.findByPatientId(patientId +"");
		
		if((phoneEntity != null) && (phoneEntity.getId() != null))
		{
			alreadyRegisterPojo.setIsDeviceRegistered("yes");
		}
		else
		{
			alreadyRegisterPojo.setIsDeviceRegistered("no");
		}
		
		return alreadyRegisterPojo;
	}
}
