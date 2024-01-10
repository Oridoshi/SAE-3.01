package ihm.creationObjet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import ihm.FrameIhm;
import ihm.classPerso.JDoubleTextField;
import ihm.classPerso.JIntegerTextField;
import metier.model.CategorieIntervenant;
import metier.model.Intervenant;

public class PageEditionIntervenant implements ActionListener
{

	private Controleur ctrl;
	private JFrame mere;

	private JDialog dial;

	private JButton btnValider;
	private JButton btnAnnuler;
	
	private JComboBox<CategorieIntervenant> cbIntervenant;

	private JTextField txtFPrenom;
	private JTextField txtFNom;

	private JIntegerTextField txtFHServ;
	private JIntegerTextField txtFHMax;
	private JDoubleTextField txtFCoefTP;

	private Intervenant inter;
	private JLabel lblHServDefault;
	private JLabel lblHMaxDefault;
	private JLabel lblCoefTPDefault;

	public PageEditionIntervenant(FrameIhm mere, Controleur ctrl, Intervenant inter)
	{
		this.ctrl = ctrl;
		this.mere = mere;
		this.inter = inter;

		this.dial = new JDialog(this.mere, "Edition d'un Intervenant", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(500, 500);
		this.dial.setLocationRelativeTo(mere);

		/*----------Formulaire----------*/
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// Categorie Intervenant \\
		JLabel lblIntervenant = new JLabel("Categorie : ");
		this.cbIntervenant = new JComboBox<CategorieIntervenant>((new Vector<>(this.ctrl.getLstCategorieIntervenant())));
		this.cbIntervenant.setRenderer(new Renderer());
		this.cbIntervenant.setSelectedItem(inter.getCategorie());
		this.cbIntervenant.addActionListener(this);

		CategorieIntervenant cateInter = (CategorieIntervenant) cbIntervenant.getSelectedItem();

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblIntervenant, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.cbIntervenant, gbc);

		// Nom \\
		JLabel lblNom = new JLabel("Nom : ");
		this.txtFNom = new JTextField(15);
		this.txtFNom.setText(inter.getNom());

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(lblNom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(txtFNom, gbc);

		// Prenom \\
		JLabel lblPrenom = new JLabel("Prenom : ");
		this.txtFPrenom = new JTextField(15);
		this.txtFPrenom.setText(inter.getPrenom());

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(lblPrenom, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(txtFPrenom, gbc);

		// hServ \\
		JLabel lblHServ = new JLabel("hServ : ");
		this.txtFHServ = new JIntegerTextField(15);
		if(inter.getHMin() != cateInter.getMinH())
			this.txtFHServ.setValue(inter.getHMin());
		this.txtFHServ.setAllowsInvalid(false);
		this.lblHServDefault = new JLabel(cateInter.getMinH() + "h");


		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(lblHServ, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(txtFHServ, gbc);
		gbc.gridx = 2;
		panelFormulaire.add(lblHServDefault, gbc);

		// hMax \\
		JLabel lblHMax = new JLabel("hMax : ");
		this.txtFHMax = new JIntegerTextField(15);
		if(inter.gethMax() != cateInter.getMaxH())
			this.txtFHMax.setValue(inter.gethMax());
		this.txtFHMax.setAllowsInvalid(false);
		this.lblHMaxDefault = new JLabel(cateInter.getMaxH() + "h");
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelFormulaire.add(lblHMax, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(txtFHMax, gbc);
		gbc.gridx = 2;
		panelFormulaire.add(lblHMaxDefault, gbc);

		// CoefTP \\
		JLabel lblCoefTP = new JLabel("CoefTP : ");
		this.txtFCoefTP = new JDoubleTextField(15);
		if(inter.getCoefTP() != cateInter.getCoefTp())
			this.txtFCoefTP.setValue(inter.getCoefTP());
		this.txtFCoefTP.setAllowsInvalid(false);
		this.lblCoefTPDefault = new JLabel(cateInter.getCoefTp() + "");

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelFormulaire.add(lblCoefTP, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(txtFCoefTP, gbc);
		gbc.gridx = 2;
		panelFormulaire.add(lblCoefTPDefault, gbc);

		// Ajout du panelFormulaire au panel principal \\
		this.dial.add(panelFormulaire, BorderLayout.CENTER);
		/*----------Fin Formulaire----------*/

		/*----------Boutons----------*/
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(1, 2));

		// btnValider \\
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(this);

		// btnAnnuler \\
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.addActionListener(this);

		panelBtn.add(this.btnValider);
		panelBtn.add(this.btnAnnuler);

		// Ajout du panelBtn au panel principal \\
		this.dial.add(panelBtn, BorderLayout.SOUTH);
		/*----------Fin Boutons----------*/


		this.dial.setResizable(false);
		this.dial.setVisible(true);
	}

	private class Renderer extends DefaultListCellRenderer
	{
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value instanceof CategorieIntervenant)
			{
				CategorieIntervenant cateInter = (CategorieIntervenant) value;
				setText(cateInter.getCode());
			}

			return this;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cbIntervenant)
		{
			CategorieIntervenant cateInter = (CategorieIntervenant) cbIntervenant.getSelectedItem();
			this.lblCoefTPDefault.setText(cateInter.getCoefTp() + "");
			this.lblHMaxDefault.setText(cateInter.getMaxH() + "h");
			this.lblHServDefault.setText(cateInter.getMinH() + "h");
		}
		else if( e.getSource() == this.btnValider)
		{
			if(this.txtFNom.getText().isEmpty() || this.txtFPrenom.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this.dial, "Veuillez remplir les champs Nom et Prenom", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(this.txtFHServ.getValue() > this.txtFHMax.getValue())
				{
					JOptionPane.showMessageDialog(this.dial, "Le nombre d'heures de minimum ne peut pas être supérieur au nombre d'heures maximum", "ERREUR HEURE", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					CategorieIntervenant cateInter = (CategorieIntervenant) cbIntervenant.getSelectedItem();
					this.inter.setCategorie(cateInter);

					this.inter.setNom(this.txtFNom.getText());
					this.inter.setPrenom(this.txtFPrenom.getText());
					this.inter.sethMax(this.txtFHMax.isEmpty()?-1:this.txtFHMax.getValue());
					this.inter.setHMin(this.txtFHServ.isEmpty()?-1:this.txtFHServ.getValue());
					this.inter.setCoefTP(this.txtFCoefTP.isEmpty()?-1:this.txtFCoefTP.getValue());

					this.ctrl.ajouterSauvAttente(inter);
					this.dial.dispose();
				}
			}
		}
		else if(e.getSource() == this.btnAnnuler)
		{
			this.dial.dispose();
		}
	}
}
