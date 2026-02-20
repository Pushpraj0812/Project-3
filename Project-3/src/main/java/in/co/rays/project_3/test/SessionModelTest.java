package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.SessionDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.SessionModelHibImpl;
import in.co.rays.project_3.model.SessionModelInt;
import in.co.rays.project_3.model.UserModelHibImp;
import in.co.rays.project_3.model.UserModelInt;

public class SessionModelTest {

	public static SessionModelInt model = new SessionModelHibImpl();

	public static void main(String[] args) throws Exception {
		// addTest();
		// deletetest();
		// updatetest();
		// findBySessionId();
		// listtest();
		searchtest();
	}

	private static void searchtest() throws ApplicationException {
		
		SessionDTO dto = new SessionDTO();
		//dto.setSessionId(1L);
		// dto.setSessionToken("inactive");
		dto.setUserName("test");
		
		ArrayList<SessionDTO> a = (ArrayList<SessionDTO>) model.search(dto, 0, 0);
		for (SessionDTO udto1 : a) {
			System.out.println(udto1.getSessionId());
			System.out.println(udto1.getSessionToken());
			System.out.println(udto1.getUserName());
			System.out.println(udto1.getSessionStatus());
			System.out.println(udto1.getLoginTime());
			System.out.println(udto1.getLogoutTime());
		}
		
	}

	private static void listtest() throws ApplicationException {
		SessionDTO dto = new SessionDTO();
		List list = new ArrayList();
		list = model.list(0, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (SessionDTO) it.next();
			System.out.println(dto.getSessionId());
			System.out.println(dto.getSessionToken());
			System.out.println(dto.getUserName());
			System.out.println(dto.getSessionStatus());
			System.out.println(dto.getLoginTime());
			System.out.println(dto.getLogoutTime());
		}

	}

	private static void findBySessionId() throws ApplicationException {
		SessionDTO dto = model.findBysessionId(1L);
		System.out.println("findBysessionId successfully");
		System.out.println(dto.getSessionId() + "\t" + dto.getSessionToken() + "\t" + dto.getUserName() + "\t"
				+ dto.getSessionStatus() + "\t" + dto.getLoginTime() + "\t" + dto.getLogoutTime());
	}

	private static void updatetest() throws ApplicationException, DuplicateRecordException {

		SessionDTO dto = new SessionDTO();
		dto.setSessionId(2L);
		dto.setSessionToken("inactive");
		dto.setUserName("test2");
		dto.setSessionStatus("In progess");
		dto.setLoginTime(LocalDateTime.of(2026, 2, 20, 10, 30));
		dto.setLogoutTime(LocalDateTime.of(2026, 2, 20, 18, 45));
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

	private static void deletetest() throws ApplicationException {
		SessionDTO dto = new SessionDTO();
		dto.setSessionId(3L);
		;
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void addTest() throws Exception {

		SessionDTO dto = new SessionDTO();

		dto.setSessionId(1L);
		dto.setSessionToken("active");
		dto.setUserName("test");
		dto.setSessionStatus("In progess");
		dto.setLoginTime(LocalDateTime.of(2026, 2, 20, 10, 30));
		dto.setLogoutTime(LocalDateTime.of(2026, 2, 20, 18, 45));
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}
}