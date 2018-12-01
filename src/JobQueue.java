import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JobQueue {
private static final String FILENAME ="../cpumemoryio.txt";
private BufferedReader br;
private FileReader fr;
private String sCurrentLine;
private PQ<PCB> JobQueue;
	public JobQueue() {
		BufferedReader br = null;
		FileReader fr = null;
	
	}
public static PQ loadToJobQueue() {
	
	
	try {

		br = new BufferedReader(new FileReader(FILENAME));
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);

		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
		}

	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}

}
}

}
