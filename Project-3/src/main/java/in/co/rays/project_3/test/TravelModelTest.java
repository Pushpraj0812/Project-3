package in.co.rays.project_3.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.TravelDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TravelModelInt;

public class TravelModelTest {

	public static TravelModelInt model = ModelFactory.getInstance().getTravelModel();

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

		TravelDTO dto = new TravelDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		// dto.setId(5L);
		// dto.setTraveler_Name("Pushpraj");
		// dto.setDestination("Ujjain");
		// dto.setStart_Date(sdf.parse("20-03-2026"));
		 dto.setEnd_Date(sdf.parse("25-03-2026"));

		ArrayList<TravelDTO> a = (ArrayList<TravelDTO>) model.search(dto, 0, 0);

		for (TravelDTO tdto1 : a) {
			System.out.println(tdto1.getId() + "\t" + tdto1.getTraveler_Name() + "\t" + tdto1.getDestination() + "\t"
					+ tdto1.getStart_Date() + "\t" + tdto1.getEnd_Date());
			System.out.println();
		}
	}

	private static void listTest() throws Exception {

		TravelDTO dto = new TravelDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (TravelDTO) it.next();
			System.out.print(dto.getId());
			System.out.print(dto.getTraveler_Name());
			System.out.print(dto.getDestination());
			System.out.print(dto.getStart_Date());
			System.out.print(dto.getEnd_Date());
			System.out.println();
		}
	}

	private static void findByLoginTest() throws ApplicationException {

		TravelDTO dto = model.findBytraveler_Name("test5");
		System.out.println(dto.getId() + "\t" + dto.getTraveler_Name() + "\t" + dto.getDestination() + "\t"
				+ dto.getStart_Date() + "\t" + dto.getEnd_Date());
	}

	private static void findByPKTest() throws ApplicationException {

		TravelDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getTraveler_Name() + "\t" + dto.getDestination() + "\t"
				+ dto.getStart_Date() + "\t" + dto.getEnd_Date());
	}

	private static void deleteTest() throws ApplicationException {

		TravelDTO dto = new TravelDTO();
		dto.setId(8L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	private static void updateTest() throws Exception {

		TravelDTO dto = new TravelDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setId(4L);
		dto.setTraveler_Name("delete2");
		dto.setDestination("Ujjain");
		dto.setStart_Date(sdf.parse("20-03-2026"));
		dto.setEnd_Date(sdf.parse("25-03-2026"));

		model.update(dto);
		System.out.println("data update successfully");
	}

	private static void addTest() throws ParseException, ApplicationException, DuplicateRecordException {

		TravelDTO dto = new TravelDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setTraveler_Name("Test7");
		dto.setDestination("Bhopal");
		dto.setStart_Date(sdf.parse("14-03-2026"));
		dto.setEnd_Date(sdf.parse("15-03-2026"));

		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

}