package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-09
 */
public class LongEventFactory implements EventFactory<LongEvent>{
	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}
}
