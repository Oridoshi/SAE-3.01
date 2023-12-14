package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import controleur.Controleur;
import metier.model.Intervenant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.concurrent.Flow;


public class PageIntervenants extends JPanel implements ActionListener, ListSelectionListener
{
	private Controleur ctrl;

	private JPanel panelBoutons;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private JPanel panelTableau;

	private JTable tableIntervenants;
	private ListSelectionModel selectionModel;

	private JScrollPane spTableauIntervenants;
	private JPanel panelBoutonsTableau;
	private JButton btnAjouter;
	private JButton btnSupprimer;

	private FrameIhm mere;


	public PageIntervenants(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());

		this.setBorder(new EmptyBorder(15, 30, 15, 30));
		this.mere.setTitle("Intervenants");



		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.panelBoutons.setBorder(new EmptyBorder(30, 0, 0, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");



		/*------------------*/
		//   panelTableau   //
		/*------------------*/
		this.panelTableau = new JPanel(new BorderLayout());
		this.panelTableau.setBorder(new EmptyBorder(0, 0, 15, 0));

		// Ajout de la table
		this.tableIntervenants = new JTable( new ModelAffichageTableau(this.ctrl, this.ctrl.getLstIntervenants()) );
		this.tableIntervenants.setFillsViewportHeight(true);
		this.tableIntervenants.setRowHeight(25);
		TableColumnModel model = this.tableIntervenants.getColumnModel();

		for (int i = 3; i <= 14; i++)
		{
			(model.getColumn(i)).setPreferredWidth(5);
		}

		spTableauIntervenants = new JScrollPane(this.tableIntervenants);




		this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.panelBoutonsTableau.setBorder(new EmptyBorder(15, 0, 0, 0));

		this.btnAjouter = new JButton("ajouter");
		this.btnSupprimer = new JButton("supprimer");
		this.btnSupprimer.setEnabled(false); // Désactiver le bouton au démarrage

		this.panelBoutonsTableau.add(this.btnAjouter);
		this.panelBoutonsTableau.add(this.btnSupprimer);



		this.panelTableau.add(this.spTableauIntervenants, BorderLayout.CENTER);
		this.panelTableau.add(this.panelBoutonsTableau, BorderLayout.SOUTH);
		this.add(this.panelTableau, BorderLayout.CENTER);

		this.selectionModel = this.tableIntervenants.getSelectionModel();
		this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.selectionModel.addListSelectionListener(this);
		/*------------------*/


		// Ajout des boutons
		this.add(panelBoutons, BorderLayout.SOUTH);
		this.panelBoutons.add(this.btnEnregistrer);
		this.panelBoutons.add(this.btnAnnuler);


		this.btnAnnuler.addActionListener(this);
	}



	private class ModelAffichageTableau extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;
		private String[] tabEntetes;

		public ModelAffichageTableau(Controleur ctrl, List<Intervenant> lstIntervenants)
		{
			this.ctrl = ctrl;

			int nbCol = 15;
			int nbLig = lstIntervenants.size();

			this.tabDonnees = new Object[nbLig][nbCol];

			for (int ligne = 0; ligne < nbLig; ligne++)
			{
				tabDonnees[ligne][0] = lstIntervenants.get(ligne).getCategorie().getNom();
				tabDonnees[ligne][1] = lstIntervenants.get(ligne).getNom();
				tabDonnees[ligne][2] = lstIntervenants.get(ligne).getPrenom();
				tabDonnees[ligne][3] = lstIntervenants.get(ligne).getHMin();
				tabDonnees[ligne][4] = lstIntervenants.get(ligne).gethMax();
				tabDonnees[ligne][5] = lstIntervenants.get(ligne).getCoefTP();
				tabDonnees[ligne][6] = lstIntervenants.get(ligne).getHParSemestre(1);
				tabDonnees[ligne][7] = lstIntervenants.get(ligne).getHParSemestre(3);
				tabDonnees[ligne][8] = lstIntervenants.get(ligne).getHParSemestre(5);
				tabDonnees[ligne][9] = lstIntervenants.get(ligne).getTotalParImpair();
				tabDonnees[ligne][10] = lstIntervenants.get(ligne).getHParSemestre(2);
				tabDonnees[ligne][11] = lstIntervenants.get(ligne).getHParSemestre(4);
				tabDonnees[ligne][12] = lstIntervenants.get(ligne).getHParSemestre(6);
				tabDonnees[ligne][13] = lstIntervenants.get(ligne).getTotalParPair();
				tabDonnees[ligne][14] = lstIntervenants.get(ligne).getTotal();
				
				this.tabEntetes = new String[]{"Catégorie", "Nom", "Prénom", "hServ", "hMax", "Coef TP", "S1", "S3", "S5", "sTot", "S2", "S4", "S6", "sTot", "sTotal"};
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
		public String getColumnName (int col)                  {return this.tabEntetes[col];}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}



	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && this.tableIntervenants.getSelectedRow() != -1) {
			this.btnSupprimer.setEnabled(true); // Activer le bouton
		} else {
			this.btnSupprimer.setEnabled(false); // Désactiver le bouton si aucune ligne n'est sélectionnée
		}
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAnnuler)
		{
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
	}
}


