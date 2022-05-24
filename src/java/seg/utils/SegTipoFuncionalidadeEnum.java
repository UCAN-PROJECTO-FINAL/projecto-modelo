/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.entities.SegTipoFuncionalidade;

/**
 *
 * @author aires
 */
public enum SegTipoFuncionalidadeEnum
{
    SUBMENU("Submenu", "Menu"),
    MENUITEM_FORM("Menuitem-Form", "Item de Menu para Formulário"),
    MENUITEM_ACTION_LISTENER("Menuitem-ActionListener", "Item de Menu - Acção", "action"),
    HYPERLINK_CAIXA_DIALOGO("Hyperlink-CaixaDialogo", "Hyperlink para Caixa de Diálogo", "action"),
    HYPERLINK_FORM("HyperLink-Form", "Hyperlink para Formulário"),
    HYPERLINK_ACTION_LISTENER("Hyperlink-ActionListener", "Hyperlink - Acção"),
    BOTAO_FORM("Botao-Form", "Botão para Formulário"),
    BOTAO_CAIXA_DIALOGO("Botao-CaixaDialogo", "Botão para Caixa de Diálogo", "action"),
    BOTAO_ACTION_LISTENER("Botao-ActionListener", "Botão - Acção", "action"),
    FORM("Form", "Formulário", "document"),
    MENUITEM_CAIXA_DIALOGO("Menuitem-CaixaDialogo", "Item de Menu para Caixa de Diálogo", "action"),
    BOTAO_SEM_ACTIVIDADE("Botão Sem Actividade", "Botão Sem Actividade");

    private final String nome, descricao, tipo;

//    private SegTipoFuncionalidadeEnum(String nome)
//    {
//        this.nome = nome;
//        this.descricao = nome;
//    }
//    
    private SegTipoFuncionalidadeEnum(String nome, String descricao)
    {
        this.nome = nome;
        this.descricao = descricao;
        tipo = "";
    }
    
    private SegTipoFuncionalidadeEnum(String nome, String descricao, String tipo)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static SegTipoFuncionalidadeEnum fromInteger(int x)
    {
        for (SegTipoFuncionalidadeEnum estado : SegTipoFuncionalidadeEnum.values())
        {
            if (estado.toInteger() == x)
            {
                return estado;
            }
        }
        return null;
    }

    public static SegTipoFuncionalidadeEnum fromNome(String str)
    {
        for (SegTipoFuncionalidadeEnum estado : SegTipoFuncionalidadeEnum.values())
        {
            if (estado.nome.equals(str))
            {
                return estado;
            }
        }
        return null;
    }
    
    public static SegTipoFuncionalidadeEnum fromDescricao(String str)
    {
        for (SegTipoFuncionalidadeEnum estado : SegTipoFuncionalidadeEnum.values())
        {
            if (estado.descricao.equals(str))
            {
                return estado;
            }
        }
        return null;
    }

    public static SegTipoFuncionalidadeEnum fromSegTipoFuncionalidade(SegTipoFuncionalidade estado)
    {
        return fromInteger(estado.getPkSegTipoFuncionalidade());
    }

    
    // Getters and Setters
    public String getNome()
    {
        return nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public String getTipo()
    {
        return tipo;
    }

}
