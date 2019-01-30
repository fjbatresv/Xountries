package gt.com.fjbatresv.xountries.main;

import gt.com.fjbatresv.xountries.entitys.Country;

public interface MainRepo {
    void getCountries(int page);
    void addFav(Country o);
    void validateIntro();
    void setConnectionStatus(boolean isConnected);
}
