
public class CPU {
	private PQ<PCB> readyQueue;
	private Ram ram;
	private int totalCpuTime = 0;
	private int totalIoTime = 0;
	private int time;
	
	
	public void runCpu() {
		readyQueue=ram.loadToReadyQueue();
		time=0;
		while (true) {
		PCB p1 = readyQueue.serve().data;
		p1.setStatus("Running");
		
		Cycle c = p1.getFirstCycle();
			for (int i=0; i<c.getCpuBurst();i++) {
				//Executing the cpu burst
				time++;
			}
			totalCpuTime += c.getCpuBurst();
		p1.increaseCPUSum(c.getCpuBurst());
			//io
		for (int j=0;j<c.getIOBurst();j++) {
			//waiting for io
			p1.setStatus("Waiting");
			
		}
		totalIoTime += c.getIOBurst();
		p1.increaseIOSum(c.getIOBurst());
		
		ram.addToReadyQueue(p1);

			
	if (time%100==0 || readyQueue.length()==0) {
		//you should reactivate job scheduler
		readyQueue=ram.loadToReadyQueue(); //new processes from the Job queue
		
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


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}

	
	
}
