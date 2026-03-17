package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.SessionDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.SessionModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/SessionCtl" })
public class SessionCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(SessionCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("SessionCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("sessionToken"))) {
			request.setAttribute("sessionToken", PropertyReader.getValue("error.require", "Session Token"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("sessionStatus"))) {
			request.setAttribute("sessionStatus", PropertyReader.getValue("error.require", "Session Status"));
			pass = false;
		}

		log.debug("SessionCtl validate end");
		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("SessionCtl populateDTO start");

		SessionDTO dto = new SessionDTO();

		dto.setSessionId(DataUtility.getLong(request.getParameter("sessionId")));
		dto.setSessionToken(DataUtility.getString(request.getParameter("sessionToken")));
		dto.setUserName(DataUtility.getString(request.getParameter("userName")));
		dto.setSessionStatus(DataUtility.getString(request.getParameter("sessionStatus")));

		populateBean(dto, request);

		log.debug("SessionCtl populateDTO end");
		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("SessionCtl doGet start");

		SessionModelInt model = ModelFactory.getInstance().getSessionModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				SessionDTO dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("SessionCtl doPost start");

		String op = DataUtility.getString(request.getParameter("operation"));
		SessionModelInt model = ModelFactory.getInstance().getSessionModel();

		long id = DataUtility.getLong(request.getParameter("sessionId"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			SessionDTO dto = (SessionDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Session updated successfully", request);
				} else {
					model.add(dto);
					ServletUtility.setSuccessMessage("Session saved successfully", request);
				}
				ServletUtility.setDto(dto, request);

			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			try {
				SessionDTO dto = new SessionDTO();
				dto.setSessionId(id);
				model.delete(dto);
				ServletUtility.redirect(ORSView.SESSION_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SESSION_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SESSION_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("SessionCtl doPost end");
	}

	@Override
	protected String getView() {
		return ORSView.SESSION_VIEW;
	}
}