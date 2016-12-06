package service;

import java.util.List;

import domain.Category;
import domain.Flavour;

public interface FlavourService {

	public abstract List<Flavour> selectAllFlavours();
	public abstract Flavour selectFlavourById(int id);
	public abstract void addFlavour(Flavour flavour);
	public abstract void updateFlavour(Flavour flavour);
	public abstract void removeFlavour(int id);
	
}
