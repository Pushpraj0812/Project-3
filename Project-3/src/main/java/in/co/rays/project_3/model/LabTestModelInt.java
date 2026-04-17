package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.LabTestDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface LabTestModelInt {

	public long add(LabTestDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(LabTestDTO dto) throws ApplicationException;

	public void update(LabTestDTO dto) throws ApplicationException, DuplicateRecordException;

	public LabTestDTO findByPK(long pk) throws ApplicationException;

	public LabTestDTO findByTestName(String testName) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(LabTestDTO dto, int pageNo, int pageSize) throws ApplicationException;

}