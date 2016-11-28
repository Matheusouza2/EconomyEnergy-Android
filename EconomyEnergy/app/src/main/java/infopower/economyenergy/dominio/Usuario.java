package infopower.economyenergy.dominio;

import java.io.Serializable;

/**
 * Created by Mathe on 31/10/2016.
 */

public class Usuario implements Serializable {
    private String nome;
    private String login;
    private String endereco;
    private String imagem;

    public Usuario(String nome, String login, String endereco, String imagem) {
        this.nome = nome;
        this.login = login;
        this.endereco = endereco;
        this.imagem = imagem;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}