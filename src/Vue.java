import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
/**
 * Super classe des vues
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public abstract class Vue extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;

	// énumération des états possibles pour une vue
	// cet état permet de contrôler l'enchaînement des dialogues possibles pour l'utilisateur
	// dans une vue
	final static int initiale = 0, inter1 = 1, inter2 = 2, inter3 = 3, inter4 = 4, finale = 5;
	
	private Controleur _controleur;
	private int _etat;
	private JFrame frame;

	public Vue(Controleur controleur) {
		frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
            	getControleur().fermerVue(Vue.this);
            }
        });
        frame.getContentPane().setLayout(null);
		this.setControleur(controleur);
	}
	
	protected Controleur getControleur() { return _controleur; }
	protected void setControleur(Controleur cont) { _controleur = cont; }
	public int getEtat() { return _etat; }
	public void setEtat(int etat) { _etat = etat; }
	public JFrame getFrame() { return frame; }
	
	// méthode de la classe interface Observer 
	// à implémenter par toute classe réalisant cette interface
	public void update(Observable observable, Object objet) {
		this.repaint();
	} 
	
}
