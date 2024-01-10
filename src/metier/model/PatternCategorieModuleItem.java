package metier.model;

public class PatternCategorieModuleItem{

	private int id;
	private CategorieHeure categorieHeure;
	private CategorieModule categorieModule;

	public PatternCategorieModuleItem(CategorieHeure categorieHeure, CategorieModule categorieModule) {
		this.categorieHeure = categorieHeure;
		this.categorieModule = categorieModule;
	}

	public int getId() {
		return this.id;
	}

	public PatternCategorieModuleItem setId(int id){
		this.id = id;
		return this;
	}

	public CategorieHeure getCategorieHeure() {
		return categorieHeure;
	}

	public CategorieModule getCategorieModule() {
		return categorieModule;
	}
}
