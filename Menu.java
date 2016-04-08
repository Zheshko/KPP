
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Menu {
	private Shell shell;
	private Display display;
	
	Menu(Display display, Shell shell)
	{
		this.shell=shell;
		this.display=display;
		
	}
	public void Show()
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		Image backgroundImage = new Image(display, "C:/Users/Anastasia/workspace/BoolsAndCows/Images/menu.jpg"); 
		Font buttonFont = new Font( shell.getDisplay(), new FontData( "Times New Roman", 14, SWT.NORMAL ) );
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
		buttonRules.setText("Правила игры");
		buttonRules.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonRules.setLayoutData(griddataButtonRules);
		buttonRules.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Rules rules=new Rules(display, shell);
	                }
	            });
		
		GridData griddataButtonComputer=new GridData();
		griddataButtonComputer.horizontalAlignment=GridData.CENTER;
		griddataButtonComputer.heightHint=65;
		griddataButtonComputer.widthHint=350;
		Button buttonComputer=new Button(shell, SWT.PUSH);
		buttonComputer.setText("Игрок против компьютера");
		buttonComputer.setFont(buttonFont);
		buttonComputer.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		buttonComputer.setLayoutData(griddataButtonComputer);
		buttonComputer.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	  Computer computer=new Computer(display,shell);
	                }
	            });

		GridData griddataButtonPlayers=new GridData();
		griddataButtonPlayers.horizontalAlignment=GridData.CENTER;
		griddataButtonPlayers.heightHint=65;
		griddataButtonPlayers.widthHint=350;
		Button buttonPlayers=new Button(shell, SWT.PUSH);
		buttonPlayers.setText("Игрок против игрока");
		buttonPlayers.setFont(buttonFont);
		buttonPlayers.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonPlayers.setLayoutData(griddataButtonPlayers);
		buttonPlayers.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Number number=new Number(display,shell,0);
	                }
	            });		
		
		shell.pack();
		shell.setSize(820, 920);
	}
}