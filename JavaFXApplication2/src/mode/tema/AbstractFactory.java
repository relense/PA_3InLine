/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mode.tema;

import model.peca.PecaFactory;

/**
 *
 * @author Miguel
 */
public abstract class AbstractFactory {
    
    public abstract PecaFactory getTema(String tema);
}
