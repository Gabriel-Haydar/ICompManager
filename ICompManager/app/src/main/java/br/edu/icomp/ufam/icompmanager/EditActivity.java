package br.edu.icomp.ufam.icompmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private int userId;
    private TextView editName, editFunction, editPassword, editNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editName = findViewById(R.id.addName);
        editFunction = findViewById(R.id.addFunction);
        editPassword = findViewById(R.id.addPassword);
        editNotes = findViewById(R.id.addNotes);
        userDAO = new UserDAO(this);
        Intent intent = getIntent();
        userId = intent.getIntExtra("passwordId", -1);
// Verifica se uma senha foi passada como parâmetro
        if (userId != -1) {
            User user = userDAO.get(userId);
            editName.setText(user.getName());
            editFunction.setText(user.getFunction());
            editPassword.setText(user.getPassword());
            editNotes.setText(user.getNotes());
        }
    }

    public void salvarClicado(View view) {
        User user = new User(userId, editName.getText().toString(),
                editFunction.getText().toString(), editPassword.getText().toString(),
                editNotes.getText().toString());
        boolean result;
        if (userId == -1) result = userDAO.add(user);
        else result = userDAO.update(user);
        if (result) finish();
    }

    public void removeClicked(View view) {
        boolean result;
        result = userDAO.remove(userId);
        if (result) finish();
    }
}