package in.co.rays.project_3.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.LabTestDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.LabTestModelHibImpl;
import in.co.rays.project_3.model.LabTestModelInt;

public class LabTestModelTest {

	public static LabTestModelInt model = new LabTestModelHibImpl();

	public static void main(String[] args) throws Exception {

		 addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// listTest();
		// searchTest();

	}

	public static void addTest() throws Exception {

		LabTestDTO dto = new LabTestDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setTestName("Cholesterol Test");
		dto.setCost(400l);
		dto.setTestDate(sdf.parse("07-04-2026"));

		long pk = model.add(dto);
		System.out.println(pk + " data successfully inserted");
	}

	public static void updateTest() throws Exception {

		LabTestDTO dto = new LabTestDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setLabTestId(2L);
		dto.setTestName("Cholesterol Test");
		dto.setCost(400l);
		dto.setTestDate(sdf.parse("07-04-2026"));

		model.update(dto);
		System.out.println("data updated successfully");
	}

	public static void deleteTest() throws ApplicationException {

		LabTestDTO dto = new LabTestDTO();
		dto.setLabTestId(8L);

		model.delete(dto);
		System.out.println("data deleted successfully");
	}

	public static void findByPKTest() throws ApplicationException {

		LabTestDTO dto = model.findByPK(1L);

		System.out.println(
				dto.getLabTestId() + "\t" + dto.getTestName() + "\t" + dto.getCost() + "\t" + dto.getTestDate());
	}

	public static void listTest() throws ApplicationException {

		List list = new ArrayList();
		list = model.list(1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			LabTestDTO dto = (LabTestDTO) it.next();

			System.out.println(dto.getLabTestId());
			System.out.println(dto.getTestName());
			System.out.println(dto.getCost());
			System.out.println(dto.getTestDate());
		}
	}

	public static void searchTest() throws ApplicationException {

		LabTestDTO dto = new LabTestDTO();

		// dto.setTestName("Blood");
		 dto.setCost(500l);

		ArrayList<LabTestDTO> list = (ArrayList<LabTestDTO>) model.search(dto, 0, 0);

		for (LabTestDTO d : list) {
			System.out.println(d.getLabTestId() + "\t" + d.getTestName() + "\t" + d.getCost() + "\t" + d.getTestDate());
		}
	}
}