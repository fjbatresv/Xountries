package gt.com.fjbatresv.xountries.entitys;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import gt.com.fjbatresv.xountries.db.XountryDB;

@Table(database = XountryDB.class, primaryKeyConflict = ConflictAction.REPLACE,
        insertConflict = ConflictAction.REPLACE, updateConflict = ConflictAction.REPLACE)
public class Favorites extends BaseModel implements Serializable {

    @Column
    @PrimaryKey
    private String code;
    @Column
    private boolean fav;

    public Favorites() {
    }

    public Favorites(String code, boolean fav) {
        this.code = code;
        this.fav = fav;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
