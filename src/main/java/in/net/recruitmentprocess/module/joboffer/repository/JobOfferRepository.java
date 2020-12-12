package in.net.recruitmentprocess.module.joboffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.net.recruitmentprocess.module.joboffer.model.JobOffer;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, String>{

	JobOffer findByJobTitleIgnoreCase(String jobTitle);

}
