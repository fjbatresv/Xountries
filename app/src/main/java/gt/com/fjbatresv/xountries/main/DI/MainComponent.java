package gt.com.fjbatresv.xountries.main.DI;

import javax.inject.Singleton;

import dagger.Component;
import gt.com.fjbatresv.xountries.AppModule;
import gt.com.fjbatresv.xountries.api.DI.ApiModule;
import gt.com.fjbatresv.xountries.lib.DI.LibsModule;
import gt.com.fjbatresv.xountries.main.MainActivity;

@Singleton
@Component(modules = {AppModule.class, LibsModule.class, MainModule.class, ApiModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
