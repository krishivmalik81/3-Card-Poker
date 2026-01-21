import javax.swing.JFrame;
public class Runner
{
	public static void main(String args[])
	{
		Screen game = new Screen();
		JFrame frame = new JFrame("Card Game");
		
		frame.add(game);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

