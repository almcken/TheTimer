
public interface Timer {
	
	
	public boolean start();
	
	public boolean stop();
	
	public boolean restart(boolean restartAndGo);
	
	public long getTime();
	
	public TimerState getTimerState();
	

}
