package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.TransportDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class TransportModelHibImpl implements TransportModelInt {

	@Override
	public long add(TransportDTO dto) throws ApplicationException, DuplicateRecordException {

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
	public void delete(TransportDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in transport Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(TransportDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in transport update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public TransportDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		TransportDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (TransportDTO) session.get(TransportDTO.class, pk);
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public TransportDTO findBydriverName(String driverName) throws ApplicationException {

		Session session = null;
		TransportDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransportDTO.class);

			criteria.add(Restrictions.eq("driverName", driverName));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (TransportDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting  driverName " + e.getMessage());

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
			Criteria criteria = session.createCriteria(TransportDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Transport list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TransportDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<TransportDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransportDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getTransportId() != null && dto.getTransportId() > 0) {
					criteria.add(Restrictions.eq("transportId", dto.getTransportId()));
				}

				if (dto.getVehicleNumber() != null && dto.getVehicleNumber().length() > 0) {
					criteria.add(Restrictions.like("vehicleNumber", dto.getVehicleNumber() + "%"));
				}

				if (dto.getDriverName() != null && dto.getDriverName().length() > 0) {
					criteria.add(Restrictions.like("driverName", dto.getDriverName() + "%"));
				}

				if (dto.getVehicleType() != null && dto.getVehicleType().length() > 0) {
					criteria.add(Restrictions.like("vehicleType", dto.getVehicleType() + "%"));
				}

				if (dto.getTransportStatus() != null && dto.getTransportStatus().length() > 0) {
					criteria.add(Restrictions.like("transportStatus", dto.getTransportStatus() + "%"));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<TransportDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

}