/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class RefeicaoDAO {
    public void insertRefeicao(Refeicao refeicao, boolean escreve) throws IOException {
        
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.obj";
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        Arquivo.setObj(arquivo, refeicao, escreve);
    }
    
    public ArrayList<Refeicao> getListRefeicao() throws IOException, FileNotFoundException, ClassNotFoundException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.obj";
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        if(arquivo.exists()) {
            ArrayList<Refeicao> listRefeicao = new ArrayList<>();

            for(Object obj : Arquivo.getObj(arquivo)) {
                listRefeicao.add((Refeicao) obj);
            }

            return listRefeicao;
        } else {
            return new ArrayList<Refeicao>();
        }
    }
    
    public void deleteRefeicao(int id) throws IOException, FileNotFoundException, ClassNotFoundException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.obj";
        File arquivo = Arquivo.getArquivo(dir, arq);
        ArrayList<Refeicao> listRefeicao = getListRefeicao();
        
        for(int i = 0; i < listRefeicao.size(); i++) {
            if(id == listRefeicao.get(i).getId()) {
                listRefeicao.remove(i);
                break;
            }
        }
        
        if(!listRefeicao.isEmpty()) {
            insertRefeicao(listRefeicao.get(0), false);

            for(int j = 1; j < listRefeicao.size(); j++) {
                insertRefeicao(listRefeicao.get(j), true);
            }
        } else {
            Arquivo.apagaArquivo(arquivo);
        }
    }
    
    public void updateRefeicao(Refeicao refeicao) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Refeicao> listRefeicao = getListRefeicao();
        
        for(int i = 0; i < listRefeicao.size(); i++) {
            if(refeicao.getId() == listRefeicao.get(i).getId()) {
                listRefeicao.set(i, refeicao);
            }
        }
        
        insertRefeicao(listRefeicao.get(0), false);
        
        for(int j = 1; j < listRefeicao.size(); j++) {
            insertRefeicao(listRefeicao.get(j), true);
        }
    }
    
    
    public Refeicao getRefeicaoById(int id) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Refeicao> listRefeicao = this.getListRefeicao();
        
        for(Refeicao refeicao : listRefeicao){
            if(refeicao.getId() == id){
                return refeicao;
            }
        }
        
        return null;
    }
}
