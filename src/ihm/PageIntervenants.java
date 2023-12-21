package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import controleur.Controleur;
import ihm.creationObjet.PageCreaIntervenant;
import ihm.creationObjet.PageEditionIntervenant;
import metier.model.Intervenant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;


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
	private JButton btnModifier;
	private JButton btnAcceuil;

	private FrameIhm mere;

	private ArrayList<Intervenant> lstIntervenantsLocal;

	private HashMap<Intervenant, Intervenant> hashInterModif;


	public PageIntervenants(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.hashInterModif = new HashMap<Intervenant, Intervenant>();

		this.lstIntervenantsLocal = new ArrayList<Intervenant>();
		for (Intervenant intervenant : this.ctrl.getLstIntervenants())
		{
			this.lstIntervenantsLocal.add(intervenant);
		}
		

		this.setLayout(new BorderLayout());

		this.setBorder(new EmptyBorder(15, 30, 15, 30));
		this.mere.setTitle("Intervenants");

		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.panelBoutons.setBorder(new EmptyBorder(30, 0, 0, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.addActionListener(this);

		/*------------------*/
		//   panelTableau   //
		/*------------------*/
		this.panelTableau = new JPanel(new BorderLayout());
		this.panelTableau.setBorder(new EmptyBorder(0, 0, 15, 0));

		// Ajout de la table
		this.tableIntervenants = new JTable( new ModelAffichageTableau(this.ctrl, this.lstIntervenantsLocal) );
		this.tableIntervenants.setFillsViewportHeight(true);
		this.tableIntervenants.setRowHeight(25);
		TableColumnModel model = this.tableIntervenants.getColumnModel();

		for (int i = 3; i <= 14; i++)
		{
			(model.getColumn(i)).setPreferredWidth(6);
		}

		spTableauIntervenants = new JScrollPane(this.tableIntervenants);




		this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.panelBoutonsTableau.setBorder(new EmptyBorder(15, 0, 0, 0));

		this.btnAjouter = new JButton("ajouter");
		this.btnAjouter.addActionListener(this);
		this.btnSupprimer = new JButton("supprimer");
		this.btnSupprimer.addActionListener(this);
		this.btnSupprimer.setEnabled(false); // Désactiver le bouton au démarrage
		this.btnModifier = new JButton("modifier");
		this.btnModifier.addActionListener(this);
		this.btnModifier.setEnabled(false); // Désactiver le bouton au démarrage

		this.panelBoutonsTableau.add(this.btnAjouter);
		this.panelBoutonsTableau.add(this.btnSupprimer);
		this.panelBoutonsTableau.add(this.btnModifier);


		JPanel panelAcceuil = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.btnAcceuil = new JButton("Acceuil");
		this.btnAcceuil.addActionListener(this);
		panelAcceuil.add(this.btnAcceuil);


		this.panelTableau.add(panelAcceuil, BorderLayout.NORTH);
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

	public void majTab()
	{
		this.tableIntervenants.setModel(new ModelAffichageTableau(this.ctrl, this.lstIntervenantsLocal));

		TableColumnModel model = this.tableIntervenants.getColumnModel();

		for (int i = 3; i <= 14; i++)
		{
			(model.getColumn(i)).setPreferredWidth(5);
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && this.tableIntervenants.getSelectedRow() != -1) {
			this.btnSupprimer.setEnabled(true); // Activer le bouton
			this.btnModifier.setEnabled(true);
		} else {
			this.btnSupprimer.setEnabled(false); // Désactiver le bouton si aucune ligne n'est sélectionnée
			this.btnModifier.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAnnuler)
		{
			this.ctrl.annuler();

			this.lstIntervenantsLocal = new ArrayList<Intervenant>();
			for (Intervenant intervenant : this.ctrl.getLstIntervenants())
			{
				this.lstIntervenantsLocal.add(intervenant);
			}

			for (Intervenant intervenant : hashInterModif.keySet())
			{
				intervenant.setCategorie(hashInterModif.get(intervenant).getCategorie());
				intervenant.setNom(hashInterModif.get(intervenant).getNom());
				intervenant.setPrenom(hashInterModif.get(intervenant).getPrenom());
				intervenant.setHMin(hashInterModif.get(intervenant).getHMin());
				intervenant.sethMax(hashInterModif.get(intervenant).gethMax());
				intervenant.setCoefTP(hashInterModif.get(intervenant).getCoefTP());
			}

			this.hashInterModif.clear();
		}
		else if(e.getSource() == btnSupprimer)
		{
			if(this.ctrl.getLstAffectationParIntervenant(this.lstIntervenantsLocal.get(this.tableIntervenants.getSelectedRow()).getId()) != null)
			{
				JOptionPane.showMessageDialog(this.mere, "Cette intervenant a des affectation ! ", "ERREUR SUPPRESSION", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int rep = JOptionPane.showConfirmDialog(this.mere, "Voulez-vous vraiment supprimer cette intervenant ?", "Suppression", JOptionPane.YES_NO_OPTION);
				if(rep == JOptionPane.YES_OPTION)
				{
					int emplacement = this.tableIntervenants.getSelectedRow();
					this.ctrl.ajouterSuppAttente(this.lstIntervenantsLocal.get(this.tableIntervenants.getSelectedRow()));
					this.lstIntervenantsLocal.remove(this.tableIntervenants.getSelectedRow());
					this.majTab();;

					if(this.tableIntervenants.getRowCount() <= emplacement)
						emplacement--;

					this.tableIntervenants.setRowSelectionInterval(emplacement, emplacement);
				}
			}
		}
		else if(e.getSource() == btnAjouter)
		{
			new PageCreaIntervenant(this.mere, ctrl, this.lstIntervenantsLocal);
			this.tableIntervenants.repaint();
		}
		else if(e.getSource() == btnEnregistrer)
		{
			this.ctrl.sauvegarder();
		}
		else if(e.getSource() == btnModifier)
		{
			Intervenant inter = this.lstIntervenantsLocal.get(this.tableIntervenants.getSelectedRow());

			if(!this.hashInterModif.containsKey(inter))
				this.hashInterModif.put(inter, new Intervenant(inter.getId(), inter.getCategorie(), inter.getNom(), inter.getPrenom(), inter.getHMin(), inter.gethMax(), inter.getCoefTP()));


			new PageEditionIntervenant(this.mere, ctrl, inter);
			this.majTab();
		}
		else if(e.getSource() == btnAcceuil)
		{
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}

		this.majTab();
	}


	private class ModelAffichageTableau extends AbstractTableModel
	{
		// private Controleur ctrl;

		private Object[][] tabDonnees;
		private String[] tabEntetes;

		public ModelAffichageTableau(Controleur ctrl, List<Intervenant> lstIntervenants)
		{
			// this.ctrl = ctrl;

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
			}

			this.tabEntetes = new String[]{"Catégorie", "Nom", "Prénom", "hServ", "hMax", "Coef TP", "S1", "S3", "S5", "sTot", "S2", "S4", "S6", "sTot", "sTotal"};
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
		public int getColumnCount()                             {return 15;}
		public String getColumnName (int col)                  {return this.tabEntetes[col];}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}


