package findPairBonus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FindPair {

	public static int[] getClosest(ArrayList<Integer> sortedList, int target) {
		int[] res = new int[3];
		int minDif = Integer.MAX_VALUE;
		int dif;
		 for (int i = 0; i < sortedList.size() - 2; i++) {
			 int left = i+1, right = sortedList.size()-1;
			 while(left<right) {
				 dif=target-(sortedList.get(i)+sortedList.get(left)+sortedList.get(right));
				 if(dif>0) {
					 if(dif<minDif) {
						 res[0] = sortedList.get(i);
						 res[1] = sortedList.get(left);
						 res[2] = sortedList.get(right);
						 minDif=dif;
						 
					 }
					 
					 left++;
				 }
				 else if(dif<0) {
					 right--;
				 }
				 else {
					 res[0] = sortedList.get(i);
					 res[1] = sortedList.get(left);
					 res[2] = sortedList.get(right);
					 break;
				 }
			 }
			 
		 }

		

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = System.getProperty("user.dir") + "/data/" + args[0];

		ArrayList<Integer> prices = new ArrayList<Integer>();
		HashMap<Integer, String> names = new HashMap<Integer, String>();
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {

				String[] priceName = line.split(" *, *");
				String name = priceName[0];
				int price = Integer.parseInt(priceName[1]);

				prices.add(price);
				names.put(price, name);
				line = reader.readLine();

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int target = Integer.parseInt(args[1]);
		int[] pricePair = FindPair.getClosest(prices, target);

		// System.out.println(Arrays.toString(pricePair));
		if (pricePair[0] != 0) {
			System.out.printf("%s %d,%s %d,%s %d\n", names.get(pricePair[0]), pricePair[0], names.get(pricePair[1]),pricePair[1],names.get(pricePair[2]),pricePair[2]);
		} else {
			System.out.println("Not possible");
		}

	}

}
