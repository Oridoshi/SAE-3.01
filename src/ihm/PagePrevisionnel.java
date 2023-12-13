package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import ihm.creationObjet.PageCreaRessource;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public PagePrevisionnel(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.mere.setTitle("Acceuil - Prévisionnel");
		this.setBorder(new EmptyBorder(15, 30, 15, 30));

		this.tabbedPane = new JTabbedPane();

		for (int s = 1; s <= 6; s++)
		{
			this.tabbedPane.addTab("S" + s, new PanelInfoSemestre(ctrl, s));
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
			new PageCreaRessource(this.mere, this.ctrl);
		}
		else if (e.getSource() == this.btnCreaSae)
		{

		}
		else if (e.getSource() == this.btnCreaStage)
		{

		}
		else if (e.getSource() == this.btnModif)
		{

		}
		else if (e.getSource() == this.btnSupp)
		{

		}
	}

	private class PanelInfoSemestre extends JPanel
	{
		private JTextField txtFNbGpTd; //Nombre de groupe TD
		private JTextField txtFNbGpTp; //Nombre de groupe TP
		private JTextField txtFNbEtud; //Nombre d'etudiant
		private JTextField txtFNbSeme; //Nombre de semaine

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
			panelInfoSemestre.add((this.txtFNbGpTd = new JTextField("" + this.semestreActu.getNbGroupeTd())));
			panelInfoSemestre.add(new JLabel("nb gp TP"));
			panelInfoSemestre.add((this.txtFNbGpTp = new JTextField("" + this.semestreActu.getNbGroupeTp())));
			panelInfoSemestre.add(new JLabel("nb Etd"));
			panelInfoSemestre.add((this.txtFNbGpTd = new JTextField("" + this.semestreActu.getNbGroupeTd())));
			panelInfoSemestre.add(new JLabel("nb semaine"));
			panelInfoSemestre.add((this.txtFNbGpTd = new JTextField("" + this.semestreActu.getNbGroupeTd())));


			/*----------PanelInfo----------*/

			this.add(panelInfoSemestre, BorderLayout.NORTH);

			/*----------PanelInfoSemestre----------*/
			JPanel panelTab = new JPanel();
			panelTab.setLayout(new BorderLayout());

			this.tableRessource = new JTable( new ModelAffichageTableau(this.ctrl, this.semestreActu.getlstRessource()) );
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
					tabDonnees[ligne][2] = lstRessource.get(ligne).getTotalAffecteEqTd() + "/" + lstRessource.get(ligne).getTotalPromoEqTd();
					tabDonnees[ligne][3] = lstRessource.get(ligne).getValider();
				}
			}
			else
			{
				this.tabDonnees = new Object[1][nbCol];
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

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return this.tabDonnees[0].length;}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}