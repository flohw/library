import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.SwingConstants;
import java.awt.Font;


public class VueConsulterPeriodique extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel lblIssn;
	private JLabel lblParutions;
	private JLabel lblNomDuPriodique;
	private JLabel lblDateDabonnement;
	
	private JTextField textFieldIssnRech;
	private JTextField textFieldNom;
	private JTextField textFieldDate;
	
	private JScrollPane scrollPane;
	private JList listPeriodiques;
	private DefaultListModel modelePeriodiques = new DefaultListModel();
	
	private JButton btnRechercher;
	private JButton btnTerminer;
	
	private JSeparator separator;
	
	private Periodique _periodique;

	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		getFrame().setTitle("Consulter un Périodique");
		getFrame().setBounds(100, 100, 533, 400);
		getFrame().setContentPane(content);
		
		lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(23, 23, 28, 16);
		
		textFieldIssnRech = new JTextField();
		textFieldIssnRech.setBounds(219, 17, 166, 28);
		textFieldIssnRech.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(397, 18, 113, 29);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String issn = textFieldIssnRech.getText();
				if (issn.length() == 0)
				{
					Message dialog = new Message("Vous devez rensigner le numero ISSN");
					dialog.setVisible(true);
				}
				else
					getControleur().rechPeriodique(issn);
			}
		});
		
		separator = new JSeparator();
		separator.setBounds(397, 184, 0, 12);
		
		lblParutions = new JLabel("Parutions");
		lblParutions.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblParutions.setHorizontalAlignment(SwingConstants.CENTER);
		lblParutions.setBounds(23, 164, 487, 16);
		
		lblNomDuPriodique = new JLabel("Nom du périodique");
		lblNomDuPriodique.setBounds(23, 90, 121, 16);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(219, 84, 291, 28);
		textFieldNom.setEditable(false);
		textFieldNom.setColumns(10);
		
		lblDateDabonnement = new JLabel("Date d'abonnement");
		lblDateDabonnement.setBounds(23, 130, 122, 16);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(219, 124, 291, 28);
		textFieldDate.setEditable(false);
		textFieldDate.setColumns(10);
		
		listPeriodiques = new JList();
		scrollPane = new JScrollPane(listPeriodiques);
		scrollPane.setBounds(23, 192, 487, 132);
		listPeriodiques.setModel(modelePeriodiques);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(216, 336, 100, 29);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueConsulterPeriodique.this);
				getControleur().menuBiblio();
			}
		});
		content.setLayout(null);
		
		content.add(scrollPane);
		content.add(lblIssn);
		content.add(lblNomDuPriodique);
		content.add(lblDateDabonnement);
		content.add(lblParutions);
		content.add(textFieldIssnRech);
		content.add(textFieldNom);
		content.add(textFieldDate);
		content.add(btnRechercher);
		content.add(btnTerminer);
		content.add(separator);
		
		getFrame().setVisible(true);
	}
	
	public void setPeriodique(Periodique pe) { _periodique = pe; }
	public Periodique getPeriodique() { return _periodique; }
	
	public void alimente(Periodique per)
	{
		textFieldNom.setText(per.getNom());
		textFieldDate.setText(ESDate.ecrireDate(per.getDate()));
		modelePeriodiques.clear();
		for (Integer i : per.getParutions().keySet())
			modelePeriodiques.addElement(per.getParution(i).afficheInfos());
	}
	
	public void update(Observable observable, Object objet) {
		// maj de la vue lorque l'ouvrage a été modifié
		this.alimente(this.getPeriodique());
		super.update(observable,  objet);
	}
}
