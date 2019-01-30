package gt.com.fjbatresv.xountries.main.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gt.com.fjbatresv.xountries.R;
import gt.com.fjbatresv.xountries.entitys.Country;
import gt.com.fjbatresv.xountries.lib.base.ImageLoader;
import gt.com.fjbatresv.xountries.main.ClickListener;
import com.lb.recyclerview_fast_scroller.RecyclerViewFastScroller.BubbleTextGetter;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> implements BubbleTextGetter {

    private List<Country> countries;
    private List<Country> allCountries;
    private ImageLoader imageLoader;
    private ClickListener listener;
    private Context context;
    private boolean onlyFav;

    public CountriesAdapter(List<Country> countries, ImageLoader imageLoader, ClickListener listener, Context context) {
        this.countries = countries;
        this.imageLoader = imageLoader;
        this.listener = listener;
        this.context = context;
        this.onlyFav = false;
        this.allCountries = new ArrayList<Country>();
    }

    public void setOnlyFav(boolean onlyFav) {
        this.onlyFav = onlyFav;
    }

    public boolean isOnlyFav() {
        return onlyFav;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Country country = countries.get(i);
        holder.code.setText(country.getCode());
        holder.name.setText(country.getName());
        holder.prefix.setText("| " + country.getPhone_prefix());
        holder.onClick(country, listener);
        if (country.isFav()){
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.outline_star_black_48));
        }else{
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.outline_star_border_black_48));
        }
        imageLoader.load(holder.flag, country.getFlag());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void addCountries(List<Country> countries) {
        for (Country c : countries){
            if (!this.countries.contains(c) && !this.onlyFav) {
                this.countries.add(c);
            }
            if (!this.allCountries.contains(c)){
                this.allCountries.add(c);
            }
        }
        Collections.sort(this.countries);
        Collections.sort(allCountries);
        notifyDataSetChanged();
    }

    public void update() {
        Collections.sort(countries);
        Collections.sort(allCountries);
        notifyDataSetChanged();
    }

    public void changeFav() {
        setOnlyFav(!this.onlyFav);
        this.countries.clear();
        if (this.onlyFav) {
            for (Country country : allCountries){
                if (country.isFav()){
                    this.countries.add(country);
                }
            }
        }else{
            this.countries.addAll(this.allCountries);
        }
        Collections.sort(countries);
        Collections.sort(this.allCountries);
        notifyDataSetChanged();
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        return Character.toString(countries.get(pos).getCode().charAt(0));
    }

    protected  class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.flag)
        ImageView flag;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.code)
        TextView code;
        @BindView(R.id.prefix)
        TextView prefix;
        @BindView(R.id.country_card)
        CardView card;
        @BindView(R.id.fav)
        ImageView fav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onClick(final Country country, final ClickListener listener){
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("CLICK","CARD");
                    listener.click("info", country);
                }
            });

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("CLICK","FAV");
                    listener.click("fav", country);
                }
            });
        }
    }

}
