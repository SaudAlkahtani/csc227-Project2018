import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		JobQueue j1= new JobQueue();
//		Queue<PCB> jobs = j1.getProcesses();
//		Queue<PCB> temp = new Queue<PCB>();
//
//		PCB process1 = new PCB(1);
//		PCB process2 = new PCB(2);
		OS os= new OS();
		try {
			os.WriteToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		
//		Queue<PCB> process = 
////		System.out.println(Integer.parseInt("0"));

//		process1.addCycle(10, 10, 10);
//		process1.addCycle(20, 20, 20);
//		process1.addCycle(30, 30, 30);
//		process1.addCycle(40, 40, 40);
//		process1.addCycle(50, 50, 50);
//		process2.addCycle(60, 60, 60);
//		process2.addCycle(70, 70, 70);
//		process2.addCycle(80, 80, 80);
//		process2.addCycle(90, 90, 90);
//		process2.addCycle(100, 100, 100);
//
//		jobs.enqueue(process1);
//		jobs.enqueue(process2);

//		PCB a;
//
//		while (jobs.length() != 0) {
//			a = jobs.serve();
//			a.printall();
//			temp.enqueue(a);
//
//		}
	}
}
