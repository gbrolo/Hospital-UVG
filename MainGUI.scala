import java.awt.BorderLayout
import java.awt.Color
import java.awt.EventQueue
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.PriorityQueue
import java.util.Vector
import javax.swing.ButtonGroup
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.ListSelectionModel
import javax.swing.SwingConstants
import javax.swing.border.EmptyBorder
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JMenu
import MainGUI._





import scala.collection.JavaConversions._

object MainGUI {

  def main(args: Array[String]) {
    EventQueue.invokeLater(new Runnable() {

      def run() {
        try {
          val frame = new MainGUI()
          frame.setVisible(true)
          frame.setResizable(false)
          frame.setLocationRelativeTo(null)
          frame.setTitle("El Hospital de Mancos")
        } catch {
          case e: Exception => e.printStackTrace()
        }
      }
    })
  }
}

@SerialVersionUID(1L)
class MainGUI extends JFrame {

  private var contentPane: JPanel = new JPanel()

  private var listModel: DefaultListModel[String] = new DefaultListModel()

  private var pacientesIn: ArrayList[String] = _

  private var pacienteList: ArrayList[Paciente] = _

  private var qType: String = "JCF"

  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  setBounds(100, 100, 500, 425)

  val menuBar = new JMenuBar()

  setJMenuBar(menuBar)

  val mnInformacion = new JMenu("Informacion")

  menuBar.add(mnInformacion)

  val mntmAyuda = new JMenuItem("Ayuda")

  mntmAyuda.addActionListener(new ActionListener() {
      
      
      

    def actionPerformed(e: ActionEvent) {
      JOptionPane.showMessageDialog(null, "1. Para cargar el archivo de pacientes de click en el boton 'Cargar Fichas'. " + 
        "\n2. Seleccione la implementacion de la PriorityQueue que desea utilizar. Puede elegir entre la del JCF \no una implementación basada en Vector. " + 
        "\n3. Para mostrar el listado de pacientes en orden de prioridad de click en el boton 'Mostrar prioridad de atencion'" + 
        "\n4. Se le proporcionan dos ejemplos de archivos de texto: 'pacientes.txt' y 'pacientes2.txt' \nNote que la forma correcta de agregar pacientes al archivo es mediante la separacion de coma y un espacio.", 
        "Como utilizar el programa.", JOptionPane.INFORMATION_MESSAGE)
    }
  })

  mnInformacion.add(mntmAyuda)

  val mntmAbout = new JMenuItem("About")

  mntmAbout.addActionListener(new ActionListener() {

    def actionPerformed(e: ActionEvent) {
      JOptionPane.showMessageDialog(null, "2016. Gabriel Brolo, Jose Luis Mendez. \nAlgoritmos y Estructuras de datos. UVG.", 
        "Sobre 'El Hospital de Mancos'", JOptionPane.INFORMATION_MESSAGE)
    }
  })

  mnInformacion.add(mntmAbout)

  val mntmGit = new JMenuItem("Git")

  mntmGit.addActionListener(new ActionListener() {

    def actionPerformed(e: ActionEvent) {
      JOptionPane.showMessageDialog(null, "https://github.com/gbrolo/Hospital-UVG.git", "Git", JOptionPane.INFORMATION_MESSAGE)
    }
  })

  mnInformacion.add(mntmGit)

  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5))

  contentPane.setLayout(new BorderLayout(0, 0))

  setContentPane(contentPane)

  val pnlMain = new JPanel()

  pnlMain.setBackground(Color.WHITE)

  contentPane.add(pnlMain, BorderLayout.CENTER)

  pnlMain.setLayout(null)

  val lblTitle = new JLabel("EL HOSPITAL DE MANCOS")

  lblTitle.setHorizontalAlignment(SwingConstants.CENTER)

  lblTitle.setFont(new Font("Dialog", Font.BOLD, 30))

  lblTitle.setBounds(21, 12, 440, 36)

  pnlMain.add(lblTitle)

  val btnCargarFichas = new JButton("Cargar fichas")

  btnCargarFichas.addActionListener(new ActionListener() {

    def actionPerformed(e: ActionEvent) {
      val fc = new JFileChooser()
      fc.setCurrentDirectory(new java.io.File("./src"))
      fc.setDialogTitle("Seleccione su archivo")
      fc.setFileFilter(new FileNameExtensionFilter("Text files (.txt)", "txt"))
      if (fc.showOpenDialog(btnCargarFichas) == JFileChooser.APPROVE_OPTION) {
        try {
          val fstream = new FileInputStream(fc.getSelectedFile.getAbsolutePath)
          val br = new BufferedReader(new InputStreamReader(fstream))
          pacientesIn = new ArrayList[String]()
          pacienteList = new ArrayList[Paciente]()
          var strLine: String = null
          while ((strLine = br.readLine()) != null) {
            pacientesIn.add(strLine)
          }
          for (i <- 0 until pacientesIn.size) {
            val temp = pacientesIn.get(i)
            val partes = temp.split(", ")
            val ptemp = new Paciente(partes(0), partes(1), partes(2))
            pacienteList.add(ptemp)
          }
          JOptionPane.showMessageDialog(null, "Se ha cargado el archivo con las fichas de los pacientes.")
        } catch {
          case j: Exception => j.printStackTrace()
        }
      }
    }
  })

  btnCargarFichas.setBounds(12, 60, 135, 25)

  pnlMain.add(btnCargarFichas)

  val btnMostrarPrioridadDe = new JButton("Mostrar prioridad de atencion")

  btnMostrarPrioridadDe.addActionListener(new ActionListener() {

    def actionPerformed(e: ActionEvent) {
      try {
        listModel.removeAllElements()
        if (qType == "JCF") {
          jcfQueue()
        } else if (qType == "VECTOR") {
          vectorQueue()
        }
      } catch {
        case j: Exception => JOptionPane.showMessageDialog(null, "No ha cargado un archivo .txt")
      }
    }
  })

  btnMostrarPrioridadDe.setBounds(221, 60, 249, 25)

  pnlMain.add(btnMostrarPrioridadDe)

  val listaPacientes = new JList[String](listModel)

  listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)

  listaPacientes.setLayoutOrientation(JList.VERTICAL)

  val pnlShowData = new JScrollPane()

  pnlShowData.setBounds(12, 127, 458, 218)

  pnlShowData.setViewportView(listaPacientes)

  pnlMain.add(pnlShowData)

  val rdbtnJCF = new JRadioButton("JCF Priority Queue")

  rdbtnJCF.setSelected(true)

  rdbtnJCF.addActionListener(new ActionListener() {

    def actionPerformed(arg0: ActionEvent) {
      qType = "JCF"
    }
  })

  rdbtnJCF.setBackground(Color.WHITE)

  rdbtnJCF.setBounds(12, 92, 205, 23)

  pnlMain.add(rdbtnJCF)

  val rdbtnVectorPriorityQueue = new JRadioButton("Vector Priority Queue")

  rdbtnVectorPriorityQueue.addActionListener(new ActionListener() {

    def actionPerformed(e: ActionEvent) {
      qType = "VECTOR"
    }
  })

  rdbtnVectorPriorityQueue.setBackground(Color.WHITE)

  rdbtnVectorPriorityQueue.setBounds(221, 93, 249, 23)

  pnlMain.add(rdbtnVectorPriorityQueue)

  val qGroup = new ButtonGroup()

  qGroup.add(rdbtnJCF)

  qGroup.add(rdbtnVectorPriorityQueue)

  def jcfQueue() {
    val pQueue = new PriorityQueue[Paciente]()
    for (qTemp <- pacienteList) {
      pQueue.add(qTemp)
    }
    while (!pQueue.isEmpty) {
      listModel.addElement(pQueue.remove().toString)
    }
  }

  def vectorQueue() {
    try {
      val pQueue = new VectorHeap[Paciente]()
      for (qTemp <- pacienteList) {
        pQueue.add(qTemp)
      }
      while (!pQueue.isEmpty) {
        listModel.addElement(pQueue.remove().toString)
      }
    } catch {
      case j: Exception => 
    }
  }
}