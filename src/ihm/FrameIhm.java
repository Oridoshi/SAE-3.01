package ihm;

import javax.swing.*;

import org.json.JSONObject;

import controleur.Controleur;

import java.io.File;

/**
 * FrameIhm
 */
public class FrameIhm extends JFrame
{
	private Controleur ctrl;

	public FrameIhm(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(PageConnexion.constructeurConnexion(this.ctrl, this, false));
		this.setResizable(false);
		this.setVisible(true);

		this.addWindowListener(new java.awt.event.WindowAdapter() 
		{
			public void windowClosing(java.awt.event.WindowEvent windowEvent) 
			{
				this.resetParamConnexion();

				FrameIhm.this.ctrl.fermerConnexion();
			}

			private void resetParamConnexion()
			{
				JSONObject json = Controleur.getJson();

				if(json != null)
				{
					if(!json.getBoolean("resterConnecte"))
					{
						json.put("chemin", "");
						json.put("identifiant", "");
						json.put("motDePasse", "");
						json.put("resterConnecte", false);
					}
					
					this.recritureFichierParamConnexion(json);
				}
			}

			private void recritureFichierParamConnexion(JSONObject json)
			{
				try
				{
					File file = new File("./data/ParametreConnexion.json");

					java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
					fos.write(json.toString().getBytes("UTF-8"));
					fos.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	// {
	// 	"chemin" : "bernouy.com/sae301",
	// 	"identifiant" : "admin",
	// 	"motDePasse" : "Matthias76930!"
	// }

	public void changerPage(JPanel page)
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(page);
		this.getContentPane().repaint();
		this.getContentPane().revalidate();
	}
}