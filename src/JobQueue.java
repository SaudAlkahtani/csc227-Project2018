import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JobQueue  {
	private static final String FILENAME = "src//cpumemoryio.txt";
	private BufferedReader br;
	private FileReader fr;
	private String sCurrentLine;
	private PQ<PCB> JobQueue;

	public JobQueue() {
		BufferedReader br = null;
		FileReader fr = null;
		JobQueue= new PQ<PCB>();

	}

	private PQ<PCB> loadToJobQueue() throws FileNotFoundException {
		

		try {

			br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			int pid=0;
			int cpuBurst=0;
			int memory=0;
			int io=0;
			 

			String sCurrentLine;
			br.readLine(); //first Line "Name CPU Memory IO etc..
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				String[] PCBInfo = sCurrentLine.split("	");
				pid= Integer.parseInt(PCBInfo[0]);
				cpuBurst= Integer.parseInt(PCBInfo[1]);
				memory=Integer.parseInt(PCBInfo[2]);
				io=Integer.parseInt(PCBInfo[3]);
				
				PCB process = new PCB(pid,cpuBurst,memory,io);
				JobQueue.enqueueMin(process, 1);
				
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
		return JobQueue;

	}
	
	public PQ<PCB> getProcesses() {
		try {
			return this.loadToJobQueue();
		} catch (FileNotFoundException e) {
			
			System.err.println("File not found!!");
		}
		return JobQueue;
	}

}
