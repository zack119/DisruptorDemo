package cc.liubin.disruptor.demo;

import com.lmax.disruptor.EventHandler;

/**
 * @author: bin.liu
 * @date 2020/6/15 09:31
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event);
    }

}
