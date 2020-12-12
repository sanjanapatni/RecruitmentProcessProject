package in.net.recruitmentprocess.module.candidateapplication.service;

import java.util.List;

import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;


public interface CandidateApplicationService {

	CandidateApplication saveCandidateApplication(CandidateApplication candidateApplication);
	
	List<CandidateApplication> getAllCandidateApplication();
	
	List<CandidateApplication> getCandidateApplicationByOffer(String relatedOffer);

	CandidateApplication getUniqueCandidateApplication(CandidateApplicationPK candidateApplicationPK);

}
