package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import metier.model.Intervenant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.concurrent.Flow;


public class PageIntervenants extends JPanel
{
	private Controleur ctrl;

	private JPanel panelBoutons;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private JPanel panelTableau;
	private JTable tableIntervenants;
	private JScrollPane spTableauIntervenants;
	private JPanel panelBoutonsTableau;
	private JButton btnAjouter;
	private JButton btnSupprimer;

	private JFrame mere;


	public PageIntervenants(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());

		this.setBorder(new EmptyBorder(15, 30, 15, 30));
		this.mere.setTitle("Intervenants");



		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		// Ajout du titre
		this.add(new JLabel("Liste des intervenants"), BorderLayout.NORTH);



		/*------------------*/
		//   panelTableau   //
		/*------------------*/
		this.panelTableau = new JPanel(new BorderLayout());

		// Ajout de la table
		this.tableIntervenants = new JTable( new ModelAffichageTableau(this.ctrl, this.ctrl.getLstIntervenants()) );
			this.tableIntervenants.setFillsViewportHeight(true);
			this.tableIntervenants.setRowHeight(25);
			this.tableIntervenants.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableIntervenants.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête

			spTableauIntervenants = new JScrollPane(this.tableIntervenants);




		this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.setBorder(new EmptyBorder(0, 0, 30, 0));

		this.btnAjouter = new JButton("ajouter");
		this.btnAjouter = new JButton("supprimer");

		this.panelBoutonsTableau.add(this.btnAjouter);
		this.panelBoutonsTableau.add(this.btnSupprimer);



		this.panelTableau.add(this.spTableauIntervenants, BorderLayout.CENTER);
		this.panelTableau.add(this.panelBoutonsTableau, BorderLayout.SOUTH);
		this.add(this.panelTableau, BorderLayout.CENTER);
		/*------------------*/


		// Ajout des boutons
		this.add(panelBoutons, BorderLayout.SOUTH);
		this.panelBoutons.add(this.btnEnregistrer);
		this.panelBoutons.add(this.btnAnnuler);
	}



	private class ModelAffichageTableau extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableau(Controleur ctrl, ArrayList<Intervenant> lstIntervenants)
		{
			this.ctrl = ctrl;

			int nbCol = 15;
			int nbLig = lstIntervenants.size();

			this.tabDonnees = new Object[nbLig][nbCol];

			for (int ligne = 0; ligne < nbLig; ligne++)
			{
				tabDonnees[ligne][0] = lstIntervenants.get(ligne).getCategorie();
				tabDonnees[ligne][1] = lstIntervenants.get(ligne).getNom();
				tabDonnees[ligne][2] = lstIntervenants.get(ligne).getPrenom();
				//tabDonnees[ligne][3] = lstIntervenants.get(ligne).getHServ();
				// ...
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// Spécifiez le type de données pour chaque colonne
			switch (columnIndex) {
				case 0: // Colonne 0
					return String.class;
				case 1: // Colonne 1
					return String.class;
				case 2: // Colonne 2
					return String.class;
				case 3: // Colonne 3
					return Integer.class;
				case 4: // Colonne 3
					return Integer.class;
				default:
					return Double.class;
			}
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return this.tabDonnees[0].length;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}


