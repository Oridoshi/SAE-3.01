package metier.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import metier.repo.ProgrammeItemDB;

public class Programme{

	private Map<String, ProgrammeItem> programme;

	public Programme(){
		this.programme = new HashMap<>();
	}

	public ProgrammeItem getItem(String key){
		return this.programme.get(key);
	}

	public Collection<ProgrammeItem> listProgrammeItems(){
		return programme.values();
	}

	public void removeItem(String key){
		ProgrammeItemDB.delete(this.programme.get(key));
		this.programme.remove(key);
	}

	/*
	 * Méthode inutile
	 */
	public void addItem(String key){
		this.programme.put(key, new ProgrammeItem(null, null, null, null, null, null));
	}

	public void addItem(ProgrammeItem item){
		this.programme.put(item.getCategorieHeure().getNom(), item);
	}

}
