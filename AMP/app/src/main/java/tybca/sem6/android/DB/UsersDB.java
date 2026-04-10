package tybca.sem6.android.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDB extends SQLiteOpenHelper {
    public static final String db_name = "user.db";
    public static final String table_name = "users";
    public static final String col_id = "id";
    public static final String col_username  = "username";
    public static final String col_password = "password";
    public static final String col_email = "email";
    public static final String col_phone = "phone";
    public static final String col_updated_at = "updated_at";
    public UsersDB(Context context){
        super(context,db_name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_name+"("+col_id+" int primary key autoincrement,"+col_username+" " +
                "varchar(50),"+col_email+" varchar(100),"+col_phone+" varchar(12),"+col_password+" TEXT,"+col_updated_at+" " +
                "datetime DEFAULT CURRENT_TIMESTAMP )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists "+table_name+"");
    }
    public String insertUserData(String username,String email,String phone,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getUserData(username);
        if(cursor.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_username, username);
            contentValues.put(col_email, email);
            contentValues.put(col_phone, phone);
            contentValues.put(col_password, password);
            long result = db.insert(table_name, null, contentValues);
            if (result == -1) {
                return "Error";
            } else {
                return "Success";
            }
        }
        else{
            return "DuplicateUser";
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table_name+"",null);
        return cursor;
    }
    public Cursor getUserData(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table_name+" where "+col_username+" = '"+username+"'",null);
        return cursor;
    }
    public Cursor getUserFromId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table_name+" where "+col_id+" = '"+id+"'",null);
        return cursor;
    }
    public Boolean updateUserData(int id,String username,String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_username,username);
        contentValues.put(col_email,email);
        contentValues.put(col_phone,phone);
        long result = db.update(table_name,contentValues,"id = ?",new String[]{String.valueOf(id)});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean deleteUserData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name,"id = ?",new String[]{String.valueOf(id)});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updateUserPassword(int id,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_password,password);
        long result = db.update(table_name,contentValues,"id=?",new String[]{String.valueOf(id)});
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
