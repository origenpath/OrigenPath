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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByCodigoMenu", query = "SELECT m FROM Menu m WHERE m.codigoMenu = :codigoMenu"),
    @NamedQuery(name = "Menu.findByNombreMenu", query = "SELECT m FROM Menu m WHERE m.nombreMenu = :nombreMenu"),
    @NamedQuery(name = "Menu.findByUrl", query = "SELECT m FROM Menu m WHERE m.url = :url")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO_MENU")
    private Integer codigoMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE_MENU")
    private String nombreMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "URL")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<Menu> menuList;
    @JoinColumn(name = "ORDEN", referencedColumnName = "CODIGO_MENU")
    @ManyToOne(optional = false)
    private Menu orden;
    @JoinColumn(name = "TIPO_MENU", referencedColumnName = "CODIGO_TIPO")
    @ManyToOne(optional = false)
    private TipoMenu tipoMenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private List<PermisosUsuario> permisosUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private List<PermisosRol> permisosRolList;

    public Menu() {
    }

    public Menu(Integer codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public Menu(Integer codigoMenu, String nombreMenu, String url) {
        this.codigoMenu = codigoMenu;
        this.nombreMenu = nombreMenu;
        this.url = url;
    }

    public Integer getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(Integer codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getOrden() {
        return orden;
    }

    public void setOrden(Menu orden) {
        this.orden = orden;
    }

    public TipoMenu getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(TipoMenu tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    @XmlTransient
    public List<PermisosUsuario> getPermisosUsuarioList() {
        return permisosUsuarioList;
    }

    public void setPermisosUsuarioList(List<PermisosUsuario> permisosUsuarioList) {
        this.permisosUsuarioList = permisosUsuarioList;
    }

    @XmlTransient
    public List<PermisosRol> getPermisosRolList() {
        return permisosRolList;
    }

    public void setPermisosRolList(List<PermisosRol> permisosRolList) {
        this.permisosRolList = permisosRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMenu != null ? codigoMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.codigoMenu == null && other.codigoMenu != null) || (this.codigoMenu != null && !this.codigoMenu.equals(other.codigoMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.origenpath.entidades.Menu[ codigoMenu=" + codigoMenu + " ]";
    }
    
}
