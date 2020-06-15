package cc.liubin.disruptor.demo;


import com.lmax.disruptor.EventFactory;

/**
 * @author: bin.liu
 * @date 2020/6/15 09:28
 */
public class LongEventFactory implements EventFactory {


    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
