
public class LongTermScheduler {
	public final static int ramSize = 192;
	// private JobQueue jobQueue ;
	private int availableSize;
	public PQ<PCB> readyQueue;
	public PQ<PCB> waitingQueue;
	public PQ<PCB> jobQueue;
	public JobQueue obj ;

	public LongTermScheduler() {
		this.jobQueue = obj.getProcesses();
		this.availableSize = 144;
		this.readyQueue = new PQ<PCB>();
		this.waitingQueue = new PQ<PCB>();
	}
	

	public PQ loadToReadyQueue() {
		PQ<PCB> tempQ = new PQ<PCB>();
		// check if both Queue empty
		if (jobQueue.length() <= 0 && waitingQueue.length() <= 0) {
			return this.readyQueue;
		}
		// Check Waiting queue first
		while (waitingQueue.length() > 0) {

			PCB process = waitingQueue.serve().data;

			if (process.getMemory() <= availableSize) {
				availableSize = availableSize - process.getMemory();
				readyQueue.enqueueMin(process, process.getCpuBurst());
			} else {
				tempQ.enqueueMin(process, 1); // to cycle through waiting queue
			}

		}
		// return waiting Queue
		while (tempQ.length() != 0)
			waitingQueue.enqueueMin(tempQ.serve().data, 1);
		PCB process = jobQueue.serve().data;

		while (jobQueue.length() != 0 && availableSize != 0) {  // if there is enough   
			if (process.getMemory() <= availableSize) {
				readyQueue.enqueueMin(process, process.getCpuBurst());
				availableSize = availableSize - process.getMemory();
			}else{
				waitingQueue.enqueueMin(process, 1);
			}
			
			process = jobQueue.serve().data;

		}
		if (availableSize == 0)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
