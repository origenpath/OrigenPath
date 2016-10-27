/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.EstadoPedidoFacadeLocal;
import co.com.origenpath.entidades.EstadoPedido;
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
public class EstadoPedidoControlador {

    @EJB
    EstadoPedidoFacadeLocal estadoPedidoFacadeLocal;

    EstadoPedido estadoPedido;

    @PostConstruct
    public void init() {
        estadoPedido = new EstadoPedido();
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void guardarEstadoPedido() {
        estadoPedidoFacadeLocal.create(estadoPedido);
    }

    public List<EstadoPedido> listarEstadoPedido() {
        return estadoPedidoFacadeLocal.findAll();
    }

    public void buscarEstadoPedido(Integer id) {
        try {
            EstadoPedido ep = estadoPedidoFacadeLocal.find(id);
            estadoPedido.setIdEstadoPedido(ep.getIdEstadoPedido());
            estadoPedido.setNombreEstadoPedido(ep.getNombreEstadoPedido());
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void editarEstadoPedido(EstadoPedido estadoPedido) {
        try {
            EstadoPedido estped = new EstadoPedido();
            estped.setIdEstadoPedido(estadoPedido.getIdEstadoPedido());
            estped.setNombreEstadoPedido(estadoPedido.getNombreEstadoPedido());
            estadoPedidoFacadeLocal.edit(estped);
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void eliminarEstadoPedido(Integer id) {
        try {
            EstadoPedido ep = estadoPedidoFacadeLocal.find(id);
            estadoPedidoFacadeLocal.remove(ep);
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    /**
     * Creates a new instance of DetallePedidoFacade
     */
    public EstadoPedidoControlador() {
    }

}
