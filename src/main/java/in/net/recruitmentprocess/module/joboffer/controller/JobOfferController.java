package in.net.recruitmentprocess.module.joboffer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.joboffer.model.JobOffer;
import in.net.recruitmentprocess.module.joboffer.service.JobOfferService;

@RestController
@RequestMapping("/recruitment-process")
public class JobOfferController {

	@Autowired 
	private JobOfferService jobOfferService;
	
	/**API for User to save the job offer if not exist**/
	@PostMapping("/save-job-offer")
	public JobOffer saveJobOffer(@RequestBody JobOffer jobOffer) {
		return jobOfferService.saveJobOffer(jobOffer);
	}
	
	/**API for User to retrieve all the job offer if exist**/
	@GetMapping("/get-all-job-offer")
	public List<JobOffer> getAllJobOffer() {
		return jobOfferService.getAllJobOffer();
	}
	
	/**API for User to retrieve the job offer with mentioned job title**/
	@GetMapping("/get-job-offer-by-job-title/{jobTitle}")
	public JobOffer getJobOfferByJobTitle(@PathVariable String jobTitle) {
		return jobOfferService.getJobOfferByJobTitle(jobTitle);
	}
	
	/**API to retrieve all the candidate application if exist.
	 * This API is related to Candidate Apllication but only USER can
	 * access these API, thats why it is written here**/
	
	@GetMapping("/get-all-candidate-application")
	public List<CandidateApplication> getAllCandidateApplication() {
		return jobOfferService.getAllCandidateApplication();
	}
	
	/**API to retrieve the candidate application with offer mentioned
	 * This API is related to Candidate Apllication but only USER can
	 * access these API, thats why it is written here**/
	
	@GetMapping("/get-candidate-application-by-offer/{relatedOffer}")
	public List<CandidateApplication> getCandidateApplicationByOffer(
			@PathVariable String relatedOffer) {
		return jobOfferService.getCandidateApplicationByOffer(relatedOffer);
	}
	
	/**API to retrieve the unique candidate application with related offer and email
	 * This API is related to Candidate Apllication but only USER can
	 * access these API, thats why it is written here**/
	
	@PostMapping("/get-unique-candidate-application")
	public CandidateApplication getUniqueCandidateApplication(
			@RequestBody CandidateApplicationPK candidateApplicationPK) {
		return jobOfferService.getUniqueCandidateApplication(candidateApplicationPK);
	}
}
