//Anthony Miklos
//COP2552.0M2
//project 1 Invoice- returns values based on user input

//import class for dialog boxes
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

//create invoice class
//add variable values that can be used within the class for figuring totals
//create variables to hold user input
//create output
public class Invoice 
{
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public static String tipAmounts(double subTotal)
	{
		//creating tip values
		double fifteenPerc = subTotal * 0.15;
		double twentyPerc = subTotal * 0.2;
		double twentyFivePerc = subTotal * 0.25;
		
		return ("Suggested Tip Amounts: " 
				+ "\n\n" 
				+ "15% = $" 
				+ df2.format(fifteenPerc) 
				+ "\n" 
				+ "20% = $" 
				+ df2.format(twentyPerc) 
				+ "\n" 
				+ "25% = $" 
				+ df2.format(twentyFivePerc) 
				+ "\n\n" 
				+ "Enter the tip %:");
	}
	
	public static void main(String[] args)
	{
		//variables
		double taxRate = 0.065;
		double deliveryFee = 0.1;
		double tipAmount = 0;
		double subTotal = 0;
		double grandTotal = 0;
		String TipAmountStr = "";
		String inputAmount = "";
		String userName = "";
		
		//get user name
		userName = JOptionPane.showInputDialog("Welcome to the Delivery Calculator! \n\n Enter your name:");
		//get input amount as a string
		inputAmount = JOptionPane.showInputDialog("Hello " + userName + "\n\n" + "Enter the subtotal amount: $");
		//convert input string to double.
		subTotal = Double.parseDouble(inputAmount);
		
		//get tip amount
		//calling tip amounts method
		String tipDialogText = tipAmounts(subTotal);

		TipAmountStr = JOptionPane.showInputDialog(tipDialogText);
		//convert input str into double for calculating total.
		tipAmount = Double.parseDouble(TipAmountStr);
		tipAmount =  tipAmount * 0.01;
		
		//create grand total and display output text box
		df2.format(grandTotal = subTotal + (subTotal*taxRate) + (subTotal*deliveryFee) + (subTotal*tipAmount));
		JOptionPane.showMessageDialog(null,"Your Delivery Cost \n\n" 
				+ "Subtotal = $          \t" 
				+ subTotal 
			    + "\nTax =                        \t" 
				+ df2.format(subTotal*taxRate) 
			    + "\nDelivery fee =         \t" 
				+ df2.format(subTotal * deliveryFee) 
			    + "\nTip amount =          \t" 
				+ df2.format(subTotal*tipAmount) 
				+ "\n----------------\n" 
			    + "Total Due = $        \t" 
				+ df2.format(grandTotal));
	}
}
