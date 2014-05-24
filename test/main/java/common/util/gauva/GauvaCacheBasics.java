package common.util.gauva;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GauvaCacheBasics {

	//@formatter:off
	private static LoadingCache<String, Object> qCache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.build(new CacheLoader<String, Object>(){
				@Override
				public Object load(String key) throws Exception {
					return null;
				}
			});
	//@formatter:on		

	public static void main(String[] args) {
		startProducer();
		startConsumer();
	}

	public static void startProducer() {
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				qCache.asMap().put("RAJ", "RAAZ!!" + System.currentTimeMillis());
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public static void startConsumer() {
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Calendar cal = Calendar.getInstance();
				Date time = cal.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				String out = sdf.format(time);
				System.out.println(out + qCache.asMap().get("RAJ"));
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

}
