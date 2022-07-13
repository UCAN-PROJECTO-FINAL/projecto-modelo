/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_bem_movel", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemMovel.findAll", query = "SELECT p FROM PtBemMovel p"),
    @NamedQuery(name = "PtBemMovel.findByPkPtBemMovel", query = "SELECT p FROM PtBemMovel p WHERE p.pkPtBemMovel = :pkPtBemMovel"),
    @NamedQuery(name = "PtBemMovel.findByTaxaDepreciacaoMes", query = "SELECT p FROM PtBemMovel p WHERE p.taxaDepreciacaoMes = :taxaDepreciacaoMes"),
    @NamedQuery(name = "PtBemMovel.findByImagemPath", query = "SELECT p FROM PtBemMovel p WHERE p.imagemPath = :imagemPath"),
    @NamedQuery(name = "PtBemMovel.findByCod", query = "SELECT p FROM PtBemMovel p WHERE p.cod = :cod"),
    @NamedQuery(name = "PtBemMovel.findByDetalhes", query = "SELECT p FROM PtBemMovel p WHERE p.detalhes = :detalhes")
})
public class PtBemMovel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_bem_movel", nullable = false)
    private Integer pkPtBemMovel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxa_depreciacao_mes", nullable = false)
    private int taxaDepreciacaoMes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "imagem_path", nullable = false, length = 2147483647)
    private String imagemPath;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String cod;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String detalhes;
    @OneToMany(mappedBy = "fkPtBemMovel")
    private List<PtBemSaida> ptBemSaidaList;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica")
    @ManyToOne
    private EstruturaFisica fkEstruturaFisica;
    @JoinColumn(name = "fk_pt_bem_entrada", referencedColumnName = "pk_pt_bem_entrada", nullable = false)
    @ManyToOne(optional = false)
    private PtBemEntrada fkPtBemEntrada;
    @JoinColumn(name = "fk_pt_estado_conservacao", referencedColumnName = "pk_pt_estado_conservacao", nullable = false)
    @ManyToOne(optional = false)
    private PtEstadoConservacao fkPtEstadoConservacao;
    @JoinColumn(name = "fk_pt_modelo", referencedColumnName = "pk_pt_modelo", nullable = false)
    @ManyToOne(optional = false)
    private PtModelo fkPtModelo;
    @OneToMany(mappedBy = "fkPtBemMovel")
    private List<PtBemHistorico> ptBemHistoricoList;

    public PtBemMovel()
    {
    }

    public PtBemMovel(Integer pkPtBemMovel)
    {
        this.pkPtBemMovel = pkPtBemMovel;
    }

    public PtBemMovel(Integer pkPtBemMovel, int taxaDepreciacaoMes, String imagemPath)
    {
        this.pkPtBemMovel = pkPtBemMovel;
        this.taxaDepreciacaoMes = taxaDepreciacaoMes;
        this.imagemPath = imagemPath;
    }

    public Integer getPkPtBemMovel()
    {
        return pkPtBemMovel;
    }

    public void setPkPtBemMovel(Integer pkPtBemMovel)
    {
        this.pkPtBemMovel = pkPtBemMovel;
    }

    public int getTaxaDepreciacaoMes()
    {
        return taxaDepreciacaoMes;
    }

    public void setTaxaDepreciacaoMes(int taxaDepreciacaoMes)
    {
        this.taxaDepreciacaoMes = taxaDepreciacaoMes;
    }

    public String getImagemPath()
    {
        return imagemPath;
    }

    public void setImagemPath(String imagemPath)
    {
        this.imagemPath = imagemPath;
    }

    public String getCod()
    {
        return cod;
    }

    public void setCod(String cod)
    {
        this.cod = cod;
    }

    public String getDetalhes()
    {
        return detalhes;
    }

    public void setDetalhes(String detalhes)
    {
        this.detalhes = detalhes;
    }

    public List<PtBemSaida> getPtBemSaidaList()
    {
        return ptBemSaidaList;
    }

    public void setPtBemSaidaList(List<PtBemSaida> ptBemSaidaList)
    {
        this.ptBemSaidaList = ptBemSaidaList;
    }

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public PtBemEntrada getFkPtBemEntrada()
    {
        return fkPtBemEntrada;
    }

    public void setFkPtBemEntrada(PtBemEntrada fkPtBemEntrada)
    {
        this.fkPtBemEntrada = fkPtBemEntrada;
    }

    public PtEstadoConservacao getFkPtEstadoConservacao()
    {
        return fkPtEstadoConservacao;
    }

    public void setFkPtEstadoConservacao(PtEstadoConservacao fkPtEstadoConservacao)
    {
        this.fkPtEstadoConservacao = fkPtEstadoConservacao;
    }

    public PtModelo getFkPtModelo()
    {
        return fkPtModelo;
    }

    public void setFkPtModelo(PtModelo fkPtModelo)
    {
        this.fkPtModelo = fkPtModelo;
    }

    public List<PtBemHistorico> getPtBemHistoricoList()
    {
        return ptBemHistoricoList;
    }

    public void setPtBemHistoricoList(List<PtBemHistorico> ptBemHistoricoList)
    {
        this.ptBemHistoricoList = ptBemHistoricoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtBemMovel != null ? pkPtBemMovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemMovel))
        {
            return false;
        }
        PtBemMovel other = (PtBemMovel) object;
        if ((this.pkPtBemMovel == null && other.pkPtBemMovel != null) || (this.pkPtBemMovel != null && !this.pkPtBemMovel.equals(other.pkPtBemMovel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemMovel[ pkPtBemMovel=" + pkPtBemMovel + " ]";
    }
    
}
