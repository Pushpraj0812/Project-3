package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.TransformationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.TransformationModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/TransformationCtl" })

public class TransformationCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TransformationCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TransformationCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("transformId"))) {
			request.setAttribute("transformId", PropertyReader.getValue("error.require", "Transform Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("transformCode"))) {
			request.setAttribute("transformCode", PropertyReader.getValue("error.require", "Transform Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("ruleName"))) {
			request.setAttribute("ruleName", PropertyReader.getValue("error.require", "Rule Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("logic"))) {
			request.setAttribute("logic", PropertyReader.getValue("error.require", "Logic"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}

		log.debug("TransformationCtl validate end");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("TransformationCtl populateDTO start");

		TransformationDTO dto = new TransformationDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setTransformId(DataUtility.getLong(request.getParameter("transformId")));
		dto.setTransformCode(DataUtility.getString(request.getParameter("transformCode")));
		dto.setRuleName(DataUtility.getString(request.getParameter("ruleName")));
		dto.setLogic(DataUtility.getString(request.getParameter("logic")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));

		populateBean(dto, request);

		log.debug("TransformationCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("TransformationCtl doGet Started");

		TransformationModelInt model = ModelFactory.getInstance().getTransformationModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		TransformationDTO dto = null;

		try {

			if (id > 0) {
				dto = model.findByPK(id);
			}

			if (dto == null) {
				dto = new TransformationDTO();
			}

			ServletUtility.setDto(dto, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {

			log.error(e);
			ServletUtility.handleException(e, request, response);
		}

		log.debug("TransformationCtl doGet End");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("TransformationCtl doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		TransformationModelInt model = ModelFactory.getInstance().getTransformationModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TransformationDTO dto = (TransformationDTO) populateDTO(request);

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
						ServletUtility.setErrorMessage("Transformation already exists", request);
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
				ServletUtility.setErrorMessage("Transformation already exists", request);
				ServletUtility.forward(getView(), request, response);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TransformationDTO dto = (TransformationDTO) populateDTO(request);

			try {

				model.delete(dto);
				ServletUtility.redirect(ORSView.TRANSFORMATION_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSFORMATION_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TRANSFORMATION_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("TransformationCtl doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.TRANSFORMATION_VIEW;
	}
}