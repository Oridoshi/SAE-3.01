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
	private PreparedStatement psGetSemestreParId;

	public SemestreDB(){
		try{
			this.psGetSemestres = db.prepareStatement("SELECT * FROM Semestre");
			this.psGetSemestreParId = db.prepareStatement("SELECT * FROM Semestre WHERE id = ?");
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<Semestre> getSemestres(){
		DBResult result = DB.query(this.psGetSemestres);
		List<Semestre> semestres = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			semestres.add(new Semestre(
				Integer.parseInt(ligne.get("id")),
				Integer.parseInt(ligne.get("nbGrpTd")),
				Integer.parseInt(ligne.get("nbGrpTp")),
				Integer.parseInt(ligne.get("nbEtd")),
				Integer.parseInt(ligne.get("nbSemaines"))
			));
		}
		return semestres;
	}

	public Semestre getSemestreParId(int id){
		try{
			this.psGetSemestreParId.setInt(1, id);
		} catch ( Exception e ){
			return null;
		}
		DBResult result = DB.query(this.psGetSemestreParId);
		Map<String, String> ligne = result.getLignes().get(0);
		return new Semestre(
			Integer.parseInt(ligne.get(id)),
			Integer.parseInt(ligne.get("nbGrpTd")),
			Integer.parseInt(ligne.get("nbGrpTp")),
			Integer.parseInt(ligne.get("nbEtd")),
			Integer.parseInt(ligne.get("nbSemaines"))
		);
	}

}