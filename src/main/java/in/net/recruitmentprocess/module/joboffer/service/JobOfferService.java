package in.net.recruitmentprocess.module.joboffer.service;

import java.util.List;

import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.joboffer.model.JobOffer;


public interface JobOfferService {

	JobOffer saveJobOffer(JobOffer jobOffer);
	
	List<JobOffer> getAllJobOffer();
	
	JobOffer getJobOfferByJobTitle(String jobTitle);
	
	List<CandidateApplication> getAllCandidateApplication();
	
	List<CandidateApplication> getCandidateApplicationByOffer(String relatedOffer);
	
	CandidateApplication getUniqueCandidateApplication(CandidateApplicationPK candidateApplicationPK);

}
