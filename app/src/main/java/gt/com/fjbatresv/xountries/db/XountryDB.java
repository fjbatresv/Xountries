package gt.com.fjbatresv.xountries.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = XountryDB.name, version = XountryDB.version)
public class XountryDB {
    public final static int version = 1;
    public final static String name = "xountries";
}
