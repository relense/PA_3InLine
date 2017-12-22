/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *Classe que representa o logger
 * @author Miguel
 */
public class Logger {

    private static Logger instance;
    private static final String LOG_FILE = "log.txt";
   /**
    * Método construtor privado 
    */
    private Logger() {

    }
    /**
     * Método para ir buscar uma instancia do logger
     * @return instance
     */    
    public static Logger getInstance() {
        if (instance == null)
            instance = new Logger();
        return instance;
    }
    /**
     * Método para adicionar algo ao logger
     * @param informacao informação a adicionar
     */
    public void addLogger(String informacao) {
        File file = new File(LOG_FILE);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(informacao + "\n");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Logg add failled");
        }

    }

}
