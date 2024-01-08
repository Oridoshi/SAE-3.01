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

	static{
		reset();
	}
	public static void reset(){
		categorieHeures = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM CategorieHeure");
			psDelete = db.prepareStatement("DELETE FROM CategorieHeure WHERE nom = ?");
			psUpdate = db.prepareStatement("UPDATE CategorieHeure SET nom = ?, coeffCat = ? WHERE nom = ?");
			psCreate = db.prepareStatement("INSERT INTO CategorieHeure VALUES (?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				categorieHeures.add(new CategorieHeure(ligne.get("nom"), Double.parseDouble(ligne.get("coeffcat"))));
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

	public static boolean delete(CategorieHeure categorieHeure){
		try{
			psDelete.setString(1, categorieHeure.getNom());
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
				psUpdate.setString(3, categorieHeure.getNomOrigine());
				if ( DB.update(psUpdate) == 1 ){
					categorieHeure.setNomOrigine(categorieHeure.getNom());
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, categorieHeure.getNom());
				psCreate.setDouble(2, categorieHeure.getCoef());
				if ( DB.update(psCreate) == 1 ){
					categorieHeures.add(categorieHeure);
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
