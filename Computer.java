import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Computer {
	/** Оболочка, в которой происходит отображение программы */
	private Shell shell;
	/** Дисплей, который использует оболочка*/
	private Display display;
	/**Строка, в кторой хранится число, загаданное игроком(или компьютером 1)*/
	private String string1;
	/**Строка, в кторой хранится число, загаданное компьютером(или компьютером 2)*/
	private String string2;
	/** Конструктор, инициализирующий переменные
	 * @param display - дисплей
	 * @param shell - оболочка экрана, в ней отображается программа
	 */
	public Computer(Display display, Shell shell)
	{
		this.shell=shell;
		this.display=display;
	}
	/**Метод, отображающий окно и устанавливающий обработку нажатия кнопок*/
	public void Show()
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		Font buttonFont = new Font( shell.getDisplay(), 
				new FontData( "Times New Roman", 14, SWT.NORMAL) );
		GridData griddataButtonEasy=new GridData();
		griddataButtonEasy.horizontalAlignment=GridData.CENTER;
		griddataButtonEasy.heightHint=65;
		griddataButtonEasy.widthHint=350;
		Button easyButton=new Button(shell, SWT.PUSH);
		easyButton.setFont(buttonFont);
		easyButton.setText("Легкий");
		easyButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		easyButton.setLayoutData(griddataButtonEasy);
		GridData griddataButtonMedium=new GridData();
		griddataButtonMedium.horizontalAlignment=GridData.CENTER;
		griddataButtonMedium.heightHint=65;
		griddataButtonMedium.widthHint=350;
		Button mediumButton=new Button(shell, SWT.PUSH);
		mediumButton.setText("Средний");
		mediumButton.setFont(buttonFont);
		mediumButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		mediumButton.setLayoutData(griddataButtonMedium);
		GridData griddataButtonHard=new GridData();
		griddataButtonHard.horizontalAlignment=GridData.CENTER;
		griddataButtonHard.heightHint=65;
		griddataButtonHard.widthHint=350;
		Button hardButton=new Button(shell, SWT.PUSH);
		hardButton.setText("Тяжелый");
		hardButton.setFont(buttonFont);
		hardButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		hardButton.setLayoutData(griddataButtonHard);
		GridData griddataButtonAuto=new GridData();
		griddataButtonAuto.horizontalAlignment=GridData.CENTER;
		griddataButtonAuto.heightHint=65;
		griddataButtonAuto.widthHint=350;
		Button autoButton=new Button(shell, SWT.PUSH);
		autoButton.setText("Автоматичексий");
		autoButton.setFont(buttonFont);
		autoButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		autoButton.setLayoutData(griddataButtonAuto);
		Listener listener = new Listener() {
		      public void handleEvent(Event event) {
		        if (event.widget == easyButton){
		        	Number number=new Number(display,shell,2);
	            	number.Show();
		        }
		        else if(event.widget == mediumButton){
		        	Number number=new Number(display,shell,3);
	            	number.Show();
		        }
		        else if(event.widget == hardButton){
		        	Number number=new Number(display,shell,4);
	            	number.Show();
		        }
		        else if(event.widget==autoButton){
		        	string1=Number.CreateBotNumber();
		        	string2=Number.CreateBotNumber();
		        	ComputerPlay computerPlay=new ComputerPlay(display,shell,string1,string2,5);
		        	computerPlay.Show();
		        	Runnable timer = new Runnable() {	
						@Override
						public void run() 
						{
							int win=computerPlay.autoBot();
							if(win==0)
								display.timerExec(500, this);
							else 
							{
								Winner winner = new Winner(display, shell, win,2);
								winner.Show();
							}
						}
					};	
					display.timerExec(500, timer);
		        }
		      }
		    };  
		easyButton.addListener(SWT.Selection,listener);
		mediumButton.addListener(SWT.Selection,listener);
		hardButton.addListener(SWT.Selection, listener);
		autoButton.addListener(SWT.Selection,listener);
		shell.pack();
		shell.setSize(820, 920);
	}
}