/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brkga;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class BRKGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
//        FileInputStream stream = new FileInputStream("test.txt");
//        FileInputStream stream = new FileInputStream("v100_d30_t2_i1.dat");
//        FileInputStream stream = new FileInputStream("v200_d30_t1_i1.dat");
//        FileInputStream stream = new FileInputStream("v200_d30_t1_i2.dat");
//        FileInputStream stream = new FileInputStream("v200_d30_t1_i3.dat");
//        FileInputStream stream = new FileInputStream("v200_d30_t1_i4.dat");
//        FileInputStream stream = new FileInputStream("v300_d30_t1_i1.dat");
//        FileInputStream stream = new FileInputStream("v200_d30_t1_i1.dat");
//        FileInputStream stream = new FileInputStream("v200_d70_t2_i5.dat");
//        FileInputStream stream = new FileInputStream("v300_d30_t1_i4.dat");
//        FileInputStream stream = new FileInputStream("v300_d30_t1_i1.dat");
        print(args.length);
        long tempoInicio = System.currentTimeMillis();  
        FileInputStream stream = new FileInputStream(args[0]);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        
        int num_nos=0;
        int pesos_nos[] = new int[1000];
        String string_aux[];
        
        string_aux=linha.split(" ");
        num_nos=Integer.parseInt(string_aux[0]);
        int num_arestas=Integer.parseInt(string_aux[1]);
        
        linha = br.readLine();
        if(linha.equals("-1")){
            for(int i=0;i<num_nos;i++){
                linha = br.readLine();
                string_aux=linha.split(" ");
                pesos_nos[i]=Integer.parseInt(string_aux[2]);
            }
        }else{
            print("arquvo em formato inválido");
        }
        
        linha = br.readLine();
        
        int arestas[][] = new int [num_nos][num_nos];
        int grau[]=new int[num_nos];
        for(int i=0;i<num_nos;i++){
            grau[i]=0;
            for(int j=0;j<num_nos;j++){
                arestas[i][j]=-1;
            }
        }
        
        
        
        if(linha.equals("-1")){
            int origem;
            int destino;
            for(int i=0;i<num_arestas;i++){
                
                linha = br.readLine();
                string_aux=linha.split(" ");
                origem=Integer.parseInt(string_aux[0]);
                destino=Integer.parseInt(string_aux[1]);
                arestas[origem][destino]=Integer.parseInt(string_aux[2]);
                arestas[destino][origem]=arestas[origem][destino];
                grau[origem]++;
                grau[destino]++;
            }
        }else{
            print("arquvo em formato inválido");
        }
        br.close();

        
        int pop_tam=num_nos;
        Individuo[]populacao=new Individuo[pop_tam];
        Random pesof = new Random(1);
        Solucao []solucoes=new Solucao[pop_tam];
        
        for(int i=0;i<pop_tam;i++){
            populacao[i]=new Individuo(num_nos);
            solucoes[i]=new Solucao(num_nos);
        }
        
        int vizinhos[][]=new int[num_nos][num_nos];
        int p_vizinhos[]=new int [num_nos];
        for(int i=0;i<num_nos;i++){
            int p=-2;
            for(int j=num_nos-1;j>=0;j--){
                vizinhos[i][j]=-1;
                if(arestas[i][j]!=-1){
                    if(p==-2){
                        p_vizinhos[i]=j;
                        p=j;
                        vizinhos[i][j]=Integer.MAX_VALUE;
                    }else if(p!=-2){
                        vizinhos[i][j]=p;
                        p=j;
                        p_vizinhos[i]=j;
                    }
                }
            }
        }
        
        
        
//        for(int i=0;i<num_nos;i++){
//            print("");
//            for(int j=0;j<num_nos;j++){
//                System.out.print(arestas[i][j]+" ");
//            }
//            
//        }
//        print("");
//        for(int j=0;j<num_nos;j++){
//            System.out.print(p_vizinhos[j]+" ");
//        }
//       print("");
//        for(int i=0;i<num_nos;i++){
//            print("");
//            for(int j=0;j<num_nos;j++){
//                System.out.print(vizinhos[i][j]+" ");
//            }
//            
//        }
//print("");
        
//        for(int i=0;i<num_nos;i++){
//            for(int j=p_vizinhos[i];j<num_nos;){
//                System.out.print(vizinhos[i][j]+" "+i+" "+j+" ------ ");
//                j=vizinhos[i][j];
//            }
//            print("");
//        }
        
        for(int i=0;i<pop_tam;i++){
//            print(i);
            solucoes[i].custo=0;
            for(int j=0;j<num_nos;j++){
                double aux1=pesof.nextDouble();
                if(aux1==0.0){
                    populacao[i].chave_nos[j]=0.00001*pesos_nos[i];
                }else{
                    populacao[i].chave_nos[j]=aux1*pesos_nos[i];
                }
                solucoes[i].grau[j]=0;
                for(int k=j;k<num_nos;k++){
                    solucoes[i].sol[j][k]=0;
                    if(arestas[j][k]!=-1){
                        populacao[i].chave_arestas[j][k]=pesof.nextDouble()*arestas[j][k];
                        populacao[i].chave_arestas[k][j]=populacao[i].chave_arestas[j][k];
                        if(populacao[i].chave_arestas[j][k]==0){
                            populacao[i].chave_arestas[j][k]=0.00001*arestas[j][k];
                            populacao[i].chave_arestas[k][j]=0.00001*arestas[k][j];
                            
                        }
                    }
                }
            }
        }
            
            
        



//                for(int i=0;i<num_nos;i++){
//
//                    for(int j=0;j<num_nos;j++){
//                        System.out.print(populacao[0].chave_arestas[i][j]+" ");
//                    }
//                    print("");
//                }
        

        
//  criação das soluções - ini -
        int lista_tam=0;
        int indice=-1;
        double kcusto=0.0;
        int [] ordenados = new int[pop_tam];
        int [] vindo_de = new int[num_nos];
        int [] incluso = new int[num_nos];
//              selecionar vértice raiz
        int [][]melhor_sol_arestas=new int [num_nos][num_nos];
        int melhor_sol_custo=Integer.MAX_VALUE;
        double menor = 0.0;
        for(int elemento=0;elemento<pop_tam;elemento++){
                solucoes[elemento].grau[0]=0;
                for(int i=0;i<num_nos;i++){
                    ordenados[i]=-1;
                    incluso[i]=0;
                    vindo_de[i]=-1;
                    if(elemento<num_nos){
                        melhor_sol_arestas[elemento][i]=0;
                    }
                }
                incluso[0]=1;

                for(int cont=1;cont<num_nos;cont++){
                    double[]distancias=new double[num_nos];
                    for(int i=0;i<num_nos;i++){
                        if(incluso[i]!=1){
                            distancias[i]=Double.MAX_VALUE;
                        }else{
                            distancias[i]=-1;
                        }
                    }

                    for(int i=0;i<num_nos;i++){
                        if(incluso[i]==1){
//                            print("i = "+i);
                            for(int j=p_vizinhos[i];j<num_nos;){
//                                System.out.print(j+"--> ");
                                if(incluso[j]==0 && distancias[j]>populacao[elemento].chave_arestas[i][j] && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 &&solucoes[elemento].grau[i]==0 && arestas[i][j]!=-1 ){
                                    distancias[j]=populacao[elemento].chave_arestas[i][j];
                                    vindo_de[j]=i;
//                                    j=vizinhos[i][j];
//                                    print("j = "+j);
                                }else if(incluso[j]==0 && distancias[j]>(populacao[elemento].chave_arestas[i][j]+populacao[elemento].chave_nos[i]) && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 && solucoes[elemento].grau[i]==1 && arestas[i][j]!=-1){
                                    distancias[j]=populacao[elemento].chave_arestas[i][j] + populacao[elemento].chave_nos[i];
                                    vindo_de[j]=i;
//                                     j=vizinhos[i][j];
//                                     print("j = "+j);
                                }else if(incluso[j]==0 && distancias[j]>populacao[elemento].chave_arestas[i][j] && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 &&solucoes[elemento].grau[i]>1 && arestas[i][j]!=-1 ){
                                    distancias[j]=populacao[elemento].chave_arestas[i][j];
                                    vindo_de[j]=i;
//                                     j=vizinhos[i][j];
//                                     print("j = "+j);
                                }
                                j=vizinhos[i][j];
                            }
                        }
                        ordenados[i]=-1;
                    }

//                    print("fiiiiim");
                    indice=-1;
                    lista_tam=0;
                    int[]verif=new int[num_nos];
                    for(int h=0;h<num_nos;++h){
                        verif[h]=0;
                    }

                    for(int i=0;i<num_nos;i++){
                        menor=Double.MAX_VALUE;
                        
                        for(int j=0;j<num_nos;j++){
                            if(menor>distancias[j] && verif[j]==0 && distancias[j]>-1){
                                menor=distancias[j];
                                indice=j; 
                            }
                        }

                        if(menor!=Double.MAX_VALUE){
                            ordenados[lista_tam]=indice;
                            lista_tam++;
                            verif[indice]=1;
//                            print(menor);

                        }
                    }
//                    print("lt "+lista_tam);
//print("ord "+ordenados[0]);
//for(int q=0 ;q<num_nos;q++){
//    System.out.print(distancias[q]+" ");
//}
                    kcusto+=distancias[ordenados[0]];
                    int novo=ordenados[0];
                    int ligado = vindo_de[novo];
//                    print("novo  "+novo);
//                    print("ligado "+ligado);
//                    print(ligado+"_"+novo+" =-= "+solucoes[elemento].grau[ligado]);
                    if(solucoes[elemento].grau[ligado]==1){
                        solucoes[elemento].custo+=(arestas[ligado][novo] + pesos_nos[ligado]);
                        int aux=arestas[ligado][novo] + pesos_nos[ligado];
//                        print(aux);
                    }else if(solucoes[elemento].grau[ligado]>1){
                        solucoes[elemento].custo+=arestas[ligado][novo]; 
//                        print(arestas[ligado][novo]);
                    }else if(solucoes[elemento].grau[ligado]==0){
                        solucoes[elemento].custo+=arestas[ligado][novo]; 
//                        print(arestas[ligado][novo]);
                    }
                    solucoes[elemento].grau[novo]+=1;
                    solucoes[elemento].grau[ligado]+=1;
                    solucoes[elemento].sol[novo][ligado]=1;
                    solucoes[elemento].sol[ligado][novo]=1;
                    incluso[novo]=1;


                    //adicionar custo econtinuar


                }
//                for(int i=0;i<num_nos;i++){
//                    System.out.print(populacao[elemento].chave_nos[i]+" ");
//                }
//                print("");
//                print(kcusto);
//                for(int i=0;i<num_nos;i++){
//
//                    for(int j=0;j<num_nos;j++){
//                        System.out.print(populacao[elemento].chave_arestas[i][j]+" ");
//                    }
//                    print("");
//                }
//                print("----------------------");
                
                
//                int cal=0;
//                for(int i=0;i<num_nos;i++){
//                    if(solucoes[elemento].grau[i]>1){
//                        cal+=pesos_nos[i];
//                    }
//                    for(int j=i;j<num_nos;j++){
//                        if(solucoes[elemento].sol[i][j]==1){
//                            cal+=arestas[i][j];
//                        }
//                    }
//                }
//                
//                print("indivíduo"+elemento+"cal "+cal);
//                print(solucoes[elemento].custo);
//                print(elemento+" ==== "+solucoes[elemento].custo);
//                for(int j=0;j<num_nos;j++){
//                    System.out.print(solucoes[elemento].grau[j]+" ");
//                }
//                print("");
//
//                print("");
//                print(solucoes[elemento].custo);
//                for(int i=0;i<num_nos;i++){
//
//                    for(int j=0;j<num_nos;j++){
//                        System.out.print(solucoes[elemento].sol[i][j]+" ");
//                    }
//                    print("");
//                }
        
        }
        
//        for(int i=0;i<num_nos;i++){
//                    print(i+" ==== "+solucoes[i].custo);
////                    for(int j=0;j<num_nos;j++){
////                        System.out.print(solucoes[i].grau[j]+" ");
////                    }
////                    print("");
//                }
        
        indice=-1;
        int menor_custo=Integer.MAX_VALUE;
        for(int j=0;j<pop_tam;j++){
            if(menor_custo>solucoes[j].custo){
                menor_custo=solucoes[j].custo;
                indice=j;
            }
        }
        melhor_sol_custo=menor_custo;
        for(int i=0;i<num_nos;i++){
            for(int j=i;j<num_nos;j++){
                melhor_sol_arestas[i][j]=solucoes[indice].sol[i][j];
                melhor_sol_arestas[j][i]=melhor_sol_arestas[i][j];
            }
        }
//  criação das soluções - end -
//        print("melhor construtivo = "+melhor_sol_custo);
//        for(int i=0;i<num_nos;i++){
//            for(int j=0;j<num_nos;j++){
//                for(int k=0;k<num_nos;k++){
//                    System.out.print(melhor_sol_arestas[j][k]+"  ");
//                }
//                print("");
//            }
//            
//            print("-------------------------------");
//        }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int troca=1;
        int iteracoes_limite=(int)(pop_tam*0.1);
//        int iteracoes_limite=2;
        int iteracoes_sem_melhora=0;
        Individuo[]populacao2=new Individuo[pop_tam];
        for(int i=0;i<pop_tam;i++){
            populacao2[i]=new Individuo(num_nos);
        }
        int []lista_solucoes=new int[pop_tam];
        while(iteracoes_sem_melhora<iteracoes_limite){
            int []lista_classe=new int[pop_tam];
            int[]verif=new int[pop_tam];
            for(int i=0;i<pop_tam;i++){
                lista_solucoes[i]=-1;
                verif[i]=0;
                lista_classe[i]=2;
                for(int j=0;j<num_nos;j++){
                    solucoes[i].grau[j]=0;
                    for(int k=j;k<num_nos;k++){
                        solucoes[i].sol[j][k]=0;
                        solucoes[i].sol[k][j]=0;
                    }
                }
            }
            lista_tam=0;
            if(troca==1){
//                print("");
//                print("troca = "+troca);
//                print("it "+iteracoes_sem_melhora);
//                print("");
                for(int i=0;i<pop_tam;i++){
                    menor=Double.MAX_VALUE;
                    for(int j=0;j<pop_tam;j++){
                        if(menor>solucoes[j].custo && verif[j]==0){
                            menor=solucoes[j].custo;
                            indice=j; 
                        }
                    }

                    if(menor!=Double.MAX_VALUE){
                        ordenados[lista_tam]=indice;
                        lista_tam++;
                        verif[indice]=1;

                    }
                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+ "  ====  " +ordenados[i] +" ==== "+solucoes[ordenados[i]].custo);
//                }
                int elite=(int)(pop_tam*0.2);
                int bot=pop_tam-((int)(pop_tam*0.1));
//                print("elite "+elite);
//                print("bot "+bot);
                for(int i=0;i<pop_tam;i++){
                    if(i<elite){
                        lista_classe[ordenados[i]]=1;
                    }else if(i>=bot){
                        lista_classe[ordenados[i]]=3;
                    }
                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+" === "+lista_classe[i]+ " === "+ordenados[ordenados[i]]);
//                }

                for(int i=0;i<pop_tam;i++){
                    if(lista_classe[ordenados[i]]==1){
//                        print("classe 1 === "+ordenados[i]+" === "+solucoes[i].custo);
                        for(int j=0;j<num_nos;j++){
                            populacao2[ordenados[i]].chave_nos[j]=populacao[ordenados[i]].chave_nos[j];
                            for(int k=j;k<num_nos;k++){
                                populacao2[ordenados[i]].chave_arestas[j][k]=populacao[ordenados[i]].chave_arestas[j][k];
                                populacao2[ordenados[i]].chave_arestas[k][j]=populacao2[ordenados[i]].chave_arestas[j][k];
                            }
                        }
                    }else if(lista_classe[ordenados[i]]==2){
//                        print("classe 2 === "+ordenados[i]+" === "+solucoes[ordenados[i]].custo);

                        for(int j=0;j<num_nos;j++){
                            int dad1=pesof.nextInt(elite);
                            int dad2=elite+pesof.nextInt(7);
                            if(pesof.nextDouble()<0.5){
                                populacao2[ordenados[i]].chave_nos[j]=populacao[dad1].chave_nos[j];
                            }else{
                                populacao2[ordenados[i]].chave_nos[j]=populacao[dad2].chave_nos[j];
                            }
                            for(int k=j;k<num_nos;k++){
                                if(pesof.nextDouble()<0.5){
                                    populacao2[ordenados[i]].chave_arestas[j][k]=populacao[dad1].chave_arestas[j][k];
                                    populacao2[ordenados[i]].chave_arestas[k][j]=populacao[dad1].chave_arestas[k][j];
                                }else{
                                    populacao2[ordenados[i]].chave_arestas[j][k]=populacao[dad2].chave_arestas[j][k];
                                    populacao2[ordenados[i]].chave_arestas[k][j]=populacao[dad2].chave_arestas[k][j];
                                }
                            }
                        }


                    }else if(lista_classe[ordenados[i]]==3){
//                        print("classe 3 === "+ordenados[i]+" === "+solucoes[ordenados[i]].custo);
                            for(int j=0;j<num_nos;j++){
                            double aux1=pesof.nextDouble();
                            if(aux1==0.0){
                                populacao2[ordenados[i]].chave_nos[j]=0.00001*pesos_nos[i];
                            }else{
                                populacao2[ordenados[i]].chave_nos[j]=aux1*pesos_nos[i];
                            }
                            solucoes[ordenados[i]].grau[j]=0;
                            for(int k=j;k<num_nos;k++){

                                if(arestas[j][k]!=-1){
                                    populacao2[ordenados[i]].chave_arestas[j][k]=pesof.nextDouble()*arestas[j][k];
                                    populacao2[ordenados[i]].chave_arestas[k][j]=populacao2[ordenados[i]].chave_arestas[j][k];
                                    if(populacao2[ordenados[i]].chave_arestas[j][k]==0){
                                        populacao2[ordenados[i]].chave_arestas[j][k]=0.00001*arestas[j][k];
                                        populacao2[ordenados[i]].chave_arestas[k][j]=0.00001*arestas[k][j];

                                    }
                                }
                            }
                            
//                            for(int z=0;z<num_nos;z++){
//
//                                for(int x=0;x<num_nos;x++){
//                                    System.out.print(populacao[ordenados[i]].chave_arestas[z][x]+" ");
//                                }
//                                print("");
//                            }
                            
                        }

                    }
                    
                }
                
                
            
                lista_tam=0;
                indice=-1;
                kcusto=0.0;

        //              selecionar vértice raiz

                menor = 0.0;

                for(int elemento=0;elemento<pop_tam;elemento++){
//                    print("elemento "+elemento);
                        for(int i=0;i<num_nos;i++){
                            ordenados[i]=-1;
                            incluso[i]=0;
                            vindo_de[i]=-1;
                            solucoes[elemento].grau[i]=0;
                        }
                        solucoes[elemento].custo=0;
                        incluso[0]=1;

                        for(int cont=1;cont<num_nos;cont++){
                            double[]distancias=new double[num_nos];
                            for(int i=0;i<num_nos;i++){
                                if(incluso[i]!=1){
                                    distancias[i]=Double.MAX_VALUE;
                                }else{
                                    distancias[i]=-1;
                                }
                            }

                            for(int i=0;i<num_nos;i++){
                                if(incluso[i]==1){

                                    for(int j=p_vizinhos[i];j<num_nos;){
//                                        print("cont "+cont);
//                                        print("incluso "+incluso[i]);
//                                        print("distancias "+distancias[j]);
//                                        print("p2 key "+populacao2[elemento].chave_arestas[i][j]);
//                                        print("grau inc "+solucoes[elemento].grau[i]);
//                                        print("arestas i j "+arestas[i][j]);
//                                        print("i  j "+ i+" "+ j);
                                        if(incluso[j]==0 && distancias[j]>populacao2[elemento].chave_arestas[i][j] && populacao2[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 && solucoes[elemento].grau[i]==0 && arestas[i][j]!=-1 ){
                                            distancias[j]=populacao2[elemento].chave_arestas[i][j];
                                            vindo_de[j]=i;
                                        }else if(incluso[j]==0 && distancias[j]>(populacao2[elemento].chave_arestas[i][j]+populacao2[elemento].chave_nos[i]) && populacao2[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 && solucoes[elemento].grau[i]==1 && arestas[i][j]!=-1){
                                            distancias[j]=populacao2[elemento].chave_arestas[i][j] + populacao2[elemento].chave_nos[i];
                                            vindo_de[j]=i;
                                        }else if(incluso[j]==0 && distancias[j]>populacao2[elemento].chave_arestas[i][j] && populacao2[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 &&solucoes[elemento].grau[i]>1 && arestas[i][j]!=-1 ){
                                            distancias[j]=populacao2[elemento].chave_arestas[i][j];
                                            vindo_de[j]=i;
                                        }
                                        j=vizinhos[i][j];
                                    }
                                }
                                ordenados[i]=-1;
                            }

                            indice=-1;
                            lista_tam=0;

                            for(int h=0;h<num_nos;h++){
                                verif[h]=0;
                            }

                            for(int i=0;i<num_nos;i++){
                                menor=Double.MAX_VALUE;
                                for(int j=0;j<num_nos;j++){
                                    if(menor>distancias[j] && verif[j]==0 && distancias[j]>-1){
                                        menor=distancias[j];
                                        indice=j; 
                                    }
                                }
                                if(menor!=Double.MAX_VALUE){
                                    ordenados[lista_tam]=indice;
                                    lista_tam++;
                                    verif[indice]=1;

                                }
                            }
                            
//                            for(int q=0;q<num_nos;q++){
//                                if(incluso[q]){
//                                    
//                                }
//                            }
//                            print("cont "+cont);
//                            print("lt "+lista_tam);
//                            print("ord "+ordenados[0]);
//                            print("d "+distancias[0]);
//                            for(double q:distancias){
//                                System.out.print(q+" ");
//                            }
                                
//                            kcusto+=distancias[ordenados[0]];
                            int novo=ordenados[0];
                            int ligado = vindo_de[novo];
        //                    print("novo  "+novo);
        //                    print("ligado "+ligado);
                            if(solucoes[elemento].grau[ligado]==1){
                                solucoes[elemento].custo+=(arestas[ligado][novo] + pesos_nos[ligado]);
                            }else if(solucoes[elemento].grau[ligado]>1){
                                solucoes[elemento].custo+=arestas[ligado][novo];
                            }else if(solucoes[elemento].grau[ligado]==0){
                                solucoes[elemento].custo+=arestas[ligado][novo]; 
//                                print(arestas[ligado][novo]);
                            }
                            solucoes[elemento].grau[novo]+=1;
                            solucoes[elemento].grau[ligado]+=1;
                            solucoes[elemento].sol[novo][ligado]=1;
                            solucoes[elemento].sol[ligado][novo]=1;
                            incluso[novo]=1;


                            //adicionar custo econtinuar


                        }
        //                for(int i=0;i<num_nos;i++){
        //                    System.out.print(populacao[elemento].chave_nos[i]+" ");
        //                }
        //                print("");
        //                print(kcusto);
        //                for(int i=0;i<num_nos;i++){
        //
        //                    for(int j=0;j<num_nos;j++){
        //                        System.out.print(populacao[elemento].chave_arestas[i][j]+" ");
        //                    }
        //                    print("");
        //                }
                        
//                        print("----------------------");
//                        int cal=0;
//                for(int i=0;i<num_nos;i++){
//                    if(solucoes[elemento].grau[i]>1){
//                        cal+=pesos_nos[i];
//                    }
//                    for(int j=i;j<num_nos;j++){
//                        if(solucoes[elemento].sol[i][j]==1){
//                            cal+=arestas[i][j];
//                        }
//                    }
//                }
//                print("cal "+cal);
//                        print(solucoes[elemento].custo);
//                        for(int i=0;i<num_nos;i++){
//        
//                            for(int j=0;j<num_nos;j++){
//                                System.out.print(solucoes[elemento].sol[i][j]+" ");
//                            }
//                            print("");
//                        }
//                        

                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+" ==== "+solucoes[i].custo);
////                    for(int j=0;j<num_nos;j++){
////                        System.out.print(solucoes[i].grau[j]+" ");
////                    }
////                    print("");
//                }
                int m=Integer.MAX_VALUE;
                int ind=-1;
                for(int i=0;i<pop_tam;i++){
                    if(m>solucoes[i].custo){
                        m=solucoes[i].custo;
                        ind=i;
                    }
                }

//                print("melhor da geração -> "+solucoes[ind].custo);
//                print("melhor atual ->"+melhor_sol_custo);
                if(melhor_sol_custo>solucoes[ind].custo){
                    melhor_sol_custo=solucoes[ind].custo;
                    iteracoes_sem_melhora=0;
//                    print("melhorou para >>> "+melhor_sol_custo);
                    for(int j=0;j<num_nos;j++){
                        for(int k=0;k<num_nos;k++){
                            melhor_sol_arestas[j][k]=solucoes[ind].sol[j][k];
//                            System.out.print(solucoes[ind].sol[j][k]+" ");
                        }
                    }
                }
                troca=2;
            }else if(troca==2){
//                print("");
//                print("troca = "+troca);
//                print("it "+iteracoes_sem_melhora);
//                print("");
                for(int i=0;i<pop_tam;i++){
                    menor=Double.MAX_VALUE;
                    for(int j=0;j<num_nos;j++){
                        if(menor>solucoes[j].custo && verif[j]==0){
                            menor=solucoes[j].custo;
                            indice=j; 
                        }
                    }

                    if(menor!=Double.MAX_VALUE){
                        ordenados[lista_tam]=indice;
                        lista_tam++;
                        verif[indice]=1;

                    }
                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+ "  ====  " +ordenados[i] +" ==== "+solucoes[ordenados[i]].custo);
//                }
                int elite=(int)(pop_tam*0.2);
                int bot=pop_tam-((int)(pop_tam*0.1));
//                print("elite "+elite);
//                print("bot "+bot);
                for(int i=0;i<pop_tam;i++){
                    if(i<elite){
                        lista_classe[ordenados[i]]=1;
                    }else if(i>=bot){
                        lista_classe[ordenados[i]]=3;
                    }
                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+" === "+lista_classe[ordenados[i]]+ " === "+ordenados[ordenados[i]]);
//                }

                for(int i=0;i<pop_tam;i++){
                    if(lista_classe[ordenados[i]]==1){
//                        print("classe 1 === "+ordenados[i]+" === "+solucoes[i].custo);
                        for(int j=0;j<num_nos;j++){
                            populacao[ordenados[i]].chave_nos[j]=populacao2[ordenados[i]].chave_nos[j];
                            for(int k=j;k<num_nos;k++){
                                populacao[ordenados[i]].chave_arestas[j][k]=populacao2[ordenados[i]].chave_arestas[j][k];
                                populacao[ordenados[i]].chave_arestas[k][j]=populacao[ordenados[i]].chave_arestas[j][k];
                            }
                        }
                    }else if(lista_classe[ordenados[i]]==2){
//                        print("classe 2 === "+ordenados[i]+" === "+solucoes[ordenados[i]].custo);

                        for(int j=0;j<num_nos;j++){
                            int dad1=pesof.nextInt(elite);
                            int dad2=elite+pesof.nextInt(7);
                            if(pesof.nextDouble()<0.5){
                                populacao[ordenados[i]].chave_nos[j]=populacao2[dad1].chave_nos[j];
                            }else{
                                populacao[ordenados[i]].chave_nos[j]=populacao2[dad2].chave_nos[j];
                            }
                            for(int k=j;k<num_nos;k++){
                                if(pesof.nextDouble()<0.5){
                                    populacao[ordenados[i]].chave_arestas[j][k]=populacao2[dad1].chave_arestas[j][k];
                                    populacao[ordenados[i]].chave_arestas[k][j]=populacao2[dad1].chave_arestas[k][j];
                                }else{
                                    populacao[ordenados[i]].chave_arestas[j][k]=populacao2[dad2].chave_arestas[j][k];
                                    populacao[ordenados[i]].chave_arestas[k][j]=populacao2[dad2].chave_arestas[k][j];
                                }
                            }
                        }


                    }else if(lista_classe[ordenados[i]]==3){
//                        print("classe 3 === "+ordenados[i]+" === "+solucoes[ordenados[i]].custo);
                        for(int j=0;j<num_nos;j++){
                            double aux1=pesof.nextDouble();
                            if(aux1==0.0){
                                populacao[ordenados[i]].chave_nos[j]=0.00001*pesos_nos[i];
                            }else{
                                populacao[ordenados[i]].chave_nos[j]=aux1*pesos_nos[i];
                            }
                            solucoes[ordenados[i]].grau[j]=0;
                            for(int k=j;k<num_nos;k++){
                                solucoes[ordenados[i]].sol[j][k]=0;
                                if(arestas[j][k]!=-1){
                                    populacao[ordenados[i]].chave_arestas[j][k]=pesof.nextDouble()*arestas[j][k];
                                    populacao[ordenados[i]].chave_arestas[k][j]=populacao[ordenados[i]].chave_arestas[j][k];
                                    if(populacao[ordenados[i]].chave_arestas[j][k]==0){
                                        populacao[ordenados[i]].chave_arestas[j][k]=0.00001*arestas[j][k];
                                        populacao[ordenados[i]].chave_arestas[k][j]=0.00001*arestas[k][j];

                                    }
                                }
                            }
                        }

                    }
                }
                
                
               
            
                lista_tam=0;
                indice=-1;
                kcusto=0.0;

        //              selecionar vértice raiz

                menor = 0.0;

                for(int elemento=0;elemento<pop_tam;elemento++){
                        for(int i=0;i<num_nos;i++){
                            ordenados[i]=-1;
                            incluso[i]=0;
                            vindo_de[i]=-1;
                            solucoes[elemento].grau[i]=0;

                        }
                        solucoes[elemento].custo=0;
                        solucoes[elemento].grau[0]=0;
                        incluso[0]=1;

                        for(int cont=1;cont<num_nos;cont++){
                            double[]distancias=new double[num_nos];
                            for(int i=0;i<num_nos;i++){
                                if(incluso[i]!=1){
                                    distancias[i]=Double.MAX_VALUE;
                                }else{
                                    distancias[i]=-1;
                                }
                            }

                            for(int i=0;i<num_nos;i++){
                                if(incluso[i]==1){
                                    for(int j=p_vizinhos[i];j<num_nos;){
                                        if(incluso[j]==0 && distancias[j]>populacao[elemento].chave_arestas[i][j] && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 &&solucoes[elemento].grau[i]==0 && arestas[i][j]!=-1 ){
                                            distancias[j]=populacao[elemento].chave_arestas[i][j];
                                            vindo_de[j]=i;
                                        }else if(incluso[j]==0 && distancias[j]>(populacao[elemento].chave_arestas[i][j]+populacao[elemento].chave_nos[i]) && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 && solucoes[elemento].grau[i]==1 && arestas[i][j]!=-1){
                                            distancias[j]=populacao[elemento].chave_arestas[i][j] + populacao[elemento].chave_nos[i];
                                            vindo_de[j]=i;
                                        }else if(incluso[j]==0 && distancias[j]>populacao[elemento].chave_arestas[i][j] && populacao[elemento].chave_arestas[i][j]>-1 && distancias[j]>-1 &&solucoes[elemento].grau[i]>1 && arestas[i][j]!=-1 ){
                                            distancias[j]=populacao[elemento].chave_arestas[i][j];
                                            vindo_de[j]=i;
                                        }
                                        j=vizinhos[i][j];
                                    }
                                }
                                ordenados[i]=-1;
                            }

                            indice=-1;
                            lista_tam=0;

                            for(int h=0;h<num_nos;++h){
                                verif[h]=0;
                            }

                            for(int i=0;i<num_nos;i++){
                                menor=Double.MAX_VALUE;
                                for(int j=0;j<num_nos;j++){
                                    if(menor>distancias[j] && verif[j]==0 && distancias[j]>-1){
                                        menor=distancias[j];
                                        indice=j; 
                                    }
                                }

                                if(menor!=Double.MAX_VALUE){
                                    ordenados[lista_tam]=indice;
                                    lista_tam++;
                                    verif[indice]=1;

                                }
                            }
        //                    print("fff "+cont);
//                            kcusto+=distancias[ordenados[0]];
                            int novo=ordenados[0];
                            int ligado = vindo_de[novo];
        //                    print("novo  "+novo);
        //                    print("ligado "+ligado);
                            if(solucoes[elemento].grau[ligado]==1){
                                solucoes[elemento].custo+=(arestas[ligado][novo] + pesos_nos[ligado]);
                            }else if(solucoes[elemento].grau[ligado]>1){
                                solucoes[elemento].custo+=arestas[ligado][novo];
                            }else if(solucoes[elemento].grau[ligado]==0){
                                solucoes[elemento].custo+=arestas[ligado][novo]; 
//                                print(arestas[ligado][novo]);
                            }
                            solucoes[elemento].grau[novo]+=1;
                            solucoes[elemento].grau[ligado]+=1;
                            solucoes[elemento].sol[novo][ligado]=1;
                            solucoes[elemento].sol[ligado][novo]=1;
                            incluso[novo]=1;


                            //adicionar custo econtinuar


                        }
        //                for(int i=0;i<num_nos;i++){
        //                    System.out.print(populacao2[elemento].chave_nos[i]+" ");
        //                }
        //                print("");
        //                print(kcusto);
        //                for(int i=0;i<num_nos;i++){
        //
        //                    for(int j=0;j<num_nos;j++){
        //                        System.out.print(populacao2[elemento].chave_arestas[i][j]+" ");
        //                    }
        //                    print("");
        //                }
                        
//                        print("----------------------");
//                        int cal=0;
//                for(int i=0;i<num_nos;i++){
//                    if(solucoes[elemento].grau[i]>1){
//                        cal+=pesos_nos[i];
//                    }
//                    for(int j=i;j<num_nos;j++){
//                        if(solucoes[elemento].sol[i][j]==1){
//                            cal+=arestas[i][j];
//                        }
//                    }
//                }
//                print("cal "+cal);
//                        print(solucoes[elemento].custo);
//                        for(int i=0;i<num_nos;i++){
//        
//                            for(int j=0;j<num_nos;j++){
//                                System.out.print(solucoes[elemento].sol[i][j]+" ");
//                            }
//                            print("");
//                        }
                        

                }
//                for(int i=0;i<num_nos;i++){
//                    print(i+" ==== "+solucoes[i].custo);
////                    for(int j=0;j<num_nos;j++){
////                        System.out.print(solucoes[i].grau[j]+" ");
////                    }
////                    print("");
//                }
                int m=Integer.MAX_VALUE;
                int ind=-1;
                for(int i=0;i<pop_tam;i++){
                    if(m>solucoes[i].custo){
                        m=solucoes[i].custo;
                        ind=i;
                    }
                }
//                print(m+"  "+ind);
//                print("melhor da geração -> "+solucoes[ind].custo);
//                print("melhor atual - >"+melhor_sol_custo);
                if(melhor_sol_custo>solucoes[ind].custo){
                    melhor_sol_custo=solucoes[ind].custo;
                    iteracoes_sem_melhora=0;
//                    print("melhorou para >>> "+melhor_sol_custo);
                    for(int j=0;j<num_nos;j++){
                        for(int k=0;k<num_nos;k++){
                            melhor_sol_arestas[j][k]=solucoes[ind].sol[j][k];
//                            System.out.print(solucoes[ind].sol[j][k]+" ");
                        }
                    }
                }
                
        
                troca=1;
            }
            
            
            iteracoes_sem_melhora++;
        }
        
//        for(int i=0;i<num_nos;i++){
//            print(ordenados[i]);
//            print(solucoes[ordenados[i]].custo);
//            for(int j=0;j<num_nos;j++){
//                for(int k=0;k<num_nos;k++){
//                    System.out.print(populacao2[ordenados[i]].chave_arestas[j][k]+" ");
//                }
//                print("");
//            }
//            
//            print("-------------------------------");
//        }
        
        
//        print("");
//        print("");
//        print("");
//        print("");
        long tempo=System.currentTimeMillis()-tempoInicio;  
        print("Sol_final = "+melhor_sol_custo+"   Tempo = "+tempo);
//        for(int j=0;j<num_nos;j++){
//            for(int k=0;k<num_nos;k++){
//                System.out.print(melhor_sol_arestas[j][k]+" ");
//            }
//            print("");
//        }

    }

    
    
    
    
    
    
    
    
    public static void print(String q){
        System.out.println(q);
    }
    
    public static void print(int q){
        System.out.println(q);
    }
    
    public static void print(long q){
        System.out.println(q);
    }
    
    public static void print(double q){
        System.out.println(q);
    }

    
    
    
}
