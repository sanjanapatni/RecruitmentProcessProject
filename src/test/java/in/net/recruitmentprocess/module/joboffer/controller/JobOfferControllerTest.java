package in.net.recruitmentprocess.module.joboffer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.net.recruitmentprocess.common.util.RecruitmentProcessConstants;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;
import in.net.recruitmentprocess.module.joboffer.model.JobOffer;
import in.net.recruitmentprocess.module.joboffer.service.JobOfferService;

@SpringBootTest
@AutoConfigureMockMvc
class JobOfferControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private JobOfferService jobOfferService;
	
	/** Test to save job offer **/
	@Test
	void testSaveJobOffer() throws Exception{
		String URI = "/recruitment-process/save-job-offer";

		JobOffer mockJobOffer = getMockDataForJobOffer();
		String inputJson = this.mapToJson(mockJobOffer);
		Mockito.when(jobOfferService.saveJobOffer(Mockito.any(JobOffer.class)))
		.thenReturn(mockJobOffer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/** Test to retrieve all job offers **/ 
	@Test
	void testGetAllJobOffer() throws Exception {
		String URI = "/recruitment-process/get-all-job-offer";
		
		List<JobOffer> mockJobOfferList = getMockDataListForJobOffer();
		Mockito.when(jobOfferService.getAllJobOffer())
		.thenReturn(mockJobOfferList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String inputJson = this.mapToJson(mockJobOfferList);
		String outputJson = result.getResponse().getContentAsString();
		
		assertThat(outputJson).isEqualTo(inputJson);
	}

	/** Test to retrieve all job offers with given job title **/
	@Test
	void testGetJobOfferByJobTitle() throws Exception {
		String URI = "/recruitment-process/get-job-offer-by-job-title/Java Developer";
		
		JobOffer mockJobOffer = getMockDataForJobOffer();
		Mockito.when(jobOfferService.getJobOfferByJobTitle(Mockito.anyString()))
		.thenReturn(mockJobOffer);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String inputJson = this.mapToJson(mockJobOffer);
		String outputJson = result.getResponse().getContentAsString();
		
		assertThat(outputJson).isEqualTo(inputJson);
	}
	
	/** Test to retrieve all candidate application by USER **/
	@Test
	void testGetAllCandidateApplication() throws Exception {
		String URI = "/recruitment-process/get-all-candidate-application";
		
		List<CandidateApplication> mockCandidateApplicationList =
				getMockListForCandidateApplication();
		
		 Mockito.when(jobOfferService.getAllCandidateApplication())
		.thenReturn(mockCandidateApplicationList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String inputJson = this.mapToJson(mockCandidateApplicationList);
		String outputJson = result.getResponse().getContentAsString();
		
		assertThat(outputJson).isEqualTo(inputJson);
	}

	/** Test to retrieve all candidate application of particular job offer by USER **/
	@Test
	void testGetCandidateApplicationByOffer() throws Exception {
		String URI = "/recruitment-process/get-candidate-application-by-offer/Java Developer";
		
		List<CandidateApplication> mockCandidateApplicationList =
				getMockListForJavaCandidateApplication();
		
		 Mockito.when(jobOfferService.getCandidateApplicationByOffer(
				 Mockito.anyString()))
		.thenReturn(mockCandidateApplicationList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String inputJson = this.mapToJson(mockCandidateApplicationList);
		String outputJson = result.getResponse().getContentAsString();
		
		assertThat(outputJson).isEqualTo(inputJson);
	}
	
	/** Test to retrieve unique candidate application of particular offer and email
	 *  by USER **/
	@Test
	void testGetUniqueCandidateApplication() throws Exception {
		String URI = "/recruitment-process/get-unique-candidate-application";
		
		CandidateApplication mockCandidateApplication =
				getMockDataForCandidateApplication();
		String inputJson = this.mapToJson(mockCandidateApplication);

		Mockito.when(jobOfferService.getUniqueCandidateApplication(
				Mockito.any(CandidateApplicationPK.class)))
		.thenReturn(mockCandidateApplication);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
	}

	/** Maps an object into JSON String. It uses a Jackson ObjectMapper**/
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	/** Required data set **/ 
	private JobOffer getMockDataForJobOffer() {
		JobOffer mockJobOffer = new JobOffer();
		mockJobOffer.setJobTitle("Java Developer");
		mockJobOffer.setStartDate(null);
		mockJobOffer.setJobLocation("Noida");
		mockJobOffer.setNumberOfApplication(10);
		mockJobOffer.setNoticePeriod("90 Days");
		return mockJobOffer;
	}
	
	private List<JobOffer> getMockDataListForJobOffer() {
		List<JobOffer> mockJobOfferList = new ArrayList<>();
		
		JobOffer mockJobOffer1 = new JobOffer();
		mockJobOffer1.setJobId(new BigDecimal(1));
		mockJobOffer1.setJobTitle("Java Developer");
		mockJobOffer1.setStartDate(null);
		mockJobOffer1.setJobLocation("Noida");
		mockJobOffer1.setNumberOfApplication(10);
		mockJobOffer1.setNoticePeriod("90 Days");

		mockJobOfferList.add(mockJobOffer1);

		JobOffer mockJobOffer2 = new JobOffer();
		mockJobOffer1.setJobId(new BigDecimal(2));
		mockJobOffer2.setJobTitle("Java Developer");
		mockJobOffer2.setStartDate(null);
		mockJobOffer2.setJobLocation("Noida");
		mockJobOffer2.setNumberOfApplication(10);
		mockJobOffer2.setNoticePeriod("90 Days");

		mockJobOfferList.add(mockJobOffer2);
		return mockJobOfferList;
	}
	
	private List<CandidateApplication> getMockListForCandidateApplication() {
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
		mockCandidateApplication2.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		
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

	private CandidateApplication getMockDataForCandidateApplication() {
		CandidateApplication mockCandidateApplication = new CandidateApplication();
		CandidateApplicationPK mockCandidateApplicationPK = new CandidateApplicationPK();
		mockCandidateApplicationPK.setRelatedOffer("Java Developer");
		mockCandidateApplicationPK.setEmail("sanjanapatni@gmail.com");
		
		mockCandidateApplication.setId(mockCandidateApplicationPK);
		mockCandidateApplication.setResumeText("Looking for Java Developer opportunities.");
		mockCandidateApplication.setApplicationStatus(RecruitmentProcessConstants.APPLIED);
		return mockCandidateApplication;
	}
	
}
