package html;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import controleur.Controleur;
import metier.model.Intervenant;
import metier.model.Module;
import metier.repo.IntervenantDB;
import metier.model.Affectation;

public class GenererPage
{
	private int[]  tabSemestre;
	private String fichier;


	private GenererPage ( int choix, Intervenant inter, Module module )
	{
		if ( choix == 1 )
			this.fichier = inter.getId() + "_" + inter.getNom() + "_" + inter.getPrenom() + ".html";
		else
			this.fichier = module.getCode() + ".html";


		this.tabSemestre = new int[8];

		for ( int cpt = 0; cpt < this.tabSemestre.length; cpt ++ )
		{
			this.tabSemestre   [cpt] = 0;
		}
	}



	/* -------------------------------- */
	/* -------------------------------- */
	/*     GENERER PAGE INTERVENANT     */
	/* -------------------------------- */
	/* -------------------------------- */

	public static void genererPageInter ( Intervenant inter)
	{
		GenererPage page = new GenererPage(1, inter, null);


		try
		{
			File fichier = new File (page.fichier);

			// Vérifier si le fichier existe
			if ( !fichier.exists() )
				fichier.createNewFile();

			// Création d'un PrintWriter pour écrire dans le fichier
			PrintWriter pw = new PrintWriter(new FileWriter(fichier.getAbsoluteFile(), true));

			pw.println( page.creerHead(1) + '\n' );

			pw.println( "\t<body>" );
			pw.println( "\t\t<h1> Fiche de l'intervenant <b>" + inter.getNom() + "</b> </h1>\n" );


			for ( int cptNbSemestres = 1; cptNbSemestres <= 6; cptNbSemestres ++ )
				pw.print( page.creerTableauSemestres( cptNbSemestres, page.genererLstAffectations(cptNbSemestres, inter.getModulesOuIntervient() )));

			pw.println( "\n\t\t<hr>\n\n" );

			// pw.println( page.creerTableauRecapitulatif( inter ) );
			pw.println( "\t</body>\n" );
			pw.println( "</table>" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}


	private String creerTableauSemestres ( int semestre, List<Affectation> lstAffectation )
	{
		String   res = "";
		String   tab = "\t\t";
		String   tmp;
		double[] tmpTabHeures;

		String[] tabNomEnTete    = { "Code du module", "Nom du module", "CM", "TD", "TP", "H tut", "REH", "SAÉ", "HP", "Total" };
		String[] tabClasseEnTete = { null, "nomModule", "heure", "heure", "heure", "heure", "heure", "heure", "heure", "total" };

		int[] tabTotalHeures = new int[7];

		List<Module> lstModulesVues = new ArrayList<Module>();



		for ( int cptHeures = 0; cptHeures < tabTotalHeures.length; cptHeures ++ )
			tabTotalHeures[cptHeures] = 0;


		res += tab + "<section>\n";
		tab += "\t";

		res += tab + "<h2> Semestre " + semestre + " </h2>\n\n\n";

		res += tab + "<table class = 'infosIntervenants'>\n\n";
		tab += "\t";

		res += tab + "<thead>\n";
		res += tab + "\t<tr>\n";
		tab += "\t\t";


		for ( int cptEnTete = 0; cptEnTete < tabNomEnTete.length; cptEnTete ++ )
		{
			tmp = (tabClasseEnTete[cptEnTete] == null) ? "" : "class = '" + tabClasseEnTete[cptEnTete] + "'";

			res += tab + "<th " + String.format("%-19s", tmp)      + "> " +
			       String.format("%-14s", tabNomEnTete[cptEnTete]) + " </th>\n";
		}

		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</tr>\n";
		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</thead>\n\n";

		res += tab + "<tbody>\n";
		tab += "\t";


		for ( Affectation a : lstAffectation )
		{

			if ( lstModulesVues.contains(a.getModule()) )
				continue;

			lstModulesVues.add(a.getModule());

			res += tab + "<tr>\n";

			res += tab + "\t<td> " + a.getModule().getCode()        + " </td>\n";
			res += tab + "\t<td> " + a.getModule().getLibelleLong() + " </td>\n";

			tmpTabHeures = genererTabHeuresModules(a.getModule(), lstAffectation);

			for ( int cptCell = 0; cptCell < tabTotalHeures.length; cptCell ++ )
			{
				res += tab + "\t<td> " + tmpTabHeures[cptCell] + " </td>\n";

				tabTotalHeures  [cptCell   ] += tmpTabHeures[cptCell];
				this.tabSemestre[semestre-1] += tmpTabHeures[cptCell];
			}

			res += tab + "\t<td class = 'total'> " + tmpTabHeures[7] + " </td>\n";
			res += tab + "</tr>\n\n";
		}


		res += tab + "<tr class = 'final'>\n";
		res += tab + "\t<td class = 'tdFinal' colspan = '2'> Total pour le semestre " + semestre + " </td>";

		for ( int cptHeureComp = 0; cptHeureComp < tabTotalHeures.length; cptHeureComp ++ )
			res += tab + "\t<td class = 'tdFinal'> " + tabTotalHeures[cptHeureComp] + " </td>\n";

		res += tab + "\t<td class = 'totalFinal'> " + this.tabSemestre[semestre-1] + " </td>\n";
		res += tab + "</tr>\n";

		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</tbody>\n\n";
		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</table>\n";
		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</section>\n";


		return res;
	}


	private String creerTableauRecapitulatif ( Intervenant inter )
	{
		String res = "";
		String tab = "\t\t";


		// PREMIER TABLEAU : RECAPITULATIF DE CHAQUE SEMESTRES

		res += tab + "<h2> Récapitulatif </2>\n\n";
		res += tab + "<table class = 'statsIntervenants tab1'>\n\n";
		tab += '\t';

		res += tab + "<thead>";
		tab += '\t';

		res += tab + "<tr>\n";

		res += tab + "\t<th class = \"statsS\"   > Semestre        </th>\n";
		res += tab + "\t<th class = \"statsStot\"> Nombre d'heures </th>\n";

		res += tab + "</tr>\n";
		tab  = tab.substring(0, tab.length() - 1);

		res += tab + "</thead>\n\n";

		res += tab + "<tbody>\n";
		tab += "\t";

		for ( int cptNbSemestres = 0; cptNbSemestres < this.tabSemestre.length; cptNbSemestres++ )
		{
			res += tab + "<tr>\n";
			res += tab + "\t<td> S" + cptNbSemestres + 1    + " </td>\n";
			res += tab + "\t<td> "  + this.tabSemestre[cptNbSemestres] + " </td>\n";
			res += tab + "</tr>\n\n";
		}

		res += tab + "<tr class = 'statsTotal'>\n";
		res += tab + "\t<td> <b>TOTAL</b> </td>\n";
		res += tab + "\t<td> <b>"  + inter.getTotal() + "</b> </td>\n";
		res += tab + "</tr>\n";

		tab = tab.substring(0, tab.length() - 1);

		res += "</tbody>\n\n";
		tab  = tab.substring(0, tab.length() - 1);

		res += "</table>\n\n\n";




		// DEUXIEME TABLEAU : RECAPITULATIF DU S1 S3 S5 et S2 S4 S6
		res += tab + "<table class = 'statsIntervenants tab2'>\n\n";
		tab += "\t";

		res += tab + "<thead>\n";
		tab += "\t";

		res += tab + "<tr>\n";
		res += tab + "\t<th class = \"statsS\"   > Semestre        </th>\n";
		res += tab + "\t<th class = \"statsStot\"> Nombre d'heures </th>\n";
		res += tab + "</tr>\n";

		tab = tab.substring(0, tab.length() - 1);

		res += "</thead>\n\n";
		res += "<tbody>\n";


		res += tab + "<tr>\n";
		res += tab + "\t<td> S1 S3 S5 </td>\n";
		res += tab + "\t<td> " + inter.getTotalParImpair() + " </td>\n";
		res += tab + "</tr>\n\n";


		res += tab + "<tr>\n";
		res += tab + "\t<td> S2 S4 S6 </td>\n";
		res += tab + "\t<td> " + inter.getTotalParPair() + " </td>\n";
		res += tab + "</tr>\n\n";


		res += tab + "<tr class = 'statsTotal'>\n";
		res += tab + "\t<td> <b>TOTAL</b> </td>\n";
		res += tab + "\t<td> <b>" + inter.getTotal() + "</b> </td>\n";
		res += tab + "</tr>\n";


		tab = tab.substring(0, tab.length() - 1);
		res += tab + "</tbody>\n\n";

		tab = tab.substring(0, tab.length() - 1);
		res += tab + "</table>\n";

		tab = tab.substring(0, tab.length() - 1);
		res += tab + "</section>\n";

		return res;
	}


	private List<Affectation> genererLstAffectations ( int semestre, List<Affectation> lstAffectation )
	{
		List<Affectation> lst = new ArrayList<Affectation>();

		for ( Affectation a : lstAffectation )
		{
			if(a.getModule().getSemestre().getId() == semestre )
				lst.add(a);
		}


		return lst;
	}


	private double[] genererTabHeuresModules ( Module module, List<Affectation> lstAffectation )
	{
		double[] tab = new double[8];
		double tmp;

		for ( int cpt = 0; cpt < 8; cpt ++ )
			tab[cpt] = 0;

		System.out.println(lstAffectation.size());
		for ( Affectation a : lstAffectation )
		{
			System.out.println(a.getIntervenant().getNom() + " " + a.getModule().getCode());
			if ( a.getModule().equals(module) )
			{
				if ( a.getNbHeure() != null )
				{
					tmp = a.getNbHeure();
				}
				else
					tmp = a.getNbGroupe() * a.getNbSemaine() * a.getCategorieHeure().getCoef();

				System.out.println(a.getCategorieHeure().getNom());
				switch ( a.getCategorieHeure().getNom())
				{
					case "CM"    -> { tab[0] += tmp; tab[7] += tmp; }
					case "TD"    -> { tab[1] += tmp; tab[7] += tmp; }
					case "TP"    -> { tab[2] += tmp; tab[7] += tmp; }
					case "HT"    -> { tab[3] += tmp; tab[7] += tmp; }
					case "REH"   -> { tab[4] += tmp; tab[7] += tmp; }
					case "HSAE"  -> { tab[5] += tmp; tab[7] += tmp; }
					case "HP"    -> { tab[6] += tmp; tab[7] += tmp; }
				}
			}

		}

		return tab;
	}



	/* -------------------------------- */
	/* -------------------------------- */
	/*        GENERER PAGE MODULE       */
	/* -------------------------------- */
	/* -------------------------------- */

	public static void genererPageModule ( Module module )
	{
		GenererPage page = new GenererPage(2, null, module);

		try
		{
			File fichier = new File (page.fichier);

			// Vérifier si le fichier existe
			if ( !fichier.exists() )
				fichier.createNewFile();

			// Création d'un PrintWriter pour écrire dans le fichier
			PrintWriter pw = new PrintWriter(new FileWriter(fichier.getAbsoluteFile(), true));

			pw.println( page.creerHead(2) + '\n' );

			pw.println( "\t<body>" );
			pw.println( "\t\t<h1> " + module.getCode() + " </h1>\n" );

			pw.print( page.creerTableauInfosModules(module.getLstAffectation(), page.genererLstInter(module.getLstAffectation())) );

			pw.println( "\t</body>\n" );
			pw.println( "</table>" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}


	private List<Intervenant> genererLstInter( List<Affectation> lstAffectation )
	{
		List<Intervenant> lst = new ArrayList<Intervenant>();

		for ( Affectation a : lstAffectation )
		{
			if ( !lst.contains(a.getIntervenant()) )
				lst.add(a.getIntervenant());
		}

		return lst;
	}


	private String creerTableauInfosModules ( List<Affectation> affectations , List<Intervenant> lst )
	{
		String[] tabTHead = { "Nom", "Prénom", "CM", "TD", "TP", "H tut", "REH", "SAE", "HP", "Total" };

		String res = "";
		String tab = "\t\t";
		double[] tmp;

		int[] tabTotalFinal = new int[8];


		res += tab + "<table class = \"infosModule\">\n";
		tab += "\t";

		res += tab + "<thead>\n";
		tab += "\t";


		res += tab + "<tr>\n";

		// GENERER THEAD
		for ( int cpt = 0; cpt < tabTHead.length; cpt++ )
			res += tab + "\t<th> " + tabTHead[cpt] + " </th>\n" ;

		res += tab + "</tr>\n";


		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</thead>\n\n";

		res += tab + "<tbody>\n";
		tab += "\t";


		// GENERER TBODY
		for ( int cpt = 0; cpt < lst.size(); cpt ++ )
		{
			res += tab + "<tr>\n";

			tmp = genererTabHeures( lst.get(cpt), affectations );

			res += tab + "\t<td> " + lst.get(cpt).getNom   () + " </td>\n";
			res += tab + "\t<td> " + lst.get(cpt).getPrenom() + " </td>\n";

			for ( int cptHeures = 0; cptHeures < tmp.length; cptHeures ++ )
			{
				res += tab + "\t<td> " + tmp[cptHeures] + " </td>\n";

				tabTotalFinal[cptHeures] += tmp[cptHeures];
			}

			res += tab + "\t<tr class = 'totalModule'> " + tmp[7] + " </td>\n";

			res += tab + "<\tr>\n";
		}


		res += tab + "<tr>\n";
		res += tab + "\t<td class = 'tdFinal' colspan = '2'> Total </td>";

		for ( int cptHeureTot = 0; cptHeureTot < tabTotalFinal.length - 1; cptHeureTot++ )
			res += tab + "\t<td class = 'tdFinal'> " + tabTotalFinal[cptHeureTot] + " </td>\n";

		res += tab + "\t<td class = 'totalFinal'> " + tabTotalFinal[7] + " </td>\n";
		res += tab + "</tr>\n";

		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</tbody>\n";
		tab = tab.substring(0, tab.length() - 1);

		res += tab + "</table>\n\n";

		return res;

	}


	private double[] genererTabHeures ( Intervenant inter, List<Affectation> lstAffectation )
	{
		double[]  tab = new double[8];
		double tmp;

		for ( int cpt = 0; cpt < 8; cpt ++ )
			tab[cpt] = 0;


		for ( Affectation a : lstAffectation )
		{
			if ( a.getIntervenant().equals(inter) )
			{
				if ( a.getNbHeure() != null )
					tmp = a.getNbHeure();
				else
					tmp = a.getNbGroupe() * a.getNbSemaine() * a.getCategorieHeure().getCoef() * a.getModule().getNbHeureSemaine(a.getCategorieHeure().getNom());


				switch ( a.getCategorieHeure().getNom())
				{
					case "CM"    -> { tab[0] += tmp; tab[7] += tmp; }
					case "TD"    -> { tab[1] += tmp; tab[7] += tmp; }
					case "TP"    -> { tab[2] += tmp; tab[7] += tmp; }
					case "HT"    -> { tab[3] += tmp; tab[7] += tmp; }
					case "REH"   -> { tab[4] += tmp; tab[7] += tmp; }
					case "HSAE"  -> { tab[5] += tmp; tab[7] += tmp; }
					case "HP"    -> { tab[6] += tmp; tab[7] += tmp; }
				}
			}

		}

		return tab;
	}


	/* -------------------------------- /
	/ -------------------------------- /
	/            GENERER HEAD          /
	/ -------------------------------- /
	/ -------------------------------- */
	private String creerHead( int type )
	{
		String res = "";
		String tab = "";


		res += "<!DOCTYPE html>   \n";
		res += "<html lang = 'fr'>\n";
		tab += "\t";

		res += tab + "<head>\n";
		tab += "\t";

		if ( type == 1 )
			res += tab + "<title> Fiche module </title>\n\n";
		else
			res += tab + "<title> Fiche intervenant </title>\n\n";

		res += tab + "<meta charset = 'utf-8'>\n";
		res += tab + "<meta content = 'Author' lang = 'fr' name = 'BOULOCHE Eleonore'>\n\n";

		res += tab + "<link rel = 'stylesheet' href = \"./style.css\" media = 'all' type = 'text/css'>\n";

		tab = tab.substring(0, tab.length() - 1);
		res += tab + "</head>\n";

		return res;
	}
}