import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class PaisTest extends TestCase {
	protected PaisBridge bridge;
	
	@Before
	public void setUp() throws Exception {
		bridge = Driver.getBridge();
	}

	@Test
	public void testAddShowPageIsPresented() {
		assertTrue("Can't get 'add new show' page", bridge.addShowPageIsPresented());
	}
	@Test
	public void testAddShow() {
		assertFalse("Added show altough missing fields", bridge.addShow(null, null, null, null, 0, null));
		assertTrue("Can't add show", bridge.addShow("my Show", "show description", "hall name", new Date(), 50.0, new Date()));
		//assertFalse("Added show altough missing fields", bridge.addShow(null, null, null, null, null, null));
	}

}
