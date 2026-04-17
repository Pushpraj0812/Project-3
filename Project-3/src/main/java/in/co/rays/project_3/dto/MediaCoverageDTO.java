package in.co.rays.project_3.dto;

import java.util.Date;

public class MediaCoverageDTO extends BaseDTO {

	private Long mediaCoverageId;
	private String mediaName;
	private Date coverageDate;
	private String reporter;

	public Long getMediaCoverageId() {
		return mediaCoverageId;
	}

	public void setMediaCoverageId(Long mediaCoverageId) {
		this.mediaCoverageId = mediaCoverageId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public Date getCoverageDate() {
		return coverageDate;
	}

	public void setCoverageDate(Date coverageDate) {
		this.coverageDate = coverageDate;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Override
	public String getKey() {
		return mediaCoverageId + "";
	}

	@Override
	public String getValue() {
		return mediaName;
	}

}