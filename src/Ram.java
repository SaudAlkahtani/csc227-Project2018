
public class Ram {
	public final static int ramSize = 192;
	// private JobQueue jobQueue ;
	public static int time;
	private int availableSize;
	public Queue<PCB> getJobQueue() {
		return jobQueue;
	}

	public void setJobQueue(Queue<PCB> jobQueue) {
		this.jobQueue = jobQueue;
	}

	private PQ<PCB> readyQueue;
	private Queue<PCB> waitingQueue;
	public PQ<PCB> getReadyQueue() {
		return readyQueue;
	}

	public void setReadyQueue(PQ<PCB> readyQueue) {
		this.readyQueue = readyQueue;
	}

	public Queue<PCB> getWaitingQueue() {
		return waitingQueue;
	}

	public void setWaitingQueue(Queue<PCB> waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	private Queue<PCB> jobQueue;
	private Queue<PCB> finshedProcesses;
	private JobQueue obj; // object to get the queue using getProcess method .

	public int getAvailableSize() {
		return availableSize;
	}

	public void setAvailableSize(int availableSize) {
		this.availableSize = availableSize;
	}

	public Ram() {
		obj = new JobQueue();
		this.jobQueue = obj.getProcesses();
		this.availableSize = 144;
		this.readyQueue = new PQ<PCB>();
		this.waitingQueue = new Queue<PCB>();
		this.finshedProcesses = new Queue<PCB>();

	}

	public void deleteMaxMemoryProcess(Queue<PCB> waitingQueue) {
		System.out.println("delete");
		Queue<PCB> temp = new Queue<PCB>();
		if (waitingQueue.length() == 0) {
			return;
		}
		PCB Max = waitingQueue.serve();
		PCB deleted = null;
		temp.enqueue(Max);
		int id = Max.getPid();

		while (waitingQueue.length() != 0) {// to find the max memory process in
											// the waiting queue
			if (waitingQueue.peek().getFirstMemory() > Max.getFirstMemory()) {
				Max = waitingQueue.serve();
				id = Max.getPid(); // save the id of the max memory process
				temp.enqueue(Max);
			} else {
				temp.enqueue(waitingQueue.serve());
			}
			++Timer.time;
		}

		while (temp.length() != 0) {
			if (temp.peek().getPid() == id) { // if you find the max memory
												// process don't add it to the
												// waiting Queue
				deleted = temp.serve();
			} else {
				this.waitingQueue.enqueue(temp.serve());
			}
			++Timer.time;
		}
		this.availableSize+=deleted.getMemorySum();
		deleted.setStatus("Killed");
		
		finshedProcesses.enqueue(deleted);

	}

	public PQ<PCB> loadToReadyQueue() {
		boolean flag = false;
		System.out.println("load to ready");
		Queue<PCB> temp = new Queue<PCB>();
		// check if both Queue empty
		if (jobQueue.length() <= 0 && waitingQueue.length() <= 0) {
			return this.readyQueue;
		}
		// Check Waiting queue first
		int i = waitingQueue.length();
		while (waitingQueue.length() > 0) {
			
			++Timer.time;

			PCB process = waitingQueue.serve();

			if (process.getFirstMemory() <= availableSize) {

				availableSize = availableSize - process.getFirstMemory();
				process.setStatus("Ready");
				if(process.getReadyQueueTime()==0) {
					System.out.println("First time in ready");
					process.setReadyQueueTime(Timer.time);
					}
				readyQueue.enqueue(process, process.getFirstCPU());
			} else {
				temp.enqueue(process);
			}
			flag = true;

		}
		if (flag) {
			if (i == temp.length()) { // a Deadlock happened
				deleteMaxMemoryProcess(temp);
			} else {
				while (temp.length() != 0)
					
					waitingQueue.enqueue(temp.serve());
				++Timer.time;
			}
		}

		while (jobQueue.length() != 0 && availableSize != 0) {

			PCB process = jobQueue.serve();

			if (process.getFirstMemory() <= availableSize) {

				availableSize = availableSize - process.getFirstMemory();
				if(process.getReadyQueueTime()==0) {
					System.out.println("Second if in ready");
				process.setReadyQueueTime(Timer.time);
				}
				process.setStatus("Ready");
				readyQueue.enqueue(process, process.getFirstCPU());

			} else {
				process.setStatus("Waiting");
				process.waitNumIncrement();
				waitingQueue.enqueue(process);
			}
			++Timer.time;
		}

		return this.readyQueue;
	}

	public boolean isEmpty() {

		if (jobQueue.length() == 0 && waitingQueue.length() == 0 && readyQueue.length() == 0) {
			return true;
		}

		return false;
	}

	public void addToReadyQueue(PCB process) {
		System.out.println("add to ready");

		if(process.getCycles().length()==0) {
			return;
		}
		
			if (process.getFirstMemory() <= this.availableSize) {

				this.availableSize = this.availableSize - (process.getFirstMemory());
				process.setStatus("Ready");
				if(process.getReadyQueueTime()==0) {
					System.out.println("WTF");
					process.setReadyQueueTime(Timer.time);
					}
				readyQueue.enqueue(process, process.getFirstCPU());
				System.err.println("i added something:");

			} else {
				process.setStatus("Waiting");
				process.waitNumIncrement();
				waitingQueue.enqueue(process);
			}

		

	}

	public void addToFinshedQueue(PCB process) {
		this.availableSize+= process.getMemorySum();
		this.finshedProcesses.enqueue(process);
	}

	public Queue<PCB> getFinshedProcesses() {
		return finshedProcesses;
	}

	

}
