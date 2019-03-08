package practice;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
	public void main() {
		String genres[] = {"classic", "pop", "classic", "classic", "pop"};
		int plays[] = {500, 600, 150, 800, 2500};
		
		solution(genres,plays);
	}
	
	
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        HashMap<String, List<HashMap<Integer,Integer>>> hm = new HashMap();
        HashMap<String, Integer> genresRank = new HashMap();
        
        for(int i = 0; i < genres.length ; i++) {
            List <HashMap<Integer,Integer>> addList = new ArrayList<>();
            HashMap<Integer,Integer> addHm = new HashMap();
            addHm.put(i,plays[i]);
            if (hm.containsKey(genres[i])) {
                addList = hm.get(genres[i]);
                int totalPlays = genresRank.get(genres[i]);
                totalPlays += totalPlays + plays[i];
                System.out.println("genres[i] : " + i + ", " + genres[i] + ", totalPlays : " + totalPlays);
                genresRank.put(genres[i], totalPlays);
                if (addList.size() < 2) {
                    hm.put(genres[i],checkAdd(addList,addHm));
                }
            } else {
                genresRank.put(genres[i], plays[i]);
                addList.add(addHm);
                System.out.println(genres[i] + ", " + addList);
                hm.put(genres[i],addList);
                System.out.println(hm);
            }
        }
        sortByValue(genresRank);
        System.out.println(genresRank);
        System.out.println(hm);
        
        return answer;
    }
    
    public List <HashMap<Integer,Integer>> checkAdd(List <HashMap<Integer,Integer>> checkList, HashMap<Integer,Integer> addHm) {
        System.out.println("start");
        Object firstKey = addHm.keySet().toArray()[0];
        int plays = addHm.get(firstKey);

        for (int i = 0; i < checkList.size(); i++) {
            HashMap<Integer,Integer> checkHm = checkList.get(i);
            Object firstKey1 = checkHm.keySet().toArray()[0];
            int plays2 = checkHm.get(firstKey1);
            System.out.println("index : " + i + ", plays2 : " + plays2 + ",plays : " + plays);
            if (plays2 < plays) {
                   if (i == 1) {
                       System.out.println("before : " + checkList);
                       HashMap <Integer, Integer> tempList = checkList.get(0);
                       checkList.set(1,addHm);
                       checkList.set(2,tempList);
                       System.out.println("after : " +checkList);
                   } else {
                       System.out.println("before : " + checkList);
                       checkList.add(1,addHm);
                       System.out.println("after : " +checkList);
                   }
            }
        }
        System.out.println("end");
        return checkList;
    }
    
    public static List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
        Collections.sort(list,new Comparator() {

            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }
}