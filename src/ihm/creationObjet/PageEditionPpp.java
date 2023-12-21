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
public class PageEditionPpp extends JPanel implements ActionListener, FocusListener
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

	private boolean validerAvantChangement;
	private ArrayList<Affectation> lstAffectationSupp;
	private JIntegerTextField txtFHProHCm;
	private JIntegerTextField txtFHProHTd;
	private JIntegerTextField txtFHProHTp;
	private JIntegerTextField txtFHProHTut;
	private JIntegerTextField txtFTotEqTdHProCm;
	private JIntegerTextField txtFTotEqTdHProTd;
	private JIntegerTextField txtFTotEqTdHProTp;
	private JIntegerTextField txtFTotEqTdHProTut;
	private JIntegerTextField txtFHProSomme;
	private JIntegerTextField txtFTotEqTdHProSomme;
	private JIntegerTextField txtFNbHCmSem;
	private JIntegerTextField txtFNbHTdSem;
	private JIntegerTextField txtFNbHTpSem;
	private JIntegerTextField txtFNbHTutSem;
	private JIntegerTextField txtFNbHPonctuSem;
	private JIntegerTextField txtFTotHCmAff;
	private JIntegerTextField txtFTotHTdAff;
	private JIntegerTextField txtFTotHTpAff;
	private JIntegerTextField txtFTotHTutAff;
	private JIntegerTextField txtFTotHPonctuAff;
	private JIntegerTextField txtFSom1;
	private JIntegerTextField txtFTotSom2;

	private int nbTotAffHCm;
	private int nbTotAffHTd;
	private int nbTotAffHTp;
	private int nbTotAffHTut;
	private int nbTotAffHPonctu;


	public static PageEditionPpp factorieCreationPpp(Controleur ctrl, FrameIhm mere, Semestre semestre)
	{
		PageEditionPpp page = new PageEditionPpp(ctrl, mere, semestre, new Module("PX.XX", semestre, ctrl.getCategorieModule("PPP"), false, "", ""), null);
		return page;
	}

	public static PageEditionPpp factorieEditionPpp(Controleur ctrl, FrameIhm mere, Module module)
	{
		PageEditionPpp page = new PageEditionPpp(ctrl, mere, module.getSemestre(), module, module.getLstAffectation());
		return page;
	}

	private PageEditionPpp(Controleur ctrl, FrameIhm mere, Semestre semestre, Module module, List<Affectation> lstAffectation)
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
		lblTyMo.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblTyMo.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblSeme = new JLabel("Semestre");
		lblSeme.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblSeme.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblCode = new JLabel("Code");
		lblCode.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblCode.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblLibelLong = new JLabel("Libellé Long");
		lblLibelLong.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblLibelLong.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblLibelCour = new JLabel("Libellé Court");
		lblLibelCour.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblLibelCour.setFont(PageEditionPpp.FONT_LABEL);

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
		lblNbEtd.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbEtd.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbGrpTd = new JLabel("Nb Grp TD");
		lblNbGrpTd.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbGrpTd.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbGrpTp = new JLabel("Nb Grp TP");
		lblNbGrpTp.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbGrpTp.setFont(PageEditionPpp.FONT_LABEL);

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

		JLabel lblHProCm = new JLabel("H CM");
		lblHProCm.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblHProCm.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblHProTd = new JLabel("H TD");
		lblHProTd.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblHProTd.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblHProTp = new JLabel("H TP");
		lblHProTp.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblHProTp.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblHProHt = new JLabel("H Tut");
		lblHProHt.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblHProHt.setFont(PageEditionPpp.FONT_LABEL);

		gbcTemp.gridy = 0;
		gbcTemp.gridx = 1;
		panelTemp.add(lblHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(lblHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(lblHProTp, gbcTemp);
		gbcTemp.gridx = 4;
		panelTemp.add(lblHProHt, gbcTemp);

		//deuxieme ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		this.txtFHProHCm = new JIntegerTextField(3, module.getNbHeureProgramme("CM"));
		this.txtFHProHCm.setAllowsInvalid(false);
		this.txtFHProHCm.addFocusListener(this);
		this.txtFHProHCm.addActionListener(this);
		this.txtFHProHTd = new JIntegerTextField(3, module.getNbHeureProgramme("HT"));
		this.txtFHProHTd.setAllowsInvalid(false);
		this.txtFHProHTd.addFocusListener(this);
		this.txtFHProHTd.addActionListener(this);
		this.txtFHProHTp = new JIntegerTextField(3, module.getNbHeureProgramme("TP"));
		this.txtFHProHTp.setAllowsInvalid(false);
		this.txtFHProHTp.addFocusListener(this);
		this.txtFHProHTp.addActionListener(this);
		this.txtFHProHTut = new JIntegerTextField(3, module.getNbHeureProgramme("HT"));
		this.txtFHProHTut.setAllowsInvalid(false);
		this.txtFHProHTut.addFocusListener(this);
		this.txtFHProHTut.addActionListener(this);

		gbcTemp.gridy = 1;
		gbcTemp.gridx = 1;
		panelTemp.add(this.txtFHProHCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(this.txtFHProHTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(this.txtFHProHTp, gbcTemp);
		gbcTemp.gridx = 4;
		panelTemp.add(this.txtFHProHTut, gbcTemp);

		//troisieme ligne
		gbcTemp.insets = new Insets(10, 5, 1, 10); // Marge autour des composants
		JLabel lblTotal = new JLabel("Total(eqtd)");
		lblTotal.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblTotal.setFont(PageEditionPpp.FONT_LABEL);

		this.txtFTotEqTdHProCm = new JIntegerTextField(3);
		this.txtFTotEqTdHProCm.setEditable(false);
		this.txtFTotEqTdHProCm.setValue((int) (this.txtFHProHCm.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd = new JIntegerTextField(3);
		this.txtFTotEqTdHProTd.setEditable(false);
		this.txtFTotEqTdHProTd.setValue((int) (this.txtFHProHTd.getValue() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp = new JIntegerTextField(3);
		this.txtFTotEqTdHProTp.setEditable(false);
		this.txtFTotEqTdHProTp.setValue((int) (this.txtFHProHTp.getValue() * this.ctrl.getCoefH("TP")));
		this.txtFTotEqTdHProTut = new JIntegerTextField(3);
		this.txtFTotEqTdHProTut.setEditable(false);
		this.txtFTotEqTdHProTut.setValue((int) (this.txtFHProHTp.getValue() * this.ctrl.getCoefH("HT")));

		gbcTemp.gridy = 2;
		gbcTemp.gridx = 0;
		panelTemp.add(lblTotal, gbcTemp);
		gbcTemp.insets = new Insets(5, 1, 1, 0); // Marge autour des composants
		gbcTemp.gridx = 1;
		panelTemp.add(this.txtFTotEqTdHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(this.txtFTotEqTdHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(this.txtFTotEqTdHProTp, gbcTemp);

		//quatrieme ligne
		gbcTemp.insets = new Insets(0, 5, 10, 0); // Marge autour des composants

		JLabel lblPromo = new JLabel("promo");
		lblPromo.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblPromo.setFont(PageEditionPpp.FONT_LABEL);

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
		lblHProSomme.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblHProSomme.setFont(PageEditionPpp.FONT_LABEL);

		gbcSomme.gridy = 0;
		gbcSomme.gridx = 0;
		panelSomme.add(lblHProSomme, gbcSomme);

		//deuxieme ligne
		this.txtFHProSomme = new JIntegerTextField(3);
		this.txtFHProSomme.setValue(this.txtFTotEqTdHProCm.getValue() + this.txtFTotEqTdHProTd.getValue() + this.txtFTotEqTdHProTp.getValue() + this.txtFTotEqTdHProTut.getValue());
		this.txtFHProSomme.setEditable(false);

		gbcSomme.gridy = 1;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFHProSomme, gbcSomme);

		//troisieme ligne
		gbcSomme.insets = new Insets(5, 10, 1, 5); // Marge autour des composants
		this.txtFTotEqTdHProSomme = new JIntegerTextField(3);
		this.txtFTotEqTdHProSomme.setEditable(false);
		this.txtFTotEqTdHProSomme.setValue(this.txtFTotEqTdHProCm.getValue() + this.txtFTotEqTdHProTd.getValue() + this.txtFTotEqTdHProTp.getValue() + this.txtFTotEqTdHProTut.getValue());

		gbcSomme.gridy = 2;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFTotEqTdHProSomme, gbcSomme);

		//quatrieme ligne
		gbcSomme.insets = new Insets(0, 5, 10, 0); // Marge autour des composants
		JLabel labelEspace = new JLabel(" ");
		labelEspace.setForeground(PageEditionPpp.COULEUR_LABEL);
		labelEspace.setFont(PageEditionPpp.FONT_LABEL);

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
		lblValider.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblValider.setFont(PageEditionPpp.FONT_LABEL);
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
		lblPnLocal.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblPnLocal.setFont(PageEditionPpp.FONT_LABEL);

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

		JLabel lblNbHCm = new JLabel("H Cm");
		lblNbHCm.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbHCm.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbHTd = new JLabel("H Td");
		lblNbHTd.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbHTd.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbHTp = new JLabel("H Tp");
		lblNbHTp.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbHTp.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbHTut = new JLabel("H Tut");
		lblNbHTut.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbHTut.setFont(PageEditionPpp.FONT_LABEL);
		JLabel lblNbHPonctu = new JLabel("H Ponctuelle");
		lblNbHPonctu.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblNbHPonctu.setFont(PageEditionPpp.FONT_LABEL);

		gbcRepartition.gridy = 1;
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(lblNbHCm, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(lblNbHTd, gbcRepartition);
		gbcRepartition.gridx = 3;
		panelTmpRepartition.add(lblNbHTp, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(lblNbHTut, gbcRepartition);
		gbcRepartition.gridx = 5;
		panelTmpRepartition.add(lblNbHPonctu, gbcRepartition);

		//deuxieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0);
		JLabel lblTotPromo = new JLabel("Total promo (eqtd)");
		lblTotPromo.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblTotPromo.setFont(PageEditionPpp.FONT_LABEL);

		this.txtFNbHCmSem = new JIntegerTextField(3, module.getNbHeureSemaine("CM"));
		this.txtFNbHCmSem.setAllowsInvalid(false);
		this.txtFNbHCmSem.addFocusListener(this);
		this.txtFNbHCmSem.addActionListener(this);

		this.txtFNbHTdSem = new JIntegerTextField(3, module.getNbHeureSemaine("TD"));
		this.txtFNbHTdSem.setAllowsInvalid(false);
		this.txtFNbHTdSem.addFocusListener(this);
		this.txtFNbHTdSem.addActionListener(this);

		this.txtFNbHTpSem = new JIntegerTextField(3, module.getNbHeureSemaine("TP"));
		this.txtFNbHTpSem.setAllowsInvalid(false);
		this.txtFNbHTpSem.addFocusListener(this);
		this.txtFNbHTpSem.addActionListener(this);

		this.txtFNbHTutSem = new JIntegerTextField(3, module.getNbHeureSemaine("HT"));
		this.txtFNbHTutSem.setAllowsInvalid(false);
		this.txtFNbHTutSem.addFocusListener(this);
		this.txtFNbHTutSem.addActionListener(this);

		this.txtFNbHPonctuSem = new JIntegerTextField(3, module.getNbHeureSemaine("HP"));
		this.txtFNbHPonctuSem.setAllowsInvalid(false);
		this.txtFNbHPonctuSem.addFocusListener(this);
		this.txtFNbHPonctuSem.addActionListener(this);

		gbcRepartition.gridy = 2;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(lblTotPromo, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(this.txtFNbHCmSem, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(this.txtFNbHTdSem, gbcRepartition);
		gbcRepartition.gridx = 3;
		panelTmpRepartition.add(this.txtFNbHTpSem, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(this.txtFNbHTutSem, gbcRepartition);
		gbcRepartition.gridx = 5;
		panelTmpRepartition.add(this.txtFNbHPonctuSem, gbcRepartition);

		//troisieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge
		JLabel lblTotAff = new JLabel("Total affecté (eqtd)");
		lblTotAff.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblTotAff.setFont(PageEditionPpp.FONT_LABEL);

		this.recalculeTotalAffecte();

		this.txtFTotHCmAff = new JIntegerTextField(3, this.nbTotAffHCm);
		this.txtFTotHCmAff.setEditable(false);
		this.txtFTotHTdAff = new JIntegerTextField(3, this.nbTotAffHTd);
		this.txtFTotHTdAff.setEditable(false);
		this.txtFTotHTpAff = new JIntegerTextField(3, this.nbTotAffHTp);
		this.txtFTotHTpAff.setEditable(false);
		this.txtFTotHTutAff = new JIntegerTextField(3, this.nbTotAffHTut);
		this.txtFTotHTutAff.setEditable(false);
		this.txtFTotHPonctuAff = new JIntegerTextField(3, this.nbTotAffHPonctu);
		this.txtFTotHPonctuAff.setEditable(false);

		gbcRepartition.gridy = 3;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(lblTotAff, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(this.txtFTotHCmAff, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(this.txtFTotHTdAff, gbcRepartition);
		gbcRepartition.gridx = 3;
		panelTmpRepartition.add(this.txtFTotHTpAff, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(this.txtFTotHTutAff, gbcRepartition);
		gbcRepartition.gridx = 5;
		panelTmpRepartition.add(this.txtFTotHPonctuAff, gbcRepartition);

		//Panel Somme\\
		JPanel panelSommeRepartition = new JPanel();
		panelSommeRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcSommeRepartition = new GridBagConstraints();
		gbcSommeRepartition.anchor = GridBagConstraints.CENTER;
		gbcSommeRepartition.insets = new Insets(0, 10, 1, 5); // Marge autour des composants

		//premier ligne
		JLabel lblSommeSem = new JLabel("∑");
		lblSommeSem.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblSommeSem.setFont(PageEditionPpp.FONT_LABEL);

		gbcSommeRepartition.gridy = 0;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(lblSommeSem, gbcSommeRepartition);

		//deuxieme ligne
		this.txtFSom1 = new JIntegerTextField(3);
		this.txtFSom1.setValue(this.txtFNbHCmSem.getValue() + this.txtFNbHTdSem.getValue() + this.txtFNbHTpSem.getValue() + this.txtFNbHTutSem.getValue() + this.txtFNbHPonctuSem.getValue());
		this.txtFSom1.setEditable(false);

		gbcSommeRepartition.gridy = 1;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFSom1, gbcSommeRepartition);

		//troisieme ligne
		this.txtFTotSom2 = new JIntegerTextField(3);
		this.txtFTotSom2.setValue(this.txtFTotHCmAff.getValue() + this.txtFTotHTdAff.getValue() + this.txtFTotHTpAff.getValue() + this.txtFTotHTutAff.getValue() + this.txtFTotHPonctuAff.getValue());
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
		lblListeAffectation.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblListeAffectation.setFont(PageEditionPpp.FONT_LABEL);

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
		lblRepartition.setForeground(PageEditionPpp.COULEUR_LABEL);
		lblRepartition.setFont(PageEditionPpp.FONT_LABEL);

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

		this.recalcule();

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
			String regex = "^P[1-6]\\.\\d{2}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.txtFCode.getText());
			if(matcher.matches())
			{
				regex   = "^P[" + this.MODULE.getSemestre().getId() + "]\\.\\d{2}$";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(this.txtFCode.getText());
				if(!matcher.matches())
				{
					JOptionPane.showMessageDialog(this.mere, "Vous avez un semestre différent pour votre code que celui de votre module.", "Information Module entrer différente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, "Le code doit être sous la format S[1-6].[1-99] !", "ERREUR ENTRER CODE", JOptionPane.ERROR_MESSAGE);
				this.txtFCode.setText("PX.XX");
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
			if(this.txtFCode.getText().equals("PX.XX"))
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
				int rep = JOptionPane.showConfirmDialog(this.mere, "Voulez-vous vraiment supprimer cette Affectation ?", "Suppression", JOptionPane.YES_NO_OPTION);
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
			//Verification des heures défini par rapport au heures programme
			if(this.txtFHProHCm.getValue() < this.txtFNbHCmSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTd.getValue() < this.txtFNbHTdSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTp.getValue() < this.txtFNbHTpSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProSomme.getValue() < this.txtFSom1.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure total que vous avez défini est supérieur au nombre d'heure total programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			//Verification des heures affecté par rapport au heures défini
			else if(this.txtFNbHCmSem.getValue() < this.txtFTotHCmAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTdSem.getValue() < this.txtFTotHTdAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTpSem.getValue() < this.txtFTotHTpAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHPonctuSem.getValue() < this.txtFTotHPonctuAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure Ponctuelle affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			//Verification des heures défini par rapport au heures programme
			if(this.txtFHProHCm.getValue() < this.txtFNbHCmSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTd.getValue() < this.txtFNbHTdSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTp.getValue() < this.txtFNbHTpSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProSomme.getValue() < this.txtFSom1.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure total que vous avez défini est supérieur au nombre d'heure total programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			else if(this.txtFHProHTut.getValue() > this.txtFNbHTutSem.getValue()) 
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHCm.getValue() > this.txtFNbHCmSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTd.getValue() > this.txtFNbHTdSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProHTp.getValue() > this.txtFNbHTpSem.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFHProSomme.getValue() > this.txtFSom1.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure total que vous avez défini est inférieur au nombre d'heure total programme !", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			//Verification des heures affecté par rapport au heures défini
			else if(this.txtFNbHCmSem.getValue() < this.txtFTotHCmAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTdSem.getValue() < this.txtFTotHTdAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTpSem.getValue() < this.txtFTotHTpAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHPonctuSem.getValue() < this.txtFTotHPonctuAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure Ponctuelle affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			else if(this.txtFNbHTutSem.getValue() > this.txtFTotHTutAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHCmSem.getValue() > this.txtFTotHCmAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure CM affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTdSem.getValue() > this.txtFTotHTdAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TD affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHTpSem.getValue() > this.txtFTotHTpAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TP affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if(this.txtFNbHPonctuSem.getValue() > this.txtFTotHPonctuAff.getValue())
			{
				JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure Ponctuelle affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
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
		this.MODULE.getProgramme().getItem("CM").setNbHPn(this.txtFHProHCm.getValue());
		this.MODULE.getProgramme().getItem("TD").setNbHPn(this.txtFHProHTd.getValue());
		this.MODULE.getProgramme().getItem("TP").setNbHPn(this.txtFHProHTp.getValue());
		this.MODULE.getProgramme().getItem("HT").setNbHPn(this.txtFHProHTut.getValue());
		this.MODULE.getProgramme().getItem("CM").setNbHeure(this.txtFNbHCmSem.getValue());
		this.MODULE.getProgramme().getItem("TD").setNbHeure(this.txtFNbHTdSem.getValue());
		this.MODULE.getProgramme().getItem("TP").setNbHeure(this.txtFNbHTpSem.getValue());
		this.MODULE.getProgramme().getItem("HT").setNbHeure(this.txtFNbHTutSem.getValue());
		this.MODULE.getProgramme().getItem("HP").setNbHeure(this.txtFNbHPonctuSem.getValue());
	}

	private void recalcule()
	{
		this.txtFTotEqTdHProCm.setValue((int) (this.txtFHProHCm.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd.setValue((int) (this.txtFHProHTd.getValue() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp.setValue((int) (this.txtFHProHTp.getValue() * this.ctrl.getCoefH("TP")));
		this.txtFTotEqTdHProTut.setValue((int) (this.txtFHProHTut.getValue() * this.ctrl.getCoefH("HT")));
		this.txtFHProSomme.setValue(this.txtFHProHCm.getValue() + this.txtFHProHTd.getValue() + this.txtFHProHTp.getValue() + this.txtFHProHTut.getValue());
		this.txtFTotEqTdHProSomme.setValue(this.txtFTotEqTdHProCm.getValue() + this.txtFTotEqTdHProTd.getValue() + this.txtFTotEqTdHProTp.getValue() + this.txtFTotEqTdHProTut.getValue());

		this.recalculeTotalAffecte();
		this.txtFTotHCmAff.setValue(this.nbTotAffHCm);
		this.txtFTotHTdAff.setValue(this.nbTotAffHTd);
		this.txtFTotHTpAff.setValue(this.nbTotAffHTp);
		this.txtFTotHTutAff.setValue(this.nbTotAffHTut);
		this.txtFTotHPonctuAff.setValue(this.nbTotAffHPonctu);
		this.txtFSom1.setValue(this.txtFNbHCmSem.getValue() + this.txtFNbHTdSem.getValue() + this.txtFNbHTpSem.getValue() + this.txtFNbHTutSem.getValue() + this.txtFNbHPonctuSem.getValue());
		this.txtFTotSom2.setValue(this.txtFTotHCmAff.getValue() + this.txtFTotHTdAff.getValue() + this.txtFTotHTpAff.getValue() + this.txtFTotHTutAff.getValue() + this.txtFTotHPonctuAff.getValue());

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
		this.nbTotAffHCm = 0;
		this.nbTotAffHTd = 0;
		this.nbTotAffHTp = 0;
		this.nbTotAffHTut = 0;
		this.nbTotAffHPonctu = 0;

		for (Affectation aff : lstAffectation)
		{
			if (aff.getCategorieHeure().getNom().equals("CM"))
			{
				this.nbTotAffHCm += aff.getNbHeure();
			}
			else if (aff.getCategorieHeure().getNom().equals("TD"))
			{
				this.nbTotAffHTd += aff.getNbHeure();
			}
			else if (aff.getCategorieHeure().getNom().equals("TP"))
			{
				this.nbTotAffHTp += aff.getNbHeure();
			}
			else if (aff.getCategorieHeure().getNom().equals("HT"))
			{
				this.nbTotAffHTut += aff.getNbHeure();
			}
			else
			{
				this.nbTotAffHPonctu += aff.getNbHeure();
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