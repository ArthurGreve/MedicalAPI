package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class RepositoryBase<TEntity> implements IRepository<TEntity> {

	protected Class<TEntity> entity;
	
	public RepositoryBase(Class<TEntity> entityClass) {
		this.entity = entityClass;
	}
	
	public void Add(TEntity entity){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
 
		} catch (RuntimeException erro) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw erro;
		} finally {
			session.close();
		}
	}
	
	public void Update(TEntity entity){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();

		} catch (RuntimeException erro) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw erro;
		} finally {
			session.close();
		}
	}
	
	public void Delete(TEntity entity){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();

		} catch (RuntimeException erro) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw erro;
		} finally {
			session.close();
		}
	}
	
	public void Merge(TEntity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.merge(entity);
			transaction.commit();

		} catch (RuntimeException erro) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw erro;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public TEntity GetById(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			TEntity entity = (TEntity)session.get(this.entity, id);
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TEntity> GetAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			List<TEntity> list = session.createCriteria(this.entity).list();
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}	
	}
}