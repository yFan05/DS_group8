package google.demo.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import google.demo.service.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		//root node
		WebPage rootPage = new WebPage("http://your-url.com", "RootPage");		
		WebTree tree = new WebTree(rootPage);
		
		

		while (scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();

			for (int i = 0; i < numOfKeywords; i++){
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword k = new Keyword(name, weight);
				keywords.add(k);
			}

			tree.setPostOrderScore(keywords);
		}
		scanner.close();
	}

}
