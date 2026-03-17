package in.co.rays.project_3.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.PodcastDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PodcastModelInt;

public class PodcastModelTest {

	public static PodcastModelInt model = ModelFactory.getInstance().getPodcastModel();

	public static void main(String[] args) throws Exception {
		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByepisode_title();
		// listTest();
		searchTest();
	}

	private static void searchTest() throws ApplicationException {
		PodcastDTO dto = new PodcastDTO();

		// dto.setId(2L);
		// dto.setEpisode_title("test3");
		dto.setDuration("50 minutes");

		ArrayList<PodcastDTO> a = (ArrayList<PodcastDTO>) model.search(dto, 0, 0);
		System.out.println(dto.getId() + "\t" + dto.getEpisode_title() + "\t" + dto.getHost_name() + "\t"
				+ dto.getDuration() + "\t" + dto.getRelease_date());

	}

	private static void listTest() throws ApplicationException {

		PodcastDTO dto = new PodcastDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (PodcastDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getEpisode_title());
			System.out.println(dto.getHost_name());
			System.out.println(dto.getDuration());
			System.out.println(dto.getRelease_date());
		}
	}

	private static void findByepisode_title() throws ApplicationException {
		PodcastDTO dto = model.findByepisode_title("AI Revolution in 2026");
		System.out.println(dto.getId() + "\t" + dto.getEpisode_title() + "\t" + dto.getHost_name() + "\t"
				+ dto.getDuration() + "\t" + dto.getRelease_date());
	}

	private static void findByPKTest() throws ApplicationException {

		PodcastDTO dto = model.findByPK(3L);
		System.out.println(dto.getId() + "\t" + dto.getEpisode_title() + "\t" + dto.getHost_name() + "\t"
				+ dto.getDuration() + "\t" + dto.getRelease_date());
	}

	private static void deleteTest() throws ApplicationException {
		PodcastDTO dto = new PodcastDTO();
		dto.setId(7L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	private static void updateTest() throws Exception {

		PodcastDTO dto = new PodcastDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setEpisode_title("AI Revolution in 2026");
		dto.setHost_name("Pushpraj Singh Kachhaway");
		dto.setDuration("50 minutes");
		dto.setRelease_date(sdf.parse("11-03-2026"));

		model.update(dto);
		System.out.println("data update ");
	}

	private static void addTest() throws ParseException, ApplicationException, DuplicateRecordException {

		PodcastDTO dto = new PodcastDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setEpisode_title("test6");
		dto.setHost_name("Pushpraj ");
		dto.setDuration("60 minutes");
		dto.setRelease_date(sdf.parse("16-03-2026"));

		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

}