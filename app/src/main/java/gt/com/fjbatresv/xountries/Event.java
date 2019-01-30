package gt.com.fjbatresv.xountries;

import java.io.Serializable;

public class Event implements Serializable {

    public final static int getCountries = 0;
    public final static int saveFav = 1;
    public final static int validIntro = 2;

    private int id;
    private Object object;
    private String message;

    public Event() {
    }

    public Event(int id) {
        this.id = id;
    }

    public Event(int id, Object object) {
        this.id = id;
        this.object = object;
    }

    public Event(String message) {
        this.message = message;
    }

    public Event(int id, Object object, String message) {
        this.id = id;
        this.object = object;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
