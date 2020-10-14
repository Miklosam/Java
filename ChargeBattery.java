import javax.swing.JOptionPane;

//Anthony Miklos

//TODO: the current battery life % value (range is 0 - 100%) 
//      prompt the user for the amount of time allowed to charge
//      add 1% for every minute allowed to charge
public class ChargeBattery {

	public static int currentBatteryLife = 100;
	public static int batteryChargeTime = 0;

	public static void batteryCharger() {
		String userBattString = "";
		int userBattNum;

		// prompt for time to charge 
		if (currentBatteryLife < 100) {
			userBattString = JOptionPane
					.showInputDialog(currentBatteryLife + "\n" + "How many minutes do you wish to charge: \n");
			userBattNum = Integer.parseInt(userBattString);
			currentBatteryLife = Math.min(100, currentBatteryLife + userBattNum);
			JOptionPane.showMessageDialog(null, "The current battery life is :" + currentBatteryLife + "%");
			batteryChargeTime += userBattNum;

		} else
			// display current battery charge percentage then return to main window
			JOptionPane.showMessageDialog(null, "The current battery life is already 100%");
		
	}
	// method for draining battery called by the other classes
	public static Integer drain(Integer amountToDecrease) {
		currentBatteryLife = Math.max(0, Math.min(100, currentBatteryLife - amountToDecrease));
		return Math.min(100 - currentBatteryLife, amountToDecrease);
	}

}
