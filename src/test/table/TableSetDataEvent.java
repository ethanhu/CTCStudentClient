package test.table;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableSetDataEvent {

  static int[] data = new int[0];

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    final Table table = new Table(shell, SWT.BORDER | SWT.VIRTUAL);
    table.addListener(SWT.SetData, new Listener() {
      public void handleEvent(Event e) {
        System.out.println(e);
        TableItem item = (TableItem) e.item;
        int index = table.indexOf(item);
        item.setText("Item " + data[index]);
      }
    });
    int count = 0;
    Random random = new Random();
    while (count++ < 50) {
      int grow = 10;
      int[] newData = new int[data.length + grow];
      System.arraycopy(data, 0, newData, 0, data.length);
      int index = data.length;
      data = newData;
      for (int j = 0; j < grow; j++) {
        data[index++] = random.nextInt();
      }

      table.setItemCount(data.length);
      table.clearAll();
    }
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
