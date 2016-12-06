package dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Order;
import domain.OrderItem;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO{

	EntityManager entityManager;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		entityManager.getTransaction().begin();
		entityManager.persist(orderItem);
		entityManager.getTransaction().commit();
	}

	

}
