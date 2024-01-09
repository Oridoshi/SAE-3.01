package ihm.classPerso;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import metier.model.Intervenant;

public class ModelLignePerso extends DefaultTableCellRenderer
{
	private static final Color COULEUR_TROP_HEURES = new Color(255, 179, 179); // Couleur pour les lignes impaires
	private static final Color COULEUR_PAS_ASSEZ_HEURES = new Color(179, 197, 255); // Couleur pour les lignes impaires
	// private static final Color COULEUR_FONT = new Color(255, 255, 255); // Couleur pour les textes impaires
	private List<Intervenant> lstIntervenants;

	public ModelLignePerso(List<Intervenant> lstIntervenants)
	{
		super();

		this.lstIntervenants = lstIntervenants;
	}


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		Intervenant intervenantActu = lstIntervenants.get(row);
		// Si un intervenant a trop d'heures
		if(intervenantActu.getTotal() > intervenantActu.gethMax())
		{
			component.setBackground(COULEUR_TROP_HEURES);
			// component.setForeground(COULEUR_FONT);
		}
		// Si un intervenant a trop peu d'heures
		else if(intervenantActu.getTotal() < intervenantActu.getHMin())
		{
			component.setBackground(COULEUR_PAS_ASSEZ_HEURES);
			// component.setForeground(COULEUR_FONT);
		}
		// Si un intervenant a un nombre d'heures correct
		else
		{
			component.setBackground(table.getBackground());
			// component.setForeground(table.getForeground());
		}

		return component;
	}
}
