package ihm.creationObjet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.NumberFormatter;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.model.CategorieHeure;
import metier.model.CategorieIntervenant;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class PageCreaCategorieIntervenant implements ActionListener, KeyListener
{
	private JDialog    dial;
	private Controleur ctrl;
	private FrameIhm   mere;

	private List<CategorieIntervenant> lstCategorieIntervenant;
	private JTable tableCategorieIntervenant;

	private JTextField textFieldCode;
	private JTextField textFieldNom;
	private JTextField textFieldCoefTP;
	private JTextField textFieldMinH;
	private JTextField textFieldMaxH;

	public PageCreaCategorieIntervenant(FrameIhm mere, Controleur ctrl, List<CategorieIntervenant> lstCategorieIntervenant, JTable tableCategorieIntervenant)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.lstCategorieIntervenant = lstCategorieIntervenant;
		this.tableCategorieIntervenant = tableCategorieIntervenant;

		this.dial = new JDialog(mere, "Création d'une catégorie d'intervenant", true);
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

		NumberFormat formatInteger = NumberFormat.getIntegerInstance();
        NumberFormatter formatterInteger = new NumberFormatter(formatInteger);
        formatterInteger.setValueClass(Integer.class);
        formatterInteger.setMinimum(0); // Valeur minimale autorisée
		formatterInteger.setAllowsInvalid(false); // Empêcher les valeurs invalides

        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("#0.00"));
        formatter.setValueClass(Double.class); // Définir la classe de valeur comme Double
        formatter.setMinimum(0.0); // Valeur minimale autorisée
        formatter.setMaximum(Double.MAX_VALUE); // Valeur maximale autorisée
        formatter.setAllowsInvalid(false); // Empêcher les valeurs invalides

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
		this.textFieldMinH = new JFormattedTextField(formatterInteger);
		this.textFieldMinH.setColumns(15);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(lblMinH, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldMinH, gbc);

		// maxH \\
		JLabel lblMaxH = new JLabel("Max. d'heures : ");
		this.textFieldMaxH = new JFormattedTextField(formatterInteger);
		this.textFieldMaxH.setColumns(15);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(lblMaxH, gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.textFieldMaxH, gbc);

		// CoefTP \\
		JLabel lblCoefTP = new JLabel("Coefficient TP : ");
		this.textFieldCoefTP = new JFormattedTextField(formatter);
		this.textFieldCoefTP.setColumns(15);
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
		// this.ctrl.ajouterModule(new Module(null, null, null, false, null, null, null));
		// this.mere.majTable();


		CategorieIntervenant categorieIntervenant = new CategorieIntervenant(this.textFieldCode.getText(), this.textFieldNom.getText(), Integer.parseInt(this.textFieldMinH.getText()), Integer.parseInt(this.textFieldMaxH.getText()), Double.parseDouble(this.textFieldCoefTP.getText()));
		this.lstCategorieIntervenant.add(categorieIntervenant);
		this.tableCategorieIntervenant.repaint();
		this.ctrl.ajouterSauvAttente(categorieIntervenant);

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

