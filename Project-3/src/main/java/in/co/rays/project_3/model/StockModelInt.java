package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.StockDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface StockModelInt {

	public long add(StockDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(StockDTO dto) throws ApplicationException;

	public void update(StockDTO dto) throws ApplicationException, DuplicateRecordException;

	public StockDTO findByPK(long pk) throws ApplicationException;

	public StockDTO findByStockName(String stockName) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(StockDTO dto, int pageNo, int pageSize) throws ApplicationException;

}