package test.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableSelectionListenerSelectedTableItem {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    final Table table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
    for (int i = 0; i < 16; i++) {
      TableItem item = new TableItem(table, 0);
      item.setText("Item " + i);
    }
    table.setBounds(0, 0, 100, 100);
    table.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event e) {
        String string = "";
        TableItem[] selection = table.getSelection();
        for (int i = 0; i < selection.length; i++)
          string += selection[i] + " ";
        System.out.println("Selection={" + string + "}");
      }
    });
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
