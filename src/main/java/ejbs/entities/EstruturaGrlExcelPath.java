/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_grl_excel_path", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "EstruturaGrlExcelPath.findAll", query = "SELECT e FROM EstruturaGrlExcelPath e"),
    @NamedQuery(name = "EstruturaGrlExcelPath.findByPkGrlExcelPath", query = "SELECT e FROM EstruturaGrlExcelPath e WHERE e.pkGrlExcelPath = :pkGrlExcelPath"),
    @NamedQuery(name = "EstruturaGrlExcelPath.findByPath", query = "SELECT e FROM EstruturaGrlExcelPath e WHERE e.path = :path")
})
public class EstruturaGrlExcelPath implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_grl_excel_path", nullable = false)
    private Integer pkGrlExcelPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String path;

    public EstruturaGrlExcelPath()
    {
    }

    public EstruturaGrlExcelPath(Integer pkGrlExcelPath)
    {
        this.pkGrlExcelPath = pkGrlExcelPath;
    }

    public EstruturaGrlExcelPath(Integer pkGrlExcelPath, String path)
    {
        this.pkGrlExcelPath = pkGrlExcelPath;
        this.path = path;
    }

    public Integer getPkGrlExcelPath()
    {
        return pkGrlExcelPath;
    }

    public void setPkGrlExcelPath(Integer pkGrlExcelPath)
    {
        this.pkGrlExcelPath = pkGrlExcelPath;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlExcelPath != null ? pkGrlExcelPath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaGrlExcelPath))
        {
            return false;
        }
        EstruturaGrlExcelPath other = (EstruturaGrlExcelPath) object;
        if ((this.pkGrlExcelPath == null && other.pkGrlExcelPath != null) || (this.pkGrlExcelPath != null && !this.pkGrlExcelPath.equals(other.pkGrlExcelPath)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaGrlExcelPath[ pkGrlExcelPath=" + pkGrlExcelPath + " ]";
    }
    
}
