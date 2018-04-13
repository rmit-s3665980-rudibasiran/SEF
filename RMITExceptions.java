package SEF;

import java.io.FileNotFoundException;

public class RMITExceptions extends Exception {

	public RMITExceptions() {
	}

	public static void handleExceptions(Exception e) {
		if (e instanceof FileNotFoundException)
			System.out.println("File Not Found");
		else if (e instanceof NullPointerException) {
			System.out.println("Null Pointer Exception");
			e.printStackTrace();
		} else if (e instanceof ArrayIndexOutOfBoundsException)
			System.out.println("Array Index Out Of Bounds Exception");
		else
			e.printStackTrace();
	}
}
