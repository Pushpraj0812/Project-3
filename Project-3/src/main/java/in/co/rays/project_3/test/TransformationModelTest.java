package in.co.rays.project_3.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.TransformationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TransformationModelInt;

public class TransformationModelTest {

	public static TransformationModelInt model = ModelFactory.getInstance().getTransformationModel();

	public static void main(String[] args) throws Exception {
		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByTransformCodeTest();
		// listtest();
		// testsearch();
	}

	private static void testsearch() throws ApplicationException {

		TransformationDTO dto = new TransformationDTO();
		// dto.setId(1L);
		// dto.setTransformId(1L);
		// dto.setTransformCode("T001");
		// dto.setRuleName("rule");
		// dto.setLogic("logic");
		dto.setStatus("ACTIVE");

		ArrayList<TransformationDTO> list = (ArrayList<TransformationDTO>) model.search(dto, 0, 0);

		for (TransformationDTO d : list) {
			System.out.println(d.getId() + "\t" + d.getTransformId() + "\t" + d.getTransformCode() + "\t"
					+ d.getRuleName() + "\t" + d.getLogic() + "\t" + d.getStatus());
		}
	}

	private static void listtest() throws ApplicationException {

		TransformationDTO dto = new TransformationDTO();
		List list = new ArrayList();
		list = model.list(1, 10);

		if (list.size() < 0) {
			System.out.println("list fail");
		}

		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (TransformationDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getTransformId());
			System.out.println(dto.getTransformCode());
			System.out.println(dto.getRuleName());
			System.out.println(dto.getLogic());
			System.out.println(dto.getStatus());
		}
	}

	private static void findByTransformCodeTest() throws ApplicationException {

		TransformationDTO dto = model.findByTransformCode("T001");

		System.out.println(dto.getId() + "\t" + dto.getTransformId() + "\t" + dto.getTransformCode() + "\t"
				+ dto.getRuleName() + "\t" + dto.getLogic() + "\t" + dto.getStatus());
	}

	private static void findByPKTest() throws ApplicationException {

		TransformationDTO dto = model.findByPK(1L);

		System.out.println(dto.getId() + "\t" + dto.getTransformId() + "\t" + dto.getTransformCode() + "\t"
				+ dto.getRuleName() + "\t" + dto.getLogic() + "\t" + dto.getStatus());
	}

	private static void deleteTest() throws ApplicationException {

		TransformationDTO dto = new TransformationDTO();
		dto.setId(8L);

		model.delete(dto);
		System.out.println("delete data successfully");
	}

	private static void updateTest() throws ApplicationException, DuplicateRecordException {

		TransformationDTO dto = new TransformationDTO();

		dto.setId(8L);
		dto.setTransformId(108L);
		dto.setTransformCode("T008");
		dto.setRuleName("Numeric Check");
		dto.setLogic("Allow only numbers");
		dto.setStatus("ACTIVE");

		model.update(dto);
	}

	private static void addTest() throws ApplicationException, DuplicateRecordException {

		TransformationDTO dto = new TransformationDTO();

		dto.setTransformId(108L);
		dto.setTransformCode("T008");
		dto.setRuleName("Numeric Check");
		dto.setLogic("Allow only numbers");
		dto.setStatus("INACTIVE");

		long pk = model.add(dto);

		System.out.println("Data added with PK: " + pk);
	}
}