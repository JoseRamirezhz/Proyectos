package sv.edu.utec.appsupermercadosprecios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "precio.db";
    public static final String TABLE_1 = "usuarios";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_1 +" (" +
                "usuario TEXT primary key,"+
                "password TEXT"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE "+TABLE_1);
        onCreate(db);
    }

    public Boolean insertData(String usuario, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);

        long result = db.insert("usuarios", null, contentValues);

        if(result == -1){
            return  false;
        }else{
            return true;
        }
    }

    public Boolean chexhUsuario(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where usuario = ?", new String[]{usuario});

        if(cursor.getCount() > 0){
            return  true;
        }else{
            return  false;
        }
    }

    public  Boolean checkUsuarioPassword(String usuario, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where usuario = ? and password = ?", new String[]{usuario, password});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return  false;
        }
    }
}
