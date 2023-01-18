package com.dilmen.dao;

import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.dilmen.entity.Category;

public class CategoryDao implements IDao<Category> {

	@Override
	public void create(Category entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Category data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Category to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public Category find(long id) {
		Category category = null;
		try (Session session = dataBaseConnectionHibernate()) {
			category = session.find(Category.class, id);
			if (category != null) {
				return category;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
//			throw new NullPointerException("no category found");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Category> findAll() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select c from Category as c";
			TypedQuery<Category> typedQuery = session.createQuery(query, Category.class);
			List<Category> categoryList = typedQuery.getResultList();
			return categoryList;
//			if (categoryList != null) {
//				return categoryList;
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
	public void update(long id, Category obj) {
		Session session = dataBaseConnectionHibernate();
		Category category = find(id);
		if (category != null) {
			category = obj;
			category.setId(id);
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			System.out.println("Category update success"); 
		} else {
			System.out.println("No category with id: "+id+" found"); 	
		}
		session.close();
	}

	@Override
	public void delete(long id) {
		try(Session session = dataBaseConnectionHibernate()){
			Category category = find(id);
			if (category != null) {
				session.getTransaction().begin();
				session.delete(category);
				session.getTransaction().commit();				
			}else {
				System.out.println("No category with id: "+id+" found");
			}
		} catch (Exception e) {
			System.out.println("error delete category");
		}
	}
}
