import javax.swing.JOptionPane;

//Anthony Miklos

//TODO: the total amount of time the battery charged
//      the total amount of battery life used on video chat
//      the total amount of battery life used watching videos
//      the total amount of battery life used playing games
//      the total amount of battery life left on the device, if any.

public class ExitProgram {
	// exit message called when user selects exit or the battery charge reaches 0
	public static void byeBye()
	{
		JOptionPane.showMessageDialog(null, "The current battery charge time was: " + ChargeBattery.batteryChargeTime 
				+ "\n" +
				"The amount of battery life used on video chat was: " + VideoChat.timeChatting 
				+ "\n" +
				"The amount of battery life used on watching videos was: " + WatchVideo.timeWatching
				+ "\n" +
				"The amount of battery life used on the video game was: " + PlayGame.timePlaying
				+ "\n" +
				"The amount of battery life left: " + ChargeBattery.currentBatteryLife 
				+ "%\n");
		System.exit(0);
	}
}
