package in.net.recruitmentprocess.module.candidateapplication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.net.recruitmentprocess.common.exception.ResourceAlreadyExistException;
import in.net.recruitmentprocess.common.exception.ResourceNotFoundException;
import in.net.recruitmentprocess.common.util.RecruitmentProcessConstants;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.candidateapplication.repository.CandidateApplicationRepository;
import in.net.recruitmentprocess.module.joboffer.model.JobOffer;
import in.net.recruitmentprocess.module.joboffer.repository.JobOfferRepository;

@Service
public class CandidateApplicationServiceImpl implements CandidateApplicationService {

	@Autowired
	private CandidateApplicationRepository candidateApplicationRepository;

	@Autowired
	private JobOfferRepository jobOfferRepository;
	
	@Override
	public CandidateApplication saveCandidateApplication(
			CandidateApplication candidateApplication) {
		checkJobOfferExist(candidateApplication.getId().getRelatedOffer());
		CandidateApplication application = candidateApplicationRepository
				.findByIdEmailAndIdRelatedOffer(
				candidateApplication.getId().getEmail(),
				candidateApplication.getId().getRelatedOffer());
		if (application != null)
			throw new ResourceAlreadyExistException(
					RecruitmentProcessConstants.RESOURCE_ALREADY_EXIST_MESSAGE
					+ candidateApplication.getId().getEmail());

		candidateApplication.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		return candidateApplicationRepository.save(candidateApplication);
	}

	private void checkJobOfferExist(String jobTitle) {
		JobOffer jobOffer = jobOfferRepository.findByJobTitleIgnoreCase(jobTitle);
		if (jobOffer == null)
			throw new ResourceNotFoundException(
					RecruitmentProcessConstants.RESOURCE_NON_EXISTENCE_MESSAGE+jobTitle);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CandidateApplication> getAllCandidateApplication() {
		List<CandidateApplication> candidateApplicationList = 
				candidateApplicationRepository.findAll();
		if (candidateApplicationList.isEmpty())
			throw new ResourceNotFoundException(
					RecruitmentProcessConstants.RESOURCE_NOT_FOUND_MESSAGE);

		return uniqueCandidateList(candidateApplicationList);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CandidateApplication> getCandidateApplicationByOffer(String relatedOffer) {
		List<CandidateApplication> candidateApplicationList =
				candidateApplicationRepository.findByIdRelatedOfferIgnoreCase(relatedOffer);

		checkCandidateApplicationList(candidateApplicationList);
		return uniqueCandidateList(candidateApplicationList);
	}

	private void checkCandidateApplicationList(
			List<CandidateApplication> candidateApplicationList) {
		
		if (candidateApplicationList.isEmpty())
			throw new ResourceNotFoundException(
					RecruitmentProcessConstants.RESOURCE_NOT_FOUND_MESSAGE);
	}

	private List<CandidateApplication> uniqueCandidateList(List<CandidateApplication> candidateApplicationList) {

		return candidateApplicationList.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public CandidateApplication getUniqueCandidateApplication(
			CandidateApplicationPK candidateApplicationPK) {
		
		Optional<CandidateApplication> application = candidateApplicationRepository
				.findById(candidateApplicationPK);

		if (!application.isPresent())
			throw new ResourceNotFoundException(
					RecruitmentProcessConstants.RESOURCE_NOT_FOUND_MESSAGE);
		
		return application.get();
	}

}
