import java.awt.Rectangle;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.util.Observable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class VueSaisieExemplaire extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitre;
	private JTextField textFieldDateReception;
	private JTextField textFieldNbExemplaires;
	private JTextArea textAreaInfosExemplaires;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonEnreg;
	private JButton buttonFermer;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Ouvrage _ouvrage ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton RadioButtonConsultable;
	private JRadioButton RadioButtonEmpruntable;
	

	public VueSaisieExemplaire(Controleur controleur) {
		super (controleur);
		setTitle("Enregistrement d'un nouvel exemplaire d'ouvrage");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 540, 461);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setBounds(145, 31, 43, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date réception");
		lblNewLabel_1.setBounds(91, 115, 97, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(190, 30, 159, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		textFieldDateReception = new JTextField();
		textFieldDateReception.setEditable(false);
		textFieldDateReception.setText("mm/aaaa");
		textFieldDateReception.setBounds(189, 114, 114, 19);
		contentPane.add(textFieldDateReception);
		textFieldDateReception.setColumns(10);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textFieldIsbn.getText();
				// liaison de la vue avec l'objet observé
				setOuvrage (getControleur().rechOuvrage(isbn));
				setEtat(Vue.inter1);
		}});
		buttonRech.setBounds(357, 27, 107, 25);
		contentPane.add(buttonRech);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateReception = textFieldDateReception.getText();
				String statut;
				if (RadioButtonConsultable.isSelected()) {
					statut = "consultable";}
				else {
					statut = "empruntable";}
					getControleur().nouvExemplaire(getOuvrage(), dateReception, statut);
				}
		});
		buttonEnreg.setBounds(357, 143, 107, 25);
		contentPane.add(buttonEnreg);
		
		JLabel lblNewLabel_2 = new JLabel("Titre ouvrage");
		lblNewLabel_2.setBounds(91, 85, 97, 15);
		contentPane.add(lblNewLabel_2);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(189, 83, 247, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaires");
		lblExemplaires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblExemplaires.setBounds(206, 237, 97, 15);
		contentPane.add(lblExemplaires);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(38, 222, 426, 3);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre d'exemplaires");
		lblNewLabel_3.setBounds(38, 266, 150, 15);
		contentPane.add(lblNewLabel_3);
		
		textFieldNbExemplaires = new JTextField();
		textFieldNbExemplaires.setEditable(false);
		textFieldNbExemplaires.setBounds(216, 264, 49, 19);
		contentPane.add(textFieldNbExemplaires);
		textFieldNbExemplaires.setColumns(10);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieExemplaire.this);
			}
		});
		buttonFermer.setBounds(206, 399, 107, 25);
		contentPane.add(buttonFermer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 291, 427, 96);
		
		textAreaInfosExemplaires = new JTextArea();
		textAreaInfosExemplaires.setEditable(false);
		scrollPane= new JScrollPane(textAreaInfosExemplaires);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(new Rectangle(38, 295, 426, 92));
		contentPane.add(scrollPane);
		
		
		JLabel lblNewLabel_4 = new JLabel("Statut");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(117, 172, 61, 15);
		contentPane.add(lblNewLabel_4);
		
		RadioButtonConsultable = new JRadioButton("Consultable");
		RadioButtonConsultable.setEnabled(false);
		buttonGroup.add(RadioButtonConsultable);
		RadioButtonConsultable.setBounds(190, 164, 134, 23);
		contentPane.add(RadioButtonConsultable);
		
		RadioButtonEmpruntable = new JRadioButton("Empruntable");
		RadioButtonEmpruntable.setEnabled(false);
		buttonGroup.add(RadioButtonEmpruntable);
		RadioButtonEmpruntable.setSelected(true);
		RadioButtonEmpruntable.setBounds(190, 191, 134, 23);
		contentPane.add(RadioButtonEmpruntable);
	}
	
	private Ouvrage getOuvrage() {
		return _ouvrage;
	}
	private void setOuvrage(Ouvrage ouvrage) {
		 _ouvrage = ouvrage;
	}
	
	public void alimente(Ouvrage ouv) {
		textFieldTitre.setText(ouv.getTitre());
		textFieldNbExemplaires.setText (String.valueOf(ouv.getNbExemplaires()));
		textAreaInfosExemplaires.setText("");
		for (int i : ouv.getExemplaires().keySet())
			textAreaInfosExemplaires.append(ouv.getExemplaire(i).afficheInfos());
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			buttonRech.setEnabled(true);
			break;
			}
		case inter1: {
			buttonEnreg.setEnabled(true);
			RadioButtonConsultable.setEnabled(true);
			RadioButtonEmpruntable.setEnabled(true);
			textFieldDateReception.setEditable(true);
			break;
			}
		case alternate:
			getControleur().fermerVue(VueSaisieExemplaire.this);
			this.getControleur().saisirOuvrage();
			break;
		case finale: {
			buttonRech.setEnabled(false);
			buttonFermer.setEnabled(true);
			break;
			}
		}
	}

	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getOuvrage());
		super.update(observable,  objet);
	}	
}
