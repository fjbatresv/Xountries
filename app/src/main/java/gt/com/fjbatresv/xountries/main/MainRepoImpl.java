package gt.com.fjbatresv.xountries.main;

import android.content.Context;
import android.content.SharedPreferences;

import gt.com.fjbatresv.xountries.Event;
import gt.com.fjbatresv.xountries.R;
import gt.com.fjbatresv.xountries.api.CountriesService;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.entitys.CountryAnswer;
import gt.com.fjbatresv.xountries.entitys.Favorites;
import gt.com.fjbatresv.xountries.lib.base.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepoImpl implements MainRepo {

    private CountriesService service;
    private EventBus bus;
    private SharedPreferences preferences;
    private Context context;

    public MainRepoImpl(CountriesService service, EventBus bus, SharedPreferences preferences, Context context) {
        this.service = service;
        this.bus = bus;
        this.preferences = preferences;
        this.context = context;
    }

    @Override
    public void getCountries(int page) {
        Callback<CountryAnswer> callback = new Callback<CountryAnswer>() {
            @Override
            public void onResponse(Call<CountryAnswer> call, Response<CountryAnswer> response) {
                if (response.isSuccessful()){
                    bus.post(new Event(Event.getCountries, response.body()));
                }else{
                    bus.post(new Event(response.message()));
                }
            }

            @Override
            public void onFailure(Call<CountryAnswer> call, Throwable t) {
                bus.post(new Event(t.getLocalizedMessage()));
            }
        };
        if (preferences.getBoolean("connection", true)) {
            service.getCountries(20, page).enqueue(callback);
        }else{
            bus.post(new Event(context.getString(R.string.no_connection_result)));
        }
    }

    @Override
    public void addFav(Country o) {
        Favorites favorite = new Favorites(o.getCode(), !o.isFav());
        favorite.save();
        bus.post(new Event(Event.saveFav));
    }

    @Override
    public void validateIntro() {
        boolean intro = preferences.getBoolean("intro", false);
        if (!intro) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("intro", true);
            editor.commit();
        }
        bus.post(new Event(Event.validIntro, intro));
    }

    @Override
    public void setConnectionStatus(boolean isConnected) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("connection", isConnected);
        editor.commit();
    }
}
