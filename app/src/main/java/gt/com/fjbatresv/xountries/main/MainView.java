package gt.com.fjbatresv.xountries.main;

import java.util.List;

import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.entitys.CountryAnswer;

public interface MainView {
    void loading(boolean load);
    void getCountries(CountryAnswer answer);
    void notification(String message);
    void addFav();
    void validateIntro(boolean valid);
}
