
public class CPU {
	private PQ<PCB> readyQueue;
	private Ram ram;
	private int totalCpuTime = 0;
	private int totalIoTime = 0;

	public void runCpu() {
		ram = new Ram();
		readyQueue = ram.loadToReadyQueue();
		System.out.println(readyQueue.length());
int l=1;
		while (true) {
			System.out.println("Counter in cpu: " + l++);
			//System.out.println("JOBQUEUE" + ram.getJobQueue().length());
			System.err.println(ram.getFinshedProcesses().length());
			System.out.println("rQ Length: " + readyQueue.length());
			System.out.println("Wait q" + ram.getWaitingQueue().length());
			System.out.println("JobQueue: " + ram.getJobQueue().length());
			
			if (readyQueue.length() == 0) {
				return;
			}
			PCB p1 = readyQueue.serve().data;

			p1.setStatus("Running");
			p1.CPUNumIncrement();
			Cycle c = p1.getFirstCycle();
			System.out.println("ID ------------------" + p1.getPid());
			System.out.println(" CPU BURST FOR CYCLE: "+ c.getCpuBurst());
			System.out.println(" Memory : " + c.getMemory());
			System.out.println("IO : " + c.getIOBurst());
			
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
			System.out.println("before add something");
			
			if (c.getIOBurst() == 0 && c.getMemory() == 0) { //
				System.out.println("did i add something?");
				
				p1.setStatus("Temrinated");
				ram.addToFinshedQueue(p1);
				System.err.println(ram.getFinshedProcesses().length());

			}
			ram.addToReadyQueue(p1);
			if (ram.isEmpty()) {
				System.out.println(ram.isEmpty() + " i was here ");
				break;
			}
			
			//System.out.println("Ready Queue " + readyQueue.length());
			//readyQueue = ram.loadToReadyQueue();
		//	System.out.println("size: " + ram.getAvailableSize());
//			System.err.println(ram.getFinshedProcesses().length());
//			System.out.println("rQ Length: " + readyQueue.length());
//			System.out.println("Wait q" + ram.getWaitingQueue().length());
//			System.out.println("JobQueue: " + ram.getJobQueue().length());
//			

			if (Timer.time % 100 == 0 || readyQueue.length() == 0) {
				System.out.println(readyQueue.length());

				System.out.println(Timer.time);
				// you should reactivate job scheduler
				readyQueue = ram.loadToReadyQueue(); // new processes from the Job queue

			}
		}
	System.err.println(ram.getFinshedProcesses().length());
//		System.out.println("rQ Length: " + readyQueue.length());
//		System.out.println("Wait q" + ram.getWaitingQueue().length());
//		System.out.println(" Hiiiiiiiii " + ram.getFinshedProcesses().serve().getIONum());
//		ram.getFinshedProcesses().serve().printall();
//		ram.getFinshedProcesses().serve().printall();
//		
//		System.out.println("JobQueue: " + ram.getJobQueue().length());
		
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
