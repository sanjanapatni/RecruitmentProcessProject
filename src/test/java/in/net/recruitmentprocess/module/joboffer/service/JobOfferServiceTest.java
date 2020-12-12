package in.net.recruitmentprocess.module.joboffer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import in.net.recruitmentprocess.module.joboffer.model.JobOffer;
import in.net.recruitmentprocess.module.joboffer.repository.JobOfferRepository;

@SpringBootTest
class JobOfferServiceTest {

	@Autowired
	private JobOfferService jobOfferService;
	@MockBean
	private JobOfferRepository jobOfferRepository;
    
	/** Test to retrieve all job offer **/
	@Test
	void testGetAllJobOffer() {
		List<JobOffer> mockJobOfferList = getMockDataListForJobOffer();
		when(jobOfferRepository.findAll()).thenReturn(mockJobOfferList);
		assertEquals(2, jobOfferService.getAllJobOffer().size());
	}

	/** Test to retrieve job offer of particular title **/
	@Test
	void testGetJobOfferByJobTitle() {
		String jobTitle = "Java Developer";
		JobOffer mockJobOffer = getMockDataForJobOffer(); 
		Mockito.when(jobOfferRepository.findByJobTitleIgnoreCase(jobTitle))
		.thenReturn(mockJobOffer);
		assertThat(jobOfferService.getJobOfferByJobTitle(jobTitle))
		.isEqualTo(mockJobOffer);
	}

	/** Test to save job offer **/
	@Test
	void testSaveJobOffer() {
		JobOffer mockJobOffer = getMockDataForJobOffer(); 
		Mockito.when(jobOfferRepository.save(mockJobOffer))
		.thenReturn(mockJobOffer);
		assertThat(jobOfferService.saveJobOffer(mockJobOffer))
		.isEqualTo(mockJobOffer);
	}

	/** Required data set **/ 
	
	private List<JobOffer> getMockDataListForJobOffer() {
		List<JobOffer> mockJobOfferList  = new ArrayList<>();
		JobOffer mockJobOffer1 = new JobOffer();
		mockJobOffer1.setJobId(new BigDecimal(1));
		mockJobOffer1.setJobTitle("Java Developer");
		mockJobOffer1.setStartDate(new Date());
		mockJobOffer1.setJobLocation("Noida");
		mockJobOffer1.setNumberOfApplication(10);
		mockJobOffer1.setNoticePeriod("90 Days");

		mockJobOfferList.add(mockJobOffer1);

		JobOffer mockJobOffer2 = new JobOffer();
		mockJobOffer2.setJobId(new BigDecimal(2));
		mockJobOffer2.setJobTitle("Java Developer");
		mockJobOffer2.setStartDate(new Date());
		mockJobOffer2.setJobLocation("Noida");
		mockJobOffer2.setNumberOfApplication(10);
		mockJobOffer2.setNoticePeriod("90 Days");

		mockJobOfferList.add(mockJobOffer2);
		return mockJobOfferList;
	}

	private JobOffer getMockDataForJobOffer() {
		JobOffer mockJobOffer = new JobOffer();
		mockJobOffer.setJobId(new BigDecimal(1));
		mockJobOffer.setJobTitle("Java Developer");
		mockJobOffer.setStartDate(new Date());
		mockJobOffer.setJobLocation("Noida");
		mockJobOffer.setNumberOfApplication(10);
		mockJobOffer.setNoticePeriod("90 Days");
		return mockJobOffer;
	}

}
