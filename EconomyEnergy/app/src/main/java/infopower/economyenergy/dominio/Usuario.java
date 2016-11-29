package infopower.economyenergy.dominio;

import java.io.Serializable;

import okhttp3.ResponseBody;

/**
 * Created by Mathe on 31/10/2016.
 */

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String login;
    private String endereco;
    private byte[] imagem;
    private String senha;

    public Usuario(int id, String nome, String login, String endereco, byte[] imagem, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.endereco = endereco;
        this.imagem = imagem;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}