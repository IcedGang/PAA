import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MultMatrizPD {
    public static final int MAXVALUE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        File path = new File("Dimensoes.Txt");// Caminho do Arquivo das Dimensões das Matrizes.
        Scanner scnnr = new Scanner(path); // Lê do Arquivo.

        //Lê a quantidade de matrizes a ser multiplicada.
        int qnt = scnnr.nextInt();
        
        if(qnt <= 1)
            System.out.println("Nenhuma Multiplicação.");
        else{    
            //Guarda os Índices dos Parenteses
            int[][] P = new int[qnt + 1][qnt + 1];

            //Armazena As Dimensões das Matrizes.
            int[]D = new int[qnt + 1];
            Organize(scnnr, D, qnt);

            //Printa o Número Mínimo De Multiplicações e
            //A Organização dos Parenteses
            System.out.println(MultPD(D, P, qnt) + " Multiplicações.");
            ParenthesesOrder(P, 1, qnt);
        }
        
        scnnr.close();
    }

    //Multiplicação de matriz PD
    //Entrada: Um Vetor D, lido de um arquivo, Uma Matriz para guardar a ordem Dos Parenteses e o Número de Matrizes.
    //Saída: O Número mínimo de Multiplicações entre as Matrizes.
    public static int MultPD(int[] D, int[][] P, int qnt){
        //Armazena o Número Mínimo de Multiplicações;
        int[][]M = new int[qnt + 1][qnt + 1];

        //Onde Não Existe Multiplicação.
        for(int i = 0; i < qnt; i++)
            M[i][i] = 0;

        //Percorre os Indices das Sub-Matrizes e Faz os Cálculos
        //Da Quantidade de Multiplicações Nessessárias.
        for(int l = 1; l <= qnt; l++){
            for(int i = 1; i <= qnt - l; i++){
                int j = i + l;
                
                //Define Os Valores da Matriz para uma Constante Igual ao Valor Maximo
                M[i][j] = MAXVALUE;

                //Calcula os Valores das Multiplicaçoes em Diferentes Ordens
                //Sempre Guarda o Menor Valor Encontrado.
                for(int k = i; k <= j - 1; k++){
                    int q = M[i][k] + M[k + 1][j] + D[i - 1] * D[k] * D[j];

                    if(q < M[i][j]){
                        M[i][j] = q;
                        P[i][j] = k;//Guarda o Indice dos Parenteses
                    }
                }
            }
        }

        return M[1][qnt];
    }

    //Imprime a Ordem dos Parenteses.
    public static void ParenthesesOrder(int[][] P, int i, int j){
        if(i == j)
            System.out.print("M" + i);
        else{
            System.out.print("(");
            ParenthesesOrder(P, i, P[i][j]);
            ParenthesesOrder(P, P[i][j] + 1, j);
            System.out.print(")");
        }
    }

    //Organiza o Vetor das Dimensões
    public static void Organize(Scanner scnnr, int[]D, int n){
        int[] ax = new int[n];
        
        //Retira os elementos repetidos, 
        //Enquanto Preenche o Vetor de Dimensões
        for(int i = 0; i < n; i++){
            D[i] = scnnr.nextInt();
            ax[i] = scnnr.nextInt();
        }
            
        //Acrescenta o ultimo valor ao vetor D
        D[n] = ax[n - 1];
    }
}
