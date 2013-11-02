package common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {

	public static boolean isEmpty(Object sSource) {
		boolean returnValue = true;
		if (sSource instanceof String) {
			returnValue = isEmpty((String) sSource);
		} else if (sSource instanceof Collection) {
			if (null != sSource && sSource instanceof List<?> && ((List<?>) sSource).size() > 0) {
				returnValue = false;
			} else if (null != sSource && sSource instanceof Set<?> && ((Set<?>) sSource).size() > 0) {
				returnValue = false;
			}
		} else if (sSource instanceof Map) {
			if (null != sSource && ((Map<?, ?>) sSource).size() > 0) {
				returnValue = false;
			}
		} else if (sSource != null) {
			return false;
		}
		return returnValue;
	}

	public static boolean isEmpty(String sSource) {
		if (null == sSource || "".equals(sSource.trim()) || "null".equalsIgnoreCase(sSource)) {
			return true;
		}
		return false;
	}

	public static long getTimeDifference(long startTime, long endTime) {
		return (endTime - startTime) / 1000;
	}
}
