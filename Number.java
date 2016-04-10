import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Number {
	/**Метка вводимого игроком числа для загадывания*/
	private Label enteredNumberLabel;
	/**Строка для загаданного числа первого игрока(или просто игрока, или первого компьютера)*/
	private static String string1;
	/**Строка для загаданного числа второго игрока(или просто компьютера, или второго компьютера)*/
	private String string2;
	/**Количество цифр в enteredNumberLabel*/
	private int digitsInLabel=0;
	/**Режим игры(0-создание числа первым игроком,1- создание числа вторым игроком,
	 *  2- игрок против компьютера(легкий уровень),3- игрок против компьютера(средний уровень),
	 *  4-игрок против компьютера(тяжелый уровень)*/
	private int mode;
	/**Оболочка, в которой отображается программа*/
	private Shell shell;
	/**Дисплей, который использует оболочка*/
	private Display display;
	/**Конструктор, инициализирующий переменные
	 * @param display- дисплей
	 * @param shell- оболочка экрана, в ней отображается программа
	 * @param i- режим игры*/
	public Number(Display display,Shell shell,int i)
	{			
			for (Control kid : shell.getChildren())
			{
				kid.dispose();
			}
			this.shell=shell;
			this.display=display;
			this.mode=i;
	}
	/**Метод, который отображает окно и устанавливает обработчики нажатия кнопок*/
	public void Show()
	{
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/back2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image backToMenuImage = new Image(display, is);
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
		GridLayout mainLayout=new GridLayout();
		mainLayout.numColumns=5;
		shell.setLayout(mainLayout);
		shell.setBackgroundImage(backgroundImage);
		mainLayout.marginLeft=180;
		mainLayout.marginTop=170;
		Font boldFont3 = new Font( shell.getDisplay(), new FontData( "Arial", 12, SWT.NORMAL ) );
		GridData griddataTextLabel=new GridData();
		griddataTextLabel.horizontalSpan=5;
		griddataTextLabel.horizontalAlignment=GridData.END;
		griddataTextLabel.heightHint=100;
		griddataTextLabel.widthHint=200;
		Label textLabel=new Label(shell, SWT.NONE);
		textLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	    if(mode==0) 
	    	textLabel.setText("Загадайте число, Игрок 1");
	    else if(mode==1) 
	    	textLabel.setText("Загадайте число, Игрок 2");
	    else 
	    	textLabel.setText("Загадайте число");
		Font boldFont = new Font( shell.getDisplay(), new FontData( "Arial", 14, SWT.BOLD ) );
		textLabel.setFont( boldFont );
		textLabel.setAlignment(SWT.CENTER);
		textLabel.setLayoutData(griddataTextLabel);
		enteredNumberLabel=new Label(shell, SWT.NONE);
		enteredNumberLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		Font boldFont1 = new Font( enteredNumberLabel.getDisplay(), 
				new FontData( "Arial", 40, SWT.NORMAL ) );
		enteredNumberLabel.setAlignment(SWT.CENTER | SWT.VERTICAL);
		enteredNumberLabel.setFont(boldFont1);
		GridData griddataEnteredNumberLabel=new GridData();
		griddataEnteredNumberLabel.heightHint=80;
		griddataEnteredNumberLabel.widthHint=340;
		griddataEnteredNumberLabel.horizontalSpan=4;
		enteredNumberLabel.setLayoutData(griddataEnteredNumberLabel);
		GridData griddataButtonDelete=new GridData();
		griddataButtonDelete.horizontalAlignment=GridData.CENTER;
		griddataButtonDelete.heightHint=80;
		griddataButtonDelete.widthHint=80;
		Button buttonDelete = new Button(shell, SWT.PUSH);
		buttonDelete.setText("C");
		buttonDelete.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonDelete.setFont(boldFont3);
		buttonDelete.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	if(digitsInLabel>0)
	                    {enteredNumberLabel.setText(enteredNumberLabel.getText().substring(0,
	                    		enteredNumberLabel.getText().length()-1));
	                    digitsInLabel--;
	                    }
	                }
	            });
		buttonDelete.setLayoutData(griddataButtonDelete);
		GridData griddataButton0=new GridData();
		griddataButton0.horizontalAlignment=GridData.CENTER;
		griddataButton0.heightHint=80;
		griddataButton0.widthHint=80;
		Button button0 = new Button(shell,SWT.PUSH);
		button0.setText("0");
		button0.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button0.setFont(boldFont3);
		button0.setLayoutData(griddataButton0);
		buttonPressed(button0);
		GridData griddataButton1=new GridData();
		griddataButton1.horizontalAlignment=GridData.CENTER;
		griddataButton1.heightHint=80;
		griddataButton1.widthHint=80;
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("1");
		button1.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button1.setFont(boldFont3);
		button1.setLayoutData(griddataButton1);
		buttonPressed(button1);
		GridData griddataButton2=new GridData();
		griddataButton2.horizontalAlignment=GridData.CENTER;
		griddataButton2.heightHint=80;
		griddataButton2.widthHint=80;
		Button button2 = new Button(shell,SWT.PUSH);
		button2.setText("2");
		button2.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button2.setFont(boldFont3);
		button2.setLayoutData(griddataButton2);
		buttonPressed(button2);
		GridData griddataButton3=new GridData();
		griddataButton3.horizontalAlignment=GridData.CENTER;
		griddataButton3.heightHint=80;
		griddataButton3.widthHint=80;
		Button button3 = new Button(shell,SWT.PUSH);
		button3.setText("3");
		button3.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button3.setFont(boldFont3);
		button3.setLayoutData(griddataButton3);
		buttonPressed(button3);
		GridData griddataButton4=new GridData();
		griddataButton4.horizontalAlignment=GridData.CENTER;
		griddataButton4.heightHint=80;
		griddataButton4.widthHint=80;
		Button button4 = new Button(shell,SWT.PUSH);
		button4.setText("4");
		button4.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button4.setFont(boldFont3);
		button4.setLayoutData(griddataButton4);
		buttonPressed(button4);
		GridData griddataButton5=new GridData();
		griddataButton5.horizontalAlignment=GridData.CENTER;
		griddataButton5.heightHint=80;
		griddataButton5.widthHint=80;
		Button button5 = new Button(shell,SWT.PUSH);
		button5.setText("5");
		button5.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button5.setFont(boldFont3);
		button5.setLayoutData(griddataButton5);
		buttonPressed(button5);
		GridData griddataButton6=new GridData();
		griddataButton6.horizontalAlignment=GridData.CENTER;
		griddataButton6.heightHint=80;
		griddataButton6.widthHint=80;
		Button button6= new Button(shell,SWT.PUSH);
		button6.setText("6");
		button6.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button6.setFont(boldFont3);
		button6.setLayoutData(griddataButton6);
		buttonPressed(button6);
		GridData griddataButton7=new GridData();
		griddataButton7.horizontalAlignment=GridData.CENTER;
		griddataButton7.heightHint=80;
		griddataButton7.widthHint=80;
		Button button7 = new Button(shell,SWT.PUSH);
		button7.setText("7");
		button7.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button7.setFont(boldFont3);
		button7.setLayoutData(griddataButton7);
		buttonPressed(button7);
		GridData griddataButton8=new GridData();
		griddataButton8.horizontalAlignment=GridData.CENTER;
		griddataButton8.heightHint=80;
		griddataButton8.widthHint=80;
		Button button8 = new Button(shell,SWT.PUSH);
		button8.setText("8");
		button8.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button8.setFont(boldFont3);
		button8.setLayoutData(griddataButton8);
		buttonPressed(button8);
		GridData griddataButton9=new GridData();
		griddataButton9.horizontalAlignment=GridData.CENTER;
		griddataButton9.heightHint=80;
		griddataButton9.widthHint=80;
		Button button9 = new Button(shell,SWT.PUSH);
		button9.setText("9");
		button9.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button9.setFont(boldFont3);
		button9.setLayoutData(griddataButton9);
		buttonPressed(button9);
		GridData griddataButtonOkey=new GridData();
		griddataButtonOkey.heightHint=80;
		griddataButtonOkey.widthHint=420;
		griddataButtonOkey.horizontalSpan=5;
		griddataButtonOkey.horizontalAlignment=GridData.CENTER;
		Button buttonOkey = new Button(shell,SWT.PUSH);
		buttonOkey.setText("Загадать");
		buttonOkey.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonOkey.setFont(boldFont3);
		buttonOkey.setLayoutData(griddataButtonOkey);
		buttonOkey.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	if(digitsInLabel==4) {
	                		if(mode==0)
	                		{
	                			string1=enteredNumberLabel.getText();
	                			digitsInLabel=0;Number number=new Number(display,shell,1);
	                			number.Show();
	                		}
	                		else if(mode==1)
	                		{
	                			string2=enteredNumberLabel.getText();
	                			digitsInLabel=0;Players players=new Players(display,shell,
	                					string1,string2);
	                			players.Show();
	                		}
	                		else 
	                		{
	                			string1=enteredNumberLabel.getText();
	                			string2=CreateBotNumber();digitsInLabel=0;
	                			ComputerPlay computer=new ComputerPlay(display,shell,string1,
	                					string2,mode);
	                			computer.Show();}
	                		}
	                }
	            });
		GridData griddataButtonBackToMenu=new GridData();
		griddataButtonBackToMenu.verticalIndent=15;
		griddataButtonBackToMenu.heightHint=80;
		griddataButtonBackToMenu.widthHint=80;
		Button backToMenu=new Button(shell,SWT.NONE);
		backToMenu.setImage(backToMenuImage);
		backToMenu.setLayoutData(griddataButtonBackToMenu);
		backToMenu.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	digitsInLabel=0;
	                	Menu menu=new Menu(display, shell);
	                	menu.Show();
	                }
	            });
		shell.pack();
		shell.setSize(820, 920);	
	}	
	/**Метод, обрабатывающий нажатие клавиш с цифрами*/
	private void buttonPressed(Button btn){
		btn.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	char [] number=enteredNumberLabel.getText().toCharArray();
	                	for(int i=0;i<digitsInLabel;i++)
	                	{
	                		if(btn.getText().toCharArray()[0]==number[i]) return ;
	                	}
	                	if(digitsInLabel<4)
	                    {
	                		enteredNumberLabel.setText(enteredNumberLabel.getText()+btn.getText());
	                		digitsInLabel++;
	                    }
	                }
	            });
	}
	/**Метод, генерирующий загаданное компьютером число*/
	public static String CreateBotNumber(){
		String botNumber="";
		CharSequence digit="";
		int i=0;
		Random random=new Random();
		do
		{
			digit=String.valueOf(random.nextInt(10));
			if(botNumber.contains(digit)==false) { botNumber+=String.valueOf(digit);i++;}
		} while(i<4);
		return botNumber;
	}

}
