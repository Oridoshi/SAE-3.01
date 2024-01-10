package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Intervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class IntervenantDB {

	private static Connection db = DB.getInstance();

	private static List<Intervenant> intervenants;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	private static int dernierId = 1;

	static{
		reset();
	}
	public static void reset(){
		intervenants = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM Intervenant");
			psDelete = db.prepareStatement("DELETE FROM Intervenant WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE Intervenant SET idcatintervenant = ?, nom = ?, prenom = ?, hMax = ?, hMin = ?, coeftp = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO Intervenant VALUES (?, ?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				Intervenant intervenant = new Intervenant(
					Integer.parseInt(ligne.get("id")),
					CategorieIntervenantDB.getParId(Integer.parseInt(ligne.get("idcatintervenant"))),
					ligne.get("nom"), 
					ligne.get("prenom"), 
					Integer.parseInt(ligne.get("hmin")),
					Integer.parseInt(ligne.get("hmax")),
					Double.parseDouble(ligne.get("coeftp")));
				intervenants.add(intervenant);
				int id = Integer.parseInt(ligne.get("id"));
				intervenant.setId(id);
				if ( dernierId < id ) dernierId = id;
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<Intervenant> list(){
		return intervenants;
	}

	public static Intervenant getParId(int id){
		for ( Intervenant intervenant : intervenants )
			if ( intervenant.getId() == id ) return intervenant;
		return null;
	}

	public static int getDernierId()
	{
		int id = 0;
		for ( Intervenant intervenant : intervenants )
			if ( intervenant.getId() > id ) id = intervenant.getId();
		return id;
	}

	public static boolean delete(Intervenant intervenant){
		try{
			psDelete.setInt(1, intervenant.getId());
			if ( DB.update(psDelete) == 1){
				intervenants.remove(intervenant);
				init();
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(Intervenant intervenant){
		if ( intervenants.contains(intervenant) ){
			try{
				psUpdate.setInt(1, intervenant.getCategorie().getId());
				psUpdate.setString(2, intervenant.getNom());
				psUpdate.setString(3, intervenant.getPrenom());
				psUpdate.setInt(4, intervenant.gethMax());
				psUpdate.setInt(5, intervenant.getHMin());
				psUpdate.setDouble(6, intervenant.getCoefTP());
				psUpdate.setInt(7, intervenant.getId());

				if ( DB.update(psUpdate) == 1 ){
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
				psCreate.setInt(2, intervenant.getCategorie().getId());
				psCreate.setString(3, intervenant.getNom());
				psCreate.setString(4, intervenant.getPrenom());
				// getHMax va poser problème
				psCreate.setInt(5, intervenant.gethMax());
				psCreate.setInt(6, intervenant.getHMin());
				psCreate.setDouble(7, intervenant.getCoefTP());
				if ( DB.update(psCreate) == 1 ){
					intervenants.add(intervenant);
					intervenant.setId(dernierId);
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

	public static List<Intervenant> getLstIntervenantParCode(String code)
	{
		List<Intervenant> inters = null;
		for (Intervenant intervenant : intervenants)
		{
			if (intervenant.getCategorie().getCode().equals(code))
			{
				if(inters == null)
					inters = new ArrayList<>();
				inters.add(intervenant);
			}
		}

		return inters;
	}

	private static void init(){
		Collections.sort(intervenants, Comparator.comparing(Intervenant::getNom).thenComparing(Intervenant::getPrenom));
		// for ( Intervenant intervenant : intervenants ){
		// 	// empty
		// }
	}	
}
