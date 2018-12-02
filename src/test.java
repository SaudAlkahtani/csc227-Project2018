import java.io.FileNotFoundException;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		PQ q1 = new PQ();
		PQ q2 = new PQ();
		
//		q1.enqueueMax("Process 1 ", 1);
//		q1.enqueueMax("Process 2 ", 1);
//		q1.enqueueMax("Process 3 ", 1);
//		q1.enqueueMax("Process 4 ", 1);
//		q1.enqueueMax("Process 5 ", 1);
//		q1.enqueueMax("Process 6 ", 1);
//		q1.enqueueMax("Process 7 ", 1);
//		q1.enqueueMax("Process 8 ", 1);
//		
		JobQueue j1= new JobQueue();
		Queue<PCB> process = j1.getProcesses();
//		System.out.println(Integer.parseInt("0"));
		
		while(process.length()!=0){
			int cycle=1;
			PCB a = process.serve();
			System.out.println(a.getPid());
			Queue<Cycle> Cycles=a.getCycles();
			while(Cycles.length()!=0) {
				Cycle c = Cycles.serve();
				System.out.println("Cylce: " + cycle);
				System.out.print("Cpu Burst: " + c.getCpuBurst());
				System.out.print(" Memory: " + c.getMemory());
				System.out.println(" IO : " + c.getIOBurst());
				cycle++;
				
				
			}
		
		}
//		
//		q2.printall();

		
	}

}
