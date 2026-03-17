package in.co.rays.project_3.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.TransportDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TransportModelInt;

public class TransportModelTest {

	public static TransportModelInt model = ModelFactory.getInstance().getTransportModel();

	public static void main(String[] args) throws Exception {
		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByDriverName();
		// listtest();
		testsearch();

	}

	private static void testsearch() throws ApplicationException {

		TransportDTO dto = new TransportDTO();
		//dto.setId(1L);
		//dto.setTransportId(1L);
		//dto.setDriverName("driver");
		//dto.setVehicleNumber("vehicleNumber");
		//dto.setVehicleType("small");
		dto.setTransportStatus("transportStatus");

		ArrayList<TransportDTO> a = (ArrayList<TransportDTO>) model.search(dto, 0, 0);

		for (TransportDTO tdto1 : a) {
			System.out.println(tdto1.getId() + "\t" + tdto1.getTransportId() + "\t" + tdto1.getVehicleNumber() + "\t"
					+ tdto1.getDriverName() + "\t" + tdto1.getVehicleType() + "\t" + tdto1.getTransportStatus());
		}
	}

	private static void listtest() throws ApplicationException {

		TransportDTO dto = new TransportDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (TransportDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getTransportId());
			System.out.println(dto.getVehicleNumber());
			System.out.println(dto.getDriverName());
			System.out.println(dto.getVehicleType());
			System.out.println(dto.getTransportStatus());
		}
	}

	private static void findByDriverName() throws ApplicationException {

		TransportDTO dto = model.findBydriverName("driverName");
		System.out.println(dto.getId() + "\t" + dto.getTransportId() + "\t" + dto.getVehicleNumber() + "\t"
				+ dto.getDriverName() + "\t" + dto.getVehicleType() + "\t" + dto.getTransportStatus());
	}

	private static void findByPKTest() throws ApplicationException {

		TransportDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getTransportId() + "\t" + dto.getVehicleNumber() + "\t"
				+ dto.getDriverName() + "\t" + dto.getVehicleType() + "\t" + dto.getTransportStatus());
	}

	private static void deleteTest() throws ApplicationException {
		TransportDTO dto = new TransportDTO();

		dto.setId(3L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	private static void updateTest() throws ApplicationException, DuplicateRecordException {

		TransportDTO dto = new TransportDTO();

		dto.setId(1L);
		dto.setVehicleNumber("122");
		dto.setDriverName("driver");
		dto.setVehicleType("small");
		dto.setTransportStatus("on way");

		model.update(dto);
	}

	private static void addTest() throws ApplicationException, DuplicateRecordException {

		TransportDTO dto = new TransportDTO();

		dto.setTransportId(1L);
		dto.setVehicleNumber("vehicleNumber");
		dto.setDriverName("driverName");
		dto.setVehicleType("vehicleType");
		dto.setTransportStatus("transportStatus");

		long pk = model.add(dto);
	}

}