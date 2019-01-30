package gt.com.fjbatresv.xountries.main;

import gt.com.fjbatresv.xountries.Event;
import gt.com.fjbatresv.xountries.entitys.Country;

public interface MainPresenter {

    void onCreate();
    void onDestroy();
    void onEvent(Event event);
    void getCountries(int page);

    void addFav(Country o);

    void validateIntro();
}
