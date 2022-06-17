package br.edu.icomp.ufam.icompmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UsersAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdaptor(this);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void buttonAddClick(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    class UsersAdaptor extends RecyclerView.Adapter<UsersViewHolder> {
        private Context context;
        private ArrayList<User> users;
        UserDAO userDAO;
        public UsersAdaptor(Context context) {
            this.context = context;
            userDAO = new UserDAO(context);
            update();
        }
        public void update() { users = userDAO.getList(); }
        public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item_users, parent, false);
            UsersViewHolder vh = new UsersViewHolder(v, context);
            return vh;
        }
        public void onBindViewHolder(UsersViewHolder holder, int position) {
            holder.name.setText(users.get(position).getName());
            holder.function.setText(users.get(position).getFunction());
            holder.id = users.get(position).getId();
        }
        public int getItemCount() { return users.size(); }
    }

    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView function, name;
        public int id;
        public UsersViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            name = v.findViewById(R.id.itemName);
            function = v.findViewById(R.id.itemFunction);
            v.setOnClickListener(this);
        }
        public void onClick(View v) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("passwordId", this.id);
            context.startActivity(intent);
        }
    }
}