package omg.medvedomg.dbofcarowners.mvp.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by medvedomg on 22.07.17.
 */

public class DbHelper extends SQLiteOpenHelper{

    //ONWER TABLE
    public static final String OWNER_TABLE_NAME = "owner";
    public static final String OWNER_ID = "id";
    public static final String OWNER_NAME = "owner_name";

    public DbHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    private void createTable(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + OWNER_TABLE_NAME + "("
                            + OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + OWNER_NAME + " TEXT "
                            +  ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + OWNER_TABLE_NAME);
        onCreate(db);
    }
}
