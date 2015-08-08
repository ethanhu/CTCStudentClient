package test.processbar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new GridLayout());

    // Create a smooth ProgressBar
    ProgressBar pb1 = new ProgressBar(shell, SWT.HORIZONTAL | SWT.SMOOTH);
    pb1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    pb1.setMinimum(0);
    pb1.setMaximum(30);

    // Start the first ProgressBar
    new LongRunningOperation(display, pb1).start();

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}

class LongRunningOperation extends Thread {
  private Display display;

  private ProgressBar progressBar;

  public LongRunningOperation(Display display, ProgressBar progressBar) {
    this.display = display;
    this.progressBar = progressBar;
  }

  public void run() {
    for (int i = 0; i < 30; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      display.asyncExec(new Runnable() {
        public void run() {
          if (progressBar.isDisposed())
            return;
          progressBar.setSelection(progressBar.getSelection() + 1);
        }
      });
    }
  }
}
