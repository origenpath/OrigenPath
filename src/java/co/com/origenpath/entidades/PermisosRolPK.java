/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author daniel
 */
@Embeddable
public class PermisosRolPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERMISO_R")
    private int codigoPermisoR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ROL")
    private int codigoRol;

    public PermisosRolPK() {
    }

    public PermisosRolPK(int codigoPermisoR, int codigoRol) {
        this.codigoPermisoR = codigoPermisoR;
        this.codigoRol = codigoRol;
    }

    public int getCodigoPermisoR() {
        return codigoPermisoR;
    }

    public void setCodigoPermisoR(int codigoPermisoR) {
        this.codigoPermisoR = codigoPermisoR;
    }

    public int getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(int codigoRol) {
        this.codigoRol = codigoRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPermisoR;
        hash += (int) codigoRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisosRolPK)) {
            return false;
        }
        PermisosRolPK other = (PermisosRolPK) object;
        if (this.codigoPermisoR != other.codigoPermisoR) {
            return false;
        }
        if (this.codigoRol != other.codigoRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.origenpath.entidades.PermisosRolPK[ codigoPermisoR=" + codigoPermisoR + ", codigoRol=" + codigoRol + " ]";
    }
    
}
