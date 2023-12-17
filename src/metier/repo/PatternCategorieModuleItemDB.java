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

	static{
		patternCategorieModuleItems = new ArrayList<>();
		patternCategorieModuleItemsParNomCatModule = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM RemplirCategorieModule");
			psDelete = db.prepareStatement("DELETE FROM RemplirCategorieModule WHERE nomCatHeure = ? AND nomCatModule = ?");
			psCreate = db.prepareStatement("INSERT INTO RemplirCategorieModule VALUES (?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				patternCategorieModuleItems.add(new PatternCategorieModuleItem(
					CategorieHeureDB.getParNom(ligne.get("nomcath")), 
					CategorieModuleDB.getParNom(ligne.get("nomcatmodule"))));
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

	public static boolean delete(PatternCategorieModuleItem patternCategorieModuleItem){
		try{
			psDelete.setString(1, patternCategorieModuleItem.getCategorieHeure().getNom());
			psDelete.setString(2, patternCategorieModuleItem.getCategorieModule().getNom());
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
				psCreate.setString(1, patternCategorieModuleItem.getCategorieHeure().getNom());
				psCreate.setString(2, patternCategorieModuleItem.getCategorieModule().getNom());
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
