package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TransportDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TransportModelInt {

	public long add(TransportDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(TransportDTO dto) throws ApplicationException;

	public void update(TransportDTO dto) throws ApplicationException, DuplicateRecordException;

	public TransportDTO findByPK(long pk) throws ApplicationException;

	public TransportDTO findBydriverName(String driverName) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(TransportDTO dto, int pageNo, int pageSize) throws ApplicationException;

}