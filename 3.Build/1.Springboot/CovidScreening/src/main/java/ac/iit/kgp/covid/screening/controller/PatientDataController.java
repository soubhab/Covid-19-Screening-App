package ac.iit.kgp.covid.screening.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ac.iit.kgp.covid.screening.data.AlreadyRegisterPojo;
import ac.iit.kgp.covid.screening.data.PatientDataResponse;
import ac.iit.kgp.covid.screening.entity.PatientDataEntity;
import ac.iit.kgp.covid.screening.entity.PhoneEntity;
import ac.iit.kgp.covid.screening.entity.Spo2DeviceEntity;
import ac.iit.kgp.covid.screening.service.Impl.PatientDataService;

@RestController
@RequestMapping("/patientdata")
public class PatientDataController 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private PatientDataService patientDataService;
	
	@GetMapping("/health/status")
	public String healthStatusCall()
	{
		return "App is up and running from patient data controller";
	}
	
	/*@PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();

        return new Response(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }*/
	
	@PostMapping(value="/uploadCovidSymtomsReg", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PatientDataResponse uploadCovidSymtomsReg(@RequestPart("patientData") String patientDataJson, @RequestPart("covidDataFile") MultipartFile covidDataFile, @RequestPart("chestXrayFile") MultipartFile chestXrayFile) 
	{
        return patientDataService.covidDataCollectionReg(patientDataJson, covidDataFile, chestXrayFile);
    }
	
/*	@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/

    /*@PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file - > uploadFile(file))
            .collect(Collectors.toList());
    }*/
	
	@PostMapping(value="/uploadSPO2infoReg", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PatientDataResponse uploadSPO2infoReg(@RequestPart("sampleData") String sampleDataJson, @RequestPart("coughSoundFile") MultipartFile coughSoundFile) 
	{
        return patientDataService.spo2DataCollectionReg(sampleDataJson, coughSoundFile);
    }
	
	@GetMapping(value="/phoneDeviceDetails/{patientId}")
	public PhoneEntity getPhoneDeviceDetails(@PathVariable String patientId)
	{
		 return patientDataService.getPhoneDeviceDetailsInfo(patientId);
	}
	
	@GetMapping(value="/spo2DeviceDetails/{patientId}")
	public Spo2DeviceEntity getSPO2DeviceDetails(@PathVariable String patientId)
	{
		 return patientDataService.getSPO2DeviceDetailsInfo(patientId);
	}
	
	@PostMapping(value="/uploadSPO2Data", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PatientDataResponse uploadSPO2info(@RequestPart("sampleData") String sampleDataJson, @RequestPart("coughSoundFile") MultipartFile coughSoundFile) 
	{
        return patientDataService.spo2DataCollection(sampleDataJson, coughSoundFile);
    }
	
	@PostMapping(value="/uploadCovidSymtoms", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PatientDataResponse uploadCovidSymtoms(@RequestPart("patientData") String patientDataJson, @RequestPart("chestXrayFile") MultipartFile chestXrayFile) 
	{
        return patientDataService.covidDataCollection(patientDataJson, chestXrayFile);
    }
	
	@GetMapping(value="/patientDataInfo/{patientId}")
	public AlreadyRegisterPojo getPatientDataInfo(@PathVariable int patientId)
	{
		 return patientDataService.getPatientDataInfoDetails(patientId);
	}
}
