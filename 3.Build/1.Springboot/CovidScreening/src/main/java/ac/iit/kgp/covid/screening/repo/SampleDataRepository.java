package ac.iit.kgp.covid.screening.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.iit.kgp.covid.screening.entity.SampleDataEntity;

public interface SampleDataRepository  extends JpaRepository<SampleDataEntity, Integer>
{

}
