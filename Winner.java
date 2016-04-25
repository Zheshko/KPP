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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Winner {
	/** Оболочка, в которой происходит отображение программы */
	private Shell shell;
	/** Дисплей, который использует оболочка*/
	private Display display;
	/**Флаг победы*/
	private int winner;
	/**Режим игры*/
	private int mode;
	/**Конструктор, инициализирующий переменные
	 * @param display- дисплей
	 * @param shell- оболочка экрана, в ней отображается программа
	 * @param winner- флаг победы
	 * @param n- режим игры*/
	Winner(Display display, Shell shell,int winner,int n)
	{
		this.shell=shell;
		this.display=display;
		this.winner=winner;
		this.mode=n;
	}
	/**Метод, отображающий окно и устанавливающий обработку нажатия кнопок*/
	public void Show()
	{
		System.out.println("WinText");
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/number.jpg"));
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
		Font labelFont=new Font( shell.getDisplay(), new FontData( "Arial", 20, SWT.NORMAL ) );
		
		GridLayout mainLayout=new GridLayout();
		mainLayout.numColumns=1;
		shell.setLayout(mainLayout);
		shell.setBackgroundImage(backgroundImage);
		mainLayout.marginLeft=200;
		mainLayout.marginTop=300;
		
		GridData griddataWinnerLabel=new GridData();
		griddataWinnerLabel.heightHint=65;
		griddataWinnerLabel.widthHint=350;
		Label winnerLabel=new Label(shell,SWT.NONE);
		winnerLabel.setFont(labelFont);
		winnerLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));	
		if(mode==1)
		{
			if(winner==1) winnerLabel.setText("Игрок 1 выиграл!");
			if(winner==2) winnerLabel.setText("Игрок 2 выиграл!");
			if(winner==3) winnerLabel.setText("Ничья!");
		}
		else if(mode==5)
		{
			if(winner==1) winnerLabel.setText("Компьютер 1 выиграл!");
			if(winner==2) winnerLabel.setText("Компьютер 2 выиграл!");
			if(winner==3) winnerLabel.setText("Ничья!");
		}
		else
		{
			if(winner==1) winnerLabel.setText("Вы выиграли!");
			if(winner==2) winnerLabel.setText("Компьютер выиграл!");
			if(winner==3) winnerLabel.setText("Ничья!");
		}
		winnerLabel.setAlignment(SWT.CENTER );
		winnerLabel.setLayoutData(griddataWinnerLabel);
		GridData griddataRestartButton=new GridData();
		griddataRestartButton.heightHint=65;
		griddataRestartButton.widthHint=350;
		Button restartButton=new Button(shell, SWT.PUSH);
		restartButton.setText("Рестарт");
		restartButton.setFont(buttonFont);
		restartButton.setLayoutData(griddataRestartButton);
		restartButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		GridData griddataBackToMenuButton=new GridData();
		griddataBackToMenuButton.heightHint=65;
		griddataBackToMenuButton.widthHint=350;
		Button backToMenuButton=new Button(shell, SWT.PUSH);
		backToMenuButton.setText("В главное меню");
		backToMenuButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		backToMenuButton.setFont(buttonFont);
		backToMenuButton.setLayoutData(griddataBackToMenuButton);
		Listener listener = new Listener() {
		      public void handleEvent(Event event) {
		        if (event.widget == restartButton){
		        	if(mode==1)
		        	{
		        		Number number=new Number(display,shell,0);
			        	number.Show();
		        	}
		        	else 
		        	{
		        		Computer computer=new Computer(display,shell);
		        		computer.Show();
		        	}
		        }	
		        else if(event.widget == backToMenuButton){
		        	Menu menu=new Menu(display,shell);
		        	menu.Show();
		        }
		      }
		    };
		restartButton.addListener(SWT.Selection,listener);
		backToMenuButton.addListener(SWT.Selection,listener);
		shell.pack();
		shell.setSize(800, 900);
	}
}
