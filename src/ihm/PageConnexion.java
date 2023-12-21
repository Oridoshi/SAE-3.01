package ihm;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONObject;

import controleur.Controleur;
import metier.DB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

public class PageConnexion extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private FrameIhm mere;

	private JButton btnConnexion;
	private JTextField txtServeur;
	private JTextField txtUtilisateur;
	private JPasswordField txtMotDePasse;
	private JCheckBox cbResterConnecte;
	private JTextField txtBD;

	public static JPanel constructeurConnexion(Controleur ctrl, FrameIhm mere, boolean reconnexion)
	{
		if(reconnexion)
		{
			//si on veut se reconnecter
			return new PageConnexion(ctrl, mere);
		}
		else
		{
			//essai de connexion avec les paramètres de connexion enregistrés
			return PageConnexion.essaiConnexion(ctrl, mere);
		}
	}

	private static JPanel essaiConnexion(Controleur ctrl, FrameIhm mere)
	{
		try 
		{
			JSONObject fichierCheminFichier = new JSONObject(lireFichier((new File("data/ParametreConnexion.json")).getAbsolutePath()));

			
			if(fichierCheminFichier.getBoolean("resterConnecte"))
			{
				Controleur.setJson(fichierCheminFichier);
				
				DB.getInstance().isReadOnly();
				
				return new PageAccueil(ctrl, mere);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mere, "Les données de connexion sauvegarder ne fonctionne pas veuillez les resésire !", "ERREUR CONNEXION", JOptionPane.ERROR_MESSAGE);
		}

		//si la connexion n'a pas fonctionné ou que les paramètres de connexion n'ont pas été enregistrés
		return new PageConnexion(ctrl, mere);
	}
	
	private static String lireFichier(String nomFichier) throws FileNotFoundException
	{
		try (Scanner scanner = new Scanner(new FileInputStream ( nomFichier ), "UTF-8"))
		{
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}

	private PageConnexion(Controleur ctrl, FrameIhm mere)
	{
		this.ctrl = ctrl;
		this.mere = mere;

		this.mere.setTitle("Connexion");

		this.setLayout(new BorderLayout());

		JPanel panelFormulaire = new JPanel();
		panelFormulaire.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Marge autour des composants
		
		this.txtServeur = new JTextField(20);
		this.txtBD = new JTextField(20);
		this.txtUtilisateur = new JTextField(20);
		this.txtMotDePasse = new JPasswordField(20);
		this.cbResterConnecte = new JCheckBox();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFormulaire.add(new JLabel("Serveur : "), gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtServeur, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFormulaire.add(new JLabel("Base de donnée : "), gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtBD, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFormulaire.add(new JLabel("Utilisateur : "), gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtUtilisateur, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelFormulaire.add(new JLabel("Mot de passe : "), gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.txtMotDePasse, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelFormulaire.add(new JLabel("Rester connecté : "), gbc);
		gbc.gridx = 1;
		panelFormulaire.add(this.cbResterConnecte, gbc);


		this.btnConnexion = new JButton("Connexion");
		this.btnConnexion.addActionListener(this);

		this.add(this.btnConnexion, BorderLayout.SOUTH);
		this.add(panelFormulaire, BorderLayout.CENTER);
	}

	private void connexion()
	{
		try
		{
			JSONObject fichierCheminFichierLocal = new JSONObject();
			fichierCheminFichierLocal.put("chemin", this.txtServeur.getText() + "/" + this.txtBD.getText());
			fichierCheminFichierLocal.put("identifiant", this.txtUtilisateur.getText());
			String mdp = "";
			for (char c : this.txtMotDePasse.getPassword())
			{
				mdp += c;
			}
			fichierCheminFichierLocal.put("motDePasse", mdp);
			fichierCheminFichierLocal.put("resterConnecte", this.cbResterConnecte.isSelected());

			Controleur.setJson(fichierCheminFichierLocal);

			DB.getInstance().isReadOnly();

			ArrayList<String> listTable = new ArrayList<>();
			listTable.add("semestre");
			listTable.add("categorieintervenant");
			listTable.add("categorieheure");
			listTable.add("categoriemodule");
			listTable.add("module");
			listTable.add("remplirprogramme");
			listTable.add("remplircategoriemodule");
			listTable.add("intervenant");
			listTable.add("affectation");

			boolean manqueTable = false;
			for (String table : listTable)
			{
				if(!DB.tableExiste(table))
				{
					manqueTable = true;
				}
			}

			if(manqueTable)
			{
				int rep = JOptionPane.showConfirmDialog(this.mere, "Une ou plusieurs tables n'éxiste pas, voulez-vous les créer ?", "Suppression", JOptionPane.YES_NO_OPTION);
				if(rep == JOptionPane.YES_OPTION)
				{
					this.ctrl.initialiserTable();
				}
				else
				{
					this.mere.changerPage(new PageConnexion(this.ctrl, this.mere));
					return;
				}
			}

			//si on veut se connecter avec les paramètres de connexion enregistrés a la prochaine connexion
			if(this.cbResterConnecte.isSelected())
			{
				File file = new File("data/ParametreConnexion.json");
	
				java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
				fos.write(fichierCheminFichierLocal.toString().getBytes("UTF-8"));
				fos.close();
			}

			this.mere.changerPage(new PageAccueil(this.ctrl, this.mere));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this.mere, "Les données de connexion que vous avez entré ne fonctionne pas veuillez réessayer ! ", "ERREUR CONNEXION", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.connexion();
	}
}
