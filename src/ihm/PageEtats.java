package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import controleur.Controleur;
import db.ChargerAnnee;
import db.CreationAnnee;
import html.GenererCSV;
import html.GenererPage;
import metier.model.Intervenant;
import metier.model.Module;

public class PageEtats extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private FrameIhm mere;

	private JButton btnAccueil;

	private JComboBox<String> cbxIntervenant;
	private JButton btnEtatIntervenant;

	private JComboBox<String> cbxModule;
	private JButton btnEtatModule;

	private JButton btnCSV;

	private JButton btnResetData;

	private JButton btnCreeAnnee;

	private JButton btnChargerAnnee;

	public PageEtats(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.btnAccueil = new JButton("Accueil");

		JPanel panelAccueil = new JPanel();
		panelAccueil.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAccueil.add(this.btnAccueil);


		JPanel panelBtns = new JPanel();
		panelBtns.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		this.cbxIntervenant = new JComboBox<String>();
		this.cbxIntervenant.addItem("Tous les intervenants");
		for (Intervenant intervenant : this.ctrl.getLstIntervenants())
		{
			this.cbxIntervenant.addItem(intervenant.getPrenom() + " " + intervenant.getNom());
		}

		this.btnEtatIntervenant = new JButton("Générer l'état de l'intervenant");


		this.cbxModule = new JComboBox<String>();
		this.cbxModule.addItem("Tous les modules");
		for (Module module : this.ctrl.getLstModule())
		{
			this.cbxModule.addItem(module.getCode() + " " + module.getLibelleLong());
		}

		this.btnEtatModule = new JButton("Générer l'état du module");


		this.btnCSV = new JButton("Générer le fichier CSV Intervenants");

		this.btnResetData = new JButton("Reset Data");

		this.btnCreeAnnee = new JButton("Créer une année");
		this.btnChargerAnnee = new JButton("Charger une année");	

		//premiere ligne
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBtns.add(this.cbxIntervenant, gbc);
		gbc.gridx = 1;
		panelBtns.add(this.btnEtatIntervenant, gbc);

		//deuxieme ligne
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBtns.add(this.cbxModule, gbc);
		gbc.gridx = 1;
		panelBtns.add(this.btnEtatModule, gbc);

		//troisieme ligne
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panelBtns.add(this.btnCSV, gbc);

		//quatrieme ligne
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		panelBtns.add(this.btnCreeAnnee, gbc);

		//cinquieme ligne
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		panelBtns.add(this.btnChargerAnnee, gbc);

		//sixieme ligne
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		panelBtns.add(new JLabel(""), gbc);

		//septieme ligne
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		panelBtns.add(this.btnResetData, gbc);


		this.add(panelAccueil, BorderLayout.NORTH);
		this.add(panelBtns, BorderLayout.CENTER);


		this.btnAccueil.addActionListener(this);
		this.btnEtatIntervenant.addActionListener(this);	
		this.btnEtatModule.addActionListener(this);
		this.btnCSV.addActionListener(this);
		this.btnCreeAnnee.addActionListener(this);
		this.btnChargerAnnee.addActionListener(this);
		this.btnResetData.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnEtatIntervenant)
		{
			String nomIntervenant = (String) this.cbxIntervenant.getSelectedItem();
			System.out.println(nomIntervenant);
			if (nomIntervenant.equals("Tous les intervenants"))
			{
				for (Intervenant inter : this.ctrl.getLstIntervenants())
				{
					GenererPage.genererPageInter(inter);
						
					String chemin = "data/html/interv/" + inter.getId() + "_" + inter.getNom() + "_" + inter.getPrenom() + ".html";
					ouvrirePageWeb(chemin);
				}
			}
			else
			{
				for (Intervenant inter : this.ctrl.getLstIntervenants())
				{
					if ((inter.getPrenom() + " " + inter.getNom()).equals(nomIntervenant))
					{
						GenererPage.genererPageInter(inter);
						
						String chemin = "data/html/interv/" + inter.getId() + "_" + inter.getNom() + "_" + inter.getPrenom() + ".html";
						ouvrirePageWeb(chemin);
					}
				}
			}
		}

		if (e.getSource() == this.btnEtatModule)
		{
			String nomModule = (String) this.cbxModule.getSelectedItem();
			System.out.println(nomModule);
			if (nomModule.equals("Tous les modules"))
			{
				for (Module module : this.ctrl.getLstModule())
				{
					GenererPage.genererPageModule(module);
						
					String chemin = "data/html/module/" + module.getCode() + ".html";
					ouvrirePageWeb(chemin);
				}
			}
			else
			{
				for (Module module : this.ctrl.getLstModule())
				{
					if ((module.getCode() + " " + module.getLibelleLong()).equals(nomModule))
					{
						GenererPage.genererPageModule(module);
						
						String chemin = "data/html/module/" + module.getCode() + ".html";
						ouvrirePageWeb(chemin);
					}
				}
			}
		}

		if (e.getSource() == this.btnCSV)
		{
			new GenererCSV(this.ctrl.getLstIntervenants());
		}

		if (e.getSource() == this.btnAccueil)
		{
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}

		if(e.getSource() == this.btnCreeAnnee)
		{
			String nomAnnee = JOptionPane.showInputDialog(this.mere, "Veuillez entrer le nom de l'année sur la quelle vous être entrains de travailler afin de la sauvegarder", "Création d'une année", JOptionPane.QUESTION_MESSAGE);
			if (nomAnnee != null)
			{
				if(nomAnnee.equals(""))
					new CreationAnnee("NoName", this.mere, this.ctrl);
				else
					new CreationAnnee(nomAnnee, this.mere, this.ctrl);
			}
		}

		if(e.getSource() == this.btnChargerAnnee)
		{
			new ChargerAnnee(this.ctrl, this.mere);
		}

		if (e.getSource() == this.btnResetData)
		{
			int rep = JOptionPane.showConfirmDialog(this.mere, "Vous êtes sur le point de supprimer tout les données de vos tables et de les remettre par défault, voulez-vous continuez ?", "Suppression", JOptionPane.YES_NO_OPTION);
			if(rep == JOptionPane.YES_OPTION)
			{
				this.ctrl.initialiserTable();
				this.ctrl.reset();
			}
		}
	}

	private void ouvrirePageWeb(String url)
	{
		System.out.println(url);
		try
		{
			Desktop.getDesktop().browse((new File(url)).toURI());
		}
		catch (java.io.IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
