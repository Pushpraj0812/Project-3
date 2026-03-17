package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PodcastDTO;
import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PodcastModelHibImpl implements PodcastModelInt {

	@Override
	public long add(PodcastDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Podcast Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(PodcastDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Podcast Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(PodcastDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Podcast update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public PodcastDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		PodcastDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PodcastDTO) session.get(PodcastDTO.class, pk); // SELECT * FROM ST_USER WHERE ID = ?;

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting Podcast by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public PodcastDTO findByepisode_title(String episode_title) throws ApplicationException {

		Session session = null;
		PodcastDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PodcastDTO.class); // SELECT * FROM ST_USER WHERE (Criteria where
																			// ki
																			// query bana ta hai )

			criteria.add(Restrictions.eq("episode_title", episode_title));

			List list = criteria.list();// Executes the query and returns a list of results from the database.

			if (list.size() == 1) {
				dto = (PodcastDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Podcast by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(PodcastDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Podcast list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(PodcastDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<PodcastDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PodcastDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}

				if (dto.getEpisode_title() != null && dto.getEpisode_title().length() > 0) {
					criteria.add(Restrictions.like("episode_title", dto.getEpisode_title() + "%"));
				}

				if (dto.getHost_name() != null && dto.getHost_name().length() > 0) {
					criteria.add(Restrictions.like("host_name", dto.getHost_name() + "%"));
				}

				if (dto.getDuration() != null && dto.getDuration().length() > 0) {
					criteria.add(Restrictions.like("duration", dto.getDuration() + "%"));
				}

				if (dto.getRelease_date() != null) {
					criteria.add(Restrictions.eq("release_date", dto.getRelease_date()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<PodcastDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in podcast search");
		} finally {
			session.close();
		}

		return list;
	}

}