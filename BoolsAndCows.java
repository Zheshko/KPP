import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public class BoolsAndCows {
	public static void main(String[] args) {
		Display display=new Display();
		Shell shell = new Shell(display);
		shell.open();
		Menu menu=new Menu(display,shell);	
		menu.Show();
		while(!shell.isDisposed()){
			 if(!display.readAndDispatch())
			 display.sleep();
			 }
			 display.dispose();
	}	
}
