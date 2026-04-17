package in.co.rays.project_3.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.MediaCoverageDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.MediaCoverageModelImpl;
import in.co.rays.project_3.model.MediaCoverageModelInt;

public class MediaCoverageModelTest {

	public static MediaCoverageModelInt model = new MediaCoverageModelImpl();

	public static void main(String[] args) throws Exception {

		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByMediaNameTest();
		// listTest();
		 searchTest();
	}

	private static void addTest() throws Exception {

		MediaCoverageDTO dto = new MediaCoverageDTO();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setMediaName("Doordarshan");
		dto.setReporter("Manoj Gupta");
		dto.setCoverageDate(sdf.parse("09-04-2026"));

		long pk = model.add(dto);

		System.out.println("Data inserted successfully with PK = " + pk);
	}

	private static void updateTest() throws Exception {

		MediaCoverageDTO dto = new MediaCoverageDTO();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setMediaCoverageId(9L);
		dto.setMediaName(" Tak");
		dto.setReporter(" Verma");
		dto.setCoverageDate(sdf.parse("02-04-2026"));

		model.update(dto);

		System.out.println("Data updated successfully");
	}

	private static void deleteTest() throws ApplicationException {

		MediaCoverageDTO dto = new MediaCoverageDTO();

		dto.setMediaCoverageId(9L);

		model.delete(dto);

		System.out.println("Data deleted successfully");
	}

	private static void findByPKTest() throws ApplicationException {

		MediaCoverageDTO dto = model.findByPK(1L);

		System.out.println(dto.getMediaCoverageId() + "\t" + dto.getMediaName() + "\t" + dto.getReporter() + "\t"
				+ dto.getCoverageDate());
	}

	private static void findByMediaNameTest() throws ApplicationException {

		MediaCoverageDTO dto = model.findByMediaName("Aaj Tak");

		System.out.println(dto.getMediaCoverageId() + "\t" + dto.getMediaName() + "\t" + dto.getReporter() + "\t"
				+ dto.getCoverageDate());
	}

	private static void listTest() throws ApplicationException {

		List list = model.list(1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			MediaCoverageDTO dto = (MediaCoverageDTO) it.next();

			System.out.println(dto.getMediaCoverageId());
			System.out.println(dto.getMediaName());
			System.out.println(dto.getReporter());
			System.out.println(dto.getCoverageDate());
		}
	}

	private static void searchTest() throws ApplicationException {

		MediaCoverageDTO dto = new MediaCoverageDTO();

		dto.setMediaName("Times");

		ArrayList<MediaCoverageDTO> list = (ArrayList<MediaCoverageDTO>) model.search(dto, 0, 0);

		for (MediaCoverageDTO m : list) {

			System.out.println(m.getMediaCoverageId() + "\t" + m.getMediaName() + "\t" + m.getReporter() + "\t"
					+ m.getCoverageDate());
		}
	}

}