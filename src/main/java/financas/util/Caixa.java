/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author majoao
 */
public class Caixa implements Serializable{

    private String cx = "";
    private String user = "";
    private String estado = "";
    private Date data;

    public Caixa() {
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
