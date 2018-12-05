import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class OS {

	public static void main(String args[]) {

		System.out.println("Welcome to our program!");
		System.out.println("This program will simulate single core processer.");
		
		Ram r1 = new Ram();

		CPU cpu1 = new CPU();
		cpu1.runCpu();
		Queue<PCB> jobs = cpu1.getRam().getFinshedProcesses();
		
		
		//System.out.println("Finished: " + jobs.length());
		PCB b = jobs.serve();
		System.out.println(b.getReadyQueueTime());
		System.out.println(b.getCPUNum());
		System.out.println(b.getCPUSum());
		
		
//		if(r1.getFinshedProcesses().length()==50) {
//			
//		}
	}

	public void WriteToFile() throws IOException {
		try {
			// Sample 01: Open the FileWriter, Buffered Writer
			FileWriter fw = new FileWriter("TestFile.Txt");
			BufferedWriter WriteFileBuffer = new BufferedWriter(fw);

			// Sample 02: Write Some Text to File
			// Using Buffered Writer)
			WriteFileBuffer.write("First Line \n Second line!");

			// Sample 03: Close both the Writers
			WriteFileBuffer.close();
			System.out.println("I think it worked!");

		} catch (IOException Ex) {
			System.out.println(Ex.getMessage());
		}

	}
}
