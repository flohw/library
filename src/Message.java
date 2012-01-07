import javax.swing.JOptionPane;

//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//
//import javax.swing.JButton;
import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class Message extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public Message(String str, int type) {
		int typeMsg = JOptionPane.INFORMATION_MESSAGE;
		String titre = "Information";
		switch (type) {
		case Controleur.information:
			typeMsg = JOptionPane.INFORMATION_MESSAGE;
			titre = "Information";
			break;
		case Controleur.attention:
			typeMsg = JOptionPane.WARNING_MESSAGE;
			titre = "Attention";
			break;
		case Controleur.erreur:
			typeMsg = JOptionPane.ERROR_MESSAGE;
			titre = "Erreur";
			break;
		}
		JOptionPane.showMessageDialog(null, str, titre, typeMsg);
	}

}
