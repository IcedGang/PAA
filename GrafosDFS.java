import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrafosDFS {
    public static int[][] Adj; // Matriz de Adjacência
    public static int[] Pred; // Vetor de Predecessores
    public static char[] Color; // Vetor de Cores
    static final int MAX = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws FileNotFoundException {
        // Caminho para o Arquivo da Matriz de Adjacência.
        File path = new File("Grafos.txt");
        Scanner scnnr = new Scanner(path);

        // Lê o Tamanho da Matriz e Preenche a Matriz Adj com os valores do Arquivo.
        int n = scnnr.nextInt();
        Adj = new int[n][n];
        Reader(scnnr);

        // Chama a Função de Busca em Profundidade
        DFS(n, 0);

        // Printa o Vetor de Cores
        String colors = ColorString();
        System.out.println(colors);
    }

    // Armazena o Vetor de Cores em uma String
    public static String ColorString(){
        String aux = "Vetor de Cores:\n" + "[ ";
        for (int i = 0; i < Color.length; i++) 
            aux += Color[i] + " ";
        aux += "]";

        return aux;
    }

    // Preenche a Matriz Adj Com os Valores do Arquivo
    public static void Reader(Scanner scnnr) {
        for (int i = 0; i < Adj.length; i++) {
            for (int j = 0; j < Adj.length; j++)
                Adj[i][j] = scnnr.nextInt();
        }
    }

    //Busca em Profundidade
    public static void DFS(int n, int r){
        //Vetor de Cores e Vetor de Predecessores
        Color = new char[n];
        Pred = new int[n];

        //Incializa o Vetor de Cores com Todos os Elementos Branco
        //Inicializa o Vetor de Predecessores com Valor Máximo    
        for(int w = 0; w < Color.length; w++){
            Color[w] = 'B';
            Pred[w] = MAX;
        }

        //Visita Todos os Vertices u
        for(int u = 0; u < Color.length; u++){
            if(Color[u] == 'B')
                Visite(u);
        }
    }

    public static void Visite(int r){
        Color[r] = 'C';
        
        //Visita os Vértices Adjacentes de r
        for (int v = 0; v < Color.length; v++) {
            
            if(Color[v] == 'B' && Adj[r][v] == 1){
                Pred[v] = r;
                Visite(v);
            }
            
            //Todos os Vértices Adjacentes de r Foram Visitados
            Color[r] = 'P';
        }
    }
}
