
public class LongTermScheduler {
	public final static int ramSize = 192;
	// private JobQueue jobQueue ;
	private int availableSize;
	private PQ<PCB> readyQueue;
	private Queue<PCB> waitingQueue;
	private Queue<PCB> IOQueue;
	private Queue<PCB> jobQueue;
	private JobQueue obj;

	public LongTermScheduler() {
		this.jobQueue = obj.getProcesses();
		this.availableSize = 144;
		this.readyQueue = new PQ<PCB>();
		this.waitingQueue = new Queue<PCB>();
		this.IOQueue = new Queue<PCB>();
	}

	public PQ<PCB> loadToReadyQueue() {
		
		if (availableSize == 0){
			sleep();
		}

		// check if both Queue empty
		if (jobQueue.length() <= 0 && waitingQueue.length() <= 0) {
			return this.readyQueue;
		}
		// Check Waiting queue first
		while (waitingQueue.length() > 0) {

			PCB process = waitingQueue.peek();

			if (process.getFirstMemory() <= availableSize) {

				PCB waitingProcess = waitingQueue.serve();
				availableSize = availableSize - waitingProcess.getFirstMemory();
				waitingProcess.setStatus("Ready");
				readyQueue.enqueue(waitingProcess, waitingProcess.getFirstCPU());
			}

		}
		
		// Check Waiting queue first
				while (waitingQueue.length() > 0) {

					PCB process = waitingQueue.peek();

					if (process.getFirstMemory() <= availableSize) {

						PCB waitingProcess = waitingQueue.serve();
						availableSize = availableSize - waitingProcess.getFirstMemory();
						waitingProcess.setStatus("Ready");
						readyQueue.enqueue(waitingProcess, waitingProcess.getFirstCPU());
					}

				}

		while (jobQueue.length() != 0 && availableSize != 0) {
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

	public void sleep() {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	
	
	
}
