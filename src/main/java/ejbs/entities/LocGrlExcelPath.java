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
@Table(name = "loc_grl_excel_path", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "LocGrlExcelPath.findAll", query = "SELECT l FROM LocGrlExcelPath l"),
    @NamedQuery(name = "LocGrlExcelPath.findByPkLocGrlExcelPath", query = "SELECT l FROM LocGrlExcelPath l WHERE l.pkLocGrlExcelPath = :pkLocGrlExcelPath"),
    @NamedQuery(name = "LocGrlExcelPath.findByPath", query = "SELECT l FROM LocGrlExcelPath l WHERE l.path = :path")
})
public class LocGrlExcelPath implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_loc_grl_excel_path", nullable = false)
    private Integer pkLocGrlExcelPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String path;

    public LocGrlExcelPath()
    {
    }

    public LocGrlExcelPath(Integer pkLocGrlExcelPath)
    {
        this.pkLocGrlExcelPath = pkLocGrlExcelPath;
    }

    public LocGrlExcelPath(Integer pkLocGrlExcelPath, String path)
    {
        this.pkLocGrlExcelPath = pkLocGrlExcelPath;
        this.path = path;
    }

    public Integer getPkLocGrlExcelPath()
    {
        return pkLocGrlExcelPath;
    }

    public void setPkLocGrlExcelPath(Integer pkLocGrlExcelPath)
    {
        this.pkLocGrlExcelPath = pkLocGrlExcelPath;
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
        hash += (pkLocGrlExcelPath != null ? pkLocGrlExcelPath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocGrlExcelPath))
        {
            return false;
        }
        LocGrlExcelPath other = (LocGrlExcelPath) object;
        if ((this.pkLocGrlExcelPath == null && other.pkLocGrlExcelPath != null) || (this.pkLocGrlExcelPath != null && !this.pkLocGrlExcelPath.equals(other.pkLocGrlExcelPath)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.LocGrlExcelPath[ pkLocGrlExcelPath=" + pkLocGrlExcelPath + " ]";
    }
    
}
