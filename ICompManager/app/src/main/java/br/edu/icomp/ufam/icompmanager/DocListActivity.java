package br.edu.icomp.ufam.icompmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DocListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DocListActivity.DocumentosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_list);
        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DocListActivity.DocumentosAdapter(this);
        recyclerView.setAdapter(adapter);
        TextView textBemVindo = findViewById(R.id.textBemVindo);
        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        textBemVindo.setText("Olá, " + login + "!");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    public void buttonAddClick(View view) {
        Intent intent = new Intent(this, DocEditActivity.class);
        startActivity(intent);
    }

    public void showReport(View view) {
        DocumentoDAO documentoDAO = new DocumentoDAO(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(documentoDAO.showReport())
                .setNeutralButton("Ok", null)
                .show();
    }

    class DocumentosAdapter extends RecyclerView.Adapter<DocListActivity.DocumentosViewHolder> implements Filterable {
        private Context context;
        private ArrayList<Documento> documentos;
        private ArrayList<Documento> documentosParaBusca;
        DocumentoDAO documentoDAO;
        public DocumentosAdapter(Context context) {
            this.context = context;
            documentoDAO = new DocumentoDAO(context);
            update();
        }
        public void update() { documentos = documentoDAO.getList(); documentosParaBusca = new ArrayList<>(documentos);}

        public DocListActivity.DocumentosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            DocListActivity.DocumentosViewHolder vh = new DocListActivity.DocumentosViewHolder(v, context);
            return vh;
        }
        public void onBindViewHolder(DocListActivity.DocumentosViewHolder holder, int position) {
            holder.tipo.setText(documentos.get(position).getTipo());
            holder.interessado.setText(documentos.get(position).getInteressado());
            holder.id = documentos.get(position).getId();
        }
        public int getItemCount() { return documentos.size(); }

        @Override
        public Filter getFilter() {
            return exampleFilter;
        }

        private Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Documento> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(documentosParaBusca);
                }
                else{
                    String filteredPattern = constraint.toString();

                    for (Documento doc : documentosParaBusca) {
                        if (doc.getTipo().contains(filteredPattern)||doc.getInteressado().contains(filteredPattern)){
                            filteredList.add(doc);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                documentos.clear();
                documentos.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    class DocumentosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView tipo, interessado;
        public int id;
        public DocumentosViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            interessado = v.findViewById(R.id.itemFunction);
            tipo = v.findViewById(R.id.itemName);
            v.setOnClickListener(this);
        }
        public void onClick(View v) {
            Intent intent = new Intent(context, DocEditActivity.class);
            intent.putExtra("docId", this.id);
            context.startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}