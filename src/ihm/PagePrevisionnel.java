package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import ihm.classPerso.JIntegerTextField;
import ihm.creationObjet.PageEditionRessource;
import metier.model.Affectation;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PagePrevisionnel extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnCreaRessource;
	private JButton btnCreaSae;
	private JButton btnCreaStage;
	private JButton btnModif;
	private JButton btnSupp;

	private FrameIhm mere;
	private JTabbedPane tabbedPane;

	private List<PanelInfoSemestre> lstPanelInfoSemestre;

	public PagePrevisionnel(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.mere.setTitle("Accueil - Prévisionnel");
		this.setBorder(new EmptyBorder(15, 30, 15, 30));

		this.tabbedPane = new JTabbedPane();

		this.lstPanelInfoSemestre = new ArrayList<PanelInfoSemestre>();
		for (int s = 1; s <= 6; s++)
		{
			this.lstPanelInfoSemestre.add(new PanelInfoSemestre(this.ctrl, s));
			this.tabbedPane.addTab("S" + s, this.lstPanelInfoSemestre.get(s - 1));
		}

		this.add(this.tabbedPane);

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(1, 5, 5, 0));

		this.btnCreaRessource = new JButton("créer Ressource");
		this.btnCreaSae       = new JButton("créer SAE");
		this.btnCreaStage     = new JButton("créer Stage/Suivi");
		this.btnModif         = new JButton("modifier");
		this.btnSupp          = new JButton("supprimer");

		this.btnCreaRessource.addActionListener(this);
		this.btnCreaSae      .addActionListener(this);
		this.btnCreaStage    .addActionListener(this);
		this.btnModif        .addActionListener(this);
		this.btnSupp         .addActionListener(this);

		panelBtn.add(btnCreaRessource);
		panelBtn.add(btnCreaSae);
		panelBtn.add(btnCreaStage);
		panelBtn.add(btnModif);
		panelBtn.add(btnSupp);

		panelBtn.setBorder(new EmptyBorder(10, 5, 0, 0));

		this.add(panelBtn, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCreaRessource)
		{
			this.mere.changerPage(PageEditionRessource.factorieCreationRessource(this.ctrl, this.mere, this.ctrl.getSemestre(this.tabbedPane.getSelectedIndex() + 1)));
		}
		else if (e.getSource() == this.btnCreaSae)
		{

		}
		else if (e.getSource() == this.btnCreaStage)
		{

		}
		else if (e.getSource() == this.btnModif)
		{
			if(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).hasRowSelect())
			{
				this.mere.changerPage(PageEditionRessource.factorieEditionRessource(this.ctrl, this.mere, this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getModuleSelectedRow()));
			}
		}
		else if (e.getSource() == this.btnSupp)
		{

		}
	}

	private class PanelInfoSemestre extends JPanel
	{
		private JIntegerTextField txtFNbGpTd; //Nombre de groupe TD
		private JIntegerTextField txtFNbGpTp; //Nombre de groupe TP
		private JIntegerTextField txtFNbEtud; //Nombre d'etudiant
		private JIntegerTextField txtFNbSeme; //Nombre de semaine

		private JTable     tableRessource;

		private Controleur ctrl;
		private Semestre semestreActu;

		public PanelInfoSemestre(Controleur ctrl, int s)
		{
			this.ctrl = ctrl;

			this.setLayout(new BorderLayout());

			this.semestreActu = this.ctrl.getSemestre(s);

			JScrollPane spDonneIntervenant;
			
			JPanel panelTemp = new JPanel();
			panelTemp.setLayout(new BorderLayout());


			/*----------PanelInfoSemestre----------*/
			JPanel panelInfoSemestre = new JPanel();
			panelInfoSemestre.setLayout(new GridLayout(1, 8));

			panelInfoSemestre.add(new JLabel("nb gp TD"));
			panelInfoSemestre.add((this.txtFNbGpTd = new JIntegerTextField(3, this.semestreActu.getNbGroupeTd())));
			this.txtFNbGpTd.bloquerCaractereNonValide(true);
			panelInfoSemestre.add(new JLabel("nb gp TP"));
			panelInfoSemestre.add((this.txtFNbGpTp = new JIntegerTextField(3, this.semestreActu.getNbGroupeTp())));
			this.txtFNbGpTp.bloquerCaractereNonValide(true);
			panelInfoSemestre.add(new JLabel("nb Etd"));
			panelInfoSemestre.add((this.txtFNbEtud = new JIntegerTextField(3, this.semestreActu.getNbEtu())));
			this.txtFNbEtud.bloquerCaractereNonValide(true);
			panelInfoSemestre.add(new JLabel("nb semaine"));
			panelInfoSemestre.add((this.txtFNbSeme = new JIntegerTextField(3, this.semestreActu.getNbSemaine())));
			this.txtFNbSeme.bloquerCaractereNonValide(true);


			/*----------PanelInfo----------*/

			this.add(panelInfoSemestre, BorderLayout.NORTH);

			/*----------PanelInfoSemestre----------*/
			JPanel panelTab = new JPanel();
			panelTab.setLayout(new BorderLayout());

			this.tableRessource = new JTable( new ModelAffichageTableau(this.ctrl, this.semestreActu.getlstModules()) );
			this.tableRessource.setFillsViewportHeight(true);
			this.tableRessource.setRowHeight(25);
			this.tableRessource.setShowVerticalLines(false); // pour ne pas afficher les lignes verticales dans le tableau
			this.tableRessource.setTableHeader(null); // pour être sur qu'il n'y ait pas d'entête

			spDonneIntervenant = new JScrollPane(this.tableRessource);

			panelTab.add(new JLabel("Liste des Modules"), BorderLayout.NORTH);
			panelTab.add(spDonneIntervenant, BorderLayout.CENTER);
			/*----------PanelTab----------*/

			this.add(panelTab, BorderLayout.CENTER);
		}

		public Module getModuleSelectedRow()
		{
			return this.semestreActu.getlstModules().get(this.tableRessource.getSelectedRow());
		}

		public boolean hasRowSelect()
		{
			return this.tableRessource.getSelectedRow() != -1;
		}
	}

	private class ModelAffichageTableau extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableau(Controleur ctrl, List<Module> lstRessource)
		{
			this.ctrl = ctrl;

			int nbCol = 4;
			if(lstRessource != null && lstRessource.size() > 0)
			{
				int nbLig = lstRessource.size();

				this.tabDonnees = new Object[nbLig][nbCol];

				for (int ligne = 0; ligne < nbLig; ligne++)
				{
					tabDonnees[ligne][0] = lstRessource.get(ligne).getCode();
					tabDonnees[ligne][1] = lstRessource.get(ligne).getLibelleLong();
					tabDonnees[ligne][2] = calcul(lstRessource.get(ligne));
					tabDonnees[ligne][3] = lstRessource.get(ligne).getValider();
				}
			}
			else
			{
				this.tabDonnees = new Object[0][nbCol];
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
					return Boolean.class;
				default:
					return Object.class;
			}
		}

		private String calcul(Module m)
		{
			int nbTotAffHCm = 0;
			int nbTotAffHTd = 0;
			int nbTotAffHTp = 0;
			int nbTotAffHPonctu = 0;

			List<Affectation> lstAff = m.getLstAffectation();

			if(lstAff == null) return "0/TOT";


			for (Affectation aff : m.getLstAffectation())
			{
				if (aff.getCategorieHeure().getNom().equals("CM"))
				{
					if(aff.getNbHeure() != null)
						nbTotAffHCm += aff.getNbHeure();
					else
						nbTotAffHCm += aff.getNbGroupe() * aff.getNbSemaine();
				}
				else if (aff.getCategorieHeure().getNom().equals("TD"))
				{
					if(aff.getNbHeure() != null)
						nbTotAffHTd += aff.getNbHeure();
					else
						nbTotAffHTd += aff.getNbGroupe() * aff.getNbSemaine();
				}
				else if (aff.getCategorieHeure().getNom().equals("TP"))
				{
					if(aff.getNbHeure() != null)
						nbTotAffHTp += aff.getNbHeure();
					else
						nbTotAffHTp += aff.getNbGroupe() * aff.getNbSemaine();
				}
				else
				{
					if(aff.getNbHeure() != null)
						nbTotAffHPonctu += aff.getNbHeure();
					else
						nbTotAffHPonctu += aff.getNbGroupe() * aff.getNbSemaine();
				}
			}


			return (nbTotAffHCm + nbTotAffHTd + nbTotAffHTp + nbTotAffHPonctu) + "/" + "TOT";
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return 4;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}