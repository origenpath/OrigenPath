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
import javax.validation.constraints.Size;

/**
 *
 * @author daniel
 */
@Embeddable
public class PermisosUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERMISO_U")
    private int codigoPermisoU;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CODIGO_USUARIO")
    private String codigoUsuario;

    public PermisosUsuarioPK() {
    }

    public PermisosUsuarioPK(int codigoPermisoU, String codigoUsuario) {
        this.codigoPermisoU = codigoPermisoU;
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoPermisoU() {
        return codigoPermisoU;
    }

    public void setCodigoPermisoU(int codigoPermisoU) {
        this.codigoPermisoU = codigoPermisoU;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPermisoU;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisosUsuarioPK)) {
            return false;
        }
        PermisosUsuarioPK other = (PermisosUsuarioPK) object;
        if (this.codigoPermisoU != other.codigoPermisoU) {
            return false;
        }
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.origenpath.entidades.PermisosUsuarioPK[ codigoPermisoU=" + codigoPermisoU + ", codigoUsuario=" + codigoUsuario + " ]";
    }
    
}
