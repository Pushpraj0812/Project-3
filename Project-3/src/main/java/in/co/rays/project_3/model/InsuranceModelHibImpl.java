package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.InsuranceDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class InsuranceModelHibImpl implements InsuranceModelInt {

	@Override
	public long add(InsuranceDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in InsuranceDTO Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(InsuranceDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in InsuranceDTO Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(InsuranceDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in InsuranceDTO update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public InsuranceDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		InsuranceDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (InsuranceDTO) session.get(InsuranceDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting InsuranceDTO by pk");
		} finally {
			session.close();
		}

		return dto;

	}

	@Override
	public InsuranceDTO findBypolicyHolderName(String policyHolderName) throws ApplicationException {

		Session session = null;
		InsuranceDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InsuranceDTO.class); // SELECT * FROM ST_USER WHERE (Criteria
																			// where ki
			// query bana ta hai )

			criteria.add(Restrictions.eq("policyHolderName", policyHolderName)); // SELECT * FROM ST_USER WHERE LOGIN =
																					// ?; ()

			List list = criteria.list();// Executes the query and returns a list of results from the database.

			if (list.size() == 1) {
				dto = (InsuranceDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting InsuranceDTO by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(InsuranceDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in  InsuranceDTO list" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(InsuranceDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<InsuranceDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InsuranceDTO.class);
			if (dto != null) {
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}

				if (dto.getPolicyNumber() != null && dto.getPolicyNumber().length() > 0) {
					criteria.add(Restrictions.like("policyNumber", dto.getPolicyNumber() + "%"));
				}

				if (dto.getPolicyHolderName() != null && dto.getPolicyHolderName().length() > 0) {
					criteria.add(Restrictions.like("policyHolderName", dto.getPolicyHolderName() + "%"));
				}

				if (dto.getExpiryDate() != null) {
					criteria.add(Restrictions.eq("expiryDate", dto.getExpiryDate()));
				}

				if (dto.getInsuranceStatus() != null && dto.getInsuranceStatus().length() > 0) {
					criteria.add(Restrictions.like("insuranceStatus", dto.getInsuranceStatus() + "%"));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<InsuranceDTO>) criteria.list();
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Insurance search" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

}