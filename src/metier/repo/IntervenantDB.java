package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Intervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntervenantDB {

	private Connection db = DB.getInstance();

	private CategorieIntervenantDB categorieIntervenantDB;

	private PreparedStatement psGetIntervenants;
	private PreparedStatement psGetIntervenantParId;
	private PreparedStatement psAjouterIntervenant;
	private PreparedStatement psSuppIntervenant;
	private PreparedStatement psUpdateIntervenant;



	public IntervenantDB(){
		this.categorieIntervenantDB = new CategorieIntervenantDB();
		try{
			this.psGetIntervenants = db.prepareStatement("SELECT * FROM CategorieIntervenant");
			this.psGetIntervenantParId = db.prepareStatement("SELECT * FROM CategorieIntervenant WHERE idIntervenant = ?");
			this.psAjouterIntervenant = db.prepareStatement("INSERT INTO Intervenant (id, codeCatIntervenant, nom, prenom, hmax) VALUES (?, ?, ?, ?, ?)");
			this.psSuppIntervenant = db.prepareStatement("DELETE FROM Intervenant WHERE id = ?");
			this.psUpdateIntervenant = db.prepareStatement("UPDATE Intervenant SET codeCatIntervenant = ?, nom = ?, prenom = ?, hmax = ? WHERE id = ?");

		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public List<Intervenant> getIntervenants(){
		DBResult result = DB.query(this.psGetIntervenants);
		List<Intervenant> intervenants = new ArrayList<>();
		for ( Map<String, String> ligne : result.getLignes() ){
			intervenants.add(ligneToIntervenant(ligne));
		}
		return intervenants;
	}

	public Intervenant getIntervenantParId(String id){
		try{
			this.psGetIntervenantParId.setString(1, id);
			DBResult result = DB.query(this.psGetIntervenantParId);
			Map<String, String> ligne = result.getLignes().get(0);
			return ligneToIntervenant(ligne);
		} catch ( Exception e ){
			return null;
		}
	}

	private Intervenant ligneToIntervenant(Map<String, String> ligne){
		return new Intervenant(
				Integer.parseInt(ligne.get("id")),
				categorieIntervenantDB.getCategorieParId(ligne.get("codeCatIntervenant")),
				ligne.get("nom"),
				ligne.get("prenom"),
				Integer.parseInt(ligne.get("hMax"))
		);
	}

	public void ajouterIntervenant(Intervenant i){
		try{
			psAjouterIntervenant.setInt(1, i.getId());
			psAjouterIntervenant.setString(2, i.getCategorie().getCode());
			psAjouterIntervenant.setString(3, i.getNom());
			psAjouterIntervenant.setString(4, i.getPrenom());
			psAjouterIntervenant.setInt(5, i.gethMax());
			psAjouterIntervenant.executeUpdate();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public void suppIntervenant(Intervenant i){
		try{
			psSuppIntervenant.setInt(1, i.getId());
			psSuppIntervenant.executeUpdate();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public void updateIntervenant(Intervenant i)
	{
		try {
			psUpdateIntervenant.setString(1, i.getCategorie().getCode());
			psUpdateIntervenant.setString(2, i.getNom());
			psUpdateIntervenant.setString(3, i.getPrenom());
			psUpdateIntervenant.setInt(4, i.gethMax());
			psUpdateIntervenant.setInt(5, i.getId());
			psUpdateIntervenant.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



}
