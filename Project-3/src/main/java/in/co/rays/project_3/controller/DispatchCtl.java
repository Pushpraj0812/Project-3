package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.DispatchDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.DispatchModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/DispatchCtl" })
public class DispatchCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(DispatchCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("dispatchDate"))) {
			request.setAttribute("dispatchDate", PropertyReader.getValue("error.require", "Dispatch Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courierName"))) {
			request.setAttribute("courierName", PropertyReader.getValue("error.require", "Courier Name"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		DispatchDTO dto = new DispatchDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setDispatchId(DataUtility.getLong(request.getParameter("dispatchId")));
		dto.setDispatchDate(DataUtility.getDate(request.getParameter("dispatchDate")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));
		dto.setCourierName(DataUtility.getString(request.getParameter("courierName")));

		populateBean(dto, request);

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("DispatchCtl doGet start");

		DispatchModelInt model = ModelFactory.getInstance().getDispatchModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		DispatchDTO dto = new DispatchDTO();

		if (id > 0) {
			try {

				dto = model.findByPK(id);

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.setDto(dto, request);
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		DispatchModelInt model = ModelFactory.getInstance().getDispatchModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			DispatchDTO dto = (DispatchDTO) populateDTO(request);

			try {

				if (id > 0) {

					model.update(dto);
					ServletUtility.setSuccessMessage("Data updated successfully", request);

				} else {

					model.add(dto);
					ServletUtility.setSuccessMessage("Data saved successfully", request);
				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);

			} catch (DuplicateRecordException e) {

				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DISPATCH_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DISPATCH_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.DISPATCH_VIEW;
	}
}