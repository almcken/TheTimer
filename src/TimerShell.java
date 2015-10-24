import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimerShell {

	public static void main(String[] args) {
		TimerImpl ti = new TimerImpl(Direction.DOWN, 5, TimeUnit.MINUTES);
		TimerDisplay td = new TimerDisplay(ti);
		//ti.start();
		
		TimerController tc = new TimerController(ti);
		tc.setText(td.getHumanReadable(ti.getTime()));
		
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(td.getComponent(), BorderLayout.EAST);
		panel.add(tc.getComponent(), BorderLayout.WEST);
		
		
		JFrame frame = new JFrame("JFrame Source Demo");
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
