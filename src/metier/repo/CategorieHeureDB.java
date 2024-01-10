package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieHeure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieHeureDB {

	private static Connection db = DB.getInstance();

	private static List<CategorieHeure> categorieHeures;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	private static int dernierId;

	static{
		reset();
	}
	public static void reset(){
		categorieHeures = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM CategorieHeure");
			psDelete = db.prepareStatement("DELETE FROM CategorieHeure WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE CategorieHeure SET nom = ?, coeffCat = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO CategorieHeure VALUES (?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				CategorieHeure categorieHeure = new CategorieHeure(ligne.get("nom"), Double.parseDouble(ligne.get("coeffcat")));
				categorieHeure.setId(Integer.parseInt(ligne.get("id")));
				categorieHeures.add(categorieHeure);
				if ( Integer.parseInt(ligne.get("id")) > dernierId ) dernierId = Integer.parseInt(ligne.get("id"));
			}
			
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<CategorieHeure> list(){
		return categorieHeures;
	}

	public static CategorieHeure getParNom(String nom){
		for ( CategorieHeure categorieHeure : categorieHeures )
			if ( categorieHeure.getNom().equals(nom) ) return categorieHeure;
		return null;
	}

	public static CategorieHeure getParId(int id){
		for ( CategorieHeure categorieHeure : categorieHeures )
			if ( categorieHeure.getId() == id ) return categorieHeure;
		return null;
	}

	public static boolean delete(CategorieHeure categorieHeure){
		try{
			psDelete.setInt(1, categorieHeure.getId());
			if ( DB.update(psDelete) == 1){
				categorieHeures.remove(categorieHeure);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(CategorieHeure categorieHeure){
		if ( categorieHeures.contains(categorieHeure) ){
			try{
				psUpdate.setString(1, categorieHeure.getNom());
				psUpdate.setDouble(2, categorieHeure.getCoef());
				psUpdate.setInt(3, categorieHeure.getId());
				if ( DB.update(psUpdate) == 1 ){
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setInt(1, ++dernierId);
				psCreate.setString(2, categorieHeure.getNom());
				psCreate.setDouble(3, categorieHeure.getCoef());
				if ( DB.update(psCreate) == 1 ){
					categorieHeures.add(categorieHeure);
					categorieHeure.setId(dernierId);
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e ){
				return false;
			}
		}

	}

	private static void init(){
		// for ( CategorieHeure categorieHeure : categorieHeures ){
		// 	// empty
		// }
	}
	
}
