package gt.com.fjbatresv.xountries.entitys;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable, Comparable<Country> {

    private String code;
    private String name;
    private String residence;
    private String phone_prefix;
    private List<Link> links;
    private Min min;
    private Max max;

    public Country() {
    }

    public Country(String code, String name, String residence, String phone_prefix, List<Link> links, Min min, Max max) {
        this.code = code;
        this.name = name;
        this.residence = residence;
        this.phone_prefix = phone_prefix;
        this.links = links;
        this.min = min;
        this.max = max;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPhone_prefix() {
        return phone_prefix;
    }

    public void setPhone_prefix(String phone_prefix) {
        this.phone_prefix = phone_prefix;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Min getMin() {
        return min;
    }

    public void setMin(Min min) {
        this.min = min;
    }

    public Max getMax() {
        return max;
    }

    public void setMax(Max max) {
        this.max = max;
    }

    public String getFlag(){
        return "https://www.countryflags.io/" + this.code + "/flat/64.png";
    }

    public boolean isFav(){
        Favorites favorite =  SQLite.select().from(Favorites.class)
                .where(Favorites_Table.code.eq(this.code)).querySingle();
        if (favorite == null ){
            return false;
        }
        return favorite.isFav();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Country){
            Country tmp = (Country) obj;
            if (tmp.getCode().equals(this.code)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(@NonNull Country country) {
        if (getCode() == null || country.getCode() == null) {
            return 0;
        }
        return getCode().compareTo(country.getCode());
    }
}
