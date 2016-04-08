import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Computer {
	
	Computer(Display display, Shell shell)
	{
	for (Control kid : shell.getChildren())
	{
		kid.dispose();
	}
	
	Font buttonFont = new Font( shell.getDisplay(), new FontData( "Times New Roman", 14, SWT.NORMAL ) );
	GridData griddataButtonEasy=new GridData();
	griddataButtonEasy.horizontalAlignment=GridData.CENTER;
	griddataButtonEasy.heightHint=65;
	griddataButtonEasy.widthHint=350;
	Button easyButton=new Button(shell, SWT.PUSH);
	easyButton.setFont(buttonFont);
	easyButton.setText("Легкий");
	easyButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
	easyButton.setLayoutData(griddataButtonEasy);
	easyButton.addSelectionListener(
            new org.eclipse.swt.events.SelectionAdapter() {
                public void widgetSelected(
                        org.eclipse.swt.events.SelectionEvent e) {
                	    Number number=new Number(display,shell,2);
                }
            });
	
	
	
	GridData griddataButtonMedium=new GridData();
	griddataButtonMedium.horizontalAlignment=GridData.CENTER;
	griddataButtonMedium.heightHint=65;
	griddataButtonMedium.widthHint=350;
	Button mediumButton=new Button(shell, SWT.PUSH);
	mediumButton.setText("Средний");
	mediumButton.setFont(buttonFont);
	mediumButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
	mediumButton.setLayoutData(griddataButtonMedium);
	mediumButton.addSelectionListener(
            new org.eclipse.swt.events.SelectionAdapter() {
                public void widgetSelected(
                        org.eclipse.swt.events.SelectionEvent e) {
                	Number number=new Number(display,shell,3);
                }
            });
	
	
	GridData griddataButtonHard=new GridData();
	griddataButtonHard.horizontalAlignment=GridData.CENTER;
	griddataButtonHard.heightHint=65;
	griddataButtonHard.widthHint=350;
	Button hardButton=new Button(shell, SWT.PUSH);
	hardButton.setText("Тяжелый");
	hardButton.setFont(buttonFont);
	hardButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
	hardButton.addSelectionListener(
            new org.eclipse.swt.events.SelectionAdapter() {
                public void widgetSelected(
                        org.eclipse.swt.events.SelectionEvent e) {
                	Number number=new Number(display,shell,4);
                }
            });
	hardButton.setLayoutData(griddataButtonHard);
	shell.pack();
	shell.setSize(820, 920);
}

}