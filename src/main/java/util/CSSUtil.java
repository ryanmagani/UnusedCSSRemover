package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * order matters
 * duplicates are okay
 * empty decs are okay
 * 
 * so basically:
 * don't change the file at all, just get everything in order in a way that's
 * easy to remove things
 * 
 * regex for finding comments
 * (?s)\/\*.*?\*\/
 */

public class CSSUtil
{

	/**
	 * Parses a map of CSS selectors to declarations from the given file. Any
	 * comments in the file will be ignored.
	 * 
	 * @param filename
	 *            The name of the file to parse
	 * @requires the file is a properly formatted CSS file. filename is not null
	 * @return a map whose keys are CSS selectors and values are declarations
	 */
	public static Map<String, Set<String>> parse(String filename)
	{
		Map<String, Set<String>> css = new HashMap<String, Set<String>>();
		return css;
	}

	/**
	 * Writes a single-line CSS file from the given selector:declarations
	 * representation. Does not adequately compress the file.
	 * 
	 * Overwrites the file if it already exists, creates it otherwise.
	 * 
	 * @param css
	 *            A map whose keys are CSS selectors and values are declarations
	 * @param filename
	 *            The name of the file to write
	 * @requires neither CSS nor filename are null
	 */
	public static void writeToFile(Map<String, Set<String>> css,
			String filename)
	{
	}
}
