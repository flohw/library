import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class VueSaisieExemplaire extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitre;
	private JTextField textFieldDateReception;
	private JTextField textFieldNbExemplaires;
	
	private JList listExemplaires;
	private DefaultListModel modeleExemplaires = new DefaultListModel();
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonEnreg;
	private JButton buttonFermer;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Ouvrage _ouvrage ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton RadioButtonConsultable;
	private JRadioButton RadioButtonEmpruntable;
	private JScrollPane scrollExemplaires;
	private JScrollPane scrollIsbn;
	private JList listIsbn;
	private DefaultListModel modeleIsbn = new DefaultListModel();

	public VueSaisieExemplaire(Controleur controleur) {
		super (controleur);
		contentPane = new JPanel();
		getFrame().setTitle("Enregistrement d'un nouvel exemplaire d'ouvrage");
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		getFrame().setBounds(100, 100, 590, 610);
		getFrame().setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setBounds(45, 12, 30, 14);
		contentPane.add(lblNewLabel);
		
		for (Integer isbn : getControleur().getOuvrages().keySet())
			modeleIsbn.addElement(getControleur().getOuvrage(isbn).afficheOuvrage());
		listIsbn = new JList(modeleIsbn);
		listIsbn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollIsbn = new JScrollPane(listIsbn);
		scrollIsbn.setBounds(251, 10, 259, 114);
		
		JLabel lblNewLabel_1 = new JLabel("Date réception");
		lblNewLabel_1.setBounds(45, 197, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldDateReception = new JTextField();
		textFieldDateReception.setBounds(251, 195, 114, 18);
		textFieldDateReception.setEditable(false);
		textFieldDateReception.setText("mm/aaaa");
		contentPane.add(textFieldDateReception);
		textFieldDateReception.setColumns(10);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.setBounds(45, 69, 114, 24);
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// liaison de la vue avec l'objet observé
				int index = listIsbn.getSelectedIndex();
				if (index == -1)
					Message.message("Vous devez sélectionner un ouvrage", Controleur.information);
				else
					getControleur().rechOuvrage(modeleIsbn.get(index).toString());
		}});
		contentPane.add(buttonRech);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.setBounds(420, 192, 114, 24);
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statut;
				if (RadioButtonConsultable.isSelected()) {
					statut = "consultable";}
				else {
					statut = "empruntable";}
					getControleur().nouvExemplaire(getOuvrage(), textFieldDateReception.getText(), statut);
				}
		});
		contentPane.add(buttonEnreg);
		
		JLabel lblNewLabel_2 = new JLabel("Titre ouvrage");
		lblNewLabel_2.setBounds(45, 154, 95, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(251, 152, 257, 18);
		textFieldTitre.setEditable(false);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaires");
		lblExemplaires.setBounds(251, 316, 82, 14);
		lblExemplaires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		contentPane.add(lblExemplaires);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre d'exemplaires");
		lblNewLabel_3.setBounds(78, 344, 158, 14);
		contentPane.add(lblNewLabel_3);
		
		textFieldNbExemplaires = new JTextField();
		textFieldNbExemplaires.setBounds(284, 342, 114, 18);
		textFieldNbExemplaires.setEditable(false);
		contentPane.add(textFieldNbExemplaires);
		textFieldNbExemplaires.setColumns(10);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.setBounds(280, 536, 84, 24);
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieExemplaire.this);
				getControleur().menuBiblio();
			}
		});
		contentPane.add(buttonFermer);
		
		listExemplaires = new JList(modeleExemplaires);
		scrollExemplaires= new JScrollPane(listExemplaires);
		scrollExemplaires.setBounds(78, 372, 460, 131);
		scrollExemplaires.setEnabled(false);
		contentPane.add(scrollExemplaires);
		
		
		JLabel lblNewLabel_4 = new JLabel("Statut");
		lblNewLabel_4.setBounds(106, 244, 45, 14);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_4);
		
		RadioButtonConsultable = new JRadioButton("Consultable");
		RadioButtonConsultable.setBounds(251, 240, 110, 22);
		RadioButtonConsultable.setEnabled(false);
		buttonGroup.add(RadioButtonConsultable);
		contentPane.add(RadioButtonConsultable);
		
		RadioButtonEmpruntable = new JRadioButton("Empruntable");
		RadioButtonEmpruntable.setBounds(251, 266, 115, 22);
		RadioButtonEmpruntable.setEnabled(false);
		buttonGroup.add(RadioButtonEmpruntable);
		RadioButtonEmpruntable.setSelected(true);
		contentPane.add(RadioButtonEmpruntable);
		contentPane.add(scrollIsbn);
		getFrame().setVisible(true);
	}
	
	private Ouvrage getOuvrage() { return _ouvrage; }
	public void setOuvrage(Ouvrage ouvrage) { _ouvrage = ouvrage; }
	
	public void alimente(Ouvrage ouv) {
		textFieldTitre.setText(ouv.getTitre());
		textFieldNbExemplaires.setText (String.valueOf(ouv.getNbExemplaires()));
		modeleExemplaires.clear();
		for (int i : ouv.getExemplaires().keySet())
			modeleExemplaires.addElement(ouv.getExemplaire(i).afficheInfos());
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale:
			buttonRech.setEnabled(true);
			break;
		case inter1:
			buttonEnreg.setEnabled(true);
			RadioButtonConsultable.setEnabled(true);
			RadioButtonEmpruntable.setEnabled(true);
			textFieldDateReception.setEditable(true);
			break;
		case finale:
			buttonFermer.setEnabled(true);
			break;
		}
	}

	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getOuvrage());
		super.update(observable,  objet);
	}
}
