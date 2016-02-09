/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.aeopensolutions.view.utils.JsfUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    public void errorInicioSesion() {
        try {
            JsfUtils.messageError(null, "Usuario y/o Contraseña incorrectos", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        System.out.println("logout...");
        try {
            HttpSession session = JsfUtils.getSession();
            session.invalidate();
            JsfUtils.getResponse().sendRedirect(JsfUtils.getRequest().getContextPath() + "/home.jsf?faces-redirect=true");
        } catch (Exception ex) {
            ex.getStackTrace();
        }

    }

}
