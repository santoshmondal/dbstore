package common.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.List;

public class HelloNio {
	public static final String WORKING_PATH = "/home/santoshm/Desktop";
	
	public static void main(String[] args) throws Exception {
		watchServiceDemo();
	}

	public static void watchServiceDemo() throws IOException,
			InterruptedException {
		Path path = FileSystems.getDefault().getPath(WORKING_PATH);
				
		WatchService service = path.getFileSystem().newWatchService();		
		path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
		
		WatchKey key = null;
		while(true) {
			key = service.take();

			// Dequeueing events
			Kind<?> kind = null;
			for(WatchEvent<?> watchEvent : key.pollEvents()) {
				kind = watchEvent.kind();
				if (StandardWatchEventKinds.OVERFLOW == kind) {
					continue; //loop
				} else if (StandardWatchEventKinds.ENTRY_CREATE == kind) {
					Path newPath = ((WatchEvent<Path>) watchEvent).context();
					System.out.println("New path created: " + newPath + "::" + newPath.toAbsolutePath());
				} else if(StandardWatchEventKinds.ENTRY_DELETE == kind) {
					Path newPath = ((WatchEvent<Path>) watchEvent).context();
					System.out.println("Path Deleted: " + newPath + "::" + newPath.toAbsolutePath());
				}
			}
			
			if(!key.reset()) {
				break; //loop
			}
		}
	}

	public static void fileBasicAttributes() throws IOException {
		Path path = FileSystems.getDefault().getPath(WORKING_PATH);
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		FileTime creationTime = attributes.creationTime();
		FileTime lastAccessTime = attributes.lastAccessTime();
		FileTime lastModifiedTime = attributes.lastModifiedTime();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(creationTime.toMillis());
		System.out.println("CREATION-Date/Month/Year " + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR));
		
		cal.setTimeInMillis(lastAccessTime.toMillis());
		System.out.println("LASTACCESS-Date/Month/Year " + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR));
		
		cal.setTimeInMillis(lastModifiedTime.toMillis());
		System.out.println("LASTMODIFIED-Date/Month/Year " + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR));
	}

	public static void iterateRecursively() throws IOException {
		Path path = FileSystems.getDefault().getPath("/home/santoshm/Desktop");
		
		Files.walkFileTree(path, new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println(file.getFileName() + " :: " + file.toAbsolutePath());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
	}

	public static void itermateDirectory() throws IOException {
		Path path = FileSystems.getDefault().getPath("/home/santoshm/Desktop");
		if(Files.isDirectory(path)) {
			DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path);
			for(Path path2 : newDirectoryStream) {
				if(!Files.isDirectory(path2)) {
					System.out.println(path2.getFileName() + "\t" + path2.toAbsolutePath());
				}
			}
			
		} else {
			System.out.println("ITS FILE NOT DIRECTORY!!");
		}
	}

	public static void demo1() throws IOException {
		Path path = Paths.get("/home/santoshm/Desktop/rule.txt");
		
		System.out.println("IS_ABSOLUTE :: " + path.isAbsolute());
		System.out.println("FILENAME :: " + path.getFileName());
		System.out.println("PARENT :: " + path.getParent());
		System.out.println("ROOT :: " + path.getRoot());
		System.out.println("ABSOLUTE PATH :: " + path.toAbsolutePath());
		System.out.println("NORMALIZE :: " + path.normalize());
		
		System.out.println("NAME_COUNT :: " + path.getNameCount() + " :: " + path.getName(1));
		
		System.out.println("FILE SIZE :: " + Files.size(path)/1024 + " KB");
		List<String> readAllLines = Files.readAllLines(path);
		System.out.println("NUMBER OF LINES :: " + readAllLines.size());
		
		
		FileTime lastModifiedTime = Files.getLastModifiedTime(path);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(lastModifiedTime.toMillis());
		
		System.out.println("LAST MODIFIED TIME :: " + cal.get(Calendar.DATE));
		
		
		FileSystem fs = FileSystems.getDefault();
		System.out.println(fs.getSeparator());
		
		
		Path path2 = FileSystems.getDefault().getPath("/home/santoshm/Desktop/rule.txt");
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{java,class,txt}");
		System.out.println(pathMatcher.matches(path2));
	}
}
