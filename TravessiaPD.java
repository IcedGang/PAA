import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class TravessiaPD {
  public static int[][] M;//Matriz com as alturas.
  public static int[][] D;//Matriz que Armazena os Calculos da Diferença entre os Desníveis
  static final int MAX = Integer.MAX_VALUE;
  
  public static void main(String[] args) throws IOException {
    File path = new File("desniveis.txt");//Caminho para o Arquivo com as Alturas.
    Scanner scnnr = new Scanner(path);
    
    //Lê o Tamanho das Matrizes, Cria e Preenche as Matriz M com os valores do Arquivo.
    int n = scnnr.nextInt();
    M = new int[n][n];
    
    //Preenche a Matriz D
    D = new int[n][n];
    DReader();
    
    //Garantir que a Função Não Saia do Tamanho da Matriz.
    n--;
    
    //Busca e Printa o Caminho com o Gasto Mínimo.
    int min = TravMin(n, n);
    System.out.println(min);
    
    scnnr.close();
  }

  //Preenche a Matriz M Com os Valores do Arquivo
  public static void Reader(Scanner scnnr) {
    for (int i = 0; i < M.length; i++) {
      for (int j = 0; j < M.length; j++)
         M[i][j] = scnnr.nextInt();
    }
  }

  //Preenche a Matriz D Com MAX;
  public static void DReader() {
    for (int i = 0; i < D.length; i++) {
      for (int j = 0; j < D.length; j++)
        D[i][j] = MAX;
    }

  }

  //Busca o Caminho com Menor Gasto
  public static int TravMin(int i, int j) {
    //Se o Gasto Já Foi Calculado Retorna o Valor Naquela Posição
    if(D[i][j] != MAX) 
      return D[i][j];
    
    //retorna 0 caso esteja na posição [0][0]
    if (i == 0 && j == 0)
      return 0;
    
    //Calcula os Gastos e Armazena os Gastos na Matriz D       
    //Caso esteja na primeira linha [0][n]
    //Calcula o Gasto Para Esquerda
    if (i == 0)
      return TravMin(0, j - 1) + Math.abs(M[0][j - 1] - M[0][j]);
  
    //Caso esteja na primeira coluna [n][0]
    //Calcula o Gasto para cima
    if (j == 0)
      return TravMin(i - 1, 0) + Math.abs(M[i - 1][0] - M[i][0]);
  
    //k Calcula o Gasto para cima e q Calcula o Gasto Para Esquerda
    int k = TravMin(i - 1, j) + Math.abs(M[i][j] - M[i - 1][j]);
    int q = TravMin(i, j - 1) + Math.abs(M[i][j] - M[i][j - 1]);
  
    //Retorna o minimo entre k e q
    D[i][j] = Math.min(k, q);
    return D[i][j];
  }
}
