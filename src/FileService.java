import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *  FileService
 */

public class FileService extends Helper {

    public FileService(String name) {
        this.name = name;
    }

    public Map<String, Map<String, Integer>> textFileIndex(Path pathDirectory) {
        // result index
        ConcurrentMap<String, Map<String, Integer>> index = new ConcurrentHashMap<>();
        // directory of indexing files
        try(DirectoryStream<Path> directory = Files.newDirectoryStream(pathDirectory, "*.txt")) {
            // paths of indexing files
            List<Path> filePaths = new ArrayList<>();
            for (Path filePath : directory) { filePaths.add(filePath); }
            // barrier to similar start
            CyclicBarrier startBarrier = new CyclicBarrier(filePaths.size());
            // executor of calculation
            ExecutorService executor = Executors.newCachedThreadPool();
            // list of result futures (for each file index)
            List<Future> results = new ArrayList<>();
            // start index
            for(Path path : filePaths) { results.add(executor.submit(new TextFileIndexer(path, startBarrier, index))); }
            // log all results
            results.forEach((f) -> namedLog(future(f).toString()));
            // print indexing data
            index.forEach((word, fileMap) -> {
                List<String> lines = new ArrayList<>();
                for(Map.Entry<String, Integer> fileEntry : fileMap.entrySet()) {
                    lines.add(_2format(_2strFormat, fileEntry.getKey(), fileEntry.getValue().toString()));
                }
                log(word, lines.stream().collect(Collectors.joining(", ")));
            });
            // shutdown executor
            executor.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }
}