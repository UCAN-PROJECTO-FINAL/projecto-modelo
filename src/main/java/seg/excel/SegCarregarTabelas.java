/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.excel;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @version 2
 * @author Helena
 */
@Named(value = "segCarregarTabelas")
@ApplicationScoped
public class SegCarregarTabelas implements Serializable
{

    @Inject 
    private SegFuncionalidadeExcelBean segFuncionalidadeExcelBean;

    public SegCarregarTabelas()
    {
    }

    /**
    * @version 1
    * @author Helena
    */
    public void actualizarTabelas()
    {
		System.err.println("0: SegCarregarTabelas.actualizarTabelas()");
        segFuncionalidadeExcelBean.carregar();
		System.err.println("3: SegCarregarTabelas.actualizarTabelas()");
    }
}
