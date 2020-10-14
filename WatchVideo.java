import javax.swing.JOptionPane;

//Anthony Miklos

//TODO: prompt the user for the length of the video
//      decrease the battery life by 5% for every minute of use
public class WatchVideo {

	public static int timeWatching = 0;
	public static void watchTube()
	{
		String videoString = JOptionPane
				.showInputDialog(ChargeBattery.currentBatteryLife + "%\n" + "How many minutes do you wish to watch a video? \n");
		int vidBattMinus = Integer.parseInt(videoString) * 5;
		int percentDrained = ChargeBattery.drain(vidBattMinus);
		JOptionPane.showMessageDialog(null, "The current battery life is :" + ChargeBattery.currentBatteryLife + "%");
		timeWatching += percentDrained / 5;
	}
}
