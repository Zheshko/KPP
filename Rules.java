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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Rules {
	/** ��������, � ������� ���������� ����������� ���������*/
	private Shell shell;
	/** �������, ������� ���������� ��������*/
	private Display display;
	/** �����������, ���������������� ���������� ������
	 *  @param display - �������
	 *  @param shell - �������� ������, � ��� ������������ ���������
	 *  */
	Rules(Display display, Shell shell)
	{
		this.shell=shell;
		this.display=display;
	}
	/**�����, �������, ������� ���������� ���� � ��������� ������*/
	public void Show()
	{
		for (Control kid : shell.getChildren())
		{
			kid.dispose();
		}
		InputStream is=null;
		try {
			is = Files.newInputStream(Paths.get("Images/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image backToMenuImage = new Image(display, is); 
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
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
		text.setText("\t������ �� ������� ���������� ������ �������������� ����� � ����������������"
				+ " �������.\n\n\t�����, ������� �������� ����, ������ ������ ������� �������� "
				+ "�����. ������� � ��� 4-������� ����� � ���������������� �������, ����������"
				+ " ����������. ��������� �������� � �����, ������� ���� ������� ��� ����������"
				+ " � �� ��������� � ������ ����� (�� ���� ���������� �����) � ������� ������� "
				+ "������ �� ������� � ������ ����� (�� ���� ���������� �����).\n\n\t ��������:"
				+ "�������� ������ ����� �3219�.\n\n\t�������: �2310�.\n\n\t���������: ��� ��������"
				+ " (��� �����: �2� � �3� � ������� �� �������� ��������) � ���� ���� (���� �����"
				+ " �1� ������� ������ �� �������).\n\n\t������ ������ ������� ������� �� �������."
				+ " ��������� ���, ��� ������� ����� ������, ��� �������, ��� �� �� ������� ����."
				+ " ���� �� ���������� ������� ���� � ��� ���������� ��������������� ��������� ����"
				+ " ������� ������������������.\n\n\t��� ���� ������ ���������� ����� ������ "
				+ "���������� ���� �� ������, ���� �� �������� ��� ������������������.");
		text.setFont(textFont);
		text.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		text.setLayoutData(griddataText);
		GridData griddataButtonBackToMenu=new GridData();
		griddataButtonBackToMenu.verticalIndent=15;
		griddataButtonBackToMenu.heightHint=60;
		griddataButtonBackToMenu.widthHint=60;
		Button buttonBackToMenu=new Button(shell,SWT.NONE);
		buttonBackToMenu.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonBackToMenu.setImage(backToMenuImage);
		buttonBackToMenu.setLayoutData(griddataButtonBackToMenu);
		buttonBackToMenu.addSelectionListener(
	            new org.eclipse.swt.events.SelectionAdapter() {
	                public void widgetSelected(
	                        org.eclipse.swt.events.SelectionEvent e) {
	                	Menu menu=new Menu(display, shell);
	                	menu.Show();
	                }
	            });
		shell.pack();
		shell.setSize(820, 920);
	}
}
