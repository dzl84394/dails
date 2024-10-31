package cn.dails.logging.counter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter {
//	protected final AtomicInteger counter = new AtomicInteger(0);

	private static Map<String, AtomicInteger> counter ;

	private static ConcurrentHashMap<String,Long> concurrentHashMap;

	public AtomicInteger getCurrency(String key) {
		AtomicInteger atomic = counter.get(key);
		if (atomic == null) {
			atomic = new AtomicInteger(0);
		}
		return atomic;
	}

	public void setCurrency(String key,AtomicInteger atomic) {
		counter.put(key, atomic);
	}





}
