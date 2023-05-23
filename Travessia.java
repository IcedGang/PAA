import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Travessia {
    public static int[][] M;//Matriz com as alturas.
    
    public static void main(String[] args) throws IOException {
        File path = new File("Matriz.txt");//Caminho para o Arquivo com as Alturas.
        Scanner scnnr = new Scanner(path);//Lê do Arquivo.

        //Lê o Tamanho da Matriz, Cria e Preenche a Matriz com os valores do Arquivo.
        int n = scnnr.nextInt();
        M = new int[n][n];
        Reader(scnnr, M);
        
        n--;//Garantir que a Função Não Saia do Tamanho da Matriz.
        
        //Busca e Printa o Caminho com o Gasto Mínimo.
        int min = TravMin(n, n);
        System.out.println(min);

    }

    //Preenche a Matriz Com os Valores do Arquivo
    public static void Reader(Scanner scnnr, int[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++)
                M[i][j] = scnnr.nextInt();
        }

    }

    //Busca o Caminho com Menor Gasto
    public static int TravMin(int i, int j) {
        //retorna 0 caso esteja na posição [0][0]
        if (i == 0 && j == 0)
            return 0;

        //Caso esteja na primeira linha [0][n]
        //Calcula o Gasto para Esquerda
        if (i == 0)
            return TravMin(0, j - 1) + Math.abs(M[0][j - 1] - M[0][j]);

        //Caso esteja na primeira coluna [n][0]
        //Calcula o Gasto para Cima
        if (j == 0)
            return TravMin(i - 1, 0) + Math.abs(M[i - 1][0] - M[i][0]);

        //k Calcula o Gasto para cima e q Calcula o Gasto Para Esquerda
        int k = TravMin(i - 1, j) + Math.abs(M[i][j] - M[i - 1][j]);
        int q = TravMin(i, j - 1) + Math.abs(M[i][j] - M[i][j - 1]);
        
        //Retorna o minimo entre k e q
        return Math.min(k, q);
    }

}
