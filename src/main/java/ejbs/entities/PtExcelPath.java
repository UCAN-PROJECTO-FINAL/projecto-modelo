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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_excel_path", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtExcelPath.findAll", query = "SELECT p FROM PtExcelPath p"),
    @NamedQuery(name = "PtExcelPath.findByPkPtExcelPath", query = "SELECT p FROM PtExcelPath p WHERE p.pkPtExcelPath = :pkPtExcelPath"),
    @NamedQuery(name = "PtExcelPath.findByPath", query = "SELECT p FROM PtExcelPath p WHERE p.path = :path")
})
public class PtExcelPath implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_excel_path", nullable = false)
    private Integer pkPtExcelPath;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String path;

    public PtExcelPath()
    {
    }

    public PtExcelPath(Integer pkPtExcelPath)
    {
        this.pkPtExcelPath = pkPtExcelPath;
    }

    public Integer getPkPtExcelPath()
    {
        return pkPtExcelPath;
    }

    public void setPkPtExcelPath(Integer pkPtExcelPath)
    {
        this.pkPtExcelPath = pkPtExcelPath;
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
        hash += (pkPtExcelPath != null ? pkPtExcelPath.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtExcelPath))
        {
            return false;
        }
        PtExcelPath other = (PtExcelPath) object;
        if ((this.pkPtExcelPath == null && other.pkPtExcelPath != null) || (this.pkPtExcelPath != null && !this.pkPtExcelPath.equals(other.pkPtExcelPath)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtExcelPath[ pkPtExcelPath=" + pkPtExcelPath + " ]";
    }
    
}
