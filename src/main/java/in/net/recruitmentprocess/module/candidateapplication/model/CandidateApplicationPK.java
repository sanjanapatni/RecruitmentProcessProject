package in.net.recruitmentprocess.module.candidateapplication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CandidateApplicationPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="RELATED_OFFER")
	private String relatedOffer;
	@Column(name="EMAIL")
	private String email;
	
	public String getRelatedOffer() {
		return relatedOffer;
	}
	public void setRelatedOffer(String relatedOffer) {
		this.relatedOffer = relatedOffer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((relatedOffer == null) ? 0 : relatedOffer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateApplicationPK other = (CandidateApplicationPK) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (relatedOffer == null) {
			if (other.relatedOffer != null)
				return false;
		} else if (!relatedOffer.equals(other.relatedOffer))
			return false;
		return true;
	}

	public CandidateApplicationPK() {
	//empty constructor
	}
	
}
