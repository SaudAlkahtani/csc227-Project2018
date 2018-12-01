
public class Ram {
	public final static int ramSize = 160 ;
	private int availableSize ;
	LongTermScheduler jopQ ;
	public PQ readyQueue ;
	
	public Ram(){
		this.availableSize = 32 ;
		this.jopQ = new LongTermScheduler();
		this.readyQueue = new PQ();
	}
	
	public void addToReadyQueue(){
		
	}
	
	
	
	
	
	

}
