/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdUser;
import org.aeopensolutions.view.components.DataList;

/**
 *
 * @author Usuario
 */
@Named("adUser")
@SessionScoped
public class AdUserControllers implements Serializable{
    
    @Inject
    private AdUserFacade adUserFacade;
    
    
    private DataList<AdUser> listaUsuarios = new DataList<AdUser>() {
        @Override
        protected void initialize() {
            
        }

        @Override
        public List<AdUser> loadDataList() {
            return adUserFacade.findAll();
        }

        @Override
        protected AdUser create() {
            System.out.println("create aduser");
            return new AdUser(); 
        }

        @Override
        protected AdUser save(AdUser item) {
            System.out.println("save aduser");
            return new AdUser(); 
        }

        @Override
        protected void delete(List<AdUser> items) {
            System.out.println("delete aduser");
        }
        
        
        
        
    };

    public DataList<AdUser> getListaUsuarios() {
        return listaUsuarios;
    }
    
    
    
    
}
