
package score;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import score.data.Person;

public class TresslerMain {

	static final CSVFileReader reader = new CSVFileReader();
	static final CSVFileWriter jsonWriter = new CSVFileWriter();
	static final boolean consoleLogging = true;

	static class ProcessingThread extends Thread {

		WatchKey key;

		public ProcessingThread(WatchKey k) {
			key = k;
		}

		public void run() {
			processNewFiles(key);
		}
	}

	public static void main(String[] args) {
		if (consoleLogging) {
			System.out.println("begin watching for csv files");
		}
		WatchService watcher = initialize();
		boolean valid = true;
		do {
			WatchKey key = findNewFiles(watcher);
			new ProcessingThread(key).start();
			valid = key.reset();
		} while (valid);

	}

	private static WatchService initialize() {
		WatchService watcher = null;
		Path dir = FileSystems.getDefault().getPath(CSVConstants.INPUT_DIRECTORY);
		try {
			watcher = FileSystems.getDefault().newWatchService();
			dir.register(watcher, ENTRY_CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return watcher;
	}

	private static WatchKey findNewFiles(WatchService watcher) {
		WatchKey key = null;
		try {
			key = watcher.take(); // blocking call, similar to sleep()
		} catch (InterruptedException x) {
			return null; // this happens if your thread got interrupted
		}
		return key;
	}

	@SuppressWarnings("unchecked")
	private static void processNewFiles(WatchKey key) {
		for (WatchEvent<?> event : key.pollEvents()) {
			WatchEvent<Path> ev = (WatchEvent<Path>) event;
			String temp = ev.context().toString();
			String aFileName = temp.substring(0, temp.lastIndexOf(CSVConstants.CSV));
			if (consoleLogging) {
				System.out.println("found file: " + aFileName);
			}
			Person[] p = reader.parseFile(aFileName);
			if (p.length > 0) {
				jsonWriter.writeFile(p, aFileName);
				if (consoleLogging) {
					System.out.println("results file: " + aFileName + " with " + p.length + " valid entries.");
				}
			}
			try {
				new File(CSVConstants.INPUT_DIRECTORY + "//" + temp).delete();
				if (consoleLogging) {
					System.out.println("deleted file: " + temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
