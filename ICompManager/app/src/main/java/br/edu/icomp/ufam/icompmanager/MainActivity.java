package br.edu.icomp.ufam.icompmanager;


        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.preference.PreferenceManager;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void enterClicked(View view) {
        UserDAO userDAO = new UserDAO(this);
        String editLogin = ((EditText) findViewById(R.id.editLogin)).getText().toString();
        String editPass = ((EditText) findViewById(R.id.editPassword)).getText().toString();
        if (userDAO.isRegistered(editLogin, editPass)) {
            Intent intent = new Intent(this, DocListActivity.class);
            EditText inputLogin = findViewById(R.id.editLogin);
            intent.putExtra("login", inputLogin.getText().toString());
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Login/senha inválidos!", Toast.LENGTH_SHORT).show();
    }

    public void newUserClicked(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        Toast.makeText(this, "Bem vindo(a)!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Este gerenciador cadastra, remove, atualiza, e consulta documentos e usuários. Criado por Gabriel Isaac Gonçalves Haydar. \n\nIComp Manager v1.27.3.2015")
                        .setNeutralButton("Ok", null)
                        .show();
                return true;
            case R.id.configs:
                Intent intentConfig = new Intent(this, PreferencesActivity.class);
                startActivity(intentConfig);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}