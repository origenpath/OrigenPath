    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.controladores;

import co.com.origenpath.dao.BarriosFacadeLocal;
import co.com.origenpath.entidades.Barrios;
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
public class BarrioControlador {

    @EJB
    BarriosFacadeLocal barriosFacadeLocal;
    private Barrios barrios;

    @PostConstruct
    public void init() {
        barrios = new Barrios();
    }

    public Barrios getBarrios() {
        return barrios;
    }

    public void setBarrios(Barrios barrios) {
        this.barrios = barrios;
    }

    public void guardarBarrio() {
        try {
            barriosFacadeLocal.create(barrios);
            jsfUtils.addSuccessMessage("Registro Guardado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al guardar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    public List<Barrios> listarBarrios() {
        return barriosFacadeLocal.findAll();
    }

    public void buscarBarrio(Integer id) {
        Barrios b = barriosFacadeLocal.find(id);
        barrios.setIdBarrio(b.getIdBarrio());
        barrios.setNombreBarrio(b.getNombreBarrio());
    }

    public void editarBarrio(Barrios barrio) {
        try {
            Barrios b = new Barrios();
            b.setIdBarrio(barrio.getIdBarrio());
            b.setNombreBarrio(barrio.getNombreBarrio());
            barriosFacadeLocal.edit(b);
            jsfUtils.addSuccessMessage("Registro Editado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al editar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    public void eliminarBarrio(Integer id) {
        try {
            Barrios b = barriosFacadeLocal.find(id);
            barriosFacadeLocal.remove(b);
            jsfUtils.addSuccessMessage("Registro eliminado Correctamente");
        } catch (Exception e) {
            jsfUtils.addErrorMessage("Error al eliminar el registro verifique los datos o pongase en contacto con el adminstrador del sistema");
        }

    }

    /**
     * Creates a new instance of BarrioControlador
     */
    public BarrioControlador() {
    }

}
