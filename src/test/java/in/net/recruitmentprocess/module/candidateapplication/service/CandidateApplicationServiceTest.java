package in.net.recruitmentprocess.module.candidateapplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import in.net.recruitmentprocess.common.util.RecruitmentProcessConstants;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.candidateapplication.repository.CandidateApplicationRepository;

@SpringBootTest
class CandidateApplicationServiceTest {

	@Autowired
	private CandidateApplicationService candidateApplicationService;
	@MockBean
	private CandidateApplicationRepository candidateApplicationRepository;

	/** Test to check save operation **/
	@Test
	void testSaveCandidateApplication() {
		CandidateApplication mockCandidateApplication = getMockForCandidateApplication();
		Mockito.when(candidateApplicationRepository.save(mockCandidateApplication))
		.thenReturn(mockCandidateApplication);
		assertThat(candidateApplicationService.saveCandidateApplication(mockCandidateApplication))
		.isEqualTo(mockCandidateApplication);
	}

	/** Test to check all data is retrieved **/
	@Test
	void testGetAllCandidateApplication() {
		List<CandidateApplication> mockCandidateApplicationList = getMockDataListForCandidateApplication();
		when(candidateApplicationRepository.findAll())
		.thenReturn(mockCandidateApplicationList);
		assertEquals(2, candidateApplicationService.getAllCandidateApplication().size());
	}

	/** Test to check data with job offer is retrieved **/
	@Test
	void testGetCandidateApplicationByOffer() {
		List<CandidateApplication> mockCandidateApplicationList =
				getMockListForJavaCandidateApplication();
		String relatedOffer = "Java Developer";
		
		Mockito.when(candidateApplicationRepository
				.findByIdRelatedOfferIgnoreCase(relatedOffer))
		.thenReturn(mockCandidateApplicationList);
		
		assertEquals(2, candidateApplicationService.
				getCandidateApplicationByOffer(relatedOffer).size());
	}

	/** Test to check data with related offer and email is retrieved **/
	@Test
	void testGetUniqueCandidateApplication() {
		String jobOffer = "Java Developer";
		String email = "sanjanapatni@gmail.com";
		CandidateApplicationPK mockCandidateApplicationPK = new CandidateApplicationPK();
		mockCandidateApplicationPK.setEmail(email);
		mockCandidateApplicationPK.setRelatedOffer(jobOffer);
		CandidateApplication mockCandidateApplication = getMockForCandidateApplication(); 
		Mockito.when(candidateApplicationRepository.findById(mockCandidateApplicationPK))
		.thenReturn(Optional.of(mockCandidateApplication));
		assertThat(candidateApplicationService.
				getUniqueCandidateApplication(mockCandidateApplicationPK))
		.isEqualTo(mockCandidateApplication);
	}
	
	/** Required data set **/ 
	private CandidateApplication getMockForCandidateApplication() {
		CandidateApplication mockCandidateApplication = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK = new CandidateApplicationPK();
		mockCandidateApplicationPK.setRelatedOffer("Java Developer");
		mockCandidateApplicationPK.setEmail("sanjanapatni@gmail.com");
		
		mockCandidateApplication.setId(mockCandidateApplicationPK);
		mockCandidateApplication.setResumeText("Looking for Java Developer opportunities.");
		mockCandidateApplication.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		return mockCandidateApplication;
	}
	
	private List<CandidateApplication> getMockDataListForCandidateApplication() {
		List<CandidateApplication> mockCandidateApplicationList = new ArrayList<>();
		CandidateApplication mockCandidateApplication1 = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK1 = new CandidateApplicationPK();
		mockCandidateApplicationPK1.setRelatedOffer("Java Developer");
		mockCandidateApplicationPK1.setEmail("sanjanapatni@gmail.com");
		
		mockCandidateApplication1.setId(mockCandidateApplicationPK1);
		mockCandidateApplication1.setResumeText("Looking for Java Developer opportunities.");
		mockCandidateApplication1.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		
		mockCandidateApplicationList.add(mockCandidateApplication1);

		CandidateApplication mockCandidateApplication2 = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK2 = new CandidateApplicationPK();
		mockCandidateApplicationPK2.setRelatedOffer("Angular Developer");
		mockCandidateApplicationPK2.setEmail("xyz@gmail.com");
		
		mockCandidateApplication2.setId(mockCandidateApplicationPK2);
		mockCandidateApplication2.setResumeText("Looking for Angular Developer opportunities.");
		mockCandidateApplication2.setApplicationStatus(RecruitmentProcessConstants.REJECTED);
		
		mockCandidateApplicationList.add(mockCandidateApplication2);
		return mockCandidateApplicationList;
	}

	private List<CandidateApplication> getMockListForJavaCandidateApplication() {
		List<CandidateApplication> mockJavaCandidateApplicationList = new ArrayList<>();
		CandidateApplication mockCandidateApplication1 = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK1 = new CandidateApplicationPK();
		mockCandidateApplicationPK1.setRelatedOffer("Java Developer");
		mockCandidateApplicationPK1.setEmail("sanjanapatni@gmail.com");
		
		mockCandidateApplication1.setId(mockCandidateApplicationPK1);
		mockCandidateApplication1.setResumeText("Looking for Java Developer opportunities.");
		mockCandidateApplication1.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		
		mockJavaCandidateApplicationList.add(mockCandidateApplication1);

		CandidateApplication mockCandidateApplication2 = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK2 = new CandidateApplicationPK();
		mockCandidateApplicationPK2.setRelatedOffer("Java Developer");
		mockCandidateApplicationPK2.setEmail("xyz@gmail.com");
		
		mockCandidateApplication2.setId(mockCandidateApplicationPK2);
		mockCandidateApplication2.setResumeText("Looking for Java Role.");
		mockCandidateApplication2.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		
		mockJavaCandidateApplicationList.add(mockCandidateApplication2);
		return mockJavaCandidateApplicationList;
	}
	
}
