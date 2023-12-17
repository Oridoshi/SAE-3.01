package ihm.creationObjet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import ihm.FrameIhm;

import metier.model.CategorieHeure;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ihm.classPerso.JDoubleTextField;

public class PageCreaCategorieHeure implements ActionListener
{
	private JDialog    dial;
	private Controleur ctrl;
	private FrameIhm   mere;

	private CategorieHeure categorieHeure;

	private JTextField textFieldNom;
	private JDoubleTextField textFieldCoefTP;
	private JButton btnAjout;
	private JButton btnAnnuler;

	public PageCreaCategorieHeure(FrameIhm mere, Controleur ctrl, CategorieHeure categorieHeure)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.categorieHeure = categorieHeure;

		this.dial = new JDialog(this.mere, "Édition d'une catégorie d'heure", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(500, 500);
		this.dial.setLocationRelativeTo(mere);
		
		// Ajout du Button Ajouter
		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new GridLayout(1, 2));
		this.btnAjout = new JButton("Modifier");
		btnAjout.addActionListener(this);
		panelBouton.add(btnAjout);

		this.btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(this);
		panelBouton.add(btnAnnuler);
		
		this.dial.add(panelBouton, BorderLayout.SOUTH);
		

		/*----------Formulaire----------*/
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// Nom \\
		JLabel lblNom = new JLabel("Nom de la catégorie : ");
		this.textFieldNom = new JTextField(15);
		this.textFieldNom.setText(this.categorieHeure.getNom());
		this.textFieldNom.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldNom, gbc);

		// Coef \\
		JLabel lblCoefTP = new JLabel("Coefficient : ");
		this.textFieldCoefTP = new JDoubleTextField(15);
		this.textFieldCoefTP.setValue(this.categorieHeure.getCoef());
		this.textFieldCoefTP.setAllowsInvalid(false);
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
		if(e.getSource() == this.btnAnnuler)
		{
			this.dial.dispose();
		}
		else if(e.getSource() == this.btnAjout)
		{
			if(this.textFieldCoefTP.isEmpty())
			{
				JOptionPane.showMessageDialog(this.dial, "Veuillez saisir un coefficient", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				this.categorieHeure.setCoef(this.textFieldCoefTP.getValue());
				this.ctrl.ajouterSauvAttente(categorieHeure);
		
				this.dial.dispose();
			}
		}
	}
}

