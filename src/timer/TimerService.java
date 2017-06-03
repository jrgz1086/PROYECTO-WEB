package timer;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Stateless(name = "TimerServiceBean")
public class TimerService {
	
	@Schedule(second = "*/60", minute = "*", hour = "*")
	private void scheduledTimeout(final Timer t) {
		System.out.println("@Schedule called at: " + new java.util.Date());
	}
	
	
}



