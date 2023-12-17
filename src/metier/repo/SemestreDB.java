package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Semestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SemestreDB {

	private static Connection db = DB.getInstance();

	private static List<Semestre> semestres;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	static{
		semestres = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM Semestre");
			psDelete = db.prepareStatement("DELETE FROM Semestre WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE Semestre SET id = ?, nbGrpTd = ?, nbGrpTp = ?, nbEtd = ?, nbSemaines = ?  WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO Semestre VALUES (?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				semestres.add(new Semestre(
					Integer.parseInt(ligne.get("id")),
					Integer.parseInt(ligne.get("nbgrptd")),
					Integer.parseInt(ligne.get("nbgrptp")),
					Integer.parseInt(ligne.get("nbetd")),
					Integer.parseInt(ligne.get("nbsemaines"))));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<Semestre> list(){
		return semestres;
	}

	public static Semestre getParId(int id){
		for ( Semestre semestre : semestres )
			if ( semestre.getId() == id ) return semestre;
		return null;
	}

	public static boolean delete(Semestre semestre){
		try{
			psDelete.setInt(1, semestre.getId());
			if ( DB.update(psDelete) == 1){
				semestres.remove(semestre);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(Semestre semestre){
		if ( semestres.contains(semestre) ){
			try{
				psUpdate.setInt(1, semestre.getId());
				psUpdate.setInt(2, semestre.getNbGroupeTd());
				psUpdate.setInt(3, semestre.getNbGroupeTp());
				psUpdate.setInt(4, semestre.getNbEtu());
				psUpdate.setInt(5, semestre.getNbSemaine());
				psUpdate.setInt(6, semestre.getId());
				return DB.update(psUpdate) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setInt(1, semestre.getId());
				psCreate.setInt(2, semestre.getNbGroupeTd());
				psCreate.setInt(3, semestre.getNbGroupeTp());
				psCreate.setInt(4, semestre.getNbEtu());
				psCreate.setInt(5, semestre.getNbSemaine());
				if ( DB.update(psCreate) == 1 ){
					semestres.add(semestre);
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
		// for ( Semestre semestre : semestres ){
		// 	// empty
		// }
	}
	
}
