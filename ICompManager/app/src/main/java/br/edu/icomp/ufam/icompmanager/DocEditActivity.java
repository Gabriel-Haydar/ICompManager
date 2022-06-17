package br.edu.icomp.ufam.icompmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class DocEditActivity extends AppCompatActivity {
    private DocumentoDAO documentoDAO;
    private int docId;
    private TextView editData, editInteressado, editTipo, editDescricao, editArmazenamento,
            editArmazenamentoCompleto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_edit);
        editData = findViewById(R.id.addName);
        editInteressado = findViewById(R.id.addFunction);
        editTipo = findViewById(R.id.addPassword);
        editDescricao = findViewById(R.id.addNotes);
        editArmazenamento = findViewById(R.id.addArmazenamento);
        editArmazenamentoCompleto = findViewById(R.id.addArmazenamentoCompleto);
        documentoDAO = new DocumentoDAO(this);
        Intent intent = getIntent();
        docId = intent.getIntExtra("docId", -1);
// Verifica se uma senha foi passada como parâmetro
        if (docId != -1) {
            Documento doc = documentoDAO.get(docId);
            editData.setText(doc.getData());
            editInteressado.setText(doc.getInteressado());
            editTipo.setText(doc.getTipo());
            editDescricao.setText(doc.getDescricao());
            editArmazenamento.setText(doc.getArmazenamento());
            editArmazenamentoCompleto.setText(doc.getArmazenamentoCompleto());
        }
        TextView textDocId = findViewById(R.id.textDocId);
        textDocId.setText(String.format("ID #%d", docId));
    }

    public void salvarClicado(View view) {
        Documento doc = new Documento(docId, editData.getText().toString(),
                editInteressado.getText().toString(), editTipo.getText().toString(),
                editDescricao.getText().toString(), editArmazenamento.getText().toString(),
                editArmazenamentoCompleto.getText().toString());
        boolean result;
        if (docId == -1) result = documentoDAO.add(doc);
        else result = documentoDAO.update(doc);
        if (result) finish();
    }

    public void removerClicado(View view) {
        boolean result;
        result = documentoDAO.remove(docId);
        if (result) finish();
    }
}