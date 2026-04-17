package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.LabTestDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.LabTestModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/LabTestCtl" })
public class LabTestCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(LabTestCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LabTestCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("testName"))) {
			request.setAttribute("testName", PropertyReader.getValue("error.require", "Test Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cost"))) {
			request.setAttribute("cost", PropertyReader.getValue("error.require", "Cost"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("testDate"))) {
			request.setAttribute("testDate", PropertyReader.getValue("error.require", "Test Date"));
			pass = false;
		}

		log.debug("LabTestCtl validate end");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("LabTestCtl populateDTO start");

		LabTestDTO dto = new LabTestDTO();

		dto.setLabTestId(DataUtility.getLong(request.getParameter("id")));
		dto.setTestName(DataUtility.getString(request.getParameter("testName")));
		dto.setCost(DataUtility.getLong(request.getParameter("cost")));
		dto.setTestDate(DataUtility.getDate(request.getParameter("testDate")));

		populateBean(dto, request);

		log.debug("LabTestCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("LabTestCtl doGet Started");

		LabTestModelInt model = ModelFactory.getInstance().getLabTestModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				LabTestDTO dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
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

		log.debug("LabTestCtl doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		LabTestModelInt model = ModelFactory.getInstance().getLabTestModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			LabTestDTO dto = (LabTestDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Lab Test updated successfully", request);
				} else {
					model.add(dto);
					ServletUtility.setSuccessMessage("Lab Test added successfully", request);
				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Lab Test already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			LabTestDTO dto = (LabTestDTO) populateDTO(request);

			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.LABTEST_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.LABTEST_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.LABTEST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LabTestCtl doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LABTEST_VIEW;
	}
}