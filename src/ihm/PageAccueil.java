package ihm;

import javax.swing.*;

import java.awt.*;

import controleur.Controleur;

public class PageAccueil extends JPanel
{
	private Controleur ctrl;

	private JLabel lblTitre;
	private JLabel lblVersion;
	private JButton btnParametres;
	private JButton btnPrevisionnel;
	private JButton btnIntervenants;
	private JButton btnEtats;

	public PageAccueil(Controleur ctrl)
	{
		this.ctrl = ctrl;


		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints(); 
		c.insets = new Insets(0, 0, 7, 7); 

		// lblTitre //
		lblTitre = new JLabel("ASTRE");
		//lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 36));
		this.add(lblTitre, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 7, 7), 0, 0));

		// lblVersion //
		lblVersion = new JLabel("" + this.ctrl.getVersion());
		this.add(lblVersion, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 7), 0, 0));

		// btnParametres //
		btnParametres = new JButton("Paramètres");
		this.add(btnParametres, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 7, 7), 0, 0));

		// btnParametres //
		btnPrevisionnel = new JButton("Prévisionnel");
		this.add(btnPrevisionnel, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 7, 7), 0, 0));

		// btnParametres //
		btnIntervenants = new JButton("Intervenants");
		this.add(btnIntervenants, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 7, 7), 0, 0));

		// btnParametres //
		btnEtats = new JButton("Etats");
		this.add(btnEtats, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 7, 7), 0, 0));

	}
}
