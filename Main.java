import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File diretorio = new File("testes\\");
        File[] arquivos = diretorio.listFiles();
        LeitorTxt leitorTxt;
        //EscritorTxt escritor = new EscritorTxt();
        ArrayList<String[]> linhas = new ArrayList<>();
    

        for (File arquivo : arquivos) {
            String name = arquivo.getName();
            System.out.println(arquivo.getName());
            leitorTxt = new LeitorTxt(arquivo.getAbsolutePath());
            String[] linhaFatiada = null;
            while ((linhaFatiada = leitorTxt.proximaLinha()) != null) {
                linhas.add(linhaFatiada);
                // popular ArrayList "linha"
            }
            try {
                CacheMapper cm = new CacheMapper(linhas);
                //escritor.write(null, arquivo.getName());
                
            }catch(NullPointerException e){
                System.out.println(-1);
            }
            linhas.clear();
        }
    }
}
