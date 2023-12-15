package ihm;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public PageAccueil(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;


		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints(); 
		c.insets = new Insets(0, 0, 7, 7); 

		GridBagConstraints gbc = new GridBagConstraints();

		// lblTitre //
		lblTitre = new JLabel("ASTRE");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 36));
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(lblTitre, gbc);

		// lblVersion //
		lblVersion = new JLabel(this.ctrl.getVersion());
		gbc.gridy = 1;
		this.add(lblVersion, gbc);

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(5, 1, 5, 0));

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

		// btnTestPage //
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(this);
		panelBtn.add(btnTest, gbc);
		
		gbc.gridy = 2;
		this.add(panelBtn, gbc);
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
			//this.mere.changerPage(new PageParametres(this.ctrl, this.mere));
		}
		// else
		// {
		// 	this.mere.changerPage(new PageEditionModule(this.ctrl, this.mere));
		// }
	}
}
