package in.net.recruitmentprocess.module.candidateapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.service.CandidateApplicationService;

@RestController
@RequestMapping("/recruitment-process")
public class CandidateApplicationController {

	@Autowired 
	private CandidateApplicationService candidateApplicationService;
	
	/**API for Candidate to save application if not exist**/
	@PostMapping("/save-candidate-application")
	public CandidateApplication saveCandidateApplication(
			@RequestBody CandidateApplication candidateApplication) {
		return candidateApplicationService.saveCandidateApplication(candidateApplication);
	}
	
}
