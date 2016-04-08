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

public class Winner {

	Winner(Display display, Shell shell,int winner)
	{
		System.out.println("WinText");
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		
		Image backgroundImage = new Image(display, "C:/Users/Anastasia/workspace/BoolsAndCows/Images/number.jpg");
		Font buttonFont = new Font( shell.getDisplay(), new FontData( "Times New Roman", 14, SWT.NORMAL ) );
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
		if(winner==1) winnerLabel.setText("Игрок 1 выиграл!");
		if(winner==2) winnerLabel.setText("Игрок 2 выиграл!");
		if(winner==3) winnerLabel.setText("Ничья!");
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
		restartButton.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Computer computer=new Computer(display,shell);
	                }
	            });
		
		GridData griddataBackToMenuButton=new GridData();
		griddataBackToMenuButton.heightHint=65;
		griddataBackToMenuButton.widthHint=350;
		Button backToMenuButton=new Button(shell, SWT.PUSH);
		backToMenuButton.setText("В главное меню");
		backToMenuButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		backToMenuButton.setFont(buttonFont);
		backToMenuButton.setLayoutData(griddataBackToMenuButton);
		backToMenuButton.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Menu menu=new Menu(display,shell);
	                }
	            });
	
		
		shell.pack();
		shell.setSize(800, 900);
	}
}
