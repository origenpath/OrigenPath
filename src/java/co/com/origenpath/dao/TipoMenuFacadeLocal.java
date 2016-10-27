/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.dao;

import co.com.origenpath.entidades.TipoMenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author daniel
 */
@Local
public interface TipoMenuFacadeLocal {

    void create(TipoMenu tipoMenu);

    void edit(TipoMenu tipoMenu);

    void remove(TipoMenu tipoMenu);

    TipoMenu find(Object id);

    List<TipoMenu> findAll();

    List<TipoMenu> findRange(int[] range);

    int count();
    
}
