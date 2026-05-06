package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.TransformationDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class TransformationModelHibImpl implements TransformationModelInt {

	@Override
	public long add(TransformationDTO dto) throws ApplicationException, DuplicateRecordException {

		TransformationDTO existDto = null;
		existDto = findByTransformCode(dto.getTransformCode());
		if (existDto != null) {
			throw new DuplicateRecordException("login id already exist");
		}
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
			throw new ApplicationException("Exception in User Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();

	}

	@Override
	public void delete(TransformationDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Transformation Delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(TransformationDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Transformation Update " + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public TransformationDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		TransformationDTO dto = null;

		try {
			session = HibDataSource.getSession();
			dto = (TransformationDTO) session.get(TransformationDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting Transformation by PK " + e.getMessage());
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public TransformationDTO findByTransformCode(String transformCode) throws ApplicationException {

		Session session = null;
		TransformationDTO dto = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransformationDTO.class);

			criteria.add(Restrictions.eq("transformCode", transformCode));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TransformationDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting transformCode " + e.getMessage());

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
			Criteria criteria = session.createCriteria(TransformationDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Transformation list " + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TransformationDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<TransformationDTO> list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransformationDTO.class);

			if (dto != null) {

				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getTransformId() > 0) {
					criteria.add(Restrictions.eq("transformId", dto.getTransformId()));
				}

				if (dto.getTransformCode() != null && dto.getTransformCode().length() > 0) {
					criteria.add(Restrictions.like("transformCode", dto.getTransformCode() + "%"));
				}

				if (dto.getRuleName() != null && dto.getRuleName().length() > 0) {
					criteria.add(Restrictions.like("ruleName", dto.getRuleName() + "%"));
				}

				if (dto.getLogic() != null && dto.getLogic().length() > 0) {
					criteria.add(Restrictions.like("logic", dto.getLogic() + "%"));
				}

				if (dto.getStatus() != null && dto.getStatus().length() > 0) {
					criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
				}
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = (ArrayList<TransformationDTO>) criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Transformation search " + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}
}