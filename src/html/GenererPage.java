import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenererPage
{
	private int[]  tabSemestre;
	private String fichier;


	public GenererPage ( int choix )
	{
		if ( choix == 1 )
			this.fichier = "intervenants.txt";
		else
			this.fichier = "modules.txt";


		this.tabTotalHeures = new int[8];

		for ( int cpt = 0; cpt < this.tabTotalHeures.length; cpt ++ )
		{
			this.tabTotalHeures[cpt] = 0;
			this.tabSemestre   [cpt] = 0;
		}


		System.out.println( creerTableauHTML(1) );

		// genererPageInter();
	}


	public void genererPageInter ()
	{
		try
		{
			File fichier = new File ("intervenant.html");

			// Vérifier si le fichier existe
			if ( !fichier.exists() )
				fichier.createNewFile();

			// Création d'un PrintWriter pour écrire dans le fichier
			PrintWriter pw = new PrintWriter(new FileWriter(fichier.getAbsoluteFile(), true));

			for ( int cptNbSemestres = 0; cptNbSemestres < 3; cptNbSemestres ++ )
				pw.print( creerTableauSemestres(1, cptNbSemestres) );

			pw.println( "\n\t\t<hr>\n\n" );

			pw.print()

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}


	private String creerTableauSemestres ( int semestre, int nbCompetencesSemestre )
	{
		String res = "";
		String tab = "";
		String tmp;

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

		res += tab + "<thead>\n" + tab + "\t<tr>\n";
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

		for ( int cptComp = 0, cptComp < 7; cptComp ++ )
		{
			res += tab + "<tr>\n";

			res += tab + "\t<td> " + N00 + " </td>\n";
			res += tab + "\t<td> " + N01 + " </td>\n";

			for ( int cptCell = 2; cptCell < 8; cptCell ++ )
			{
				res += tab + "\t<td> " + N[cptCell] + " </td>\n";

				tabTotalHeures[cptCell] += N[cptCell];
				this.tabSemestre[semestre-1] += N[cptCell];
			}

			res += tab + "\t<td class = 'total'> " + N[8] +  " </td>\n";
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

		int totalHeures = 0;

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

			totalHeures += this.tabSemestre[cpt];
		}

		res += tab + "<tr class = 'statsTotal'>\n";
		res += tab + "\t<td> <b>TOTAL</b> </td>\n";
		res += tab + "\t<td> <b>"  + totalHeures + "</b> </td>\n";
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


		for ( int cpt = 1; cpt <= 2; cpt ++ )
		{
			tmp = " S" + cpt + " S" + (cpt+2) + " S" + (cpt+4);

			res += tab + "<tr>\n";
			res += tab + "\t<td>" + tmp + " </td>\n";
			res += tab + "\t<td> " + this.tabSemestre[cpt] + this.tabSemestre[cpt+2] + this.tabSemestre[cpt+3] + " </td>\n";
			res += tab + "</tr>\n\n";
		}


		res += tab + "<tr class = 'statsTotal'>\n";
		res += tab + "\t<td> <b>TOTAL</b> </td>\n";
		res += tab + "\t<td> <b>" + totalHeures + "</b> </td>\n";
		res += tab + "</tr>\n";


		tab = substring(0, tab.length() - 1);
		res += tab + "</tbody>\n\n";

		tab = substring(0, tab.length() - 1);
		res += tab + "</table>\n";

		tab = substring(0, tab.length() - 1);
		res += tab + "</section>\n";
	}


	public static void main (String[] a)
	{
		GenererPage g = new GenererPage(1);
	}

}
