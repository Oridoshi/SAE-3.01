package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.CategorieModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorieModuleDB {

	private static Connection db = DB.getInstance();

	private static List<CategorieModule> categoriesModule;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	private static int dernierId = 1;

	static{
		reset();
	}
	public static void reset(){
		categoriesModule = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM CategorieModule");
			psDelete = db.prepareStatement("DELETE FROM CategorieModule WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE CategorieModule SET nom = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO CategorieModule VALUES (?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				CategorieModule categorieModule = new CategorieModule(ligne.get("nom"));
				int id = Integer.parseInt(ligne.get("id"));
				categorieModule.setId(id);
				categoriesModule.add(categorieModule);
				if ( dernierId < id  ) dernierId = id;
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<CategorieModule> list(){
		return categoriesModule;
	}

	public static CategorieModule getParNom(String nom){
		for ( CategorieModule categorieModule : categoriesModule )
			if ( categorieModule.getNom().equals(nom) ) return categorieModule;
		return null;
	}

	public static CategorieModule getParId(int id){
		for ( CategorieModule categorieModule : categoriesModule )
			if ( categorieModule.getId() == id ) return categorieModule;
		return null;
	}

	public static boolean delete(CategorieModule categorieModule){
		try{
			psDelete.setInt(1, categorieModule.getId());
			if ( DB.update(psDelete) == 1){
				categoriesModule.remove(categorieModule);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(CategorieModule categorieModule){
		if ( categoriesModule.contains(categorieModule) ){
			try{
				psUpdate.setString(1, categorieModule.getNom());
				psUpdate.setInt(2, categorieModule.getId());
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
				psCreate.setString(2, categorieModule.getNom());
				if ( DB.update(psCreate) == 1 ){
					categoriesModule.add(categorieModule);
					categorieModule.setId(dernierId);
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
		// for ( CategorieModule categorieModule : categoriesModule ){
		// 	// empty
		// }
	}
	
}
