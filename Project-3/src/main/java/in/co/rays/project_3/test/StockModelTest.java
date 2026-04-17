package in.co.rays.project_3.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.StockDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.StockModelHibImpl;
import in.co.rays.project_3.model.StockModelInt;

public class StockModelTest {

	public static StockModelInt model = new StockModelHibImpl();

	public static void main(String[] args) throws Exception {
		 addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// listTest();
		// searchTest();
	}

	public static void addTest() throws Exception {

		StockDTO dto = new StockDTO();

		dto.setStockName("Speaker");
		dto.setPrice(3000L);
		dto.setQuantity(12);

		long pk = model.add(dto);
		System.out.println(pk + " data successfully inserted");
	}

	public static void updateTest() throws Exception {

		StockDTO dto = new StockDTO();

		dto.setStockId(5L);
		dto.setStockName("Earbuds");
		dto.setPrice(6000L);
		dto.setQuantity(8);

		model.update(dto);
		System.out.println("Data updated successfully");
	}

	public static void deleteTest() throws ApplicationException {

		StockDTO dto = new StockDTO();
		dto.setStockId(6L);

		model.delete(dto);
		System.out.println("Data deleted successfully");
	}

	public static void findByPKTest() throws ApplicationException {

		StockDTO dto = model.findByPK(1L);

		System.out.println(
				dto.getStockId() + "\t" + dto.getStockName() + "\t" + dto.getPrice() + "\t" + dto.getQuantity());
	}

	public static void listTest() throws ApplicationException {

		List list = new ArrayList();
		list = model.list(1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			StockDTO dto = (StockDTO) it.next();

			System.out.println(dto.getStockId());
			System.out.println(dto.getStockName());
			System.out.println(dto.getPrice());
			System.out.println(dto.getQuantity());
		}
	}

	public static void searchTest() throws ApplicationException {

		StockDTO dto = new StockDTO();

		// dto.setStockId(1L);
		// dto.setStockName("Ear");
		// dto.setPrice(1000.0);
		 dto.setQuantity(10);

		ArrayList<StockDTO> list = (ArrayList<StockDTO>) model.search(dto, 0, 0);

		for (StockDTO sdto : list) {
			System.out.println(sdto.getStockId() + "\t" + sdto.getStockName() + "\t" + sdto.getPrice() + "\t"
					+ sdto.getQuantity());
		}
	}

}