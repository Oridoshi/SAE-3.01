package ihm;

import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import controleur.Controleur;

public class PageAccueil extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JLabel lblTitre;
	private JLabel lblVersion;
	private JButton btnParametres;
	private JButton btnPrevisionnel;
	private JButton btnIntervenants;
	private JButton btnEtats;

	private FrameIhm mere;

	private JButton btnDeconnexion;

	public PageAccueil(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints(); 
		c.insets = new Insets(0, 0, 7, 7); 

		GridBagConstraints gbc = new GridBagConstraints();

		// lblTitre //
		lblTitre = new JLabel("ASTRE");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 36));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCentre.add(lblTitre, gbc);

		// lblVersion //
		lblVersion = new JLabel(this.ctrl.getVersion());
		gbc.gridy = 1;
		panelCentre.add(lblVersion, gbc);

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(4, 1, 5, 0));

		// btnParametres //
		this.btnParametres = new JButton("Paramètres");
		this.btnParametres.addActionListener(this);
		panelBtn.add(btnParametres, gbc);

		// btnParametres //
		this.btnPrevisionnel = new JButton("Prévisionnel");
		this.btnPrevisionnel.addActionListener(this);
		panelBtn.add(btnPrevisionnel, gbc);

		// btnIntervenants //
		this.btnIntervenants = new JButton("Intervenants");
		this.btnIntervenants.addActionListener(this);
		this.btnIntervenants.setPreferredSize(new Dimension(500, 500));
		panelBtn.add(btnIntervenants, gbc);

		// btnParametres //
		this.btnEtats = new JButton("Etats");
		this.btnEtats.addActionListener(this);
		panelBtn.add(btnEtats, gbc);

		// // btnTestPage //
		// JButton btnTest = new JButton("Test");
		// btnTest.addActionListener(this);
		// panelBtn.add(btnTest, gbc);
		
		gbc.gridy = 2;
		panelCentre.add(panelBtn, gbc);


		JPanel panelBtn2 = new JPanel();
		panelBtn2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// btnDeconnexion //
		this.btnDeconnexion = new JButton("Déconnexion");
		this.btnDeconnexion.addActionListener(this);
		panelBtn2.add(btnDeconnexion);


		this.setLayout(new BorderLayout());

		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelBtn2, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnParametres)
		{
			this.mere.changerPage(new PageParametres(this.ctrl, this.mere));
		}
		else if (e.getSource() == this.btnPrevisionnel)
		{
			this.mere.changerPage(new PagePrevisionnel(this.ctrl, this.mere));
		}
		else if (e.getSource() == this.btnIntervenants)
		{
			this.mere.changerPage(new PageIntervenants(this.ctrl, this.mere));
		}
		else if (e.getSource() == this.btnEtats)
		{
			this.mere.changerPage(new PageEtats(this.ctrl, this.mere));
		}
		else if (e.getSource() == this.btnDeconnexion)
		{
			this.ctrl.fermerConnexion();

			try
			{
				JSONObject json = Controleur.getJson();

				json.put("chemin", "");
				json.put("identifiant", "");
				json.put("motDePasse", "");
				json.put("resterConnecte", false);

				File file = new File("data/ParametreConnexion.json");

				java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
				fos.write(json.toString().getBytes("UTF-8"));
				fos.close();

			} catch (Exception ex){System.out.println(ex.getMessage());}

			this.mere.changerPage(PageConnexion.constructeurConnexion(this.ctrl, this.mere, true));
		}
	}
}
