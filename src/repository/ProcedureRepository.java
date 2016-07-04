package repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import domain.Procedure;
import util.HibernateUtil;

public class ProcedureRepository extends RepositoryBase<Procedure> {

	public ProcedureRepository() {
		super(Procedure.class);
	}

	@Override
	public Procedure GetById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Procedure entity = (Procedure)session.get(this.entity, id);
			Hibernate.initialize(entity.getProcedureProducts());
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}
}
