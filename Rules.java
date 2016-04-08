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
import org.eclipse.swt.widgets.Text;

public class Rules {
	Rules(Display display, Shell shell)
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		
		Image backgroundImage = new Image(display, "C:/Users/Anastasia/workspace/BoolsAndCows/Images/back.png");
		
		GridLayout mainLayout=new GridLayout();
		mainLayout.marginTop=30;
		mainLayout.marginLeft=30;
		mainLayout.numColumns=1;
		shell.setLayout(mainLayout);
		Font textFont = new Font( shell.getDisplay(), new FontData( "Arial", 11, SWT.NORMAL ) );
		
		GridData griddataText=new GridData();
		griddataText.heightHint=700;
		griddataText.widthHint=700;
		Text text=new Text(shell,SWT.READ_ONLY|SWT.BORDER|SWT.WRAP);
		text.setText("������ �� ������� ���������� � ���������� ������ �������������� ����� � �����n����������� �������. �����, ������� �������� ���� �� ������, ������ ������ ������� �������� �����. ������� � ��� 4-������� ����� � ���������������� �������, ���������� ����������. ��������� �������� � �����, ������� ���� ������� ��� ���������� � �� ��������� � ������ ����� (�� ���� ���������� �����) � ������� ������� ������ �� ������� � ������ ����� (�� ���� ���������� �����). ��������:�������� ������ ����� �3219�.�������: �2310�.���������: ��� �������� (��� �����: �2� � �3� � ������� �� �������� ��������) � ���� ���� (���� ����� �1� ������� ������ �� �������).������ ������ ������� ������� �� �������. ��������� ���, ��� ������� ����� ������, ��� �������, ��� �� �� ������� ����. ���� �� ���������� ������� ���� � ��� ���������� ��������������� ��������� ���� ������� ������������������.��� ���� ������ ���������� ����� ������ ���������� ���� �� ������, ���� �� �������� ��� ������������������.");
		text.setFont(textFont);
		text.setLayoutData(griddataText);
		
		GridData griddataButtonBackToMenu=new GridData();
		griddataButtonBackToMenu.verticalIndent=15;
		griddataButtonBackToMenu.heightHint=60;
		griddataButtonBackToMenu.widthHint=60;
		Button buttonBackToMenu=new Button(shell,SWT.NONE);
		buttonBackToMenu.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonBackToMenu.setImage(backgroundImage);
		buttonBackToMenu.setLayoutData(griddataButtonBackToMenu);
		buttonBackToMenu.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Menu menu=new Menu(display, shell);
	                }
	            });

		shell.pack();
		shell.setSize(820, 920);
	}
}
