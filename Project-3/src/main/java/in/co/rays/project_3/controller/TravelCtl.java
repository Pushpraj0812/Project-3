package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.TravelDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TravelModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "TravelCtl", urlPatterns = { "/ctl/TravelCtl" })
public class TravelCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TravelCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TravelCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("traveler_Name"))) {
			request.setAttribute("traveler_Name", PropertyReader.getValue("error.require", "Traveler Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Destination"))) {
			request.setAttribute("Destination", PropertyReader.getValue("error.require", "Destination"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("start_Date"))) {
			request.setAttribute("start_Date", PropertyReader.getValue("error.require", "Start Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("end_Date"))) {
			request.setAttribute("end_Date", PropertyReader.getValue("error.require", "End Date"));
			pass = false;
		}

		log.debug("TravelCtl validate end");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TravelCtl populateDTO start");

		TravelDTO dto = new TravelDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setTraveler_Name(DataUtility.getString(request.getParameter("traveler_Name")));

		dto.setDestination(DataUtility.getString(request.getParameter("Destination")));

		dto.setStart_Date(DataUtility.getDate(request.getParameter("start_Date")));

		dto.setEnd_Date(DataUtility.getDate(request.getParameter("end_Date")));

		populateBean(dto, request);

		log.debug("TravelCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("TravelCtl doGet start");

		long id = DataUtility.getLong(request.getParameter("id"));

		TravelModelInt model = ModelFactory.getInstance().getTravelModel();

		if (id > 0) {
			try {
				TravelDTO dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("TravelCtl doGet end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("TravelCtl doPost start");

		String op = DataUtility.getString(request.getParameter("operation"));

		TravelModelInt model = ModelFactory.getInstance().getTravelModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TravelDTO dto = (TravelDTO) populateDTO(request);

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
				ServletUtility.forward(getView(), request, response);
				return;

			} catch (DuplicateRecordException e) {

				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Record already exists", request);

			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TravelDTO dto = (TravelDTO) populateDTO(request);

			try {

				model.delete(dto);
				ServletUtility.redirect(ORSView.TRAVEL_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;

			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRAVEL_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRAVEL_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("TravelCtl doPost end");
	}

	@Override
	protected String getView() {
		return ORSView.TRAVEL_VIEW;
	}
}