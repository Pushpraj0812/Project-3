package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.TransportDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TransportModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/TransportCtl" })

public class TransportCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TransportCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("TransportCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("transportId"))) {
			request.setAttribute("transportId", PropertyReader.getValue("error.require", "Transport Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("vehicleNumber"))) {
			request.setAttribute("vehicleNumber", PropertyReader.getValue("error.require", "Vehicle Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("driverName"))) {
			request.setAttribute("driverName", PropertyReader.getValue("error.require", "Driver Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("vehicleType"))) {
			request.setAttribute("vehicleType", PropertyReader.getValue("error.require", "Vehicle Type"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("transportStatus"))) {
			request.setAttribute("transportStatus", PropertyReader.getValue("error.require", "Transport Status"));
			pass = false;
		}

		log.debug("TransportCtl validate end");

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TransportCtl populateDTO start");

		TransportDTO dto = new TransportDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setTransportId(DataUtility.getLong(request.getParameter("transportId")));
		dto.setVehicleNumber(DataUtility.getString(request.getParameter("vehicleNumber")));
		dto.setDriverName(DataUtility.getString(request.getParameter("driverName")));
		dto.setVehicleType(DataUtility.getString(request.getParameter("vehicleType")));
		dto.setTransportStatus(DataUtility.getString(request.getParameter("transportStatus")));

		populateBean(dto, request);

		log.debug("TransportCtl populateDTO end");

		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("TransportCtl doGet Started");

		TransportModelInt model = ModelFactory.getInstance().getTransportModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		TransportDTO dto = null;

		try {

			if (id > 0) {
				dto = model.findByPK(id);
			}

			if (dto == null) {
				dto = new TransportDTO();
			}

			ServletUtility.setDto(dto, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {

			log.error(e);
			ServletUtility.handleException(e, request, response);
		}

		log.debug("TransportCtl doGet End");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("TransportCtl doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		TransportModelInt model = ModelFactory.getInstance().getTransportModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TransportDTO dto = (TransportDTO) populateDTO(request);

			try {

				if (id > 0) {

					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Updated", request);

				} else {

					try {

						model.add(dto);

						ServletUtility.setSuccessMessage("Data is successfully saved", request);

					} catch (DuplicateRecordException e) {

						ServletUtility.setDto(dto, request);

						ServletUtility.setErrorMessage("Transport already exists", request);
					}

				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {

				log.error(e);

				ServletUtility.setErrorMessage(e.getMessage(), request);

				ServletUtility.forward(getView(), request, response);

				return;

			} catch (DuplicateRecordException e) {

				ServletUtility.setDto(dto, request);

				ServletUtility.setErrorMessage("Transport already exists", request);

				ServletUtility.forward(getView(), request, response);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TransportDTO dto = (TransportDTO) populateDTO(request);

			try {

				model.delete(dto);

				ServletUtility.redirect(ORSView.TRANSPORT_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {

				log.error(e);

				ServletUtility.setErrorMessage(e.getMessage(), request);

				ServletUtility.forward(getView(), request, response);

				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSPORT_LIST_CTL, request, response);

			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSPORT_CTL, request, response);

			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("TransportCtl doPost Ended");
	}

	@Override
	protected String getView() {

		return ORSView.TRANSPORT_VIEW;
	}
}