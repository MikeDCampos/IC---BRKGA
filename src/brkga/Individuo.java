/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brkga;

/**
 *
 * @author Michael
 */
public class Individuo{
    double chave_nos[];
    double chave_arestas[][];
    
    public Individuo(int num_nos){
        chave_nos=new double[num_nos];
        chave_arestas=new double [num_nos][num_nos];
        for(int i=0;i<num_nos;i++){
            for(int j=i;j<num_nos;j++){
                chave_arestas[i][j]=0.0;
                chave_arestas[j][i]=0.0;
            }
            chave_nos[i]=0.0;
        }
    }

}
