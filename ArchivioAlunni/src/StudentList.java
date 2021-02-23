import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentList extends ArrayList<Student> implements Serializable{
	public StudentList() {
		super();
	}
	public void save(File f) throws FileNotFoundException, IOException {
		FileWriter fw = new FileWriter(f);
		for(int i=0;i<this.size();i++) {
			fw.write(this.get(i).toCsv()+"\n");
		}
		fw.close();
	}
	public void load(File f) throws FileNotFoundException, IOException {
		this.clear();
		FileReader fr = new FileReader(f);
		BufferedReader br= new BufferedReader(fr);
		String line;
		while((line=br.readLine())!= null) {
			this.add(this.criminalFromCsvLine(line));
		}
	}
	
	  public Student criminalFromCsvLine(String csvLine) {
	 
		String[] csvArray= csvLine.split(",");
		 return new Student(csvArray[0],csvArray[1],csvArray[2],csvArray[3]);
	}
	
}
