package br.edu.icomp.ufam.icompmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDAO {
    private Context context;
    private SQLiteDatabase database;

    public UserDAO(Context context) {
        this.context = context;
        this.database = (new DatabaseUsers(context)).getWritableDatabase();
    }

    public ArrayList<User> getList() {
        ArrayList<User> result = new ArrayList<User>();
        String sql = "SELECT * FROM users ORDER BY name";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String function = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            result.add(new User(id, name, function, password, notes));
        }
        return result;
    }

    public boolean add(User user) {
        String sql = "INSERT INTO users VALUES (NULL, "
                + "'" + user.getName() + "', "
                + "'" + user.getFunction() + "', "
                + "'" + user.getPassword() + "', "
                + "'" + user.getNotes() + "')";
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean remove(int userId) {
        String sql = "DELETE FROM users WHERE id=" + userId+ ";";
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Usuário eliminado :(", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isRegistered(String name, String password){
        ArrayList<User> users = getList();
        int i = 0;
        User userAtual;
        while(i < users.size()){
            userAtual = users.get(i);
            if ((userAtual.getName().equals(name)) && (userAtual.getPassword().equals(password))){
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean update(User user) {
        String sql = "UPDATE users SET "
                + "name='" + user.getName() + "', "
                + "function='" + user.getFunction() + "', "
                + "password='" + user.getPassword() + "', "
                + "notes='" + user.getNotes() + "' "
                + "WHERE id=" + user.getId();
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public User get(int id) {
        String sql = "SELECT * FROM users WHERE id=" + id;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String function = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            return new User(id, name, function, password, notes);
        }
        return null;
    }
}
