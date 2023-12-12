import javax.swing.*;
import java.awt.*;

public class ExempleGridBagLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Exemple GridBagLayout avec BorderLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marge entre les composants

        // Ajouter un composant (par exemple, un JLabel) aligné à gauche
        JLabel label1 = new JLabel("Composant 1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.weightx = 0; // Poids pour occuper l'espace disponible
        gridBagPanel.add(label1, gbc);

         // Ajouter un composant (par exemple, un JLabel) aligné à gauche
        JLabel label2 = new JLabel("Composant 12");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gridBagPanel.add(label2, gbc);

        // Ajouter un autre composant (par exemple, un JTextField) aligné à gauche
        JTextField textField = new JTextField(10);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.weightx = 1.0
        ; // Poids pour occuper l'espace disponible
        gridBagPanel.add(textField, gbc);

        mainPanel.add(gridBagPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
