/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Renan Rodrigues
 */
public class AlunoDAO {
    public void insertAluno(Aluno aluno, boolean escreve) throws IOException {
        
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.obj";
        
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        Arquivo.setObj(arquivo, aluno, escreve);
    }
    
    public ArrayList<Aluno> getListAluno() throws IOException, FileNotFoundException, ClassNotFoundException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.obj";
        File arquivo = Arquivo.getArquivo(dir, arq);
        
        if(arquivo.exists()) {
            ArrayList<Aluno> listAluno = new ArrayList<>();

            for(Object obj : Arquivo.getObj(arquivo)) {
                listAluno.add((Aluno) obj);
            }

            return listAluno;
        } else {
            return new ArrayList<Aluno>();
        }
    }
    
    public void deleteAluno(int id) throws IOException, FileNotFoundException, ClassNotFoundException {
        String dir = "/home/rodrigo/Documentos/RU2.0/";
        String arq = "aluno.obj";
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
    
    public void updateAluno(Aluno aluno) throws IOException, FileNotFoundException, ClassNotFoundException {
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
    
    
    public Aluno getAlunoById(int id) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Aluno> alunos = this.getListAluno();
        
        for(Aluno aluno : alunos){
            if(aluno.getId() == id){
                return aluno;
            }
        }
        
        return null;
    }
}
