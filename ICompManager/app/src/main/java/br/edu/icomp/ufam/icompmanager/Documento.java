package br.edu.icomp.ufam.icompmanager;

public class Documento {
    private int id;
    private String data;
    private String interessado;
    private String tipo;
    private String descricao;
    private String armazenamento;
    private String armazenamentoCompleto;

    public Documento(int id, String data, String interessado, String tipo, String descricao, String armazenamento, String armazenamentoCompleto){
        this.setId(id);
        this.setData(data);
        this.setInteressado(interessado);
        this.setTipo(tipo);
        this.setDescricao(descricao);
        this.setArmazenamento(armazenamento);
        this.setArmazenamentoCompleto(armazenamentoCompleto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInteressado() {
        return interessado;
    }

    public void setInteressado(String interessado) {
        this.interessado = interessado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String getArmazenamentoCompleto() {
        return armazenamentoCompleto;
    }

    public void setArmazenamentoCompleto(String armazenamentoCompleto) {
        this.armazenamentoCompleto = armazenamentoCompleto;
    }
}
