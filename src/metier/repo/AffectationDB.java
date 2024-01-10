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
import java.util.Collections;
import java.util.Comparator;
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

	private static PreparedStatement psGetAffectations;
	private static PreparedStatement psUpdateAffectation;
	private static PreparedStatement psCreateAffectation;
	private static PreparedStatement psDeleteAffectation;

	private static int dernierId;

	static{
		reset();
	}
	public static void reset(){
		affectations = new ArrayList<>();
		affectationsParIntervenant = new HashMap<>();
		affectationsParModule = new HashMap<>();
		modulesParIntervenant = new HashMap<>();
		try{
			psGetAffectations = db.prepareStatement("SELECT * FROM Affectation");
			psDeleteAffectation = db.prepareStatement("DELETE FROM Affectation WHERE id = ?");
			psUpdateAffectation = db.prepareStatement("UPDATE Affectation SET nbGrp = ?, nbSemaine = ?, nbH = ?, commentaire = ? WHERE id = ?");
			psCreateAffectation = db.prepareStatement("INSERT INTO Affectation VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			DBResult result = new DBResult(psGetAffectations.executeQuery());
			for ( Map<String, String> ligne : result.getLignes() ){
				affectations.add(new Affectation(
					Integer.parseInt(ligne.get("id")),
					IntervenantDB.getParId(Integer.parseInt(ligne.get("idintervenant"))),
					CategorieHeureDB.getParId(Integer.parseInt(ligne.get("idcatheure"))),
					Integer.parseInt(ligne.get("nbgrp")),
					Integer.parseInt(ligne.get("nbsemaine")),
					Integer.parseInt(ligne.get("nbh")),
					ligne.get("commentaire"),
					ModuleDB.getParId(Integer.parseInt(ligne.get("idmodule")))
				));
			}
			int max = 0;
			for ( Affectation affectation : affectations )
				if ( affectation.getId() > max ) max = affectation.getId();
			dernierId =  max;
			init();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public static int getDernierId(){
		return ++dernierId;
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

	public static Affectation getParId(int id){
		for ( Affectation affectation : affectations )
			if ( affectation.getId() == id ) return affectation;
		return null;
	}

	public static boolean delete(Affectation affectation){
		try{
			psDeleteAffectation.setInt(1, affectation.getId());
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
				psUpdateAffectation.setInt(1, affectation.getNbGroupe() == null?0:affectation.getNbGroupe());
				psUpdateAffectation.setInt(2, affectation.getNbSemaine() == null?0:affectation.getNbSemaine());
				psUpdateAffectation.setInt(3, affectation.getNbHeure() == null?0:affectation.getNbHeure());
				psUpdateAffectation.setString(4, affectation.getCommentaire());
				psUpdateAffectation.setInt(5, affectation.getId());
				if ( DB.update(psUpdateAffectation) == 1 ){
					init();
					return true;
				} else {
					return false;
				}
			} catch ( SQLException e) {
				return false;
			}
		} else {
			try{
				psCreateAffectation.setInt(1, affectation.getId());
				psCreateAffectation.setInt(2, affectation.getIntervenant().getId());
				psCreateAffectation.setInt(3, affectation.getCategorieHeure().getId());
				psCreateAffectation.setInt(4, affectation.getNbHeure() == null?0:affectation.getNbHeure());
				psCreateAffectation.setInt(5, affectation.getNbGroupe() == null?0:affectation.getNbGroupe());
				psCreateAffectation.setInt(6, affectation.getModule().getId());
				psCreateAffectation.setString(7, affectation.getCommentaire());
				psCreateAffectation.setInt(8, affectation.getNbSemaine() == null?0:affectation.getNbSemaine());
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
		Collections.sort(affectations, Comparator
		.<Affectation, String>comparing(affectation -> affectation.getIntervenant().getNom())
		.thenComparing(affectation -> affectation.getIntervenant().getPrenom()));
		for ( Affectation affectation : affectations ){

			// On place dans l'array AffectationParIntervenant
			affectationsParIntervenant.putIfAbsent(affectation.getIntervenant().getId(), new ArrayList<>());
			if(!affectationsParIntervenant.get(affectation.getIntervenant().getId()).contains(affectation))
				affectationsParIntervenant.get(affectation.getIntervenant().getId()).add(affectation);

			// On place dans l'array AffectationParModule
			affectationsParModule.putIfAbsent(affectation.getModule().getCode(), new ArrayList<>());
			if(!affectationsParModule.get(affectation.getModule().getCode()).contains(affectation))
				affectationsParModule.get(affectation.getModule().getCode()).add(affectation);

			// On place dans ModulesParIntervenants
			modulesParIntervenant.putIfAbsent(affectation.getIntervenant(), new HashSet<>());
			if(!modulesParIntervenant.get(affectation.getIntervenant()).contains(affectation.getModule()))
				modulesParIntervenant.get(affectation.getIntervenant()).add(affectation.getModule());
		}
	}
}
