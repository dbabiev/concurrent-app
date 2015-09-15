import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

@FunctionalInterface
interface FutureTryCatch<T> {
    T apply();
}

public class Helper {

    protected String name;

    final protected String _2strFormat = "%s (%s)";

/*    protected Comparator maxSize = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (o1 > o2) ? 1 : 0;
        }
    };*/

    protected String _2format(String format, String one, String two){
        return String.format("%s(%s)", one, two);
    }

    protected void log(String text){
        System.out.println(text);
    }

    protected void log(String author, String text){
        //int max = textes.stream().map((text) -> text.length()).max(maxSize).orElse(0);
        //textes.stream().reduce((acc, text) -> acc + ((text.length() < max) ? text : text));
        System.out.println(String.format("[%s]: %s", author, text));
    }

    protected void namedLog(String text){
        System.out.println(String.format("[%s]: %s", name, text));
    }

    protected <T> T future(Future f){
        T result = null;
        try {
            result = (T)f.get();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
