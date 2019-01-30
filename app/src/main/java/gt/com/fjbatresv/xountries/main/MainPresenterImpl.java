package gt.com.fjbatresv.xountries.main;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import gt.com.fjbatresv.xountries.Event;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.entitys.CountryAnswer;
import gt.com.fjbatresv.xountries.lib.base.EventBus;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainInt interactor;
    private EventBus bus;

    public MainPresenterImpl(MainView view, MainInt interactor, EventBus bus) {
        this.view = view;
        this.interactor = interactor;
        this.bus = bus;
    }

    @Override
    public void onCreate() {
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        bus.unRegister(this);
    }

    @Subscribe
    @Override
    public void onEvent(Event event) {
        view.loading(false);
        if (event.getMessage() != null && !event.getMessage().isEmpty()) {
            view.notification(event.getMessage());
        } else {
            switch (event.getId()) {
                case Event.getCountries:
                    CountryAnswer answer = (CountryAnswer) event.getObject();
                    view.getCountries(answer);
                    break;
                case Event.saveFav:
                    view.addFav();
                    break;
                case Event.validIntro:
                    view.validateIntro((boolean) event.getObject());
                    break;
            }
        }
    }

    @Override
    public void getCountries(int page) {
        view.loading(true);
        interactor.getCountries(page);
    }

    @Override
    public void addFav(Country o) {
        view.loading(true);
        interactor.addFav(o);
    }

    @Override
    public void validateIntro() {
        view.loading(true);
        interactor.validateIntro();
    }

    @Override
    public void setConnectionStatus(boolean isConnected) {
        interactor.setConnectionStatus(isConnected);
    }
}
