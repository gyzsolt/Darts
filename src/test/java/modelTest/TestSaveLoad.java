package modelTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import model.Dobas;
import model.JatekAllapot;
import model.Jatekos;
import model.SaveLoad;

public class TestSaveLoad {
	@ClassRule
	public static TemporaryFolder folder = new TemporaryFolder();

	private static JatekAllapot jatekAllapot;

	private static File resourceFile;

	@BeforeClass
	public static void beforeClass() {
		jatekAllapot = new JatekAllapot();

		Jatekos Kati = new Jatekos("Kati");
		Jatekos Sanyi = new Jatekos("Sanyi");

		Kati.addDobasok(new Dobas(20, "T"));
		Kati.addDobasok(new Dobas(20, "T"));
		Kati.addDobasok(new Dobas(20, "S"));

		Sanyi.addDobasok(new Dobas(6, "S"));
		Sanyi.addDobasok(new Dobas(17, "D"));
		Sanyi.addDobasok(new Dobas(19, "S"));

		String jatekTipus = "301";
		int jatekPont = 301;
		int kovetkezoJatekos = 0;
		int dobas = 0;

		List<Jatekos> jatekosok = new ArrayList<>();
		jatekosok.add(Kati);
		jatekosok.add(Sanyi);

		jatekAllapot.setDobas(dobas);
		jatekAllapot.setJatekosok(jatekosok);
		jatekAllapot.setJatekPont(jatekPont);
		jatekAllapot.setJatekTipus(jatekTipus);
		jatekAllapot.setKovetkezoJatekos(kovetkezoJatekos);

		try {
			resourceFile = folder.newFile();
			Class cl = TestSaveLoad.class;
			File resource = new File(cl.getResource("/TestSaveLoad/darts.xml").getFile());
			FileChannel src = new FileInputStream(resource).getChannel();
			FileChannel dst = new FileOutputStream(resourceFile).getChannel();
			dst.transferFrom(src, 0, src.size());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Intit fail");
		}
	}

	@Test
	public void TestSave() {
		try {
			File savedFile = folder.newFile();
			SaveLoad save = new SaveLoad(savedFile.getAbsolutePath());
			save.save(jatekAllapot);

			FileInputStream savedIStream = new FileInputStream(savedFile);
			BufferedReader savedReader = new BufferedReader(new InputStreamReader(savedIStream));
			StringBuilder savedSb = new StringBuilder();
			String thisLine;
			while ((thisLine = savedReader.readLine()) != null) {
				savedSb.append(thisLine);
			}

			FileInputStream resourceIStream = new FileInputStream(resourceFile);
			BufferedReader resourceReader = new BufferedReader(new InputStreamReader(resourceIStream));
			StringBuilder resourceSb = new StringBuilder();
			thisLine = "";
			while ((thisLine = resourceReader.readLine()) != null) {
				resourceSb.append(thisLine);
			}

			assertEquals(resourceSb.toString(), savedSb.toString());

		} catch (IOException e) {
			e.printStackTrace();
			fail("testSave fail");
		}

	}

	@Test
	public void TestLoad() {
		SaveLoad load = new SaveLoad(resourceFile.getAbsolutePath());

		assertEquals(load.load(), jatekAllapot);
	}

}
