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

			this.genererFichier();
		}
	}


	private void genererFichier ()
	{
		String[] lstNomsCell = { "Catégorie"    , "Nom"                         , "Prénom"        ,
		                         "Service dû"   , "Max heures autorisées"       , "Coefficient TP",
		                         "S1 théoriques", "S1 réelles", "S3 théoriques" , "S3 réelles",
		                         "S5 théoriques", "S5 réelles", "Total Semestres Impairs",
		                         "S2 théoriques", "S2 réelles", "S4 théoriques" , "S4 réelles",
		                         "S6 théoriques", "S6 réelles", "Total Semestres Pair",
		                         "Total Semestres"};

		String res = "";


		try
		{
			// Créer un objet File avec le chemin du fichier
			File fichier = new File(this.fichier);

			// Créer le dossier parent s'il n'existe pas
			fichier.getParentFile().mkdirs();

			// Créer le fichier s'il n'existe pas
			if (!fichier.exists())
			{
				fichier.createNewFile();
			}
			else
			{
				fichier.delete();
				fichier.createNewFile();
			}

			// Créer le PrintWriter avec le fichier et l'encodage UTF-8
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fichier, true), "UTF-8"));


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

			// Obtenir le coeff TP
			res += inter.getCoefTP() + ",";


			// Nombre d'heures par SEMESTRES IMPAIRS
			for ( int cptSemestres = 1; cptSemestres < 6; cptSemestres += 2 )
			{
				res += inter.getHParSemestre(cptSemestres) + ",";
				res += String.format("%.02f",(inter.getHParSemestre(cptSemestres) * catInter.getCoefTp())).replace(",", ".") + ",";
			}


			// Nombre d'heures total par SEMESTRES IMPAIRS
			res += inter.getTotalParImpair() + ",";


			// Nombre d'heures par SEMESTRES PAIRS
			for ( int cptSemestres = 2; cptSemestres <= 6; cptSemestres += 2 )
			{
				res += inter.getHParSemestre(cptSemestres) + ",";
				res += String.format("%.02f",(inter.getHParSemestre(cptSemestres) * catInter.getCoefTp())).replace(",", ".") + ",";
			}

			// Nombre d'heures total par SEMESTRES PAIRS
			res += inter.getTotalParPair() + ",";

			res += inter.getTotal() + "\n";
		}

		return res;
	}

}