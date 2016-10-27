/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "tipo_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMenu.findAll", query = "SELECT t FROM TipoMenu t"),
    @NamedQuery(name = "TipoMenu.findByCodigoTipo", query = "SELECT t FROM TipoMenu t WHERE t.codigoTipo = :codigoTipo"),
    @NamedQuery(name = "TipoMenu.findByNombreTipo", query = "SELECT t FROM TipoMenu t WHERE t.nombreTipo = :nombreTipo")})
public class TipoMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO")
    private Integer codigoTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "NOMBRE_TIPO")
    private String nombreTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMenu")
    private List<Menu> menuList;

    public TipoMenu() {
    }

    public TipoMenu(Integer codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public TipoMenu(Integer codigoTipo, String nombreTipo) {
        this.codigoTipo = codigoTipo;
        this.nombreTipo = nombreTipo;
    }

    public Integer getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(Integer codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipo != null ? codigoTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMenu)) {
            return false;
        }
        TipoMenu other = (TipoMenu) object;
        if ((this.codigoTipo == null && other.codigoTipo != null) || (this.codigoTipo != null && !this.codigoTipo.equals(other.codigoTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.origenpath.entidades.TipoMenu[ codigoTipo=" + codigoTipo + " ]";
    }
    
}
