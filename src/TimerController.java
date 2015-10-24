import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class allows the user to control a Timer via buttons
 * @author Alex
 *
 */
public class TimerController {
	
	private final Timer m_timer;
	
	private final JPanel m_panel;
	
	private JTextField m_time;
	
	private JButton start;
	
	private JButton stop;
	
	private JButton restart;
	
	public TimerController(Timer timer) {
		m_timer = timer;
		m_panel = new JPanel(new GridBagLayout());
		setDisplay();
	}
	
	public void setText(String time) {
		m_time.setText(time);
	}
	
	private void setDisplay() {
		GridBagConstraints c = new GridBagConstraints();
		m_time = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		m_panel.add(m_time, c);
		
		start = new JButton("START");
		start.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_timer.start();
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		m_panel.add(start, c);
		
		stop = new JButton("STOP");
		stop.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_timer.stop();
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		m_panel.add(stop, c);
		
		restart = new JButton("RESTART");
		restart.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				m_timer.restart(false);
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		m_panel.add(restart, c);
		
		
		
		
		
	}
	
	public Component getComponent() {
		return m_panel;
	}
	
	

}
