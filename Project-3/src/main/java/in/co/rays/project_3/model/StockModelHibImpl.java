package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.StockDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class StockModelHibImpl implements StockModelInt {

	@Override
	public long add(StockDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Stock Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getStockId();
	}

	@Override
	public void delete(StockDTO dto) throws ApplicationException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Stock Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(StockDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in stock update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public StockDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		StockDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (StockDTO) session.get(StockDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting StockDTO by pk" + e.getMessage());
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public StockDTO findByStockName(String stockName) throws ApplicationException {

		Session session = null;
		StockDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StockDTO.class);

			criteria.add(Restrictions.eq("stockName", stockName));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (StockDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting StockDTO by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StockDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in  Stock list" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(StockDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<StockDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StockDTO.class);
			if (dto != null) {

				if (dto.getStockId() != null) {
					criteria.add(Restrictions.eq("stockId", dto.getStockId()));
				}

				if (dto.getStockName() != null && dto.getStockName().length() > 0) {
					criteria.add(Restrictions.like("stockName", dto.getStockName() + "%"));
				}

				if (dto.getPrice() != null && dto.getPrice() > 0) {
					criteria.add(Restrictions.eq("price", dto.getPrice()));
				}

				if (dto.getQuantity() != null && dto.getQuantity() > 0) {
					criteria.add(Restrictions.eq("quantity", dto.getQuantity()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<StockDTO>) criteria.list();
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in StockDTO search" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

}