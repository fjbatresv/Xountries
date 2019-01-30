package gt.com.fjbatresv.xountries.entitys;

import java.io.Serializable;

public class Max implements Serializable {

    private String currency_code;

    public Max() {
    }

    public Max(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
