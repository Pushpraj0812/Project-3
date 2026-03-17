package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PodcastDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PodcastModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/PodcastCtl" })
public class PodcastCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(PodcastCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("PodcastCtl validate start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("episode_title"))) {
			request.setAttribute("episode_title", PropertyReader.getValue("error.require", "Episode Title"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("host_name"))) {
			request.setAttribute("host_name", PropertyReader.getValue("error.require", "Host Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("release_date"))) {
			request.setAttribute("release_date", PropertyReader.getValue("error.require", "Release Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("release_date"))) {
			request.setAttribute("release_date", PropertyReader.getValue("error.date", "Release Date"));
			pass = false;
		}

		log.debug("PodcastCtl validate end");

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("PodcastCtl populateDTO start");

		PodcastDTO dto = new PodcastDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setEpisode_title(DataUtility.getString(request.getParameter("episode_title")));

		dto.setHost_name(DataUtility.getString(request.getParameter("host_name")));

		dto.setDuration(DataUtility.getString(request.getParameter("duration")));

		dto.setRelease_date(DataUtility.getDate(request.getParameter("release_date")));

		populateBean(dto, request);

		log.debug("PodcastCtl populateDTO end");

		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("PodcastCtl doGet Started");

		PodcastModelInt model = ModelFactory.getInstance().getPodcastModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			PodcastDTO dto = null;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("PodcastCtl doPost start");

		String op = DataUtility.getString(request.getParameter("operation"));

		PodcastModelInt model = ModelFactory.getInstance().getPodcastModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			PodcastDTO dto = (PodcastDTO) populateDTO(request);

			try {

				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {

					model.add(dto);
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);

				}

				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			PodcastDTO dto = (PodcastDTO) populateDTO(request);

			try {

				model.delete(dto);

				ServletUtility.redirect(ORSView.PODCAST_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PODCAST_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PODCAST_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("PodcastCtl doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.PODCAST_VIEW;
	}
}