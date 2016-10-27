/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.TipoProductoFacadeLocal;
import co.com.origenpath.entidades.TipoProducto;
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
public class TipoProductoControlador {

    @EJB
    TipoProductoFacadeLocal tipoProductoFacadeLocal;
    private TipoProducto tipoProducto;

    @PostConstruct
    public void init() {
        tipoProducto = new TipoProducto();
        tipoProducto.setIdTipoProducto(buscarId()+1);
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public void guardarTipoProducto() {
        try {
            tipoProductoFacadeLocal.create(tipoProducto);
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    public List<TipoProducto> listarTipoProductos() {
        return tipoProductoFacadeLocal.findAll();
    }

    public void buscarTipoProducto(Integer id) {
        TipoProducto tp = tipoProductoFacadeLocal.find(id);
        tipoProducto.setIdTipoProducto(tp.getIdTipoProducto());
        tipoProducto.setNombreTipoProducto(tp.getNombreTipoProducto());
    }

    public void editarTipoProducto(TipoProducto tp) {
        try {
            TipoProducto t = new TipoProducto();
            t.setIdTipoProducto(tp.getIdTipoProducto());
            t.setNombreTipoProducto(tp.getNombreTipoProducto());
            tipoProductoFacadeLocal.edit(t);
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void eliminarTipoProducto(Integer id) {
        try {
            TipoProducto tp = tipoProductoFacadeLocal.find(id);
            tipoProductoFacadeLocal.remove(tp);
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }
    
    public Integer buscarId(){
        int id = tipoProductoFacadeLocal.count();
        return id;
    }

    /**
     * Creates a new instance of TipoProductoControlador
     */
    public TipoProductoControlador() {
    }

}
