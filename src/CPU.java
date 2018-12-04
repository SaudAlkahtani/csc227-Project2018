
public class CPU {
	private PQ<PCB> readyQueue;
	private IO io;
	private Ram ram;
	
	
	public void runCpu() {
		readyQueue=ram.loadToReadyQueue();
		while (true) {
		PCB p1 = readyQueue.serve().data;
		p1.setStatus("running");
		
		Cycle c = p1.getFirstCycle();
			for (int i=0; i<c.getCpuBurst();i++) {
				//Executing the cpu burst
			}
		
	
		}
	}

	
	
}
