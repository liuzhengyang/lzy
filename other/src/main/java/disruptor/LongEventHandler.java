package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-09
 */
public class LongEventHandler implements EventHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("Events: " + event);
	}
}
