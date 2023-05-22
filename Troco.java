import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Troco {
    public static void main(String[] args) throws IOException {
        String path = "Moedas.txt"; //Caminho para o Arquivo com os Valores das Moedas
        int[] value = Troco.Reader(path); //Vetor que Armazena os Valores das Moedas
        int change = 7; // Valor do troco

        System.out.println(Troco.Coins(value, change) + " Moedas");
    }

    //Lê os Valores das Moedas Do Arquivo e Retorna um Vetor Com esses Valores
    public static int[] Reader(String path) throws IOException{
        try (BufferedReader buffer = new BufferedReader(new FileReader(path));) {
            int num = Integer.parseInt(buffer.readLine());
            int[] value = new int[num];
            
            int i = 0;
            String aux;

            //Lê cada linha do Arquivo, Converte em Inteiro e Armazena no Vetor 'value'
            while((aux = buffer.readLine()) != null){
                for (String str : aux.split(" ")){
                    value[i] = Integer.parseInt(str);
                    i++;
                }
            }
            
            return value;
        }
  
    }

    //Calcula a Quantidade Mínima de Moedas para o Valor do Troco
    public static int Coins(int[] value, int change){
        int[] quant = new int[change + 1]; //Armazena a quantidade minima de moedas para o Troco

        for (int atualcoin : value) {
            for(int i = atualcoin; i < quant.length; i++){
            
                //Calcula a Diferença Entre o Valor i (0 <= i <= Troco) do Troco e o Valor Atual da Moeda
                int diff = i - atualcoin; 
                
                //Condição que Verifica se é Possivel Existir Quantidade de Moeda para o valor i
                if(diff > 0 && quant[diff] == 0)
                    continue;
        
                //Calcula a Quantidade de Moeda Para o Valor i 
                //Se for igual a 0 calucula o valor, se for diferente de 0 só armazena quando a quant 
                //for Menor do que a quant já guardado na posição i
                if(quant[i] == 0)
                    quant[i] = 1 + quant[diff];
                else{
                    int ax = 1 + quant[diff];
                    if(ax < quant[i])
                        quant[i] = ax;
                    
                }
            }
        }

        //Se n existir Troco retorna -1;
        if(quant[change] == 0)
            return -1;

        return quant[change];
    }
}