/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notas.utils;

/**
 *
 * @author aires
 */
public class NotaErro extends Nota
{
    public NotaErro(String nota)
    {
        super(nota, NotaTipoEnum.ERRO);
    }
    
}
