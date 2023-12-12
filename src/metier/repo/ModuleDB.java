package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Module;
import metier.model.ProgrammeItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModuleDB {

	private Connection db = DB.getInstance();

	private SemestreDB semestreDB;
	private CategorieModuleDB categorieModuleDB;

	private PreparedStatement psGetModules;
	private PreparedStatement psGetModuleParCode;
	private PreparedStatement psGetProgrammeItem;

	public ModuleDB(){
		this.semestreDB = new SemestreDB();
		this.categorieModuleDB = new CategorieModuleDB();
		try{
			this.psGetModules = db.prepareStatement("SELECT * FROM Module");
			this.psGetModuleParCode = db.prepareStatement("SELECT * FROM Module WHERE code = ?");
			this.psGetProgrammeItem = db.prepareStatement("SELECT * FROM ProgrammeItem WHERE codeModule = ?");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<Module> getModules(){
		DBResult result = DB.query(this.psGetModules);
		List<Module> modules = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() )
			modules.add(ligneToModule(ligne));
		return modules;
	}

	public Module getModuleParCode(String code){
		try{ this.psGetModuleParCode.setString(1, code); }
		catch ( Exception e ) { e.printStackTrace(); }
		DBResult result = DB.query(this.psGetModuleParCode);
		return ligneToModule(result.getLignes().get(0));
	}

	private Module ligneToModule(Map<String, String> ligne){
		return new Module(
				ligne.get("code"),
				semestreDB.getSemestreParId(Integer.parseInt(ligne.get("idSemestre"))),
				categorieModuleDB.getCategorieModuleParId(ligne.get("nomCatModule")),
				Boolean.getBoolean(ligne.get("forceValider")),
				ligne.get("libLong"),
				ligne.get("libCourt"),
				null
		);
	}


	private ArrayList<ProgrammeItem> getListeProgrammeItem(String codeModule) {
		ArrayList<ProgrammeItem> listeProgrammeItem = new ArrayList<>();
		try {
				this.psGetProgrammeItem.setString(1, codeModule);
				DBResult result = DB.query(this.psGetProgrammeItem);
				for (Map<String, String> ligne : result.getLignes()) {
					listeProgrammeItem.add(new ProgrammeItem(
							new Integer(ligne.get("nbHProgramme")),
							new Integer(ligne.get("nbSemaine")),
							new Integer(ligne.get("nbHPromo"))));
					}

		} catch (Exception e) {
			// TODO: handle exception
		}


		return listeProgrammeItem;
	}
}
