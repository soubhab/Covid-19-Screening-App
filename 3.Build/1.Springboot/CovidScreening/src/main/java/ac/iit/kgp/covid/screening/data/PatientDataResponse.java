package ac.iit.kgp.covid.screening.data;

import java.util.List;

public class PatientDataResponse 
{
	private String patientId;
	private String statusCode;
	private String statusMessage;
	private String actionPerformed;
	private String message;
	private List<FileUploadResponse> uploadFileStatus;
	//private FileUploadResponse covidTetsSoundFile;
	
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getActionPerformed() {
		return actionPerformed;
	}
	
	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public List<FileUploadResponse> getUploadFileStatus() {
		return uploadFileStatus;
	}

	public void setUploadFileStatus(List<FileUploadResponse> uploadFileStatus) {
		this.uploadFileStatus = uploadFileStatus;
	}
}