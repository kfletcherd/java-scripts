package glob.bots;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * This class periodically sends keyboard events to the OS
 * to prevent it from locking
 */
final class OperatingSystemSkeletonKey {

	public static void main(String[] a)
	throws Exception {
		Robot angryKeyboardMasher = new Robot();

		while(true){
			//Thread.sleep(1000); // 1 sec
			Thread.sleep(1000 * 60 * 5); // 5 minutes
			angryKeyboardMasher.keyPress(KeyEvent.VK_CONTROL);
			angryKeyboardMasher.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

}

