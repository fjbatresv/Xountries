package gt.com.fjbatresv.xountries;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.junit.Test;

import java.util.ArrayList;

import gt.com.fjbatresv.xountries.api.CountriesClient;
import gt.com.fjbatresv.xountries.api.CountriesService;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.entitys.CountryAnswer;
import gt.com.fjbatresv.xountries.entitys.Favorites;
import gt.com.fjbatresv.xountries.main.adapters.CountriesAdapter;
import gt.com.fjbatresv.xountries.util.ObjectSerializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    @Test
    public void validApi() {
        CountriesService service = new CountriesClient().getCountriesService();
        Callback<CountryAnswer> callback = new Callback<CountryAnswer>() {
            @Override
            public void onResponse(Call<CountryAnswer> call, Response<CountryAnswer> response) {
                if (response.isSuccessful()){
                    assertTrue(response.message(), response.body() instanceof CountryAnswer);
                } else {
                    fail(response.message());
                }
            }

            @Override
            public void onFailure(Call<CountryAnswer> call, Throwable t) {
                fail(t.getLocalizedMessage());
            }
        };
        service.getCountries(10, 1).enqueue(callback);
    }

    @Test
    public void serializerTest(){
        try{
            Favorites favorites = new Favorites();
            String serialiazed = ObjectSerializer.serialize(favorites);
            favorites = (Favorites) ObjectSerializer.deserialize(serialiazed);
            assertTrue(favorites instanceof Favorites);
        } catch (Exception ex){
            fail(ex.getLocalizedMessage());
        }
    }

}