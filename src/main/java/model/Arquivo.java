/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author rodrigo
 */
public class Arquivo {
    public static void setTexto(File diretorio, File arquivo, String dado, boolean escrita) {
        try {
            BufferedWriter escritorbuffer = new BufferedWriter(new FileWriter(arquivo, escrita));
            escritorbuffer.write(dado + "\n");
            escritorbuffer.close();
        } catch (IOException erro) {
            System.err.println("Erro na escrita do arquivo. " + erro);
        }
    }

    public static String getTexto(File arquivo) throws FileNotFoundException, IOException {
        
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader leitorbuffer = new BufferedReader(
                    new FileReader(arquivo));
            while (leitorbuffer.ready()) {
                str.append(leitorbuffer.readLine()).append("\n");
            }
            leitorbuffer.close();
        } catch (IOException erro) {
            System.err.println("Erro na leitura do arquivo. " + erro);
        } finally {
            return str.toString();
        }
        
    }
    
    public static File getArquivo(String diretorio, String arquivo) {
        File dir = new File(diretorio);
        dir.mkdir();
        
        File arq = new File(dir, arquivo);
        
        return arq;
    }
}
