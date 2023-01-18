package com.dilmen.dao;

import java.util.List;

import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.dilmen.entity.Admin;
import com.dilmen.entity.Customer;

public class AdminDao implements IDao<Admin> {

	@Override
	public void create(Admin entity) {
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
	public Admin find(long id) {
		Admin admin = null;
		try (Session session = dataBaseConnectionHibernate()) {
			admin = session.find(Admin.class, id);
			if (admin != null) {
				return admin;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
//			throw new NullPointerException("no admin found");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Admin> findAll() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select c from Customer as c";
			TypedQuery<Admin> typedQuery = session.createQuery(query, Admin.class);
			List<Admin> adminList = typedQuery.getResultList();
			return adminList;
//			if (adminList != null) {
//				return adminList;
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
	public void update(long id, Admin obj) {
		Session session = dataBaseConnectionHibernate();
		Admin admin = find(id);
		if (admin != null) {
			admin = obj;
			admin.setId(id);
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			System.out.println("Customer update success"); 
		} else {
			System.out.println("No admin with id: "+id+" found"); 	
		}
		session.close();
	}

	@Override
	public void delete(long id) {
		try(Session session = dataBaseConnectionHibernate()){
			Admin admin = find(id);
			if (admin != null) {
				session.getTransaction().begin();
				session.delete(admin);
				session.getTransaction().commit();				
			}else {
				System.out.println("No admin with id: "+id+" found");
			}
		} catch (Exception e) {
			System.out.println("error delete admin");
		}
	}
}
