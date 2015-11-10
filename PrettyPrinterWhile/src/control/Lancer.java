package control;

import java.io.FileNotFoundException;

import Autre.Outil;

/**
 * @author qfdk
 * Lancer.java
 * 2015年11月9日
 */
public class Lancer {
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String tmp=Outil.lire("tmp.while");
		System.out.println(tmp);
		System.out.println("entete===>");
		System.out.println(Outil.nomFunction(tmp));
//		System.out.println(Outil.readVar(tmp));
//		System.out.println(Outil.commands(tmp));
//		System.out.println(Outil.writeVar(tmp));
		System.out.println("Read var==>");
		System.out.println(tmp.split("%")[0].split(":")[1]);
		System.out.println("Commands==>");
		System.out.println(tmp.split("%")[1]);
		System.out.println("Write var==>");
		System.out.println(tmp.split("%")[2]);
	}
	
}
