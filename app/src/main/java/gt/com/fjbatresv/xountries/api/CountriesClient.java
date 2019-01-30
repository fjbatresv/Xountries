package gt.com.fjbatresv.xountries.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesClient {
    private Retrofit retrofit;
    private final static String baseUrl = "https://mobile.xoom.com/";

    public CountriesClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CountriesService getCountriesService(){
        return this.retrofit.create(CountriesService.class);
    }
}
