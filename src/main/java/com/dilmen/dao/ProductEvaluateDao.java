package com.dilmen.dao;

import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.dilmen.entity.ProductEvaluate;

public class ProductEvaluateDao implements IDao<ProductEvaluate> {

	@Override
	public void create(ProductEvaluate entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Customer data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding ProductEvaluate to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public ProductEvaluate find(long id) {
		ProductEvaluate productEvaluate = null;
		try (Session session = dataBaseConnectionHibernate()) {
			productEvaluate = session.find(ProductEvaluate.class, id);
			if (productEvaluate != null) {
				return productEvaluate;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
//			throw new NullPointerException("no productEvaluate found");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ProductEvaluate> findAll() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select c from ProductEvaluate as c";
			TypedQuery<ProductEvaluate> typedQuery = session.createQuery(query, ProductEvaluate.class);
			List<ProductEvaluate> productEvaluateList = typedQuery.getResultList();
			return productEvaluateList;
//			if (productEvaluateList != null) {
//				return productEvaluateList;
//			} else {
//				throw new NullPointerException();
//			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("IllegalStateException");
		} catch (QueryTimeoutException e2) {
			e2.printStackTrace();
			System.out.println("QueryTimeoutException");
		} catch (TransactionRequiredException e3) {
			e3.printStackTrace();
			System.out.println("TransactionRequiredException");
		} catch (PessimisticLockException e4) {
			e4.printStackTrace();
			System.out.println("PessimisticLockException");
		} catch (LockTimeoutException e5) {
			e5.printStackTrace();
			System.out.println("LockTimeoutException");
		} catch (PersistenceException e6) {
			e6.printStackTrace();
			System.out.println("PersistenceException");
		} catch (Exception e7) {
			e7.printStackTrace();
			System.out.println("Exception -> none of the above");
		}

		return null;
	}

	@Override
	public void update(long id, ProductEvaluate obj) {
		Session session = dataBaseConnectionHibernate();
		ProductEvaluate productEvaluate = find(id);
		if (productEvaluate != null) {
			productEvaluate = obj;
			productEvaluate.setId(id);
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			System.out.println("ProductEvaluate update success"); 
		} else {
			System.out.println("No productEvaluate with id: "+id+" found"); 	
		}
		session.close();
	}

	@Override
	public void delete(long id) {
		try(Session session = dataBaseConnectionHibernate()){
			ProductEvaluate productEvaluate = find(id);
			if (productEvaluate != null) {
				session.getTransaction().begin();
				session.delete(productEvaluate);
				session.getTransaction().commit();				
			}else {
				System.out.println("No productEvaluate with id: "+id+" found");
			}
		} catch (Exception e) {
			System.out.println("error delete productEvaluate");
		}
	}
}
