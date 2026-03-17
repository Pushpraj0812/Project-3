package in.co.rays.project_3.dto;

import java.util.Date;

public class PodcastDTO extends BaseDTO {

	private String episode_title;

	private String host_name;

	private String duration;

	private Date release_date;

	public String getEpisode_title() {
		return episode_title;
	}

	public void setEpisode_title(String episode_title) {
		this.episode_title = episode_title;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return episode_title;
	}

}