import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FileService extends Helper {

    public FileService(String name) {
        this.name = name;
    }

    public Map<String, Map<String, Integer>> fileIndex(Path pathDirectory) {
        ConcurrentMap<String, Map<String, Integer>> index = new ConcurrentHashMap<>();
        try(DirectoryStream<Path> directory = Files.newDirectoryStream(pathDirectory, "*.txt")) {
            List<Path> filePaths = new ArrayList<>();
            for (Path filePath : directory) { filePaths.add(filePath); }

            CyclicBarrier startBarrier = new CyclicBarrier(filePaths.size());
            //CountDownLatch finishLatch = new CountDownLatch(filePaths.size());
            ExecutorService executor = Executors.newCachedThreadPool();
            List<Future> results = new ArrayList<>();
            for(Path path : filePaths) { results.add(executor.submit(new FileIndexer(path, startBarrier, /*finishLatch,*/ index))); }



            results.forEach((f) -> namedLog(future(f).toString()));
            System.out.println(2);
            //finishLatch.await();
            System.out.println(3);

            index.forEach((word, fileMap) -> {
                List<String> lines = new ArrayList<>();
                for(Map.Entry<String, Integer> fileEntry : fileMap.entrySet()) {
                    lines.add(_2format(_2strFormat, fileEntry.getKey(), fileEntry.getValue().toString()));
                }
                log(word, lines.stream().collect(Collectors.joining(", ")));
            });

            executor.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }
}