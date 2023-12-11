package ihm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controleur.Controleur;
import ihm.classPerso.ModelAffichageSemestre;

import java.awt.BorderLayout;

public class pageSemestre extends JPanel
{
	private Controleur ctrl;
	private JTable     tableRessource;

	public pageSemestre(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.add(new JLabel("Liste des intervenants"), BorderLayout.NORTH);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		JScrollPane spDonneIntervenant;

		this.tableRessource = new JTable( new ModelAffichageSemestre(this.ctrl, this.ctrl.getlstRessource(1)) );
		this.tableRessource.setFillsViewportHeight(true);
		this.tableRessource.setRowHeight(25);
		// this.tableRessource.setDefaultRenderer(Object.class, new ModelLignePerso());

		spDonneIntervenant = new JScrollPane(this.tableRessource);

		this.add(spDonneIntervenant, BorderLayout.CENTER);
	}
}
