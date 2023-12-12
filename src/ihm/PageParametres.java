package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;
import metier.model.CategorieIntervenant;
import metier.model.Module;
import metier.model.Semestre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class PageParametres extends JPanel// implements ActionListener
{
	private Controleur ctrl;



	private JPanel panelBoutons;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	private JFrame mere;
	private JTabbedPane tabbedPane;

	public PageParametres(Controleur ctrl, JFrame mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.mere.setTitle("Paramètres");
		this.setBorder(new EmptyBorder(15, 30, 15, 30));

		// Ajout du tabbedPane
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.addTab("Catégories d'intervenants", new PanelCategoriesIntervenant(ctrl, this.ctrl.getLstCategorieIntervenant));
		this.tabbedPane.addTab("Catégories d'heures", new PanelCategoriesHeure(ctrl, this.ctrl.getLstCategorieHeure));
		
		this.add(this.tabbedPane, BorderLayout.CENTER);


		// Ajout du panel de boutons général
		this.panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		this.btnEnregistrer = new JButton("Enregistrer");
		this.btnAnnuler = new JButton("Annuler");

		// Ajout des boutons
		this.add(panelBoutons, BorderLayout.SOUTH);
		this.panelBoutons.add(this.btnEnregistrer);
		this.panelBoutons.add(this.btnAnnuler);
	}


	/*---------------------------*/
	//   Catégorie Intervenant   //
	/*---------------------------*/
	private class PanelCategoriesIntervenant extends JPanel
	{
		private Controleur ctrl;
		private ArrayList<CategorieIntervenant> lstCategorieIntervenant;

		private JPanel panelBoutonsTableau;
		private JButton btnAjouter;
		private JButton btnSupprimer;

		public PanelCategoriesIntervenant(Controleur ctrl, ArrayList<CategorieIntervenant> lstCategorieIntervenant)
		{
			this.ctrl = ctrl;
			this.lstCategorieIntervenant = lstCategorieIntervenant;

			this.setLayout(new BorderLayout());

			this.add(new JLabel("Catégories d'intervenants"), BorderLayout.NORTH);


			// Ajout des boutons
			this.panelBoutonsTableau = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

			this.btnAjouter = new JButton("ajouter");
			this.btnAjouter = new JButton("supprimer");

			this.panelBoutonsTableau.add(this.btnAjouter);
			this.panelBoutonsTableau.add(this.btnAjouter);
		}

	}
}