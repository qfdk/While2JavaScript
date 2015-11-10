package Autre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qfdk
 * Outil.java
 * 2015年11月9日
 */
public class Outil {

	/**
	 * @param name nom du fichier
	 * @return une chine de caracter
	 * @throws FileNotFoundException
	 */
	public static String lire(String name) throws FileNotFoundException {
		File file = new File("./donnees/"+name);
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		try {
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String nomFunction(String str)
	{
		  String pattern = "^(function) \\w*:";

	      Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(str);
	      if (m.find( )) {
	    	  return m.group(0);
	      }
	      return null;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String readVar(String str)
	{
		  String pattern = "^(read) \\w*%";

	      Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(str);
	      if (m.find( )) {
	    	  return m.group(0);
	      }
	      return null;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String writeVar(String str)
	{
		  String pattern = "^(write)\\w*";

	      Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(str);
	      if (m.find( )) {
	    	  return m.group(0);
	      }
	      return null;
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String commands(String str)
	{
		  String pattern = "%\\w*%";

	      Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(str);
	      if (m.find( )) {
	    	  return m.group(0);
	      }
	      return null;
	}
	
}
