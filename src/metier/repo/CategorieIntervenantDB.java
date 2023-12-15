package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.CategorieIntervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorieIntervenantDB {

	private static Connection db = DB.getInstance();

	private static List<CategorieIntervenant> categoriesIntervenant;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	static{
		categoriesIntervenant = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM CategorieIntervenant");
			psDelete = db.prepareStatement("DELETE FROM CategorieIntervenant WHERE code = ?");
			psUpdate = db.prepareStatement("UPDATE CategorieIntervenant SET code = ?, nom = ?, minh = ?, maxh = ?, coeftp = ? WHERE code = ?");
			psCreate = db.prepareStatement("INSERT INTO CategorieIntervenant VALUES (?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				categoriesIntervenant.add(new CategorieIntervenant(
					ligne.get("code"), 
					ligne.get("nom"), 
					Integer.parseInt(ligne.get("minh")), 
					Integer.parseInt(ligne.get("maxh")), 
					Double.parseDouble(ligne.get("coeftp"))));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<CategorieIntervenant> list(){
		return categoriesIntervenant;
	}

	public static CategorieIntervenant getParCode(String code){
		for ( CategorieIntervenant categorieIntervenant : categoriesIntervenant )
			if ( categorieIntervenant.getCode().equals(code) ) return categorieIntervenant;
		return null;
	}

	public static boolean delete(CategorieIntervenant categorieIntervenant){
		try{
			psDelete.setString(1, categorieIntervenant.getCode());
			if ( DB.update(psDelete) == 1){
				categoriesIntervenant.remove(categorieIntervenant);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(CategorieIntervenant categorieIntervenant){
		if ( categoriesIntervenant.contains(categorieIntervenant) ){
			try{
				psUpdate.setString(1, categorieIntervenant.getCode());
				psUpdate.setString(2, categorieIntervenant.getNom());
				psUpdate.setInt(3, categorieIntervenant.getMinH());
				psUpdate.setInt(4, categorieIntervenant.getMaxH());
				psUpdate.setDouble(5, categorieIntervenant.getCoefTp());
				psUpdate.setString(6, categorieIntervenant.getCode());
				return DB.update(psUpdate) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, categorieIntervenant.getCode());
				psCreate.setString(2, categorieIntervenant.getNom());
				psCreate.setInt(3, categorieIntervenant.getMinH());
				psCreate.setInt(4, categorieIntervenant.getMaxH());
				psCreate.setDouble(5, categorieIntervenant.getCoefTp());
				if ( DB.update(psCreate) == 1 ){
					categoriesIntervenant.add(categorieIntervenant);
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
		for ( CategorieIntervenant categorieIntervenant : categoriesIntervenant ){
			// empty
		}
	}
	
}
