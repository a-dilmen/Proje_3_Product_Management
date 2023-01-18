package com.dilmen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.dilmen.entity.Customer;
import com.dilmen.utils.HibernateUtils;

public class CustomerDao implements IDao<Customer> {

	@Override
	public void create(Customer entity) {
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
	public Customer find(long id) {
		Customer customer = null;
		try (Session session = dataBaseConnectionHibernate()) {
			customer = session.find(Customer.class, id);
			if (customer != null) {
				return customer;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
//			throw new NullPointerException("no customer found");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> findAll() {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select c from Customer as c";
			TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
			List<Customer> customerList = typedQuery.getResultList();
			return customerList;
//			if (customerList != null) {
//				return customerList;
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
	public void update(long id, Customer obj) {
		Session session = dataBaseConnectionHibernate();
		Customer customer = find(id);
		if (customer != null) {
			customer = obj;
			customer.setId(id);
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			System.out.println("Customer update success"); 
		} else {
			System.out.println("No customer with id: "+id+" found"); 	
		}
		session.close();
	}

	@Override
	public void delete(long id) {
		try(Session session = dataBaseConnectionHibernate()){
			Customer customer = find(id);
			if (customer != null) {
				session.getTransaction().begin();
				session.delete(customer);
				session.getTransaction().commit();				
			}else {
				System.out.println("No customer with id: "+id+" found");
			}
		} catch (Exception e) {
			System.out.println("error delete customer");
		}
	}

	public Customer findByIdentityNumber(String identity) {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select customer from Customer as customer where customer.identityNumber = '"+identity+"'";
			TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
			Customer customer = typedQuery.getSingleResult();
			return customer;
		} catch (Exception e7) {
//			e7.printStackTrace();
			System.out.println("Exception -> none of the above");
		}
		return null;
	}
	public Customer findByEmail(String email) {
		try (Session session = dataBaseConnectionHibernate()) {
			String query = "select customer from Customer as customer where customer.email = '"+email+"'";
			TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
			Customer customer = typedQuery.getSingleResult();
			return customer;
		} catch (Exception e7) {
//			e7.printStackTrace();
			System.out.println("Exception -> none of the above");
		}
		return null;
	}
}
