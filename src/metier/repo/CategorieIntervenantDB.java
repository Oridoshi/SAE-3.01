package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieIntervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieIntervenantDB
{

	private Connection db = DB.getInstance();

	private PreparedStatement psGetCategories;
	private PreparedStatement psGetCategorieParId;

	public CategorieIntervenantDB(){
		try{
			this.psGetCategories = db.prepareStatement("SELECT * FROM CategorieIntervenant");
			this.psGetCategorieParId = db.prepareStatement("SELECT * FROM CategorieIntervenant WHERE idCatIntervenant = ?");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<CategorieIntervenant> getCategoriesIntervenant(){
		DBResult result = DB.query(this.psGetCategories);
		List<CategorieIntervenant> categories = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			categories.add(ligneToCategorieIntervenant(ligne));

		}
		return categories;
	}

	public CategorieIntervenant getCategorieParId(String id){
		try{
			this.psGetCategorieParId.setString(1, id);
			DBResult result = DB.query(this.psGetCategorieParId);
			Map<String, String> ligne = result.getLignes().get(0);
			return ligneToCategorieIntervenant(ligne);
		} catch ( SQLException e ){
			return null;
		}
	}

	private CategorieIntervenant ligneToCategorieIntervenant(Map<String, String> ligne)
	{
			return new CategorieIntervenant(
			ligne.get("code"),
			ligne.get("nom"),
			Integer.parseInt(ligne.get("minH")),
			Integer.parseInt(ligne.get("maxH")),
			Double.parseDouble(ligne.get("coefTp")));
	}

}