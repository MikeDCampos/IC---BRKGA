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
public class Solucao {
    int [][]sol;
    int []grau;
    int custo;
    public Solucao(int num_nos) {
        custo=0;
        sol=new int [num_nos][num_nos];
        grau=new int [num_nos];
        for(int i=0;i<num_nos;i++){
            for(int j=i;j<num_nos;j++){
                sol[i][j]=0;
                sol[j][i]=0;
            }
            grau[i]=0;
        }
    }
}
