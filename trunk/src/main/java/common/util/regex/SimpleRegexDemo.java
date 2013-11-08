package common.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegexDemo {

	public static void splitRegexDemo() {
		String source = "this is ram 12 raj34 aam aaddmin ram again";

		// test:1, looks for a in the source
		String pattern = "a";
		String output[] = source.split(pattern);

		// test:2 looks for a followed by m
		pattern = "am";
		output = source.split(pattern);

		// test:3 looks for occurence of ram
		pattern = "ram";
		output = source.split(pattern);

		// test:4 either a or m
		source = "jai shree ram mere prabhu";
		pattern = "[am]";
		output = source.split(pattern);

		// test:5 either jai | ram
		source = "jai shree ram mere prabhu";
		pattern = "jai|ram|rab";
		output = source.split(pattern);

		// test:6 jai followed by ram
		source = "jai ram shree ram mere prabhu";
		pattern = "(jai)\\s+(ram)";
		output = source.split(pattern);

		System.out.println("outupt" + output);
	}

	public static void matchesRegexDemo() {
		// test:1 starts with j followed 
		String source = "jai shree";
		// String pattern = "(^j)(.*)"; // true
		// String pattern = "(^ja)(.*)"; // true
		// String pattern = "(^[iaj])(.*)"; // true
		// String pattern = "(^ji)(.*)"; // false
		String pattern = "(^[ji])(.*)"; // true
		boolean output = source.matches(pattern);
		System.out.println("outupt::" + output);

		// test:2 ends with
		pattern = "(.*)(shree$)"; // true
		pattern = "(.*)(\\w$)"; // true
		pattern = "(.*)(\\d$)"; // false
		pattern = "(.*)([abc]$)"; // false
		output = source.matches(pattern);
		System.out.println("test2::" + output);
	}

	public static void patternMatcherBasics() {
		String source = "jai shree ram";
		String pattern = "(^\\w)"; // false
		pattern = "(^\\w)(.*)"; // true

		Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher mat = pat.matcher(source);
		boolean output = mat.matches();
		System.out.println(output);
	}

	public static void patternMatcherBasics1() {
		String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";
		Pattern pattern = Pattern.compile("\\w+"); // ([\\w']+)
		Matcher matcher = pattern.matcher(EXAMPLE_TEST);

		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}

		// now create a new pattern and matcher to replace whitespace with tabs
		Pattern replace = Pattern.compile("\\s+");
		Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
		System.out.println(matcher2.replaceAll("\t"));

		String source = "this is java and some part of jsp";
		String regex = "java|jsp";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(source);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void patternMacherParser() {
		String source = "[202.131.142.161] - - /get?abcd profiles";

		// This regex has been built with 5 group space has been excluded
		String regex = "([\\w\\[\\].]+)\\s+([\\w-]+)\\s+([\\w-]+)\\s+([\\w/?]+)\\s+([\\w]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);

		while (matcher.find()) {
			String grp1 = matcher.group(1);
			String grp2 = matcher.group(2);
			String grp3 = matcher.group(3);
			String grp4 = matcher.group(4);
			String grp5 = matcher.group(5);

			System.out.println(grp1 + "|" + grp2 + "|" + grp3 + "|" + grp4 + "|" + grp5);
			System.out.println(matcher.group());
		}

	}

	public static void strageTest() {
		String regex = "((?=.*\\d))";
		String source = "san5tosh";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void main(String[] args) {
		patternMacherParser();
	}

}
