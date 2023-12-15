package metier.repo;

import metier.DB;
import metier.DBResult;
import metier.model.Affectation;
import metier.model.Module;
import metier.model.Intervenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AffectationDB {

	private static Connection db = DB.getInstance();

	private static List<Affectation> affectations;
	private static Map<Integer, List<Affectation>> affectationsParIntervenant;
	private static Map<String, List<Affectation>> affectationsParModule;
	private static Map<Intervenant, Set<Module>> modulesParIntervenant;
	private static Map<String, List<Affectation>> affectationsParSemestreEtIntervenant;

	private static PreparedStatement psGetAffectations;
	private static PreparedStatement psUpdateAffectation;
	private static PreparedStatement psCreateAffectation;
	private static PreparedStatement psDeleteAffectation;

	static{
		affectations = new ArrayList<>();
		affectationsParIntervenant = new HashMap<>();
		affectationsParModule = new HashMap<>();
		modulesParIntervenant = new HashMap<>();
		affectationsParSemestreEtIntervenant = new HashMap<>();
		try{
			psGetAffectations = db.prepareStatement("SELECT * FROM Affectation");
			psDeleteAffectation = db.prepareStatement("DELETE FROM Affectation WHERE idIntervenant = ? AND nomCatHeure = ? AND codeModule = ?");
			psUpdateAffectation = db.prepareStatement("UPDATE Affectation SET nbGrp = ?, nbSemaine = ?, nbH = ?, commentaire = ? WHERE idIntervenant = ? AND nomCatHeure = ? AND codeModule = ?");
			psCreateAffectation = db.prepareStatement("INSERT INTO Affectation VALUES (?, ?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAffectations.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				affectations.add(new Affectation(
					IntervenantDB.getParId(Integer.parseInt(ligne.get("idintervenant"))),
					CategorieHeureDB.getParNom(ligne.get("nomcatheure")),
					Integer.parseInt(ligne.get("nbgrp")),
					Integer.parseInt(ligne.get("nbsemaine")),
					Integer.parseInt(ligne.get("nbh")),
					ligne.get("commentaire"),
					ModuleDB.getParCode(ligne.get("codemodule"))
				));
			}
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static List<Affectation> list(){
		return affectations;
	}

	public static List<Affectation> getAffectationsParModule(String code){
		return affectationsParModule.get(code);
	}

	public static List<Affectation> getAffectationsParIntervenant(int id){
		return affectationsParIntervenant.get(id);
	}

	public static Set<Module> getModulesParIntervenant(Intervenant intervenant){
		return modulesParIntervenant.get(intervenant);
	}

	public static List<Affectation> getAffectationsParIntervenantParSemestre(Intervenant intervenant, int idSemestre){
		String s = intervenant.getId() + idSemestre + "";
		return affectationsParSemestreEtIntervenant.get(s);
	}

	public static boolean delete(Affectation affectation){
		try{
			psDeleteAffectation.setInt(1, affectation.getIntervenant().getId());
			psDeleteAffectation.setString(2, affectation.getCategorieHeure().getNom());
			psDeleteAffectation.setString(3, affectation.getModule().getCode());
			if ( DB.update(psDeleteAffectation) == 1){
				affectations.remove(affectation);
				init();
				return true;
			} else {
				return false;
			}
		} catch ( SQLException e ){
			return false;
		}
	}

	public static boolean save(Affectation affectation){
		if ( affectations.contains(affectation) ){
			try{
				psUpdateAffectation.setInt(1, affectation.getNbGroupe());
				psUpdateAffectation.setInt(2, affectation.getNbSemaine());
				psUpdateAffectation.setInt(3, affectation.getNbHeure());
				psUpdateAffectation.setString(4, affectation.getCommentaire());
				psUpdateAffectation.setInt(5, affectation.getIntervenant().getId());
				psUpdateAffectation.setString(6, affectation.getCategorieHeure().getNom());
				psUpdateAffectation.setString(7, affectation.getModule().getCode());
				return DB.update(psGetAffectations) == 1;
			} catch ( SQLException e){
				return false;
			}
		} else {
			try{
				psCreateAffectation.setInt(1, affectation.getIntervenant().getId());
				psCreateAffectation.setString(2, affectation.getCategorieHeure().getNom());
				psCreateAffectation.setInt(3, affectation.getNbHeure());
				psCreateAffectation.setInt(4, affectation.getNbGroupe());
				psCreateAffectation.setString(5, affectation.getModule().getCode());
				psCreateAffectation.setString(6, affectation.getCommentaire());
				psCreateAffectation.setInt(7, affectation.getNbSemaine());
				if ( DB.update(psCreateAffectation) == 1 ){
					affectations.add(affectation);
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

	private static void init(){
		for ( Affectation affectation : affectations ){

			// On place dans l'array AffectationParIntervenant
			affectationsParIntervenant.putIfAbsent(affectation.getIntervenant().getId(), new ArrayList<>());
			affectationsParIntervenant.get(affectation.getIntervenant().getId()).add(affectation);

			// On place dans l'array AffectationParModule
			affectationsParModule.putIfAbsent(affectation.getModule().getCode(), new ArrayList<>());
			affectationsParModule.get(affectation.getModule().getCode()).add(affectation);

			// On place dans ModulesParIntervenants
			modulesParIntervenant.putIfAbsent(affectation.getIntervenant(), new HashSet<>());
			modulesParIntervenant.get(affectation.getIntervenant()).add(affectation.getModule());

			// On place dans affectationsParSemestreEtIntervenant
			String c = affectation.getIntervenant().getId() + affectation.getModule().getSemestre().getId() + "";
			affectationsParSemestreEtIntervenant.putIfAbsent(c, new ArrayList<>());
			affectationsParSemestreEtIntervenant.get(c).add(affectation);
		}
	}
}
