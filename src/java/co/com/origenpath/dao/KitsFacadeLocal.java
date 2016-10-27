/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.origenpath.dao;

import co.com.origenpath.entidades.Kits;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author daniel
 */
@Local
public interface KitsFacadeLocal {

    void create(Kits kits);

    void edit(Kits kits);

    void remove(Kits kits);

    Kits find(Object id);

    List<Kits> findAll();

    List<Kits> findRange(int[] range);

    int count();
    
}
