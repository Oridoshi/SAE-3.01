package ihm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import controleur.Controleur;

public class PageConnexion extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private FrameIhm mere;

	private JButton btnConnexion;
	public PageConnexion(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.mere.setTitle("Connexion");

		this.btnConnexion = new JButton("Connexion");
		this.btnConnexion.addActionListener(this);
		this.add(this.btnConnexion);
	}

	private void connexion()
	{
		try
		{
			// JSONObject fichierCheminFichier = new JSONObject(lireFichier("/ParametreConnexion.json"));
			// System.out.println(fichierCheminFichier.getString("chemin"));

			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this.mere, "Les données de connexion que vous avez entré ne fonctionne pas veuillez réessayer ! ", "ERREUR CONNEXION", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}

	private String lireFichier(String nomFichier) throws IOException
	{
		InputStream ips = this.getClass().getResourceAsStream(nomFichier);
		try (Scanner scanner = new Scanner(ips, "UTF-8"))
		{
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.connexion();
	}
}
