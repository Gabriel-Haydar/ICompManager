package br.edu.icomp.ufam.icompmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DocumentoDAO {
    private Context context;
    private SQLiteDatabase database;

    public DocumentoDAO(Context context) {
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Documento> getList() {
        ArrayList<Documento> result = new ArrayList<Documento>();
        String sql = "SELECT * FROM documentos";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String data = cursor.getString(1);
            String interessado = cursor.getString(2);
            String tipo = cursor.getString(3);
            String descricao = cursor.getString(4);
            String armazenamento = cursor.getString(5);
            String armazenamentoCompleto = cursor.getString(6);
            result.add(new Documento(id, data, interessado, tipo, descricao, armazenamento,
                    armazenamentoCompleto));
        }
        return result;
    }

    public String showReport(){
        ArrayList<Documento> docs = getList();
        int i = 0;
        Documento docAtual;
        String report = "";
        while(i < docs.size()){
            docAtual = docs.get(i);
            report += "\nDocumento " + docAtual.getId()+ "\n\n----- Data: "
                    + docAtual.getData() + " Interessado: " + docAtual.getInteressado() + " Tipo: "
                    + docAtual.getTipo() + " Descrição: " + docAtual.getDescricao() +
                    " Endereço de Armazenamento: " + docAtual.getArmazenamento()
                    + " Endereço de Armazenamento Completo: " + docAtual.getArmazenamentoCompleto()
                    + "\n";
            i++;
        }
        return report;
    }

    public boolean add(Documento doc) {
        String sql = "INSERT INTO documentos VALUES (NULL, "
                + "'" + doc.getData() + "', "
                + "'" + doc.getInteressado() + "', "
                + "'" + doc.getTipo() + "', "
                + "'" + doc.getDescricao() + "', "
                + "'" + doc.getArmazenamento() + "', "
                + "'" + doc.getArmazenamentoCompleto() + "')";
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Documento salvo!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean remove(int docId) {
        String sql = "DELETE FROM documentos WHERE id=" + docId+ ";";
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Documento removido :(", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean update(Documento doc) {
        String sql = "UPDATE documentos SET "
                + "data='" + doc.getData() + "', "
                + "interessado='" + doc.getInteressado() + "', "
                + "tipo='" + doc.getTipo() + "', "
                + "descricao='" + doc.getDescricao() + "', "
                + "armazenamento='" + doc.getArmazenamento() + "', "
                + "armazenamentoCompleto='" + doc.getArmazenamentoCompleto() + "' "
                + "WHERE id=" + doc.getId();
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Documento atualizado!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public Documento get(int id) {
        String sql = "SELECT * FROM documentos WHERE id=" + id;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            String data = cursor.getString(1);
            String interessado = cursor.getString(2);
            String tipo = cursor.getString(3);
            String descricao = cursor.getString(4);
            String armazenamento = cursor.getString(5);
            String armazenamentoCompleto = cursor.getString(6);
            return new Documento(id, data, interessado, tipo, descricao, armazenamento, armazenamentoCompleto);
        }
        return null;
    }
}
