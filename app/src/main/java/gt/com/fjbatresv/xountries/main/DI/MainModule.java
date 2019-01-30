package gt.com.fjbatresv.xountries.main.DI;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gt.com.fjbatresv.xountries.api.CountriesService;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.lib.base.EventBus;
import gt.com.fjbatresv.xountries.lib.base.ImageLoader;
import gt.com.fjbatresv.xountries.main.ClickListener;
import gt.com.fjbatresv.xountries.main.MainInt;
import gt.com.fjbatresv.xountries.main.MainIntImpl;
import gt.com.fjbatresv.xountries.main.MainPresenter;
import gt.com.fjbatresv.xountries.main.MainPresenterImpl;
import gt.com.fjbatresv.xountries.main.MainRepo;
import gt.com.fjbatresv.xountries.main.MainRepoImpl;
import gt.com.fjbatresv.xountries.main.MainView;
import gt.com.fjbatresv.xountries.main.adapters.CountriesAdapter;

@Module
public class MainModule {

    private MainView view;
    private ClickListener listener;

    public MainModule(MainView view, ClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    MainView providesMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    ClickListener providesClickListener(){
        return this.listener;
    }

    @Provides
    @Singleton
    List<Country> providesCountriesList(){
        return new ArrayList<Country>();
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainView view, MainInt inter, EventBus bus){
        return new MainPresenterImpl(view, inter, bus);
    }

    @Provides
    @Singleton
    MainInt providesMainInt(MainRepo repo){
        return new MainIntImpl(repo);
    }

    @Provides
    @Singleton
    MainRepo providesMainRepo(CountriesService service, EventBus bus, SharedPreferences preferences, Context context){
        return new MainRepoImpl(service, bus, preferences, context);
    }

    @Provides
    @Singleton
    CountriesAdapter providesCountriesAdapter(List<Country> countries, ImageLoader imageLoader, ClickListener listener, Context context){
        return new CountriesAdapter(countries, imageLoader, listener, context);
    }
}
