
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PQ q1 = new PQ();
		PQ q2 = new PQ();

		q1.enqueueMax("Process 1 ", 1);
		q1.enqueueMax("Process 2 ", 1);
		q1.enqueueMax("Process 3 ", 1);
		q1.enqueueMax("Process 4 ", 1);
		q1.enqueueMax("Process 5 ", 1);
		q1.enqueueMax("Process 6 ", 1);
		q1.enqueueMax("Process 7 ", 1);
		q1.enqueueMax("Process 8 ", 1);
		
		
		
		q1.printall();
		
		System.out.println("------------------------------------------------------------------------------------------------");
		
		while(q1.length()!=0){
			PQNode a = q1.serve();
			
			q2.enqueueMin(a.data, a.priority);
		}
		
		q2.printall();

		
	}

}
