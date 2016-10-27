/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.EstadoProveedorFacadeLocal;
import co.com.origenpath.entidades.EstadoProveedor;
import co.com.origenpath.utils.jsfUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author daniel
 */
@ManagedBean
@RequestScoped
public class EstadoProveedorControlador {

    @EJB
    EstadoProveedorFacadeLocal estadoProveedorFacadeLocal;
    private EstadoProveedor estadoProveedor;

    @PostConstruct
    public void init() {
        estadoProveedor = new EstadoProveedor();
        estadoProveedor.setIdEstadoProveedor(buscarId()+1);
    }

    public EstadoProveedor getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(EstadoProveedor estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }

    public void guardarEstadoProveedor() {
        try {
            estadoProveedorFacadeLocal.create(estadoProveedor);
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public List<EstadoProveedor> listarEstadoProveedor() {
        return estadoProveedorFacadeLocal.findAll();
    }

    public void buscarEstadoProveedor(Integer id) {
        EstadoProveedor ep = estadoProveedorFacadeLocal.find(id);
        estadoProveedor.setIdEstadoProveedor(ep.getIdEstadoProveedor());
        estadoProveedor.setNombreEstadoProveedor(ep.getNombreEstadoProveedor());
    }

    public void editarEstadoProveedor(EstadoProveedor ep) {
        try {
            EstadoProveedor estprov = new EstadoProveedor();
            estprov.setIdEstadoProveedor(ep.getIdEstadoProveedor());
            estprov.setNombreEstadoProveedor(ep.getNombreEstadoProveedor());
            estadoProveedorFacadeLocal.edit(estprov);
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void eliminarEstadoProveedor(Integer id) {
        try {
            EstadoProveedor estpro = estadoProveedorFacadeLocal.find(id);
            estadoProveedorFacadeLocal.remove(estpro);
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }
    
    public Integer buscarId(){
        int id=estadoProveedorFacadeLocal.count();
        return id;
    }

    /**
     * Creates a new instance of EstadoProveedorControlador
     */
    public EstadoProveedorControlador() {
    }

}
