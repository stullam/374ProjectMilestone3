package app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphVizImageConverter {

	private String dotCommandLocation;
	
	public GraphVizImageConverter(String location)
	{
		dotCommandLocation = location;
	}
	
	
	//TAKES OUR GRAPHVIZ TEXT AND CONVERTS IT INTO A GRAPHVIZ IMAGE.
	//IMAGE IS SAVED AS myGraph.png
	public void convertGraphVizText(String nameOfImage, String locationOfText) throws IOException {
		 try {
		      String line;
		      Process p = Runtime.getRuntime().exec(dotCommandLocation+" -Tpng -o "+nameOfImage+".png "+locationOfText);
		      BufferedReader bri = new BufferedReader
		        (new InputStreamReader(p.getInputStream()));
		      BufferedReader bre = new BufferedReader
		        (new InputStreamReader(p.getErrorStream()));
		      while ((line = bri.readLine()) != null) {
		        System.out.println(line);
		      }
		      bri.close();
		      while ((line = bre.readLine()) != null) {
		        System.out.println(line);
		      }
		      bre.close();
		      p.waitFor();
		      System.out.println("Done.");
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		  }
	
	
	//OPENS A IMAGE.THIS METHOD IS SIMPLY A TEST. RUNS "open image.txt"
	public void openGraphVizImage(String locationOfImage)
	{
		 try {
		      String line;
		      Process p = Runtime.getRuntime().exec("open "+locationOfImage);
		      BufferedReader bri = new BufferedReader
		        (new InputStreamReader(p.getInputStream()));
		      BufferedReader bre = new BufferedReader
		        (new InputStreamReader(p.getErrorStream()));
		      while ((line = bri.readLine()) != null) {
		        System.out.println(line);
		      }
		      bri.close();
		      while ((line = bre.readLine()) != null) {
		        System.out.println(line);
		      }
		      bre.close();
		      p.waitFor();
		      System.out.println("Done.");
		    }
		    catch (Exception err) {
		      err.printStackTrace();
		    }
		  }
	}

