/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "bbt_exemplar_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtExemplarDocumento.findAll", query = "SELECT b FROM BbtExemplarDocumento b"),
    @NamedQuery(name = "BbtExemplarDocumento.findByPkBbtExemplarDocumento", query = "SELECT b FROM BbtExemplarDocumento b WHERE b.pkBbtExemplarDocumento = :pkBbtExemplarDocumento"),
    @NamedQuery(name = "BbtExemplarDocumento.findByDataEntrada", query = "SELECT b FROM BbtExemplarDocumento b WHERE b.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "BbtExemplarDocumento.findByCodInterno", query = "SELECT b FROM BbtExemplarDocumento b WHERE b.codInterno = :codInterno"),
    @NamedQuery(name = "BbtExemplarDocumento.findByNumRegisto", query = "SELECT b FROM BbtExemplarDocumento b WHERE b.numRegisto = :numRegisto")
})
public class BbtExemplarDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_exemplar_documento", nullable = false)
    private Integer pkBbtExemplarDocumento;
    @Column(name = "data_entrada")
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;
    @Column(name = "cod_interno")
    private Integer codInterno;
    @Size(max = 2147483647)
    @Column(name = "num_registo", length = 2147483647)
    private String numRegisto;
    @JoinColumn(name = "fk_bbt_biblioteca", referencedColumnName = "pk_bbt_biblioteca", nullable = false)
    @ManyToOne(optional = false)
    private BbtBiblioteca fkBbtBiblioteca;
    @JoinColumn(name = "fk_bbt_documento", referencedColumnName = "pk_bbt_documento", nullable = false)
    @ManyToOne(optional = false)
    private BbtDocumento fkBbtDocumento;

    public BbtExemplarDocumento()
    {
    }

    public BbtExemplarDocumento(Integer pkBbtExemplarDocumento)
    {
        this.pkBbtExemplarDocumento = pkBbtExemplarDocumento;
    }

    public Integer getPkBbtExemplarDocumento()
    {
        return pkBbtExemplarDocumento;
    }

    public void setPkBbtExemplarDocumento(Integer pkBbtExemplarDocumento)
    {
        this.pkBbtExemplarDocumento = pkBbtExemplarDocumento;
    }

    public Date getDataEntrada()
    {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada)
    {
        this.dataEntrada = dataEntrada;
    }

    public Integer getCodInterno()
    {
        return codInterno;
    }

    public void setCodInterno(Integer codInterno)
    {
        this.codInterno = codInterno;
    }

    public String getNumRegisto()
    {
        return numRegisto;
    }

    public void setNumRegisto(String numRegisto)
    {
        this.numRegisto = numRegisto;
    }

    public BbtBiblioteca getFkBbtBiblioteca()
    {
        return fkBbtBiblioteca;
    }

    public void setFkBbtBiblioteca(BbtBiblioteca fkBbtBiblioteca)
    {
        this.fkBbtBiblioteca = fkBbtBiblioteca;
    }

    public BbtDocumento getFkBbtDocumento()
    {
        return fkBbtDocumento;
    }

    public void setFkBbtDocumento(BbtDocumento fkBbtDocumento)
    {
        this.fkBbtDocumento = fkBbtDocumento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtExemplarDocumento != null ? pkBbtExemplarDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtExemplarDocumento))
        {
            return false;
        }
        BbtExemplarDocumento other = (BbtExemplarDocumento) object;
        if ((this.pkBbtExemplarDocumento == null && other.pkBbtExemplarDocumento != null) || (this.pkBbtExemplarDocumento != null && !this.pkBbtExemplarDocumento.equals(other.pkBbtExemplarDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtExemplarDocumento[ pkBbtExemplarDocumento=" + pkBbtExemplarDocumento + " ]";
    }
    
}
