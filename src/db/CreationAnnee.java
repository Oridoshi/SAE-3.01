package db;

import javax.swing.JOptionPane;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.CategorieIntervenant;
import metier.model.CategorieModule;
import metier.model.Intervenant;
import metier.model.Semestre;
import metier.repo.DuplicationDB;
import metier.model.Module;

public class CreationAnnee
{
	private FrameIhm mere;

	private Controleur ctrl;

	public CreationAnnee(String nomAnnee, FrameIhm mere, Controleur ctrl)
	{
		
		this.mere = mere;

		this.ctrl = ctrl;
		
		if ( DuplicationDB.getDuplication(nomAnnee) != null )
		{
			int rep = JOptionPane.showConfirmDialog(this.mere, "Il y a déjà une année du nom " + nomAnnee + " voulez vous écraser les donnée présente ?", "Suppression", JOptionPane.YES_NO_OPTION);
			if(rep == JOptionPane.NO_OPTION) return;
		}

		String annee =
		"DROP TABLE if exists Module cascade;"+
		"DROP TABLE if exists RemplirProgramme cascade;"+
		"DROP TABLE if exists Semestre cascade;"+
		"DROP TABLE if exists Intervenant cascade;"+
		"DROP TABLE if exists CategorieIntervenant cascade;"+
		"DROP TABLE if exists CategorieHeure cascade;"+
		"DROP TABLE if exists CategorieModule cascade;"+
		"DROP TABLE if exists Affectation cascade;"+
		"DROP TABLE if exists RemplirCategorieModule cascade;"+
		
		"CREATE TABLE Semestre ("+
		"	id SERIAL PRIMARY KEY,"+
		"	nbGrpTd INT NOT NULL,"+
		"	nbGrpTp INT NOT NULL,"+
		"	nbEtd INT NOT NULL,"+
		"	nbSemaines INT NOT NULL"+
		");"+
		
		"CREATE TABLE CategorieIntervenant ("+
		"	id SERIAL PRIMARY KEY,"+
		"	code VARCHAR(255) NOT NULL,"+
		"	nom VARCHAR(255) NOT NULL,"+
		"	minH INT NOT NULL,"+
		"	maxH INT NOT NULL,"+
		"	coefTp DECIMAL(5, 2) NOT NULL default 1"+
		");"+
		
		"CREATE TABLE CategorieHeure ("+
		"	id SERIAL PRIMARY KEY,"+
		"	nom VARCHAR(50),"+
		"	coeffCat DECIMAL(5, 2) NOT NULL default 1"+
		");"+
		
		"CREATE TABLE CategorieModule("+
		"	id SERIAL PRIMARY KEY,"+
		"	nom VARCHAR(50)"+
		");"+
		
		"CREATE TABLE Module ("+
		"	id SERIAL PRIMARY KEY,"+
		"	code VARCHAR(30),"+
		"	forceValider boolean NOT NULL,"+
		"	idSemestre INT NOT NULL,"+
		"	idCatModule INT NOT NULL,"+
		"	libLong VARCHAR(255) NOT NULL,"+
		"	libCourt VARCHAR(50) NOT NULL,"+
		"	FOREIGN KEY (idSemestre) REFERENCES Semestre(id),"+
		"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id)"+
		");"+
		
		"CREATE TABLE RemplirProgramme("+
		"	id SERIAL PRIMARY KEY,"+
		"	idCatModule INT NOT NULL,"+
		"	idCatH INT NOT NULL,"+
		"	idModule INT NOT NULL,"+
		"	nbHProgramme INT NOT NULL,"+
		"	nbHPromo INT default 0 NOT NULL,"+
		"	nbSemaine INT default 0 NOT NULL,"+
		"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),"+
		"	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id),"+
		"	FOREIGN KEY (idModule) REFERENCES Module(id)"+
		");"+
		
		"CREATE TABLE RemplirCategorieModule ("+
		"	id SERIAL PRIMARY KEY,"+
		"	idCatModule INT NOT NULL,"+
		"	idCatH INT NOT NULL,"+
		"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),"+
		"	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id)"+
		");"+
		
		"CREATE TABLE Intervenant ("+
		"	id SERIAL PRIMARY KEY,"+
		"	idCatIntervenant INT NOT NULL,"+
		"	nom VARCHAR(255) NOT NULL,"+
		"	prenom VARCHAR(255) NOT NULL,"+
		"	hMax INT default 0 NOT NULL,"+
		"	hMin INT default 0 NOT NULL,"+
		"	coefTp DECIMAL(5, 2) NOT NULL default 1,"+
		"	FOREIGN KEY (idCatIntervenant) REFERENCES CategorieIntervenant(id)"+
		");"+
		
		"CREATE TABLE Affectation ("+
		"	id SERIAL PRIMARY KEY,"+
		"	idIntervenant INT NOT NULL,"+
		"	idCatHeure INT NOT NULL,"+
		"	nbH INT default 0 NOT NULL,"+
		"	nbGrp INT default 0 NOT NULL,"+
		"	idModule INT NOT NULL,"+
		"	commentaire TEXT,"+
		"	nbSemaine INT default 0 NOT NULL,"+
		"	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(id),"+
		"	FOREIGN KEY (idCatHeure) REFERENCES CategorieHeure(id),"+
		"	FOREIGN KEY (idModule) REFERENCES Module(id)"+
		");"
		;
		
		/*********/
		/*données*/
		/*********/
		
		// semestres
		String txt = null;
		for (int cpt = 1; cpt <= 6; cpt++)
		{
			Semestre s = this.ctrl.getSemestre(cpt);
			if (txt != null)
			txt += ",(" + s.getId() + ", " + s.getNbGroupeTd() + ", " + s.getNbGroupeTp() + ", " + s.getNbEtu() + ", " + s.getNbSemaine() + ")";
			else
			txt = "(" + s.getId() + ", " + s.getNbGroupeTd() + ", " + s.getNbGroupeTp() + ", " + s.getNbEtu() + ", " + s.getNbSemaine() + ")";
		}
		if(txt != null)
			annee += "\nINSERT INTO Semestre VALUES " + txt + ";";
		
		// catégories d'intervenants
		txt = null;
		for (CategorieIntervenant ci : this.ctrl.getLstCategorieIntervenant())
		{
			if (txt != null)
			txt += ",('" + ci.getCode() + "', '" + ci.getNom() + "', " + ci.getMinH() + ", " + ci.getMaxH() + ", " + ci.getCoefTp() + ")";
			else
			txt = "('" + ci.getCode() + "', '" + ci.getNom() + "', " + ci.getMinH() + ", " + ci.getMaxH() + ", " + ci.getCoefTp() + ")";
		}
		if(txt != null)
			annee += "\nINSERT INTO CategorieIntervenant (code, nom, minH, maxH, coefTp) VALUES " + txt + ";";
		
		// catégories d'heures
		txt = null;
		for (CategorieHeure ch : this.ctrl.getLstCategorieHeure())
		{
			if(txt != null)
			txt += ",('" + ch.getNom() + "', " + ch.getCoef() + ")";
			else
			txt = "('" + ch.getNom() + "', " + ch.getCoef() + ")";
		}
		if(txt != null)
			annee += "\nINSERT INTO CategorieHeure (nom, coeffCat) VALUES " + txt + ";";
		
		// catégories de modules
		txt = null;
		for (CategorieModule cm : this.ctrl.getLstCategorieModule())
		{
			if(txt != null)
			txt += ",(" + cm.getId() + ", '" + cm.getNom() + "')";
			else
			txt = "(" + cm.getId() + ", '" + cm.getNom() + "')";
		}
		if(txt != null)
			annee += "\nINSERT INTO CategorieModule VALUES " + txt + ";";
		
		// modules
		/*
		* code VARCHAR(30) PRIMARY KEY,
		* forceValider boolean NOT NULL,
		* idSemestre INT NOT NULL,
		* nomCatModule VARCHAR(50) NOT NULL,
		* libLong VARCHAR(255) NOT NULL,
		* libCourt VARCHAR(50) NOT NULL,
		*/
		txt = null;
		for(Module m : this.ctrl.getLstModule())
		{
			if(txt != null)
			txt += ",( " + m.getId() +", '" + m.getCode() + "', " + m.isValider() + ", " + m.getSemestre().getId() + ", " + m.getCategorieModule().getId() + ", '" + m.getLibelleLong() + "', '" + m.getLibelleCourt() + "')";
			else
			txt = "( " + m.getId() +", '" + m.getCode() + "', " + m.isValider() + ", " + m.getSemestre().getId() + ", " + m.getCategorieModule().getId() + ", '" + m.getLibelleLong() + "', '" + m.getLibelleCourt() + "')";
		}
		if(txt != null)
			annee += "\nINSERT INTO Module (id, code, forceValider, idSemestre, idCatModule, libLong, libCourt) VALUES " + txt + ";";
		
		// remplir catégorie module
		txt = null;
		for(CategorieModule cm : this.ctrl.getLstCategorieModule())
		{
			for (int i = 0; i < cm.getCategorieHeures().size(); i++)
			{
				if(txt != null)
				txt += ",(" + cm.getId() + ", " + cm.getCategorieHeures().get(i).getCategorieHeure().getId() + ")";
				else
				txt = "(" + cm.getId() + ", " + cm.getCategorieHeures().get(i).getCategorieHeure().getId() + ")";
			}
		}
		if(txt != null)
			annee += "\nINSERT INTO RemplirCategorieModule (idCatModule, idCatH) VALUES " + txt + ";";
		
		// remplir programme
		/*
			* nomCatModule VARCHAR(50) NOT NULL,
			* nomCatH VARCHAR(50) NOT NULL,
			* codeModule VARCHAR(50) NOT NULL,
			* nbHProgramme INT NOT NULL,
			* nbHPromo INT default 0 NOT NULL,
			* nbSemaine INT default 0 NOT NULL,
			*/
		txt = null;
		for(Module m : this.ctrl.getLstModule())
		{
			for (int i = 0; i < m.getCategorieModule().getCategorieHeures().size(); i++)
			{
				if(txt != null)
					txt += ",(" + 
					m.getCategorieModule().getId() + ", " + 
					m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getId() + ", " + 
					m.getId() + ", " + 
					m.getNbHeureProgramme(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + "," 
					+ m.getNbHeureSemaine(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + "," 
					+ m.getNbSemaine(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + ")";
				else{
					txt = "(" + 
					m.getCategorieModule().getId() + ", " + 
					m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getId() + ", " + 
					m.getId() + ", " + 
					m.getNbHeureProgramme(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + "," + 
					m.getNbHeureSemaine(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + "," + 
					m.getNbSemaine(m.getCategorieModule().getCategorieHeures().get(i).getCategorieHeure().getNom()) + ")";
				}
			}
		}
		if(txt != null)
			annee += "\nINSERT INTO RemplirProgramme (idCatModule, idCatH, idModule, nbHProgramme, nbHPromo, nbSemaine) VALUES " + txt + ";";
		
		// intervenants
		txt = null;
		for(Intervenant i : this.ctrl.getLstIntervenants())
		{
			if(txt != null)
			txt += ",(" + i.getId() + ", " + i.getCategorie().getId() + ", '" + i.getNom() + "', '" + i.getPrenom() + "', " + i.gethMax() + ", " + i.getHMin() + ", " + i.getCoefTP() + ")";
			else
			txt = "(" + i.getId() + ", " + i.getCategorie().getId() + ", '" + i.getNom() + "', '" + i.getPrenom() + "', " + i.gethMax() + ", " + i.getHMin() + ", " + i.getCoefTP() + ")";
		}
		if(txt != null)
			annee += "\nINSERT INTO Intervenant VALUES " + txt + ";";
		
		// affectations
		txt = null;
		for(Intervenant i : this.ctrl.getLstIntervenants())
		{
			if(this.ctrl.getLstAffectationParIntervenant(i.getId()) != null)
			for(Affectation a : this.ctrl.getLstAffectationParIntervenant(i.getId()))
			{
				if(txt != null)
				txt += ",(" + a.getId() + ", " + 
				a.getIntervenant().getId() + ", " + 
				a.getCategorieHeure().getId() + ", " + 
				(a.getNbHeure()==null?"0":a.getNbHeure()) + ", " + 
				(a.getNbGroupe()==null?"0":a.getNbGroupe()) + ", " + 
				a.getModule().getId() + ", '" + 
				(a.getCommentaire()==null?"":a.getCommentaire()) + "', " + 
				(a.getNbSemaine()==null?"0":a.getNbSemaine()) + ")";
				else
				txt = "(" + a.getId() + ", " + 
				a.getIntervenant().getId() + ", " + 
				a.getCategorieHeure().getId() + ", " + 
				(a.getNbHeure()==null?"0":a.getNbHeure()) + ", " + 
				(a.getNbGroupe()==null?"0":a.getNbGroupe()) + ", " + 
				a.getModule().getId() + ", '" + 
				(a.getCommentaire()==null?"":a.getCommentaire()) + "', " + 
				(a.getNbSemaine()==null?"0":a.getNbSemaine()) + ")";
			}
		}
		if(txt != null)
			annee += "\nINSERT INTO Affectation VALUES " + txt + ";";
		
		DuplicationDB.save(nomAnnee, annee);

		// try
		// {
		// 	PrintWriter pw = new PrintWriter("src/db/annee.txt");
		// 	pw.println(annee);
		// 	pw.close();
		// }
		// catch (Exception e)
		// {
		// 	e.printStackTrace();
		// }

		int rep = JOptionPane.showConfirmDialog(mere, "Voulez vous copier les données de l'année sur la quelle vous travaillez sur votre nouvelle année ?", "Copy", JOptionPane.YES_NO_OPTION);
		if(rep == JOptionPane.NO_OPTION)
		{
			Initialisation.initialisationBD();
			ctrl.reset();
		}
	}
}