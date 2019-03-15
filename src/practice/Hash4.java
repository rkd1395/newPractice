package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Hash4 {
	public static void main(String[] args) {
		String genres[] = {"classic", "pop", "classic", "classic", "pop","RnB", "classic", "classic", "classic"};
		int plays[] = {500, 600, 150, 800, 2500, 3500, 700, 850, 1500};
		
		solution(genres,plays);
	}
	
	
    public static int[] solution(String[] genres, int[] plays) {
    	List<Integer> answerList = new ArrayList<Integer>();
        int[] answer = {};
        
        HashMap<String, List<HashMap<Integer,Integer>>> top2Rank = new HashMap();
        HashMap<String, Integer> genresRank = new HashMap();
        
        for(int i = 0; i < genres.length ; i++) {
            
        	HashMap<Integer,Integer> genresRankAddHm = new HashMap();
            HashMap <Integer,Integer> top2RankAddHm = new HashMap<Integer, Integer>();
            
            genresRankAddHm.put(i,plays[i]);
            
            HashMap<Integer,Integer> hm4newList = new HashMap<Integer, Integer>();
            hm4newList.put(i, plays[i]);
            
            if (genresRank.containsKey(genres[i])) {
                int totalPlays = genresRank.get(genres[i]);
                
                totalPlays += totalPlays + plays[i];
                genresRank.put(genres[i], totalPlays);
                
                List<HashMap<Integer,Integer>> originList = top2Rank.get(genres[i]);
                List<HashMap<Integer,Integer>> modifiedList = checkAdd(originList,hm4newList);
                top2Rank.put(genres[i], modifiedList);
            } else {
                genresRank.put(genres[i], plays[i]);
                List<HashMap<Integer,Integer>> newList = new ArrayList<HashMap<Integer,Integer>>();
                newList.add(hm4newList);
                top2Rank.put(genres[i], newList);
            }
        }
        Iterator it = sortByValue(genresRank).iterator();
        while(it.hasNext()){
            String temp = (String) it.next();
            
            
            List<HashMap<Integer,Integer>> genreRankNumList = top2Rank.get(temp);
            answerList.add((Integer) genreRankNumList.get(0).keySet().toArray()[0]);
            if (genreRankNumList.size() > 1)
            answerList.add((Integer) genreRankNumList.get(1).keySet().toArray()[0]);
        }
        
        System.out.println(answerList);
        answer = answerList.stream().mapToInt(i->i).toArray();
        return answer;
    }
    
    public static List <HashMap<Integer,Integer>> checkAdd(List <HashMap<Integer,Integer>> orgList, HashMap<Integer,Integer> addHm) {
    	Object firstKey = addHm.keySet().toArray()[0];
        int comparePlays = addHm.get(firstKey);
        int orgListSize = orgList.size();

        for (int i = 0; i < orgListSize; i++) {
            HashMap<Integer,Integer> orgHm = orgList.get(i);
            Object orgNumber = orgHm.keySet().toArray()[0];
            int orgPlays = orgHm.get(orgNumber);
            if (orgPlays < comparePlays) {
                   if (i == 0) { // 첫번째 값보다 클 때 새로운 값을 앞에 끼워넣고 만약 길이가 1이면 첫번째 값이 두번쨰 값으로 이동만, 길이가 2면 첫번째 값이 두번쨰 값으로 가고 3번째 값은 삭제.
                       HashMap <Integer, Integer> tempList = orgList.get(0);
                       orgList.set(0,addHm);
                       if (orgListSize != 2) {
                    	   orgList.add(tempList);
                    	   break;
                       } else {
                    	   orgList.remove(1);
                    	   orgList.add(tempList);
                    	   break;
                       }
                   } else { // 두번째 값보다 클 때 두번째 값을 지우고 추가한다.
                	   orgList.remove(1);
                       orgList.add(addHm);
                   }
            } else {
            	if (orgListSize != 2) {
             	   orgList.add(addHm);
                }
            }
        }
        return orgList;
    }
    
    public static List sortByValue(final Map map) {
        
        List<String> keySetList = new ArrayList<>(map.keySet());
        // 내림차순 //
        Collections.sort(keySetList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return ((Integer) map.get(o2)).compareTo((Integer) map.get(o1));
            }
        });
        
        Collections.sort(keySetList, (o1, o2) -> (((Integer) map.get(o1)).compareTo((Integer) map.get(o2))));
        
        Collections.reverse(keySetList); 
        return keySetList;
    }
}