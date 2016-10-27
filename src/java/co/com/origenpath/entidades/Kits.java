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
@Table(name = "kits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kits.findAll", query = "SELECT k FROM Kits k"),
    @NamedQuery(name = "Kits.findByIdKits", query = "SELECT k FROM Kits k WHERE k.idKits = :idKits"),
    @NamedQuery(name = "Kits.findByNombreKit", query = "SELECT k FROM Kits k WHERE k.nombreKit = :nombreKit")})
public class Kits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KITS")
    private Integer idKits;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "NOMBRE_KIT")
    private String nombreKit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKit")
    private List<DetalleKit> detalleKitList;

    public Kits() {
    }

    public Kits(Integer idKits) {
        this.idKits = idKits;
    }

    public Kits(Integer idKits, String nombreKit) {
        this.idKits = idKits;
        this.nombreKit = nombreKit;
    }

    public Integer getIdKits() {
        return idKits;
    }

    public void setIdKits(Integer idKits) {
        this.idKits = idKits;
    }

    public String getNombreKit() {
        return nombreKit;
    }

    public void setNombreKit(String nombreKit) {
        this.nombreKit = nombreKit;
    }

    @XmlTransient
    public List<DetalleKit> getDetalleKitList() {
        return detalleKitList;
    }

    public void setDetalleKitList(List<DetalleKit> detalleKitList) {
        this.detalleKitList = detalleKitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKits != null ? idKits.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kits)) {
            return false;
        }
        Kits other = (Kits) object;
        if ((this.idKits == null && other.idKits != null) || (this.idKits != null && !this.idKits.equals(other.idKits))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.origenpath.entidades.Kits[ idKits=" + idKits + " ]";
    }
    
}
