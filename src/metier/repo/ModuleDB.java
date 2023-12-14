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
	private PreparedStatement psGetModuleParSemestre;
	private PreparedStatement psAjouterModule;
	private PreparedStatement psSuppModule;


	public ModuleDB(){
		this.semestreDB = new SemestreDB();
		this.categorieModuleDB = new CategorieModuleDB();
		try{
			this.psGetModules = db.prepareStatement("SELECT * FROM Module");
			this.psGetModuleParCode = db.prepareStatement("SELECT * FROM Module WHERE code = ?");
			this.psGetProgrammeItem = db.prepareStatement("SELECT * FROM ProgrammeItem WHERE codeModule = ?");
			this.psGetModuleParSemestre = db.prepareStatement("SELECT * FROM Module WHERE idSemestre = ?");
			this.psAjouterModule = db.prepareStatement("INSERT INTO Module VALUES(?,?,?,?,?,?,?)");
			this.psSuppModule = db.prepareStatement("DELETE FROM Module WHERE code = ?");
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

	public List<Module> getModulesParSemestre(int idSemestre){
		try{ this.psGetModuleParSemestre.setInt(1, idSemestre); }
		catch ( Exception e ) { e.printStackTrace(); }
		DBResult result = DB.query(this.psGetModuleParSemestre);
		List<Module> modules = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() )
			modules.add(ligneToModule(ligne));
		return modules;
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

	public void ajouterModule(Module m)
	{
		try {
			psAjouterModule.setString(1, m.getCode());
			psAjouterModule.setBoolean(2, m.getValider());
			psAjouterModule.setInt(3, m.getSemestre().getId());
			psAjouterModule.setString(4, m.getCategorieModule().getNom());
			psAjouterModule.setString(5, m.getLibelleLong());
			psAjouterModule.setString(6, m.getLibelleCourt());
			psAjouterModule.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void suppModule(Module m)
	{
		try {
			psSuppModule.setString(1, m.getCode());
			psSuppModule.executeUpdate();
		} catch (Exception e) {}
	}
}
