/**
 * ���� ����� ������������ ��� ������ ����.
 * @author Anastasia
 * @version 0.1
 */
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Menu {
	/** ��������, � ������� ���������� ����������� ��������� */
	private Shell shell;
	/** �������, ������� ���������� ��������*/
	private Display display;
	/** �����������, ���������������� ����������
	 * @param display - �������
	 * @param shell - �������� ������, � ��� ������������ ���������
	 */
	Menu(Display display, Shell shell)
	{
		this.shell=shell;
		this.display=display;	
	}
	/**�����, �������, ������� ���������� ���� ���� � ����������� ��������� ������*/
	public void Show()
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/menu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image backgroundImage = new Image(display, is); 
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Font buttonFont = new Font( shell.getDisplay(), 
				new FontData( "Times New Roman", 14, SWT.NORMAL ) );
		shell.setBackgroundImage(backgroundImage);
		GridLayout mainlayout=new GridLayout();
		mainlayout.numColumns=1;
		shell.setLayout(mainlayout);
		mainlayout.marginLeft=220;
		mainlayout.marginTop=315;
		GridData griddataButtonRules=new GridData();
		griddataButtonRules.horizontalAlignment=GridData.CENTER;
		griddataButtonRules.heightHint=65;
		griddataButtonRules.widthHint=350;
		Button buttonRules=new Button(shell, SWT.PUSH);
		buttonRules.setFont(buttonFont);
		buttonRules.setText("������� ����");
		buttonRules.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonRules.setLayoutData(griddataButtonRules);
		GridData griddataButtonComputer=new GridData();
		griddataButtonComputer.horizontalAlignment=GridData.CENTER;
		griddataButtonComputer.heightHint=65;
		griddataButtonComputer.widthHint=350;
		Button buttonComputer=new Button(shell, SWT.PUSH);
		buttonComputer.setText("����� ������ ����������");
		buttonComputer.setFont(buttonFont);
		buttonComputer.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonComputer.setLayoutData(griddataButtonComputer);
		GridData griddataButtonPlayers=new GridData();
		griddataButtonPlayers.horizontalAlignment=GridData.CENTER;
		griddataButtonPlayers.heightHint=65;
		griddataButtonPlayers.widthHint=350;
		Button buttonPlayers=new Button(shell, SWT.PUSH);
		buttonPlayers.setText("����� ������ ������");
		buttonPlayers.setFont(buttonFont);
		buttonPlayers.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonPlayers.setLayoutData(griddataButtonPlayers);	
		Listener listener = new Listener() {
		      public void handleEvent(Event event) {
		        if (event.widget == buttonRules){
		        	Rules rules=new Rules(display, shell); 
		        	rules.Show();
		        }
		        else if(event.widget == buttonComputer){
		        	Computer computer=new Computer(display,shell);
		        	computer.Show();
		        }
		        else if(event.widget == buttonPlayers){
		        	Number number=new Number(display,shell,0);
		        	number.Show();
		        }
		      }
		    };
		    buttonRules.addListener(SWT.Selection,listener);
		    buttonComputer.addListener(SWT.Selection,listener);
		    buttonPlayers.addListener(SWT.Selection,listener);
		shell.pack();
		shell.setSize(820, 920);
	}
}