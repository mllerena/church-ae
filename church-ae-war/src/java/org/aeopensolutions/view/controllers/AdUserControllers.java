/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.model.exceptions.ExecuteRollbackException;
import org.aeopensolutions.view.components.DataList;
import org.aeopensolutions.view.utils.JsfUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "adUser")
@ViewScoped
public class AdUserControllers implements Serializable {

    @Inject
    private AdUserFacade adUserFacade;

    private String pass1;

    private String pass2;
    
    @PostConstruct
    public void initialize(){
        listaUsuarios.load();
    }

    private DataList<AdUser> listaUsuarios = new DataList<AdUser>() {
        @Override
        protected void initialize() {
            System.out.println("initialize DataList AdUser");
        }

        @Override
        public List<AdUser> loadDataList() {
            return adUserFacade.findAll();
        }

        @Override
        protected AdUser create() {
            System.out.println("create aduser");
            setPass1(null);
            setPass2(null);
            return new AdUser();
        }

        @Override
        protected AdUser edit(AdUser item) {
            System.out.println("edit aduser: "+item);
            setPass1(item.getPassword());
            setPass2(item.getPassword());
            return item; 
        }
        
        

        @Override
        protected AdUser save(AdUser item) {
            System.out.println("save aduser: " + item);

            try {
                adUserFacade.save(item, getPass1(), getPass2());
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return null;
            }

            JsfUtils.messageInfo(null, "Usuario guardado correctamente.", null);

            return item;
        }

        @Override
        protected void delete(List<AdUser> items) {
            System.out.println("delete aduser: " + items);
            try {
                adUserFacade.delete(items);
            } catch (Exception e) {
                JsfUtils.messageError(null, e.getMessage(), null);
                return;
            }

            JsfUtils.messageInfo(null, "Usuario eliminado correctamente.", null);
        }

        @Override
        protected void cancel() {
            System.out.println("cancel user");
        }
        
        

    };

    public DataList<AdUser> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

}
