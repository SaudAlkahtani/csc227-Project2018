
public class PCB {
	private int pid;
	private String status;
	private Queue<Cycle> Cycles;
	private int CPUNum;
	private int IONum;
	private int waitNum;
	private int CPUSum;
	private int IOSum;

	public PCB() {
		this.pid = 0;
		this.status = "";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum = 0;
		this.IOSum = 0;
	}

	public PCB(int pid) {
		this.pid = pid;
		this.status = "new";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum = 0;
		this.IOSum = 0;
	}

	public void addCycle(int cpuBurst, int memory, int IOBurst) {

		Cycle c = new Cycle(cpuBurst, memory, IOBurst);
		this.Cycles.enqueue(c);
		this.CPUSum += cpuBurst;
		this.IOSum += IOBurst;
	}

	public void printall() {
		System.out.println("Process ID "+ pid);
		Queue<Cycle> temp = new Queue<Cycle>();
		
		while (Cycles.length() != 0) {
			Cycle a = Cycles.serve();
			System.out.println("CPU burst: " + a.getCpuBurst() + " Memory : "+ a.getMemory()+ " IO : " + a.getIOBurst() );
			temp.enqueue(a);
		}
		
		while(temp.length()!= 0 )
			this.Cycles.enqueue(temp.serve());
		
		System.out.println("---------------------------------------------");
		System.out.println();
	}
	
	public Cycle getFirstCycle(){
		return this.Cycles.serve();
	}

	public Queue<Cycle> getCycles() {
		return Cycles;
	}

	public void setCycles(Queue<Cycle> cycles) {
		Cycles = cycles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getCPUNum() {
		return CPUNum;
	}

	public int getIONum() {
		return IONum;
	}

	public int getWaitNum() {
		return waitNum;
	}

	public void CPUNumIncrement() {
		this.CPUNum = CPUNum + 1;
	}

	public void IONumIncrement() {
		this.IONum = IONum + 1;
	}

	public void waitNumIncrement() {
		this.waitNum = waitNum + 1;
	}

	public int getCPUSum() {
		return CPUSum;
	}

	public int getIOSum() {
		return IOSum;
	}

	public int getFirstMemory() {
		return Cycles.peek().getMemory();
	}
	
	public int getFirstCPU() {
		return Cycles.peek().getCpuBurst();
	}

	
	public Cycle serveCycle(){
		return Cycles.serve();
	}

	
	
}
