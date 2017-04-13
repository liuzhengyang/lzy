package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-10
 */
public class LongEventMain {
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newCachedThreadPool();

		LongEventFactory factory = new LongEventFactory();

		int bufferSize = 1024;

		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);

		disruptor.handleEventsWith(new LongEventHandler());

		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		LongEventProducer producer = new LongEventProducer(ringBuffer);


		ByteBuffer bb = ByteBuffer.allocate(8);
		for (int i = 0; i < 1000; i++) {
			bb.putLong(0, i);
			producer.onData(bb);
			Thread.sleep(1000);
		}
	}
}
