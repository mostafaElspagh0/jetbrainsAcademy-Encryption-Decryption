package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        String comand = "enc";
        String alg = "shift";
        StringBuilder s = new StringBuilder("");
        String in ;
        File inputFile ;
        FileWriter outputFile = null;
        boolean outflag = false;
        int key = 0;
        for (int i = 0 ; i < args.length ; i+=2) {
            if(args[i].equals("-mode")){
                comand = args[i+1];
            }else if ( args[i].equals("-key")){
                key = parseInt(args[i+1]);
            }else if ( args[i].equals("-data")){
                s = new StringBuilder(args[i+1]);
            }else if ( args[i].equals("-in")){
                Scanner scanner = new Scanner( new File(args[i+1]) );
                s = new StringBuilder(scanner.useDelimiter("\\A").next());
                scanner.close();
            } else if (args[i].equals("-out")){
                outflag = true;
                outputFile = new FileWriter(args[i+1]);
            } else if (args[i].equals("-alg")){
                alg = args[i+1];

            }
        }

        if(alg.equals("shift")){
            if(comand.equals("enc")){
                for(int i = 0 ; i < s.length() ; i++){
                    if((int) s.charAt(i) >= 97 && (int) s.charAt(i) <= 122){
                        s.setCharAt(i,(char)(((int)s.charAt(i) + key - 97) % 26 + 97));
                    } else if((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 90){
                        s.setCharAt(i,(char)(((int)s.charAt(i) + key - 65) % 26 + 65));
                    }
                }
            } else {
                for(int i = 0 ; i < s.length() ; i++){
                    if((int) s.charAt(i) >= 97 && (int) s.charAt(i) <= 122){
                        s.setCharAt(i,(char)(((int)s.charAt(i) +26 - key - 97) % 26 + 97));
                    } else if((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 90){
                        s.setCharAt(i,(char)(((int)s.charAt(i) +26- key - 65) % 26 + 65));
                    }
                }
            }
        }else{
            if(comand.equals("enc")){
                for(int i = 0 ; i < s.length() ; i++){
                    s.setCharAt(i,(char)((int)s.charAt(i)+key));
                }
            } else {
                for(int i = 0 ; i < s.length() ; i++){
                    s.setCharAt(i,(char)((int)s.charAt(i)-key));
                }
            }
        }

        if(outflag){
            outputFile.write(s.toString());
            outputFile.close();
        }else{
            System.out.println(s);
        }

    }
}
