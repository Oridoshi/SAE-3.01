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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
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
import javax.swing.text.NumberFormatter;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.model.Affectation;
import metier.model.Semestre;

/**
 * PageEditionModule
 */
public class PageCreationRessource extends JPanel implements ActionListener, FocusListener
{
	private static final Color COULEUR_LABEL = new Color(158,158,158);
	private static final Font FONT_LABEL = new Font("Arial", 0, 8);

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelOuest;

	private JFrame mere;
	private Controleur ctrl;
	private Semestre semestre;

	private int nbTotAffHCm;
	private int nbTotAffHTd;
	private int nbTotAffHTp;

	private JTextField txtFCode;
	private JTextField txtFLibelLong;
	private JTextField txtFLibelCour;
	private JTextField txtFSeme;
	private JTextField txtFHProCm;
	private JTextField txtFHProTd;
	private JTextField txtFHProTp;
	private JTextField txtFHProSomme;
	private JTextField txtFTotEqTdHProCm;
	private JTextField txtFTotEqTdHProTd;
	private JTextField txtFTotEqTdHProTp;
	private JTextField txtFTotEqTdHProSomme;
	private JTextField txtFNbSemCm;
	private JTextField txtFNbHCmSem;
	private JTextField txtFNbSemTd;
	private JTextField txtFNbHTdSem;
	private JTextField txtFNbSemTp;
	private JTextField txtFNbHTpSem;
	private JTextField txtFTotCm1;
	private JTextField txtFTotTd1;
	private JTextField txtFTotTp1;
	private JTextField txtFSom1;
	private JTextField txtFHPonctu1;
	private JTextField txtFTotCm2;
	private JTextField txtFTotTd2;
	private JTextField txtFTotTp2;
	private JTextField txtFHPonctu2;
	private JTextField txtFTotSom2;
	private JTextField txtFTotCm3;
	private JTextField txtFTotTd3;
	private JTextField txtFTotTp3;
	private JTextField txtFHPonctu3;
	private JTextField txtFTotSom3;
	
	private List<Affectation> lstAffectation;
	private JTable tableAffectation;
	private int nbTotAffHPonctu;
	private JButton btnAjouterAffectation;
	private JButton btnSupprimerAffectation;

	public PageCreationRessource(Controleur ctrl, FrameIhm mere, Semestre semestre)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.semestre = semestre;
		this.lstAffectation = new ArrayList<Affectation>();

		this.panelCentre = new JPanel();

		// Formateur pour forcé la saisie d'un entier
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);

		/*------------Panel Nord------------*/

		this.panelNord   = new JPanel();
		this.panelNord.setLayout(new GridBagLayout());
		this.panelNord.setBorder(new EmptyBorder(0, 0, 10, 0));

		GridBagConstraints gbcNord = new GridBagConstraints();
		gbcNord.anchor = GridBagConstraints.WEST;
		// gbcNord.fill = GridBagConstraints.HORIZONTAL; // Occuper toute la largeur disponible
		gbcNord.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		//premier ligne
		JLabel lblTyMo = new JLabel("Type Module");
		lblTyMo.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTyMo.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblSeme = new JLabel("Semestre");
		lblSeme.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblSeme.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblCode = new JLabel("Code");
		lblCode.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblCode.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblLibelLong = new JLabel("Libellé Long");
		lblLibelLong.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblLibelLong.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblLibelCour = new JLabel("Libellé Court");
		lblLibelCour.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblLibelCour.setFont(PageCreationRessource.FONT_LABEL);

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
		JTextField txtFTyMo = new JTextField(8);
		txtFTyMo.setText("Ressource");
		txtFTyMo.setEditable(false);
		this.txtFSeme = new JTextField(4);
		txtFSeme.setText("S" + this.semestre.getId());
		txtFSeme.setEditable(false);
		this.txtFCode = new JTextField(4);
		this.txtFCode.setText("R" + this.semestre.getId() + ".XX");
		this.txtFCode.addFocusListener(this);
		this.txtFCode.addActionListener(this);
		this.txtFLibelLong = new JTextField(30);
		this.txtFLibelCour = new JTextField(15);

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
		lblNbEtd.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbEtd.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbGrpTd = new JLabel("Nb Grp TD");
		lblNbGrpTd.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbGrpTd.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbGrpTp = new JLabel("Nb Grp TP");
		lblNbGrpTp.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbGrpTp.setFont(PageCreationRessource.FONT_LABEL);

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
		txtFNbEtd.setText("" + this.semestre.getNbEtu());
		txtFNbEtd.setEditable(false);
		JTextField txtFNbGrpTd = new JTextField(2);
		txtFNbGrpTd.setHorizontalAlignment(JTextField.CENTER);
		txtFNbGrpTd.setText("" + this.semestre.getNbGroupeTd());
		txtFNbGrpTd.setEditable(false);
		JTextField txtFNbGrpTp = new JTextField(2);
		txtFNbGrpTp.setHorizontalAlignment(JTextField.CENTER);
		txtFNbGrpTp.setText("" + this.semestre.getNbGroupeTp());
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
		panelTemp.setBorder(new LineBorder(COULEUR_LABEL, 1 /*Largeur de la bordure*/));
		panelTemp.setLayout(new GridBagLayout());
		GridBagConstraints gbcTemp = new GridBagConstraints();
		gbcTemp.anchor = GridBagConstraints.WEST;
		
		//premier ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		JLabel lblHProCm = new JLabel("CM");
		lblHProCm.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblHProCm.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblHProTd = new JLabel("TD");
		lblHProTd.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblHProTd.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblHProTp = new JLabel("TP");
		lblHProTp.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblHProTp.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblHProSomme = new JLabel("∑");
		lblHProSomme.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblHProSomme.setFont(PageCreationRessource.FONT_LABEL);

		gbcTemp.gridy = 0;
		gbcTemp.gridx = 1;
		panelTemp.add(lblHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(lblHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(lblHProTp, gbcTemp);
		gbcTemp.insets = new Insets(0, 10, 1, 0); // Marge autour des composants
		gbcTemp.gridx = 4;
		panelTemp.add(lblHProSomme, gbcTemp);

		//deuxieme ligne
		gbcTemp.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		this.txtFHProCm = new JFormattedTextField(formatter);
		this.txtFHProCm.addFocusListener(this);
		this.txtFHProCm.addActionListener(this);
		this.txtFHProCm.setColumns(3);
		this.txtFHProCm.setText("0");
		this.txtFHProTd = new JFormattedTextField(formatter);
		this.txtFHProTd.addFocusListener(this);
		this.txtFHProTd.addActionListener(this);
		this.txtFHProTd.setColumns(3);
		this.txtFHProTd.setText("0");
		this.txtFHProTp = new JFormattedTextField(formatter);
		this.txtFHProTp.addFocusListener(this);
		this.txtFHProTp.addActionListener(this);
		this.txtFHProTp.setColumns(3);
		this.txtFHProTp.setText("0");
		this.txtFHProSomme = new JFormattedTextField(formatter);
		this.txtFHProSomme.setColumns(3);
		this.txtFHProSomme.setText("" + (Integer.parseInt(this.txtFHProCm.getText()) + Integer.parseInt(this.txtFHProTd.getText()) + Integer.parseInt(this.txtFHProTp.getText())));
		this.txtFHProSomme.setEditable(false);

		gbcTemp.gridy = 1;
		gbcTemp.gridx = 1;
		panelTemp.add(txtFHProCm, gbcTemp);
		gbcTemp.gridx = 2;
		panelTemp.add(txtFHProTd, gbcTemp);
		gbcTemp.gridx = 3;
		panelTemp.add(txtFHProTp, gbcTemp);
		gbcTemp.insets = new Insets(0, 10, 1, 0); // Marge autour des composants
		gbcTemp.gridx = 4;
		panelTemp.add(txtFHProSomme, gbcTemp);

		//troisieme ligne
		gbcTemp.insets = new Insets(10, 5, 1, 10); // Marge autour des composants

		JLabel lblTotal = new JLabel("Total(eqtd)");
		lblTotal.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTotal.setFont(PageCreationRessource.FONT_LABEL);

		this.txtFTotEqTdHProCm = new JFormattedTextField(formatter);
		this.txtFTotEqTdHProCm.setColumns(3);
		this.txtFTotEqTdHProCm.setEditable(false);
		this.txtFTotEqTdHProCm.setText("" + (int) (Integer.parseInt(this.txtFHProCm.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd = new JFormattedTextField(formatter);
		this.txtFTotEqTdHProTd.setColumns(3);
		this.txtFTotEqTdHProTd.setEditable(false);
		this.txtFTotEqTdHProTd.setText("" + (int) (Integer.parseInt(this.txtFHProTd.getText()) * this.semestre.getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp = new JFormattedTextField(formatter);
		this.txtFTotEqTdHProTp.setColumns(3);
		this.txtFTotEqTdHProTp.setEditable(false);
		this.txtFTotEqTdHProTp.setText("" + (int) (Integer.parseInt(this.txtFHProTp.getText()) * this.semestre.getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotEqTdHProSomme = new JFormattedTextField(formatter);
		this.txtFTotEqTdHProSomme.setColumns(3);
		this.txtFTotEqTdHProSomme.setEditable(false);
		this.txtFTotEqTdHProSomme.setText("" + (int) (Integer.parseInt(this.txtFTotEqTdHProCm.getText()) + Integer.parseInt(this.txtFTotEqTdHProTd.getText()) + Integer.parseInt(this.txtFTotEqTdHProTp.getText())));
		
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
		gbcTemp.insets = new Insets(5, 10, 1, 0); // Marge autour des composants
		gbcTemp.gridx = 4;
		panelTemp.add(this.txtFTotEqTdHProSomme, gbcTemp);

		//quatrieme ligne
		gbcTemp.insets = new Insets(0, 5, 10, 0); // Marge autour des composants

		JLabel lblPromo = new JLabel("promo");
		lblPromo.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblPromo.setFont(PageCreationRessource.FONT_LABEL);

		gbcTemp.gridx = 0;
		gbcTemp.gridy = 3;
		panelTemp.add(lblPromo, gbcTemp);

		panelTmpOuest.add(panelTemp, BorderLayout.CENTER);


		//Bouton valider
		JPanel panelValider = new JPanel();
		panelValider.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblValider = new JLabel("Validation");
		lblValider.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblValider.setFont(PageCreationRessource.FONT_LABEL);
		JCheckBox chkValider = new JCheckBox();
		chkValider.setSelected(false);

		panelValider.add(lblValider);
		panelValider.add(chkValider);

		panelTmpOuest.add(panelValider, BorderLayout.SOUTH);

		this.panelOuest.add(panelTmpOuest, BorderLayout.NORTH);
		/*---------Fin Panel  Ouest---------*/

		/*-----------Panel Centre-----------*/

		this.panelCentre.setLayout(new BorderLayout());
		this.panelCentre.setBorder(new EmptyBorder(0, 5, 0, 0));


		JPanel panelRepartition = new JPanel();
		panelRepartition.setLayout(new BorderLayout());
		panelRepartition.setBorder(new EmptyBorder(0, 0, 10, 0));


		JPanel panelTmpRepartition = new JPanel();
		panelTmpRepartition.setBorder(new LineBorder(COULEUR_LABEL, 1 /*Largeur de la bordure*/));
		panelTmpRepartition.setLayout(new GridBagLayout());
		GridBagConstraints gbcRepartition = new GridBagConstraints();
		
		//premier ligne
		gbcRepartition.anchor = GridBagConstraints.CENTER;
		gbcRepartition.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		JLabel lblCmSem = new JLabel("CM");
		lblCmSem.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblCmSem.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblTdSem = new JLabel("TD");
		lblTdSem.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTdSem.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblTpSem = new JLabel("TP");
		lblTpSem.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTpSem.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblHponctu = new JLabel("Heures");
		lblHponctu.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblHponctu.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblSommeSem = new JLabel("∑");
		lblSommeSem.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblSommeSem.setFont(PageCreationRessource.FONT_LABEL);

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
		gbcRepartition.gridheight = 2;
		panelTmpRepartition.add(lblSommeSem, gbcRepartition);
		gbcRepartition.gridheight = 1;

		//deuxieme ligne
		gbcRepartition.anchor = GridBagConstraints.WEST;

		JLabel lblNbSem1 = new JLabel("Nb sem");
		lblNbSem1.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbSem1.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbHSem1 = new JLabel("Nb h/sem");
		lblNbHSem1.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbHSem1.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbSem2 = new JLabel("Nb sem");
		lblNbSem2.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbSem2.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbHSem2 = new JLabel("Nb h/sem");
		lblNbHSem2.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbHSem2.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbSem3 = new JLabel("Nb sem");
		lblNbSem3.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbSem3.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblNbHSem3 = new JLabel("Nb h/sem");
		lblNbHSem3.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblNbHSem3.setFont(PageCreationRessource.FONT_LABEL);

		JLabel lblCmRepar = new JLabel("CM");
		lblCmRepar.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblCmRepar.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblTdRepar = new JLabel("TD");
		lblTdRepar.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTdRepar.setFont(PageCreationRessource.FONT_LABEL);
		JLabel lblTpRepar = new JLabel("TP");
		lblTpRepar.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTpRepar.setFont(PageCreationRessource.FONT_LABEL);

		JLabel lblhPonctu = new JLabel("ponctuelles");
		lblhPonctu.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblhPonctu.setFont(PageCreationRessource.FONT_LABEL);

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
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(lblhPonctu, gbcRepartition);

		//troisieme ligne
		this.txtFNbSemCm = new JFormattedTextField(formatter);
		this.txtFNbSemCm.setColumns(3);
		this.txtFNbSemCm.setText("0");
		this.txtFNbSemCm.addFocusListener(this);
		this.txtFNbSemCm.addActionListener(this);
		this.txtFNbHCmSem = new JFormattedTextField(formatter);
		this.txtFNbHCmSem.setColumns(3);
		this.txtFNbHCmSem.setText("0");
		this.txtFNbHCmSem.addFocusListener(this);
		this.txtFNbHCmSem.addActionListener(this);

		this.txtFNbSemTd = new JFormattedTextField(formatter);
		this.txtFNbSemTd.setColumns(3);
		this.txtFNbSemTd.setText("0");
		this.txtFNbSemTd.addFocusListener(this);
		this.txtFNbSemTd.addActionListener(this);
		this.txtFNbHTdSem = new JFormattedTextField(formatter);
		this.txtFNbHTdSem.setColumns(3);
		this.txtFNbHTdSem.setText("0");
		this.txtFNbHTdSem.addFocusListener(this);
		this.txtFNbHTdSem.addActionListener(this);

		this.txtFNbSemTp = new JFormattedTextField(formatter);
		this.txtFNbSemTp.setColumns(3);
		this.txtFNbSemTp.setText("0");
		this.txtFNbSemTp.addFocusListener(this);
		this.txtFNbSemTp.addActionListener(this);
		this.txtFNbHTpSem = new JFormattedTextField(formatter);
		this.txtFNbHTpSem.setColumns(3);
		this.txtFNbHTpSem.setText("0");
		this.txtFNbHTpSem.addFocusListener(this);
		this.txtFNbHTpSem.addActionListener(this);

		this.txtFTotCm1 = new JFormattedTextField(formatter);
		this.txtFTotCm1.setColumns(3);
		this.txtFTotCm1.setText("" + (Integer.parseInt(this.txtFNbSemCm.getText()) * Integer.parseInt(this.txtFNbHCmSem.getText())));
		this.txtFTotCm1.setEditable(false);
		this.txtFTotTd1 = new JFormattedTextField(formatter);
		this.txtFTotTd1.setColumns(3);
		this.txtFTotTd1.setText("" + (Integer.parseInt(this.txtFNbSemTd.getText()) * Integer.parseInt(this.txtFNbHTdSem.getText())));
		this.txtFTotTd1.setEditable(false);
		this.txtFTotTp1 = new JFormattedTextField(formatter);
		this.txtFTotTp1.setColumns(3);
		this.txtFTotTp1.setText("" + (Integer.parseInt(this.txtFNbSemTp.getText()) * Integer.parseInt(this.txtFNbHTpSem.getText())));
		this.txtFTotTp1.setEditable(false);
		this.txtFHPonctu1 = new JFormattedTextField(formatter);
		this.txtFHPonctu1.setColumns(3);
		this.txtFHPonctu1.setText("0");
		this.txtFHPonctu1.addFocusListener(this);
		this.txtFHPonctu1.addActionListener(this);
		this.txtFSom1 = new JFormattedTextField(formatter);
		this.txtFSom1.setColumns(3);
		this.txtFSom1.setText("" + (Integer.parseInt(this.txtFTotCm1.getText()) + Integer.parseInt(this.txtFTotTd1.getText()) + Integer.parseInt(this.txtFTotTp1.getText()) + Integer.parseInt(this.txtFHPonctu1.getText())));
		this.txtFSom1.setEditable(false);

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
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(this.txtFHPonctu1, gbcRepartition);
		gbcRepartition.gridx = 11;
		panelTmpRepartition.add(this.txtFSom1, gbcRepartition);

		//quatrieme ligne
		JLabel lblTotPromo = new JLabel("Total promo (eqtd)");
		lblTotPromo.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTotPromo.setFont(PageCreationRessource.FONT_LABEL);
		this.txtFTotCm2 = new JFormattedTextField(formatter);
		this.txtFTotCm2.setColumns(3);
		this.txtFTotCm2.setText("" + (int) (Integer.parseInt(this.txtFTotCm1.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFTotCm2.setEditable(false);
		this.txtFTotTd2 = new JFormattedTextField(formatter);
		this.txtFTotTd2.setColumns(3);
		this.txtFTotTd2.setText("" + (int) (Integer.parseInt(this.txtFTotTd1.getText()) * this.semestre.getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotTd2.setEditable(false);
		this.txtFTotTp2 = new JFormattedTextField(formatter);
		this.txtFTotTp2.setColumns(3);
		this.txtFTotTp2.setText("" + (int) (Integer.parseInt(this.txtFTotTp1.getText()) * this.semestre.getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotTp2.setEditable(false);
		this.txtFHPonctu2 = new JFormattedTextField(formatter);
		this.txtFHPonctu2.setColumns(3);
		this.txtFHPonctu2.setText("" + (int) (Integer.parseInt(this.txtFHPonctu1.getText()) * this.semestre.getNbGroupeTd()));
		this.txtFHPonctu2.setEditable(false);
		this.txtFTotSom2 = new JFormattedTextField(formatter);
		this.txtFTotSom2.setColumns(3);
		this.txtFTotSom2.setText("" + (int) (Integer.parseInt(this.txtFSom1.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFTotSom2.setEditable(false);

		gbcRepartition.gridy = 3;
		gbcRepartition.gridx = 6;
		panelTmpRepartition.add(lblTotPromo, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(txtFTotCm2, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(txtFTotTd2, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(txtFTotTp2, gbcRepartition);
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(txtFHPonctu2, gbcRepartition);
		gbcRepartition.gridx = 11;
		panelTmpRepartition.add(txtFTotSom2, gbcRepartition);

		//cinquieme ligne
		JLabel lblTotAffect = new JLabel("Total affectaté (eqtd)");
		lblTotAffect.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblTotAffect.setFont(PageCreationRessource.FONT_LABEL);
		this.txtFTotCm3 = new JFormattedTextField(formatter);
		this.txtFTotCm3.setColumns(3);
		this.txtFTotCm3.setText("" + this.nbTotAffHCm);
		this.txtFTotCm3.setEditable(false);
		this.txtFTotTd3 = new JFormattedTextField(formatter);
		this.txtFTotTd3.setColumns(3);
		this.txtFTotTd3.setText("" + this.nbTotAffHTd);
		this.txtFTotTd3.setEditable(false);
		this.txtFTotTp3 = new JFormattedTextField(formatter);
		this.txtFTotTp3.setColumns(3);
		this.txtFTotTp3.setText("" + this.nbTotAffHTp);
		this.txtFTotTp3.setEditable(false);
		this.txtFHPonctu3 = new JFormattedTextField(formatter);
		this.txtFHPonctu3.setColumns(3);
		this.txtFHPonctu3.setText("" + this.nbTotAffHPonctu);
		this.txtFHPonctu3.setEditable(false);
		this.txtFTotSom3 = new JFormattedTextField(formatter);
		this.txtFTotSom3.setColumns(3);
		this.txtFTotSom3.setText("" + (int) (Integer.parseInt(txtFTotCm3.getText()) + Integer.parseInt(txtFTotTd3.getText()) + Integer.parseInt(txtFTotTp3.getText()) + Integer.parseInt(txtFHPonctu3.getText())));
		this.txtFTotSom3.setEditable(false);

		gbcRepartition.gridy = 4;
		gbcRepartition.gridx = 6;
		panelTmpRepartition.add(lblTotAffect, gbcRepartition);
		gbcRepartition.gridx = 7;
		panelTmpRepartition.add(txtFTotCm3, gbcRepartition);
		gbcRepartition.gridx = 8;
		panelTmpRepartition.add(txtFTotTd3, gbcRepartition);
		gbcRepartition.gridx = 9;
		panelTmpRepartition.add(txtFTotTp3, gbcRepartition);
		gbcRepartition.gridx = 10;
		panelTmpRepartition.add(txtFHPonctu3, gbcRepartition);
		gbcRepartition.gridx = 11;
		panelTmpRepartition.add(txtFTotSom3, gbcRepartition);

		panelRepartition.add(panelTmpRepartition, BorderLayout.CENTER);


		JPanel panelTab = new JPanel();
		panelTab.setLayout(new BorderLayout());
		panelTab.setBorder(new EmptyBorder(10, 0, 0, 0));


		this.tableAffectation = new JTable( new ModelAffichageTableau(this.ctrl, this.lstAffectation) );
		this.tableAffectation.setFillsViewportHeight(true);
		this.tableAffectation.setRowHeight(25);
		TableColumnModel model = this.tableAffectation.getColumnModel();
		(model.getColumn(1)).setPreferredWidth(22);
		(model.getColumn(2)).setPreferredWidth(40);
		(model.getColumn(3)).setPreferredWidth(22);
		(model.getColumn(4)).setPreferredWidth(22);
		(model.getColumn(5)).setPreferredWidth(30);


		JScrollPane spDonneAffectation = new JScrollPane(this.tableAffectation);

		JLabel lblListeAffectation = new JLabel("Affectation");
		lblListeAffectation.setForeground(PageCreationRessource.COULEUR_LABEL);
		lblListeAffectation.setFont(PageCreationRessource.FONT_LABEL);

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


		this.panelCentre.add(panelRepartition, BorderLayout.NORTH);
		this.panelCentre.add(panelTab, BorderLayout.CENTER);
		/*---------Fin Panel Centre---------*/

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(15, 10, 15, 10));
		this.mere.setTitle("Prévisionnel - Module");

		this.add(this.panelNord, BorderLayout.NORTH);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelOuest, BorderLayout.WEST);
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
			String regex = "^[RSP][1-6]\\.\\d{2}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.txtFCode.getText());
			if(matcher.matches())
			{
				regex   = "^[RSP][" + this.semestre.getId() + "]\\.\\d{2}$";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(this.txtFCode.getText());
				if(!matcher.matches())
				{
					JOptionPane.showMessageDialog(this.mere, "Vous avez un semestre différent pour votre code que celui de votre module.", "Information Module entrer différente", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, "Le code doit être sous la format RX.XX !", "ERREUR ENTRER CODE", JOptionPane.ERROR_MESSAGE);
				this.txtFCode.setText("RX.XX");
			}
		}
		else
		{
			JFormattedTextField tmp = (JFormattedTextField) e.getSource();
			if(tmp.getText().equals(""))
				tmp.setText("0");
			this.recalcule();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnAjouterAffectation)
		{
			new PageCreaAffectation(this.ctrl, this.mere, this.lstAffectation, this.tableAffectation);

			this.recalcule();
		}
		else if(e.getSource() == this.btnSupprimerAffectation)
		{
			if(this.tableAffectation.getSelectedRow() != -1 && this.lstAffectation.size() > 0)
			{
				this.lstAffectation.remove(this.tableAffectation.getSelectedRow());

				this.recalcule();
			}
			else
			{
				JOptionPane.showMessageDialog(this.mere, this.lstAffectation.size() > 0?"Vous devez sélectionner une affectation !":"Il n'y a aucune affectation !", "ERREUR SELECTION AFFECTATION", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == this.txtFCode)
		{
			String regex = "^[RSP]" + this.semestre.getId() + "\\.\\d{2}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(this.txtFCode.getText());
			if(matcher.matches())
			{
				this.txtFSeme.setText("S" + this.txtFCode.getText().charAt(1));
			}
		}
		else
		{
			JFormattedTextField tmp = (JFormattedTextField) e.getSource();
			if(tmp.getText().equals(""))
				tmp.setText("0");

			this.recalcule();
		}
	}

	private void recalcule()
	{
		this.txtFHProSomme.setText("" + (Integer.parseInt(this.txtFHProCm.getText()) + Integer.parseInt(this.txtFHProTd.getText()) + Integer.parseInt(this.txtFHProTp.getText())));
		this.txtFTotEqTdHProCm.setText("" + (int) (Integer.parseInt(this.txtFHProCm.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFTotEqTdHProTd.setText("" + (int) (Integer.parseInt(this.txtFHProTd.getText()) * this.semestre.getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotEqTdHProTp.setText("" + (int) (Integer.parseInt(this.txtFHProTp.getText()) * this.semestre.getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotEqTdHProSomme.setText("" + (int) (Integer.parseInt(this.txtFTotEqTdHProCm.getText()) + Integer.parseInt(this.txtFTotEqTdHProTd.getText()) + Integer.parseInt(this.txtFTotEqTdHProTp.getText())));
		this.txtFTotCm1.setText("" + (Integer.parseInt(this.txtFNbSemCm.getText()) * Integer.parseInt(this.txtFNbHCmSem.getText())));
		this.txtFTotTd1.setText("" + (Integer.parseInt(this.txtFNbSemTd.getText()) * Integer.parseInt(this.txtFNbHTdSem.getText())));
		this.txtFTotTp1.setText("" + (Integer.parseInt(this.txtFNbSemTp.getText()) * Integer.parseInt(this.txtFNbHTpSem.getText())));
		this.txtFSom1.setText("" + (Integer.parseInt(this.txtFTotCm1.getText()) + Integer.parseInt(this.txtFTotTd1.getText()) + Integer.parseInt(this.txtFTotTp1.getText()) + Integer.parseInt(this.txtFHPonctu1.getText())));
		this.txtFTotCm2.setText("" + (int) (Integer.parseInt(this.txtFTotCm1.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFTotTd2.setText("" + (int) (Integer.parseInt(this.txtFTotTd1.getText()) * this.semestre.getNbGroupeTd() * this.ctrl.getCoefH("TD")));
		this.txtFTotTp2.setText("" + (int) (Integer.parseInt(this.txtFTotTp1.getText()) * this.semestre.getNbGroupeTp() * this.ctrl.getCoefH("TP")));
		this.txtFTotSom2.setText("" + (int) (Integer.parseInt(this.txtFSom1.getText()) * this.ctrl.getCoefH("CM")));
		this.txtFHPonctu2.setText("" + (int) (Integer.parseInt(this.txtFHPonctu1.getText()) * this.semestre.getNbGroupeTd()));
		this.recalculeTotalAffecte();
		this.txtFTotCm3.setText("" + this.nbTotAffHCm);
		this.txtFTotTd3.setText("" + this.nbTotAffHTd);
		this.txtFTotTp3.setText("" + this.nbTotAffHTp);
		this.txtFHPonctu3.setText("" + this.nbTotAffHPonctu);
		this.txtFTotSom3.setText("" + (this.nbTotAffHCm + this.nbTotAffHTd + this.nbTotAffHTp + this.nbTotAffHPonctu));


		this.updateTable();
		this.repaint();
		this.revalidate();
	}

	public void updateTable()
	{
		this.tableAffectation.setModel(new ModelAffichageTableau(this.ctrl, this.lstAffectation));

		TableColumnModel model = this.tableAffectation.getColumnModel();
		(model.getColumn(1)).setPreferredWidth(22);
		(model.getColumn(2)).setPreferredWidth(40);
		(model.getColumn(3)).setPreferredWidth(22);
		(model.getColumn(4)).setPreferredWidth(22);
		(model.getColumn(5)).setPreferredWidth(30);
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

	public Integer getHSemaine(String nom)
	{
		if(nom.equals("CM"))
			return this.semestre.getNbGroupeTd();
		else if(nom.equals("TD"))
			return this.semestre.getNbGroupeTd();
		else if(nom.equals("TP"))
			return this.semestre.getNbGroupeTp();
		else
			return 0;
	}

	private class ModelAffichageTableau extends AbstractTableModel
	{
		private Controleur ctrl;

		private Object[][] tabDonnees;

		private String[] tabEntetes;

		public ModelAffichageTableau(Controleur ctrl, List<Affectation> lstAffectation)
		{
			this.ctrl = ctrl;

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
				this.tabDonnees = new Object[1][nbCol];
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
		public int getColumnCount()                             {return this.tabDonnees[0].length;}
		public String getColumnName (int col)                  {return this.tabEntetes[col];}
		public Object getValueAt(int rowIndex, int columnIndex) {return this.tabDonnees[rowIndex][columnIndex];}
	}
}