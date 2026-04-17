package in.co.rays.project_3.dto;

import java.util.Date;

public class PolicyDTO extends BaseDTO {

	private Long policyId;
	private String policyName;
	private Long premiumAmount;
	private Date startDate;

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Long getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Long premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String getKey() {
		return policyId + "";
	}

	@Override
	public String getValue() {
		return policyName;
	}

}