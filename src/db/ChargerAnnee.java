package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import ihm.FrameIhm;
import metier.DB;
import metier.repo.DuplicationDB;

public class ChargerAnnee
{
	private JComboBox<String> comboBox;

	public ChargerAnnee(Controleur ctrl, FrameIhm mere)
	{
		// Créer une JComboBox avec les options
		this.comboBox = new JComboBox<String>((new Vector<>(DuplicationDB.list())));

		// Créer un JPanel pour contenir la JComboBox
		JPanel panel = new JPanel();
		panel.add(this.comboBox);

		// Afficher la boîte de dialogue avec la JComboBox
		int choix = JOptionPane.showOptionDialog(
				null,
				panel,
				"Choisissez une année",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				null);

		// Récupérer le choix de l'utilisateur
		if (choix == JOptionPane.OK_OPTION)
		{
			String optionSelectionnee = (String) comboBox.getSelectedItem();
			try
			{
				Connection db = DB.getInstance();
				
				Scanner sc = new Scanner(DuplicationDB.getDuplication(optionSelectionnee));
				PreparedStatement ps;
				while (sc.hasNextLine())
				{
					String line = sc.nextLine();
					ps = db.prepareStatement(line);
					ps.executeUpdate();
				}

				ctrl.reset();
			} catch (Exception e) { System.out.println(e.getMessage()); }
		}
	}
}

