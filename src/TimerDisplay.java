import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TimerDisplay {
	
	private final JPanel m_panel;
	
	private ScheduledExecutorService m_scheduler;
	
	private Timer m_timer;
	
	private JTextField m_tx;
	
	public TimerDisplay(Timer timer) {
		m_scheduler = Executors.newScheduledThreadPool(1);
		m_panel = new JPanel();
		//m_panel.setPreferredSize(new Dimension(900, 200));
		m_tx = new JTextField(6);
		m_tx.setEditable(false);
		Font ft = Font.decode("Arial-BOLD-180");
		m_tx.setFont(ft);
		m_timer = timer;
		m_panel.add(m_tx);
		long time = timer.getTime();
		m_tx.setText(getHumanReadable(time));
		m_scheduler.scheduleAtFixedRate(new UpdateDisplay(), 1, 1, TimeUnit.MILLISECONDS);
	}
	
	private class UpdateDisplay implements Runnable {
		@Override
		public void run() {
			long time = m_timer.getTime();
			m_tx.setText(getHumanReadable(time));
			m_panel.repaint();
		}
	}
	
	public Component getComponent() {
		return m_panel;
	}
	
	public String getHumanReadable(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(0));
		cal.setTimeZone(TimeZone.getTimeZone("Zulu"));
		
		//TODO this could be an issue in the future
		cal.add(Calendar.MILLISECOND, (int)time);
		StringBuilder sb = new StringBuilder();
		
		//TODO impl this.
		if (time > 86400000) {
			//greater then 1 day
			if (time > 31536000000L) {
				//greater then 1 year
			}
			//high order yr/months/ days
		}
		
		
		//handle hours/min/sec/ms
		sb.append(cal.get(Calendar.HOUR_OF_DAY) + ":");
		sb.append(cal.get(Calendar.MINUTE) + ":");
		sb.append(cal.get(Calendar.SECOND) + ".");
		sb.append(cal.get(Calendar.MILLISECOND));
		

		return sb.toString();
	}

	public static void main(String[] args) {
		TimerImpl ti = new TimerImpl(Direction.DOWN, 5, TimeUnit.MINUTES);
		TimerDisplay td = new TimerDisplay(ti);
		ti.start();
		JFrame frame = new JFrame("JFrame Source Demo");
		frame.add(td.getComponent());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
