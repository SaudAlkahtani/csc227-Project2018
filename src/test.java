
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<PCB> jobs = new Queue<PCB>();
		Queue<PCB> temp = new Queue<PCB>();

		PCB process1 = new PCB(1);
		PCB process2 = new PCB(2);
		
		process1.addCycle(10, 10, 10);
		process1.addCycle(20, 20, 20);
		process1.addCycle(30, 30, 30);
		process1.addCycle(40, 40, 40);
		process1.addCycle(50, 50, 50);
		process2.addCycle(60, 60, 60);
		process2.addCycle(70, 70, 70);
		process2.addCycle(80, 80, 80);
		process2.addCycle(90, 90, 90);
		process2.addCycle(100, 100, 100);
		
		jobs.enqueue(process1);
		jobs.enqueue(process2);

		PCB a ;
		
		while(jobs.length()!= 0){
			a = jobs.serve();
			a.printall();
			temp.enqueue(a);
		}
		
		while(temp.length() != 0)
			jobs.enqueue(temp.serve());

		
	}

}
