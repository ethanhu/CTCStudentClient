package test.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableColumnMoveListener {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new RowLayout(SWT.HORIZONTAL));
    final Table table = new Table(shell, SWT.BORDER | SWT.CHECK);
    table.setLayoutData(new RowData(-1, 300));
    table.setHeaderVisible(true);
    TableColumn column = new TableColumn(table, SWT.LEFT);
    column.setText("Column 0");
    column = new TableColumn(table, SWT.CENTER);
    column.setText("Column 1");
    column = new TableColumn(table, SWT.CENTER);
    column.setText("Column 2");
    column = new TableColumn(table, SWT.CENTER);
    column.setText("Column 3");
    column = new TableColumn(table, SWT.CENTER);
    column.setText("Column 4");
    for (int i = 0; i < 100; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      String[] text = new String[] { i + " 0", i + " 1", i + " 2", i + " 3", i + " 4" };
      item.setText(text);
    }
    Listener listener = new Listener() {
      public void handleEvent(Event e) {
        System.out.println("Move " + e.widget);
      }
    };
    TableColumn[] columns = table.getColumns();
    for (int i = 0; i < columns.length; i++) {
      columns[i].pack();
      columns[i].setMoveable(true);
      columns[i].addListener(SWT.Move, listener);
    }
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
