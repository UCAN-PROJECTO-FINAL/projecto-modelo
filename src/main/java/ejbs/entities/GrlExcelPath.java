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
@Table(name = "grl_excel_path", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlExcelPath.findAll", query = "SELECT g FROM GrlExcelPath g"),
    @NamedQuery(name = "GrlExcelPath.findByPkExcelPath", query = "SELECT g FROM GrlExcelPath g WHERE g.pkExcelPath = :pkExcelPath"),
    @NamedQuery(name = "GrlExcelPath.findByPath", query = "SELECT g FROM GrlExcelPath g WHERE g.path = :path")
})
public class GrlExcelPath implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_excel_path", nullable = false)
    private Integer pkExcelPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String path;

    public GrlExcelPath()
    {
    }

    public GrlExcelPath(Integer pkExcelPath)
    {
        this.pkExcelPath = pkExcelPath;
    }

    public GrlExcelPath(Integer pkExcelPath, String path)
    {
        this.pkExcelPath = pkExcelPath;
        this.path = path;
    }

    public Integer getPkExcelPath()
    {
        return pkExcelPath;
    }

    public void setPkExcelPath(Integer pkExcelPath)
    {
        this.pkExcelPath = pkExcelPath;
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
        hash += (pkExcelPath != null ? pkExcelPath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlExcelPath))
        {
            return false;
        }
        GrlExcelPath other = (GrlExcelPath) object;
        if ((this.pkExcelPath == null && other.pkExcelPath != null) || (this.pkExcelPath != null && !this.pkExcelPath.equals(other.pkExcelPath)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlExcelPath[ pkExcelPath=" + pkExcelPath + " ]";
    }
    
}
