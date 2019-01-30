package gt.com.fjbatresv.xountries.api.DI;

import javax.inject.Singleton;

import dagger.Component;
import gt.com.fjbatresv.xountries.AppModule;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

}
