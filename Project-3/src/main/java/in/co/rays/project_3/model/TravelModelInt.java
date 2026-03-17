package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TravelDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TravelModelInt {

	public long add(TravelDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(TravelDTO dto) throws ApplicationException;

	public void update(TravelDTO dto) throws ApplicationException, DuplicateRecordException;

	public TravelDTO findByPK(long pk) throws ApplicationException;

	public TravelDTO findBytraveler_Name(String traveler_Name) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(TravelDTO dto, int pageNo, int pageSize) throws ApplicationException;

}