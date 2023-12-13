package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AffectationDB {

	private Connection db = DB.getInstance();

	private IntervenantDB intervenantDB;
	private CategorieHeureDB categorieHeureDB;
	private ModuleDB moduleDB;

	private PreparedStatement psGetAffectations;
	private PreparedStatement getPsGetAffectationsParCodeModule;
	private PreparedStatement psGetAffectationsParIntervenantId;


	public AffectationDB(){
		this.moduleDB = new ModuleDB();
		this.intervenantDB = new IntervenantDB();
		this.categorieHeureDB = new CategorieHeureDB();
		try{
			psGetAffectations = db.prepareStatement("SELECT * FROM Affectation");
			getPsGetAffectationsParCodeModule = db.prepareStatement("SELECT * FROM Affectation WHERE codeModule = ?");
			psGetAffectationsParIntervenantId = db.prepareStatement("SELECT * FROM Affectation WHERE idIntervenant = ?");
			);
		} catch ( SQLException e ){
			e.printStackTrace();
		}
	}

	public List<Affectation> getAffectations(){
		DBResult result = DB.query(this.psGetAffectations);
		List<Affectation> affectations = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			affectations.add(ligneToAffectation(ligne));
		}
		return null;
	}

	public List<Affectation> getAffectationsParIntervenantId(int id){
		try{
			psGetAffectationsParIntervenantId.setInt(1, id);
		} catch ( Exception e ){
			e.printStackTrace();
		}
		DBResult result = DB.query(this.psGetAffectationsParIntervenantId);
		List<Affectation> affectations = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			affectations.add(ligneToAffectation(ligne));
		}
		return affectations;
	}

	public List<Affectation> getAffectationsParCodeModule(String code){
		try{
			this.getPsGetAffectationsParCodeModule.setString(1, code);
		} catch ( Exception e ){
			e.printStackTrace();
			return null;
		}
		DBResult result = DB.query(this.getPsGetAffectationsParCodeModule);
		List<Affectation> affectations = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			affectations.add(ligneToAffectation(ligne));
		}
		return affectations;
	}

	private Affectation ligneToAffectation(Map<String, String> ligne){
		return new Affectation(
				intervenantDB.getIntervenantParId(ligne.get("idIntervenant")),
				categorieHeureDB.getCategorieHeureParId(ligne.get("nomCatHeure")),
				new Integer(ligne.get("nbGrp")),
				new Integer(ligne.get("nbSemaine")),
				new Integer(ligne.get("nbH")),
				ligne.get("commentaire"),
				moduleDB.getModuleParCode("codeModule")
		);
	}
}
