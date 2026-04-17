package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.LabTestDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class LabTestModelHibImpl implements LabTestModelInt {

	@Override
	public long add(LabTestDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in LabTest Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getLabTestId();
	}

	@Override
	public void delete(LabTestDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in LabTest Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(LabTestDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in LabTest update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public LabTestDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		LabTestDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (LabTestDTO) session.get(LabTestDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting LabTest by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public LabTestDTO findByTestName(String testName) throws ApplicationException {

		Session session = null;
		LabTestDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(LabTestDTO.class);

			criteria.add(Restrictions.eq("testName", testName));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (LabTestDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting LabTest by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(LabTestDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  LabTest list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(LabTestDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<LabTestDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(LabTestDTO.class);
			if (dto != null) {
				if (dto.getLabTestId() != null) {
					criteria.add(Restrictions.eq("labTestId", dto.getLabTestId()));
				}

				if (dto.getTestName() != null && dto.getTestName().length() > 0) {
					criteria.add(Restrictions.like("testName", dto.getTestName() + "%"));
				}

				if (dto.getCost() != null && dto.getCost() > 0) {
					criteria.add(Restrictions.eq("cost", dto.getCost()));
				}

				if (dto.getTestDate() != null) {
					criteria.add(Restrictions.eq("testDate", dto.getTestDate()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<LabTestDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in LabTest search");
		} finally {
			session.close();
		}

		return list;
	}

}