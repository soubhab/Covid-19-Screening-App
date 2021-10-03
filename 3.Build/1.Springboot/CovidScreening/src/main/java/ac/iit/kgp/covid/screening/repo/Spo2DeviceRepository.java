package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.Spo2DeviceEntity;


public interface Spo2DeviceRepository extends JpaRepository<Spo2DeviceEntity, Integer> 
{
	Spo2DeviceEntity findByPatientId(String patientId);
}
