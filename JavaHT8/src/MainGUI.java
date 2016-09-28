import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> listModel;
	
	/**
	 * Crea el Frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBackground(Color.WHITE);
		contentPane.add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(null);
		
		JLabel lblTitle = new JLabel("EL HOSPITAL DE MANCOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitle.setBounds(21, 12, 440, 36);
		pnlMain.add(lblTitle);
		
		JButton btnCargarFichas = new JButton("Cargar fichas");
		btnCargarFichas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Abrir archivo */
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("./src"));
				fc.setDialogTitle("Seleccione su archivo");
				fc.setFileFilter(new FileNameExtensionFilter("Text files (.txt)", "txt"));
				if(fc.showOpenDialog(btnCargarFichas) == JFileChooser.APPROVE_OPTION){
					try{
						/*Lee el archivo y obtiene la cadena*/
						FileInputStream fstream = new FileInputStream(fc.getSelectedFile().getAbsolutePath());
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						
						
						
						JOptionPane.showMessageDialog(null, "Se ha cargado el archivo con las fichas de los pacientes.");
						
					} catch(Exception j){
						j.printStackTrace();
					}
				}
			}
		});
		btnCargarFichas.setBounds(12, 60, 135, 25);
		pnlMain.add(btnCargarFichas);
		
		JButton btnMostrarPrioridadDe = new JButton("Mostrar prioridad de atencion");
		btnMostrarPrioridadDe.setBounds(221, 60, 249, 25);
		pnlMain.add(btnMostrarPrioridadDe);
		
		listModel = new DefaultListModel<>();
		JList<String> listaPacientes = new JList<>(listModel);
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPacientes.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane pnlShowData = new JScrollPane();
		pnlShowData.setBounds(12, 97, 458, 248);
		pnlShowData.setViewportView(listaPacientes);
		pnlMain.add(pnlShowData);
	}
	
	/**
	 * Ejecuta la aplicacion.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setTitle("El Hospital de Mancos");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
