package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import metier.model.CategorieHeure;
import metier.model.CategorieIntervenant;
import metier.model.Intervenant;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class PageParametres extends JPanel implements ActionListener
{
	private Controleur ctrl;



	private JPanel panelBoutons;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private FrameIhm mere;
	private JTabbedPane tabbedPane;

	public PageParametres(Controleur ctrl, FrameIhm mere) 
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.mere.setTitle("Paramètres");
		this.setBorder(new EmptyBorder(15, 30, 15, 30));

		// Ajout du tabbedPane
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.setBorder(new EmptyBorder(0, 0, 15, 0));
		this.tabbedPane.addTab("Catégories d'intervenants", new PanelCategoriesIntervenant(ctrl, this.ctrl.getLstCategorieIntervenant()));
		this.tabbedPane.addTab("Catégories d'heures", new PanelCategoriesHeure(ctrl, this.ctrl.getLstCategorieHeure()));
		
		this.add(this.tabbedPane, BorderLayout.CENTER);


		// Ajout du panel de boutons général
		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		// Ajout des boutons
		this.add(panelBoutons, BorderLayout.SOUTH);
		this.panelBoutons.add(this.btnEnregistrer);
		this.panelBoutons.add(this.btnAnnuler);

		// Ajout des listeners
		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnEnregistrer)
		{
			// TODO
		}
		
		if (e.getSource() == this.btnAnnuler)
		{
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
	}


	/*---------------------------*/
	//   Catégorie Intervenant   //
	/*---------------------------*/
	private class PanelCategoriesIntervenant extends JPanel implements ActionListener
	{
		private Controleur ctrl;
		private ArrayList<CategorieIntervenant> lstCategorieIntervenant;

		private JPanel panelBoutonsTableau;
		private JButton btnAjouter;
		private JButton btnSupprimer;

		private JTable tableCategorieIntervenant;
		private JScrollPane spTableauCategorieIntervenant;

		public PanelCategoriesIntervenant(Controleur ctrl, ArrayList<CategorieIntervenant> lstCategorieIntervenant)
		{
			this.ctrl = ctrl;
			this.lstCategorieIntervenant = lstCategorieIntervenant;

			this.setLayout(new BorderLayout());

			this.tableCategorieIntervenant = new JTable( new ModelAffichageTableauIntervenant(this.ctrl, lstCategorieIntervenant));
			this.tableCategorieIntervenant.setFillsViewportHeight(true);
			this.tableCategorieIntervenant.setRowHeight(25);
			this.tableCategorieIntervenant.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableCategorieIntervenant.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête

			spTableauCategorieIntervenant = new JScrollPane(this.tableCategorieIntervenant);
			this.add(this.spTableauCategorieIntervenant, BorderLayout.CENTER);
			

			// Ajout des boutons
			this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
			this.panelBoutonsTableau.setBorder(new EmptyBorder(5, 0, 5, 0));

			this.btnAjouter = new JButton("ajouter");
			this.btnSupprimer = new JButton("supprimer");

			this.panelBoutonsTableau.add(this.btnAjouter);
			this.panelBoutonsTableau.add(this.btnSupprimer);

			this.add(this.panelBoutonsTableau, BorderLayout.SOUTH);


			// Ajout des listeners
			this.btnAjouter.addActionListener(this);
			this.btnSupprimer.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
		}
	}




	/*---------------------------*/
	//   Catégorie Heures        //
	/*---------------------------*/
	private class PanelCategoriesHeure extends JPanel implements ActionListener
	{
		private Controleur ctrl;
		private ArrayList<CategorieHeure> lstCategorieHeure;

		private JPanel panelBoutonsTableau;
		private JButton btnAjouter;
		private JButton btnSupprimer;

		private JTable tableCategorieHeure;
		private JScrollPane spTableauCategorieHeure;

		public PanelCategoriesHeure(Controleur ctrl, ArrayList<CategorieHeure> lstCategorieHeure)
		{
			this.ctrl = ctrl;
			this.lstCategorieHeure = lstCategorieHeure;

			this.setLayout(new BorderLayout());

			this.tableCategorieHeure = new JTable( new ModelAffichageTableauHeure(this.ctrl, lstCategorieHeure));
			this.tableCategorieHeure.setFillsViewportHeight(true);
			this.tableCategorieHeure.setRowHeight(25);
			this.tableCategorieHeure.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableCategorieHeure.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête

			spTableauCategorieHeure = new JScrollPane(this.tableCategorieHeure);
			this.add(this.spTableauCategorieHeure, BorderLayout.CENTER);
			

			// Ajout des boutons
			this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
			this.panelBoutonsTableau.setBorder(new EmptyBorder(5, 0, 5, 0));

			this.btnAjouter = new JButton("ajouter");
			this.btnSupprimer = new JButton("supprimer");

			this.panelBoutonsTableau.add(this.btnAjouter);
			this.panelBoutonsTableau.add(this.btnSupprimer);

			this.add(this.panelBoutonsTableau, BorderLayout.SOUTH);


			// Ajout des listeners
			this.btnAjouter.addActionListener(this);
			this.btnSupprimer.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
		}
	}







	private class ModelAffichageTableauIntervenant extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableauIntervenant(Controleur ctrl, ArrayList<CategorieIntervenant> lstCategorieIntervenant)
		{
			this.ctrl = ctrl;

			int nbCol = 5;
			int nbLig = lstCategorieIntervenant.size();

			this.tabDonnees = new Object[nbLig][nbCol];

			for (int ligne = 0; ligne < nbLig; ligne++)
			{
				tabDonnees[ligne][0] = lstCategorieIntervenant.get(ligne).getCode();
				tabDonnees[ligne][1] = lstCategorieIntervenant.get(ligne).getNom();
				tabDonnees[ligne][2] = lstCategorieIntervenant.get(ligne).getMinH();
				tabDonnees[ligne][3] = lstCategorieIntervenant.get(ligne).getMaxH();
				tabDonnees[ligne][4] = lstCategorieIntervenant.get(ligne).getCoefTp();
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
					return Integer.class;
				case 3: // Colonne 3
					return Integer.class;
				case 4: // Colonne 3
					return Double.class;
				default:
					return Object.class;
			}
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return this.tabDonnees[0].length;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}


	private class ModelAffichageTableauHeure extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableauHeure(Controleur ctrl, ArrayList<CategorieHeure> lstCategorieHeure)
		{
			this.ctrl = ctrl;

			int nbCol = 2;
			int nbLig = lstCategorieHeure.size();

			this.tabDonnees = new Object[nbLig][nbCol];

			for (int ligne = 0; ligne < nbLig; ligne++)
			{
				tabDonnees[ligne][0] = lstCategorieHeure.get(ligne).getNom();
				tabDonnees[ligne][1] = lstCategorieHeure.get(ligne).getCoef();
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// Spécifiez le type de données pour chaque colonne
			switch (columnIndex) {
				case 0: // Colonne 0
					return String.class;
				case 1: // Colonne 1
					return Double.class;
				default:
					return Object.class;
			}
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return this.tabDonnees[0].length;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}

}