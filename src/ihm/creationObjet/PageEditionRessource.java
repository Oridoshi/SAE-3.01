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
public class PageEditionRessource extends JPanel implements ActionListener, FocusListener
{
	private static final Color COULEUR_LABEL = new Color(158,158,158);
	private static final Font FONT_LABEL = new Font("Arial", 0, 8);

	private final Module MODULE;

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelOuest;

	private FrameIhm mere;
	private Controleur ctrl;

	private int nbTotAffHCm;
	private int nbTotAffHTd;
	private int nbTotAffHTp;

	private JTextField txtFCode;
	private JTextField txtFLibelLong;
	private JTextField txtFLibelCour;
	private JTextField txtFTyMo;
	private JTextField txtFSeme;

	private JIntegerTextField txtFHProCm;
	private JIntegerTextField txtFHProTd;
	private JIntegerTextField txtFHProTp;
	private JIntegerTextField txtFHProSomme;
	private JIntegerTextField txtFTotEqTdHProCm;
	private JIntegerTextField txtFTotEqTdHProTd;
	private JIntegerTextField txtFTotEqTdHProTp;
	private JIntegerTextField txtFTotEqTdHProSomme;
	private JIntegerTextField txtFNbSemCm;
	private JIntegerTextField txtFNbHCmSem;
	private JIntegerTextField txtFNbSemTd;
	private JIntegerTextField txtFNbHTdSem;
	private JIntegerTextField txtFNbSemTp;
	private JIntegerTextField txtFNbHTpSem;
	private JIntegerTextField txtFTotCm1;
	private JIntegerTextField txtFTotTd1;
	private JIntegerTextField txtFTotTp1;
	private JIntegerTextField txtFSom1;
	private JIntegerTextField txtFHPonctu1;
	private JIntegerTextField txtFTotCm2;
	private JIntegerTextField txtFTotTd2;
	private JIntegerTextField txtFTotTp2;
	private JIntegerTextField txtFHPonctu2;
	private JIntegerTextField txtFTotSom2;
	private JIntegerTextField txtFTotCm3;
	private JIntegerTextField txtFTotTd3;
	private JIntegerTextField txtFTotTp3;
	private JIntegerTextField txtFHPonctu3;
	private JIntegerTextField txtFTotSom3;

	private List<Affectation> lstAffectation;
	private JTable tableAffectation;
	private int nbTotAffHPonctu;
	private JButton btnAjouterAffectation;
	private JButton btnSupprimerAffectation;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JCheckBox chkValider;

	private boolean validerAvantChangement;
	private ArrayList<Affectation> lstAffectationSupp;

	public static PageEditionRessource factorieCreationRessource(Controleur ctrl, FrameIhm mere, Semestre semestre)
	{
		PageEditionRessource page = new PageEditionRessource(ctrl, mere, semestre, new Module("RX.XX", semestre, ctrl.getCategorieModule("Ressource"), false, "", ""), null);
		return page;
	}

	public static PageEditionRessource factorieEditionRessource(Controleur ctrl, FrameIhm mere, Module module)
	{
		PageEditionRessource page = new PageEditionRessource(ctrl, mere, module.getSemestre(), module, module.getLstAffectation());
		return page;
	}

	private PageEditionRessource(Controleur ctrl, FrameIhm mere, Semestre semestre, Module module, List<Affectation> lstAffectation)
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
		lblTyMo.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTyMo.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblSeme = new JLabel("Semestre");
		lblSeme.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblSeme.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblCode = new JLabel("Code");
		lblCode.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblCode.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblLibelLong = new JLabel("Libellé Long");
		lblLibelLong.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblLibelLong.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblLibelCour = new JLabel("Libellé Court");
		lblLibelCour.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblLibelCour.setFont(PageEditionRessource.FONT_LABEL);

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
		lblNbEtd.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbEtd.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbGrpTd = new JLabel("Nb Grp TD");
		lblNbGrpTd.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbGrpTd.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbGrpTp = new JLabel("Nb Grp TP");
		lblNbGrpTp.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbGrpTp.setFont(PageEditionRessource.FONT_LABEL);

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

		JLabel lblHProCm = new JLabel("CM");
		lblHProCm.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblHProCm.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblHProTd = new JLabel("TD");
		lblHProTd.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblHProTd.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblHProTp = new JLabel("TP");
		lblHProTp.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblHProTp.setFont(PageEditionRessource.FONT_LABEL);

		gbcTemp.gridy = 0;
		gbcTemp.gridx = 1;
		panelTemp.add(lblHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(lblHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(lblHProTp, gbcTemp);

		//deuxieme ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		this.txtFHProCm = new JIntegerTextField(3, module.getNbHeureProgramme("CM"));
		this.txtFHProCm.setAllowsInvalid(false);
		this.txtFHProCm.addFocusListener(this);
		this.txtFHProCm.addActionListener(this);
		this.txtFHProTd = new JIntegerTextField(3, module.getNbHeureProgramme("TD"));
		this.txtFHProTd.setAllowsInvalid(false);
		this.txtFHProTd.addFocusListener(this);
		this.txtFHProTd.addActionListener(this);
		this.txtFHProTp = new JIntegerTextField(3, module.getNbHeureProgramme("TP"));
		this.txtFHProTp.setAllowsInvalid(false);
		this.txtFHProTp.addFocusListener(this);
		this.txtFHProTp.addActionListener(this);

		gbcTemp.gridy = 1;
		gbcTemp.gridx = 1;
		panelTemp.add(txtFHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(txtFHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(txtFHProTp, gbcTemp);

		//troisieme ligne
		gbcTemp.insets = new Insets(10, 5, 1, 10); // Marge autour des composants

		JLabel lblTotal = new JLabel("Total(eqtd)");
		lblTotal.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTotal.setFont(PageEditionRessource.FONT_LABEL);

		this.txtFTotEqTdHProCm = new JIntegerTextField(3);
		this.txtFTotEqTdHProCm.setEditable(false);
		this.txtFTotEqTdHProCm.setValue((int) (this.txtFHProCm.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd = new JIntegerTextField(3);
		this.txtFTotEqTdHProTd.setEditable(false);
		this.txtFTotEqTdHProTd.setValue((int) (this.txtFHProTd.getValue() * this.MODULE.getSemestre().getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp = new JIntegerTextField(3);
		this.txtFTotEqTdHProTp.setEditable(false);
		this.txtFTotEqTdHProTp.setValue((int) (this.txtFHProTp.getValue() * this.MODULE.getSemestre().getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		
		gbcTemp.gridx = 0;
		gbcTemp.gridy = 2;
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
		lblPromo.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblPromo.setFont(PageEditionRessource.FONT_LABEL);

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
		lblHProSomme.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblHProSomme.setFont(PageEditionRessource.FONT_LABEL);

		gbcSomme.gridy = 0;
		gbcSomme.gridx = 0;
		panelSomme.add(lblHProSomme, gbcSomme);

		//deuxieme ligne
		this.txtFHProSomme = new JIntegerTextField(3);
		this.txtFHProSomme.setValue(this.txtFHProCm.getValue() + this.txtFHProTd.getValue() + this.txtFHProTp.getValue());
		this.txtFHProSomme.setEditable(false);

		gbcSomme.gridy = 1;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFHProSomme, gbcSomme);

		//troisieme ligne
		gbcSomme.insets = new Insets(5, 10, 1, 5); // Marge autour des composants
		this.txtFTotEqTdHProSomme = new JIntegerTextField(3);
		this.txtFTotEqTdHProSomme.setEditable(false);
		this.txtFTotEqTdHProSomme.setValue(this.txtFTotEqTdHProCm.getValue() + this.txtFTotEqTdHProTd.getValue() + this.txtFTotEqTdHProTp.getValue());

		gbcSomme.gridy = 2;
		gbcSomme.gridx = 0;
		panelSomme.add(this.txtFTotEqTdHProSomme, gbcSomme);

		//quatrieme ligne
		gbcSomme.insets = new Insets(0, 5, 10, 0); // Marge autour des composants
		JLabel labelEspace = new JLabel(" ");
		labelEspace.setForeground(PageEditionRessource.COULEUR_LABEL);
		labelEspace.setFont(PageEditionRessource.FONT_LABEL);

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
		lblValider.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblValider.setFont(PageEditionRessource.FONT_LABEL);
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
		lblPnLocal.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblPnLocal.setFont(PageEditionRessource.FONT_LABEL);

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
		
		//premier ligne
		gbcRepartition.anchor = GridBagConstraints.CENTER;
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		JLabel lblCmSem = new JLabel("CM");
		lblCmSem.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblCmSem.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblTdSem = new JLabel("TD");
		lblTdSem.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTdSem.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblTpSem = new JLabel("TP");
		lblTpSem.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTpSem.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblHponctu = new JLabel("Heures");
		lblHponctu.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblHponctu.setFont(PageEditionRessource.FONT_LABEL);

		gbcRepartition.gridy = 0;
		gbcRepartition.gridx = 0;
		gbcRepartition.gridwidth = 2;
		panelTmpRepartition.add(lblCmSem, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(lblTdSem, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(lblTpSem, gbcRepartition);
		gbcRepartition.gridx = 10;
		gbcRepartition.gridwidth = 1;
		panelTmpRepartition.add(lblHponctu, gbcRepartition);
		gbcRepartition.gridx = 11;

		//deuxieme ligne
		gbcRepartition.anchor = GridBagConstraints.WEST;

		JLabel lblNbSem1 = new JLabel("Nb sem");
		lblNbSem1.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbSem1.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbHSem1 = new JLabel("Nb h/sem");
		lblNbHSem1.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbHSem1.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbSem2 = new JLabel("Nb sem");
		lblNbSem2.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbSem2.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbHSem2 = new JLabel("Nb h/sem");
		lblNbHSem2.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbHSem2.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbSem3 = new JLabel("Nb sem");
		lblNbSem3.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbSem3.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblNbHSem3 = new JLabel("Nb h/sem");
		lblNbHSem3.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblNbHSem3.setFont(PageEditionRessource.FONT_LABEL);

		JLabel lblCmRepar = new JLabel("CM");
		lblCmRepar.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblCmRepar.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblTdRepar = new JLabel("TD");
		lblTdRepar.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTdRepar.setFont(PageEditionRessource.FONT_LABEL);
		JLabel lblTpRepar = new JLabel("TP");
		lblTpRepar.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTpRepar.setFont(PageEditionRessource.FONT_LABEL);

		JLabel lblhPonctu = new JLabel("ponctuelles");
		lblhPonctu.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblhPonctu.setFont(PageEditionRessource.FONT_LABEL);

		gbcRepartition.gridy = 1;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(lblNbSem1, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(lblNbHSem1, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(lblNbSem2, gbcRepartition);
		gbcRepartition.gridx = 3;
		panelTmpRepartition.add(lblNbHSem2, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(lblNbSem3, gbcRepartition);
		gbcRepartition.gridx = 5;
		panelTmpRepartition.add(lblNbHSem3, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(lblCmRepar, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(lblTdRepar, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(lblTpRepar, gbcRepartition);
		gbcRepartition.insets = new Insets(0, 10, 1, 0); // Marge
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(lblhPonctu, gbcRepartition);

		//troisieme ligne
		gbcRepartition.anchor = GridBagConstraints.CENTER;
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge
		this.txtFNbSemCm = new JIntegerTextField(3, module.getNbSemaine("CM"));
		this.txtFNbSemCm.setAllowsInvalid(false);
		this.txtFNbSemCm.addFocusListener(this);
		this.txtFNbSemCm.addActionListener(this);
		this.txtFNbHCmSem = new JIntegerTextField(3, module.getNbHeureSemaine("CM"));
		this.txtFNbHCmSem.setAllowsInvalid(false);
		this.txtFNbHCmSem.addFocusListener(this);
		this.txtFNbHCmSem.addActionListener(this);

		this.txtFNbSemTd = new JIntegerTextField(3, module.getNbSemaine("TD"));
		this.txtFNbSemTd.setAllowsInvalid(false);
		this.txtFNbSemTd.addFocusListener(this);
		this.txtFNbSemTd.addActionListener(this);
		this.txtFNbHTdSem = new JIntegerTextField(3, module.getNbHeureSemaine("TD"));
		this.txtFNbHTdSem.setAllowsInvalid(false);
		this.txtFNbHTdSem.addFocusListener(this);
		this.txtFNbHTdSem.addActionListener(this);

		this.txtFNbSemTp = new JIntegerTextField(3, module.getNbSemaine("TP"));
		this.txtFNbSemTp.setAllowsInvalid(false);
		this.txtFNbSemTp.addFocusListener(this);
		this.txtFNbSemTp.addActionListener(this);
		this.txtFNbHTpSem = new JIntegerTextField(3, module.getNbHeureSemaine("TP"));
		this.txtFNbHTpSem.setAllowsInvalid(false);
		this.txtFNbHTpSem.addFocusListener(this);
		this.txtFNbHTpSem.addActionListener(this);

		this.txtFTotCm1 = new JIntegerTextField(3);
		this.txtFTotCm1.setValue(this.txtFNbSemCm.getValue() * this.txtFNbHCmSem.getValue());
		this.txtFTotCm1.setEditable(false);
		this.txtFTotTd1 = new JIntegerTextField(3);
		this.txtFTotTd1.setValue(this.txtFNbSemTd.getValue() * this.txtFNbHTdSem.getValue());
		this.txtFTotTd1.setEditable(false);
		this.txtFTotTp1 = new JIntegerTextField(3);
		this.txtFTotTp1.setValue(this.txtFNbSemTp.getValue() * this.txtFNbHTpSem.getValue());
		this.txtFTotTp1.setEditable(false);
		this.txtFHPonctu1 = new JIntegerTextField(3, module.getNbHeureSemaine("HP"));
		this.txtFHPonctu1.setAllowsInvalid(false);
		this.txtFHPonctu1.addFocusListener(this);
		this.txtFHPonctu1.addActionListener(this);

		gbcRepartition.gridy = 2;
		gbcRepartition.gridx = 0;
		panelTmpRepartition.add(this.txtFNbSemCm, gbcRepartition);
		gbcRepartition.gridx = 1;
		panelTmpRepartition.add(this.txtFNbHCmSem, gbcRepartition);
		gbcRepartition.gridx = 2;
		panelTmpRepartition.add(this.txtFNbSemTd, gbcRepartition);
		gbcRepartition.gridx = 3;
		panelTmpRepartition.add(this.txtFNbHTdSem, gbcRepartition);
		gbcRepartition.gridx = 4;
		panelTmpRepartition.add(this.txtFNbSemTp, gbcRepartition);
		gbcRepartition.gridx = 5;
		panelTmpRepartition.add(this.txtFNbHTpSem, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(this.txtFTotCm1, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(this.txtFTotTd1, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(this.txtFTotTp1, gbcRepartition);
		gbcRepartition.insets = new Insets(0, 10, 1, 0); // Marge
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(this.txtFHPonctu1, gbcRepartition);

		//quatrieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge
		JLabel lblTotPromo = new JLabel("Total promo (eqtd)");
		lblTotPromo.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTotPromo.setFont(PageEditionRessource.FONT_LABEL);
		this.txtFTotCm2 = new JIntegerTextField(3);
		this.txtFTotCm2.setValue((int) (this.txtFTotCm1.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotCm2.setEditable(false);
		this.txtFTotTd2 = new JIntegerTextField(3);
		this.txtFTotTd2.setValue((int) (this.txtFTotTd1.getValue() * this.MODULE.getSemestre().getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotTd2.setEditable(false);
		this.txtFTotTp2 = new JIntegerTextField(3);
		this.txtFTotTp2.setValue((int) (this.txtFTotTp1.getValue() * this.MODULE.getSemestre().getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotTp2.setEditable(false);
		this.txtFHPonctu2 = new JIntegerTextField(3);
		this.txtFHPonctu2.setValue((int) (this.txtFHPonctu1.getValue() * this.MODULE.getSemestre().getNbGroupeTd()));
		this.txtFHPonctu2.setEditable(false);

		gbcRepartition.gridy = 3;
		gbcRepartition.gridx = 6;
		panelTmpRepartition.add(lblTotPromo, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(txtFTotCm2, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(txtFTotTd2, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(txtFTotTp2, gbcRepartition);
		gbcRepartition.insets = new Insets(0, 10, 1, 0); // Marge
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(txtFHPonctu2, gbcRepartition);

		//cinquieme ligne
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge
		this.recalculeTotalAffecte();
		JLabel lblTotAffect = new JLabel("Total affecté (eqtd)");
		lblTotAffect.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblTotAffect.setFont(PageEditionRessource.FONT_LABEL);
		this.txtFTotCm3 = new JIntegerTextField(3);
		this.txtFTotCm3.setText("" + this.nbTotAffHCm);
		this.txtFTotCm3.setEditable(false);
		this.txtFTotTd3 = new JIntegerTextField(3);
		this.txtFTotTd3.setText("" + this.nbTotAffHTd);
		this.txtFTotTd3.setEditable(false);
		this.txtFTotTp3 = new JIntegerTextField(3);
		this.txtFTotTp3.setText("" + this.nbTotAffHTp);
		this.txtFTotTp3.setEditable(false);
		this.txtFHPonctu3 = new JIntegerTextField(3);
		this.txtFHPonctu3.setText("" + this.nbTotAffHPonctu);
		this.txtFHPonctu3.setEditable(false);

		gbcRepartition.gridy = 4;
		gbcRepartition.gridx = 6;
		panelTmpRepartition.add(lblTotAffect, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(txtFTotCm3, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(txtFTotTd3, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(txtFTotTp3, gbcRepartition);
		gbcRepartition.insets = new Insets(0, 10, 1, 0); // Marge
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(txtFHPonctu3, gbcRepartition);

		//Panel Somme\\
		JPanel panelSommeRepartition = new JPanel();
		panelSommeRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcSommeRepartition = new GridBagConstraints();
		gbcSommeRepartition.anchor = GridBagConstraints.CENTER;
		gbcSommeRepartition.insets = new Insets(0, 10, 1, 5); // Marge autour des composants

		//premier ligne
		JLabel lblSommeSem = new JLabel("∑");
		lblSommeSem.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblSommeSem.setFont(PageEditionRessource.FONT_LABEL);

		gbcSommeRepartition.gridy = 0;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(lblSommeSem, gbcSommeRepartition);

		//deuxieme ligne
		JLabel lblEspace = new JLabel(" ");
		lblEspace.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblEspace.setFont(PageEditionRessource.FONT_LABEL);

		gbcSommeRepartition.gridy = 1;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(lblEspace, gbcSommeRepartition);

		//troisieme ligne
		this.txtFSom1 = new JIntegerTextField(3);
		this.txtFSom1.setValue(this.txtFTotCm1.getValue() + this.txtFTotTd1.getValue() + this.txtFTotTp1.getValue() + this.txtFHPonctu1.getValue());
		this.txtFSom1.setEditable(false);

		gbcSommeRepartition.gridy = 2;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFSom1, gbcSommeRepartition);

		//quatrieme ligne
		this.txtFTotSom2 = new JIntegerTextField(3);
		this.txtFTotSom2.setValue(txtFTotCm2.getValue() + txtFTotTd2.getValue() + txtFTotTp2.getValue() + txtFHPonctu2.getValue());
		this.txtFTotSom2.setEditable(false);

		gbcSommeRepartition.gridy = 3;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFTotSom2, gbcSommeRepartition);

		//cinquieme ligne
		this.txtFTotSom3 = new JIntegerTextField(3);
		this.txtFTotSom3.setText("" + (int) (Integer.parseInt(txtFTotCm3.getText()) + Integer.parseInt(txtFTotTd3.getText()) + Integer.parseInt(txtFTotTp3.getText()) + Integer.parseInt(txtFHPonctu3.getText())));
		this.txtFTotSom3.setEditable(false);

		gbcSommeRepartition.gridy = 4;
		gbcSommeRepartition.gridx = 0;
		panelSommeRepartition.add(this.txtFTotSom3, gbcSommeRepartition);

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
		lblListeAffectation.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblListeAffectation.setFont(PageEditionRessource.FONT_LABEL);

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
		lblRepartition.setForeground(PageEditionRessource.COULEUR_LABEL);
		lblRepartition.setFont(PageEditionRessource.FONT_LABEL);

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
			String regex = "^R[1-6]\\.\\d{2}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.txtFCode.getText());
			if(matcher.matches())
			{
				regex   = "^R[" + this.MODULE.getSemestre().getId() + "]\\.\\d{2}$";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(this.txtFCode.getText());
				if(!matcher.matches())
				{
					JOptionPane.showMessageDialog(this.mere, "Vous avez un semestre différent pour votre code que celui de votre module.", "Information Module entrer différente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, "Le code doit être sous la format R[1-6].[1-99] !", "ERREUR ENTRER CODE", JOptionPane.ERROR_MESSAGE);
				this.txtFCode.setText("RX.XX");
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
			if(this.txtFCode.getText().equals("RX.XX"))
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
		// if(selected)
		// {
		// 	//Verification des heures défini par rapport au heures programme
		// 	if(this.txtFHProHReh.getValue() < this.txtFNbHRehSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	//Verification des heures affecté par rapport au heures défini
		// 	else if(this.txtFNbHRehSem.getValue() < this.txtFTotHRehAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else
		// 	{
		// 		return true;
		// 	}
		// }
		// else
		// {
		// 	//Verification des heures défini par rapport au heures programme
		// 	if(this.txtFHProHReh.getValue() < this.txtFNbHRehSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHReh.getValue() > this.txtFNbHRehSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() > this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() < this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est supérieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	if(this.txtFHProHTut.getValue() > this.txtFNbHTutSem.getValue()) 
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT que vous avez défini est inférieur au nombre d'heure programme!", "ERREUR HEURE PROGRAMME", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	//Verification des heures affecté par rapport au heures défini
		// 	else if(this.txtFNbHRehSem.getValue() < this.txtFTotHRehAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHRehSem.getValue() > this.txtFTotHRehAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure REH affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() > this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() < this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est supérieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else if(this.txtFNbHTutSem.getValue() > this.txtFTotHTutAff.getValue())
		// 	{
		// 		JOptionPane.showMessageDialog(this.mere, "Le nombre d'heure TUT affecté est inférieur au nombre d'heure programmé !", "ERREUR HEURE AFFECTE", JOptionPane.ERROR_MESSAGE);
		// 		return false;
		// 	}
		// 	else
		// 	{
		// 		return true;
		// 	}
		// }

		return true;
	}

	private void majModule()
	{
		//Definition des informations du module
		this.MODULE.setLibelleLong(this.txtFLibelLong.getText());
		this.MODULE.setLibelleCourt(this.txtFLibelCour.getText());

		//Definition des heures du module
		this.MODULE.getProgramme().getItem("CM").setNbHPn(this.txtFHProCm.getValue());
		this.MODULE.getProgramme().getItem("TD").setNbHPn(this.txtFHProTd.getValue());
		this.MODULE.getProgramme().getItem("TP").setNbHPn(this.txtFHProTp.getValue());
		this.MODULE.getProgramme().getItem("CM").setNbHeure(this.txtFNbHCmSem.getValue());
		this.MODULE.getProgramme().getItem("TD").setNbHeure(this.txtFNbHTdSem.getValue());
		this.MODULE.getProgramme().getItem("HP").setNbHeure(this.txtFHPonctu1.getValue());
		this.MODULE.getProgramme().getItem("TP").setNbHeure(this.txtFNbHTpSem.getValue());
		this.MODULE.getProgramme().getItem("CM").setNbSemaine(this.txtFNbSemCm.getValue());
		this.MODULE.getProgramme().getItem("TD").setNbSemaine(this.txtFNbSemTd.getValue());
		this.MODULE.getProgramme().getItem("TP").setNbSemaine(this.txtFNbSemTp.getValue());
	}

	private void recalcule()
	{
		this.txtFHProSomme.setValue(this.txtFHProCm.getValue() + this.txtFHProTd.getValue() + this.txtFHProTp.getValue());
		this.txtFTotEqTdHProCm.setValue((int) (this.txtFHProCm.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd.setValue((int) (this.txtFHProTd.getValue() * this.MODULE.getSemestre().getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp.setValue((int) (this.txtFHProTp.getValue() * this.MODULE.getSemestre().getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotEqTdHProSomme.setValue((int) (this.txtFTotEqTdHProCm.getValue() + this.txtFTotEqTdHProTd.getValue() + this.txtFTotEqTdHProTp.getValue()));
		this.txtFTotCm1.setValue(this.txtFNbSemCm.getValue() * this.txtFNbHCmSem.getValue());
		this.txtFTotTd1.setValue(this.txtFNbSemTd.getValue() * this.txtFNbHTdSem.getValue());
		this.txtFTotTp1.setValue(this.txtFNbSemTp.getValue() * this.txtFNbHTpSem.getValue());
		this.txtFSom1.setValue(this.txtFTotCm1.getValue() + this.txtFTotTd1.getValue() + this.txtFTotTp1.getValue() + this.txtFHPonctu1.getValue());
		this.txtFTotCm2.setValue((int) (this.txtFTotCm1.getValue() * this.ctrl.getCoefH("CM")));
		this.txtFTotTd2.setValue((int) (this.txtFTotTd1.getValue() * this.MODULE.getSemestre().getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotTp2.setValue((int) (this.txtFTotTp1.getValue() * this.MODULE.getSemestre().getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFHPonctu2.setValue((int) (this.txtFHPonctu1.getValue() * this.MODULE.getSemestre().getNbGroupeTd()));
		this.txtFTotSom2.setValue(this.txtFTotCm2.getValue() + this.txtFTotTd2.getValue() + this.txtFTotTp2.getValue() + this.txtFHPonctu2.getValue());
		this.recalculeTotalAffecte();
		this.txtFTotCm3.setValue(this.nbTotAffHCm);
		this.txtFTotTd3.setValue(this.nbTotAffHTd);
		this.txtFTotTp3.setValue(this.nbTotAffHTp);
		this.txtFHPonctu3.setValue(this.nbTotAffHPonctu);
		this.txtFTotSom3.setValue(this.nbTotAffHCm + this.nbTotAffHTd + this.nbTotAffHTp + this.nbTotAffHPonctu);


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
		this.nbTotAffHPonctu = 0;

		for (Affectation aff : lstAffectation)
		{
			if (aff.getCategorieHeure().getNom().equals("CM"))
			{
				if(aff.getNbHeure() != null)
					this.nbTotAffHCm += aff.getNbHeure();
				else
					this.nbTotAffHCm += aff.getNbGroupe() * aff.getNbSemaine();
			}
			else if (aff.getCategorieHeure().getNom().equals("TD"))
			{
				if(aff.getNbHeure() != null)
					this.nbTotAffHTd += aff.getNbHeure();
				else
					this.nbTotAffHTd += aff.getNbGroupe() * aff.getNbSemaine();
			}
			else if (aff.getCategorieHeure().getNom().equals("TP"))
			{
				if(aff.getNbHeure() != null)
					this.nbTotAffHTp += aff.getNbHeure();
				else
					this.nbTotAffHTp += aff.getNbGroupe() * aff.getNbSemaine();
			}
			else
			{
				if(aff.getNbHeure() != null)
					this.nbTotAffHPonctu += aff.getNbHeure();
				else
					this.nbTotAffHPonctu += aff.getNbGroupe() * aff.getNbSemaine();
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