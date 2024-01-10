package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.PatternCategorieModuleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternCategorieModuleItemDB {

	private static Connection db = DB.getInstance();

	private static List<PatternCategorieModuleItem> patternCategorieModuleItems;
	private static Map<String, List<PatternCategorieModuleItem>> patternCategorieModuleItemsParNomCatModule;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psCreate;

	private static int dernierId = 1;

	static{
		reset();
	}

	public static void reset(){
		patternCategorieModuleItems = new ArrayList<>();
		patternCategorieModuleItemsParNomCatModule = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM RemplirCategorieModule");
			psDelete = db.prepareStatement("DELETE FROM RemplirCategorieModule WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO RemplirCategorieModule VALUES (?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				PatternCategorieModuleItem patternCategorieModuleItem = new PatternCategorieModuleItem(
					CategorieHeureDB.getParId(Integer.parseInt(ligne.get("idcath"))), 
					CategorieModuleDB.getParId(Integer.parseInt(ligne.get("idcatmodule"))));
				int id = Integer.parseInt(ligne.get("id"));
				if ( dernierId < id ) dernierId = id;
				patternCategorieModuleItem.setId(id);
				patternCategorieModuleItems.add(patternCategorieModuleItem);

			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<PatternCategorieModuleItem> list(){
		return patternCategorieModuleItems;
	}

	public static List<PatternCategorieModuleItem> listParNomCatModule(String nom){
		return patternCategorieModuleItemsParNomCatModule.get(nom);
	}


	public static PatternCategorieModuleItem getParId(int id){
		for ( PatternCategorieModuleItem patternCategorieModuleItem : patternCategorieModuleItems )
			if ( patternCategorieModuleItem.getId() == id ) return patternCategorieModuleItem;
		return null;
	}

	public static boolean delete(PatternCategorieModuleItem patternCategorieModuleItem){
		try{
			psDelete.setInt(1, patternCategorieModuleItem.getId());
			if ( DB.update(psDelete) == 1){
				patternCategorieModuleItems.remove(patternCategorieModuleItem);
				init();
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(PatternCategorieModuleItem patternCategorieModuleItem){
		if ( patternCategorieModuleItems.contains(patternCategorieModuleItem) ){
			return false;
		} else {
			try{
				psCreate.setInt(1, ++dernierId);
				psCreate.setInt(2, patternCategorieModuleItem.getCategorieHeure().getId());
				psCreate.setInt(3, patternCategorieModuleItem.getCategorieModule().getId());
				if ( DB.update(psCreate) == 1 ){
					patternCategorieModuleItems.add(patternCategorieModuleItem);
					init();
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
		for ( PatternCategorieModuleItem patternCategorieModuleItem : patternCategorieModuleItems ){
			patternCategorieModuleItemsParNomCatModule.putIfAbsent(patternCategorieModuleItem.getCategorieModule().getNom(), new ArrayList<>());
			patternCategorieModuleItemsParNomCatModule.get(patternCategorieModuleItem.getCategorieModule().getNom()).add(patternCategorieModuleItem);
		}
	}
	
}
