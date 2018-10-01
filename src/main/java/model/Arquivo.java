/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class Arquivo {
    public static void setObj(File arquivo, Object obj, boolean escrita) throws FileNotFoundException, IOException {
        ObjectOutputStream out = null;
        
        if (arquivo.exists() && escrita) {
            out = new ObjectOutputStream(new FileOutputStream(arquivo, escrita)) {
                    @Override
                    protected void writeStreamHeader() {
                        // do not write a header
                    }
                };
        } else {
            out = new ObjectOutputStream(new FileOutputStream(arquivo, escrita));
        }
        
        out.writeObject(obj);
        out.close();
    }

    public static ArrayList<Object> getObj(File arquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        if(arquivo.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
            ArrayList<Object> objList = new ArrayList();

            try {
                do {
                    objList.add(ois.readObject());
                } while(true);
            } catch(EOFException ex) {
                System.out.println("Fim do arquivo " + ex);
            }
            
            return objList;
        } else {
            return new ArrayList<>();
        }
    }
    
    public static void apagaArquivo(File arquivo) {
        arquivo.delete();
    }
    
    public static File getArquivo(String diretorio, String arquivo) {
        File dir = new File(diretorio);
        dir.mkdir();
        
        File arq = new File(dir, arquivo);
        
        return arq;
    }
}
