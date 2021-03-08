package my

import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.kms.katalon.core.configuration.RunConfiguration

/**
 * The original was http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/SWTTreeSimpleDemo.htm
 * 
 * @author kazurayam
 *
 */
public class SwtTreeDemo {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Tree Example");

		final Text text = new Text(shell, SWT.BORDER);
		text.setBounds(0, 270, 290, 25);

		final Tree tree = new Tree(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		tree.setSize(290, 260);
		shell.setSize(300, 330);

		// visit the "MockProfilesTree" folder to construct a SWT Tree view
		Path projectDir = Paths.get(RunConfiguration.getProjectDir())
		Path profilesTree = projectDir.resolve("MockProfilesTree")
		FileVisitor visitor = new ProfilesTreeVisitorImpl(tree)
		Files.walkFileTree(profilesTree, visitor)

		tree.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						if (event.detail == SWT.CHECK) {
							text.setText(event.item + " was checked.");
						} else {
							text.setText(event.item + " was selected");
						}
					}
				});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}

