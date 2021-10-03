package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.PatientDataEntity;

public interface PatientDataRepository extends JpaRepository<PatientDataEntity, Integer>
{
	PatientDataEntity findByPatientId(int patientId);
}
