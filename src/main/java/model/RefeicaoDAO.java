/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class RefeicaoDAO {
    public void insertRefeicao(Refeicao refeicao, boolean escreve) {
        
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.txt";
        StringBuilder stringDado = new StringBuilder();
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        stringDado.append(refeicao.getId())
                .append(",")
                .append(refeicao.getIdUsuario())
                .append(",")
                .append(refeicao.getData().toString());
        
        Arquivo.setTexto(new File(dir), arquivo, stringDado.toString(), escreve);
    }
    
    public ArrayList<Refeicao> getListRefeicao() throws IOException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.txt";
        StringBuilder stringDados = new StringBuilder();
        ArrayList<Refeicao> listRefeicao = new ArrayList<>();
        Refeicao refeicao = new Refeicao();
        String stringDado;
        int aux = 0;
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        stringDado = Arquivo.getTexto(arquivo);
        
        
            char[] charDado = stringDado.toCharArray();
            
            for(int i = 0; i < charDado.length; i++) {
                
                if(aux == 0 && charDado[i] == ',') {
                    refeicao.setId(Integer.parseInt(stringDados.toString()));
                    System.out.println(stringDados.toString());
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 1 && charDado[i] == ',') {
                    refeicao.setIdUsuario(Integer.parseInt(stringDados.toString()));
                    System.out.println(stringDados.toString());
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 2 && charDado[i] == '\n') {
                    refeicao.setData(LocalDateTime.parse(stringDados.toString().subSequence(0, stringDados.toString().length())));
                    System.out.println(stringDados.toString());
                    stringDados.delete(0, stringDados.length());
                    aux = 0;
                    listRefeicao.add(refeicao);
                    refeicao = new Refeicao();
                }
                
                if(charDado[i] != ',' && charDado[i] != '\n') {
                    stringDados.append(charDado[i]);
                }
            }
        
        
        return listRefeicao;
    }
    
    public void deleteRefeicao(int id) throws IOException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "refeicao.txt";
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
    
    public void updateRefeicao(Refeicao refeicao) throws IOException {
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
    
    
    public Refeicao getRefeicaoById(int id) throws IOException{
        ArrayList<Refeicao> listRefeicao = this.getListRefeicao();
        
        for(Refeicao refeicao : listRefeicao){
            if(refeicao.getId() == id){
                return refeicao;
            }
        }
        
        return null;
    }
}
