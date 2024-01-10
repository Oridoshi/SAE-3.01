package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	private static int dernierId = 1;

	static{
		reset();
	}
	public static void reset(){
		modules = new ArrayList<>();
		modulesParIdSemestre = new HashMap<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM Module");
			psDelete = db.prepareStatement("DELETE FROM Module id code = ?");
			psUpdate = db.prepareStatement("UPDATE Module SET code = ?, idSemestre = ?, idCatModule = ?, forceValider = ?, libcourt = ?, liblong = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO Module VALUES (?, ?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());			
			for ( Map<String, String> ligne : result.getLignes() ){
				Module module = new Module(
					ligne.get("code"), 
					SemestreDB.getParId(Integer.parseInt(ligne.get("idsemestre"))),
					CategorieModuleDB.getParId(Integer.parseInt(ligne.get("idcatmodule"))),
					!ligne.get("forcevalider").equals("f"),
					ligne.get("libcourt"),
					ligne.get("liblong"));
				int id = Integer.parseInt(ligne.get("id"));
				module.setId(id);
				if ( dernierId < id ) dernierId = id;
				modules.add(module);
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

	public static Module getParId(int id){
		for ( Module module : modules )
			if ( module.getId() == id ) return module;
		return null;
	}

	public static boolean delete(Module module){
		try{
			//psDeleteDependance.setString(1, module.getCode());
			//DB.update(psDeleteDependance);
			psDelete.setInt(1, module.getId());
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
				psUpdate.setInt (3, module.getCategorieModule().getId());
				psUpdate.setBoolean(4, module.isValider());
				psUpdate.setString (5, module.getLibelleCourt());
				psUpdate.setString (6, module.getLibelleLong());
				psUpdate.setInt (7, module.getId());
				if ( DB.update(psUpdate) == 1 )
				{
					init();
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
				psCreate.setString(2, module.getCode());
				psCreate.setBoolean(3, module.isValider());
				psCreate.setInt(4, module.getSemestre().getId());
				psCreate.setString(5, module.getCategorieModule().getNom());
				psCreate.setString(6, module.getLibelleCourt());
				psCreate.setString(7, module.getLibelleLong());
				if ( DB.update(psCreate) == 1 ){
					modules.add(module);
					module.setId(dernierId);
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
		Collections.sort(modules, Comparator.comparing(Module::getCode));

		for ( Module module : modules ){
			modulesParIdSemestre.putIfAbsent(module.getSemestre().getId(), new ArrayList<>());
			if(!modulesParIdSemestre.get(module.getSemestre().getId()).contains(module))
				modulesParIdSemestre.get(module.getSemestre().getId()).add(module);
		}
	}
	
}
