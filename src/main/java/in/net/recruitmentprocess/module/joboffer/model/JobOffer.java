package in.net.recruitmentprocess.module.joboffer.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "JOB_OFFER")
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JOB_ID")
	private BigDecimal jobId;
	
	@Column(name="JOB_TITLE", nullable= false, unique=true)
	private String jobTitle;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="NUMBER_OF_APPLICATION")
	private int numberOfApplication;
	
	@Column(name="JOB_LOCATION")
	private String jobLocation;
	
	@Override
	public String toString() {
		return "JobOffer [jobId=" + jobId + ", jobTitle=" + jobTitle + ", startDate=" + startDate
				+ ", numberOfApplication=" + numberOfApplication + ", jobLocation=" + jobLocation + ", noticePeriod="
				+ noticePeriod + "]";
	}
	@Column(name="NOTICE_PERIOD")
	private String noticePeriod;

	public BigDecimal getJobId() {
		return jobId;
	}
	public void setJobId(BigDecimal jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getNumberOfApplication() {
		return numberOfApplication;
	}
	public void setNumberOfApplication(int numberOfApplication) {
		this.numberOfApplication = numberOfApplication;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public String getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public JobOffer() {
	}
}
