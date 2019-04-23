import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AP1 {
    public List wordsWithoutList(String[] words, int len) {
        List<String> list = new ArrayList<String>();
        for(String s : words){
            if(s.length() != len) list.add(s);
        }
        return list;
    }
    public boolean hasOne(int n) {
        return String.valueOf(n).contains("1");
    }
    public boolean dividesSelf(int n) {
        for(int i = n; i > 0; i /= 10){
            if(i%10 == 0 || n%(i%10) != 0 )
                return false;
        }
        return true;
    }
    public int[] copyEvens(int[] nums, int count) {
        int[] evens = new int[count];
        for(int num : nums){
            if(count == 0) break;
            if(num%2 == 0){
                evens[evens.length - count] = num;
                count--;
            }
        }
        return evens;
    }
    public int[] copyEndy(int[] nums, int count) {
        int[] endy = new int[count];
        for(int num : nums){
            if(count == 0) break;
            if((num >= 0 && num <= 10) || (num >= 90 && num <= 100)){ //too lazy to put this in separate method lol
                endy[endy.length - count] = num;
                count--;
            }
        }
        return endy;
    }
    public int matchUp(String[] a, String[] b) {
        int c = 0;
        for(int i = 0; i < a.length; ++i){
            if(!a[i].isEmpty() && !b[i].isEmpty()){
                if(a[i].charAt(0) == b[i].charAt(0))
                    c++;
            }
        }
        return c;
    }
    public int scoreUp(String[] key, String[] answers) {
        int score = 0;
        for(int i = 0; i < key.length; ++i){
            if(!answers[i].equals("?")){
                score += key[i].equals(answers[i]) ? 4 : -1;
            }
        }
        return score;
    }
    public String[] wordsWithout(String[] words, String target) {
        int count = 0;
        for(int i = 0; i < words.length; ++i){
            if(!words[i].equals(target)){
                words[count] = words[i];
                count++;
            }
        }
        return Arrays.copyOfRange(words, 0, count);
    }
    public int scoresSpecial(int[] a, int[] b) {
        return findMax(a) + findMax(b);
    }
    // +helper method
    private int findMax(int[] a){
        int p = 0;
        for(int i : a){
            if(i > p && i%10 == 0){
                p = i;
            }
        }
        return p;
    }
    public int sumHeights(int[] heights, int start, int end) {
        int sum = 0;
        for(int i = start+1; i <= end; ++i){
            sum += Math.abs(heights[i-1] - heights[i]);
        }
        return sum;
    }
    public int sumHeights2(int[] heights, int start, int end) {
        int count = 0;
        for(int i = start+1; i <= end; ++i)
            count += (heights[i-1] < heights[i]) ? Math.abs(heights[i-1] - heights[i])*2 : Math.abs(heights[i-1] - heights[i]);
        return count;
    }
    public int bigHeights(int[] heights, int start, int end) {
        int count = 0;
        for(int i = start+1; i <= end; ++i){
            if(Math.abs(heights[i] - heights[i-1]) >= 5)
                count++;
        }
        return count;
    }
    public int userCompare(String aName, int aId, String bName, int bId) {
        if(!aName.equals(bName))
            return (aName.compareTo(bName) < 0) ? -1 : 1;
        if(!(bId == aId)) return (aId < bId) ? -1 : 1;
        return 0;
    }
    public String[] mergeTwo(String[] a, String[] b, int n) { //O(n)
        String[] f = new String[n];
        int f1 = 0; int f2 = 0;
        for(int i = 0; i < n; ++i){
            if(a[f1].equals(b[f2])){
                f[i] = a[f1]; f1++; f2++;
            } else if(a[f1].compareTo(b[f2]) > 0){
                f[i] = b[f2]; f2++;
            } else {
                f[i] = a[f1]; f1++;
            }
        }
        return f;
    }
    public int commonTwo(String[] a, String[] b) {
        int count = 0;
        int f1 = 0; int f2 = 0;
        while(f1 < a.length && f2 < b.length){
            if(a[f1].equals(b[f2])){
                count++; String temp = a[f1];
                while(f1 < a.length && a[f1].equals(temp)) f1++;
                temp = b[f2];
                while(f2 < b.length && b[f2].equals(temp)) f2++;
            } else if(a[f1].compareTo(b[f2]) > 0)
                f2++;
            else f1++;
        }
        return count;
    }
}
