package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.MediaCoverageDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class MediaCoverageModelImpl implements MediaCoverageModelInt {

	@Override
	public long add(MediaCoverageDTO dto) throws ApplicationException, DuplicateRecordException {

		MediaCoverageDTO existDto = findByMediaName(dto.getMediaName());

		if (existDto != null) {
			throw new DuplicateRecordException("Media Name already exists");
		}

		Session session = HibDataSource.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();

		} catch (HibernateException e) {

			if (tx != null) {
				tx.rollback();
			}
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in MediaCoverage Add " + e.getMessage());

		} finally {
			session.close();
		}

		return dto.getMediaCoverageId();
	}

	@Override
	public void delete(MediaCoverageDTO dto) throws ApplicationException {

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

			throw new ApplicationException("Exception in MediaCoverage Delete " + e.getMessage());

		} finally {
			session.close();
		}
	}

	@Override
	public void update(MediaCoverageDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;
		Transaction tx = null;

		MediaCoverageDTO existDto = findByMediaName(dto.getMediaName());

		if (existDto != null && existDto.getMediaCoverageId() != dto.getMediaCoverageId()) {
			throw new DuplicateRecordException("Media Name already exists");
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

			throw new ApplicationException("Exception in MediaCoverage Update " + e.getMessage());

		} finally {
			session.close();
		}
	}

	@Override
	public MediaCoverageDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		MediaCoverageDTO dto = null;

		try {
			session = HibDataSource.getSession();

			dto = (MediaCoverageDTO) session.get(MediaCoverageDTO.class, pk);

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting MediaCoverage by PK" + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public MediaCoverageDTO findByMediaName(String mediaName) throws ApplicationException {

		Session session = null;
		MediaCoverageDTO dto = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(MediaCoverageDTO.class);

			criteria.add(Restrictions.eq("mediaName", mediaName));

			List list = criteria.list();

			if (list.size() == 1) {
				dto = (MediaCoverageDTO) list.get(0);
			}

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting MediaCoverage by Name " + e.getMessage());

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

			Criteria criteria = session.createCriteria(MediaCoverageDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in MediaCoverage list" + e.getMessage());

		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(MediaCoverageDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<MediaCoverageDTO> list = null;

		try {
			session = HibDataSource.getSession();

			Criteria criteria = session.createCriteria(MediaCoverageDTO.class);

			if (dto != null) {

				if (dto.getMediaCoverageId() != null) {
					criteria.add(Restrictions.eq("mediaCoverageId", dto.getMediaCoverageId()));
				}

				if (dto.getMediaName() != null && dto.getMediaName().length() > 0) {
					criteria.add(Restrictions.like("mediaName", dto.getMediaName() + "%"));
				}

				if (dto.getReporter() != null && dto.getReporter().length() > 0) {
					criteria.add(Restrictions.like("reporter", dto.getReporter() + "%"));
				}

				if (dto.getCoverageDate() != null) {
					criteria.add(Restrictions.eq("coverageDate", dto.getCoverageDate()));
				}
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = (ArrayList<MediaCoverageDTO>) criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in MediaCoverage search" + e.getMessage());

		} finally {
			session.close();
		}

		return list;
	}

}