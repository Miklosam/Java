import javax.swing.JOptionPane;

//Anthony Miklos

//TODO: prompt the user for the length of time playing a game
//      decrease the battery life by 10% for every minute of use
public class PlayGame {

	public static int timePlaying = 0;
	public static void bigGaming()
	{
		String gameString = JOptionPane
				.showInputDialog(ChargeBattery.currentBatteryLife + "%\n" + "How many minutes do you wish to play the video game? \n");
		int gameBattMinus = Integer.parseInt(gameString) * 10;
		int percentageDrained = ChargeBattery.drain(gameBattMinus);
		JOptionPane.showMessageDialog(null, "The current battery life is :" + ChargeBattery.currentBatteryLife + "%");
		timePlaying += percentageDrained / 10;
	}
}
