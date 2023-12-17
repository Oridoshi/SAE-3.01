package metier.model;

public class PatternCategorieModuleItem{

	private CategorieHeure categorieHeure;
	private CategorieModule categorieModule;

	public PatternCategorieModuleItem(CategorieHeure categorieHeure, CategorieModule categorieModule) {
		this.categorieHeure = categorieHeure;
		this.categorieModule = categorieModule;
	}

	public CategorieHeure getCategorieHeure() {
		return categorieHeure;
	}

	public CategorieModule getCategorieModule() {
		return categorieModule;
	}
}
