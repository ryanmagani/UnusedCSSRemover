package util;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CSSUtilTest
{

	private static final int TIMEOUT = 30000;

	private Map<String, Set<String>> css;

	private Set<String> expectedSet;

	// /////////////////////////////////////////////////////////////////////////
	// tests for parse
	// /////////////////////////////////////////////////////////////////////////

	@Before
	public void setup()
	{
		css = CSSUtil.parse("test.css");
		expectedSet = new HashSet<String>();
	}

	@Test(timeout = TIMEOUT)
	public void duplicateDeclarationsWithVaryingWhiteSpaceOnlyIncludedOnce()
	{
		expectedSet.add("color:blue");
		assertDeclarations("'a' tag and declaration appear "
				+ "thrice but should be included once", "a");
	}

	@Test(timeout = TIMEOUT)
	public void firstSelectorInLineIncluded()
	{
		expectedSet.add("color:blue");
		assertDeclarations(
				"the first selector on a multi-selector line is included",
				"body");
	}

	@Test(timeout = TIMEOUT)
	public void middleSelectorInLineIncluded()
	{
		expectedSet.add("color:red");
		assertDeclarations(
				"selector in the middle of a mult-selector line is included",
				"li");
	}

	@Test(timeout = TIMEOUT)
	public void endSelectorInLineIncluded()
	{
		expectedSet.add("color:yellow");
		assertDeclarations("selector at the end of a line is included", "span");
	}

	@Test(timeout = TIMEOUT)
	public void rulesSpanningMultipleLinesAreIncluded()
	{
		expectedSet.add("border-radious:0px");
		expectedSet.add("background-color:red");
		expectedSet.add("text-transform:lowercase");
		assertDeclarations("all rules of multi-line declaration are included",
				"ul");
	}

	@Test(timeout = TIMEOUT)
	public void commaSeparatedSelectorsIncludedAsOneDeclaration()
	{
		expectedSet.add("width:300px");
		assertDeclarations("rule for multiple tags are added as one",
				"ol, div, span");
	}

	@Test(timeout = TIMEOUT)
	public void childSelectorIncluded()
	{
		expectedSet.add("text-align:center");
		assertDeclarations("child selectors are handled properly", "body > div");
	}

	@Test(timeout = TIMEOUT)
	public void adjacentSelectorIncluded()
	{
		expectedSet.add("color:green");
		assertDeclarations("adjacent selectors are handled properly",
				"body + div");
	}

	@Test(timeout = TIMEOUT)
	public void siblingSelectorIncluded()
	{
		expectedSet.add("color:blue");
		assertDeclarations("sibling selectors are handled properly",
				"body ~ div");
	}

	@Test(timeout = TIMEOUT)
	public void descendantSelectorIncluded()
	{
		expectedSet.add("color:orange");
		assertDeclarations("descendant selectors are handled properly",
				"body div");
	}

	@Test(timeout = TIMEOUT)
	public void classSelectorsIncluded()
	{
		expectedSet.add("text-alignt:right");
		assertDeclarations("class selectors handled", ".right");
	}

	@Test(timeout = TIMEOUT)
	public void idSelectorsIncluded()
	{
		expectedSet.add("vertical-align:inherit");
		assertDeclarations("id selectors handled", "#bob");
	}

	@Test(timeout = TIMEOUT)
	public void commentsIgnored()
	{
		expectedSet = null;
		assertDeclarations(
				"object selector commented out so its set should be null",
				"object");
	}

	@Test(timeout = TIMEOUT)
	public void multiCombinationsIncluded()
	{
		expectedSet.add("color:green");
		assertDeclarations("multi-combinations handled",
				"ol > div#bob > span.div");
	}

	@Test(timeout = TIMEOUT)
	public void pseudoClassesIncluded()
	{
		expectedSet.add("color:green");
		assertDeclarations("pseudo-classes handled", "a:hover");
	}

	@Test(timeout = TIMEOUT)
	public void pseudoElementsIncluded()
	{
		expectedSet.add("font-size:10px");
		assertDeclarations("pseudo-elements handled", "p::first-letter");
	}

	@Test(timeout = TIMEOUT)
	public void simpleAttrIncluded()
	{
		expectedSet.add("background-color:yellow");
		assertDeclarations("simple attribute selector handled",
				"a[target=\"_blank\"");
	}

	@Test(timeout = TIMEOUT)
	public void tildaAttrIncluded()
	{
		expectedSet.add("color:blue");
		assertDeclarations("tilda attribute selector handled",
				"a[target=\"_blank\"");
	}

	@Test(timeout = TIMEOUT)
	public void pipeAttrIncluded()
	{
		expectedSet.add("color:yellow");
		assertDeclarations("pipe attribute selector handled", "[class|=\"top\"");
	}

	@Test(timeout = TIMEOUT)
	public void dollarAttrIncluded()
	{
		expectedSet.add("color:yellow");
		assertDeclarations("dollar attribute selector handled", "[class$=\"top\"");
	}

	@Test(timeout = TIMEOUT)
	public void caretAttrIncluded()
	{
		expectedSet.add("color:yellow");
		assertDeclarations("caret attribute selector handled", "[class^=\"top\"");
	}

	@Test(timeout = TIMEOUT)
	public void starAttrIncluded()
	{
		expectedSet.add("color:yellow");
		assertDeclarations("star attribute selector handled", "[class*=\"top\"");
	}

	private void assertDeclarations(String message, String key)
	{
		Set<String> actualSet = css.get(key);
		assertEquals(message, expectedSet, actualSet);
	}

	// /////////////////////////////////////////////////////////////////////////
	// tests for writeToFile
	// /////////////////////////////////////////////////////////////////////////
}
