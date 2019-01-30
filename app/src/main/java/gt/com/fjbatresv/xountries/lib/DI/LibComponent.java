package gt.com.fjbatresv.xountries.lib.DI;


import javax.inject.Singleton;

import dagger.Component;
import gt.com.fjbatresv.xountries.AppModule;

/**
 * Created by javie on 11/12/2017.
 */
@Singleton
@Component(modules = {AppModule.class, LibsModule.class})
public interface LibComponent {
}
