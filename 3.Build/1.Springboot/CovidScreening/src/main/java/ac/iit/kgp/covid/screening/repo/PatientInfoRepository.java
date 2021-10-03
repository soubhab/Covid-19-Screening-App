package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.PatientInfoEntity;

public interface PatientInfoRepository extends JpaRepository<PatientInfoEntity, Integer> 
{
	PatientInfoEntity findByPhoneNo(String phoneNo);
	PatientInfoEntity findByAdharCardNo(String adharCardNo);
	PatientInfoEntity findByEmailId(String emailId);
	PatientInfoEntity findById(int id);
	
	PatientInfoEntity findByPhoneNoAndPasswordText(String phoneNo, String passwordText);
}
