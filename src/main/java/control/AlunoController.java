/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import model.AlunoDAO;
import view.GUI_Creditos;

/**
 *
 * @author rodrigo
 */
public class AlunoController {
    public void updateCredito(int creditosNovo, int decisao, Aluno aluno) throws FileNotFoundException, ClassNotFoundException {
        if(decisao == 1) {
            aluno.setQtdCreditos(aluno.getQtdCreditos() + creditosNovo);
        } else { 
            aluno.setQtdCreditos(aluno.getQtdCreditos() - creditosNovo);
        }
       
        try {
            (new AlunoDAO()).updateAluno(aluno);
        } catch (IOException ex) {
            Logger.getLogger(GUI_Creditos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
