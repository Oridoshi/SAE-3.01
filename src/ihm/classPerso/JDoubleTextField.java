package ihm.classPerso;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.text.NumberFormat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;


public class JDoubleTextField extends JTextField implements KeyListener, FocusListener {

	private boolean autoriserCaractereInvalide;

	public JDoubleTextField() {
		super();
		this.autoriserCaractereInvalide = true;

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	public JDoubleTextField(int nbCaractere) {
		super(nbCaractere);
		this.autoriserCaractereInvalide = true;

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	public JDoubleTextField(int nbCaractere, int valeurDefaut) {
		super(nbCaractere);
		this.autoriserCaractereInvalide = true;
		this.setText(valeurDefaut + ".0");

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	public JDoubleTextField(int nbCaractere, double valeurDefaut) {
		super(nbCaractere);
		this.autoriserCaractereInvalide = true;
		this.setValue(valeurDefaut);

		this.addKeyListener(this);
		this.addFocusListener(this);
	}

	public Double getValue() {
		if (this.getText().equals("")) return 0.0;
		return Double.parseDouble(this.getText().replace(" ", "").replace(",", "."));
	}

	public void setValue(int value){
		NumberFormat numberFormat = NumberFormat.getInstance();
		String nombreAvecEspace = numberFormat.format(value);
		this.setText(nombreAvecEspace);
	}

	public void setValue(double value)
	{
		NumberFormat numberFormat = NumberFormat.getInstance();
		String nombreAvecEspace = numberFormat.format(value);
		this.setText(nombreAvecEspace);
	}

	public boolean isEmpty() {
		return this.getText().equals("");
	}

	public void setAllowsInvalid(boolean allows) {
		this.autoriserCaractereInvalide = allows;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (!this.autoriserCaractereInvalide)
			if (!verifCaractere(e.getKeyChar()))
				e.consume();
	}

	private boolean verifCaractere(char charactereEntree) {
		String regex = "\\d{1}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(charactereEntree + "");
		if(matcher.matches()) return true;

		if(charactereEntree == ',' || charactereEntree == '.')
			if(!(this.getText().contains(".") || this.getText().contains(",")))
				return true;

		return false;
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

		if (this.autoriserCaractereInvalide)
			try {
				Integer.parseInt(this.getText());
			} catch (Exception err) {
				this.setText("");
			}

		NumberFormat numberFormat = NumberFormat.getInstance();
		String nombreAvecEspace = numberFormat.format(Double.parseDouble(this.getText().replace(",", ".")));
		this.setText(nombreAvecEspace);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// Non utilisé dans cette implémentation
	}

}