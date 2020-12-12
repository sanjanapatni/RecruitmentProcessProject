package in.net.recruitmentprocess.common.util;

public final class RecruitmentProcessConstants {
    
	public static final String RESOURCE_NOT_FOUND_MESSAGE = "No Data Available for asked scenario";
	public static final String RESOURCE_NULL_MESSAGE = "Job title can not be null";
	public static final String RESOURCE_ALREADY_EXIST_MESSAGE ="Already Exist ";
	public static final String RESOURCE_NON_EXISTENCE_MESSAGE ="No job available for ";
	public static final String APPLIED ="APPLIED";
	public static final String INVITED ="INVITED";
	public static final String REJECTED ="REJECTED";
	public static final String HIRED ="HIRED";
	
	private RecruitmentProcessConstants() {
		throw new IllegalStateException("RecruitmentProcessConstants class");
	}
}
