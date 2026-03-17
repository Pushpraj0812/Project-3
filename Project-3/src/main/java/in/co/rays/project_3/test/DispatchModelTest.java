package in.co.rays.project_3.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import in.co.rays.project_3.dto.DispatchDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.DispatchModelInt;
import in.co.rays.project_3.model.ModelFactory;

public class DispatchModelTest {

	public static DispatchModelInt model = ModelFactory.getInstance().getDispatchModel();

	public static void main(String[] args) throws Exception {

		// testadd();
		// testupdate();
		// testdelete();
		// testfindbypk();
		// testfindByStatus();
		testsearch();
	}

	private static void testsearch() throws ApplicationException {

		DispatchDTO dto = new DispatchDTO();

		dto.setId(1L);

		ArrayList<DispatchDTO> a = (ArrayList<DispatchDTO>) model.search(dto, 0, 0);

		 for (DispatchDTO d : a) {

		        System.out.println(d.getId() + "\t"
		                + d.getDispatchId() + "\t"
		                + d.getDispatchDate() + "\t"
		                + d.getStatus() + "\t"
		                + d.getCourierName());
		    }
	}

	private static void testfindByStatus() throws ApplicationException {

		DispatchDTO dto = model.findByStatus("active");
		System.out.println(dto.getId() + "\t" + dto.getDispatchId() + "\t" + dto.getDispatchDate() + "\t"
				+ dto.getStatus() + "\t" + dto.getCourierName());
	}

	private static void testfindbypk() throws ApplicationException {

		DispatchDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getDispatchId() + "\t" + dto.getDispatchDate() + "\t"
				+ dto.getStatus() + "\t" + dto.getCourierName());
	}

	private static void testdelete() throws ApplicationException {

		DispatchDTO dto = new DispatchDTO();
		dto.setId(3L);
		model.delete(dto);
	}

	private static void testupdate() throws Exception {

		DispatchDTO dto = new DispatchDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setId(2L);
		dto.setDispatchId(1L);
		dto.setDispatchDate(sdf.parse("05-03-2026"));
		dto.setStatus("active");
		dto.setCourierName("test");

		model.update(dto);
	}

	public static void testadd() throws ParseException, ApplicationException, DuplicateRecordException {

		DispatchDTO dto = new DispatchDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setDispatchId(1L);
		dto.setDispatchDate(sdf.parse("05-03-2026"));
		dto.setStatus("active");
		dto.setCourierName("test");

		long pk = model.add(dto);
	}

}