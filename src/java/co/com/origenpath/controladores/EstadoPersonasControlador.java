/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.EstadoPersonasFacadeLocal;
import co.com.origenpath.entidades.EstadoPersonas;
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
public class EstadoPersonasControlador {

    @EJB
    EstadoPersonasFacadeLocal estadoPersonasFacadeLocal;
    private EstadoPersonas estadoPersonas;

    @PostConstruct
    public void init() {
        estadoPersonas = new EstadoPersonas();
        estadoPersonas.setIdEstadoPersonas(buscarId()+1);
    }

    public EstadoPersonas getEstadoPersonas() {
        return estadoPersonas;
    }

    public void setEstadoPersonas(EstadoPersonas estadoPersonas) {
        this.estadoPersonas = estadoPersonas;
    }

    public void guardarEstadoPersonas() {
        try {
            estadoPersonasFacadeLocal.create(estadoPersonas);
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public List<EstadoPersonas> listarEstadoPersonas() {
        return estadoPersonasFacadeLocal.findAll();
    }

    public void buscarEstadoPersonas(Integer id) {
        EstadoPersonas ep = estadoPersonasFacadeLocal.find(id);
        estadoPersonas.setIdEstadoPersonas(ep.getIdEstadoPersonas());
        estadoPersonas.setNombreEstadoPersonas(ep.getNombreEstadoPersonas());
    }

    public void editarEstadoPersonas(EstadoPersonas ep) {
        try {
        EstadoPersonas e = new EstadoPersonas();
        e.setIdEstadoPersonas(ep.getIdEstadoPersonas());
        e.setNombreEstadoPersonas(ep.getNombreEstadoPersonas());
        estadoPersonasFacadeLocal.edit(e);
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void eliminarEstadoPersonas(Integer id) {
        try {
        EstadoPersonas ep = estadoPersonasFacadeLocal.find(id);
        estadoPersonasFacadeLocal.remove(ep);
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }
    
    public Integer buscarId(){
        int id = estadoPersonasFacadeLocal.count();
        return id;
    }

    /**
     * Creates a new instance of EstadoPersonas
     */
    public EstadoPersonasControlador() {
    }

}
