package html;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import metier.model.Intervenant;
import metier.model.CategorieIntervenant;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;



public class GenererCSV
{
	private static int nbFichierCSV = 0;

	private List<Intervenant> lstInter;
	private String fichier;

	private double[] tabTotHeures;


	public GenererCSV ( List<Intervenant> lstInter )
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION)
		{
			GenererCSV.nbFichierCSV ++;

			this.lstInter = lstInter;
			this.fichier  = fileChooser.getSelectedFile().getAbsolutePath() + File.separator + "intervenant_V" + String.format( "%02d", GenererCSV.nbFichierCSV ) + ".csv";

			this.tabTotHeures = new double[18];

			for ( int cpt = 0; cpt < this.tabTotHeures.length; cpt ++ )
				this.tabTotHeures[cpt] = 0;

			this.genererFichier();
		}
	}


	private void genererFichier ()
	{
		String[] lstNomsCell = { "Catégorie" , "Nom"                , "Prénom"        ,
		                         "Service dû", "Max heures autorisées", "Coefficient TP",
		                         "S1 théoriques", "S1 réelles", "S3 théoriques" , "S3 réelles",
		                         "S5 théoriques", "S5 réelles", "Total Semestres Impairs",
		                         "S2 théoriques", "S2 réelles", "S4 théoriques" , "S4 réelles",
		                         "S6 théoriques", "S6 réelles", "Total Semestres Pair",
		                         "Total Semestres"};

		String res = "";


		try
		{
			File fichier = new File (this.fichier);

			// Vérifier si le fichier existe
			if ( fichier.exists() )
				fichier.delete();

			fichier.createNewFile();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fichier.getAbsoluteFile(), true), "UTF-8"));


			for ( int i = 0 ; i < lstNomsCell.length - 1 ; i++ )
				res += lstNomsCell[i] + ",";
			res += lstNomsCell[lstNomsCell.length - 1] + "\n";


			pw.print( res                     );
			pw.print( genererContenuFichier() );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}


	private String genererContenuFichier ()
	{
		String res = "";
		CategorieIntervenant catInter;

		for ( Intervenant inter : this.lstInter )
		{
			catInter = inter.getCategorie();

			// Obtenir la catégorie de l'intervenant
			res += catInter.getNom() + ",";

			// Obtenir le nom de l'intervenant et son prénom
			res += inter.getNom() + "," + inter.getPrenom() + ",";

			// Obtenir le service dû
			res += catInter.getMinH() + ",";

			// Obtenir le max d'heures autorisés
			res += catInter.getMaxH() + ",";
			this.tabTotHeures[0] += catInter.getMaxH();

			// Obtenir le coeff TP
			res += catInter.getCoefTp() + ",";
			this.tabTotHeures[1] += catInter.getCoefTp();


			// Nombre d'heures par SEMESTRES IMPAIRS
			for ( int cptSemestres = 1; cptSemestres < 6; cptSemestres += 2 )
			{
				res += inter.getHParSemestre(cptSemestres) + ",";
				res += inter.getHParSemestre(cptSemestres) * catInter.getCoefTp() + ",";

				this.tabTotHeures[cptSemestres + 1] += inter.getHParSemestre(cptSemestres);
				this.tabTotHeures[cptSemestres + 2] += inter.getHParSemestre(cptSemestres) * catInter.getCoefTp();
			}


			// Nombre d'heures total par SEMESTRES IMPAIRS
			res += inter.getTotalParImpair() + ",";
			this.tabTotHeures[8] += inter.getTotalParImpair();


			// Nombre d'heures par SEMESTRES PAIRS
			for ( int cptSemestres = 2; cptSemestres <= 6; cptSemestres += 2 )
			{
				res += inter.getHParSemestre(cptSemestres) + ",";
				res += inter.getHParSemestre(cptSemestres) * catInter.getCoefTp() + ",";

				this.tabTotHeures[cptSemestres + 6]+= inter.getHParSemestre(cptSemestres);
				this.tabTotHeures[cptSemestres + 7] += inter.getHParSemestre(cptSemestres) * catInter.getCoefTp();
			}

			// Nombre d'heures total par SEMESTRES PAIRS
			res += inter.getTotalParPair() + ",";
			this.tabTotHeures[14] += inter.getTotalParPair();

			res += inter.getTotal() + "\n";
			this.tabTotHeures[15] += inter.getTotal();
		}


		res += "Total,,,";

		for ( int cpt = 0; cpt < this.tabTotHeures.length; cpt++ )
			res += this.tabTotHeures[cpt] + ",";

		return res;
	}

}