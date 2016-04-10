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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Players {
	private Label enteredNumberLabel;
	private Label playerLabel1,playerLabel2;
	private Text playerText1,playerText2;
	private Font passivePlayerFont,activePlayerFont;
	private int digitsInLabel=0,cows,bulls;//кол-во введенных цифр, кол-во коров,кол-во быков
	private int playerTurn=1,win;//ход игрока(1-первый ходит,2-второй),флаг победы
	private String correctNumber1;//загаданное число игрока 1
	private String correctNumber2;//загаданное число игрока 2
	private Shell shell;
	private Display display;
	
	public Players(Display display, Shell shell,String string1,String string2)
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		this.shell=shell;
		this.display=display;
		correctNumber1=string1;
		correctNumber2=string2;	
		playerLabel1 = new Label(shell, SWT.NONE);
		playerLabel2 = new Label(shell, SWT.NONE);
		playerText1=new Text(shell,SWT.READ_ONLY|SWT.CENTER|SWT.V_SCROLL);
		playerText2=new Text(shell,SWT.READ_ONLY|SWT.CENTER|SWT.V_SCROLL);
		passivePlayerFont = new Font( shell.getDisplay(), new FontData( "Arial", 16, SWT.NORMAL ) );
		activePlayerFont=new Font( shell.getDisplay(), new FontData( "Arial", 20, SWT.NORMAL ) );
	}

	public void Show(){
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/players.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image backgroundImage = new Image(display, is); 
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Font buttonFont = new Font( shell.getDisplay(), new FontData( "Arial", 12, SWT.NORMAL ) );
		
		GridLayout mainLayout=new GridLayout();
		mainLayout.numColumns=10;
		shell.setLayout(mainLayout);
		shell.setBackgroundImage(backgroundImage);
		mainLayout.marginLeft=35;
		mainLayout.marginTop=20;
		
		
		GridData griddataPlayerLabel1=new GridData();
		griddataPlayerLabel1.horizontalSpan=5;
		griddataPlayerLabel1.widthHint=350;
		griddataPlayerLabel1.heightHint=40;
		
		playerLabel1.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		playerLabel1.setText("Игрок 1");
		playerLabel1.setFont(activePlayerFont);
		playerLabel1.setAlignment(SWT.CENTER);
		playerLabel1.setLayoutData(griddataPlayerLabel1);
		
		GridData griddataPlayerLabel2=new GridData();
		griddataPlayerLabel2.horizontalSpan=5;
		griddataPlayerLabel2.widthHint=350;
		griddataPlayerLabel2.heightHint=40;
		playerLabel2.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerLabel2.setText("Игрок 2");
		playerLabel2.setFont(passivePlayerFont);
		playerLabel2.setAlignment(SWT.CENTER);
		playerLabel2.setLayoutData(griddataPlayerLabel2);
		
		GridData griddataPlayerText1=new GridData();
		griddataPlayerText1.horizontalSpan=5;
		griddataPlayerText1.heightHint=450;
		griddataPlayerText1.widthHint=315;
		
		playerText1.setFont(passivePlayerFont);
		playerText1.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerText1.setLayoutData(griddataPlayerText1);
		
		GridData griddataPlayerText2=new GridData();
		griddataPlayerText2.horizontalSpan=5;
		griddataPlayerText2.heightHint=450;
		griddataPlayerText2.widthHint=315;
		
		//Label playerLabel = new Label(player, SWT.NONE);
		playerText2.setFont(passivePlayerFont);
		playerText2.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerText2.setLayoutData(griddataPlayerText2);
		enteredNumberLabel=new Label(shell, SWT.NONE);
		enteredNumberLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		Font boldFont2 = new Font( enteredNumberLabel.getDisplay(), new FontData( "Arial", 36, SWT.NORMAL ) );
		enteredNumberLabel.setAlignment(SWT.CENTER | SWT.VERTICAL);
		enteredNumberLabel.setFont(boldFont2);
		GridData griddataEnteredNumberLabel=new GridData();
		griddataEnteredNumberLabel.verticalIndent=15;
		griddataEnteredNumberLabel.heightHint=60;
		griddataEnteredNumberLabel.widthHint=640;
		griddataEnteredNumberLabel.horizontalSpan=9;
		enteredNumberLabel.setLayoutData(griddataEnteredNumberLabel);
		
		GridData griddataButtonDelete=new GridData();
		griddataButtonDelete.horizontalAlignment=GridData.FILL;
		griddataButtonDelete.heightHint=60;
		griddataButtonDelete.widthHint=60;
		griddataButtonDelete.verticalIndent=15;
		Button buttonDelete = new Button(shell, SWT.PUSH);
		buttonDelete.setText("C");
		buttonDelete.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonDelete.setLayoutData(griddataButtonDelete);
		buttonDelete.setFont(buttonFont);
		buttonDelete.addSelectionListener(  //удаление последней цифры из enteredNumberLabel
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	if(digitsInLabel>0)
	                    {
	                		enteredNumberLabel.setText(enteredNumberLabel.getText().substring(0, enteredNumberLabel.getText().length()-1));
	                		digitsInLabel--;
	                    }
	                }
	            });
		
		GridData griddataButton0=new GridData();
		griddataButton0.horizontalAlignment=GridData.FILL;
		griddataButton0.heightHint=60;
		griddataButton0.widthHint=67;
		Button button0 = new Button(shell,SWT.PUSH);
		button0.setText("0");
		button0.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button0.setFont(buttonFont);
		button0.setLayoutData(griddataButton0);
		buttonPressed(button0);
		
		GridData griddataButton1=new GridData();
		griddataButton1.horizontalAlignment=GridData.FILL;
		griddataButton1.heightHint=60;
		griddataButton1.widthHint=67;
		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("1");
		button1.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button1.setFont(buttonFont);
		button1.setLayoutData(griddataButton1);
		buttonPressed(button1);
		
		GridData griddataButton2=new GridData();
		griddataButton2.horizontalAlignment=GridData.FILL;
		griddataButton2.heightHint=60;
		griddataButton2.widthHint=67;
		Button button2 = new Button(shell,SWT.PUSH);
		button2.setText("2");
		button2.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button2.setFont(buttonFont);
		button2.setLayoutData(griddataButton2);
		buttonPressed(button2);
		
		GridData griddataButton3=new GridData();
		griddataButton3.horizontalAlignment=GridData.FILL;
		griddataButton3.heightHint=60;
		griddataButton3.widthHint=67;
		Button button3 = new Button(shell,SWT.PUSH);
		button3.setText("3");
		button3.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button3.setFont(buttonFont);
		button3.setLayoutData(griddataButton3);
		buttonPressed(button3);
		
		GridData griddataButton4=new GridData();
		griddataButton4.horizontalAlignment=GridData.FILL;
		griddataButton4.heightHint=60;
		griddataButton4.widthHint=67;
		Button button4 = new Button(shell,SWT.PUSH);
		button4.setText("4");
		button4.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button4.setFont(buttonFont);
		button4.setLayoutData(griddataButton4);
		buttonPressed(button4);
		
		GridData griddataButton5=new GridData();
		griddataButton5.horizontalAlignment=GridData.FILL;
		griddataButton5.heightHint=60;
		griddataButton5.widthHint=67;
		Button button5 = new Button(shell,SWT.PUSH);
		button5.setText("5");
		button5.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button5.setFont(buttonFont);
		button5.setLayoutData(griddataButton5);
		buttonPressed(button5);
		
		GridData griddataButton6=new GridData();
		griddataButton6.horizontalAlignment=GridData.FILL;
		griddataButton6.heightHint=60;
		griddataButton6.widthHint=67;
		Button button6= new Button(shell,SWT.PUSH);
		button6.setText("6");
		button6.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button6.setFont(buttonFont);
		button6.setLayoutData(griddataButton6);
		buttonPressed(button6);
		
		GridData griddataButton7=new GridData();
		griddataButton7.horizontalAlignment=GridData.FILL;
		griddataButton7.heightHint=60;
		griddataButton7.widthHint=67;
		Button button7 = new Button(shell,SWT.PUSH);
		button7.setText("7");
		button7.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button7.setFont(buttonFont);
		button7.setLayoutData(griddataButton7);
		buttonPressed(button7);
		
		GridData griddataButton8=new GridData();
		griddataButton8.horizontalAlignment=GridData.FILL;
		griddataButton8.heightHint=60;
		griddataButton8.widthHint=67;
		Button button8 = new Button(shell,SWT.PUSH);
		button8.setText("8");
		button8.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button8.setFont(buttonFont);
		button8.setLayoutData(griddataButton8);
		buttonPressed(button8);
		
		GridData griddataButton9=new GridData();
		griddataButton9.horizontalAlignment=GridData.FILL;
		griddataButton9.heightHint=60;
		griddataButton9.widthHint=67;
		Button button9 = new Button(shell,SWT.PUSH);
		button9.setText("9");
		button9.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		button9.setFont(buttonFont);
		button9.setLayoutData(griddataButton9);
		buttonPressed(button9);
		
		GridData griddataButtonTurn=new GridData();
		griddataButtonTurn.verticalIndent=40;
		griddataButtonTurn.heightHint=60;
		griddataButtonTurn.widthHint=285;
		griddataButtonTurn.horizontalSpan=10;
		griddataButtonTurn.horizontalAlignment=GridData.CENTER;
		Button buttonTurn = new Button(shell,SWT.PUSH);
		buttonTurn.setText("Сделать ход");
		buttonTurn.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonTurn.setFont(buttonFont);
		buttonTurn.setLayoutData(griddataButtonTurn);
		buttonTurn.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	if(digitsInLabel==4) MakeTurn();//если кол-во введенных в enteredNumberLabel=4
	                	  //определение хода игрока            		
	                }
	            });
		
		shell.pack();
		shell.setSize(820, 920);
	}
	private void buttonPressed(Button btn){//обработка нажатой кнопки цифры
		btn.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	char [] number=enteredNumberLabel.getText().toCharArray();
	                	for(int i=0;i<digitsInLabel;i++)
	                	{
	                		if(btn.getText().toCharArray()[0]==number[i]) return ;//выход если такая цифра уже есть в enteredNumberLabel
	                	}
	                	if(digitsInLabel<4)
	                    {
	                		enteredNumberLabel.setText(enteredNumberLabel.getText()+btn.getText()); //считывается текст кнопки и добавляется в enteredNumberLabel
	                		digitsInLabel++;//увеличивается кол-во цифр, находящихся в enteredNumberLabel
	                    }
	                }
	            });
	}
	private void compare(char[] compareString){//сравнение хода игрока с загаданным числом
		char[] correctString = null;
		System.out.println(correctNumber1+correctNumber2);
		if(playerTurn==1) correctString=correctNumber2.toCharArray();
		else if(playerTurn==2) correctString=correctNumber1.toCharArray();
		for(int j=0;j<4;j++)
		{
			for(int k=0;k<4;k++)
			{
			  if(compareString[j]==correctString[k] && j==k) bulls++;//цифра в загаданном числе есть и стоит на своем месте-быки
			  if(compareString[j]==correctString[k] && j!=k) cows++;//цифра в загаданном числе есть,но стоит не на своем месте-коровы
			}
		}
	}	
	private void MakeTurn()
	{
		compare(enteredNumberLabel.getText().toCharArray());
		if(playerTurn==1)
		{
			//определение кол-ва быков и коров у 1ого игрока
			playerLabel2.setFont(activePlayerFont);
			playerLabel1.setFont(passivePlayerFont);
			playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
			playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			playerText1.append("\n"+enteredNumberLabel.getText()+" "+ bulls+"б. "+cows+"к.");
			if(bulls==4) win++;//если угадал 4 цифры увеличивается win
			enteredNumberLabel.setText("");
			digitsInLabel=0;
			cows=0;
			bulls=0;
			playerTurn=2;//передача хода 2ому игроку
		}
		else//для 2ого аналогично
		{
			//compare(enteredNumberLabel.getText().toCharArray());
			playerLabel1.setFont(activePlayerFont);
			playerLabel2.setFont(passivePlayerFont);
			playerLabel1.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
			playerLabel2.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			playerText2.append("\n"+enteredNumberLabel.getText()+" "+ bulls+"б. "+cows+"к.");
			if(bulls==4) win+=2;  
			
			if(win>0) //если хотя бы 1 из игроков угадал число
				{
					Winner winner=new Winner(display,shell,win,1);
					winner.Show();
				}
			
			else{
    			enteredNumberLabel.setText("");
    			digitsInLabel=0;
    			cows=0;
    			bulls=0;
    			playerTurn=1;//передача хода 1ому игроку
				}
		}
	}
}
