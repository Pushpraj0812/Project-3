package in.co.rays.project_3.test;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.PolicyDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.PolicyModelHibImpl;

public class PolicyModelTest {

	public static PolicyModelHibImpl model = new PolicyModelHibImpl();

	public static void main(String[] args) throws Exception {

		// addTest();
		// updateTest();
		// deleteTest();
		// findByPKTest();
		// findByNameTest();
		// listTest();
		 searchTest();
	}

	// Add Test
	public static void addTest() throws Exception {

		PolicyDTO dto = new PolicyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setPolicyId(108L);
		dto.setPolicyName("Wealth Growth Plan");
		dto.setPremiumAmount(70000L);
		dto.setStartDate(sdf.parse("18-08-2025"));

		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");

		long pk = model.add(dto);
		System.out.println("Data Inserted, PK = " + pk);
	}

	// Update Test
	public static void updateTest() throws Exception {

		PolicyDTO dto = new PolicyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setPolicyId(8L);
		dto.setPolicyName(" test Plan");
		dto.setPremiumAmount(75000L);
		dto.setStartDate(sdf.parse("10-02-2025"));

		dto.setModifiedBy("admin");

		model.update(dto);
		System.out.println("Data Updated Successfully");
	}

	// Delete Test
	public static void deleteTest() throws ApplicationException {

		PolicyDTO dto = new PolicyDTO();
		dto.setPolicyId(8L);;

		model.delete(dto);
		System.out.println("Data Deleted Successfully");
	}

	// Find by PK
	public static void findByPKTest() throws ApplicationException {

		PolicyDTO dto = model.findByPK(1L);

		System.out.println(dto.getId());
		System.out.println(dto.getPolicyId());
		System.out.println(dto.getPolicyName());
		System.out.println(dto.getPremiumAmount());
		System.out.println(dto.getStartDate());
	}

	// Find by Name
	public static void findByNameTest() throws ApplicationException {

		PolicyDTO dto = model.findByPolicyName("Life Secure Plan");

		if (dto != null) {
			System.out.println(dto.getPolicyId() + "\t" + dto.getPolicyName());
		} else {
			System.out.println("Record not found");
		}
	}

	// List Test
	public static void listTest() throws ApplicationException {

		List list = model.list(1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			PolicyDTO dto = (PolicyDTO) it.next();

			System.out.println(dto.getId());
			System.out.println(dto.getPolicyId());
			System.out.println(dto.getPolicyName());
			System.out.println(dto.getPremiumAmount());
			System.out.println(dto.getStartDate());
		}
	}

	// Search Test
	public static void searchTest() throws ApplicationException {

		PolicyDTO dto = new PolicyDTO();
		dto.setPolicyName("Child");

		List list = model.search(dto, 0, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			PolicyDTO d = (PolicyDTO) it.next();

			System.out.println(d.getId());
			System.out.println(d.getPolicyName());
			System.out.println(d.getPremiumAmount());
		}
	}
}