
public class Ram {
	public final static int ramSize = 192;
	// private JobQueue jobQueue ;
	private int availableSize;
	private PQ<PCB> readyQueue;
	private Queue<PCB> waitingQueue;
	private Queue<PCB> IOQueue;
	private Queue<PCB> jobQueue;
	private JobQueue obj; // object to get the queue using getProcess method .

	public Ram() {
		this.jobQueue = obj.getProcesses();
		this.availableSize = 144;
		this.readyQueue = new PQ<PCB>();
		this.waitingQueue = new Queue<PCB>();
		this.IOQueue = new Queue<PCB>();
	}

	public void deleteMaxMemoryProcess() {
		
	}

	public PQ<PCB> loadToReadyQueue() {
		Queue<PCB> temp = new Queue<PCB>();
		// check if both Queue empty
		if (jobQueue.length() <= 0 && waitingQueue.length() <= 0) {
			return this.readyQueue;
		}
		// Check Waiting queue first
		int i = waitingQueue.length();
		while (waitingQueue.length() > 0) {

			PCB process = waitingQueue.serve();

			if (process.getFirstMemory() <= availableSize) {

				availableSize = availableSize - process.getFirstMemory();
				process.setStatus("Ready");
				readyQueue.enqueue(process, process.getFirstCPU());
			} else {
				temp.enqueue(process);
			}
			
		}

		if (i == temp.length()) { // a Deadlock happened

		} else {
			while (temp.length() != 0)
				waitingQueue.enqueue(temp.serve());
		}

		while (jobQueue.length() != 0 && availableSize != 0) { // if the memory
																// is not enough

			PCB process = jobQueue.serve();

			if (process.getFirstMemory() <= availableSize) {

				availableSize = availableSize - process.getFirstMemory();
				process.setStatus("Ready");
				readyQueue.enqueue(process, process.getFirstCPU());

			} else {
				process.setStatus("Waiting");
				waitingQueue.enqueue(process);
			}

		}

		return this.readyQueue;
	}

}
