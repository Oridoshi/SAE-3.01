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

	private JComboBox<String> cbxIntervenant;
	private JButton btnEtatIntervenant;

	private JComboBox<String> cbxModule;
	private JButton btnEtatModule;

	private FrameIhm mere;

	public PageEtats(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.setLayout(new FlowLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));


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


		this.add(this.cbxIntervenant);
		this.add(this.btnEtatIntervenant);

		this.add(this.cbxModule);
		this.add(this.btnEtatModule);

		this.btnEtatIntervenant.addActionListener(this);	
		this.btnEtatModule.addActionListener(this);
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
