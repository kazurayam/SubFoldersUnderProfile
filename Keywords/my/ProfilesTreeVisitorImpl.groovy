package my

import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem

public class ProfilesTreeVisitorImpl implements FileVisitor<Path> {
	
	private final Stack<Object> nodes
	
	ProfilesTreeVisitorImpl(Tree tree) {
		Objects.requireNonNull(tree)
		nodes = new Stack<Object>()
		nodes.push(tree)
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		println "preVisit dir ${dir}"
		def parent = nodes.pop()
		TreeItem treeItem = new TreeItem(parent, 0)
		treeItem.setText(dir.getFileName().toString())
		nodes.push(parent)
		nodes.push(treeItem)
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		println "visit file ${file}"
		def parent = nodes.pop()
		TreeItem treeItem = new TreeItem(parent, 0)
		treeItem.setText(file.getFileName().toString())
		nodes.push(parent)
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		println "visit file ${file} failed"
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		println "postVisit dir ${dir}"
		def node = nodes.pop()
		return FileVisitResult.CONTINUE;
	}
}
