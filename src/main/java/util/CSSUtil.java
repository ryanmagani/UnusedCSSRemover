package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSSUtil
{

	/**
	 * Parses a map of CSS selectors to declarations from the given file. Any
	 * comments in the file will be ignored.
	 * 
	 * @param filename
	 *            The name of the file to parse
	 * @requires the file is a properly formatted CSS file. filename is not null
	 * @return a map whose keys are CSS selectors and values are lists of
	 *         declarations
	 */
	public static Map<String, List<String>> parse(String filename)
	{
		Map<String, List<String>> css = new HashMap<String, List<String>>();
		return null;
	}

	/**
	 * Writes a single-line CSS file from the given selector:declarations
	 * representation. Does not adequately compress the file.
	 * 
	 * Overwrites the file if it already exists, creates it otherwise.
	 * 
	 * @param css
	 *            A map whose keys are CSS selectors and values are lists of
	 *            declarations
	 * @param filename
	 *            The name of the file to write
	 * @requires neither CSS nor filename are null
	 */
	public static void writeToFile(Map<String, List<String>> css,
			String filename)
	{
	}
}