import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CyclicBarrier;

public class FileIndexer extends Helper implements Callable<HandleTime> {

    private Path filePath = null;
    private CyclicBarrier startBarrier = null;
    //private CountDownLatch finishLatch = null;
    private ConcurrentMap<String, Map<String, Integer>> index = null;

    public FileIndexer(Path filePath, CyclicBarrier startBarrier, /*CountDownLatch finishLatch,*/ ConcurrentMap<String, Map<String, Integer>> index) {
        this.name = filePath.getFileName().toString();
        this.filePath = filePath;
        this.startBarrier = startBarrier;
        //this.finishLatch = finishLatch;
        this.index = index;
    }

    @Override
    public HandleTime call() throws Exception {
        long start = System.currentTimeMillis();
        startBarrier.await();
        try(BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            String fileName = filePath.getFileName().toString();
            String line;
            while((line = reader.readLine()) != null) {
                String[] words = line.split("[\\p{Punct}\\s]+");
                for(String word : words){
                    if(index.containsKey(word)){
                        Map<String, Integer> entry = index.get(word);
                        entry.put(fileName, entry.getOrDefault(fileName, 0) + 1);
                    }
                    else {
                        Map<String, Integer> entry = new HashMap<>();
                        entry.put(fileName, 1);
                        index.put(word, entry);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //finishLatch.countDown();
            return new HandleTime(name, System.currentTimeMillis() - start);
        }
    }
}
