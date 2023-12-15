package ihm.classPerso;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.NumberFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

/**
 * <p>
 * Un champ de texte personnalisé pour les entiers. Cette classe étend JTextField
 * et fournit des fonctionnalités spécifiques pour la gestion des entiers, y compris
 * la possibilité de bloquer les caractères non valides et le formatage du texte avec
 * des espaces comme séparateurs de milliers.
 * </p>
 * 
 * @see JTextField
 */
public class JIntegerTextField extends JTextField implements KeyListener, FocusListener {

	private boolean bloquerCaractereNonValide;

	/**
	 * Constructeur par défaut. Initialise un champ de texte sans limite de caractères
	 * et autorise les caractères non valides.
	 */
	public JIntegerTextField() {
		super();
		this.bloquerCaractereNonValide = false;

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	/**
	 * Constructeur avec une limite de caractères. Initialise un champ de texte avec
	 * la limite spécifiée et autorise les caractères non valides.
	 * 
	 * @param nbCaractere La limite de caractères du champ de texte.
	 */
	public JIntegerTextField(int nbCaractere) {
		super(nbCaractere);
		this.bloquerCaractereNonValide = false;

		this.addKeyListener(this);
		this.addFocusListener(this);
	}


	/**
	 * Constructeur avec une limite de caractères et une valeur par défaut.
	 * Initialise un champ de texte avec la limite spécifiée, une valeur par défaut
	 * et autorise les caractères non valides.
	 * 
	 * @param nbCaractere La limite de caractères du champ de texte.
	 * @param valeurDefaut La valeur par défaut à afficher dans le champ.
	 */
	public JIntegerTextField(int nbCaractere, int valeurDefaut) {
		super(nbCaractere);
		this.bloquerCaractereNonValide = false;
		this.setText("" + valeurDefaut);

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	/**
	 * Obtient la valeur entière correspondant au texte du champ.
	 * 
	 * @return La valeur entière du champ ou 0 si le champ est vide.
	 */
	public int getValue() {
		if (this.getText().equals("")) return 0;
		return Integer.parseInt(this.getText().replace(" ", ""));
	}

	public void setValue(int value){
		NumberFormat numberFormat = NumberFormat.getInstance();
		String nombreAvecEspace = numberFormat.format(value);
		this.setText(nombreAvecEspace);
	}

	/**
	 * Vérifie si le champ de texte est vide.
	 * 
	 * @return {@code true} si le champ est vide, {@code false} sinon.
	 */
	public boolean isEmpty() {
		return this.getText().equals("");
	}

	/**
	 * Active ou désactive le blocage des caractères non valides.
	 * 
	 * @param bloquer {@code true} pour bloquer les caractères non valides, {@code false} sinon.
	 */
	public void bloquerCaractereNonValide(boolean bloquer) {
		this.bloquerCaractereNonValide = bloquer;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (this.bloquerCaractereNonValide)
			if (!verifCaractere(e.getKeyChar()))
				e.consume();
	}

	/**
	 * Vérifie si le caractère entré est un chiffre.
	 * 
	 * @param charactereEntree Le caractère à vérifier.
	 * @return {@code true} si le caractère est un chiffre, {@code false} sinon.
	 */
	private boolean verifCaractere(char charactereEntree) {
		String regex = "\\d{1}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(charactereEntree + "");
		return matcher.matches();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Non utilisé dans cette implémentation
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Non utilisé dans cette implémentation
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().equals("")) return;

		if (!this.bloquerCaractereNonValide)
			try {
				Integer.parseInt(this.getText());
			} catch (Exception err) {
				this.setText("");
			}

		if (!this.getText().equals("")) {
			NumberFormat numberFormat = NumberFormat.getInstance();
			String nombreAvecEspace = numberFormat.format(Integer.parseInt(this.getText()));
			this.setText(nombreAvecEspace);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// Non utilisé dans cette implémentation
	}
}