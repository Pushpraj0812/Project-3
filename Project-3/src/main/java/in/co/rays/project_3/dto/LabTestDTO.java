package in.co.rays.project_3.dto;

import java.util.Date;

public class LabTestDTO extends BaseDTO {

	private Long labTestId;
	private String testName;
	private Long cost;
	private Date testDate;

	public Long getLabTestId() {
		return labTestId;
	}

	public void setLabTestId(Long labTestId) {
		this.labTestId = labTestId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Override
	public String getKey() {
		return labTestId + "";
	}

	@Override
	public String getValue() {
		return testName;
	}

}