package in.co.rays.project_3.dto;

import java.util.Date;

public class TravelDTO extends BaseDTO {

	private String traveler_Name;

	private String Destination;

	private Date start_Date;

	private Date end_Date;

	public String getTraveler_Name() {
		return traveler_Name;
	}

	public void setTraveler_Name(String traveler_Name) {
		this.traveler_Name = traveler_Name;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return traveler_Name;
	}
}