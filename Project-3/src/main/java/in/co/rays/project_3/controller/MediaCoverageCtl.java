package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.MediaCoverageDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.MediaCoverageModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/MediaCoverageCtl" })
public class MediaCoverageCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MediaCoverageCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MediaCoverageCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("mediaName"))) {
			request.setAttribute("mediaName", PropertyReader.getValue("error.require", "Media Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("reporter"))) {
			request.setAttribute("reporter", PropertyReader.getValue("error.require", "Reporter"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("coverageDate"))) {
			request.setAttribute("coverageDate", PropertyReader.getValue("error.require", "Coverage Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("coverageDate"))) {
			request.setAttribute("coverageDate", PropertyReader.getValue("error.date", "Coverage Date"));
			pass = false;
		}

		log.debug("MediaCoverageCtl validate end");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MediaCoverageCtl populateDTO start");

		MediaCoverageDTO dto = new MediaCoverageDTO();

		dto.setMediaCoverageId(DataUtility.getLong(request.getParameter("id")));

		dto.setMediaName(DataUtility.getString(request.getParameter("mediaName")));

		dto.setReporter(DataUtility.getString(request.getParameter("reporter")));

		dto.setCoverageDate(DataUtility.getDate(request.getParameter("coverageDate")));

		populateBean(dto, request);

		log.debug("MediaCoverageCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("MediaCoverageCtl doGet Started");

		MediaCoverageModelInt model = ModelFactory.getInstance().getMediaCoverageModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			try {
				MediaCoverageDTO dto = model.findByPK(id);
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

		log.debug("MediaCoverageCtl doPost start");

		String op = DataUtility.getString(request.getParameter("operation"));

		MediaCoverageModelInt model = ModelFactory.getInstance().getMediaCoverageModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			MediaCoverageDTO dto = (MediaCoverageDTO) populateDTO(request);

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

				ServletUtility.setErrorMessage("Media Name already exists", request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MediaCoverageDTO dto = (MediaCoverageDTO) populateDTO(request);

			try {

				model.delete(dto);
				ServletUtility.redirect(ORSView.MEDIA_COVERAGE_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MEDIA_COVERAGE_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MEDIA_COVERAGE_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MediaCoverageCtl doPost End");
	}

	@Override
	protected String getView() {
		return ORSView.MEDIA_COVERAGE_VIEW;
	}

}