import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CacheMapper {
    private long n;//tamanho da memória
    private int p;//num de palavras no bloco
    private int l;//número total de linhas da memória cache
    private int v;// quantidade de vias (linhas  no conjunto)
    private final int wordSize = 4;//tamanho da palavra em bytes
    private String[] e;//endereços de memória
    private int cacheHit = 0, cacheMiss = 0;
    private HashSet<Integer> cache = new HashSet<>();

    public CacheMapper(ArrayList<String[]> linhas) {
        this.n = StringToInt(linhas.get(0)[0]);
        this.p = StringToInt(linhas.get(1)[0]);
        this.l = StringToInt(linhas.get(2)[0]);
        this.v = StringToInt(linhas.get(3)[0]);
        this.e = linhas.get(4);
        initialize();
    }

    public void initialize(){
        System.out.println(dataBitSize(getBlockSize()));//linha 1
        System.out.println(dataBitSize(getSetAmount()));//linha 2
        System.out.println(getTagBitSize());//linha 3
        cacheReader();
        //System.out.println(Arrays.toString(lineGenerator(e[0])));
        System.out.println(cacheMiss);//linha 4
        System.out.println(cacheHit);//linha 5
    }

    public int StringToInt(String s){
        int number = 0;
        try{
            number = Integer.parseInt(s);
        }catch(NumberFormatException e){
            System.out.println("Invalid number");
        }
        return number;
    }

    /**
     * @param num
     * @return número de bits número de bits necessários para representar um número
     */
    public int dataBitSize(long num){
        return (int)(Math.log(num)/Math.log(2));
    }
    
    /**
     * @return tamanho do bloco em bytes
     */
    public int getBlockSize(){
        return wordSize * p;
    }

    /**
     * @return quantidade de conjuntos na memória cache
     */
    public int getSetAmount() {
        return l / v;
    }

    /**
     * Calcula quantidade de bits da TAG a partir do número de conjuntos e tamanho do bloco
     * @return tamanho da TAG
     */
    public int getTagBitSize(){
        return dataBitSize(n) - (dataBitSize(getSetAmount())+dataBitSize(getBlockSize()));
    }

    /**
     * Calcula primeiro endereço de memória do bloco
     * @param address
     * @return número do bloco na memória
     */
    public int firstAddress(String address){
        int addressInt = StringToInt(address);
        return (addressInt / getBlockSize())*getBlockSize();
    }

    public void generateLine(String address){
        int currentAddress = firstAddress(address);
        //int[] line = new int[getBlockSize()];
        for (int i = 0; i < getBlockSize(); i++) {
            //line[i] = currentAddress;
            cache.add(currentAddress);
            currentAddress++;
        }
    }

    public void cacheReader(){
        for (String address : e) {
            int addressInt = StringToInt(address);
            if(cache.contains(addressInt)){
                cacheHit++;
            }else{
                cacheMiss++;
                generateLine(address);
            }
        }
    }
}
