package test.processbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarUpdateUIThread {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    ProgressBar bar = new ProgressBar(shell, SWT.SMOOTH);
    bar.setBounds(10, 10, 200, 32);
    shell.open();
    for (int i = 0; i <= bar.getMaximum(); i++) {
      try {
        Thread.sleep(100);
      } catch (Throwable th) {
      }
      bar.setSelection(i);
    }
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
