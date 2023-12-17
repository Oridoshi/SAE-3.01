package ihm.creationObjet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import ihm.classPerso.JIntegerTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.model.Module;
import ihm.PagePrevisionnel;
import metier.model.Affectation;
import metier.model.Semestre;

/**
 * PageEditionModule
 */
public class PageEditionStage extends JPanel implements ActionListener, FocusListener
{
	private static final Color COULEUR_LABEL = new Color(158,158,158);
	private static final Font FONT_LABEL = new Font("Arial", 0, 8);

	private final Module MODULE;

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelOuest;

	private FrameIhm mere;
	private Controleur ctrl;

	private JTextField txtFCode;
	private JTextField txtFLibelLong;
	private JTextField txtFLibelCour;
	private JTextField txtFTyMo;
	private JTextField txtFSeme;



	private List<Affectation> lstAffectation;
	private JTable tableAffectation;
	private JButton btnAjouterAffectation;
	private JButton btnSupprimerAffectation;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JCheckBox chkValider;

	private int nbTotAffHReh;
	private int nbTotAffHTut;
	private boolean validerAvantChangement;
	private ArrayList<Affectation> lstAffectationSupp;

	private JIntegerTextField txtFHProHReh;
	private JIntegerTextField txtFHProHTut;
	private JIntegerTextField txtFTotEqTdHProReh;
	private JIntegerTextField txtFTotEqTdHProTut;
	private JIntegerTextField txtFHProSomme;
	private JIntegerTextField txtFTotEqTdHProSomme;
	private JIntegerTextField txtFNbHRehSem;
	private JIntegerTextField txtFNbHTutSem;
	private JIntegerTextField txtFTotHRehAff;
	private JIntegerTextField txtFTotHTutAff;
	private JIntegerTextField txtFSom1;
	private JIntegerTextField txtFTotSom2;

	public static PageEditionStage factorieCreationStage(Controleur ctrl, FrameIhm mere, Semestre semestre)
	{
		PageEditionStage page = new PageEditionStage(ctrl, mere, semestre, new Module("SX.ST", semestre, ctrl.getCategorieModule("Stage/Suivi"), false, "", ""), null);
		return page;
	}

	public static PageEditionStage factorieEditionStage(Controleur ctrl, FrameIhm mere, Module module)
	{
		PageEditionStage page = new PageEditionStage(ctrl, mere, module.getSemestre(), module, module.getLstAffectation());
		return page;
	}

	private PageEditionStage(Controleur ctrl, FrameIhm mere, Semestre semestre, Module module, List<Affectation> lstAffectation)
	{
		this.ctrl = ctrl;
		this.mere = mere;
		this.MODULE = module;
		this.lstAffectationSupp = new ArrayList<Affectation>();

		if(lstAffectation != null)
			this.lstAffectation = lstAffectation;
		else
			this.lstAffectation = new ArrayList<Affectation>();

		this.panelCentre = new JPanel();

		/*------------Panel Nord------------*/

		this.panelNord   = new JPanel();
		this.panelNord.setLayout(new GridBagLayout());
		this.panelNord.setBorder(new EmptyBorder(0, 0, 10, 0));

		GridBagConstraints gbcNord = new GridBagConstraints();
		gbcNord.anchor = GridBagConstraints.WEST;
		gbcNord.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		//premier ligne
		JLabel lblTyMo = new JLabel("Type Module");
		lblTyMo.setForeground(PageEditionStage.COULEUR_LABEL);
		lblTyMo.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblSeme = new JLabel("Semestre");
		lblSeme.setForeground(PageEditionStage.COULEUR_LABEL);
		lblSeme.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblCode = new JLabel("Code");
		lblCode.setForeground(PageEditionStage.COULEUR_LABEL);
		lblCode.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblLibelLong = new JLabel("Libellé Long");
		lblLibelLong.setForeground(PageEditionStage.COULEUR_LABEL);
		lblLibelLong.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblLibelCour = new JLabel("Libellé Court");
		lblLibelCour.setForeground(PageEditionStage.COULEUR_LABEL);
		lblLibelCour.setFont(PageEditionStage.FONT_LABEL);

		gbcNord.gridx = 0;
		gbcNord.gridy = 0;
		this.panelNord.add(lblTyMo, gbcNord);
		gbcNord.gridx = 1;
		this.panelNord.add(lblSeme, gbcNord);
		gbcNord.gridx = 2;
		this.panelNord.add(lblCode, gbcNord);
		gbcNord.gridx = 3;
		this.panelNord.add(lblLibelLong, gbcNord);
		gbcNord.gridx = 4;
		this.panelNord.add(lblLibelCour, gbcNord);

		//deuxieme ligne
		this.txtFTyMo = new JTextField(8);
		txtFTyMo.setText(this.MODULE.getCategorieModule().getNom());
		txtFTyMo.setEditable(false);
		this.txtFSeme = new JTextField(4);
		txtFSeme.setText("S" + this.MODULE.getSemestre().getId());
		txtFSeme.setEditable(false);
		this.txtFCode = new JTextField(4);
		this.txtFCode.setText(this.MODULE.getCode());
		this.txtFCode.addFocusListener(this);
		this.txtFLibelLong = new JTextField(30);
		this.txtFLibelLong.setText(this.MODULE.getLibelleLong());
		this.txtFLibelCour = new JTextField(15);
		this.txtFLibelCour.setText(this.MODULE.getLibelleCourt());

		gbcNord.gridx = 0;
		gbcNord.gridy = 1;
		this.panelNord.add(txtFTyMo, gbcNord);
		gbcNord.gridx = 1;
		this.panelNord.add(txtFSeme, gbcNord);
		gbcNord.gridx = 2;
		this.panelNord.add(this.txtFCode, gbcNord);
		gbcNord.gridx = 3;
		this.panelNord.add(this.txtFLibelLong, gbcNord);
		gbcNord.gridx = 4;
		gbcNord.weightx = 1.0;
		this.panelNord.add(this.txtFLibelCour, gbcNord);
		gbcNord.weightx = 0;

		//troisieme ligne
		gbcNord.insets = new Insets(3, 3, 1, 0); // Marge autour des composants
		gbcNord.anchor = GridBagConstraints.CENTER;

		JLabel lblNbEtd = new JLabel("Nb Etd");
		lblNbEtd.setForeground(PageEditionStage.COULEUR_LABEL);
		lblNbEtd.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblNbGrpTd = new JLabel("Nb Grp TD");
		lblNbGrpTd.setForeground(PageEditionStage.COULEUR_LABEL);
		lblNbGrpTd.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblNbGrpTp = new JLabel("Nb Grp TP");
		lblNbGrpTp.setForeground(PageEditionStage.COULEUR_LABEL);
		lblNbGrpTp.setFont(PageEditionStage.FONT_LABEL);

		gbcNord.gridx = 0;
		gbcNord.gridy = 2;
		this.panelNord.add(lblNbEtd, gbcNord);
		gbcNord.gridx = 1;
		this.panelNord.add(lblNbGrpTd, gbcNord);
		gbcNord.gridx = 2;
		this.panelNord.add(lblNbGrpTp, gbcNord);

		//quatrieme ligne
		gbcNord.insets = new Insets(0, 3, 1, 0); // Marge autour des composants

		JTextField txtFNbEtd   = new JTextField(2);
		txtFNbEtd.setHorizontalAlignment(JTextField.CENTER);
		txtFNbEtd.setText("" + this.MODULE.getSemestre().getNbEtu());
		txtFNbEtd.setEditable(false);
		JTextField txtFNbGrpTd = new JTextField(2);
		txtFNbGrpTd.setHorizontalAlignment(JTextField.CENTER);
		txtFNbGrpTd.setText("" + this.MODULE.getSemestre().getNbGroupeTd());
		txtFNbGrpTd.setEditable(false);
		JTextField txtFNbGrpTp = new JTextField(2);
		txtFNbGrpTp.setHorizontalAlignment(JTextField.CENTER);
		txtFNbGrpTp.setText("" + this.MODULE.getSemestre().getNbGroupeTp());
		txtFNbGrpTp.setEditable(false);

		gbcNord.gridy = 3;
		gbcNord.gridx = 0;
		this.panelNord.add(txtFNbEtd, gbcNord);
		gbcNord.gridx = 1;
		this.panelNord.add(txtFNbGrpTd, gbcNord);
		gbcNord.gridx = 2;
		this.panelNord.add(txtFNbGrpTp, gbcNord);
		/*----------Fin Panel Nord----------*/

		/*-----------Panel  Ouest-----------*/

		this.panelOuest   = new JPanel();
		this.panelOuest.setLayout(new BorderLayout());

		JPanel panelTmpOuest = new JPanel();
		panelTmpOuest.setLayout(new BorderLayout());
		GridBagConstraints gbcOuest = new GridBagConstraints();
		gbcOuest.anchor = GridBagConstraints.WEST;
		gbcOuest.insets = new Insets(10, 1, 1, 0); // Marge autour des composants

		JPanel panelTemp = new JPanel();
		panelTemp.setLayout(new GridBagLayout());
		GridBagConstraints gbcTemp = new GridBagConstraints();
		gbcTemp.anchor = GridBagConstraints.WEST;
		
		//premier ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		JLabel lblHProCm = new JLabel("H REH");
		lblHProCm.setForeground(PageEditionStage.COULEUR_LABEL);
		lblHProCm.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblHProTd = new JLabel("H Tut");
		lblHProTd.setForeground(PageEditionStage.COULEUR_LABEL);
		lblHProTd.setFont(PageEditionStage.FONT_LABEL);

		gbcTemp.gridy = 0;
		gbcTemp.gridx = 1;
		panelTemp.add(lblHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(lblHProTd, gbcTemp);

		//deuxieme ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		this.txtFHProHReh = new JIntegerTextField(3, module.getNbHeureProgramme("REH"));
		this.txtFHProHReh.setAllowsInvalid(false);
		this.txtFHProHReh.addFocusListener(this);
		this.txtFHProHReh.addActionListener(this);
		this.txtFHProHTut = new JIntegerTextField(3, module.getNbHeureProgramme("HT"));
		this.txtFHProHTut.setAllowsInvalid(false);
		this.txtFHProHTut.addFocusListener(this);
		this.txtFHProHTut.addActionListener(this);

		gbcTemp.gridy = 1;
		gbcTemp.gridx = 1;
		panelTemp.add(this.txtFHProHReh, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(this.txtFHProHTut, gbcTemp);

		//troisieme ligne
		gbcTemp.insets = new Insets(10, 5, 1, 10); // Marge autour des composants

		JLabel lblTotal = new JLabel("Total(eqtd)");
		lblTotal.setForeground(PageEditionStage.COULEUR_LABEL);
		lblTotal.setFont(PageEditionStage.FONT_LABEL);

		this.txtFTotEqTdHProReh = new JIntegerTextField(3);
		this.txtFTotEqTdHProReh.setEditable(false);
		this.txtFTotEqTdHProReh.setValue((int) (this.txtFHProHReh.getValue() * this.ctrl.getCoefH("REH")));
		this.txtFTotEqTdHProTut = new JIntegerTextField(3);
		this.txtFTotEqTdHProTut.setEditable(false);
		this.txtFTotEqTdHProTut.setValue((int) (this.txtFHProHTut.getValue() * this.ctrl.getCoefH("HT")));

		gbcTemp.gridx = 0;
		gbcTemp.gridy = 2;
		panelTemp.add(lblTotal, gbcTemp);
		gbcTemp.insets = new Insets(5, 1, 1, 0); // Marge autour des composants
		gbcTemp.gridx = 1;
		panelTemp.add(this.txtFTotEqTdHProReh, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(this.txtFTotEqTdHProTut, gbcTemp);

		//quatrieme ligne
		gbcTemp.insets = new Insets(0, 5, 10, 0); // Marge autour des composants

		JLabel lblPromo = new JLabel("promo");
		lblPromo.setForeground(PageEditionStage.COULEUR_LABEL);
		lblPromo.setFont(PageEditionStage.FONT_LABEL);

		gbcTemp.gridx = 0;
		gbcTemp.gridy = 3;
		panelTemp.add(lblPromo, gbcTemp);


		//Panel Somme\\
		JPanel panelSomme = new JPanel();
		panelSomme.setLayout(new GridBagLayout());
		GridBagConstraints gbcSomme = new GridBagConstraints();
		gbcSomme.anchor = GridBagConstraints.CENTER;
		gbcSomme.insets = new Insets(0, 10, 1, 5); // Marge autour des composants

		//premier ligne
		JLabel lblHProSomme = new JLabel("∑");
		lblHProSomme.setForeground(PageEditionStage.COULEUR_LABEL);
		lblHProSomme.setFont(PageEditionStage.FONT_LABEL);

		gbcSomme.gridy = 0;
		gbcSomme.gridx = 0;
		panelSomme.add(lblHProSomme, gbcSomme);

		//deuxieme ligne
		this.txtFHProSomme = new JIntegerTextField(3);
		this.txtFHProSomme.setValue(this.txtFHProHReh.getValue() + this.txtFHProHTut.getValue());
		this.txtFHProSomme.setEditable(false);

		gbcSomme.gridy = 1;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFHProSomme, gbcSomme);

		//troisieme ligne
		gbcSomme.insets = new Insets(5, 10, 1, 5); // Marge autour des composants
		this.txtFTotEqTdHProSomme = new JIntegerTextField(3);
		this.txtFTotEqTdHProSomme.setEditable(false);
		this.txtFTotEqTdHProSomme.setValue(this.txtFTotEqTdHProReh.getValue() + this.txtFTotEqTdHProTut.getValue());

		gbcSomme.gridy = 2;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFTotEqTdHProSomme, gbcSomme);

		//quatrieme ligne
		gbcSomme.insets = new Insets(0, 5, 10, 0); // Marge autour des composants
		JLabel labelEspace = new JLabel(" ");
		labelEspace.setForeground(PageEditionStage.COULEUR_LABEL);
		labelEspace.setFont(PageEditionStage.FONT_LABEL);

		gbcSomme.gridy = 3;
		gbcSomme.gridx = 0;
		panelSomme.add(labelEspace, gbcSomme);


		JPanel panelGridBag = new JPanel();
		panelGridBag.setLayout(new BorderLayout());
		panelGridBag.add(panelSomme, BorderLayout.EAST);
		panelGridBag.add(panelTemp, BorderLayout.CENTER);
		panelGridBag.setBorder(new LineBorder(COULEUR_LABEL, 1 /*Largeur de la bordure*/));

		panelTmpOuest.add(panelGridBag, BorderLayout.CENTER);

		//Bouton valider\\
		JPanel panelValider = new JPanel();
		panelValider.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblValider = new JLabel("Validation");
		lblValider.setForeground(PageEditionStage.COULEUR_LABEL);
		lblValider.setFont(PageEditionStage.FONT_LABEL);
		this.chkValider = new JCheckBox();
		this.chkValider.setSelected(this.MODULE.isValider());
		this.chkValider.addActionListener(this);
		this.validerAvantChangement = this.MODULE.isValider();

		panelValider.add(lblValider);
		panelValider.add(this.chkValider);

		panelTmpOuest.add(panelValider, BorderLayout.SOUTH);

		//Label
		JPanel panelTmpLabel1 = new JPanel();

		JLabel lblPnLocal = new JLabel("PN local (nb h tot/etd)");
		lblPnLocal.setForeground(PageEditionStage.COULEUR_LABEL);
		lblPnLocal.setFont(PageEditionStage.FONT_LABEL);

		panelTmpLabel1.add(lblPnLocal);

		panelTmpOuest.add(panelTmpLabel1, BorderLayout.NORTH);


		this.panelOuest.add(panelTmpOuest, BorderLayout.NORTH);
		/*---------Fin Panel  Ouest---------*/

		/*-----------Panel Centre-----------*/

		this.panelCentre.setLayout(new BorderLayout());
		this.panelCentre.setBorder(new EmptyBorder(0, 5, 0, 0));


		JPanel panelRepartition = new JPanel();
		panelRepartition.setLayout(new BorderLayout());
		panelRepartition.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		
		JPanel panelTmpRepartition = new JPanel();
		panelTmpRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcRepartition = new GridBagConstraints();
		gbcRepartition.anchor = GridBagConstraints.CENTER;
		
		//premiere ligne

		JLabel lblNbHReh = new JLabel("H REH");
		lblNbHReh.setForeground(PageEditionStage.COULEUR_LABEL);
		lblNbHReh.setFont(PageEditionStage.FONT_LABEL);
		JLabel lblNbHSem1 = new JLabel("H Tut");
		lblNbHSem1.setForeground(PageEditionStage.COULEUR_LABEL);
		lblNbHSem1.setFont(PageEditionStage.FONT_LABEL);

		gbcRepartition.gridy = 1;
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(lblNbHReh, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(lblNbHSem1, gbcRepartition);
		
		//deuxieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0);
		JLabel lblTotPromo = new JLabel("Total promo (eqtd)");
		lblTotPromo.setForeground(PageEditionStage.COULEUR_LABEL);
		lblTotPromo.setFont(PageEditionStage.FONT_LABEL);

		this.txtFNbHRehSem = new JIntegerTextField(3, module.getNbHeureSemaine("REH"));
		this.txtFNbHRehSem.setAllowsInvalid(false);
		this.txtFNbHRehSem.addFocusListener(this);
		this.txtFNbHRehSem.addActionListener(this);

		this.txtFNbHTutSem = new JIntegerTextField(3, module.getNbHeureSemaine("HT"));
		this.txtFNbHTutSem.setAllowsInvalid(false);
		this.txtFNbHTutSem.addFocusListener(this);
		this.txtFNbHTutSem.addActionListener(this);

		gbcRepartition.gridy = 2;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(lblTotPromo, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(this.txtFNbHRehSem, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(this.txtFNbHTutSem, gbcRepartition);

		//troisieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge
		JLabel lblTotAff = new JLabel("Total affecté (eqtd)");
		lblTotAff.setForeground(PageEditionStage.COULEUR_LABEL);
		lblTotAff.setFont(PageEditionStage.FONT_LABEL);

		this.recalculeTotalAffecte();

		this.txtFTotHRehAff = new JIntegerTextField(3, this.nbTotAffHReh);
		this.txtFTotHRehAff.setEditable(false);
		this.txtFTotHTutAff = new JIntegerTextField(3, this.nbTotAffHTut);
		this.txtFTotHTutAff.setEditable(false);

		gbcRepartition.gridy = 3;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(lblTotAff, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(this.txtFTotHRehAff, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(this.txtFTotHTutAff, gbcRepartition);

		//Panel Somme\\
		JPanel panelSommeRepartition = new JPanel();
		panelSommeRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcSommeRepartition = new GridBagConstraints();
		gbcSommeRepartition.anchor = GridBagConstraints.CENTER;
		gbcSommeRepartition.insets = new Insets(0, 10, 1, 5); // Marge autour des composants

		//premier ligne
		JLabel lblSommeSem = new JLabel("∑");
		lblSommeSem.setForeground(PageEditionStage.COULEUR_LABEL);
		lblSommeSem.setFont(PageEditionStage.FONT_LABEL);

		gbcSommeRepartition.gridy = 0;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(lblSommeSem, gbcSommeRepartition);

		//deuxieme ligne
		this.txtFSom1 = new JIntegerTextField(3);
		this.txtFSom1.setValue(this.txtFNbHRehSem.getValue() + this.txtFNbHTutSem.getValue());
		this.txtFSom1.setEditable(false);

		gbcSommeRepartition.gridy = 1;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFSom1, gbcSommeRepartition);

		//troisieme ligne
		this.txtFTotSom2 = new JIntegerTextField(3);
		this.txtFTotSom2.setValue(this.txtFTotHRehAff.getValue() + txtFTotHTutAff.getValue());
		this.txtFTotSom2.setEditable(false);

		gbcSommeRepartition.gridy = 2;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFTotSom2, gbcSommeRepartition);

		JPanel panelGridBag2 = new JPanel();
		panelGridBag2.setLayout(new BorderLayout());
		panelGridBag2.setBorder(new LineBorder(COULEUR_LABEL, 1 /*Largeur de la bordure*/));
		panelGridBag2.add(panelSommeRepartition, BorderLayout.EAST);
		panelGridBag2.add(panelTmpRepartition, BorderLayout.CENTER);


		panelRepartition.add(panelGridBag2, BorderLayout.CENTER);


		JPanel panelTab = new JPanel();
		panelTab.setLayout(new BorderLayout());
		panelTab.setBorder(new EmptyBorder(10, 0, 0, 0));


		this.tableAffectation = new JTable( new ModelAffichageTableau(this.ctrl, this.lstAffectation) );
		this.tableAffectation.setFillsViewportHeight(true);
		this.tableAffectation.setRowHeight(25);
		TableColumnModel model = this.tableAffectation.getColumnModel();
		(model.getColumn(0)).setPreferredWidth(50);
		(model.getColumn(1)).setPreferredWidth(22);
		(model.getColumn(2)).setPreferredWidth(30);
		(model.getColumn(3)).setPreferredWidth(22);
		(model.getColumn(4)).setPreferredWidth(22);
		(model.getColumn(5)).setPreferredWidth(30);


		JScrollPane spDonneAffectation = new JScrollPane(this.tableAffectation);

		JLabel lblListeAffectation = new JLabel("Affectation");
		lblListeAffectation.setForeground(PageEditionStage.COULEUR_LABEL);
		lblListeAffectation.setFont(PageEditionStage.FONT_LABEL);

		panelTab.add(lblListeAffectation, BorderLayout.NORTH);
		panelTab.add(spDonneAffectation, BorderLayout.CENTER);


		JPanel panelBoutonPanelCenter = new JPanel();
		panelBoutonPanelCenter.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.btnAjouterAffectation = new JButton("Ajouter");
		this.btnAjouterAffectation.addActionListener(this);
		this.btnSupprimerAffectation = new JButton("Supprimer");
		this.btnSupprimerAffectation.addActionListener(this);

		panelBoutonPanelCenter.add(this.btnAjouterAffectation);
		panelBoutonPanelCenter.add(this.btnSupprimerAffectation);

		panelTab.add(panelBoutonPanelCenter, BorderLayout.SOUTH);

		//Label
		JPanel panelTmpLabel2 = new JPanel();

		JLabel lblRepartition = new JLabel("Répartition");
		lblRepartition.setForeground(PageEditionStage.COULEUR_LABEL);
		lblRepartition.setFont(PageEditionStage.FONT_LABEL);

		panelTmpLabel2.add(lblRepartition);

		panelRepartition.add(panelTmpLabel2, BorderLayout.NORTH);


		this.panelCentre.add(panelRepartition, BorderLayout.NORTH);
		this.panelCentre.add(panelTab, BorderLayout.CENTER);
		/*---------Fin Panel Centre---------*/

		/*----------Panel Bouton----------*/
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.btnValider = new JButton("Enregistrer");
		this.btnValider.addActionListener(this);
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.addActionListener(this);

		panelBtn.add(this.btnValider);
		panelBtn.add(this.btnAnnuler);
		/*----------Fin Panel Bouton----------*/

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(15, 10, 15, 10));
		this.mere.setTitle("Prévisionnel - Module");

		this.add(this.panelNord, BorderLayout.NORTH);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelOuest, BorderLayout.WEST);
		this.add(panelBtn, BorderLayout.SOUTH);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// Ne rien faire lorsqu'on gagne le focus
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if (e.getSource() == this.txtFCode)
		{
			String regex = "^S[1-6].ST$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.txtFCode.getText());
			if(matcher.matches())
			{
				regex   = "^S[" + this.MODULE.getSemestre().getId() + "].ST$";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(this.txtFCode.getText());
				if(!matcher.matches())
				{
					JOptionPane.showMessageDialog(this.mere, "Vous avez un semestre différent pour votre code que celui de votre module.", "Information Module entrer différente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, "Le code doit être sous la format S[1-6].ST !", "ERREUR ENTRER CODE", JOptionPane.ERROR_MESSAGE);
				this.txtFCode.setText("SX.ST");
			}
		}
		else
		{
			JIntegerTextField tmp = (JIntegerTextField) e.getSource();
			if(tmp.getText().equals(""))
				tmp.setText("0");
			this.recalcule();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnAnnuler)
		{
			this.ctrl.annuler();
			this.MODULE.setValider(validerAvantChangement);
			for (Affectation aff : lstAffectationSupp)
			{
				this.MODULE.getLstAffectation().add(aff);
			}
			this.mere.changerPage(new PagePrevisionnel(ctrl, mere));
		}
		else if(e.getSource() == this.btnValider)
		{
			if(this.txtFCode.getText().equals("SX.ST"))
			{
				JOptionPane.showMessageDialog(this.mere, "Vous devez entrer un code valide !", "ERREUR ENTRER CODE", JOptionPane.ERROR_MESSAGE);
			}
			else if(this.txtFLibelLong.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this.mere, "Vous devez entrer un libellé long !", "ERREUR ENTRER LIBELLE LONG", JOptionPane.ERROR_MESSAGE);
			}
			else if(this.txtFLibelCour.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this.mere, "Vous devez entrer un libellé court !", "ERREUR ENTRER LIBELLE", JOptionPane.ERROR_MESSAGE);
			}
			else if(!this.MODULE.setCode(this.txtFCode.getText()))
			{
				JOptionPane.showMessageDialog(this.mere, "Le code que vous avez entrée est déjà utilisé !", "ERREUR CODE", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(valeurEntreOk(this.chkValider.isSelected()))
				{
					this.majModule();
					this.ctrl.ajouterSauvAttente(MODULE);
					this.ctrl.sauvegarder();
					this.mere.changerPage(new PagePrevisionnel(ctrl, mere));
				}
			}
		}
		else if(e.getSource() == this.chkValider)
		{
			this.MODULE.setValider(this.chkValider.isSelected());
		}
		else if(e.getSource() == this.btnAjouterAffectation)
		{
			new PageCreaAffectation(this.ctrl, this.mere, this.lstAffectation, this.tableAffectation, this.MODULE);

			this.recalcule();
		}
		else if(e.getSource() == this.btnSupprimerAffectation)
		{
			if(this.tableAffectation.getSelectedRow() != -1 && this.lstAffectation.size() > 0)
			{
				int rep = JOptionPane.showConfirmDialog(this.mere, "Voulez-vous vraiment supprimer cette ressource ?", "Suppression", JOptionPane.YES_NO_OPTION);
				if(rep == JOptionPane.YES_OPTION)
				{
					this.lstAffectationSupp.add(this.lstAffectation.get(this.tableAffectation.getSelectedRow()));
					this.ctrl.ajouterSuppAttente(this.lstAffectation.get(this.tableAffectation.getSelectedRow()));
					this.lstAffectation.remove(this.tableAffectation.getSelectedRow());
				}

				this.recalcule();
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, this.lstAffectation.size() > 0?"Vous devez sélectionner une affectation !":"Il n'y a aucune affectation !", "ERREUR SELECTION AFFECTATION", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JIntegerTextField tmp = (JIntegerTextField) e.getSource();
			if(tmp.getText().equals(""))
				tmp.setText("0");

			this.recalcule();
		}
	}

	private boolean valeurEntreOk(boolean selected)
	{
		if(selected)
		{
			if(this.txtFHProSomme.getValue() < this.txtFSom1.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure que vous avez défini est supérieur au nombre d'heure programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFSom1.getValue() < this.txtFTotSom2.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			if(this.txtFHProSomme.getValue() < this.txtFSom1.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure que vous avez défini est supérieur au nombre d'heure programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFTotSom2.getValue() < this.txtFTotSom2.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProSomme.getValue() > this.txtFSom1.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure que vous avez défini est inférieur au nombre d'heure programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFSom1.getValue() < this.txtFTotSom2.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else
			{
				return true;
			}
		}
	}

	private void majModule()
	{
		//Definition des informations du module
		this.MODULE.setLibelleLong(this.txtFLibelLong.getText());
		this.MODULE.setLibelleCourt(this.txtFLibelCour.getText());

		//Definition des heures du module
		this.MODULE.getProgramme().getItem("REH").setNbHPn(this.txtFHProHReh.getValue());
		this.MODULE.getProgramme().getItem("HT").setNbHPn(this.txtFHProHTut.getValue());
		this.MODULE.getProgramme().getItem("REH").setNbHeure(this.txtFNbHRehSem.getValue());
		this.MODULE.getProgramme().getItem("HT").setNbHeure(this.txtFNbHTutSem.getValue());
	}

	private void recalcule()
	{
		this.txtFTotEqTdHProReh.setValue((int) (this.txtFHProHReh.getValue() * this.ctrl.getCoefH("REH")));
		this.txtFTotEqTdHProTut.setValue((int) (this.txtFHProHTut.getValue() * this.ctrl.getCoefH("HT")));
		this.txtFHProSomme.setValue(this.txtFHProHReh.getValue() + this.txtFHProHTut.getValue());
		this.txtFTotEqTdHProSomme.setValue(this.txtFTotEqTdHProReh.getValue() + this.txtFTotEqTdHProTut.getValue());

		this.recalculeTotalAffecte();
		this.txtFTotHRehAff.setValue(this.nbTotAffHReh);
		this.txtFTotHTutAff.setValue(this.nbTotAffHTut);
		this.txtFSom1.setValue(this.txtFNbHRehSem.getValue() + this.txtFNbHTutSem.getValue());
		this.txtFTotSom2.setValue(this.txtFTotHRehAff.getValue() + this.txtFTotHTutAff.getValue());

		this.updateTable();
		this.repaint();
		this.revalidate();
	}

	public void updateTable()
	{
		this.tableAffectation.setModel(new ModelAffichageTableau(this.ctrl, this.lstAffectation));

		TableColumnModel model = this.tableAffectation.getColumnModel();
		(model.getColumn(0)).setPreferredWidth(50);
		(model.getColumn(1)).setPreferredWidth(22);
		(model.getColumn(2)).setPreferredWidth(30);
		(model.getColumn(3)).setPreferredWidth(22);
		(model.getColumn(4)).setPreferredWidth(22);
		(model.getColumn(5)).setPreferredWidth(30);;
	}

	private void recalculeTotalAffecte()
	{
		this.nbTotAffHReh = 0;
		this.nbTotAffHTut = 0;

		for (Affectation aff : lstAffectation)
		{
			if (aff.getCategorieHeure().getNom().equals("REH"))
			{
				this.nbTotAffHReh += aff.getNbHeure();
			}
			else
			{
				this.nbTotAffHTut += aff.getNbHeure();
			}
		}
	}

	private class ModelAffichageTableau extends AbstractTableModel
	{
		private Object[][] tabDonnees;

		private String[] tabEntetes;

		public ModelAffichageTableau(Controleur ctrl, List<Affectation> lstAffectation)
		{
			int nbCol = 7;
			if(lstAffectation != null && lstAffectation.size() > 0)
			{
				int nbLig = lstAffectation.size();

				this.tabDonnees = new Object[nbLig][nbCol];

				for (int ligne = 0; ligne < nbLig; ligne++)
				{
					tabDonnees[ligne][0] = lstAffectation.get(ligne).getIntervenant().getNom();
					tabDonnees[ligne][1] = lstAffectation.get(ligne).getCategorieHeure().getNom();
					tabDonnees[ligne][2] = lstAffectation.get(ligne).getNbGroupe();
					tabDonnees[ligne][3] = lstAffectation.get(ligne).getNbSemaine();
					tabDonnees[ligne][4] = lstAffectation.get(ligne).getNbHeure();
					if(lstAffectation.get(ligne).getNbHeure() != null)
						tabDonnees[ligne][5] = lstAffectation.get(ligne).getNbHeure() * lstAffectation.get(ligne).getCategorieHeure().getCoef();
					else
					{
						tabDonnees[ligne][5] = lstAffectation.get(ligne).getNbGroupe() * lstAffectation.get(ligne).getCategorieHeure().getCoef() * lstAffectation.get(ligne).getNbSemaine();
					}
					tabDonnees[ligne][6] = lstAffectation.get(ligne).getCommentaire();
				}

			}
			else
			{
				this.tabDonnees = new Object[0][nbCol];
			}

			this.tabEntetes = new String[]{"Intervenant", "Type", "Nb Gp", "Nb Sem", "Nb H", "Total Eqtd", "Commentaire"};
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
				case 4: // Colonne 4
					return Integer.class;
				case 5: // Colonne 5
					return String.class;
				default:
					return Object.class;
			}
		}

		public int getRowCount()                                {return this.tabDonnees.length;}
		public int getColumnCount()                             {return 7;}
		public String getColumnName (int col)                  {return this.tabEntetes[col];}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}