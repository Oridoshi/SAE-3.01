import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenererPage
{
	private int[]  tabSemestre;
	private String fichier;


	private GenererPage ( int choix, Intervenant inter, Module module )
	{
		if ( choix == 1 )
			this.fichier = inter.getNom() + "_" + inter.getPrenom() + ".txt";
		else
			this.fichier = module.getCode() + "_" + module.getNom() + ".txt";


		this.tabTotalHeures = new int[8];

		for ( int cpt = 0; cpt < this.tabTotalHeures.length; cpt ++ )
		{
			this.tabTotalHeures[cpt] = 0;
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
		new GenererPage(1, inter, null);


		try
		{
			File fichier = new File (this.fichier);

			// Vérifier si le fichier existe
			if ( !fichier.exists() )
				fichier.createNewFile();

			// Création d'un PrintWriter pour écrire dans le fichier
			PrintWriter pw = new PrintWriter(new FileWriter(fichier.getAbsoluteFile(), true));

			pw.println( creerHead() + '\n' );

			pw.println( "\t<body>" );
			pw.println( "\t\t<h1> Fiche de l'intervenant <b>" + inter.getNom() + "</b> </h1>\n" );


			for ( int cptNbSemestres = 0; cptNbSemestres < 3; cptNbSemestres ++ )
				pw.print( creerTableauSemestres( cptNbSemestres, this.genererLstAffectations(cptNbSemestres + 1, inter) ) );

			pw.println( "\n\t\t<hr>\n\n" );

			pw.println( creerTableauRecapitulatif() );
			pw.println( "\t</body>\n" );
			pw.println( "</table>" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}


	private String creerTableauSemestres ( int semestre, List<Affectation> lstAffectation )
	{
		String res = "";
		String tab = "";
		String tmp;
		int    tmpNb;

		String[] tabNomEnTete    = { "Code du module", "Nom du module", "CM", "TD", "TP", "H tut", "REH", "SAÉ", "HP", "Total" };
		String[] tabClasseEnTete = { null, "nomModule", "heure", "heure", "heure", "heure", "heure", "heure", "heure", "total" };

		tabTotalHeures = new int[7];

		for ( int cptHeures = 0; cptHeures < tabTotalHeures.length; cptHeures ++ )
			tabTotalHeures[cptHeures] = 0;


		res += tab + "<section>\n";
		tab += "\t";

		res += tab + "<h2> Semestre " + semestre + " </h2>\n\n\n";
		tab += "\t";

		res += tab + "<table class = 'infosIntervenants'>\n\n";
		tab += "\t";

		res += tab + "<thead>\n";
		res += tab + "\t<tr>\n";
		tab += "\t\t";


		for ( int cptEnTete = 0; cptEnTete < tabNomEnTete.length; cptEnTete ++ )
		{
			tmp = (tabClasseEnTete[cptEnTete] == null) ? "" : "class = '" + tabClasseEnTete[cptEnTete] + "'";

			res += tab + "<tr " + String.format("%-19s", tmp)      + "> " +
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
			res += tab + "<tr>\n";

			res += tab + "\t<td> " + a.getModule().getCode()        + " </td>\n";
			res += tab + "\t<td> " + a.getModule().getLibelleLong() + " </td>\n";

			for ( int cptCell = 2; cptCell < 8; cptCell ++ )
			{
				tmpNb = hashNbHeures.getKey(a.getModule)[cptCell];

				res += tab + "\t<td> " + tmpNb + " </td>\n";

				tabTotalHeures  [cptCell   ] += tmpNb;
				this.tabSemestre[semestre-1] += tmpNb;
			}

			res += tab + "\t<td class = 'total'> " + hashNbHeures.getKey(a.getModule)[8] +  " </td>\n";
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


	private String creerTableauRecapitulatif ()
	{
		String res = "";
		String tab = "\t\t";

		String tmp;



		// PREMIER TABLEAU : RECAPITULATIF DE CHAQUE SEMESTRES

		res += tab + "<h2> Récapitulatif </2>\n\n";
		res += tab + "<table class = 'statsIntervenants tab1'>\n\n";
		tab += '\t';

		res += tab + "<thead>";
		tab += '\t';

		res += tab + "<tr>\n";

		res += tab + "\t<th class = "statsS"   > Semestre        </th>\n";
		res += tab + "\t<th class = "statsStot"> Nombre d'heures </th>\n";

		res += tab + "</tr>\n";
		tab  = substring(0, tab.length() - 1);

		res += tab + "</thead>\n\n";

		res += tab + "<tbody>\n";
		tab += "\t";

		for ( int cptNbSemestres = 0; cptNbSemestres < this.tabSemestre.length; cptNbSemestres++ )
		{
			res += tab + "<tr>\n";
			res += tab + "\t<td> S" + cptNbSemestres + 1    + " </td>\n";
			res += tab + "\t<td> "  + this.tabSemestre[cpt] + " </td>\n";
			res += tab + "</tr>\n\n";
		}

		res += tab + "<tr class = 'statsTotal'>\n";
		res += tab + "\t<td> <b>TOTAL</b> </td>\n";
		res += tab + "\t<td> <b>"  + inter.getTotal() + "</b> </td>\n";
		res += tab + "</tr>\n";

		tab = substring(0, tab.length() - 1);

		res += "</tbody>\n\n";
		tab  = substring(0, tab.length() - 1);

		res += "</table>\n\n\n";




		// DEUXIEME TABLEAU : RECAPITULATIF DU S1 S3 S5 et S2 S4 S6
		res += tab + "<table class = 'statsIntervenants tab2'>\n\n";
		tab += "\t";

		res += tab + "<thead>\n";
		tab += "\t";

		res += tab + "<tr>\n";
		res += tab + "\t<th class = "statsS"   > Semestre        </th>\n";
		res += tab + "\t<th class = "statsStot"> Nombre d'heures </th>\n";
		res += tab + "</tr>\n";

		tab = substring(0, tab.lenth() - 1);

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


		tab = substring(0, tab.length() - 1);
		res += tab + "</tbody>\n\n";

		tab = substring(0, tab.length() - 1);
		res += tab + "</table>\n";

		tab = substring(0, tab.length() - 1);
		res += tab + "</section>\n";
	}


	private List<Affectation> genererLstAffectations ( int semestre, List<Affectation> lstAffectation )
	{
		List<Affectation> lst = new ArrayList<Affectation>();

		for ( Affectation a : lstAffectation )
			( if a.getModule.getSemestre() == semestre )
				lst.add(a);


		return lst;
	}



	/* -------------------------------- */
	/* -------------------------------- */
	/*        GENERER PAGE MODULE       */
	/* -------------------------------- */
	/* -------------------------------- */

	public static void genererPageModule ( Module module )
	{
		new GenererPage(2, null, module);

		try
		{
			File fichier = new File (this.fichier);

			// Vérifier si le fichier existe
			if ( !fichier.exists() )
				fichier.createNewFile();

			// Création d'un PrintWriter pour écrire dans le fichier
			PrintWriter pw = new PrintWriter(new FileWriter(fichier.getAbsoluteFile(), true));

			pw.println( creerHead() + '\n' );

			pw.println( "\t<body>" );
			pw.println( "\t\t<h1> " + module.getCode() + " " + module.getNom() + " </h1>\n" );


			for ( int cptNbSemestres = 0; cptNbSemestres < 3; cptNbSemestres ++ )
				pw.print( creerTableauInfosModules( cptNbSemestres, this.genererLstAffectations(cptNbSemestres + 1, inter) ) );

			pw.println( "\n\t\t<hr>\n\n" );

			pw.println( creerTableauRecapitulatif() );
			pw.println( "\t</body>\n" );
			pw.println( "</table>" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}


	private String creerTableauInfosModules ( List<Affectation>, List<Intervenant> lst )
	{
		String[] tabTHead = { "Nom", "Prénom", "CM", "TD", "TP", "H tut", "REH", "SAE", "HP" };

		String res = "";
		String tab = "\t\t";
		int[] tmp;


		res += tab + "<table class = "infosModule">\n";
		tab += "\t";

		res += tab + "<thead>\n";
		tab += "\t";


		res += tab + "<tr>\n";

		// GENERER THEAD
		for ( int cpt = 0; cpt < tabTHead.length; cpt++ )
			res += tab + "\t<th> " + tabTHead[cpt] + " </th>\n" ;

		res += tab + "</tr>\n";


		tab = substring(0, tab.length() - 1);

		res += tab + "</thead>\n\n";

		res += tab + "<tbody>\n";
		tab += "\t";


		// GENERER TBODY
		for ( int cpt = 0; cpt < lst.length; cpt ++ )
		{
			res += tab + "<tr>\n";

			tmp = genererTabHeures( lst.get(cpt), affectations );

			res += tab + "\t<td> " + lst.get(cpt).getIntervenant().getNom   () + " </td>\n";
			res += tab + "\t<td> " + lst.get(cpt).getIntervenant().getPrenom() + " </td>\n";

			for ( int cptHeures = 0; cptHeures < tmp.length; cptHeures ++ )
				res += tab + "\t<td> " + tmp.get(cpt) + " </td>\n";

			res += tab + "<\tr>\n";
		}

		tab = substring(0, tab.length() - 1);

		res += tab + "</tbody>\n";
		tab = substring(0, tab.length() - 1);

		res += tab + "</table>\n\n";

		return res;

	}


	private int[] genererTabHeures ( Intervenant inter, List<Affectation> lstAffectation )
	{
		int[] tab = new int[7];

		for ( int cpt = 0; cpt < 7; cpt ++ )
			tab[cpt] = 0;

		for ( Affectation a : lstAffectation )
		{

			if ( a.getIntervenant.equals(inter) )
			{
				switch ( a.getCategorieHeure().getNom() ) : 
					case "CM"    -> tab[0] = a.getNbHeure();
					case "TD"    -> tab[1] = a.getNbHeure();
					case "TP"    -> tab[2] = a.getNbHeure();
					case "H tut" -> tab[3] = a.getNbHeure();
					case "REH"   -> tab[4] = a.getNbHeure();
					case "SAE"   -> tab[5] = a.getNbHeure();
					case "HP"    -> tab[6] = a.getNbHeure();
			}

		}

		return tab;
	}
}
