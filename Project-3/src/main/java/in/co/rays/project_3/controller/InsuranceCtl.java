package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.InsuranceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.InsuranceModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "InsuranceCtl", urlPatterns = { "/ctl/InsuranceCtl" })
public class InsuranceCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(InsuranceCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("InsuranceCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("policyNumber"))) {
			request.setAttribute("policyNumber", PropertyReader.getValue("error.require", "Policy Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("policyHolderName"))) {
			request.setAttribute("policyHolderName", PropertyReader.getValue("error.require", "Policy Holder Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("expiryDate"))) {
			request.setAttribute("expiryDate", PropertyReader.getValue("error.require", "Expiry Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("expiryDate"))) {

			request.setAttribute("expiryDate", PropertyReader.getValue("error.date", "Expiry Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("insuranceStatus"))) {

			request.setAttribute("insuranceStatus", PropertyReader.getValue("error.require", "Insurance Status"));
			pass = false;
		}

		log.debug("InsuranceCtl validate end");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("InsuranceCtl populateDTO start");

		InsuranceDTO dto = new InsuranceDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setPolicyNumber(DataUtility.getString(request.getParameter("policyNumber")));

		dto.setPolicyHolderName(DataUtility.getString(request.getParameter("policyHolderName")));

		dto.setExpiryDate(DataUtility.getDate(request.getParameter("expiryDate")));

		dto.setInsuranceStatus(DataUtility.getString(request.getParameter("insuranceStatus")));

		populateBean(dto, request);

		log.debug("InsuranceCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("InsuranceCtl doGet start");

		InsuranceModelInt model = ModelFactory.getInstance().getInsuranceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		InsuranceDTO dto = null;

		try {

			if (id > 0) {

				dto = model.findByPK(id);

			} else {

				dto = new InsuranceDTO(); // ADD CASE
			}

			ServletUtility.setDto(dto, request);

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {

			log.error(e);

			ServletUtility.handleException(e, request, response);

		}

		log.debug("InsuranceCtl doGet end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("InsuranceCtl doPost start");

		String op = DataUtility.getString(request.getParameter("operation"));

		InsuranceModelInt model = ModelFactory.getInstance().getInsuranceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			InsuranceDTO dto = (InsuranceDTO) populateDTO(request);

			try {

				if (id > 0) {

					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully updated", request);

				} else {

					model.add(dto);

					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {

				log.error(e);

				ServletUtility.setErrorMessage(e.getMessage(), request);

			} catch (DuplicateRecordException e) {

				ServletUtility.setDto(dto, request);

				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			InsuranceDTO dto = (InsuranceDTO) populateDTO(request);

			try {

				model.delete(dto);

				ServletUtility.redirect(ORSView.INSURANCE_LIST_CTL, request, response);

				return;

			} catch (ApplicationException e) {

				log.error(e);

				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.INSURANCE_LIST_CTL, request, response);

			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.INSURANCE_CTL, request, response);

			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("InsuranceCtl doPost end");
	}

	@Override
	protected String getView() {

		return ORSView.INSURANCE_VIEW;
	}
}