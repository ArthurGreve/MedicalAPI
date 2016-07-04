package repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import domain.Product;
import util.HibernateUtil;

public class ProductRepository extends RepositoryBase<Product>{

	public ProductRepository(){
		super(Product.class);
	}
	
	public Product GetById(String code){
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Criteria sql = session.createCriteria(this.entity);
			// where
			sql.add(Restrictions.idEq(code));

			Product entity = (Product) sql.uniqueResult();
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

	}
}
