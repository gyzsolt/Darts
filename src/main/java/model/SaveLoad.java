package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**A játékállapot kimentséhez és betöltéséhez szolgáló osztály.
 * 
 * @author Gyulai Zsolt
 *
 */
public class SaveLoad {
	
private File file;
	
	/**Egy {@code String}el megadott fájl elérést alakít át fájl elérési útvonallá.
	 * 
	 * @param fileName a megadott elérési út
	 */
	public SaveLoad(String fileName){
		file=new File(fileName) ;
		
	}
	/**Egy játékállapot {@code XML} állományból való betöltésére szolgál.
	 * 
	 * @return sikeres betöltés esetén visszatér egy {@link JatekAllapot}-val.
	 */
	public JatekAllapot load(){
		try {
			InputStream istream = new FileInputStream(file);
			JAXBContext context = JAXBContext
					.newInstance(JatekAllapot.class);
			Unmarshaller umarsh = context.createUnmarshaller();
			JatekAllapot ret = (JatekAllapot) umarsh.unmarshal(istream);
			return ret;
		} catch (JAXBException e) {
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * Egy megkapott játékállapotot ment ki {@code XML} állományba.
	 * 
	 * @param jatekAllapot a menteni kíván játékállapot.
	 */
	public void save(JatekAllapot jatekAllapot){
		try {
			
				OutputStream ostream = new FileOutputStream(file);


			JAXBContext context = JAXBContext
					.newInstance(JatekAllapot.class);
			Marshaller marsh = context.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marsh.marshal(jatekAllapot, ostream);
		} catch (JAXBException e) {
			//logger.error("Could not save world descriptor to " + f);
			System.out.println("Could not save world descriptor to " + file);
			System.out.println("Error message is: " + e.getMessage());
			//logger.error("Error message is: " + e.getMessage());
		} catch (FileNotFoundException e) {
			//logger.error("Could create world descriptor file " + f);
		} 
	}

}
