package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.ProgrammeItem;

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
	private static Map<Integer, List<ProgrammeItem>> programmeItemsParIdModule;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	private static int dernierId = 1;

	static{
		reset();
	}
	public static void reset(){
		programmeItems = new ArrayList<>();
		programmeItemsParCodeModule = new HashMap<>();
		programmeItemsParIdModule = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM RemplirProgramme");
			psDelete = db.prepareStatement("DELETE FROM RemplirProgramme WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE RemplirProgramme SET nbHProgramme = ?, nbHPromo = ?, nbSemaine = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO RemplirProgramme VALUES (?, ?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				ProgrammeItem programmeItem = new ProgrammeItem(
					CategorieModuleDB.getParId(Integer.parseInt(ligne.get("idcatmodule"))),
					CategorieHeureDB.getParId(Integer.parseInt(ligne.get("idcath"))),
					ModuleDB.getParId(Integer.parseInt(ligne.get("idmodule"))),
					Integer.parseInt(ligne.get("nbhprogramme")),
					Integer.parseInt(ligne.get("nbsemaine")),
					Integer.parseInt(ligne.get("nbhpromo"))
				);
				int id = Integer.parseInt(ligne.get("id"));
				programmeItem.setId(id);
				if ( dernierId < id ) dernierId = id;
				programmeItems.add(programmeItem);
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

	public static List<ProgrammeItem> listParIdModule(int id){
		return programmeItemsParIdModule.get(id);
	}

	public static ProgrammeItem getParId(int id){
		for ( ProgrammeItem programmeItem : programmeItems )
			if ( programmeItem.getId() == id ) return programmeItem;
		return null;
	}

	public static boolean delete(ProgrammeItem programmeItem){
		try{
			psDelete.setInt(1, programmeItem.getId());
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
				psUpdate.setInt(4, programmeItem.getId());
				return DB.update(psUpdate) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setInt(1, ++dernierId);
				psCreate.setInt(2, programmeItem.getCategorieModule().getId());
				psCreate.setInt(3, programmeItem.getCategorieHeure().getId());
				psCreate.setInt(4, ModuleDB.getParCode(programmeItem.getCodeModule()).getId());
				psCreate.setInt(5, programmeItem.getNbHPn());
				psCreate.setInt(6, programmeItem.getNbHeure());
				psCreate.setInt(7, programmeItem.getNbSemaine());
				if ( DB.update(psCreate) == 1 ){
					programmeItems.add(programmeItem);
					programmeItem.setId(dernierId);
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
			programmeItemsParCodeModule.putIfAbsent(programmeItem.getCodeModule(), new ArrayList<>());
			programmeItemsParCodeModule.get(programmeItem.getCodeModule()).add(programmeItem);
		}
		for ( ProgrammeItem programmeItem : programmeItems ){
			programmeItemsParIdModule.putIfAbsent(programmeItem.getModule().getId(), new ArrayList<>());
			programmeItemsParIdModule.get(programmeItem.getModule().getId()).add(programmeItem);
		}
	}
	
}
