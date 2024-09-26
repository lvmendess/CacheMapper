import java.util.ArrayList;

public class CacheMapper {
    private long n; // tamanho da memória
    private int p; // num de palavras no bloco
    private int l; //número total de linhas da memória cache
    private int v; //número de blocos do conjunto
    private final int wordSize = 4;
    private String[] e; // endereços de memória

    public CacheMapper(ArrayList<String[]> linhas) {
        this.n = StringToInt(linhas.get(0)[0]);
        this.p = StringToInt(linhas.get(1)[0]);
        this.l = StringToInt(linhas.get(2)[0]);
        this.v = StringToInt(linhas.get(3)[0]);
        this.e = linhas.get(4);
    }

    public int StringToInt(String s){
        int number = 0;
        try{
            number = Integer.valueOf(s);
        }catch(NumberFormatException e){
            System.out.println("Invalid number");
        }
        return number;
    }

    

    
}
