import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ejecuta la aplicacion. Crea el programa para gestionar la atencion de la emergencia de un Hospital.
 * Permite cargar un archivo de texto con las fichas de cada paciente, para luego determinar el orden
 * de atencion de los mismos de acuerdo a su situacion.
 * 
 * @author Gabriel Brolo 15105, Jose Luis Mendez 15024
 * @version 1.0.0
 */
public class MainGUI extends JFrame {
	/* Atributos */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private DefaultListModel<String> listModel; // Para el JScrollPanel
	private ArrayList<String> pacientesIn; // Para guardar los datos del archivo de texto
	private ArrayList<Paciente> pacienteList; // Para guardar los pacientes
	
	private String qType; // tipo de PriorityQueue, vector o jcf
	
	/**
	 * Crea el Frame.
	 */
	public MainGUI() {
		
		qType = "JCF"; // jcf queue primera opcion
		
		/* JFrame */
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
		
		/* -------------------- CARGAR DATOS DEL ARCHIVO DE TEXTO -------------------- */
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
						
						pacientesIn = new ArrayList<String>(); // guardar lineas
						pacienteList = new ArrayList<Paciente>(); // guardar pacientes
						
						String strLine;
						while((strLine = br.readLine()) != null){
							pacientesIn.add(strLine); //guarda las lineas del archivo en arraylist
						}
						
						for(int i = 0; i < pacientesIn.size(); i++){
							String temp = pacientesIn.get(i);
							String[] partes = temp.split(", "); //separa los datos de cada persona
							
							Paciente ptemp = new Paciente(partes[0], partes[1], partes[2]); //crea al paciente
							pacienteList.add(ptemp);
						}
						
						JOptionPane.showMessageDialog(null, "Se ha cargado el archivo con las fichas de los pacientes.");
						
					} catch(Exception j){
						j.printStackTrace();
					}
				}
			}
		});
		btnCargarFichas.setBounds(12, 60, 135, 25);
		pnlMain.add(btnCargarFichas);
		
		/* ------ UTILIZAR PRIORITY QUEUE PARA MOSTRAR DATOS EN PANTALLA SEGUN PRIORIDAD ------*/
		JButton btnMostrarPrioridadDe = new JButton("Mostrar prioridad de atencion");
		btnMostrarPrioridadDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					listModel.removeAllElements();
					if(qType.equals("JCF")){
						jcfQueue(); // utilizar priority queue del jcf
					} else if(qType.equals("VECTOR")){
						vectorQueue(); // utilizar implementaion del priority queue con vector
					}
				} catch (Exception j){
					JOptionPane.showMessageDialog(null, "No ha cargado un archivo .txt");
				}
				
			}
		});
		btnMostrarPrioridadDe.setBounds(221, 60, 249, 25);
		pnlMain.add(btnMostrarPrioridadDe);
		
		/* LIST MODEL */
		listModel = new DefaultListModel<>();
		JList<String> listaPacientes = new JList<>(listModel);
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPacientes.setLayoutOrientation(JList.VERTICAL);
		
        /* PANEL PARA MOSTRAR INFO */
		JScrollPane pnlShowData = new JScrollPane();
		pnlShowData.setBounds(12, 127, 458, 218);
		pnlShowData.setViewportView(listaPacientes);
		pnlMain.add(pnlShowData);
		
		/* RADIO BUTTONS para seleccionar priority queue */
		JRadioButton rdbtnJCF = new JRadioButton("JCF Priority Queue");
		rdbtnJCF.setSelected(true);
		rdbtnJCF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				qType = "JCF";
			}
		});
		rdbtnJCF.setBackground(Color.WHITE);
		rdbtnJCF.setBounds(12, 92, 205, 23);
		pnlMain.add(rdbtnJCF);
		
		JRadioButton rdbtnVectorPriorityQueue = new JRadioButton("Vector Priority Queue");
		rdbtnVectorPriorityQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qType = "VECTOR";
			}
		});
		rdbtnVectorPriorityQueue.setBackground(Color.WHITE);
		rdbtnVectorPriorityQueue.setBounds(221, 93, 249, 23);
		pnlMain.add(rdbtnVectorPriorityQueue);
		
		/* Button Group */
		ButtonGroup qGroup = new ButtonGroup();
		qGroup.add(rdbtnJCF);
		qGroup.add(rdbtnVectorPriorityQueue);
	}
	
	/**
	 * Utiliza la implementacion del PriorityQueue del JCF para mostrar en pantalla la lista de pacientes segun
	 * la prioridad.
	 */
	public void jcfQueue(){
		PriorityQueue<Paciente> pQueue = new PriorityQueue<Paciente>();
		
		for(Paciente qTemp : pacienteList){
			pQueue.add(qTemp);
		}
		
		while(!pQueue.isEmpty()){
			listModel.addElement(pQueue.remove().toString());
		}
	}
	
	/**
	 * Utiliza la implementacion del PriorityQueue: VectorHeap para mostrar en pantalla la lista de pacientes segun
	 * la prioridad.
	 */
	public void vectorQueue(){
		try{
			VectorHeap<Paciente> pQueue = new VectorHeap<Paciente>();
			
			for(Paciente qTemp : pacienteList){
				pQueue.add(qTemp);
			}
			
			while(!pQueue.isEmpty()){
				listModel.addElement(pQueue.remove().toString());
			}
		}catch (Exception j){
			
		}
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
