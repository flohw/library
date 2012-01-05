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


public class VueConsulterPeriodique extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel lblIssn;
	private JLabel lblParutions;
	private JLabel lblNomDuPriodique;
	private JLabel lblDateDabonnement;
	private JLabel lblIssn_1;
	
	private JTextField textFieldIssnRech;
	private JTextField textFieldNom;
	private JTextField textFieldDate;
	private JTextField textFieldIssn;
	
	private JScrollPane scrollPane;
	private JList listPeriodiques;
	private DefaultListModel modelePeriodiques = new DefaultListModel();
	
	private JButton btnRechercher;
	private JButton btnTerminer;
	
	private JSeparator separator;

	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		content = new JPanel();
		setTitle("Consulter un Périodique");
		setBounds(100, 100, 497, 389);
		setContentPane(content);
		content.setLayout(null);
		
		lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(6, 13, 154, 16);
		
		textFieldIssnRech = new JTextField();
		textFieldIssnRech.setBounds(172, 9, 305, 25);
		textFieldIssnRech.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(172, 41, 154, 25);
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
		separator.setBounds(0, 78, 497, 12);
		
		lblParutions = new JLabel("Parutions");
		lblParutions.setBounds(6, 222, 154, 16);
		
		lblNomDuPriodique = new JLabel("Nom du périodique");
		lblNomDuPriodique.setBounds(6, 108, 154, 16);
		
		textFieldNom = new JTextField();
		textFieldNom.setEditable(false);
		textFieldNom.setBounds(172, 104, 305, 25);
		textFieldNom.setColumns(10);
		
		lblDateDabonnement = new JLabel("Date d'abonnement");
		lblDateDabonnement.setBounds(6, 148, 154, 16);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setBounds(172, 144, 305, 25);
		textFieldDate.setColumns(10);
		
		lblIssn_1 = new JLabel("ISSN");
		lblIssn_1.setBounds(6, 188, 154, 16);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setEditable(false);
		textFieldIssn.setBounds(172, 184, 305, 25);
		textFieldIssn.setColumns(10);
		
		listPeriodiques = new JList();
		scrollPane = new JScrollPane(listPeriodiques);
		scrollPane.setBounds(172, 221, 305, 86);
		listPeriodiques.setModel(modelePeriodiques);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(172, 319, 138, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControleur().fermerVue(VueConsulterPeriodique.this);
			}
		});
		
		content.add(scrollPane);
		content.add(lblIssn);
		content.add(lblIssn_1);
		content.add(lblNomDuPriodique);
		content.add(lblDateDabonnement);
		content.add(lblParutions);
		content.add(textFieldIssnRech);
		content.add(textFieldNom);
		content.add(textFieldDate);
		content.add(textFieldIssn);
		content.add(btnRechercher);
		content.add(btnTerminer);
		content.add(separator);
	}
	
	public void alimente(Periodique per)
	{
		textFieldNom.setText(per.getNom());
		textFieldIssn.setText(per.getIssn());
		textFieldDate.setText(ESDate.ecrireDate(per.getDate()));
		modelePeriodiques.clear();
		for (Integer i : per.getParutions().keySet())
			modelePeriodiques.addElement(per.getParution(i).afficheInfos());
		if (per.getNbParutions() == 0) {
			Message dialog = new Message("Aucune parution pour ce périodique");
			dialog.setVisible(true);
		}
	}
}
