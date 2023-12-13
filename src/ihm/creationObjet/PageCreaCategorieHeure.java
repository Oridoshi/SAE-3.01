package ihm.creationObjet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.NumberFormatter;

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
import java.text.NumberFormat;

public class PageCreaCategorieHeure implements ActionListener, KeyListener
{
	private JDialog    dial;
	private Controleur ctrl;
	private FrameIhm   mere;

	private JTextField textFieldNom;
	private JTextField textFieldCoefTP;

	public PageCreaCategorieHeure(FrameIhm mere, Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.dial = new JDialog(mere, "Création d'une catégorie d'heure", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(500, 500);
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

		NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0); // Valeur minimale autorisée

		// Nom \\
		JLabel lblNom = new JLabel("Nom de la catégorie : ");
		this.textFieldNom = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldNom, gbc);

		// CoefTP \\
		JLabel lblCoefTP = new JLabel("Coefficient TP : ");
		this.textFieldCoefTP = new JFormattedTextField(formatter);
		this.textFieldCoefTP.setColumns(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(lblCoefTP, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldCoefTP, gbc);


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

