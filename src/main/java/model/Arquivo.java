/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rodrigo
 */
public class Arquivo {
    public static void setTexto(File diretorio, File arquivo, String dado) {
        try {
            String[] arquivos = diretorio.list();
            
            FileWriter escritor = new FileWriter(arquivo, true);
            BufferedWriter escritorBuffer = new BufferedWriter(escritor);
            
            escritorBuffer.write(dado);
            
            escritor.close();
            escritorBuffer.close();
        } catch (IOException erro) {
            System.err.println("Erro na escrita do arquivo. " + erro);
        }
    }

    public static String getTexto(File arquivo) {
        try {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader leitorbuffer = new BufferedReader(leitor);
            StringBuilder stringDados = new StringBuilder();
            
            while (leitorbuffer.ready()) {
                stringDados.append(leitorbuffer.readLine());
                stringDados.append("\n");
            }
            leitor.close();
            leitorbuffer.close();
            
            return stringDados.toString();
        } catch (IOException erro) {
            System.err.println("Erro ao ler arquivo. " + erro);
        }
        
        return null;
    }
    
    public static File getArquivo(String diretorio, String arquivo) {
        File dir = new File(diretorio);
        dir.mkdir();
        
        File arq = new File(dir, arquivo);
        
        return arq;
    }
}
