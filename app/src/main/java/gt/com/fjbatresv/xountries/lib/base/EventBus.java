package gt.com.fjbatresv.xountries.lib.base;

/**
 * Created by javie on 11/12/2017.
 */

public interface EventBus {
    void register(Object sub);
    void unRegister(Object sub);
    void post(Object sub);
}
