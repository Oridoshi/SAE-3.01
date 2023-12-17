package ihm.creationObjet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
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
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import ihm.classPerso.JIntegerTextField;
import metier.model.Affectation;
import metier.model.CategorieHeure;
import metier.model.Intervenant;
import metier.model.Module;

public class PageCreaAffectation implements FocusListener, ActionListener
{

	private Controleur ctrl;
	private JFrame mere;
	private List<Affectation> lstAffectation;
	private JTable tableRessource;
	private JDialog dial;
	private JComboBox<Intervenant> cbIntervenant;
	private JComboBox<CategorieHeure> cbCategorieHeure;
	private JIntegerTextField txtFNbSemaine;
	private JIntegerTextField txtFNbGroupe;
	private JTextField txtFCommentaire;
	private JIntegerTextField txtFNbHeure;
	private JButton btnValider;
	private JButton btnAnnuler;
	private Module module;
	
	public PageCreaAffectation(Controleur ctrl, JFrame mere, List<Affectation> lstAffectation, JTable tableRessource, Module module)
	{
		this.ctrl = ctrl;
		this.mere = mere;
		this.lstAffectation = lstAffectation;
		this.tableRessource = tableRessource;
		this.module = module;
		this.creationPage();
	}

	private void creationPage()
	{
		this.dial = new JDialog(mere, "Création D'une Afféctation", true);
		this.dial.setLayout(new BorderLayout());
		this.dial.setSize(500, 500);
		this.dial.setLocationRelativeTo(mere);

		/*----------Formulaire----------*/
		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants

		// Intervenant \\
		JLabel lblIntervenant = new JLabel("Intervenant : ");
		this.cbIntervenant = new JComboBox<Intervenant>((new Vector<>(this.ctrl.getLstIntervenants())));
		this.cbIntervenant.setRenderer(new Renderer());

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(lblIntervenant, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.cbIntervenant, gbc);

		// Categorie Heure \\
		JLabel lblCategorieHeure = new JLabel("Categorie Heure : ");
		this.cbCategorieHeure = new JComboBox<CategorieHeure>(new Vector<>(this.ctrl.getLstCategorieParTypeModule(module.getCategorieModule().getNom())));
		this.cbCategorieHeure.setRenderer(new Renderer());
		this.cbCategorieHeure.addActionListener(this);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(lblCategorieHeure, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.cbCategorieHeure, gbc);

		// Nombre d'heure \\
		JLabel lblNbHeure = new JLabel("Nombre d'heure : ");
		this.txtFNbHeure = new JIntegerTextField(5);
		this.txtFNbHeure.addFocusListener(this);
		this.txtFNbHeure.setAllowsInvalid(false);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(lblNbHeure, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtFNbHeure, gbc);

		// Nombre de semaine \\
		JLabel lblNbSemaine = new JLabel("Nombre de semaine : ");
		this.txtFNbSemaine = new JIntegerTextField(5);
		this.txtFNbSemaine.addFocusListener(this);
		this.txtFNbSemaine.setAllowsInvalid(false);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(lblNbSemaine, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtFNbSemaine, gbc);

		// Nombre de groupe \\
		JLabel lblNbGroupe = new JLabel("Nombre de groupe : ");
		this.txtFNbGroupe = new JIntegerTextField(5);
		this.txtFNbGroupe.addFocusListener(this);
		this.txtFNbGroupe.setAllowsInvalid(false);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelFormulaire.add(lblNbGroupe, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtFNbGroupe, gbc);

		// Commentaire \\
		JLabel lblCommentaire = new JLabel("Commentaire : ");
		this.txtFCommentaire = new JTextField(15);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelFormulaire.add(lblCommentaire, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtFCommentaire, gbc);


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

			if (value instanceof Intervenant)
			{
				Intervenant inter = (Intervenant) value;
				setText(inter.getNom());
			}
			if(value instanceof CategorieHeure)
			{
				CategorieHeure catHeure = (CategorieHeure) value;
				setText(catHeure.getNom());
			}

			return this;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnValider)
		{
			if(this.txtFNbHeure.getText().equals("") && (this.txtFNbGroupe.getText().equals("") || this.txtFNbSemaine.getText().equals("")))
			{
				JOptionPane.showMessageDialog(this.dial, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				Intervenant inter = (Intervenant) this.cbIntervenant.getSelectedItem();
				CategorieHeure catHeure = (CategorieHeure) this.cbCategorieHeure.getSelectedItem();
				Integer nbSemaine = 0;
				Integer nbHeure   = 0;
				Integer nbGroupe  = 0;
				if(this.txtFNbSemaine.isEmpty())
				{
					nbHeure = this.txtFNbHeure.getValue();
					nbGroupe = 1;
				}
				else
				{
					nbSemaine = this.txtFNbSemaine.getValue();
					nbGroupe  = this.txtFNbGroupe.getValue();
				}
				
				String  commentaire = this.txtFCommentaire.getText();

				if(nbSemaine > this.module.getSemestre().getNbSemaine())
				{
					JOptionPane.showMessageDialog(this.dial, "Le nombre de semaine ne peut pas être supérieur au nombre de semaine du semestre", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if(nbGroupe > (((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("TD")?this.module.getSemestre().getNbGroupeTd():100000000))
				{
					JOptionPane.showMessageDialog(this.dial, "Le nombre de groupe ne peut pas être supérieur au nombre de groupe du semestre", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if(nbGroupe > (((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("TP")?this.module.getSemestre().getNbGroupeTp():100000000))
				{
					JOptionPane.showMessageDialog(this.dial, "Le nombre de groupe ne peut pas être supérieur au nombre de groupe du semestre", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if(nbGroupe > (((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("CM")?this.module.getSemestre().getNbGroupeTd():100000000))
				{
					JOptionPane.showMessageDialog(this.dial, "Le nombre de groupe ne peut pas être supérieur au nombre de groupe du semestre", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Affectation affectation = new Affectation(this.ctrl.getIdNouvAffectation() , inter, catHeure, nbGroupe, nbSemaine, nbHeure, commentaire, module);
					this.ctrl.ajouterSauvAttente(affectation);
					this.lstAffectation.add(affectation);
					this.tableRessource.repaint();
					this.dial.dispose();
				}
			}
		}
		else if(e.getSource() == this.btnAnnuler)
		{
			this.dial.dispose();
		}
		else if(e.getSource() == this.cbCategorieHeure)
		{
			if(((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("HP"))
			{
				this.txtFNbSemaine.setEditable(false);
				this.txtFNbGroupe.setEditable(false);
			}
			else
			{
				this.txtFNbSemaine.setEditable(true);
				this.txtFNbGroupe.setEditable(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if(e.getSource() == this.txtFNbHeure || ((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("HP"))
		{
			this.txtFNbSemaine.setEditable(false);
			this.txtFNbGroupe.setEditable(false);
		}
		else if(e.getSource() == this.txtFNbSemaine || e.getSource() == this.txtFNbGroupe)
		{
			this.txtFNbHeure.setEditable(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if(e.getSource() == this.txtFNbHeure)
		{
			if(this.txtFNbHeure.getText().equals("") && !((CategorieHeure) this.cbCategorieHeure.getSelectedItem()).getNom().equals("HP"))
			{
				this.txtFNbSemaine.setEditable(true);
				this.txtFNbGroupe.setEditable(true);
			}
		}
		else if(e.getSource() == this.txtFNbSemaine  || e.getSource() == this.txtFNbGroupe)
		{
			if(this.txtFNbSemaine.getText().equals("") && this.txtFNbGroupe.getText().equals(""))
			{
				this.txtFNbHeure.setEditable(true);
			}
		}
	}
}
