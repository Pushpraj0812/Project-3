package in.co.rays.project_3.dto;

public class TransformationDTO extends BaseDTO {

	private Long transformId;

	private String transformCode;

	private String ruleName;

	private String logic;

	private String status;

	public Long getTransformId() {
		return transformId;
	}

	public void setTransformId(Long transformId) {
		this.transformId = transformId;
	}

	public String getTransformCode() {
		return transformCode;
	}

	public void setTransformCode(String transformCode) {
		this.transformCode = transformCode;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return transformId + "";
	}

	@Override
	public String getValue() {
		return transformCode;
	}
}