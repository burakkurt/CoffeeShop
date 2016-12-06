package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FlavourDAO;
import domain.Flavour;

@Service
public class FlavourServiceImpl implements FlavourService{
	
	@Autowired
	FlavourDAO flavourDAOImpl;

	@Override
	public List<Flavour> selectAllFlavours() {
		return flavourDAOImpl.selectAllFlavours();
	}

	@Override
	public Flavour selectFlavourById(int id) {
		return flavourDAOImpl.selectFlavourById(id);
	}

	@Override
	public void addFlavour(Flavour flavour) {
		flavourDAOImpl.addFlavour(flavour);
	}

	@Override
	public void updateFlavour(Flavour flavour) {
		flavourDAOImpl.updateFlavour(flavour);
	}

	@Override
	public void removeFlavour(int id) {
		flavourDAOImpl.removeFlavour(id);
	}

	

}
