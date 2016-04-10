import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
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

public class ComputerPlay {
	/** ��������, � ������� ���������� ����������� ��������� */
	private Shell shell;
	/** �������, ������� ���������� ��������*/
	private Display display;
	/**���������� ��������� ������� ����*/
	private int digitsInLabel=0;
	/**���������� ����� � �����*/
	private int cows;
	/**���������� ����� � �����*/
	private int bulls;
	/**���� ������(1-�����(��������� 1) �������,2-������� ���������(��������� 2),3-�����)*/
	private int win=0;
	/**����� ����(2-������ ������� ���������,3-������� ������� ���������,4-������� ������� ���������,
	 * 5-�������������� �����)*/
	private int mode;
	/**������ ���� ��������� ����� � ���������������� ������� ������(��� ���������� 1, � ����������� 
	 * �� ������)*/
	private String massiveOfNumbers1[];
	/**������ ���� ��������� ����� � ���������������� ������� ����������(��� ���������� 2, � ����������� 
	 * �� ������)*/
	private String massiveOfNumbers2[];
	/**���������� ����� ������(��� ���������� 1, � ����������� �� ������)*/ 
	private String correctPlayerNumber;
	/**���������� ����� ����������(��� ���������� 2, � ����������� �� ������)*/
	private String correctBotNumber;
	/**����� ��� ���� ������(��� ���������� 1, � ����������� �� ������)*/
	private String botNumber1;
	/**����� ��� ���� ����������(��� ���������� 2, � ����������� �� ������)*/
	private String botNumber2;
	/**����� ��� ����� ������� �����*/
	private Label enteredNumberLabel;
	/**����� ������ ��� ������(��� ���������� 1, � ����������� �� ������) */
	private Label playerLabel;
	/**����� ������ ��� ����������(��� ���������� 2, � ����������� �� ������)*/
	private Label computerLabel;
	/**��������� ����, � ������� ������������ ���������� � ����� ������(��� ���������� 1, � �����������
	 *  �� ������)*/
	private Text playerText;
	/**��������� ����, � ������� ������������ ���������� � ����� ����������(��� ���������� 2, � ����������� 
	 * �� ������)*/
	private Text computerText;
	/**����� ��� ������ ���������� ������*/
	private Font passiveFont;
	/**����� ��� ������ ��������� ������*/
	private Font activeFont;
	/**������� ���� ��� ���������� ����, ��� ���*/
	private Color redColor;
	/**������ ���� ��� ����, ��� ��������� � ���� ���*/
	private Color blackColor;
	/**�����������, ���������������� ���������� � ��������� �������� ������
	 * @param display- �������
	 * @param shell- �������� ������, � ��� ������������ ���������
	 * @param string1- ���������� ����� ������� ������
	 * @param string2- ���������� ����� ������� ������
	 * @param n- ����� ����*/
	public ComputerPlay(Display display, Shell shell, String string1,String string2,int n)
	{
		massiveOfNumbers1=CreateMassive();
		if(n==5) massiveOfNumbers2 = CreateMassive();
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		this.shell=shell;
		this.display=display;
		this.mode=n;
		correctPlayerNumber=string1;
		correctBotNumber=string2;
		playerLabel = new Label(shell, SWT.NONE);
		computerLabel = new Label(shell, SWT.NONE);
		playerText=new Text(shell,SWT.READ_ONLY|SWT.CENTER);
		computerText=new Text(shell,SWT.READ_ONLY|SWT.CENTER);
		passiveFont = new Font( shell.getDisplay(), new FontData( "Arial", 16, SWT.NORMAL ) );
		activeFont=new Font( shell.getDisplay(), new FontData( "Arial", 20, SWT.NORMAL ) );
		redColor= new Color(Display.getCurrent(),255,0,0);
		blackColor= new Color(Display.getCurrent(),0,0,0);
	}
	/**�����, ������������ ���� � ��������������� ��������� ������� ������*/
	public void Show()
	{	
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
		GridData griddataPlayerLabel=new GridData();
		griddataPlayerLabel.horizontalSpan=5;
		griddataPlayerLabel.widthHint=350;
		griddataPlayerLabel.heightHint=40;
		playerLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		if(mode!=5) playerLabel.setText("��");
		else playerLabel.setText("��������� 1");
		playerLabel.setFont(activeFont);
		playerLabel.setAlignment(SWT.CENTER);
		playerLabel.setLayoutData(griddataPlayerLabel);
		GridData griddataComputerLabel=new GridData();
		griddataComputerLabel.horizontalSpan=5;
		griddataComputerLabel.widthHint=350;
		griddataComputerLabel.heightHint=40;
		computerLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		if(mode!=5) computerLabel.setText("���������");
		else computerLabel.setText("��������� 2");
		computerLabel.setFont(passiveFont);
		computerLabel.setAlignment(SWT.CENTER);
		computerLabel.setLayoutData(griddataComputerLabel);
		GridData griddataPlayerText=new GridData();
		griddataPlayerText.horizontalSpan=5;
		griddataPlayerText.heightHint=450;
		griddataPlayerText.widthHint=335;
		playerText.setFont(passiveFont);
		playerText.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		playerText.setLayoutData(griddataPlayerText);
		GridData griddataComputerText=new GridData();
		griddataComputerText.horizontalSpan=5;
		griddataComputerText.heightHint=450;
		griddataComputerText.widthHint=335;
		computerText.setFont(passiveFont);
		computerText.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		computerText.setLayoutData(griddataComputerText);
		enteredNumberLabel=new Label(shell, SWT.NONE);
		enteredNumberLabel.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		enteredNumberLabel.setAlignment(SWT.CENTER | SWT.VERTICAL);
		Font enterLabelFont = new Font( enteredNumberLabel.getDisplay(),
				new FontData( "Arial", 36, SWT.NORMAL ) );
		enteredNumberLabel.setFont(enterLabelFont);
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
		buttonTurn.setText("������� ���");
		buttonTurn.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonTurn.setFont(buttonFont);
		buttonTurn.setLayoutData(griddataButtonTurn);
		buttonTurn.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	if(digitsInLabel==4)
	                	{
	                		cows = 0;
	                		bulls = 0;
	                		compare(enteredNumberLabel.getText().toCharArray(),1);//��������� ��� ������
	                		computerLabel.setFont(activeFont);
	                		playerLabel.setFont(passiveFont);
	                		computerLabel.setForeground(redColor);
	                		playerLabel.setForeground(blackColor);
	                		playerText.append("\n"+enteredNumberLabel.getText()+" "+ bulls+"�. "
	                				+cows+"�.");
	                		if(bulls==4) win++;
	                		enteredNumberLabel.setText("");
	                		digitsInLabel=0;
	                		cows=0;
	                		bulls=0;
							botNumber1=BotNumber(massiveOfNumbers1);//��� ���������� � botNumber
	                		compare(botNumber1.toCharArray(),2);//���-�� ����� � ����� � ���� ����������
	                		if(mode==2)//���� ������� ������
	                		for(int j=0;j<5040;j++)
	                		{
	                			if(massiveOfNumbers1[j]!="0" && !easyBot(massiveOfNumbers1[j]))//���� �� ����� ����� �� ����� 0 � ������� ������� false(�.�. ����� �� ��������)
	                				massiveOfNumbers1[j]="0";//�� ����� �������� 0
	                		}
	                		else if(mode==3)//���� ������� �������(���������� �������)
	                    			for(int j=0;j<5040;j++)
	                    		{
	                    			if(massiveOfNumbers1[j]!="0" 
	                    					&& !middleBot(massiveOfNumbers1[j]))
	                    				massiveOfNumbers1[j]="0";
	                    		}
	                		else if(mode==4)//���� ������� �������(���������� �������) 
	                    		for(int j=0;j<5040;j++)
	                    		{
	                    			if(massiveOfNumbers1[j]!="0" && !hardBot(massiveOfNumbers1[j]))
	                    				massiveOfNumbers1[j]="0";
	                    		}
	                		Runnable timer = new Runnable()
	                		{
								@Override
								public void run() 
								{
									computerText.append("\n"+botNumber1+" "+ bulls+"�. "+cows
											+"�.");
			                		playerLabel.setFont(activeFont);
			                		computerLabel.setFont(passiveFont);
			                		playerLabel.setForeground(redColor);
			                		computerLabel.setForeground(blackColor);
								}
							};
							if(bulls==4) win+=2;
							if(win==0)
								display.timerExec(1000, timer);
	                		if(win>0) 
	                			{
		                			try 
		                			{		   
		    							Thread.sleep(1000);//�������� ������ �� 3 �������(������ ����� ��  ������������ ��� ���������� ���-���)
		    						} catch (InterruptedException e1) 
		                			{
		    							// TODO Auto-generated catch block
		    							e1.printStackTrace();
		    						}
	                				Winner winner= new Winner(display,shell,win,3);
	                				winner.Show();
	                			}
	                	}
	                }
	            });
		if(mode==5)
		{
			button0.setEnabled(false);
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);
			button5.setEnabled(false);
			button6.setEnabled(false);
			button7.setEnabled(false);
			button8.setEnabled(false);
			button9.setEnabled(false);
			buttonDelete.setEnabled(false);
			buttonTurn.setEnabled(false);
		}
		shell.pack();
		shell.setSize(820, 920);
	}
	/**�����, �������������� ������� ������ � �������*/
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
	/**�����, ������������ ���������� ����� ��������� � ����� ������ � ����������� �������������� 
	 * ���������� ����� � �����*/
	private void compare(char[] compareString,int i){
		System.out.println("Compare func");
		char[] correctString = null;
		if(i==1) correctString=correctBotNumber.toCharArray();
		if(i==2) correctString=correctPlayerNumber.toCharArray();
		for(int j=0;j<4;j++)
		{
			for(int k=0;k<4;k++)
			{
			  if(compareString[j]==correctString[k] && j==k) bulls++;
			  if(compareString[j]==correctString[k] && j!=k) cows++;
			}
		}
	}
	/**����� ��� �������, �������� �� ����� �� ������� ���� ��������� ����� � ����������������
	 *  ������� ��� ������� ������ ���������*/
	private boolean easyBot(String masNumber){
		int counter=0,k;
		for(k=0;k<4;k++)
			if(masNumber.toCharArray()[k]==botNumber1.toCharArray()[k]) 
				counter++;
		if(counter!=bulls)
			return false;
		return true;
	}
	/**����� ��� �������, �������� �� ����� �� ������� ���� ��������� ����� � ����������������
	 *������� ��� �������� ������ ���������*/
	private boolean middleBot(String masNumber){
		int counter=0,k;
		for(k=0;k<4;k++)
			if(masNumber.toCharArray()[k]==botNumber1.toCharArray()[k]) 
				counter++;
		if(counter!=bulls)
			return false;
		counter=0;
		for(k=0;k<4;k++)
			if((masNumber.toCharArray()[k]!=botNumber1.toCharArray()[k]) &&
					(masNumber.contains(botNumber1.substring(k, k+1))))
				counter++;
		if(counter<cows)
			return false;
		return true;
	}
	/**����� ��� �������, �������� �� ����� �� ������� ���� ��������� ����� � ����������������
	 *������� ��� �������� ������ ���������*/
	private boolean hardBot(String masNumber){
		int counter=0,k;
		for(k=0;k<4;k++)
			if(masNumber.toCharArray()[k]==botNumber1.toCharArray()[k]) 
				counter++;
		if(counter!=bulls)
			return false;
		counter=0;
		for(k=0;k<4;k++)
			if((masNumber.toCharArray()[k]!=botNumber1.toCharArray()[k]) &&
					(masNumber.contains(botNumber1.substring(k, k+1))))
				counter++;
		if(counter!=cows)
			return false;
		return true;
	}
	/**����� ��� �������, �������� �� ����� �� ������� ���� ��������� ����� � ���������������� �������
	 * ��� ��������������� ������*/
	private boolean hardBot(String masNumber, String botNumber)
	{
		int counter=0,k;
		for(k=0;k<4;k++)
			if(masNumber.toCharArray()[k]==botNumber.toCharArray()[k]) 
				counter++;
		if(counter!=bulls)
			return false;
		counter=0;
		for(k=0;k<4;k++)
			if((masNumber.toCharArray()[k]!=botNumber.toCharArray()[k]) &&
					(masNumber.contains(botNumber.substring(k, k+1))))
				counter++;
		if(counter!=cows)
			return false;
		return true;
	}
	/**�����, � ������� ����������� ���� ����� ����� ������*/
	public int autoBot()
	{	
		botNumber1=BotNumber(massiveOfNumbers1);
		compare(botNumber1.toCharArray(),1);//���-�� ����� � ����� � ���� ����������
		for(int j=0;j<5040;j++)
		{
			if(massiveOfNumbers1[j]!="0" && !hardBot(massiveOfNumbers1[j],botNumber1))
				massiveOfNumbers1[j]="0";
		}
		computerLabel.setFont(activeFont);
		playerLabel.setFont(passiveFont);
		computerLabel.setForeground(redColor);
		playerLabel.setForeground(blackColor);
		playerText.append("\n"+botNumber1+" "+ bulls+"�. "+cows+"�.");
		if(bulls==4) win++;
		cows=0;
		bulls=0;
		try 
		{
			Thread.sleep(500);//�������� ������ �� 3 �������(������ ����� ��  ������������ ��� ���������� ���-���)
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		botNumber2=BotNumber(massiveOfNumbers2);//��� ���������� � botNumber
		compare(botNumber2.toCharArray(),2);//���-�� ����� � ����� � ���� ����������
		computerText.append("\n"+botNumber2+" "+ bulls+"�. "+cows+"�.");
		for(int j=0;j<5040;j++)
		{
			if(massiveOfNumbers2[j]!="0" && !hardBot(massiveOfNumbers2[j], botNumber2))
				massiveOfNumbers2[j]="0";
		}
		playerLabel.setFont(activeFont);
		computerLabel.setFont(passiveFont);
		playerLabel.setForeground(redColor);
		computerLabel.setForeground(blackColor);
		if(bulls==4) win+=2;  
		if(win==0) 	
		{
			cows=0;
			bulls=0;
		}
		else if(win>0)
		{
			try 
			{
				Thread.sleep(1000);//�������� ������ �� 3 �������(������ ����� ��  ������������ ��� ���������� ���-���)
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return win;
	}
	/**����� �������� ������� ���� ��������� �������������� ����� � ���������������� ������� */
	private String[] CreateMassive()
	{
		String []mas = new String[5040];
		int number[], temp, j=0;
		number = new int[4];
		for(int i=0; i<10000; i++)
		{
			temp=i;
			number[0]=temp%10;
			temp/=10;
			number[1]=temp%10;
			temp/=10;
			number[2]=temp%10;
			temp/=10;
			number[3]=temp%10;
			if(number[0]!=number[1] && number[0]!=number[2] && number[0]!=number[3] &&
					number[1]!=number[2] && number[1]!=number[3] && number[2]!=number[3])
			{
				mas[j]=String.valueOf(number[3])+String.valueOf(number[2])+
						String.valueOf(number[1])+String.valueOf(number[0]);
				j++;
			}
		}
		return mas;
	}
	/**����� ������������ ��� ����������(����� ���������� ����� �� �������)*/
	private String BotNumber(String[] mas)
	{
		Random random = new Random();
		int chosen;
		System.out.println("In random func");
		while(true)
		{
			chosen=random.nextInt(5040);
			System.out.println(mas[chosen]);
			if(mas[chosen]!="0")
				{
					System.out.println("BotNubmer func");
					return mas[chosen];
				}
		}
	}
}
