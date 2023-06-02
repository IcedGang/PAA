import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GrafosBFS {
    public static int[][] Adj; // Matriz de Adjacência
    public static char[] Color; // Vetor de Cores
    public static int[] Dist; // Vetor de Distâncias
    static final int MAX = Integer.MAX_VALUE;
 
    public static void main(String[] args) throws FileNotFoundException {
        // Caminho para o Arquivo da Matriz de Adjacência.
        File path = new File("Grafos.txt");
        Scanner scnnr = new Scanner(path);

        // Lê o Tamanho da Matriz e Preenche a Matriz Adj com os valores do Arquivo.
        int n = scnnr.nextInt();
        Adj = new int[n][n];
        Reader(scnnr);

        // Chama a Função de Busca em Largura
        BFS(n, 0);

        // Printa o Vetor de Cores
        String colors = ColorString();
        System.out.println(colors);

        System.out.println();

        // Printa o Vetor de Distâncias
        String dist = DistString();
        System.out.println(dist);
    }
    
    // Armazena o Vetor de Cores em uma String
    public static String DistString(){
        String aux = "Vetor de Distâncias:\n" + "[ ";
        for (int i = 0; i < Color.length; i++){
            
            // Se Distancia for MAX Aparece como -1
            if(Dist[i] == MAX)
                Dist[i] = -1;

            aux += Dist[i] + " ";
        } 
            
        aux += "]";

        return aux;
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

    // Busca em Largura
    public static void BFS(int n, int r){
        //Vetor de Cores e Vetor de Distâncias
        Color = new char[n];
        Dist = new int[n];

        //Incializa o Vetor de Cores com Todos os Elementos Branco
        //Inicializa o Vetor de Distancia com Valor Máximo
        for (int i = 0; i < Color.length; i++){
            Color[i] = 'B';
            Dist[i] = MAX;
        }
        
        //Primerio Elemento Visitado
        Color[r] = 'C';
        Dist[r] = 0;
        
        //Fila auxiliar
        Queue<Integer> F = new LinkedList<Integer>();
        F.add(r);

        //Loop Funciona até que a Fila Esteja Vazia
        while(!F.isEmpty()){
            //Remove o Primeiro Elemento da Fila
            int u = F.poll();
            
            //Visita os Vértices Adjacentes de u
            for (int v = 0; v < n; v++) {
                if(Color[v] == 'B' && Adj[u][v] == 1){
                    Color[v] = 'C';
                    F.add(v);

                    Dist[v] = Dist[u] + 1;
                }

            }

            //Todos os Vértices Adjacentes de u Foram Visitados
            Color[u] = 'P';
        }

    }
    
}