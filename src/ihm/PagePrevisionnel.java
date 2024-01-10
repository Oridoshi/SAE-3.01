package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import ihm.classPerso.JIntegerTextField;
import ihm.creationObjet.PageEditionPpp;
import ihm.creationObjet.PageEditionRessource;
import ihm.creationObjet.PageEditionSae;
import ihm.creationObjet.PageEditionStage;
import metier.model.CategorieModule;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PagePrevisionnel extends JPanel implements ActionListener
{
	private Controleur ctrl;


	private JButton btnCreaModule;
	private JButton btnModif;
	private JButton btnSupp;

	private FrameIhm mere;
	private JTabbedPane tabbedPane;

	private List<PanelInfoSemestre> lstPanelInfoSemestre;

	private JButton btnHome;

	private JComboBox<CategorieModule> cbCategorieModule;

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

		this.add(this.tabbedPane, BorderLayout.CENTER);

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(1, 4, 5, 0));

		this.cbCategorieModule = new JComboBox<CategorieModule>((new Vector<>(this.ctrl.getLstCategorieModule())));
		this.cbCategorieModule.setRenderer(new Renderer());
		this.btnCreaModule     = new JButton("Créer Module");
		this.btnModif          = new JButton("Modifier");
		this.btnSupp           = new JButton("Supprimer");

		this.btnCreaModule   .addActionListener(this);
		this.btnModif        .addActionListener(this);
		this.btnSupp         .addActionListener(this);

		panelBtn.add(this.cbCategorieModule);
		panelBtn.add(btnCreaModule);
		panelBtn.add(btnModif);
		panelBtn.add(btnSupp);

		panelBtn.setBorder(new EmptyBorder(10, 5, 0, 0));

		this.add(panelBtn, BorderLayout.SOUTH);

		JPanel panelNord = new JPanel();
		panelNord.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.btnHome = new JButton("Accueil");
		this.btnHome.addActionListener(this);

		panelNord.add(this.btnHome);

		this.add(panelNord, BorderLayout.NORTH);
	}

	private class Renderer extends DefaultListCellRenderer
	{
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value instanceof CategorieModule)
			{
				CategorieModule inter = (CategorieModule) value;
				setText(inter.getNom());
			}

			return this;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnCreaModule)
		{
			CategorieModule categorieModule = ((CategorieModule) this.cbCategorieModule.getSelectedItem());
			if(categorieModule.getNom().equals("Ressource"))
				this.mere.changerPage(PageEditionRessource.factorieCreationRessource(this.ctrl, this.mere, this.ctrl.getSemestre(this.tabbedPane.getSelectedIndex() + 1)));
			else if(categorieModule.getNom().equals("SAE"))
				this.mere.changerPage(PageEditionSae.factorieCreationSae(this.ctrl, this.mere, this.ctrl.getSemestre(this.tabbedPane.getSelectedIndex() + 1)));
			else if(categorieModule.getNom().equals("Stage/Suivi"))
				this.mere.changerPage(PageEditionStage.factorieCreationStage(this.ctrl, this.mere, this.ctrl.getSemestre(this.tabbedPane.getSelectedIndex() + 1)));
			else if(categorieModule.getNom().equals("PPP"))
				this.mere.changerPage(PageEditionPpp.factorieCreationPpp(this.ctrl, this.mere, this.ctrl.getSemestre(this.tabbedPane.getSelectedIndex() + 1)));
		}
		else if (e.getSource() == this.btnModif)
		{
			if(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).hasRowSelect())
			{
				Module m = this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getModuleSelectedRow();
				if(m.getCategorieModule() == this.ctrl.getCategorieModule("Ressource"))
					this.mere.changerPage(PageEditionRessource.factorieEditionRessource(this.ctrl, this.mere, m));
				else if(m.getCategorieModule() == this.ctrl.getCategorieModule("SAE"))
					this.mere.changerPage(PageEditionSae.factorieEditionSae(this.ctrl, this.mere, m));
				else if(m.getCategorieModule() == this.ctrl.getCategorieModule("Stage/Suivi"))
					this.mere.changerPage(PageEditionStage.factorieEditionStage(this.ctrl, this.mere, m));
				else if(m.getCategorieModule() == this.ctrl.getCategorieModule("PPP"))
					this.mere.changerPage(PageEditionPpp.factorieEditionPpp(this.ctrl, this.mere, m));
			}
		}
		else if (e.getSource() == this.btnSupp)
		{
			if(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).hasRowSelect())
			{
				if(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getModuleSelectedRow().getLstAffectation() != null)
				{
					JOptionPane.showMessageDialog(this.mere, "Ce Module a des affectation ! ", "ERREUR SUPPRESSION", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int rep = JOptionPane.showConfirmDialog(this.mere, "Voulez-vous vraiment supprimer cette ressource ?", "Suppression", JOptionPane.YES_NO_OPTION);
					if(rep == JOptionPane.YES_OPTION)
					{
						int emplacement = this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getSelectedIndex();
						this.ctrl.ajouterSuppAttente(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getModuleSelectedRow());
						this.ctrl.sauvegarder();
						this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).updateTable();

						if(this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).getCountRow() <= emplacement)
							emplacement--;

						this.lstPanelInfoSemestre.get(this.tabbedPane.getSelectedIndex()).setSelectedIndex(emplacement);
					}
				}
			}
		}
		else if(e.getSource() == this.btnHome)
		{
			this.mere.changerPage(new PageAccueil(ctrl, mere));
		}
	}

	private class PanelInfoSemestre extends JPanel implements ActionListener, FocusListener
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
			this.txtFNbGpTd.setAllowsInvalid(false);
			this.txtFNbGpTd.addActionListener(this);
			this.txtFNbGpTd.addFocusListener(this);
			panelInfoSemestre.add(new JLabel("nb gp TP"));
			panelInfoSemestre.add((this.txtFNbGpTp = new JIntegerTextField(3, this.semestreActu.getNbGroupeTp())));
			this.txtFNbGpTp.setAllowsInvalid(false);
			this.txtFNbGpTp.addActionListener(this);
			this.txtFNbGpTp.addFocusListener(this);
			panelInfoSemestre.add(new JLabel("nb Etd"));
			panelInfoSemestre.add((this.txtFNbEtud = new JIntegerTextField(3, this.semestreActu.getNbEtu())));
			this.txtFNbEtud.setAllowsInvalid(false);
			this.txtFNbEtud.addActionListener(this);
			this.txtFNbEtud.addFocusListener(this);
			panelInfoSemestre.add(new JLabel("nb semaine"));
			panelInfoSemestre.add((this.txtFNbSeme = new JIntegerTextField(3, this.semestreActu.getNbSemaine())));
			this.txtFNbSeme.setAllowsInvalid(false);
			this.txtFNbSeme.addActionListener(this);
			this.txtFNbSeme.addFocusListener(this);


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

		public void setSelectedIndex(int emplacement)
		{
			this.tableRessource.setRowSelectionInterval(emplacement, emplacement);
		}

		public int getCountRow()
		{
			return this.tableRessource.getRowCount();
		}

		public int getSelectedIndex()
		{
			return this.tableRessource.getSelectedRow();
		}

		public void updateTable()
		{
			this.tableRessource.setModel(new ModelAffichageTableau(this.ctrl, this.semestreActu.getlstModules()));
		}

		public Module getModuleSelectedRow()
		{
			return this.semestreActu.getlstModules().get(this.tableRessource.getSelectedRow());
		}

		public boolean hasRowSelect()
		{
			return this.tableRessource.getSelectedRow() != -1;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this.txtFNbGpTd)
			{
				this.semestreActu.setNbGroupeTd(this.txtFNbGpTd.getValue());
			}
			else if(e.getSource() == this.txtFNbGpTp)
			{
				this.semestreActu.setNbGroupeTp(this.txtFNbGpTp.getValue());
			}
			else if(e.getSource() == this.txtFNbEtud)
			{
				this.semestreActu.setNbEtu(this.txtFNbEtud.getValue());
			}
			else if(e.getSource() == this.txtFNbSeme)
			{
				this.semestreActu.setNbSemaine(this.txtFNbSeme.getValue());
			}
		}

		@Override
		public void focusGained(FocusEvent e){}

		@Override
		public void focusLost(FocusEvent e)
		{
			if(e.getSource() == this.txtFNbGpTd)
			{
				this.semestreActu.setNbGroupeTd(this.txtFNbGpTd.getValue());
			}
			else if(e.getSource() == this.txtFNbGpTp)
			{
				this.semestreActu.setNbGroupeTp(this.txtFNbGpTp.getValue());
			}
			else if(e.getSource() == this.txtFNbEtud)
			{
				this.semestreActu.setNbEtu(this.txtFNbEtud.getValue());
			}
			else if(e.getSource() == this.txtFNbSeme)
			{
				this.semestreActu.setNbSemaine(this.txtFNbSeme.getValue());
			}

			this.ctrl.ajouterSauvAttente(this.semestreActu);
			this.ctrl.sauvegarder();
		}
	}

	private class ModelAffichageTableau extends AbstractTableModel
	{
		// private Controleur ctrl;

		private Object[][] tabDonnees;

		public ModelAffichageTableau(Controleur ctrl, List<Module> lstRessource)
		{
			// this.ctrl = ctrl;

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
					tabDonnees[ligne][3] = lstRessource.get(ligne).isValider();
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
			return m.getTotalAffecteEqTd() + " / " + m.getTotalPromoEqTd();
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return 4;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}