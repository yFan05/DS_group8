import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Main
{
  public static void main(String[] args) throws IOException {

    ArrayList<Keyword> keywords = new ArrayList<>();//儲存關鍵字的列表
    keywords.add(new Keyword("綠色",3));
    keywords.add(new Keyword("綠能",3));
    keywords.add(new Keyword("能源",3));
    keywords.add(new Keyword("氣候",3));
    keywords.add(new Keyword("環保",3));
    keywords.add(new Keyword("環境",3));
    keywords.add(new Keyword("政策",2));
    keywords.add(new Keyword("法規",2));

    Scanner scanner = new Scanner(System.in);
    String url1=scanner.next();
    Node n1 = new Node(url1,"url1");
    Tree t = new Tree(n); //第一個url當root
    
    String url1=scanner.next();
    Node n2 = new Node(url2,"url2");
    addChild(n2);
   
