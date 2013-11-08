package common.util.regex;

import common.util.CommonUtil;

public class RegexUtil {

	public enum REGEX_ENUM {
		//@formatter:off
		WHITE_SPACE("\\s"), NON_WHITE_SPACE("\\S"),
		DIGIT("\\d"), NON_DIGIT("\\D"),
		WORD_CHAR("\\w"), NON_WORD_CHAR("\\W");  // [a-zA-Z_0-9]

		//@formatter:on
		private final String pattern;

		private REGEX_ENUM(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return pattern;
		}
	}

	public static String[] whiteSpaceSplit(String source) {
		if (!CommonUtil.isEmpty(source)) {
			return source.split(REGEX_ENUM.WHITE_SPACE.getPattern());
		}

		return null;
	}

	public static String[] digitSplit(String source) {
		if (!CommonUtil.isEmpty(source)) {
			return source.split(REGEX_ENUM.DIGIT.getPattern());
		}

		return null;
	}

	public static String[] wordcharSplit(String source) {
		if (!CommonUtil.isEmpty(source)) {
			return source.split(REGEX_ENUM.WORD_CHAR.getPattern());
		}

		return null;
	}

	public static String[] wordSplit(String source, String pattern) {
		if (!CommonUtil.isEmpty(source)) {
			return source.split(pattern);
		}

		return null;
	}

	public static void main(String[] args) {
	}
}
