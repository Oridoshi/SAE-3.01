package metier.model;

import java.util.HashMap;
import java.util.Map;

public class Programme {

	private Map<String, ProgrammeItem> programme;

	public Programme(){
		this.programme = new HashMap<>();
	}

	public ProgrammeItem getItem(String key){
		return this.programme.get(key);
	}

	public void removeItem(String key){
		this.programme.remove(key);
	}

	public void addItem(String key){
		this.programme.put(key, new ProgrammeItem(null, null, null, null, null, null));
	}

	public void addItem(ProgrammeItem item){
		this.programme.put(item.getCategorieHeure().getNom(), item);
	}

}
