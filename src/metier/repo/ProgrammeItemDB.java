package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.CategorieModule;
import metier.model.Intervenant;
import metier.model.ProgrammeItem;
import metier.model.Semestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammeItemDB {

	private static Connection db = DB.getInstance();

	private static List<ProgrammeItem> programmeItems;
	private static Map<String, List<ProgrammeItem>> programmeItemsParCodeModule;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	static{
		programmeItems = new ArrayList<>();
		programmeItemsParCodeModule = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM RemplirProgramme");
			psDelete = db.prepareStatement("DELETE FROM RemplirProgramme WHERE nomCatModule = ? AND nomCatH = ? AND codeModule = ?");
			psUpdate = db.prepareStatement("UPDATE RemplirProgramme SET nbHProgramme = ?, nbHPromo = ?, nbSemaine = ? WHERE nomCatModule = ? AND nomCatH = ? AND codeModule = ?");
			psCreate = db.prepareStatement("INSERT INTO RemplirProgramme VALUES (?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				programmeItems.add(new ProgrammeItem(
					CategorieModuleDB.getParNom(ligne.get("nomcatmodule")),
					CategorieHeureDB.getParNom(ligne.get("nomcath")),
					ModuleDB.getParCode(ligne.get("codemodule")),
					Integer.parseInt(ligne.get("nbhprogramme")),
					Integer.parseInt(ligne.get("nbhpromo")),
					Integer.parseInt(ligne.get("nbsemaine"))
				));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<ProgrammeItem> list(){
		return programmeItems;
	}

	public static List<ProgrammeItem> listParCodeModule(String code){
		return programmeItemsParCodeModule.get(code);
	}

	public static boolean delete(ProgrammeItem programmeItem){
		try{
			psDelete.setString(1, programmeItem.getCategorieModule().getNom());
			psDelete.setString(2, programmeItem.getCategorieHeure().getNom());
			psDelete.setString(3, programmeItem.getModule().getCode());
			if ( DB.update(psDelete) == 1){
				programmeItems.remove(programmeItem);
				init();
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(ProgrammeItem programmeItem){
		if ( programmeItems.contains(programmeItem) ){
			try{
				psUpdate.setInt(1, programmeItem.getNbHPn());
				psUpdate.setInt(2, programmeItem.getNbHeure());
				psUpdate.setInt(3, programmeItem.getNbSemaine());
				psUpdate.setString(4, programmeItem.getCategorieModule().getNom());
				psUpdate.setString(5, programmeItem.getCategorieHeure().getNom());
				psUpdate.setString(6, programmeItem.getModule().getCode());
				return DB.update(psUpdate) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, programmeItem.getCategorieModule().getNom());
				psCreate.setString(2, programmeItem.getCategorieHeure().getNom());
				psCreate.setString(3, programmeItem.getModule().getCode());
				psCreate.setInt(4, programmeItem.getNbHPn());
				psCreate.setInt(5, programmeItem.getNbHeure());
				psCreate.setInt(6, programmeItem.getNbSemaine());
				if ( DB.update(psCreate) == 1 ){
					programmeItems.add(programmeItem);
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
		for ( ProgrammeItem programmeItem : programmeItems ){
			programmeItemsParCodeModule.putIfAbsent(programmeItem.getModule().getCode(), new ArrayList<>());
			programmeItemsParCodeModule.get(programmeItem.getModule().getCode()).add(programmeItem);
		}
	}
	
}
