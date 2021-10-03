package ac.iit.kgp.covid.screening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import ac.iit.kgp.covid.screening.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@ComponentScan({
	"ac.iit.kgp.covid.screening.controller",
	"ac.iit.kgp.covid.screening.service.Impl",
	"ac.iit.kgp.covid.screening.repo",
	"ac.iit.kgp.covid.screening.entity"
})
public class CovidScreeningApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(CovidScreeningApplication.class, args);
	}
}