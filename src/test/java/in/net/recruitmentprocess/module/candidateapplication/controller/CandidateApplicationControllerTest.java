package in.net.recruitmentprocess.module.candidateapplication.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import in.net.recruitmentprocess.module.candidateapplication.service.CandidateApplicationService;

@SpringBootTest
@AutoConfigureMockMvc
class CandidateApplicationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CandidateApplicationService candidateApplicationService;
    
	/** Test to check save operation **/
	@Test
	void testSaveCandidateApplication() throws Exception{
		String URI = "/recruitment-process/save-candidate-application";

		CandidateApplication mockCandidateApplication = getMockDataForCandidateApplication();
		String inputJson = this.mapToJson(mockCandidateApplication);
		Mockito.when(candidateApplicationService.saveCandidateApplication(Mockito
				.any(CandidateApplication.class)))
		.thenReturn(mockCandidateApplication);
		
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
	
	/** Required data set **/ 
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
	
	/** Maps an object into JSON String. It uses a Jackson ObjectMapper**/
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
}
