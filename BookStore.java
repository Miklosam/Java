import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Anthony Miklos
//COP2552
//Write a Java program using good object-oriented programming principles 
//that will read and calculate data from two input files and will write 
//information to three output files.(Project 2)


public class BookStore {
	
	private static boolean isErrorFileCreated = false;
	
	//TODO: read in the input files
		//process the input files
			//first input file (CurrentStatusIn.txt)
				//first file contains previous system date, all ISBN, stock quantity
				//each ISBN has two quantities and are one group
			//second input file (Ship0916.txt)
				//automatically created when a shipment of books is delivered after business hours the same day.
				//contains ISBN and quantity shipped
		//close input files
		//create output files
			//specific directory for file location
				//update output files
					//first output file (CurrentStatusOut.txt)
						//will contain the current system date (MMDDYYY format - not hard coded) at the top of the file 
						//followed by the ISBN number, the updated current quantity, and the stock quantity each on a separate 
						//line for each book found in the (First Input File)
					//second output file (Order0916.txt)
						//will contain the ISBN number and the quantity needed to be ordered to get the quantity of books up 
						//to stock quantity if the current quantity is less than 25% of the stock quantity. 
					//third output file: (Error0916.txt)
						//will contain the current system date (MMDDYYYY format - not hard coded) at the top of the file followed 
						//by the ISBN number and quantity shipped in error.  This error occurs if the ISBN number is found in 
						//the Ship0916.txt input file, but does not exist in the CurrentStatusIn.txt input file.  If no errors 
						//exist, this file should not be created.
		//close output files
	
	private static BookShipment nextBookShipment(BufferedReader reader) throws IOException {
		String nextLine = reader.readLine();
		if (nextLine == null) {
			return null;
		}
		String isbn = nextLine;
		Integer shipmentQuantity = Integer.parseInt(reader.readLine());
		return new BookShipment(
			new Book(isbn),
			shipmentQuantity
		);
	}
	
	private static BookInventory nextBookInventory(BufferedReader reader) throws IOException {
		String nextLine = reader.readLine();
		if (nextLine == null) {
			return null;
		}
		String isbn = nextLine;
		Integer currentQuantity = Integer.parseInt(reader.readLine());
		Integer stockQuantity = Integer.parseInt(reader.readLine());
		return new BookInventory(
			new Book(isbn),
			currentQuantity,
			stockQuantity
		);
	}
	
	private static String dateString() {
		return  DateTimeFormatter.ofPattern("MMddyyyy").format(LocalDateTime.now());
	}
	
	private static void writeNewStatus(PrintWriter writer, BookInventory bookInventory) {
		writer.println(bookInventory.book.isbn);
		writer.println(bookInventory.currentQuantity);
		writer.println(bookInventory.stockQuantity);
	}

	private static void writeReorder(PrintWriter writer, Book book, Integer requestQuantity) {
		writer.println(book.isbn);
		writer.println(requestQuantity);
	}
	
	private static void reorderIfNecessary(PrintWriter reorderWriter, BookInventory bookInventory) {
		if (bookInventory.currentQuantity < (bookInventory.stockQuantity * .25)) {
			int orderAmount = bookInventory.stockQuantity - bookInventory.currentQuantity;
			writeReorder(reorderWriter, bookInventory.book, orderAmount);
		}
	}
	
	private static void writeError(BookShipment bookShipment) throws IOException {
		if(!(new File("C:\\SFC\\COP2552\\Project_2\\Error0916.txt").exists()) || !isErrorFileCreated) {
			PrintWriter errorWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\SFC\\COP2552\\Project_2\\Error0916.txt")));
			errorWriter.println(dateString());
			errorWriter.close();
			isErrorFileCreated = true;
		}
		PrintWriter errorWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\SFC\\COP2552\\Project_2\\Error0916.txt", true)));
		errorWriter.println(bookShipment.book.isbn);
		errorWriter.println(bookShipment.shipmentQuantity);
		errorWriter.close();
	}
	
	public static void currentStatusInFileRead(String[] args) throws IOException {
		File currentStatusFile = new File("C:\\SFC\\COP2552\\Project_2\\P2CurrentStatusIn.txt");
		BufferedReader currentStatusReader = new BufferedReader(new FileReader(currentStatusFile));
		
		File shipFile = new File("C:\\SFC\\COP2552\\Project_2\\P2Ship0916.txt");
		BufferedReader shipReader = new BufferedReader(new FileReader(shipFile));
		
		PrintWriter newStatusWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\SFC\\COP2552\\Project_2\\CurrentStatusOut.txt")));
		newStatusWriter.println(dateString());
		
		PrintWriter reorderWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\SFC\\COP2552\\Project_2\\Order0916.txt")));
		
		// Read the date from the current status file, but don't use it anywhere
		currentStatusReader.readLine();
		
		BookInventory bookInventory;
		BookShipment bookShipment = nextBookShipment(shipReader);
		while((bookInventory = nextBookInventory(currentStatusReader)) != null) {			
			while(bookShipment != null && bookShipment.book.isbn.compareTo(bookInventory.book.isbn) < 0) {
				writeError(bookShipment);
				bookShipment = nextBookShipment(shipReader);
			}

			if(bookShipment != null && bookInventory.book.isbn.equals(bookShipment.book.isbn)) {
				bookInventory.currentQuantity = bookInventory.currentQuantity + bookShipment.shipmentQuantity;
				writeNewStatus(newStatusWriter, bookInventory);
				bookShipment = nextBookShipment(shipReader);
				reorderIfNecessary(reorderWriter, bookInventory);
			} else {
				writeNewStatus(newStatusWriter, bookInventory);
				reorderIfNecessary(reorderWriter, bookInventory);
			}
		}
		
		newStatusWriter.close();
		reorderWriter.close();

	}
	
	public static void main(String[] args) {
		try {
			currentStatusInFileRead(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
