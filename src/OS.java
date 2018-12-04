import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class OS {

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
