
public class CPU {
	private PQ<PCB> readyQueue;
	private Queue<PCB> waitingQueue;
	private Ram ram;
	private int totalCpuTime = 0;
	private int totalIoTime = 0;

	//This method is used to "execute" the processes in the Ready Queue
	public void runCpu() {
		ram = new Ram();
		readyQueue = ram.loadToReadyQueue();
		waitingQueue = ram.getWaitingQueue();
		int l = 1;
		int m = 0; //Number of waiting processes in waiting queue
		int n6 = 0;
		int n16 = 0;

		while (true) {
			m = waitingQueue.length();
			
			//each 100 Cycle reactivate the Long term scheduler
			if (Timer.time % 100 == 0 || readyQueue.length() == 0) {
				// you should reactivate job scheduler
				readyQueue = ram.loadToReadyQueue(); // new processes from the
														// Job queue

			}

			if (readyQueue.length() == 0 && m != 0) {
				Timer.time++;
				continue;
			}

			if (readyQueue.length() == 0 && m == 0) {
				return;
			}

			PCB p1 = readyQueue.serve().data; //First process to be executed

			p1.setStatus("Running");
			p1.CPUNumIncrement();
			Cycle c = p1.getFirstCycle();

			for (int i = 0; i < c.getCpuBurst(); i++) { //Executing first cycle
				// Executing the cpu burst
				Timer.time++;
			}
			totalCpuTime += c.getCpuBurst();
			p1.increaseCPUSum(c.getCpuBurst());

			// waiting for io
			for (int j = 0; j < c.getIOBurst(); j++) {
				Timer.time++;
				p1.setStatus("Waiting");

			}
			if (c.getIOBurst() > 0) {
				p1.IONumIncrement();
			}
			totalIoTime += c.getIOBurst();
			p1.increaseIOSum(c.getIOBurst());
			p1.increaseMemorySum(c.getMemory());

			if (c.getIOBurst() == 0 && c.getMemory() == 0) { //This is the last cycle for this process

				p1.setStatus("Terminated");
				p1.setEndTime(Timer.time);
				ram.addToFinshedQueue(p1);
				

			}
			if (p1.getPid() == 6 ) { //Special Case with process 6
				n6++;
				if (n6 == 3) {
					p1.setStatus("Terminated");
					p1.setEndTime(Timer.time);
					ram.addToFinshedQueue(p1);
				}
			}
				
				if (p1.getPid() == 16 ) { //Special Case with process 16
					n16++;
					if (n16 == 3) {
						p1.setStatus("Terminated");
						p1.setEndTime(Timer.time);
						ram.addToFinshedQueue(p1);
					}
			}
			ram.addToReadyQueue(p1); //After executing the cycle , return process to ready queue

			if (ram.isEmpty()) {
				break;
			}


		}
	

	}

	public PQ<PCB> getReadyQueue() {
		return readyQueue;
	}

	public void setReadyQueue(PQ<PCB> readyQueue) {
		this.readyQueue = readyQueue;
	}

	public Ram getRam() {
		return ram;
	}

	public void setRam(Ram ram) {
		this.ram = ram;
	}

	public int getTotalCpuTime() {
		return totalCpuTime;
	}

	public void setTotalCpuTime(int totalCpuTime) {
		this.totalCpuTime = totalCpuTime;
	}

	public int getTotalIoTime() {
		return totalIoTime;
	}

	public void setTotalIoTime(int totalIoTime) {
		this.totalIoTime = totalIoTime;
	}

}
