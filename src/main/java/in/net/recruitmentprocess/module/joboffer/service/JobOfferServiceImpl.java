package in.net.recruitmentprocess.module.joboffer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.net.recruitmentprocess.common.exception.ResourceAlreadyExistException;
import in.net.recruitmentprocess.common.exception.ResourceNotFoundException;
import in.net.recruitmentprocess.common.util.RecruitmentProcessConstants;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.candidateapplication.service.CandidateApplicationService;
import in.net.recruitmentprocess.module.joboffer.model.JobOffer;
import in.net.recruitmentprocess.module.joboffer.repository.JobOfferRepository;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferRepository jobOfferRepository;
	
	@Autowired
	private CandidateApplicationService candidateApplicationService;

	@Override
	public JobOffer saveJobOffer(JobOffer jobOffer) {
		checkJobTitleNullability(jobOffer);
		List<JobOffer> offerList = jobOfferRepository.findAll();
		if(offerList.stream().anyMatch(job -> 
		job.getJobTitle().equalsIgnoreCase(jobOffer.getJobTitle())))
			throw new ResourceAlreadyExistException(
					RecruitmentProcessConstants.RESOURCE_ALREADY_EXIST_MESSAGE + jobOffer.getJobTitle());
		
		return jobOfferRepository.save(jobOffer);
	}

	private void checkJobTitleNullability(JobOffer jobOffer) {
		if(jobOffer.getJobTitle() == null)
			throw new ResourceNotFoundException(
					RecruitmentProcessConstants.RESOURCE_NULL_MESSAGE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<JobOffer> getAllJobOffer() {
		List<JobOffer> offerList = jobOfferRepository.findAll();
		if (offerList.isEmpty())
			throw new ResourceNotFoundException(RecruitmentProcessConstants.RESOURCE_NOT_FOUND_MESSAGE);

		return offerList.stream().distinct().collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public JobOffer getJobOfferByJobTitle(String jobTitle) {
		JobOffer jobOffer = jobOfferRepository.findByJobTitleIgnoreCase(jobTitle);
		if (jobOffer == null)
			throw new ResourceNotFoundException(RecruitmentProcessConstants.RESOURCE_NOT_FOUND_MESSAGE);
		return jobOffer;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CandidateApplication> getAllCandidateApplication() {
		return candidateApplicationService.getAllCandidateApplication();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CandidateApplication> getCandidateApplicationByOffer(String relatedOffer) {
		return candidateApplicationService.getCandidateApplicationByOffer(relatedOffer);
	}

	@Override
	public CandidateApplication getUniqueCandidateApplication(CandidateApplicationPK candidateApplicationPK) {
		return candidateApplicationService.getUniqueCandidateApplication(candidateApplicationPK);
	}

	
}
