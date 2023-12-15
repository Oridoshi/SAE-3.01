package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controleur.Controleur;

/**
 * PageEditionModule
 */
public class PageEditionModule extends JPanel
{
	private static final Color COULEUR_LABEL = new Color(158,158,158);
	private static final Font FONT_LABEL = new Font("Arial", 0, 8);

	private JPanel panelNord;
	private JPanel panelCentre;
	private JPanel panelSud;

	private JFrame mere;
	private Controleur ctrl;

	private JTextField txtFCode;
	private JTextField txtFLibelLong;
	private JTextField txtFLibelCour;


	public PageEditionModule(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.panelCentre = new JPanel();
		this.panelSud    = new JPanel();

		// Formateur pour forcé la saisie d'un entier
		NumberFormat format = NumberFormat.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);

		/*------------Panel Nord------------*/

		this.panelNord   = new JPanel();
		this.panelNord.setLayout(new GridBagLayout());
		GridBagConstraints gbcNord = new GridBagConstraints();
		gbcNord.anchor = GridBagConstraints.WEST;
		// gbcNord.fill = GridBagConstraints.HORIZONTAL; // Occuper toute la largeur disponible
		gbcNord.insets = new Insets(0, 1, 1, 0); // Marge autour des composants

		//premier ligne
		JLabel lblTyMo = new JLabel("Type Module");
		lblTyMo.setForeground(PageEditionModule.COULEUR_LABEL);
		lblTyMo.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblSeme = new JLabel("Semaine");
		lblSeme.setForeground(PageEditionModule.COULEUR_LABEL);
		lblSeme.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblCode = new JLabel("Code");
		lblCode.setForeground(PageEditionModule.COULEUR_LABEL);
		lblCode.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblLibelLong = new JLabel("Libellé Long");
		lblLibelLong.setForeground(PageEditionModule.COULEUR_LABEL);
		lblLibelLong.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblLibelCour = new JLabel("Libellé Court");
		lblLibelCour.setForeground(PageEditionModule.COULEUR_LABEL);
		lblLibelCour.setFont(PageEditionModule.FONT_LABEL);

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
		txtFTyMo.setEditable(false);
		JTextField txtFSeme = new JTextField(4);
		txtFSeme.setEditable(false);
		this.txtFCode = new JTextField(4);
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
		lblNbEtd.setForeground(PageEditionModule.COULEUR_LABEL);
		lblNbEtd.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblNbGrpTd = new JLabel("Nb Grp TD");
		lblNbGrpTd.setForeground(PageEditionModule.COULEUR_LABEL);
		lblNbGrpTd.setFont(PageEditionModule.FONT_LABEL);
		JLabel lblNbGrpTp = new JLabel("Nb Grp TP");
		lblNbGrpTp.setForeground(PageEditionModule.COULEUR_LABEL);
		lblNbGrpTp.setFont(PageEditionModule.FONT_LABEL);

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
		txtFNbEtd  .setEditable(false);
		JTextField txtFNbGrpTd = new JTextField(2);
		txtFNbGrpTd.setEditable(false);
		JTextField txtFNbGrpTp = new JTextField(2);
		txtFNbGrpTp.setEditable(false);

		gbcNord.gridx = 0;
		gbcNord.gridy = 3;
		this.panelNord.add(txtFNbEtd, gbcNord);
		gbcNord.gridx = 1;
		this.panelNord.add(txtFNbGrpTd, gbcNord);
		gbcNord.gridx = 2;
		this.panelNord.add(txtFNbGrpTp, gbcNord);
		/*----------Fin Panel Nord----------*/

		/*-----------Panel Centre-----------*/

		

		/*---------Fin Panel Centre---------*/

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(15, 10, 15, 10));
		this.mere.setTitle("Prévisionnel - Module");

		this.add(this.panelNord, BorderLayout.NORTH);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelSud, BorderLayout.SOUTH);
	}
}