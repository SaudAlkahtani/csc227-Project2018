	// this class represents every read process from the file.
	// every process contains cycles depends on how many cpu burst it has. 
public class PCB {
	private int pid;
	private String status;
	private Queue<Cycle> Cycles;
	private int CPUNum; // number of times the process has been in cpu
	private int IONum;	// number of times the process has been in io
	private int waitNum;// number of times the process has been in waiting queue
	private int CPUSum; // total time in cpu
	private int IOSum;	// total time in cpu
	private int memorySum;
	private int CPUstartTime;
	private int CPUendTime;
	private int readyQueueTime; // number of times the process has been in ready queue
	private int endTime;

	public PCB() {
		this.pid = 0;
		this.status = "";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum = 0;
		this.IOSum = 0;
		this.CPUstartTime = 0;
		this.CPUendTime = 0;
		this.readyQueueTime = 0;
		this.endTime = 0;

	}

	public PCB(int pid) {
		this.pid = pid;
		this.status = "new";
		this.Cycles = new Queue<Cycle>();
		this.CPUNum = 0;
		this.CPUSum = 0;
		this.IONum = 0;
		this.IOSum = 0;
		this.CPUstartTime = 0;
		this.CPUendTime = 0;
		this.readyQueueTime = 0;
		this.endTime = 0;

	}
	// add process cycles in queue
	public void addCycle(int cpuBurst, int memory, int IOBurst) {

		Cycle c = new Cycle(cpuBurst, memory, IOBurst);
		this.Cycles.enqueue(c);

	}
	// print all cycle information
	public void printall() {
		System.out.println("Process ID " + pid);
		Queue<Cycle> temp = new Queue<Cycle>();
		System.out.println(Cycles.length());
		while (Cycles.length() != 0) {
			Cycle a = Cycles.serve();
			System.out.println(
					"CPU burst: " + a.getCpuBurst() + " Memory : " + a.getMemory() + " IO : " + a.getIOBurst());
			temp.enqueue(a);
		}

		while (temp.length() != 0)
			this.Cycles.enqueue(temp.serve());

		System.out.println("---------------------------------------------");
		System.out.println();
	}

	public Cycle getFirstCycle() {
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

	public Cycle serveCycle() {
		return Cycles.serve();
	}

	public void increaseCPUSum(int CPUBurst) {
		this.CPUSum += CPUBurst;
	}

	public void increaseIOSum(int IOBurst) {
		this.IOSum += IOBurst;
	}

	public int getMemorySum() {
		return memorySum;
	}

	public void increaseMemorySum(int memorySum) {
		this.memorySum += memorySum;
	}

	public int getCPUstartTime() {
		return CPUstartTime;
	}

	public void setCPUstartTime(int cPUstartTime) {
		CPUstartTime = cPUstartTime;
	}

	public int getCPUendTime() {
		return CPUendTime;
	}

	public void setCPUendTime(int cPUendTime) {
		CPUendTime = cPUendTime;
	}

	public int getReadyQueueTime() {
		return readyQueueTime;
	}

	public void setReadyQueueTime(int readyQueueTime) {
		this.readyQueueTime = readyQueueTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
