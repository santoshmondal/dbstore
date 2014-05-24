package common.util.gauva;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GauvaCacheUpdate {

	private static RemovalListener<String, Object> removalListener = new RemovalListener<String, Object>() {
		@Override
		public void onRemoval(RemovalNotification<String, Object> removal) {
			System.out.println("Removal Event::" + removal.getKey());
		}
	};
	//@formatter:off
	private static LoadingCache<String, Object> qCache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.removalListener(removalListener)
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
