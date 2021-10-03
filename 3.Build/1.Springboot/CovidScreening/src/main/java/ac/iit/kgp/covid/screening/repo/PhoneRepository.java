package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer>
{
	PhoneEntity findByPatientId(String patientId);
}
