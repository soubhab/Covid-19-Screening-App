package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.SymptomEntity;

public interface SymptomRepository extends JpaRepository<SymptomEntity, Integer>
{

}
