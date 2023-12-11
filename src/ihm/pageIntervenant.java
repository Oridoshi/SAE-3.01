package ihm;

import javax.swing.*;
import java.awt.BorderLayout;

public class pageIntervenant extends JPanel
{
	public pageIntervenant()
	{
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Liste des intervenants"), BorderLayout.NORTH);
	}
}
