import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream; 

// [INFO ] 2015-12-19 14:53:28,570 com.scatterlab.bxt.business.CardService.setPurchase - purchase card: userId=56AK cardId=5674f0d14b280bd4dfb9995d type=FEEDBACK category=GINGER subType=EARLY_BIRD_2015 createTime=20151219145321 price=0 energy=81 reward=0
// [INFO ] 2015-12-19 15:12:05,630 com.scatterlab.bxt.controller.api.APICardController.setEvent - set event: userId=4qpK cardId=5674f5280cf20e98bf4f946b event=EARLY_BIRD_2015_120

// 목표: 두 개의 파일에서 유저의 에너지와 클릭한 에너지 출력  

class User
{
	private String userId;
	private int userEnergy;
	private int userClickEnergy;
	
	User(String name)
	{
		userId = name;
	}
	
	User(String name, int energy)
	{
		userId = name;
		userEnergy = energy; 
	}
	
	void setUserClickEnergy(int clickEnergy)
	{
		this.userClickEnergy = clickEnergy;
	}
	
	public String toString()
	{
		return "UserId: " + this.userId + ", current Energy: " + userEnergy + ", clickEnergy: " + userClickEnergy;
	}
}

public class CardReward {	
	public static void main(String[] args) throws IOException {
		final Map<String, User> map = new HashMap<>();
		String fileName = "input1.txt";
		String fileName2 = "input2.txt";
		
		/*
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream
			.map(line -> {
				String[] param = line.split(" ");
				String userId = param[8];
				String userEnergy = param[param.length-2];
				
				String[] parseUserId = userId.split("=");
				String[] parseUserEnergy = userEnergy.split("=");

				return parseUserId[1] + "," + parseUserEnergy[1];})
			.forEach((s) -> {
				String[] parsing = s.split(",");
				map.put( parsing[0], new User(parsing[0], Integer.parseInt(parsing[1])));								
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Map<String, String> userEnergies = Files.lines(Paths.get(fileName)).map(l -> l.split(" ")).collect(Collectors.toMap(data -> data[8].split("=")[1], data -> data[data.length-2].split("=")[1]));
		Map<String, String> clickEnergies = Files.lines(Paths.get(fileName2)).map(l -> l.split(" ")).collect(Collectors.toMap(data -> data[data.length-3].split("=")[1], data -> data[data.length-1].split("_")[3]));
		
		userEnergies.entrySet().stream().forEach(e -> {
			System.out.println(e.getKey() + "\t" + e.getValue() + "\t" + clickEnergies.getOrDefault(e.getKey(), "no click"));
		});
		
		//read file into stream, try-with-resources
		/*
		try (Stream<String> stream1 = Files.lines(Paths.get(fileName2))) {
			stream1
			.map(line -> {
				String[] param = line.split(" ");
				String[] parseUserId = param[param.length-3].split("=");
				String[] parseUserClickEnergy = param[param.length-1].split("_");
						
				return parseUserId[1] + "," + parseUserClickEnergy[parseUserClickEnergy.length-1]; })
			.forEach((s) -> {
				String[] parsing = s.split(",");
				String keyForUserId = parsing[0];
				
				User user = map.get(keyForUserId);
				if(user != null)
					user.setUserClickEnergy(Integer.parseInt(parsing[1]));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		for(User user : map.values()) {
			System.out.println(user.toString());
		}
		*/
	}
}
		
	
//		File myFile = new File("input1.txt");
//		FileReader fileReader = new FileReader(myFile);		
//		BufferedReader reader = new BufferedReader(fileReader);
//				
//		// 첫 번째 파일 읽으면
//		final Map<String, User> map = new HashMap<>();
//		
//		String input = "";
//				
//		while ( (input = reader.readLine()) != null) {
//			String[] param = input.split(" ");
//			String userId = param[8];
//			String userEnergy = param[param.length-2];
//			
//			String[] parseUserId = userId.split("=");
//			String[] parseUserEnergy = userEnergy.split("=");
//						
//			map.put( parseUserId[1], new User(parseUserId[1], Integer.parseInt(parseUserEnergy[1])));			
//		}
//				
//		
//		// 두 번째 파일 읽으면
//		File myFile2 = new File("input2.txt");
//		FileReader fileReader2 = new FileReader(myFile2);		
//		BufferedReader reader2 = new BufferedReader(fileReader2);
//		
//		String input2 = "";
//				
//		while ( (input2 = reader2.readLine()) != null) {
//			String[] param2 = input2.split(" ");
//			
//			String[] parseUserId = param2[param2.length-3].split("=");
//			String[] parseUserClickEnergy = param2[param2.length-1].split("_");
//	
//			String keyForUserId = parseUserId[1];
//			User user = map.get(keyForUserId);
//			if(user != null)
//				user.setUserClickEnergy(Integer.parseInt(parseUserClickEnergy[parseUserClickEnergy.length-1]));
//		}
//		
//		Iterator itr = map.keySet().iterator();
//		while (itr.hasNext()) {
//			String key = (String)itr.next();
//			User user = map.get(key);
//			System.out.println(user.toString());
//		}		

