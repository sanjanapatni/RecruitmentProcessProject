package in.net.recruitmentprocess.module.candidateapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplication;
import in.net.recruitmentprocess.module.candidateapplication.model.CandidateApplicationPK;

@Repository
public interface CandidateApplicationRepository extends JpaRepository<CandidateApplication, CandidateApplicationPK>{

	CandidateApplication findByIdEmailAndIdRelatedOffer(String email, String relatedOffer);

	List<CandidateApplication> findByIdRelatedOfferIgnoreCase(String relatedOffer);

}
