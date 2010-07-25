package javaunitevolution.core;

public class Timeout extends Thread {
	private int passed;
	private int total;
	private boolean finished;
	
	public Timeout(int total) {
		this.total = total;
	}
	
	@Override
	public void run() {
		try {
			for (passed = 0; passed < total; passed++)
				Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO: Proper error handling
			e.printStackTrace();
		} finally {
			finished = true;
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int getPassed() {
		return passed;
	}
	
	public int getTotal() {
		return total;
	}
}
