package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PolicyDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PolicyModelHibImpl implements PolicymodelInt {

	@Override
	public long add(PolicyDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();

			session.save(dto);

			tx.commit();

		} catch (HibernateException e) {

			e.printStackTrace();

			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception rbEx) {
					System.out.println("Rollback failed: " + rbEx.getMessage());
				}
			}

			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Policy Add " + e.getMessage());

		} finally {

			session.close();
		}

		return dto.getPolicyId();
	}

	public void delete(PolicyDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in policy Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(PolicyDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in policy update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public PolicyDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		PolicyDTO dto = null;

		try {
			session = HibDataSource.getSession();
			dto = (PolicyDTO) session.get(PolicyDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in getting Policy by PK");

		} finally {
			session.close();
		}

		return dto;
	}

	public PolicyDTO findByPolicyName(String policyName) throws ApplicationException {

		Session session = null;
		PolicyDTO dto = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(PolicyDTO.class);
			criteria.add(Restrictions.eq("policyName", policyName));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (PolicyDTO) list.get(0);
			}

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in getting Policy by Name");

		} finally {
			session.close();
		}

		return dto;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(PolicyDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Policy List");

		} finally {
			session.close();
		}

		return list;
	}

	public List search(PolicyDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<PolicyDTO> list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(PolicyDTO.class);

			if (dto != null) {

				if (dto.getPolicyId() != null && dto.getPolicyId() > 0) {
					criteria.add(Restrictions.eq("policyId", dto.getPolicyId()));
				}

				if (dto.getPolicyName() != null && dto.getPolicyName().length() > 0) {
					criteria.add(Restrictions.like("policyName", dto.getPolicyName() + "%"));
				}

				if (dto.getPremiumAmount() != null && dto.getPremiumAmount() > 0) {
					criteria.add(Restrictions.eq("premiumAmount", dto.getPremiumAmount()));
				}

				if (dto.getStartDate() != null) {
					criteria.add(Restrictions.eq("startDate", dto.getStartDate()));
				}
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = (ArrayList<PolicyDTO>) criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Policy Search");

		} finally {
			session.close();
		}

		return list;
	}

}