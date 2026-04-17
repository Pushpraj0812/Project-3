package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.MediaCoverageDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface MediaCoverageModelInt {

	public long add(MediaCoverageDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(MediaCoverageDTO dto) throws ApplicationException;

	public void update(MediaCoverageDTO dto) throws ApplicationException, DuplicateRecordException;

	public MediaCoverageDTO findByPK(long pk) throws ApplicationException;

	public MediaCoverageDTO findByMediaName(String mediaName) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(MediaCoverageDTO dto, int pageNo, int pageSize) throws ApplicationException;

}