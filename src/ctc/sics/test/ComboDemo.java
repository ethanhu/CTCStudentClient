package ctc.sics.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ctc.util.JsonUtil;

public class ComboDemo 
{
	
	public static void main(String[] args) 
	{
		
		Book book = new Book();
		book.setBookName("胡老大");
		book.setPrice(100);
		
		String path = "ctc.sics.test.";
		String messageName = "Book";
		String message = JsonUtil.bean2json(book);
		
		try {
			//Book b = (Book) JsonUtil.getObject4JsonString(message, Book.class);
			Book b = (Book) JsonUtil.getObject4JsonString(message, Class.forName(path + messageName));
			System.out.println("b bookName = " + Class.forName(path + messageName));
			if(b != null){
				System.out.println("b bookName = " + b.getBookName());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("转换失败！");
			e.printStackTrace();
		}
		
		/*
		final String[] MENU1 = { "�ɵ�", "�2�", "�źŻ�"};
		
		final String[] MENU2_1 = { "1", "2", "3", "4", "6"};
		
		final String[] MENU2_2 = { "1/3", "5/7", "11", "13", "15","17"};
		
		final String[] MENU2_3 = { "S1", "S2", "S3", "X1", "X2","X3"};


		Display display=new Display();
		
		final Shell shell=new Shell(display);
		shell.setText("��j�˵�");
		
		final Combo combo1=new Combo(shell,SWT.NONE);
		combo1.setBounds(10,10,50,25);
		
		combo1.setItems(MENU1);
		combo1.select(0);
		
		final Combo combo2=new Combo(shell,SWT.NONE);
		combo2.setBounds(100,10,50,25);
		combo2.setItems(MENU2_1);
		combo2.select(0);
		
		combo1.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{ 
				String selectedItem = combo1.getItem(combo1.getSelectionIndex());
				
				if(selectedItem.equals("�ɵ�"))
				{
					combo2.removeAll();
					combo2.setItems(MENU2_1);
				}
				else if(selectedItem.equals("�2�"))
				{
					combo2.removeAll();
					combo2.setItems(MENU2_2);
				}
				else if(selectedItem.equals("�źŻ�"))
				{
					combo2.removeAll();
					combo2.setItems(MENU2_3);
				}
				combo2.select(0);
			}
		});
		
		shell.pack();
		shell.open();
		
		while(!shell.isDisposed())
		{ 
			if(!display.readAndDispatch())
			{ 
				display.sleep();
			}
		}
		display.dispose();
		
		*/
	}
}
