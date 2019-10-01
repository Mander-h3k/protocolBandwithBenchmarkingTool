import java.net.*;
import java.io.*;

class protocol extends Thread{
    //class variables
    boolean c1, c2, c3, c4, c5, c6, c7;

    protocol(){
    }

    //tests the connection with a HTTPserver
    public boolean pingHTTPserver(String linkURL)throws MalformedURLException, IOException{
        String link = linkURL;
        boolean returnStatus;

        //public static void main(String[] args)
        //throws MalformedURLException, IOException{
        //try{
            URL url=new URL(link);
            URLConnection urlconn=url.openConnection();
            System.out.println(urlconn.getContentType());
        //}
        //catch(MalformedURLException URLException){
            returnStatus = false;
        //}

            //InputStream in=urlconn.getInputStream();
            //int ch;
            //while((ch = in.read())!= -1){
                //System.out.print((char)ch);
            //}
            //in.close();
        return returnStatus;
    }

    public boolean pingHTTPSserver(){
        c2 =false;
        return c2;

    }

    public boolean pingFTPserver(){
        c3 =true;
        return c3;

    }

    public boolean pingSFTPserver(){
        c4 =true;
        return c4;

    }

    public boolean pingSMBserver(){
        c5 =true;
        return c5;

    }

    public boolean pingSSHserver(){
        c6 =true;
        return c6;

    }

    public boolean pingDNSserver(){
        c7 =true;
        return c7;

    }
}