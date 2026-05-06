package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TransformationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TransformationModelInt {

	public long add(TransformationDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(TransformationDTO dto) throws ApplicationException;

	public void update(TransformationDTO dto) throws ApplicationException, DuplicateRecordException;

	public TransformationDTO findByPK(long pk) throws ApplicationException;

	public TransformationDTO findByTransformCode(String transformCode) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(TransformationDTO dto, int pageNo, int pageSize) throws ApplicationException;

}