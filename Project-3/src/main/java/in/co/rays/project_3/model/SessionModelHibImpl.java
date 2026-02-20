package in.co.rays.project_3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.SessionDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class SessionModelHibImpl implements SessionModelInt {

	@Override
	public long add(SessionDTO dto) throws ApplicationException, DuplicateRecordException {

		SessionDTO existDto = null;
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
		return dto.getSessionId();
	}

	@Override
	public void delete(SessionDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in User Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(SessionDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		SessionDTO existDto = findBysessionId(dto.getSessionId());
		// Check if updated LoginId already exist
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public SessionDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		SessionDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (SessionDTO) session.get(SessionDTO.class, pk); // SELECT * FROM ST_USER WHERE ID = ?;

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
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
			Criteria criteria = session.createCriteria(SessionDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Users list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(SessionDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		ArrayList<SessionDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(SessionDTO.class);
			if (dto != null) {
				if (dto.getSessionId() != null) {
					criteria.add(Restrictions.eq("sessionId", dto.getSessionId()));
				}

				if (dto.getSessionToken() != null && dto.getSessionToken().length() > 0) {
					criteria.add(Restrictions.like("sessionToken", dto.getSessionToken() + "%"));
				}

				if (dto.getUserName() != null && dto.getUserName().length() > 0) {
					criteria.add(Restrictions.like("userName", dto.getUserName() + "%"));
				}

				if (dto.getSessionStatus() != null && dto.getSessionStatus().length() > 0) {
					criteria.add(Restrictions.eq("sessionStatus", dto.getSessionStatus()));
				}

				if (dto.getLoginTime() != null) {
					criteria.add(Restrictions.ge("loginTime", dto.getLoginTime()));
				}

				if (dto.getLogoutTime() != null) {
					criteria.add(Restrictions.le("logoutTime", dto.getLogoutTime()));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<SessionDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public SessionDTO findBysessionId(long pk) throws ApplicationException {
		Session session = null;
		SessionDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (SessionDTO) session.get(SessionDTO.class, pk); // SELECT * FROM ST_USER WHERE ID = ?;

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public void logout(long sessionId) throws ApplicationException {
		
		 Session session = HibDataSource.getSession();
		    Transaction tx = null;

		    try {
		        tx = session.beginTransaction();

		        SessionDTO dto = (SessionDTO) session.get(SessionDTO.class, sessionId);

		        if (dto != null) {
		            dto.setLogoutTime(LocalDateTime.now()); 
		            dto.setSessionStatus("INACTIVE");
		            session.update(dto);
		        }

		        tx.commit();

		    } catch (HibernateException e) {
		        if (tx != null) tx.rollback();
		        throw new ApplicationException("Error while logout session");

		    } finally {
		        session.close();
		    }
		
	}

}