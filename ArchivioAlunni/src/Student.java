import java.io.Serializable;
import java.util.GregorianCalendar;

public class Student implements Serializable{
	private String name,surname,classe,picturePath;
	public Student(String name,String surname,String classe,String picturePath){
		this.name=name;
		this.setSurname(surname);
		this.classe=classe;
		this.picturePath=picturePath;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String toCsv() { 
		return name+","+surname+","+classe+","+picturePath;
	}
}