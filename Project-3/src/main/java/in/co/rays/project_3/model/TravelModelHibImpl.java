package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.controller.GetMarksheetCtl;
import in.co.rays.project_3.dto.TravelDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class TravelModelHibImpl implements TravelModelInt {

	@Override
	public long add(TravelDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			int pk = 0;
			tx = session.beginTransaction();

			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Travel Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(TravelDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Travel Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(TravelDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Travel update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public TravelDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		TravelDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (TravelDTO) session.get(TravelDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting Travel by pk" + e.getMessage());
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public TravelDTO findBytraveler_Name(String traveler_Name) throws ApplicationException {

		Session session = null;
		TravelDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TravelDTO.class);

			criteria.add(Restrictions.eq("traveler_Name", traveler_Name));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TravelDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting Travel by traveler_Name " + e.getMessage());

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
			Criteria criteria = session.createCriteria(TravelDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in  Travel list" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TravelDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<TravelDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TravelDTO.class);
			if (dto != null) {
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}

				if (dto.getTraveler_Name() != null && dto.getTraveler_Name().length() > 0) {
					criteria.add(Restrictions.like("traveler_Name", dto.getTraveler_Name() + "%"));
				}

				if (dto.getDestination() != null && dto.getDestination().length() > 0) {
					criteria.add(Restrictions.like("Destination", dto.getDestination() + "%"));
				}

				if (dto.getStart_Date() != null && dto.getStart_Date().getDate() > 0) {
					criteria.add(Restrictions.eq("start_Date", dto.getStart_Date()));
				}

				if (dto.getEnd_Date() != null && dto.getEnd_Date().getDate() > 0) {
					criteria.add(Restrictions.eq("end_Date", dto.getEnd_Date()));
				}
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<TravelDTO>) criteria.list();
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in travel search" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}
}