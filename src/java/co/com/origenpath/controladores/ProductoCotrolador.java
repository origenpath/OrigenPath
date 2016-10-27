/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.EstadoProductoFacadeLocal;
import co.com.origenpath.dao.ProductosFacadeLocal;
import co.com.origenpath.dao.ProveedorFacadeLocal;
import co.com.origenpath.dao.TipoProductoFacadeLocal;
import co.com.origenpath.entidades.EstadoProducto;
import co.com.origenpath.entidades.Productos;
import co.com.origenpath.entidades.Proveedor;
import co.com.origenpath.entidades.TipoProducto;
import co.com.origenpath.utils.jsfUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author daniel
 */
@ManagedBean
@ViewScoped
public class ProductoCotrolador implements Serializable {

    @EJB
    TipoProductoFacadeLocal tipoProductoFacadeLocal;
    @EJB
    EstadoProductoFacadeLocal estadoProductoFacadeLocal;
    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    @EJB
    ProductosFacadeLocal productosFacadeLocal;

    private Productos productos;
    private List<Productos> listProductos;
    private List<TipoProducto> tipoProductos;
    private List<EstadoProducto> estadoProductos;
    private List<Proveedor> proveedores;

    private String imagenProducto;

    @PostConstruct
    public void init() {
        productos = new Productos();
        listProductos = listarProductos();
        tipoProductos = listarTipoProductos();
        estadoProductos = listarEstadoProductos();
        proveedores = listarProveedores();
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public List<Productos> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Productos> listProductos) {
        this.listProductos = listProductos;
    }

    public List<TipoProducto> getTipoProductos() {
        return tipoProductos;
    }

    public void setTipoProductos(List<TipoProducto> tipoProductos) {
        this.tipoProductos = tipoProductos;
    }

    public List<EstadoProducto> getEstadoProductos() {
        return estadoProductos;
    }

    public void setEstadoProductos(List<EstadoProducto> estadoProductos) {
        this.estadoProductos = estadoProductos;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public void guardarProducto() {
        try {
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
        productosFacadeLocal.create(productos);
    }
    
    public void buscarProducto(String referencia){
        Productos pro = productosFacadeLocal.find(referencia);
        productos.setReferenciaProducto(pro.getReferenciaProducto());
        productos.setNombreProducto(pro.getNombreProducto());
        productos.setImagenProducto(pro.getImagenProducto());
        productos.setCantidadProducto(pro.getCantidadProducto());
        productos.setPrecioVenta(pro.getPrecioVenta());
        productos.setPrecioCompra(pro.getPrecioCompra());
        productos.setGramos(pro.getGramos());
        productos.setTipoProducto(pro.getTipoProducto());
        productos.setEstadoProducto(pro.getEstadoProducto());
        productos.setProveedor(pro.getProveedor());
        productos.setDescripcion(pro.getDescripcion());
        
    }
    
    public void modificarProducto(Productos prodcutos,FileUploadEvent event){
        Productos prod = new Productos();
        prod.setReferenciaProducto(prodcutos.getReferenciaProducto());
        prod.setNombreProducto(prodcutos.getNombreProducto());
        prod.setImagenProducto(prodcutos.getImagenProducto());
        prod.setCantidadProducto(prodcutos.getCantidadProducto());
        prod.setPrecioVenta(prodcutos.getPrecioVenta());
        prod.setPrecioCompra(prodcutos.getPrecioCompra());
        prod.setGramos(prodcutos.getGramos());
        prod.setTipoProducto(prodcutos.getTipoProducto());
        prod.setEstadoProducto(prodcutos.getEstadoProducto());
        prod.setProveedor(prodcutos.getProveedor());
        prod.setDescripcion(prodcutos.getDescripcion());
        productosFacadeLocal.edit(prod);
        listProductos = listarProductos(); 
        jsfUtils.addSuccessMessage("Registro Modificado Correctamente");
    }
    
    /*public void consultarImagen()throws {
        
    }*/

    public void eliminarProducto(String id) {
        try {
            Productos p = productosFacadeLocal.find(id);
            productosFacadeLocal.remove(p);
            listProductos = listarProductos();
            jsfUtils.addSuccessMessage("Registro Eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }
    }

    private List<Productos> listarProductos() {
        return productosFacadeLocal.findAll();
    }

    private List<TipoProducto> listarTipoProductos() {
        return tipoProductoFacadeLocal.findAll();
    }

    private List<EstadoProducto> listarEstadoProductos() {
        return estadoProductoFacadeLocal.findAll();
    }

    private List<Proveedor> listarProveedores() {
        return proveedorFacadeLocal.findAll();
    }

    /*
     Metodo para subir imagen al servidor
     */
    public void subirImagen(FileUploadEvent event) {
        FacesMessage mensaje = new FacesMessage();
        try {
            productos.setImagenProducto(event.getFile().getContents());
            imagenProducto = jsfUtils.guardarImagenTemporal(productos.getImagenProducto(), event.getFile().getFileName());
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Imagen subida con exito!");
        } catch (Exception e) {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Problemas al subir la imagen");
        }
        FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
    }

    /**
     * Creates a new instance of ProductoCotrolador
     */
    public ProductoCotrolador() {
    }

}
