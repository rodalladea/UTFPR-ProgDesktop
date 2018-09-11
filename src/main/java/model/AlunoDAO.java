/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Renan Rodrigues
 */
public class AlunoDAO {
    public void insertAluno(Aluno aluno, boolean escreve) {
        
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.txt";
        StringBuilder stringDado = new StringBuilder();
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        stringDado.append(aluno.getId())
                .append(",")
                .append(aluno.getNome())
                .append(",")
                .append(aluno.getRa())
                .append(",")
                .append(aluno.getSenha())
                .append(",")
                .append(aluno.getQtdCreditos());
        
        Arquivo.setTexto(new File(dir), arquivo, stringDado.toString(), escreve);
    }
    
    public ArrayList<Aluno> getListAluno() throws IOException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.txt";
        StringBuilder stringDados = new StringBuilder();
        ArrayList<Aluno> listAluno = new ArrayList<>();
        Aluno aluno = new Aluno();
        String stringDado;
        int aux = 0;
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        stringDado = Arquivo.getTexto(arquivo);
        
        
            char[] charDado = stringDado.toCharArray();
            
            for(int i = 0; i < charDado.length; i++) {
                
                if(aux == 0 && charDado[i] == ',') {
                    aluno.setId(Integer.parseInt(stringDados.toString()));
                    
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 1 && charDado[i] == ',') {
                    aluno.setNome(stringDados.toString());
                    
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 2 && charDado[i] == ',') {
                    aluno.setRa(stringDados.toString());
                    
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 3 && charDado[i] == ',') {
                    aluno.setSenha(stringDados.toString());
                    
                    stringDados.delete(0, stringDados.length());
                    aux++;
                } else if(aux == 4 && charDado[i] == '\n') {
                    aluno.setQtdCreditos(Integer.parseInt(stringDados.toString()));
                    
                    stringDados.delete(0, stringDados.length());
                    aux = 0;
                    listAluno.add(aluno);
                    aluno = new Aluno();
                }
                
                if(charDado[i] != ',' && charDado[i] != '\n') {
                    stringDados.append(charDado[i]);
                }
            }
        
        
        return listAluno;
    }
    
    public void deleteAluno(int id) throws IOException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.txt";
        File arquivo = Arquivo.getArquivo(dir, arq);
        ArrayList<Aluno> listAluno = getListAluno();
        
        for(int i = 0; i < listAluno.size(); i++) {
            if(id == listAluno.get(i).getId()) {
                listAluno.remove(i);
                break;
            }
        }
        
        if(!listAluno.isEmpty()) {
            insertAluno(listAluno.get(0), false);

            for(int j = 1; j < listAluno.size(); j++) {
                insertAluno(listAluno.get(j), true);
            }
        } else {
            Arquivo.apagaArquivo(arquivo);
        }
    }
    
    public void updateAluno(Aluno aluno) throws IOException {
        ArrayList<Aluno> listAluno = getListAluno();
        
        for(int i = 0; i < listAluno.size(); i++) {
            if(aluno.getId() == listAluno.get(i).getId()) {
                listAluno.set(i, aluno);
            }
        }
        
        insertAluno(listAluno.get(0), false);
        
        for(int j = 1; j < listAluno.size(); j++) {
            insertAluno(listAluno.get(j), true);
        }
    }
    
    
    public Aluno getAlunoById(int id) throws IOException{
        ArrayList<Aluno> alunos = this.getListAluno();
        
        for(Aluno aluno : alunos){
            if(aluno.getId() == id){
                return aluno;
            }
        }
        
        return null;
    }
}
