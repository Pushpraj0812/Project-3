package in.co.rays.project_3.dto;

public class TransportDTO extends BaseDTO {

	private Long transportId;

	private String vehicleNumber;

	private String driverName;

	private String vehicleType;

	private String transportStatus;

	public Long getTransportId() {
		return transportId;
	}

	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(String transportStatus) {
		this.transportStatus = transportStatus;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return transportId + "";
	}

	@Override
	public String getValue() {
		return driverName;
	}

}