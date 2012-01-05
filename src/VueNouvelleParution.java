import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class VueNouvelleParution extends Vue {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnEnregistrer;
	private JButton btnAnnuler;
	
	private JSeparator separator, separator_1;
	private JTextField textFieldIssn;
	private JTextField textFieldTitre;
	private JTextField textFieldId;
	private JTextField textFieldPerio;
	private JTextField textFieldDate;
	private JButton btnRechercher ;
	
	private Periodique _periodique;

	public VueNouvelleParution(Controleur controleur) {
		super (controleur);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Nouvelle Parution");
		frame.setBounds(100, 100, 465, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblISSN = new JLabel("ISSN");
		lblISSN.setBounds(6, 13, 120, 16);
		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblISSN);
		
		separator = new JSeparator();
		separator.setBounds(0, 82, 465, 12);
		frame.getContentPane().add(separator);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(164, 41, 120, 29);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String issn = textFieldIssn.getText();
				if (issn.length() == 0)
				{
					Message dialog = new Message("Vous devez remplir tous les champs");
					dialog.setVisible(true);
				}
				else
				{
					_periodique = getControleur().rechPeriodique(textFieldIssn.getText());
					if (_periodique != null)
						setEtat(Vue.inter1);
				}
			}
		});
		frame.getContentPane().add(btnRechercher);
		
		
		btnAnnuler = new JButton("Fermer");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEtat(Vue.finale);
			}
		});
		btnAnnuler.setBounds(180, 339, 93, 29);
		frame.getContentPane().add(btnAnnuler);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setEnabled(false);
		btnEnregistrer.setBounds(335, 300, 120, 29);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				String titre = textFieldTitre.getText();
				
				if (id.length() == 0 || titre.length() == 0)
				{
					Message dialog = new Message("Vous devez remplir tous les champs");
					dialog.setVisible(true);
				}
				else
				{
					getControleur().nouvelleParution(_periodique, Integer.decode(id), titre);
					textFieldId.setText("");
					textFieldTitre.setText("");
				}
			}
		});
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(138, 7, 317, 28);
		frame.getContentPane().add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(6, 272, 120, 16);
		frame.getContentPane().add(lblTitre);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setColumns(10);
		textFieldTitre.setBounds(138, 266, 317, 28);
		frame.getContentPane().add(textFieldTitre);
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setBounds(6, 305, 120, 16);
		frame.getContentPane().add(lblIdentifiant);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(138, 299, 185, 28);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 214, 465, 12);
		frame.getContentPane().add(separator_1);
		
		JLabel lblParution = new JLabel("Parution");
		lblParution.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblParution.setBounds(195, 238, 61, 16);
		frame.getContentPane().add(lblParution);
		
		JLabel lblPeriodique = new JLabel("Periodique");
		lblPeriodique.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblPeriodique.setBounds(180, 106, 80, 16);
		frame.getContentPane().add(lblPeriodique);
		
		JLabel labelTitrePerio = new JLabel("Titre");
		labelTitrePerio.setBounds(6, 140, 120, 16);
		frame.getContentPane().add(labelTitrePerio);
		
		textFieldPerio = new JTextField();
		textFieldPerio.setEditable(false);
		textFieldPerio.setColumns(10);
		textFieldPerio.setBounds(138, 134, 317, 28);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(138, 174, 317, 28);
		frame.getContentPane().add(textFieldDate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(6, 180, 120, 16);
		
		frame.getContentPane().add(lblDate);
		frame.getContentPane().add(textFieldPerio);
		frame.getContentPane().add(textFieldId);
		frame.getContentPane().add(btnEnregistrer);
		frame.setVisible(true);
	}
	
	public void alimente (Periodique pe)
	{
		textFieldPerio.setText(pe.getNom());
		textFieldDate.setText(ESDate.ecrireDate(pe.getDate()));
	}
	public JFrame getFrame() { return frame; }
	public void setEtat(int etat) {
		switch (etat) {
		case initiale:
			break;
		case inter1:
			textFieldTitre.setEditable(true);
			textFieldId.setEditable(true);
			btnEnregistrer.setEnabled(true);
			break;
		case alternate:
			frame.setVisible(false);
			getControleur().saisiePeriodique();
			break;
		case finale:
			frame.setVisible(false);
			getControleur().fermerVue(this);
			break;
		}
	}
}
