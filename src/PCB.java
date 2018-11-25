
public class PCB {
	 private int pid;
	 private int cpuBurst;
	 private int memory;
	 private int io;
	 private String status;
	 public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PCB(int pid,int cpu,int memory,int io) {
		 this.pid=pid;
		 this.cpuBurst=cpu;
		 this.memory=memory;
		 this.io=io;
		 this.status="new";
	 }
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCpuBurst() {
		return cpuBurst;
	}
	public void setCpuBurst(int cpuBurst) {
		this.cpuBurst = cpuBurst;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public int getIo() {
		return io;
	}
	public void setIo(int io) {
		this.io = io;
	}
}
