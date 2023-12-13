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
	private JTextField textFieldNcli;
	private JTextField textFieldType;
	private Controleur ctrl;
	private FrameIhm   mere;

	public PageCreaRessource(FrameIhm mere, Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.dial = new JDialog(mere, "Création d'un Module - Ressource", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(300, 200);
		this.dial.setLocationRelativeTo(mere);
		
		// Ajout du Button Ajouter
		JButton ajout = new JButton("Ajouter");d
		ajout.addActionListener(this);
		
		this.dial.add(ajout, BorderLayout.SOUTH);
		

		/*----------Formulaire----------*/
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// Code \\
		JLabel labelNom = new JLabel("NCLI : ");
		this.textFieldNcli = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(labelNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(textFieldNcli, gbc);

		// libelleLong \\
		JLabel labelPrenom = new JLabel("TYPE : ");
		this.textFieldType = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(labelPrenom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(textFieldType, gbc);

		// libelleCourt \\


		// Heures Programme TD \\


		// Heures Programme TP \\


		// Heures Programme CM \\


		// Heures Semaine TD \\


		// Nb Semaine TD \\


		// Heures Semaine TP \\


		// Nb Semaine TP \\


		// Heures Semaine CM \\


		// Nb Semaine CM \\


		// Ajout du panelFormulaire au panel principal \\
		this.dial.add(panelFormulaire, BorderLayout.CENTER);
		/*----------Fin Formulaire----------*/

		this.dial.setResizable(false);
		this.dial.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.ctrl.ajouterModule();
		// this.mere.majTable();
		this.dial.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			this.ctrl.ajouterUtilisation(Integer.parseInt(this.textFieldNcli.getText()), Integer.parseInt(this.textFieldType.getText()));
			// this.mere.majTable();
			this.dial.dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e){}

	@Override
	public void keyReleased(KeyEvent e){}
}

