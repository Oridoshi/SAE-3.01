package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import controleur.Controleur;
import html.GenererPage;
import metier.model.Intervenant;
import metier.model.Semestre;
import metier.model.Module;

public class PageEtats extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private FrameIhm mere;

	private JButton btnAccueil;

	private JPanel panelBtns;

	private JComboBox<String> cbxIntervenant;
	private JButton btnEtatIntervenant;

	private JComboBox<String> cbxModule;
	private JButton btnEtatModule;

	private JButton btnCSV;

	public PageEtats(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.btnAccueil = new JButton("Accueil");
		this.btnAccueil.setSize(10, 5);

		this.panelBtns = new JPanel();
		this.panelBtns.setLayout(new FlowLayout());

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


		this.add(this.btnAccueil, BorderLayout.NORTH);

		this.panelBtns.add(this.cbxIntervenant);
		this.panelBtns.add(this.btnEtatIntervenant);

		this.panelBtns.add(this.cbxModule);
		this.panelBtns.add(this.btnEtatModule);

		this.panelBtns.add(this.btnCSV);



		this.add(this.panelBtns, BorderLayout.CENTER);


		this.btnAccueil.addActionListener(this);

		this.btnEtatIntervenant.addActionListener(this);	
		this.btnEtatModule.addActionListener(this);
		this.btnCSV.addActionListener(this);
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
						
					String chemin = new File(inter.getId() + "_" + inter.getNom() + "_" + inter.getPrenom() + ".html").getAbsolutePath();
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
						
						String chemin = new File(inter.getId() + "_" + inter.getNom() + "_" + inter.getPrenom() + ".html").getAbsolutePath();
						ouvrirePageWeb(chemin);
					}
				}

				//GenererPage.genererPageInter(intervenant);
				
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
						
					String chemin = new File(module.getCode() + ".html").getAbsolutePath();
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
						
						String chemin = new File(module.getCode() + ".html").getAbsolutePath();
						ouvrirePageWeb(chemin);
					}
				}

				//GenererPage.genererPageInter(intervenant);
				
			}
		}

		if (e.getSource() == this.btnCSV)
		{
			//générer CSV
		}

		if (e.getSource() == this.btnAccueil)
		{
			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
	}

	private void ouvrirePageWeb(String url)
	{
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
