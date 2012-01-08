import javax.swing.JOptionPane;
import javax.swing.JDialog;

public abstract class Message extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public static void message(String str, int type) {
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
	
	public static String question(String str, String titre) {
		return JOptionPane.showInputDialog(null, str, titre, JOptionPane.QUESTION_MESSAGE);
	}

	public static int confirmation(String str, String titre) {
		return JOptionPane.showConfirmDialog(null, str, titre, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

}
