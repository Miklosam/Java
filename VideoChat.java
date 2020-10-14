import javax.swing.JOptionPane;

//Anthony Miklos

//TODO: prompt the user for the amount of time spent on video chat
//      decrease the battery life by 1% for every minute of use
public class VideoChat {

	public static int timeChatting = 0;
	public static void zoomSkype()
	
	//method displays input dialog and calls method on class chargeBattery to initiate a "drain" 
	{
		String chatString = JOptionPane
				.showInputDialog(ChargeBattery.currentBatteryLife + "%\n" + "How many minutes do you wish to video chat? \n");
		int chatBattMinus = Integer.parseInt(chatString);
		int percentDrained = ChargeBattery.drain(chatBattMinus);
		JOptionPane.showMessageDialog(null, "The current battery life is :" + ChargeBattery.currentBatteryLife + "%");
		timeChatting += percentDrained;
				
	}
}
