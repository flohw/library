import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VueConsulterPeriodique extends Vue {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JLabel lblIssn;
	private JLabel lblParutions;
	private JLabel lblNomDuPriodique;
	private JLabel lblDateDabonnement;
	private JLabel lblIssn_1;
	
	private JTextField textFieldIssnRech;
	private JTextField textFieldNom;
	private JTextField textFieldDate;
	private JTextField textFieldIssn;
	
	JScrollPane scrollPane;
	JList listPeriodiques;
	private DefaultListModel modelePeriodiques = new DefaultListModel();
	
	private JButton btnRechercher;
	private JButton btnTerminer;
	
	private JSeparator separator;

	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		frame = new JFrame();
		frame.setTitle("Consulter un Périodique");
		frame.setResizable(false);
		frame.setBounds(100, 100, 497, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		frame.getContentPane().add(scrollPane);
		listPeriodiques.setModel(modelePeriodiques);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(172, 319, 138, 25);
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setEtat(Vue.finale);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblIssn);
		frame.getContentPane().add(lblIssn_1);
		frame.getContentPane().add(lblNomDuPriodique);
		frame.getContentPane().add(lblDateDabonnement);
		frame.getContentPane().add(lblParutions);
		frame.getContentPane().add(textFieldIssnRech);
		frame.getContentPane().add(textFieldNom);
		frame.getContentPane().add(textFieldDate);
		frame.getContentPane().add(textFieldIssn);
		frame.getContentPane().add(btnRechercher);
		frame.getContentPane().add(btnTerminer);
		frame.getContentPane().add(separator);
	}
	
	public void alimente(Periodique per)
	{
		textFieldNom.setText(per.getNom());
		textFieldIssn.setText(per.getIssn());
		textFieldDate.setText(ESDate.ecrireDate(per.getDate()));
		modelePeriodiques.clear();
		for (Integer i : per.getParutions().keySet())
			modelePeriodiques.addElement(per.getParution(i).afficheInfos());
		if (per.getNbParutions() == 0)
		{
			Message dialog = new Message("Aucune parution pour ce périodique");
			dialog.setVisible(true);
		}
	}
	
	public JFrame getFrame() { return frame; }
	public void setEtat(int etat) {
		switch(etat){
		case initiale:
			frame.setVisible(true);
			break;
		case alternate:
			frame.setVisible(false);
			getControleur().saisiePeriodique();
			break;
		case finale:
			frame.setVisible(false);
			getControleur().menuBiblio();
			break;
		}
	}
}
