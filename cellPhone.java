//Anthony Miklos
//COP2552
//Creating an application that will behave as a cell phone battery program.

import javax.swing.JOptionPane;

//TODO: When the application begins, the battery of the hand held device will be at full charge. 
//      The user will continue to be prompted what they would like to do on the device until the 
//      battery is at 0%.  When the battery is at 0%, the application will close.

public class cellPhone {
	
	public static void promptWindow()
	{
		int userOptionNum = 0;
		
		String homeText = "Press 1 to Charge the Battery \n"
				+ "Press 2 to video chat \n"
				+ "Press 3 to watch a video \n"
				+ "Press 4 to play a game \n" 
				+ "Press 5 to exit \n";

		//check battery life, call ExitProgram if 0
		while (ChargeBattery.currentBatteryLife > 0)
		{
			userOptionNum = Integer.parseInt(JOptionPane.showInputDialog(homeText));
			//using switch case to call each class
			switch (userOptionNum)
			{
				case 1: 
					ChargeBattery.batteryCharger();
					break;
				case 2:
					VideoChat.zoomSkype();
					break;
				case 3:
					WatchVideo.watchTube();
					break;
				case 4:
					PlayGame.bigGaming();
					break;
				case 5:
					ExitProgram.byeBye();
					break;
			}
		}
		ExitProgram.byeBye();
	}
	
	public static void main(String[] args)
	{
		//calling main dialog
		cellPhone.promptWindow();
	}
	
}
