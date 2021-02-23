import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentDialog extends JDialog implements ActionListener{
	private String path="base.png";
	private JLabel name,surname,crimes;
	private JTextField nameField,surnameField,classField;
	private JButton save,cancel,setImage;
	private StudentList list;
	private int criminalIndex;
	private Finestra parentFrame;
	private void initComponents() {
		name= new JLabel("Nome");
		surname= new JLabel("Cognome");
		crimes= new JLabel("Classe");
		
		nameField= new JTextField(15);
		surnameField= new JTextField(15);
		classField= new JTextField(15);
		
		setImage= new JButton("Imposta immagine");
		save = new JButton("Salva");
		cancel= new JButton("Annulla");
		
		this.setLayout(new GridLayout(6,3));
		
		this.add(name);
		this.add(surname);
		this.add(nameField);
		this.add(surnameField);
		this.add(crimes);
		
		this.add(classField);
		
		this.add(setImage);
		this.add(save);
		this.add(cancel);
		
		setImage.addActionListener(this);
		cancel.addActionListener(this);
		save.addActionListener(this);
	}
	public StudentDialog(Finestra f, boolean modal,StudentList list,int criminalIndex) {
		super(f, modal);
		parentFrame=f;
		this.list=list;
		initComponents();
		this.criminalIndex=criminalIndex;
		if(criminalIndex!=(-1)) {
			Student temp= list.get(criminalIndex);
			nameField.setText(temp.getName());
			surnameField.setText(temp.getSurname());
			classField.setText(temp.getClasse());
			this.path=temp.getPicturePath();
			if(this.path!=null && this.path!="base.png") {
				this.setImage.setText("Cambia Immagine");
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(setImage)) {
			JFileChooser jfc = new JFileChooser();
			//jfc.setFileFilter();
			//jfc.setAcceptAllFileFilterUsed(false);
			int rv= jfc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION) {
		        File file = jfc.getSelectedFile();
		        path=file.getAbsolutePath();
				this.setImage.setText("Cambia Immagine");
			}
		}
		else if(e.getSource().equals(cancel)) {
			this.dispose();
		}
		else if(e.getSource().equals(save)) {
			if(path==null) path="base.png";
			if(this.criminalIndex!=-1) {
				list.set(this.criminalIndex, new Student(nameField.getText(), surnameField.getText(), classField.getText(), path));
			}
			else {
				list.add(new Student(nameField.getText(), surnameField.getText(), classField.getText(), path));
			}
	        parentFrame.updateTable();
	        parentFrame.getThumbnail().setPath(path);
	        this.dispose();
		}
		
	}
	
}
