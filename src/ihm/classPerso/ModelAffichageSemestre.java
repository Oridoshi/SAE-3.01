package ihm.classPerso;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import controleur.Controleur;
import metier.ModuleResource;

/**
 * <h4>ModelAffichageSemestre</h4>
 * <p>Classe de création du model pour l'affichage des semestres</p>
 */
public class ModelAffichageSemestre extends AbstractTableModel
{
	private Controleur ctrl;

	private Object[][] tabDonnees;

	public ModelAffichageSemestre(Controleur ctrl, ArrayList<ModuleResource> lstRessource)
	{
		this.ctrl = ctrl;

		int nbCol = 4;
		int nbLig = lstRessource.size();

		this.tabDonnees = new Object[nbLig][nbCol];

		for (int ligne = 0; ligne < nbLig; ligne++)
		{
			tabDonnees[ligne][0] = lstRessource.get(ligne).getCode();
			tabDonnees[ligne][1] = lstRessource.get(ligne).getNom();
			tabDonnees[ligne][2] = lstRessource.get(ligne).getTotalAffecteEqTd() + "/" + lstRessource.get(ligne).getTotalPromoEqTd();
			tabDonnees[ligne][3] = lstRessource.get(ligne).getValider();
		}
	}

	public int getRowCount()                                {return this.tabDonnees.length;}
	public int getColumnCount()                             {return this.tabDonnees[0].length;}
	public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}

}