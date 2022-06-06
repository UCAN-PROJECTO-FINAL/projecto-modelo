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
@Table(name = "ar_arquivo_morto", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArArquivoMorto.findAll", query = "SELECT a FROM ArArquivoMorto a"),
    @NamedQuery(name = "ArArquivoMorto.findByPkArquivoMorto", query = "SELECT a FROM ArArquivoMorto a WHERE a.pkArquivoMorto = :pkArquivoMorto"),
    @NamedQuery(name = "ArArquivoMorto.findByTitulo", query = "SELECT a FROM ArArquivoMorto a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "ArArquivoMorto.findByDataRegistro", query = "SELECT a FROM ArArquivoMorto a WHERE a.dataRegistro = :dataRegistro"),
    @NamedQuery(name = "ArArquivoMorto.findByEstado", query = "SELECT a FROM ArArquivoMorto a WHERE a.estado = :estado"),
    @NamedQuery(name = "ArArquivoMorto.findByCaminho", query = "SELECT a FROM ArArquivoMorto a WHERE a.caminho = :caminho")
})
public class ArArquivoMorto implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_arquivo_morto", nullable = false)
    private Integer pkArquivoMorto;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String titulo;
    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    private Boolean estado;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String caminho;
    @JoinColumn(name = "fk_ano_curricular", referencedColumnName = "pk_ano_curricular")
    @ManyToOne
    private ArAnoCurricular fkAnoCurricular;
    @JoinColumn(name = "fk_ano_lectivo", referencedColumnName = "pk_ano_lectivo")
    @ManyToOne
    private ArAnoLectivo fkAnoLectivo;
    @JoinColumn(name = "fk_cadeira", referencedColumnName = "pk_cadeira")
    @ManyToOne
    private ArCadeira fkCadeira;
    @JoinColumn(name = "fk_docente", referencedColumnName = "pk_docente")
    @ManyToOne
    private ArDocente fkDocente;
    @JoinColumn(name = "fk_periodo", referencedColumnName = "pk_periodo")
    @ManyToOne
    private ArPeriodo fkPeriodo;
    @JoinColumn(name = "fk_semestre", referencedColumnName = "pk_semestre")
    @ManyToOne
    private ArSemestre fkSemestre;
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "pk_tipo_documento")
    @ManyToOne
    private ArTipoDocumento fkTipoDocumento;
    @JoinColumn(name = "fk_turma", referencedColumnName = "pk_turma")
    @ManyToOne
    private ArTurma fkTurma;
    @JoinColumn(name = "fk_estrutura_logica_fisica", referencedColumnName = "pk_estrutura_logica_fisica")
    @ManyToOne
    private EstruturaLogicaFisica fkEstruturaLogicaFisica;

    public ArArquivoMorto()
    {
    }

    public ArArquivoMorto(Integer pkArquivoMorto)
    {
        this.pkArquivoMorto = pkArquivoMorto;
    }

    public Integer getPkArquivoMorto()
    {
        return pkArquivoMorto;
    }

    public void setPkArquivoMorto(Integer pkArquivoMorto)
    {
        this.pkArquivoMorto = pkArquivoMorto;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public Date getDataRegistro()
    {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro)
    {
        this.dataRegistro = dataRegistro;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public String getCaminho()
    {
        return caminho;
    }

    public void setCaminho(String caminho)
    {
        this.caminho = caminho;
    }

    public ArAnoCurricular getFkAnoCurricular()
    {
        return fkAnoCurricular;
    }

    public void setFkAnoCurricular(ArAnoCurricular fkAnoCurricular)
    {
        this.fkAnoCurricular = fkAnoCurricular;
    }

    public ArAnoLectivo getFkAnoLectivo()
    {
        return fkAnoLectivo;
    }

    public void setFkAnoLectivo(ArAnoLectivo fkAnoLectivo)
    {
        this.fkAnoLectivo = fkAnoLectivo;
    }

    public ArCadeira getFkCadeira()
    {
        return fkCadeira;
    }

    public void setFkCadeira(ArCadeira fkCadeira)
    {
        this.fkCadeira = fkCadeira;
    }

    public ArDocente getFkDocente()
    {
        return fkDocente;
    }

    public void setFkDocente(ArDocente fkDocente)
    {
        this.fkDocente = fkDocente;
    }

    public ArPeriodo getFkPeriodo()
    {
        return fkPeriodo;
    }

    public void setFkPeriodo(ArPeriodo fkPeriodo)
    {
        this.fkPeriodo = fkPeriodo;
    }

    public ArSemestre getFkSemestre()
    {
        return fkSemestre;
    }

    public void setFkSemestre(ArSemestre fkSemestre)
    {
        this.fkSemestre = fkSemestre;
    }

    public ArTipoDocumento getFkTipoDocumento()
    {
        return fkTipoDocumento;
    }

    public void setFkTipoDocumento(ArTipoDocumento fkTipoDocumento)
    {
        this.fkTipoDocumento = fkTipoDocumento;
    }

    public ArTurma getFkTurma()
    {
        return fkTurma;
    }

    public void setFkTurma(ArTurma fkTurma)
    {
        this.fkTurma = fkTurma;
    }

    public EstruturaLogicaFisica getFkEstruturaLogicaFisica()
    {
        return fkEstruturaLogicaFisica;
    }

    public void setFkEstruturaLogicaFisica(EstruturaLogicaFisica fkEstruturaLogicaFisica)
    {
        this.fkEstruturaLogicaFisica = fkEstruturaLogicaFisica;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkArquivoMorto != null ? pkArquivoMorto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArArquivoMorto))
        {
            return false;
        }
        ArArquivoMorto other = (ArArquivoMorto) object;
        if ((this.pkArquivoMorto == null && other.pkArquivoMorto != null) || (this.pkArquivoMorto != null && !this.pkArquivoMorto.equals(other.pkArquivoMorto)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArArquivoMorto[ pkArquivoMorto=" + pkArquivoMorto + " ]";
    }
    
}
