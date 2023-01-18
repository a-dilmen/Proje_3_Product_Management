package com.dilmen.dao;

import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.dilmen.entity.Product;

public class ProductDao implements IDao<Product> {

	@Override
	public void create(Product entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Customer data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Customer to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public Product find(long id) {
		Product product = null;
		try (Session session = dataBaseConnectionHibernate()) {
			product = session.find(Product.class, id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> findAll() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select c from Product as c";
			TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
			List<Product> productList = typedQuery.getResultList();
			return productList;
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
	public void update(long id, Product obj) {
		Session session = dataBaseConnectionHibernate();
		Product product = find(id);
		if (product != null) {
			product = obj;
			product.setId(id);
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			System.out.println("Customer update success"); 
		} else {
			System.out.println("No product with id: "+id+" found"); 	
		}
		session.close();
	}

	@Override
	public void delete(long id) {
		try(Session session = dataBaseConnectionHibernate()){
			Product product = find(id);
			if (product != null) {
				session.getTransaction().begin();
				session.delete(product);
				session.getTransaction().commit();				
			}else {
				System.out.println("No product with id: "+id+" found");
			}
		} catch (Exception e) {
			System.out.println("error delete product");
		}
	}

	public List<Product> listLowOnStock() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select product from Product as product where product.stock <10";
			TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
			List<Product> productList = typedQuery.getResultList();
			return productList;
		} catch (Exception e7) {
			e7.printStackTrace();
			System.out.println("Exception ->");
		}

		return null;
	}
}
