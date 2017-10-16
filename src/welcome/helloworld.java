package welcome;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.Paths;
import welcome.Graph.*;

public class helloworld {
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException{
		
//		File file = new File("C:\\Users\\RIZERO\\Desktop\\text.txt");
//		Scanner sc = new Scanner(file,"UTF-8");
		Scanner sc = new Scanner(Paths.get("C:\\Users\\Slardar\\Desktop\\text.txt"),"UTF-8");
		ArrayList<String> list = new ArrayList<String>();
		String s = "[a-z]*", word1 = "", word2 = "";
		Pattern pattern = Pattern.compile(s);
		Matcher ma;
		String str;
		boolean flag1 = true, flag2 = true;
		
		List<Vertex> verList = new LinkedList<Graph.Vertex>();

		Map<String, List<Edge>> ve_Map = new HashMap<String, List<Edge>>();

		
		Vertex vertex = null;

		while(sc.hasNext()) 
		{
	        str = sc.next().toLowerCase();
	        ma = pattern.matcher(str);
			while(ma.find()) 
				if(!ma.group().equals(""))
				    list.add(ma.group());
		}
			for(String word : list) {
				word1 = word2;
				word2 = word;
				flag1 = true;
				flag2 = true;
				vertex = new Vertex(word1);
				for(Vertex ver : verList) {
					if (word1.equals(ver.getName())|| word1.equals("")) {
						flag1 = false;
						break;
					}
				}
				
                if(flag1) {
                	vertex = new Vertex(word1);
					verList.add(vertex);
					ve_Map.put(vertex.getName(), new LinkedList<Graph.Edge>());
				}
                
                for(Edge edge : ve_Map.get(word1)) {
					if (word2.equals(edge.getEnd().getName())) {
	
						edge.upWeight();
						flag2 = false;
						break;
						}
					}
				
                if(flag2 && !(word1.equals(""))) {
                	vertex = new Vertex(word2);
                	ve_Map.get(word1).add(new Edge(vertex, 1));
                }	
			}
		
		for(Vertex ver : verList) {
			for(Edge edge : ve_Map.get(ver.getName())) {
				System.out.println(ver.getName()+"->"+edge.getEnd().getName()+" = "+edge.getWeight());
			}
		}
		System.out.println(list);
		System.out.println(new Date());
		sc.close();
		
		
		
	}
}