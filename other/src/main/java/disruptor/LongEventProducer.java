package disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-10
 */
public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb) {
		// grab the next sequence
		long sequence = ringBuffer.next();

		try {
			LongEvent event = ringBuffer.get(sequence);

			// fill with data
			event.setValue(bb.getLong(0));
		} finally {
			ringBuffer.publish(sequence);
		}
	}
}
