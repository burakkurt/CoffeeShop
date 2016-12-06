package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Order;


@Repository
public class OrderDAOImpl implements OrderDAO {
	
	EntityManager entityManager;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertOrder(Order order) {
		entityManager.getTransaction().begin();
		entityManager.persist(order);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public Order selectOrderByID(int id) {
		TypedQuery<Order> typedQuery = entityManager.createQuery("Select o from CustomerOrder o WHERE o.id = :id", Order.class).setParameter("id", id);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<Order> getOrderList() {
		TypedQuery<Order> typedQuery = entityManager.createQuery("Select o from CustomerOrder o", Order.class);
		return typedQuery.getResultList();

	}

	@Override
	public double getOrdersTotalPrice() {
		TypedQuery<Double> typedQuery = entityManager.createQuery("Select sum(o.price) from CustomerOrder o", Double.class);
		return typedQuery.getSingleResult();
	}

	public List<Object[]> getOrdersTotalAmountByCustomerName() {
		TypedQuery<Object[]> typedQuery = entityManager.createQuery("Select o.customer.customerName , sum(o.price) from CustomerOrder o group by o.customer.customerName",
				Object[].class);
		return typedQuery.getResultList();
	}

	

	

	
}
