package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Semestre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SemestreDB{

	private Connection db = DB.getInstance();

	private PreparedStatement psGetSemestres;

	public SemestreDB(){
		try{
			this.psGetSemestres = db.prepareStatement("SELECT * FROM Semestre");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<Semestre> getSemestres(){
		DBResult result = DB.query(this.psGetSemestres);
		List<Semestre> semestres = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			semestres.add(ligneToSemestre(ligne));
		}
		return semestres;
	}

	private Semestre ligneToSemestre(Map<String, String> ligne){
		return new Semestre(
			Integer.parseInt(ligne.get("id")),
			Integer.parseInt(ligne.get("nbGrpTd")),
			Integer.parseInt(ligne.get("nbGrpTp")),
			Integer.parseInt(ligne.get("nbEtd")),
			Integer.parseInt(ligne.get("nbSemaines"))
		);
	}

}