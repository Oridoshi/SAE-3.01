package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import ihm.creationObjet.PageCreaCategorieHeure;
import ihm.creationObjet.PageCreaCategorieIntervenant;
import metier.model.CategorieHeure;
import metier.model.CategorieIntervenant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageParametres extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JPanel panelBoutons;

	private JButton btnEnregistrer;
	private JButton btnAnnuler;
	private JButton btnAcceuil;

	private FrameIhm mere;
	private JTabbedPane tabbedPane;

	private PanelCategoriesIntervenant panelCateIntervenant;
	private PanelCategoriesHeure panelCateHeur;


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
		this.panelCateIntervenant = new PanelCategoriesIntervenant(ctrl, this.mere, this.ctrl.getLstCategorieIntervenant());
		this.tabbedPane.addTab("Catégories d'intervenants", panelCateIntervenant);
		this.panelCateHeur = new PanelCategoriesHeure(ctrl, this.mere, this.ctrl.getLstCategorieHeure());
		this.tabbedPane.addTab("Catégories d'heures", this.panelCateHeur);
		
		this.add(this.tabbedPane, BorderLayout.CENTER);


		// Config du panel de boutons général
		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		// Config du panel acceuil
		JPanel panelAccueil = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.btnAcceuil = new JButton("Accueil");
		panelAccueil.add(this.btnAcceuil);

		// Ajout des boutons
		this.add(panelBoutons, BorderLayout.SOUTH);
		this.add(panelAccueil, BorderLayout.NORTH);
		this.panelBoutons.add(this.btnEnregistrer);
		this.panelBoutons.add(this.btnAnnuler);

		// Ajout des listeners
		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler.addActionListener(this);
		this.btnAcceuil.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnEnregistrer)
		{
			this.ctrl.sauvegarder();
			this.panelCateIntervenant.setLstInitial();
			// this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
		
		if (e.getSource() == this.btnAnnuler)
		{
			this.ctrl.annuler();
			this.panelCateHeur.refreshHeure();
			this.panelCateIntervenant.resetLst();
			// this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}

		if (e.getSource() == this.btnAcceuil)
		{
			this.ctrl.annuler();
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
	}


	/*---------------------------*/
	//   Catégorie Intervenant   //
	/*---------------------------*/
	private class PanelCategoriesIntervenant extends JPanel implements ActionListener, ListSelectionListener
	{
		private Controleur ctrl;
		private FrameIhm mere;
		private List<CategorieIntervenant> lstCategorieIntervenantLocal;

		private JPanel panelBoutonsTableau;
		private JButton btnAjouter;
		private JButton btnSupprimer;

		private JTable tableCategorieIntervenant;
		private JScrollPane spTableauCategorieIntervenant;
		private ListSelectionModel selectionModel;
		private List<CategorieIntervenant> lstLocalInitial;
		private JButton btnModifier;
		private HashMap<CategorieIntervenant, CategorieIntervenant> lstCateModif;

		public PanelCategoriesIntervenant(Controleur ctrl, FrameIhm mere, List<CategorieIntervenant> lstCategorieIntervenant)
		{
			this.ctrl = ctrl;

			this.lstCategorieIntervenantLocal = new ArrayList<CategorieIntervenant>();
			this.lstLocalInitial = new ArrayList<CategorieIntervenant>();
			this.lstCateModif = new HashMap<CategorieIntervenant, CategorieIntervenant>();
			for (CategorieIntervenant categorieIntervenant : lstCategorieIntervenant)
			{
				this.lstCategorieIntervenantLocal.add(categorieIntervenant);
				this.lstLocalInitial.add(categorieIntervenant);
			}

			this.setLayout(new BorderLayout());

			this.tableCategorieIntervenant = new JTable( new ModelAffichageTableauIntervenant(this.ctrl, lstCategorieIntervenant));
			this.tableCategorieIntervenant.setFillsViewportHeight(true);
			this.tableCategorieIntervenant.setRowHeight(25);
			this.tableCategorieIntervenant.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableCategorieIntervenant.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête


			spTableauCategorieIntervenant = new JScrollPane(this.tableCategorieIntervenant);
			this.add(this.spTableauCategorieIntervenant, BorderLayout.CENTER);
			this.selectionModel = this.tableCategorieIntervenant.getSelectionModel();
			this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.selectionModel.addListSelectionListener(this);
			

			// Ajout des boutons
			this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
			this.panelBoutonsTableau.setBorder(new EmptyBorder(5, 0, 5, 0));

			this.btnAjouter = new JButton("ajouter");
			this.btnSupprimer = new JButton("supprimer");
			this.btnModifier = new JButton("modifier");

			this.panelBoutonsTableau.add(this.btnAjouter);
			this.panelBoutonsTableau.add(this.btnSupprimer);
			this.panelBoutonsTableau.add(this.btnModifier);
			this.btnSupprimer.setEnabled(false);
			this.btnModifier.setEnabled(false);

			this.add(this.panelBoutonsTableau, BorderLayout.SOUTH);


			// Ajout des listeners
			this.btnAjouter.addActionListener(this);
			this.btnSupprimer.addActionListener(this);
			this.btnModifier.addActionListener(this);
		}

		public void resetLst()
		{
			this.lstCategorieIntervenantLocal = new ArrayList<CategorieIntervenant>();
			for (CategorieIntervenant categorieIntervenant : lstLocalInitial)
			{
				this.lstCategorieIntervenantLocal.add(categorieIntervenant);
			}

			for (CategorieIntervenant categorieIntervenant : lstCateModif.keySet())
			{
				categorieIntervenant.setCode(lstCateModif.get(categorieIntervenant).getCode());
				categorieIntervenant.setNom(lstCateModif.get(categorieIntervenant).getNom());
				categorieIntervenant.setMinH(lstCateModif.get(categorieIntervenant).getMinH());
				categorieIntervenant.setMaxH(lstCateModif.get(categorieIntervenant).getMaxH());
				categorieIntervenant.setCoefTp(lstCateModif.get(categorieIntervenant).getCoefTp());
			}
			this.majTab();
		}

		public void setLstInitial()
		{
			this.lstLocalInitial = new ArrayList<CategorieIntervenant>();
			for (CategorieIntervenant categorieIntervenant : lstCategorieIntervenantLocal)
			{
				this.lstLocalInitial.add(categorieIntervenant);
			}
		}

		public void majTab()
		{
			this.tableCategorieIntervenant.setModel(new ModelAffichageTableauIntervenant(this.ctrl, this.lstCategorieIntervenantLocal));
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == this.btnAjouter)
			{
				new PageCreaCategorieIntervenant(this.mere, ctrl, this.lstCategorieIntervenantLocal);
				this.tableCategorieIntervenant.repaint();
			}
			
			if (e.getSource() == this.btnSupprimer)
			{
				if(this.ctrl.getLstIntervenantParCode(this.lstCategorieIntervenantLocal.get(this.tableCategorieIntervenant.getSelectedRow()).getCode()) != null)
				{
					JOptionPane.showMessageDialog(this.mere, "Impossible de supprimer cette catégorie car elle est utilisée par un intervenant", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					int rep = JOptionPane.showConfirmDialog(this.mere, "Voulez-vous vraiment supprimer ce tyoe intervenant ?", "Suppression", JOptionPane.YES_NO_OPTION);
					if(rep == JOptionPane.YES_OPTION)
					{
						int emplacement = this.tableCategorieIntervenant.getSelectedRow();
						this.ctrl.ajouterSuppAttente(this.lstCategorieIntervenantLocal.get(this.tableCategorieIntervenant.getSelectedRow()));
						this.lstCategorieIntervenantLocal.remove(this.tableCategorieIntervenant.getSelectedRow());
						this.majTab();;

						if(this.tableCategorieIntervenant.getRowCount() <= emplacement)
							emplacement--;

						this.tableCategorieIntervenant.setRowSelectionInterval(emplacement, emplacement);
					}
				}
			}

			if (e.getSource() == this.btnModifier)
			{
				CategorieIntervenant cateIntervenantModif = this.lstCategorieIntervenantLocal.get(this.tableCategorieIntervenant.getSelectedRow());
				this.lstCateModif.put(cateIntervenantModif, new CategorieIntervenant(cateIntervenantModif.getCode(), cateIntervenantModif.getNom(), cateIntervenantModif.getMinH(), cateIntervenantModif.getMaxH(), cateIntervenantModif.getCoefTp()));
				new PageCreaCategorieIntervenant(this.mere, ctrl, cateIntervenantModif, this.lstCategorieIntervenantLocal);
			}

			this.majTab();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// Vérifier si une ligne est sélectionnée
			if (!e.getValueIsAdjusting() && this.tableCategorieIntervenant.getSelectedRow() != -1) {
				this.btnSupprimer.setEnabled(true); // Activer le bouton
				this.btnModifier.setEnabled(true);
			} else {
				this.btnSupprimer.setEnabled(false); // Désactiver le bouton si aucune ligne n'est sélectionnée
				this.btnModifier.setEnabled(false);
			}
		}

	}


	/*---------------------------*/
	//   Catégorie Heures        //
	/*---------------------------*/
	private class PanelCategoriesHeure extends JPanel implements ActionListener, ListSelectionListener
	{
		private Controleur ctrl;
		private FrameIhm mere;
		private List<CategorieHeure> lstCategorieHeure;

		private JPanel panelBoutonsTableau;
		private JButton btnModifier;

		private JTable tableCategorieHeure;
		private JScrollPane spTableauCategorieHeure;
		private ListSelectionModel selectionModel;

		private HashMap<CategorieHeure, Double> hashCateHeurModi;

		public PanelCategoriesHeure(Controleur ctrl, FrameIhm mere, List<CategorieHeure> lstCategorieHeure)
		{
			this.ctrl = ctrl;
			this.mere = mere;
			this.lstCategorieHeure = lstCategorieHeure;
			this.hashCateHeurModi = new HashMap<CategorieHeure, Double>();

			this.setLayout(new BorderLayout());

			this.tableCategorieHeure = new JTable( new ModelAffichageTableauHeure(this.ctrl, lstCategorieHeure));
			this.tableCategorieHeure.setFillsViewportHeight(true);
			this.tableCategorieHeure.setRowHeight(25);
			this.tableCategorieHeure.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableCategorieHeure.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête

			spTableauCategorieHeure = new JScrollPane(this.tableCategorieHeure);
			this.add(this.spTableauCategorieHeure, BorderLayout.CENTER);

			this.selectionModel = this.tableCategorieHeure.getSelectionModel();
			this.selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.selectionModel.addListSelectionListener(this);
			

			// Ajout des boutons
			this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
			this.panelBoutonsTableau.setBorder(new EmptyBorder(5, 0, 5, 0));

			this.btnModifier = new JButton("modifier");
			this.btnModifier.setEnabled(false);

			this.panelBoutonsTableau.add(this.btnModifier);

			this.add(this.panelBoutonsTableau, BorderLayout.SOUTH);


			// Ajout des listeners
			this.btnModifier.addActionListener(this);
		}

		/**
		 * Permet de remettre tout les coef des categorie d'heure avant modification
		 */
		public void refreshHeure()
		{
			for (CategorieHeure categorieHeure : hashCateHeurModi.keySet())
			{
				categorieHeure.setCoef(hashCateHeurModi.get(categorieHeure));
			}

			this.hashCateHeurModi.clear();
		}

		public void majTab()
		{
			this.tableCategorieHeure.setModel(new ModelAffichageTableauHeure(this.ctrl, this.lstCategorieHeure));
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == this.btnModifier)
			{
				CategorieHeure cateHeurModif = this.lstCategorieHeure.get(this.tableCategorieHeure.getSelectedRow());
				if(!hashCateHeurModi.containsKey(cateHeurModif))
					this.hashCateHeurModi.put(cateHeurModif,((Double) cateHeurModif.getCoef()));

				new PageCreaCategorieHeure(this.mere, ctrl, cateHeurModif);
			}

			this.majTab();
		}

		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			// Vérifier si une ligne est sélectionnée
			if (!e.getValueIsAdjusting() && tableCategorieHeure.getSelectedRow() != -1) {
				btnModifier.setEnabled(true); // Activer le bouton
			} else {
				btnModifier.setEnabled(false); // Désactiver le bouton si aucune ligne n'est sélectionnée
			}
		}
	}

	private class ModelAffichageTableauIntervenant extends AbstractTableModel
	{
		// private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableauIntervenant(Controleur ctrl, List<CategorieIntervenant> lstCategorieIntervenant)
		{
			// this.ctrl = ctrl;

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
		public int getColumnCount()                             {return 5;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}


	private class ModelAffichageTableauHeure extends AbstractTableModel
	{
		// private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableauHeure(Controleur ctrl, List<CategorieHeure> lstCategorieHeure)
		{
			// this.ctrl = ctrl;

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
		public int getColumnCount()                             {return 2;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}