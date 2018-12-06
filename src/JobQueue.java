import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//This class represents the Job Queue which loads processes from the "Harddisk"
//Which the text file "cpumemoryio.txt"
public class JobQueue {
	private static final String FILENAME = "src//cpumemoryio.txt";
	private BufferedReader br;
	private FileReader fr;
	private String sCurrentLine;
	private Queue<PCB> JobQueue;

	public JobQueue() {
		BufferedReader br = null;
		FileReader fr = null;
		JobQueue = new Queue<PCB>();

	}
	//This method loops through the file and creates processes and cycles for each process
	private Queue<PCB> loadToJobQueue() throws FileNotFoundException {
		try {

			br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			int pid = 0;
			int cpuBurst = 0;
			int memory = 0;
			int IOBurst = 0;
			int counter = 0;

			String sCurrentLine;
			br.readLine(); // first Line "Name CPU Memory IO " etc..
			// Cycle contains:
			// cpuBurst, memory, IOBurst
			while ((sCurrentLine = br.readLine()) != null ) {

				
				String[] PCBInfo = sCurrentLine.split("	");		
				pid = Integer.parseInt(PCBInfo[0]); // Name of Process
				PCB pcb1 = new PCB(pid);
				cpuBurst = Integer.parseInt(PCBInfo[1]);
				memory = Math.abs(Integer.parseInt(PCBInfo[2])); //First memory should be positive
				IOBurst = Integer.parseInt(PCBInfo[3]);
				pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 1
				cpuBurst = Integer.parseInt(PCBInfo[4]);
				memory = Integer.parseInt(PCBInfo[5]);
				IOBurst = Integer.parseInt(PCBInfo[6]);
				pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 2
				cpuBurst = Integer.parseInt(PCBInfo[7]);
				memory = Integer.parseInt(PCBInfo[8]);
				IOBurst = Integer.parseInt(PCBInfo[9]);
				pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 3
				if (PCBInfo.length == 10) {
					JobQueue.enqueue(pcb1);
					continue;
				}

				cpuBurst = Integer.parseInt(PCBInfo[10]);
				memory = Integer.parseInt(PCBInfo[11]);
				IOBurst = Integer.parseInt(PCBInfo[12]);
				pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 4

				if (PCBInfo.length == 14) { // Has only 5 Cycles
					
					cpuBurst = Integer.parseInt(PCBInfo[13]);
					memory = 0;
					IOBurst = 0;
					pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 5

				}
				if (PCBInfo.length == 17) { // if it has a 6th cycles
					
					cpuBurst = Integer.parseInt(PCBInfo[13]);
					memory = Integer.parseInt(PCBInfo[14]);
					IOBurst = Integer.parseInt(PCBInfo[15]);
					pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 5
					cpuBurst = Integer.parseInt(PCBInfo[16]);
					memory = 0;
					IOBurst = 0;
					pcb1.addCycle(cpuBurst, memory, IOBurst);// Cycle 6

				}

				JobQueue.enqueue(pcb1);
				counter++;
			}

		} catch (

		IOException e) {

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
	//Returns the job queue with loaded processes from the file
	public Queue<PCB> getProcesses() {
		try {
			return this.loadToJobQueue();
			
		} catch (FileNotFoundException e) {

			System.err.println("File not found!!");
		}
		return JobQueue;
	}

}
