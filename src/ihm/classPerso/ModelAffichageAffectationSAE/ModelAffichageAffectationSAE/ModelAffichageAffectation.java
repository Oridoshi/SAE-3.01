package ihm.classPerso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import controleur.Controleur;
import metier.ModuleResource;

public class ModelAffichageAffectation extends AbstractTableModel
{

	private Controleur ctrl;

	private String[] tabEntetes;
	private Object[][] tabDonnees;

	public ModelAffichageAffectation(Controleur ctrl, ArrayList<ModuleResource> lstRessource)
	{
		this.ctrl = ctrl;

		int nbCol = this.ctrl.getNbCol(table);
		int nbLig = this.ctrl.getNbTuple(table);

		this.tabDonnees = new Object[nbLig][nbCol];

		String[] tabInfo = this.ctrl.getTupleTable(table).split(":");

		String[] tabInfoActu;

		for (int ligne = 0; ligne < nbLig; ligne++)
		{
			tabInfoActu = tabInfo[ligne].split(",");

			for (int val = 0; val < nbCol; val++)
			{
				tabDonnees[ligne][val] = tabInfoActu[val];
			}
		}
		
		this.tabEntetes = ctrl.getNomCol(table);
	}

	public int getRowCount()                               { return this.tabDonnees.length; }
	public String getColumnName (int col)                  { return this.tabEntetes[col]; }
	public int getColumnCount()                            { return this.tabEntetes.length; }
	public Object getValueAt(int rowIndex, int columnIndex){ return this.tabDonnees[rowIndex][columnIndex]; }

}