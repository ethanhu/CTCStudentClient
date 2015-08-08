package test.scale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

public class Snippet45 {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Scale scale = new Scale(shell, SWT.BORDER);
		scale.setSize(200, 64);
		scale.setMaximum(40);
		scale.setPageIncrement(5);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
