package in.co.rays.project_3.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.RoleDTO;
import in.co.rays.project_3.dto.SessionDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.RoleModelInt;
import in.co.rays.project_3.model.SessionModelInt;
import in.co.rays.project_3.model.UserModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

/**
 * login functionality controller. perform login operation
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */

@WebServlet(urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl validate method start");

		boolean pass = true;

		String op = request.getParameter("operation");

		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "password"));
			pass = false;
		}
		log.debug("LoginCtl validate method end");
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("LoginCtl populateDTO method start");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl populateDTO method end");
		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("LoginCtl doGet method start");

		String op = request.getParameter("operation");

		UserModelInt model = ModelFactory.getInstance().getUserModel();

		HttpSession session = request.getSession(false);

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_LOG_OUT.equals(op)) {
			session = request.getSession();

			if (session != null) {

				// Login ke time store kiya hua DB sessionId nikalo
				Long sessionId = (Long) session.getAttribute("sessionId");

				// DB logout (logoutTime update)
				if (sessionId != null) {
					SessionModelInt sessionModel = ModelFactory.getInstance().getSessionModel();

					try {
						sessionModel.logout(sessionId);
					} catch (ApplicationException e) {
						e.printStackTrace();
					}
				}

				session.invalidate();
				ServletUtility.setSuccessMessage("User Logged Out Successfully!", request);
				ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
				return;
			}
			if (id > 0) {
				UserDTO dto;
				try {
					dto = model.findByPK(id);
					ServletUtility.setDto(dto, request);
				} catch (ApplicationException e) {

					e.printStackTrace();
					ServletUtility.handleException(e, request, response);
					return;
				}

			}
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doGet method end");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("LoginCtl doPost method start");

		String op = request.getParameter("operation");

		HttpSession session = request.getSession(true);

		UserModelInt userModel = ModelFactory.getInstance().getUserModel();
		RoleModelInt model1 = ModelFactory.getInstance().getRoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				dto = userModel.authenticate(dto.getLogin(), dto.getPassword());
				if (dto != null) {
					session.setAttribute("user", dto);

					// SESSION TABLE ENTRY (AUTO)
					SessionDTO sDto = new SessionDTO();
					sDto.setUserName(dto.getLogin());
					sDto.setSessionToken(session.getId()); // HttpSession ID
					sDto.setSessionStatus("ACTIVE");
					sDto.setLoginTime(LocalDateTime.now());

					SessionModelInt sessionModel = ModelFactory.getInstance().getSessionModel();

					long sessionId = sessionModel.add(sDto);

					// future logout ke liye store
					session.setAttribute("sessionId", sessionId);

					long roleId = dto.getRoleId();
					RoleDTO rdto = model1.findByPK(roleId);
					if (rdto != null) {
						session.setAttribute("role", rdto.getName());
					}
					String uri = (String) request.getParameter("uri");
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} else {
						if (rdto.getId() == 1) {
							ServletUtility.redirect(uri, request, response);
						} else {
							ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						}

						return;
					}

				} else {
					dto = (UserDTO) populateDTO(request);
					ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password!", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl dopost method end");

	}

	@Override
	protected String getView() {

		return ORSView.LOGIN_VIEW;
	}
}