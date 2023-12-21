package ihm;

import java.io.File;

import javax.swing.*;
import controleur.Controleur;

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
		this.setIconImage(new ImageIcon(new File("data/icon/ASTRE_Logo.png").getAbsolutePath()).getImage());
		this.setResizable(false);
		this.setVisible(true);

		this.addWindowListener(new java.awt.event.WindowAdapter() 
		{
			public void windowClosing(java.awt.event.WindowEvent windowEvent) 
			{
				FrameIhm.this.ctrl.fermerConnexion();
			}
		});
	}

	public void changerPage(JPanel page)
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(page);
		this.getContentPane().repaint();
		this.getContentPane().revalidate();
	}
}