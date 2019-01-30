package gt.com.fjbatresv.xountries.api.DI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gt.com.fjbatresv.xountries.api.CountriesClient;
import gt.com.fjbatresv.xountries.api.CountriesService;

@Module
public class ApiModule {

    @Provides
    @Singleton
    CountriesService providesCountriesService(){
        return new CountriesClient().getCountriesService();
    }

}
