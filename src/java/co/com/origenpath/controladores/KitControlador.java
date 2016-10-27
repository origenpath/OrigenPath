/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.DetalleKitFacadeLocal;
import co.com.origenpath.dao.KitsFacadeLocal;
import co.com.origenpath.dao.ProductosFacadeLocal;
import co.com.origenpath.entidades.DetalleKit;
import co.com.origenpath.entidades.Kits;
import co.com.origenpath.entidades.Productos;
import co.com.origenpath.utils.jsfUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class KitControlador {

    @EJB
    KitsFacadeLocal kitFacadeLocal;
    @EJB
    DetalleKitFacadeLocal detalleKitFacadeLocal;
    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    Productos pro;
    private Kits kit;
    private DetalleKit detalleKit;
    private List<Kits> kits;
    private List<DetalleKit> listaDetalle = new ArrayList<>();

    @PostConstruct
    public void init() {
        kit = new Kits();
        detalleKit = new DetalleKit();
        kits = listarKits();

    }

    public Kits getKit() {
        return kit;
    }

    public void setKit(Kits kit) {
        this.kit = kit;
    }

    public List<Kits> getKits() {
        return kits;
    }

    public void setKits(List<Kits> kits) {
        this.kits = kits;
    }

    private List<Kits> listarKits() {
        return kitFacadeLocal.findAll();
    }

    public Productos getPro() {
        return pro;
    }

    public void setPro(Productos pro) {
        this.pro = pro;
    }

    public List<DetalleKit> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleKit> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public DetalleKit getDetalleKit() {
        return detalleKit;
    }

    public void setDetalleKit(DetalleKit detalleKit) {
        this.detalleKit = detalleKit;
    }

    public void guardarKit() {
        try {
            kitFacadeLocal.create(kit);
            for (DetalleKit det : listaDetalle) {
                int id = detalleKitFacadeLocal.count();
                DetalleKit detalleList = new DetalleKit();
                detalleList.setIdDetalleKit(id + 1);
                detalleList.setCantidadProducto(det.getCantidadProducto());
                detalleList.setIdKit(det.getIdKit());
                detalleList.setReferenciaProducto(det.getReferenciaProducto());
                detalleKitFacadeLocal.create(detalleList);
            }
            kits = listarKits();
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void buscarKit(Integer id) {
        Kits k = kitFacadeLocal.find(id);
        kit.setIdKits(k.getIdKits());
        kit.setNombreKit(k.getNombreKit());
    }

    public void editarKit(Kits kit) {
        try {
            Kits k = new Kits();
            k.setIdKits(kit.getIdKits());
            k.setNombreKit(kit.getNombreKit());
            kitFacadeLocal.edit(k);
            kits = listarKits();
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void eliminarKit(Integer id) {
        try {
            Kits k = kitFacadeLocal.find(id);
            kitFacadeLocal.remove(k);
            kits = listarKits();
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    public void agregar() {
        try {
            DetalleKit detalle = new DetalleKit();
            detalle.setCantidadProducto(detalleKit.getCantidadProducto());
            detalle.setReferenciaProducto(pro);
            detalle.setIdKit(kit);
            this.listaDetalle.add(detalle);
            jsfUtils.addSuccessMessage("Producto Agregado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al agregar el producto verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    public List<Productos> completeStrin(String ref) {
        List<Productos> results = productosFacadeLocal.findByRef(ref);
        return results;
    }
    
    public void onRowEdit(RowEditEvent event) {
        jsfUtils.addSuccessMessage("Cantidad Modificada Con Exito");
    }
     
    public void onRowCancel(RowEditEvent event) {
        jsfUtils.addSuccessMessage("Cantidad No Modificada");
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Creates a new instance of KitControlador
     */
    public KitControlador() {
    }

}
