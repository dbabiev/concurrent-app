public class HandleTime {

    private String threadName;
    private Long time;

    public HandleTime(String threadName, Long time) {
        this.threadName = threadName;
        this.time = time;
    }

    public String getThreadName() {
        return threadName;
    }

    public Long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s: %s mills", threadName, time);
    }
}
