package gt.com.fjbatresv.xountries.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lb.recyclerview_fast_scroller.RecyclerViewFastScroller;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import gt.com.fjbatresv.xountries.App;
import gt.com.fjbatresv.xountries.IntroActivity;
import gt.com.fjbatresv.xountries.R;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.entitys.CountryAnswer;
import gt.com.fjbatresv.xountries.lib.base.ImageLoader;
import gt.com.fjbatresv.xountries.main.adapters.CountriesAdapter;
import gt.com.fjbatresv.xountries.receiver.ConnectivityReceiver;
import gt.com.fjbatresv.xountries.util.Functions;

public class MainActivity extends AppCompatActivity implements MainView, ClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    @BindView(R.id.countries)
    RecyclerView countries;
    @BindView(R.id.loading)
    RelativeLayout loader;
    @BindView(R.id.total_loaded)
    TextView total;
    @BindView(R.id.fastscroller)
    RecyclerViewFastScroller fastScroller;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.connection_status)
    TextView connectionStatus;


    @BindString(R.string.total_replace)
    String totalReplace;
    @BindString(R.string.no_more_results)
    String noMoreResults;
    @BindString(R.string.country_code)
    String countryCode;
    @BindString(R.string.phone_prefix)
    String phonePrefix;
    @BindString(R.string.favorite_updated)
    String favoriteUpdated;
    @BindString(R.string.favorite_replace)
    String favoriteReplace;
    @BindString(R.string.ok_connection)
    String okConnection;
    @BindString(R.string.no_connection)
    String noConnection;

    @BindDrawable(R.drawable.outline_star_border_white_48)
    Drawable starBoderWhite;
    @BindDrawable(R.drawable.outline_star_white_48)
    Drawable starWhite;

    @Inject
    MainPresenter presenter;
    @Inject
    CountriesAdapter adapter;
    @Inject
    ImageLoader imageLoader;

    private App app;
    private Menu menu;
    private int page = 1;
    private int lastPage = 0;
    private AlertDialog dialog;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.app = (App) getApplication();
        inject();
        setSupportActionBar(toolbar);
        presenter.onCreate();
        setRecycler();
        connectionChange();
        presenter.getCountries(page);
    }

    private void connectionChange() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(new ConnectivityReceiver(), intentFilter);
        onNetworkConnectionChanged(ConnectivityReceiver.isConnected());
        app.setConnectivityListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.main);
        this.menu = toolbar.getMenu();
        presenter.validateIntro();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (adapter.isOnlyFav()) {
            changeFav();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav:
                changeFav();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeFav() {
        if (!adapter.isOnlyFav()) {
            GridLayoutManager layoutManager = (GridLayoutManager) this.countries.getLayoutManager();
            currentPosition = layoutManager.findLastVisibleItemPosition();
            if (currentPosition - 1 < 0) {
                currentPosition = 0;
            } else {
                currentPosition--;
            }
            menu.getItem(0).setIcon(starBoderWhite);
            countries.smoothScrollToPosition(0);
        }
        adapter.changeFav();
        if (!adapter.isOnlyFav()) {
            menu.getItem(0).setIcon(starWhite);
            countries.smoothScrollToPosition(currentPosition);
        }
        total.setText(String.format(adapter.isOnlyFav() ? favoriteReplace : totalReplace, String.valueOf(adapter.getItemCount())));
    }

    private void setRecycler() {
        countries.setLayoutManager(new GridLayoutManager(this, Functions.calculateNoOfColumns(this, 300)) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                final int firstVisibleItemPosition = findFirstVisibleItemPosition();
                if (firstVisibleItemPosition != 0) {
                    if (firstVisibleItemPosition == -1) {
                        fastScroller.setVisibility(View.GONE);
                    }
                    return;
                }
                final int lastVisibleItemPosition = findLastVisibleItemPosition();
                int itemsShown = lastVisibleItemPosition - firstVisibleItemPosition + 1;
                fastScroller.setVisibility(adapter.getItemCount() > itemsShown ? View.VISIBLE : View.GONE);
            }
        });
        fastScroller.setRecyclerView(countries);
        fastScroller.setViewsToUse(R.layout.recycler_view_fast_scroller, R.id.fastscroller_bubble, R.id.fastscroller_handle);
        countries.setAdapter(adapter);
        countries.setItemAnimator(new DefaultItemAnimator());
        countries.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if (page <= lastPage) {
                        if (loader.getVisibility() == View.GONE && !adapter.isOnlyFav()) {
                            presenter.getCountries(page);
                        }
                    } else {
                        notification(noMoreResults);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void inject() {
        app.main(this, this).inject(this);
    }

    @Override
    public void loading(boolean load) {
        loader.setVisibility(load ? View.VISIBLE : View.GONE);
    }

    @Override
    public void getCountries(CountryAnswer answer) {
        this.lastPage = answer.getTotal_pages();
        page++;
        adapter.addCountries(answer.getItems());
        total.setText(String.format(totalReplace, String.valueOf(adapter.getItemCount())));
    }

    @Override
    public void notification(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addFav() {
        adapter.update();
        notification(favoriteUpdated);
    }

    @Override
    public void validateIntro(boolean valid) {
        if (!valid) {
            startActivity(new Intent(this, IntroActivity.class));
        }
    }

    @Override
    public void click(String action, Object o) {
        Country country = (Country) o;
        switch (action) {
            case "info":
                Log.e("INFO", country.getFlag());
                popup(country);
                break;
            case "fav":
                presenter.addFav(country);
                break;
        }
    }

    private void popup(Country country) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.country_dialog, null);
        ImageView flag = (ImageView) view.findViewById(R.id.dial_flag);
        imageLoader.load(flag, country.getFlag());
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(country.getName());
        TextView code = (TextView) view.findViewById(R.id.code);
        code.setText(String.format(countryCode, country.getCode()));
        TextView prefix = (TextView) view.findViewById(R.id.prefix);
        prefix.setText(String.format(phonePrefix, country.getPhone_prefix()));
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        builder.setView(view);
        this.dialog = builder.show();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        presenter.setConnectionStatus(isConnected);
        if (isConnected) {
            this.connectionStatus.setText(okConnection);
            this.connectionStatus.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            this.connectionStatus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            this.connectionStatus.postDelayed(new Runnable() {
                @Override
                public void run() {
                    connectionStatus.setVisibility(android.view.View.GONE);
                }
            }, 3000);
        } else if (!isConnected) {
            this.connectionStatus.setBackgroundColor(getResources().getColor(R.color.colorRed));
            this.connectionStatus.setText(noConnection);
            this.connectionStatus.setTextColor(Color.WHITE);
            this.connectionStatus.setVisibility(android.view.View.VISIBLE);
        }
    }
}
