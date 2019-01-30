package gt.com.fjbatresv.xountries;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.List;

import gt.com.fjbatresv.xountries.api.DI.ApiModule;
import gt.com.fjbatresv.xountries.lib.DI.LibsModule;
import gt.com.fjbatresv.xountries.main.ClickListener;
import gt.com.fjbatresv.xountries.main.DI.DaggerMainComponent;
import gt.com.fjbatresv.xountries.main.DI.MainComponent;
import gt.com.fjbatresv.xountries.main.DI.MainModule;
import gt.com.fjbatresv.xountries.main.MainView;
import gt.com.fjbatresv.xountries.receiver.ConnectivityReceiver;
/*
 * Created by javie on 11/28/2017.
 */

public class App extends Application {
    private AppModule appModule;
    private LibsModule libsModule;
    private ApiModule apiModule;
    private static App instance;

    @Override
    public void onCreate()  {
        super.onCreate();
        instance = this;
        initModules();
        initDb();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DbTearDown();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    private void initDb() {
        FlowManager.init(this);
    }

    private void DbTearDown(){
        FlowManager.destroy();
    }

    private void initModules() {
        this.appModule = new AppModule(this);
        this.libsModule = new LibsModule();
        this.apiModule = new ApiModule();
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public static String getUserSharedPreferences() {
        return "Xountries";
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    //Inyeccion - INICIO
    public MainComponent main(MainView view, ClickListener listener){
        return DaggerMainComponent.builder()
                .appModule(appModule)
                .apiModule(apiModule)
                .libsModule(libsModule)
                .mainModule(new MainModule(view, listener))
                .build();
    }
    //Inyection - FIN
}
