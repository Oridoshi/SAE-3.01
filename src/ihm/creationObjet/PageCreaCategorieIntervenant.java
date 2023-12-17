package ihm.creationObjet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import ihm.FrameIhm;
import ihm.classPerso.JDoubleTextField;
import ihm.classPerso.JIntegerTextField;
import metier.model.CategorieIntervenant;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PageCreaCategorieIntervenant implements ActionListener
{
	private JDialog    dial;
	private Controleur ctrl;
	private FrameIhm   mere;

	private List<CategorieIntervenant> lstCategorieIntervenant;

	private JTextField textFieldCode;
	private JTextField textFieldNom;
	private JDoubleTextField textFieldCoefTP;
	private JIntegerTextField textFieldMinH;
	private JIntegerTextField textFieldMaxH;

	public PageCreaCategorieIntervenant(FrameIhm mere, Controleur ctrl, List<CategorieIntervenant> lstCategorieIntervenant)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.lstCategorieIntervenant = lstCategorieIntervenant;

		this.dial = new JDialog(this.mere, "Création d'une catégorie d'intervenant", true);
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

		// Code \\
		JLabel lblCode = new JLabel("Code de la catégorie : ");
		this.textFieldCode = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblCode, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldCode, gbc);

		// Nom \\
		JLabel lblNom = new JLabel("Nom de la catégorie : ");
		this.textFieldNom = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(lblNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldNom, gbc);

		// minH \\
		JLabel lblMinH = new JLabel("Min. d'heures : ");
		this.textFieldMinH = new JIntegerTextField(15);
		this.textFieldMinH.setAllowsInvalid(false);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(lblMinH, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldMinH, gbc);

		// maxH \\
		JLabel lblMaxH = new JLabel("Max. d'heures : ");
		this.textFieldMaxH = new JIntegerTextField(15);
		this.textFieldMaxH.setAllowsInvalid(false);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(lblMaxH, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldMaxH, gbc);

		// CoefTP \\
		JLabel lblCoefTP = new JLabel("Coefficient TP : ");
		this.textFieldCoefTP = new JDoubleTextField(15);
		this.textFieldCoefTP.setAllowsInvalid(false);
		gbc.gridx = 0;
		gbc.gridy = 4;
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
		if(this.textFieldCode.getText().equals("") || this.textFieldNom.getText().equals("") || this.textFieldMinH.isEmpty() || this.textFieldMaxH.isEmpty() || this.textFieldCoefTP.isEmpty())
		{
			JOptionPane.showMessageDialog(this.dial, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			for (CategorieIntervenant categorieIntervenant : lstCategorieIntervenant)
			{
				if(categorieIntervenant.getCode().equals(this.textFieldCode.getText().trim()))
				{
					JOptionPane.showMessageDialog(this.dial, "Ce code est déja pris", "ERREUR CODE", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			if(this.textFieldMinH.getValue() > this.textFieldMaxH.getValue())
			{
				JOptionPane.showMessageDialog(this.dial, "Le nombre d'heures minimum ne peut pas être supérieur au nombre d'heures maximum", "ERREUR HEURES", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(this.textFieldCoefTP.getValue() <= 0)
			{
				JOptionPane.showMessageDialog(this.dial, "Le coefficient TP doit être supérieur à 0", "ERREUR COEFFICIENT", JOptionPane.ERROR_MESSAGE);
				return;
			}

			CategorieIntervenant categorieIntervenant = new CategorieIntervenant(this.textFieldCode.getText().trim(), this.textFieldNom.getText().trim(), this.textFieldMinH.getValue(), this.textFieldMaxH.getValue(), this.textFieldCoefTP.getValue());
			this.lstCategorieIntervenant.add(categorieIntervenant);
			this.ctrl.ajouterSauvAttente(categorieIntervenant);
	
			this.dial.dispose();
		}
	}
}

