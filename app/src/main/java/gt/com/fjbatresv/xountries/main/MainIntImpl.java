package gt.com.fjbatresv.xountries.main;

import gt.com.fjbatresv.xountries.entitys.Country;

public class MainIntImpl implements MainInt {

    private MainRepo repo;

    public MainIntImpl(MainRepo repo) {
        this.repo = repo;
    }

    @Override
    public void getCountries(int page) {
        repo.getCountries(page);
    }

    @Override
    public void addFav(Country o) {
        repo.addFav(o);
    }

    @Override
    public void validateIntro() {
        repo.validateIntro();
    }
}
