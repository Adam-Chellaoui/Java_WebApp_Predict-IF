package Action;


import javax.servlet.http.HttpServletRequest;
import metier.service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamchellaoui
 */
  
public abstract class Action {
    
    
    public abstract void execute(HttpServletRequest request);
    
}