import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerImpl implements Timer {
	
	private final Direction m_direction;
	
	private TimerState m_state;
	
	private ScheduledExecutorService m_scheduler;
	
	private Future<?> m_future;
	
	private long m_time = 0;
	
	private final long m_initalTime;
	
	
	public TimerImpl(Direction direction, long time, TimeUnit unit) {
		m_direction = direction;
		m_state = TimerState.INITALIZED;
		m_scheduler = Executors.newScheduledThreadPool(1);
		m_initalTime = TimeUnit.MILLISECONDS.convert(time, unit);
		m_time = m_initalTime;
	}

	
	/**
	 * Internal Class to control the counting. 
	 * @author Alex
	 *
	 */
	private class Movement implements Runnable {
		@Override
		public void run() {
			if (m_direction == Direction.UP) {
				m_time++;
			} else {
				m_time--;
			}
			//TODO feature to add later. roll over and count up
			if (m_time == 0) {
				stop();
			}
		}
	}




	@Override
	public boolean start() {
		if (m_state == TimerState.INITALIZED || m_state == TimerState.STOPPED) {
			m_future = m_scheduler.scheduleAtFixedRate(new Movement(), 1, 1, TimeUnit.MILLISECONDS);
			m_state = TimerState.RUNNING;
			return true;
		} else {
			return false;
		}
	}





	@Override
	public boolean stop() {
		if (m_state == TimerState.RUNNING || m_state == TimerState.RESTARTING) {
			m_future.cancel(true);
			m_state = TimerState.STOPPED;
			return true;
		} else {
			return false;
		}
	}





	@Override
	public boolean restart(boolean restartAndGo) {
		if (m_state == TimerState.RUNNING || m_state == TimerState.RESTARTING || m_state == TimerState.STOPPED) {
			m_state = TimerState.RESTARTING;
			m_future.cancel(true);
			m_time = m_initalTime;
			m_future = m_scheduler.scheduleAtFixedRate(new Movement(), 1, 1, TimeUnit.MILLISECONDS);
			m_state = TimerState.RUNNING;
			return true;
		} else {
			return false;
		}
	}
	
	public long getTime() {
		return m_time;
	}
	
	public TimerState getTimerState() {
		return m_state;
	}




}
