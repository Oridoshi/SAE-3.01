package ihm.creationObjet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PageCreaRessource implements ActionListener, KeyListener
{
	private JDialog    dial;
	private Controleur ctrl;
	private FrameIhm   mere;

	private JTextField textFieldCode;
	private JTextField textFieldLibelleLong;
	private JTextField textFieldLibelleCourt;
	private JTextField textFieldHProTd;
	private JTextField textFieldHProTp;
	private JTextField textFieldHProCm;
	private JTextField textFieldHSemTd;
	private JTextField textFieldSemTd;
	private JTextField textFieldHSemTp;
	private JTextField textFieldSemTp;
	private JTextField textFieldHSemCm;
	private JTextField textFieldSemCm;

	public PageCreaRessource(FrameIhm mere, Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.dial = new JDialog(mere, "Création d'un Module - Ressource", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(300, 1000);
		this.dial.setLocationRelativeTo(mere);
		
		// Ajout du Button Ajouter
		JButton ajout = new JButton("Ajouter");
		ajout.addActionListener(this);
		
		this.dial.add(ajout, BorderLayout.SOUTH);
		

		/*----------Formulaire----------*/
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// Code \\
		JLabel lblCode = new JLabel("Code : ");
		this.textFieldCode = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblCode, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldCode, gbc);

		// LibelleLong \\
		JLabel lblLibelleLong = new JLabel("LibelleLong : ");
		this.textFieldLibelleLong = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(lblLibelleLong, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldLibelleLong, gbc);

		// LibelleCourt \\
		JLabel lblLibelleCourt = new JLabel("TYPE : ");
		this.textFieldLibelleCourt = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(lblLibelleCourt, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldLibelleCourt, gbc);

		// Heures Programme TD \\
		JLabel lblHProTd = new JLabel("TYPE : ");
		this.textFieldHProTd = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(lblHProTd, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHProTd, gbc);

		// Heures Programme TP \\
		JLabel lblHProTp = new JLabel("TYPE : ");
		this.textFieldHProTp = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelFormulaire.add(lblHProTp, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHProTp, gbc);

		// Heures Programme CM \\
		JLabel lblHProCm = new JLabel("TYPE : ");
		this.textFieldHProCm = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelFormulaire.add(lblHProCm, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHProCm, gbc);


		// Heures Semaine TD \\
		JLabel lblHSemTd = new JLabel("TYPE : ");
		this.textFieldHSemTd = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 6;
		panelFormulaire.add(lblHSemTd, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHSemTd, gbc);


		// Nb Semaine TD \\
		JLabel lblSemTd = new JLabel("TYPE : ");
		this.textFieldSemTd = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 7;
		panelFormulaire.add(lblSemTd, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldSemTd, gbc);


		// Heures Semaine TP \\
		JLabel lblHSemTp = new JLabel("TYPE : ");
		this.textFieldHSemTp = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 8;
		panelFormulaire.add(lblHSemTp, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHSemTp, gbc);

		// Nb Semaine Tp \\
		JLabel lblSemTp = new JLabel("TYPE : ");
		this.textFieldSemTp = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 9;
		panelFormulaire.add(lblSemTp, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldSemTp, gbc);

		// Heures Semaine CM \\
		JLabel lblHSemCm = new JLabel("TYPE : ");
		this.textFieldHSemCm = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 10;
		panelFormulaire.add(lblHSemCm, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldHSemCm, gbc);

		// Nb Semaine CM \\
		JLabel lblSemCm = new JLabel("TYPE : ");
		this.textFieldSemCm = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 11;
		panelFormulaire.add(lblSemCm, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldSemCm, gbc);

		// Ajout du panelFormulaire au panel principal \\
		this.dial.add(panelFormulaire, BorderLayout.CENTER);
		/*----------Fin Formulaire----------*/

		this.dial.setResizable(false);
		this.dial.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// this.ctrl.ajouterModule(new Module(null, null, null, false, null, null, null));
		// this.mere.majTable();
		this.dial.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			// this.ctrl.ajouterModule(new Module(null, null, null, false, null, null, null));
			// this.mere.majTable();
			this.dial.dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e){}

	@Override
	public void keyReleased(KeyEvent e){}
}

