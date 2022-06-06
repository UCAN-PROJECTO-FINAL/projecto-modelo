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
public enum NotaTipoEnum
{
    SUCESSO("green", "Sucesso"),
    INFORMACAO("orange", "Informação"),
    ERRO("red", "Erro");

    private final String color, descricao;

    private NotaTipoEnum(String cor, String descricao)
    {
        this.color = cor;
        this.descricao = descricao;
    }

    public int toInteger()
    {
        return this.ordinal() + 1;
    }

    public static NotaTipoEnum fromInteger(int x)
    {
        for (NotaTipoEnum estado : NotaTipoEnum.values())
        {
            if (estado.toInteger() == x)
            {
                return estado;
            }
        }
        return null;
    }

    public static NotaTipoEnum fromCor(String str)
    {
        for (NotaTipoEnum estado : NotaTipoEnum.values())
        {
            if (estado.color.equals(str))
            {
                return estado;
            }
        }
        return null;
    }

    public static NotaTipoEnum fromDescricao(String str)
    {
        for (NotaTipoEnum estado : NotaTipoEnum.values())
        {
            if (estado.descricao.equals(str))
            {
                return estado;
            }
        }
        return null;
    }

    public String getColor()
    {
        return color;
    }

    public String getDescricao()
    {
        return descricao;
    }

}
