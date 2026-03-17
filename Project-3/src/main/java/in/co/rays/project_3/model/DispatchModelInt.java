package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.DispatchDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface DispatchModelInt {

	public long add(DispatchDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(DispatchDTO dto) throws ApplicationException;

	public void update(DispatchDTO dto) throws ApplicationException, DuplicateRecordException;

	public DispatchDTO findByPK(long pk) throws ApplicationException;

	public DispatchDTO findByStatus(String login) throws ApplicationException;

	public List search(DispatchDTO dto, int pageNo, int pageSize) throws ApplicationException;
	
	public List list(int pageNo,int pageSize)throws ApplicationException;
}