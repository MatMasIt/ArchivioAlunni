import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Finestra extends JFrame implements ActionListener{
	private JMenuBar bar;
	private JMenu file,data;
	private JMenuItem save,load,clear,add,edit,delete;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultTableModel;
	private JThumbnail thumbnail;
	private StudentList list;
	private JPanel thumbnailPanel,tablePanel;
	private JComboBox selector;
	private JSplitPane splitPane;
	private void initComponents() {
		bar= new JMenuBar();
		file= new JMenu("File");
		data= new JMenu("Dati");
		save= new JMenuItem("Salva");
		load= new JMenuItem("Carica");
		clear= new JMenuItem("Elimina tutto");
		add= new JMenuItem("Aggiungi");
		edit= new JMenuItem("Modifica");
		delete= new JMenuItem("Elimina");
		
		Object[][] tableData = {};
		String[] columnNames = {"Nome","Cognome","Classe" };
		defaultTableModel = new DefaultTableModel(tableData, columnNames);
		table= new JTable(defaultTableModel);
		scrollPane= new JScrollPane(table);
	
		thumbnail= new JThumbnail("base.png","Seleziona un record",400,400);
		
		file.add(save);
		file.add(load);
		
		data.add(clear);
		data.add(add);
		data.add(edit);
		data.add(delete);
		
		bar.add(file);
		bar.add(data);
		
		add.addActionListener(this);
		edit.addActionListener(this);
		
		thumbnailPanel = new JPanel();
		
		this.setJMenuBar(bar);
		
	
		String[] options = { "Tutti","1A", "1B", "2A", "2B", "3A" };

		selector = new JComboBox(options);
		
		thumbnailPanel.setLayout(new GridLayout(2,1));
		
		thumbnailPanel.add(thumbnail);
		
		thumbnailPanel.add(selector);
		
		tablePanel  = new JPanel();
		
		tablePanel.add(scrollPane);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				thumbnailPanel, tablePanel);
		
		this.add(splitPane);
		
		delete.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		
		table.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if(row!=-1)  getThumbnail().setPath(list.get(row).getPicturePath());

            }
        });
		
		
		
		selector.addActionListener(this);

	}
	private void studentDialog(int i) {
		StudentDialog td = new StudentDialog(this, true, this.list,i);
		td.setSize(800, 600);
		td.setVisible(true);
	}
	public Finestra() {
		this.list= new StudentList();
		initComponents();
		
	}
	public static void main(String[] args) {
		Finestra f= new Finestra();
		f.setSize(1000,900);
		f.setVisible(true);
		f.setTitle("ArchivioAlunni");
	}
	public void updateTable() {
		defaultTableModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			Object[] row = {list.get(i).getName(),list.get(i).getSurname(),list.get(i).getClasse()};
			defaultTableModel.addRow(row);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(add)) {
			this.studentDialog(-1);
		}
		else if(e.getSource().equals(clear)) {
			this.list.clear();
			this.updateTable();
			this.thumbnail.setPath("base.png");
		}
		else if(e.getSource().equals(edit)) {
			this.studentDialog(table.getSelectedRow());
		}
		else if(e.getSource().equals(load)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        try {
					list.load(file);
				} catch ( IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        this.updateTable();
				this.thumbnail.setPath("base.png");
			}
		}
		else if(e.getSource().equals(save)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showSaveDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        try {
					list.save(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        this.updateTable();
				this.thumbnail.setPath("base.png");
			}
		}
		else if(e.getSource().equals(delete)) {
			int i = table.getSelectedRow();
			if(i!=-1) {
				this.list.remove(i);
				this.updateTable();
				this.thumbnail.setPath("base.png");
			}
		}
		else if(e.getSource().equals(selector)) {
				this.showFiltered((String)selector.getSelectedItem());
		}
		
	}
	
	private void showFiltered(String classe) {
		defaultTableModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getClasse().equals(classe) || classe.equals("Tutti")) {
				Object[] row = {list.get(i).getName(),list.get(i).getSurname(),list.get(i).getClasse()};
				defaultTableModel.addRow(row);
			}
			this.thumbnail.setPath("base.png");
		}
	}
	public JThumbnail getThumbnail() {
		return thumbnail;
	}
}
