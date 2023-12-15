package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.CategorieModule;
import metier.model.Intervenant;
import metier.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntervenantDB {

	private static Connection db = DB.getInstance();

	private static List<Intervenant> intervenants;

	private static PreparedStatement psGetAll;
	private static PreparedStatement psDelete;
	private static PreparedStatement psUpdate;
	private static PreparedStatement psCreate;

	static{
		intervenants = new ArrayList<>();
		try{
			psGetAll = db.prepareStatement("SELECT * FROM Intervenant");
			psDelete = db.prepareStatement("DELETE FROM Intervenant WHERE id = ?");
			psUpdate = db.prepareStatement("UPDATE Intervenant SET codeCatIntervenant = ?, nom = ?, prenom = ?, hMax = ? WHERE id = ?");
			psCreate = db.prepareStatement("INSERT INTO Intervenant (codeCatIntervenant, nom, prenom, hMax) VALUES (?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAll.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				intervenants.add(new Intervenant(
					Integer.parseInt(ligne.get("id")), 
					CategorieIntervenantDB.getParCode(ligne.get("codecatintervenant")),
					ligne.get("nom"), 
					ligne.get("prenom"), 
					Integer.parseInt(ligne.get("hmax"))));
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

<<<<<<< HEAD

    private Intervenant ligneToIntervenant(Map<String, String> ligne){
        return new Intervenant(
                Integer.parseInt(ligne.get("id")),
                categorieIntervenantDB.getCategorieParId(ligne.get("codeCatIntervenant")),
                ligne.get("nom"),
                ligne.get("prenom"),
                Integer.parseInt(ligne.get("hMax"))
        );
    }
=======
	public static boolean delete(Intervenant intervenant){
		try{
			psDelete.setInt(1, intervenant.getId());
			if ( DB.update(psDelete) == 1){
				intervenants.remove(intervenant);
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}
>>>>>>> e292c9a49f48603c658a976960105432e9334e1d

	public static boolean save(Intervenant intervenant){
		if ( intervenants.contains(intervenant) ){
			try{
				psUpdate.setString(1, intervenant.getCategorie().getCode());
				psUpdate.setString(2, intervenant.getNom());
				psUpdate.setString(3, intervenant.getPrenom());
				// getHMax va poser problème
				psUpdate.setInt(4, intervenant.gethMax());
				psUpdate.setInt(5, intervenant.getId());
				return DB.update(psUpdate) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreate.setString(1, intervenant.getCategorie().getCode());
				psCreate.setString(2, intervenant.getNom());
				psCreate.setString(3, intervenant.getPrenom());
				// getHMax va poser problème
				psCreate.setInt(4, intervenant.gethMax());
				if ( DB.update(psCreate) == 1 ){
					intervenants.add(intervenant);
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
		for ( Intervenant intervenant : intervenants ){
			// empty
		}
	}
	
}
