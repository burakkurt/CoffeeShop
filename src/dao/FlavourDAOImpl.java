package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Flavour;

@Repository
public class FlavourDAOImpl implements FlavourDAO{
	
	EntityManager entityManager;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public List<Flavour> selectAllFlavours() {
		TypedQuery<Flavour> typedQuery = entityManager.createQuery("Select f from Flavour f", Flavour.class);
		return typedQuery.getResultList();
	}

	@Override
	public Flavour selectFlavourById(int id) {
		return entityManager.find(Flavour.class, id);
	}

	@Override
	public void addFlavour(Flavour flavour) {
		entityManager.getTransaction().begin();
		entityManager.persist(flavour);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void updateFlavour(Flavour flavour) {
		Flavour foundFlavour = selectFlavourById(flavour.getId());
		
		if(foundFlavour != null){
			entityManager.getTransaction().begin();
			foundFlavour.setName(flavour.getName());
			foundFlavour.setPrice(flavour.getPrice());
			entityManager.getTransaction().commit();
		}
		
	}

	@Override
	public void removeFlavour(int id) {
		Flavour flavour = selectFlavourById(id);
		
		entityManager.getTransaction().begin();
		entityManager.remove(flavour);
		entityManager.getTransaction().commit();
	}

}
