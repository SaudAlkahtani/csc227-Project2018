
public class CPU {
	private PQ<PCB> readyQueue;
	private Queue<PCB> waitingQueue;
	private Ram ram;
	private int totalCpuTime = 0;
	private int totalIoTime = 0;

	public void runCpu() {
		ram = new Ram();
		readyQueue = ram.loadToReadyQueue();
		waitingQueue = ram.getWaitingQueue();
		int l = 1;
		int m = 0;
		int n6 = 0;
		int n16 = 0;

		while (true) {
			m = waitingQueue.length();
			System.out.println("Counter in cpu: " + l++);
			// System.out.println("JOBQUEUE" + ram.getJobQueue().length());
			System.out.println("rQ Length: " + readyQueue.length());
			System.out.println("Wait q" + ram.getWaitingQueue().length());
			System.out.println("JobQueue: " + ram.getJobQueue().length());

			if (Timer.time % 100 == 0 || readyQueue.length() == 0) {
				System.out.println(readyQueue.length());

				System.out.println(Timer.time);
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

			PCB p1 = readyQueue.serve().data;

			p1.setStatus("Running");
			p1.CPUNumIncrement();
			Cycle c = p1.getFirstCycle();

			for (int i = 0; i < c.getCpuBurst(); i++) {
				// Executing the cpu burst
				Timer.time++;
			}
			totalCpuTime += c.getCpuBurst();
			p1.increaseCPUSum(c.getCpuBurst());

			// io
			for (int j = 0; j < c.getIOBurst(); j++) {
				// waiting for io

				Timer.time++;
				p1.setStatus("Waiting");

			}
			if (c.getIOBurst() > 0) {
				p1.IONumIncrement();
			}
			totalIoTime += c.getIOBurst();
			p1.increaseIOSum(c.getIOBurst());
			p1.increaseMemorySum(c.getMemory());

			if (c.getIOBurst() == 0 && c.getMemory() == 0) { //

				p1.setStatus("Terminated");
				p1.setEndTime(Timer.time);
				ram.addToFinshedQueue(p1);
				System.err.println(ram.getFinshedProcesses().length());

			}
			if (p1.getPid() == 6 ) {
				n6++;
				if (n6 == 3) {
					p1.setStatus("Terminated");
					p1.setEndTime(Timer.time);
					ram.addToFinshedQueue(p1);
				}
			}
				
				if (p1.getPid() == 16 ) {
					n16++;
					if (n16 == 3) {
						p1.setStatus("Terminated");
						p1.setEndTime(Timer.time);
						ram.addToFinshedQueue(p1);
					}
			}
			ram.addToReadyQueue(p1);

			if (ram.isEmpty()) {
				break;
			}

			// System.out.println("Ready Queue " + readyQueue.length());
			// readyQueue = ram.loadToReadyQueue();
			// System.out.println("size: " + ram.getAvailableSize());
			// System.err.println(ram.getFinshedProcesses().length());
			// System.out.println("rQ Length: " + readyQueue.length());
			// System.out.println("Wait q" + ram.getWaitingQueue().length());
			// System.out.println("JobQueue: " + ram.getJobQueue().length());
			//

		}
		//		System.err.println(ram.getFinshedProcesses().length());
		// System.out.println("rQ Length: " + readyQueue.length());
		// System.out.println("Wait q" + ram.getWaitingQueue().length());
		// System.out.println(" Hiiiiiiiii " +
		// ram.getFinshedProcesses().serve().getIONum());
		// ram.getFinshedProcesses().serve().printall();
		// ram.getFinshedProcesses().serve().printall();
		//
		// System.out.println("JobQueue: " + ram.getJobQueue().length());

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
