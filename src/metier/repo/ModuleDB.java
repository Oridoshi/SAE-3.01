package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleDB {

	private static Connection db = DB.getInstance();

	private static List<Module> modules;
	private static Map<Integer, List<Module>> modulesParIdSemestre;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	private static PreparedStatement psDeleteDependance;

	static{
		modules = new ArrayList<>();
		modulesParIdSemestre = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM Module");
			psDeleteDependance = db.prepareStatement("DELETE FROM remplirprogramme WHERE codeModule = ?");
			psDelete = db.prepareStatement("DELETE FROM Module WHERE code = ?");
			psUpdate = db.prepareStatement("UPDATE Module SET code = ?, idSemestre = ?, nomCatModule = ?, forceValider = ?, libcourt = ?, liblong = ? WHERE code = ?");
			psCreate = db.prepareStatement("INSERT INTO Module VALUES (?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());			
			for ( Map<String, String> ligne : result.getLignes() ){
				modules.add(new Module(
					ligne.get("code"), 
					SemestreDB.getParId(Integer.parseInt(ligne.get("idsemestre"))),
					CategorieModuleDB.getParNom(ligne.get("nomcatmodule")),
					!ligne.get("forcevalider").equals("f"),
					ligne.get("libcourt"),
					ligne.get("liblong")));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<Module> list(){
		return modules;
	}

	public static Module getParCode(String code){
		for ( Module module : modules )
			if ( module.getCode().equals(code) ) return module;
		return null;
	}

	public static List<Module> getParIdSemestre(int id){
		return modulesParIdSemestre.get(id);
	}

	public static boolean delete(Module module){
		try{
			psDeleteDependance.setString(1, module.getCode());
			DB.update(psDeleteDependance);
			psDelete.setString(1, module.getCode());
			if ( DB.update(psDelete) == 1){
				modules.remove(module);
				modulesParIdSemestre.get(module.getSemestre().getId()).remove(module);
				init();
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(Module module){
		if ( modules.contains(module) ){
			try{
				psUpdate.setString (1, module.getCode());
				psUpdate.setInt    (2, module.getSemestre().getId());
				psUpdate.setString (3, module.getCategorieModule().getNom());
				psUpdate.setBoolean(4, module.isValider());
				psUpdate.setString (5, module.getLibelleCourt());
				psUpdate.setString (6, module.getLibelleLong());
				psUpdate.setString (7, module.getCodeOrigine());
				if ( DB.update(psUpdate) == 1 )
				{
					module.setCodeOrigine(module.getCode());
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, module.getCode());
				psCreate.setBoolean(2, module.isValider());
				psCreate.setInt(3, module.getSemestre().getId());
				psCreate.setString(4, module.getCategorieModule().getNom());
				psCreate.setString(5, module.getLibelleCourt());
				psCreate.setString(6, module.getLibelleLong());
				if ( DB.update(psCreate) == 1 ){
					modules.add(module);
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
	// ANAS 
	private static void init(){
		for ( Module module : modules ){
			modulesParIdSemestre.putIfAbsent(module.getSemestre().getId(), new ArrayList<>());
			if(!modulesParIdSemestre.get(module.getSemestre().getId()).contains(module))
				modulesParIdSemestre.get(module.getSemestre().getId()).add(module);
		}
	}
	
}
