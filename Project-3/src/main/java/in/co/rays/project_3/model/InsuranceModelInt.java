package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.InsuranceDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface InsuranceModelInt {

	public long add(InsuranceDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(InsuranceDTO dto) throws ApplicationException;

	public void update(InsuranceDTO dto) throws ApplicationException, DuplicateRecordException;

	public InsuranceDTO findByPK(long pk) throws ApplicationException;

	public InsuranceDTO findBypolicyHolderName(String policyHolderName) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(InsuranceDTO dto, int pageNo, int pageSize) throws ApplicationException;

}