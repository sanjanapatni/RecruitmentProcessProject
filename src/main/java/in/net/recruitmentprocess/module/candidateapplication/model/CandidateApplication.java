package in.net.recruitmentprocess.module.candidateapplication.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CANDIDATE_APPLICATION")
public class CandidateApplication {
	@EmbeddedId
	private CandidateApplicationPK id;
	@Column(name="RESUME_TEXT")
	private String resumeText;
	@Column(name="APPLICATION_STATUS")
	private String applicationStatus;
	
	public CandidateApplicationPK getId() {
		return id;
	}
	public void setId(CandidateApplicationPK id) {
		this.id = id;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public CandidateApplication() {
		//empty constructor
	}
	
}
