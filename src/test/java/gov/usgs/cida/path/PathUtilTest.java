package gov.usgs.cida.path;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class PathUtilTest {
	private static final Logger log = LoggerFactory.getLogger(PathUtilTest.class);
	
	@Test
	public void testRelativePathCalculated() {
		String expected = "../";
		String requestURI = "/gcmrc-labs/time/";
		String contextPath = "/gcmrc-labs";
		String actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "";
		requestURI = "/gcmrc-labs/time";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../";
		requestURI = "/gcmrc-labs//time";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../../";
		requestURI = "/gcmrc-labs/time//";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../../../";
		requestURI = "/gcmrc-labs//time//";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../../";
		requestURI = "/gcmrc-labs/time/1203789600000/";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../../";
		requestURI = "/gcmrc-labs/time/1203789600000/CST";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
		
		expected = "../../../";
		requestURI = "/gcmrc-labs/time/1203789600000/CST/";
		contextPath = "/gcmrc-labs";
		actual = PathUtil.calculateRelativePath(requestURI, contextPath);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetRestOfPath() {
		String param1 = "utcMillis";
		String param2 = "zone";
		
		String expected = null;
		String requestURI = "/gcmrc-labs/time";
		String basePath = "time";
		String actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time";
		basePath = "/time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time";
		basePath = "/time/";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time";
		basePath = "/gcmrc-labs/time/";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time/";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time//";
		basePath = "time/";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = "1203789600000";
		requestURI = "/gcmrc-labs/time/1203789600000";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = "1203789600000";
		requestURI = "/gcmrc-labs/time/1203789600000/";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = "1203789600000";
		requestURI = "/gcmrc-labs/time//1203789600000";
		basePath = "gcmrc-labs//time/";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = "1203789600000";
		requestURI = "/gcmrc-labs/time//1203789600000";
		basePath = "gcmrc-labs///time/";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = "1203789600000";
		requestURI = "/gcmrc-labs//time/1203789600000";
		basePath = "gcmrc-labs/time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1).get(param1);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time/1203789600000";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time//1203789600000/";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
		
		expected = null;
		requestURI = "/gcmrc-labs/time/1203789600000//";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
		
		expected = "CST";
		requestURI = "/gcmrc-labs/time/1203789600000/CST";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
		
		expected = "CST";
		requestURI = "/gcmrc-labs/time/1203789600000/CST/";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
		
		expected = "CST";
		requestURI = "/gcmrc-labs/time//1203789600000//CST//";
		basePath = "time";
		actual = PathUtil.calculateRestOfURI(requestURI, basePath, param1, param2).get(param2);
		assertEquals(expected, actual);
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}
}
