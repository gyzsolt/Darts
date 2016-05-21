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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**A játékállapot kimentséhez és betöltéséhez szolgáló osztály.
 * 
 * @author Gyulai Zsolt
 *
 */
public class SaveLoad {
	/**
	 * A naplózáshoz szükséges logger.
	 */
	static private Logger logger = LoggerFactory.getLogger(SaveLoad.class);	
	
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
			logger.error("Nem tudta betölteni a" + file);
			return null;
		} catch (FileNotFoundException e) {
			logger.error("Nem található" + file);
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
			logger.error("Nem tudta kimenteni ajátékállapotot. " + file);
			//System.out.println("Nem tudta kimenteni ajátékállapotot. " + file);
			//System.out.println("Error message is: " + e.getMessage());
			logger.error("Error message is: " + e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error("Nem tallható az állapot." + file);
		} 
	}

}
