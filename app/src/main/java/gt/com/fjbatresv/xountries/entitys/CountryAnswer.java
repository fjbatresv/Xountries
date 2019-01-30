package gt.com.fjbatresv.xountries.entitys;

import java.io.Serializable;
import java.util.List;

public class CountryAnswer implements Serializable {

    private int total_items;
    private int total_pages;
    private List<Link> links;
    private List<Country> items;

    public CountryAnswer() {
    }

    public CountryAnswer(int total_items, int total_pages, List<Link> links, List<Country> items) {
        this.total_items = total_items;
        this.total_pages = total_pages;
        this.links = links;
        this.items = items;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Country> getItems() {
        return items;
    }

    public void setItems(List<Country> items) {
        this.items = items;
    }
}
