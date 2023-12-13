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
			affectations.add(ligneToAffectatiopackage metier.repo;

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
				private CategorieModuleDB categorieModuleDB;
				private ModuleDB moduleDB;
			
				private PreparedStatement psGetAffectations;
				private PreparedStatement psAjouterAffectation;
				private PreparedStatement psSuppAffectation;
			
				public AffectationDB(){
					this.moduleDB = new ModuleDB();
					this.intervenantDB = new IntervenantDB();
					this.categorieHeureDB = new CategorieHeureDB();
					this.categorieModuleDB = new CategorieModuleDB();
					try{
						db.prepareStatement("SELECT * FROM Affectation");
						db.prepareStatement("INSERT INTO Affectation VALUES(?,?,?,?,?,?,?)");
						db.prepareStatement("DELETE FROM Affectation WHERE idIntervenant = ? AND nomCatHeure = ? AND codeModule = ?");
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
			
				public void ajouterAffectation(Affectation a)
				{
					try{
						this.psAjouterAffectation.setInt(1, a.getIntervenant().getId());
						this.psAjouterAffectation.setString(2, a.getCategorieHeure().getNom());
						this.psAjouterAffectation.setInt(3, a.getNbHeure());
						this.psAjouterAffectation.setInt(4, a.getNbGroupe());
						this.psAjouterAffectation.setString(5, a.getModule().getCode());
						this.psAjouterAffectation.setString(6, a.getCommentaire());
						this.psAjouterAffectation.setInt(7, a.getNbSemaine());
						this.psAjouterAffectation.executeUpdate();
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			
				public void suppAffectation(Affectation a)
				{
					try
					{
						this.psSuppAffectation.setInt(1, a.getIntervenant().getId());
						this.psSuppAffectation.setString(2, a.getCategorieHeure().getNom());
						this.psSuppAffectation.setString(3, a.getModule().getCode());
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
			
					/*
				idIntervenant INT NOT NULL,
				nomCatHeure INT NOT NULL,
				nbH INT default 0,
				nbGrp INT default 0,
				codeModule VARCHAR(50) NOT NULL,
				commentaire TEXT,
				nbSemaine INT,
				FOREIGN KEY (idIntervenant) REFERENCES Intervenant(idIntervenant),
				FOREIGN KEY (nomCatHeure) REFERENCES CategorieHeure(nom),
				FOREIGN KEY (codeModule) REFERENCES Module(code),
				PRIMARY KEY(idIntervenant, nomCatHeure, codeModule)
				 */
			
			
			}
			n(ligne));
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
