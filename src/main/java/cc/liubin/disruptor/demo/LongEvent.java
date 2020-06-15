package cc.liubin.disruptor.demo;

/**
 * @author: bin.liu
 * @date 2020/6/15 09:27
 */
public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void reset() {
        value = 0;
    }
}
