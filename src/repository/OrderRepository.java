package repository;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import domain.Order;
import util.HibernateUtil;

public class OrderRepository extends RepositoryBase<Order> {

	public OrderRepository(){
		super(Order.class);
	}
	
	@Override
	public Order GetById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Order entity = (Order)session.get(this.entity, id);
			Hibernate.initialize(entity.getDoctor());
			Hibernate.initialize(entity.getProcedure());
			Hibernate.initialize(entity.getHealthEnsurance());
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}
}
