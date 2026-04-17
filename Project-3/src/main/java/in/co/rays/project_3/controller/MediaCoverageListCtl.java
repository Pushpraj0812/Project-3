package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.MediaCoverageDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.MediaCoverageModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "MediaCoverageListCtl", urlPatterns = { "/ctl/MediaCoverageListCtl" })
public class MediaCoverageListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MediaCoverageListCtl.class);

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MediaCoverageListCtl populateDTO start");

		MediaCoverageDTO dto = new MediaCoverageDTO();

		dto.setMediaName(DataUtility.getString(request.getParameter("mediaName")));
		dto.setReporter(DataUtility.getString(request.getParameter("reporter")));
		dto.setCoverageDate(DataUtility.getDate(request.getParameter("coverageDate")));

		populateBean(dto, request);

		log.debug("MediaCoverageListCtl populateDTO end");

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MediaCoverageListCtl doGet Start");

		List list;
		List next;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		MediaCoverageDTO dto = (MediaCoverageDTO) populateDTO(request);

		MediaCoverageModelInt model = ModelFactory.getInstance().getMediaCoverageModel();

		try {

			list = model.search(dto, pageNo, pageSize);
			next = model.search(dto, pageNo + 1, pageSize);

			ServletUtility.setList(list, request);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);
			} else {
				request.setAttribute("nextListSize", next.size());
			}

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {

			log.error(e);
			ServletUtility.handleException(e, request, response);
		}

		log.debug("MediaCoverageListCtl doGet End");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MediaCoverageListCtl doPost Start");

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		MediaCoverageDTO dto = (MediaCoverageDTO) populateDTO(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		MediaCoverageModelInt model = ModelFactory.getInstance().getMediaCoverageModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.MEDIA_COVERAGE_CTL, request, response);
				return;

			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.MEDIA_COVERAGE_LIST_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {

				if (ids != null && ids.length > 0) {

					pageNo = 1;

					MediaCoverageDTO deleteDto = new MediaCoverageDTO();

					for (String id : ids) {

						deleteDto.setMediaCoverageId(DataUtility.getLong(id));
						model.delete(deleteDto);
					}

					ServletUtility.setSuccessMessage("Data Successfully Deleted!", request);

				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			list = model.search(dto, pageNo, pageSize);
			next = model.search(dto, pageNo + 1, pageSize);

			ServletUtility.setDto(dto, request);
			ServletUtility.setList(list, request);

			if (list == null || list.size() == 0) {

				if (!OP_DELETE.equalsIgnoreCase(op)) {
					ServletUtility.setErrorMessage("No record found", request);
				}
			}

			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);
			} else {
				request.setAttribute("nextListSize", next.size());
			}

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {

			log.error(e);
			ServletUtility.handleException(e, request, response);
		}

		log.debug("MediaCoverageListCtl doPost End");
	}

	@Override
	protected String getView() {
		return ORSView.MEDIA_COVERAGE_LIST_VIEW;
	}

}