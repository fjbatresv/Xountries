package gt.com.fjbatresv.xountries.api;

import gt.com.fjbatresv.xountries.entitys.CountryAnswer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountriesService {

    @GET("catalog/v2/countries")
    Call<CountryAnswer> getCountries(@Query("page_size") int size, @Query("page") int page);

}
