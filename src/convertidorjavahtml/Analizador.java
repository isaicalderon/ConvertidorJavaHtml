
package convertidorjavahtml;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kirk
 */

public class Analizador {
    String[] pr = new String[50];
    String pr1 = "<span cla-ss='pr'>";
    String pr2 = "</span>";
    
    FileDialog filetmp;
    Menu menu;
    File file;
    FileReader fr;
    BufferedReader br;
    String nameFile = "";
    int cont = 0;
    
    public Analizador(){
        this.pr[0] = "abstract";
	this.pr[1] = "assert";
	this.pr[2] = "boolean";
	this.pr[3] = "break";
	this.pr[4] = "byte";
	this.pr[5] = "case";
	this.pr[6] = "catch";
	this.pr[7] = "char";
	this.pr[8] = "class";
	this.pr[9] = "const";
 	
	this.pr[10] = "continue";
	this.pr[11] = "default";
	this.pr[12] = "do";
	this.pr[13] = "double";
	this.pr[14] = "else";
	this.pr[15] = "enum";
	this.pr[16] = "extends";
	this.pr[17] = "final";
	this.pr[18] = "finally";
	this.pr[19] = "float";
 	
	this.pr[20] = "for";
	this.pr[21] = "goto";
	this.pr[22] = "if";
	this.pr[23] = "implements";
	this.pr[24] = "import";
	this.pr[25] = "instanceof";
	this.pr[26] = "int";
	this.pr[27] = "interface";
	this.pr[28] = "long";
	this.pr[29] = "native";
 	
	this.pr[30] = "new";
	this.pr[31] = "package";
	this.pr[32] = "private";
	this.pr[33] = "protected";
	this.pr[34] = "public";
	this.pr[35] = "return";
	this.pr[36] = "short";
	this.pr[37] = "static";
	this.pr[38] = "strictfp";
	this.pr[39] = "super";
 	
	this.pr[40] = "switch";
	this.pr[41] = "synchronized";
	this.pr[42] = "this";
	this.pr[43] = "throw";
	this.pr[44] = "throws";
	this.pr[45] = "transient";
	this.pr[46] = "try";
	this.pr[47] = "void";
	this.pr[48] = "volatile";
	this.pr[49] = "while";
    }
    
    public String analizar(String text){
        text = text.replace("\n", " <br> ");
        text = text.replace("\t", " TAB ");
        String[] tmp = text.split(" ");
        String value = "";
        boolean var = false;
        
        for (int i = 0; i < tmp.length; i++) {
            System.out.println("Analizando: "+tmp[i]);
            if (buscarPalabraReservada(tmp[i])) {
                text = text.replace(tmp[i], pr1+tmp[i]+pr2);
            }else{
                if (var) {
                    for (int j = 0; j < tmp[i].length(); j++) {
                        if(tmp[i].charAt(j)!=';'){
                            value += tmp[i].charAt(j);
                            System.out.println(value);
                        }else{
                            break;
                        }
                    }

                    //value = value;
                    //System.out.println("buscando: "+value+" status: "+ m.find());
                    text = text.replace(value+";", "<span cla-ss='var'>"+value+"</span>;");
                    var = false;
                }
                if (tmp[i].equals("String")) {
                    System.out.println("asdfghsa");
                    var = true;
                }
                
                //System.out.println("no soy PR soy "+tmp[i]);
                
                /*
                Pattern p = Pattern.compile("(\\W)([a-z]*)");
                Matcher m = p.matcher(tmp[i]);
                //if (m.find()) {
                    while(m.find()){
                        for (int k = m.start(); k < m.end(); k++) {
                            //System.out.println("este mero: "+tmp[i].charAt(k));
                            //m.
                            value += tmp[i].charAt(k);
                        }
                    }
                    value = value+";";
                    System.out.println("buscando: "+value+" status: "+ m.find());
                    text = text.replace(value, "<span cla-ss='var'>"+value+"</span>;");
                    value = "";
                    
                //}
                */
            }
            
            /*
            for (int j = 0; j < pr.length; j++) {
                if (tmp[i].equals(pr[j])) {
                    text = text.replace(pr[j], pr1+pr[j]+pr2);
                }else{
                    
                    System.out.println("no soy PR soy "+tmp[i]);
                    Pattern p = Pattern.compile("^[a-z]*");
                    Matcher m = p.matcher(tmp[i]);
                    //if (m.find()) {
                        while(m.find()){
                            for (int k = m.start(); k < m.end(); k++) {
                                //System.out.println("este mero: "+tmp[i].charAt(k));
                                //m.
                                value += tmp[i].charAt(k);
                            }
                        }
                        value = value+";";
                        System.out.println("buscando: "+value);
                        text = text.replace(value, "<span cla-ss='var'>"+value+"</span>;");
                        value = "";
                    //}
                }
            }
            */
        }
        text = formatear(text);
        return text;
    }
    
    public boolean buscarPalabraReservada(String palabra){
        for (String pr: pr){
            if (pr.equals(palabra)) {
                return true;
            }
        }
        return false;
    }
    
    public String formatear(String text){
        System.out.println("Formateando...");
        text = text.replace("cla-ss", "class");
        text = text.replace("TAB", "<span class='tab'></span>");
        
        //String[] tmp = text.split(" ");
        //boolean tab = false;
        
//        for (String temp : tmp){
//            //System.out.println(temp+"");
//            if (temp.equals("TAB")) {
//                tab = true;
//            }
////            
////            if(tab){
////                for (int i = 0; i < temp.length(); i++) {
////                    if (temp.charAt(i) == ';') {
////                        System.out.println("Encontre un ;");
////                        tab = false;
////                    }
////                    
////                }
////                
////            }
//            
//        }
//        
        return text;
    }
    
    public String abrirArchivo(){
        filetmp = new FileDialog(menu, "Abrir archivo",FileDialog.LOAD);
        filetmp.setFile("*.txt");
        filetmp.setVisible(true);
        nameFile = filetmp.getFile();
        
        String ruta = filetmp.getDirectory();
        file = new File(ruta+""+nameFile);
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String texto;
            String tmp="";
            int leng = 0;
            try {
                while((texto=br.readLine())!=null ){
                    leng++;
                    tmp = tmp + texto;
                }
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                tmp = "";
                while((texto=br.readLine())!=null){
                    cont++;
                    if (cont == leng) {
                        tmp = tmp + texto;
                    }else{
                        tmp = tmp + texto+"\n";
                    }
                }
            } catch (IOException ex) {
                System.out.println("Nota: "+ex);
            }
            //clean(tmp);
            
            return tmp;
        } catch (FileNotFoundException ex) {
            System.out.println("Nota: "+ex);
        }
        return "No se abrio el archivo";
        
    }
    
    
}
