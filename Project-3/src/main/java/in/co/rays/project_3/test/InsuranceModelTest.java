package in.co.rays.project_3.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.InsuranceDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.InsuranceModelInt;
import in.co.rays.project_3.model.ModelFactory;

public class InsuranceModelTest {

	public static InsuranceModelInt model = ModelFactory.getInstance().getInsuranceModel();

	public static void main(String[] args) throws Exception {
		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByLoginTest();
		// listTest();
		searchTest();
	}

	private static void searchTest() throws Exception {

		InsuranceDTO dto = new InsuranceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		// dto.setId(2L);
		// dto.setPolicyHolderName("Pushpraj Singh Kachhaway");
		// dto.setExpiryDate(sdf.parse("08-12-2028"));
		dto.setInsuranceStatus("Active");

		ArrayList<InsuranceDTO> a = (ArrayList<InsuranceDTO>) model.search(dto, 0, 0);

		for (InsuranceDTO dto1 : a) {
			System.out.println(dto1.getId() + "\t" + dto1.getId() + "\t" + dto1.getPolicyNumber() + "\t"
					+ dto1.getPolicyHolderName() + "\t" + dto1.getExpiryDate() + "\t" + dto1.getInsuranceStatus());
		}
	}

	private static void listTest() throws Exception {

		InsuranceDTO dto = new InsuranceDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (InsuranceDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getPolicyNumber());
			System.out.println(dto.getPolicyHolderName());
			System.out.println(dto.getExpiryDate());
			System.out.println(dto.getInsuranceStatus());
		}
	}

	private static void findByLoginTest() throws Exception {
		InsuranceDTO dto = model.findBypolicyHolderName("Pushpraj Singh Kachhaway");
		System.out.println(dto.getId() + "\t" + dto.getPolicyNumber() + "\t" + dto.getPolicyHolderName() + "\t"
				+ dto.getExpiryDate() + "\t" + dto.getInsuranceStatus());

	}

	private static void findByPKTest() throws Exception {

		InsuranceDTO dto = model.findByPK(3L);
		System.out.println(dto.getId() + "\t" + dto.getPolicyNumber() + "\t" + dto.getPolicyHolderName() + "\t"
				+ dto.getExpiryDate() + "\t" + dto.getInsuranceStatus());
	}

	private static void deleteTest() throws Exception {

		InsuranceDTO dto = new InsuranceDTO();
		dto.setId(4L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	private static void updateTest() throws Exception {

		InsuranceDTO dto = new InsuranceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setId(3L);
		dto.setInsuranceId(2L);
		dto.setPolicyNumber("POL123458");
		dto.setPolicyHolderName("test ");
		dto.setExpiryDate(sdf.parse("08-12-2028"));
		dto.setInsuranceStatus("Active");
		model.update(dto);
		System.out.println("data update successfully insert");
	}

	private static void addTest() throws ParseException, ApplicationException, DuplicateRecordException {

		InsuranceDTO dto = new InsuranceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setInsuranceId(1L);
		dto.setPolicyNumber("POL123456");
		dto.setPolicyHolderName("Pushpraj Singh Kachhaway");
		dto.setExpiryDate(sdf.parse("08-12-2028"));
		dto.setInsuranceStatus("Active");
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

}