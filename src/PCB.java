
public class PCB {
	private int pid;
	private String status;
	private Queue<Cycle> Cycles;
	private int CPUNum;
	private int IONum;
	private int waitNum;
	private int CPUSum ;
	private int IOSum ;

	public PCB() {
		this.pid = 0;
		this.status = "";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum  = 0;
		this.IOSum  = 0;
	}

	public PCB(int pid) {
		this.pid = pid;
		this.status = "new";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum  = 0;
		this.IOSum  = 0;
	}

	public void addCicle(int cpuBurst, int memory, int IOBurst) {
		
		Cycle c = new Cycle(cpuBurst, memory, IOBurst);
		this.Cycles.enqueue(c);
		this.CPUSum+=cpuBurst ;
		this.IOSum+= IOBurst  ;
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
	
	
	
	
}
