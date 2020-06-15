package cc.liubin.disruptor.demo;

import cc.liubin.disruptor.demo.LongEvent;
import cc.liubin.disruptor.demo.LongEventFactory;
import cc.liubin.disruptor.demo.LongEventHandler;
import cc.liubin.disruptor.demo.LongEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author: bin.liu
 * @date 2020/6/15 10:07
 */
public class LongEventMain2 {
    public static void main(String[] args) {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();


        LongEventProducer producer = new LongEventProducer(ringBuffer);


        EventTranslator<LongEvent> longEventEventTranslator = (event, sequence) -> {
            event.reset();
            event.set(5);
        };

        ringBuffer.tryPublishEvent(longEventEventTranslator);

//        ByteBuffer bb = ByteBuffer.allocate(8);
//
//        for (long l = 0; true; l++)
//        {
//            EventTranslator<LongEvent> longEventEventTranslator = (event, sequence) -> {
//                event.reset();
//                event.set(l);
//            };
//
//            ringBuffer.tryPublishEvent(longEventEventTranslator);
//        }
    }
}
