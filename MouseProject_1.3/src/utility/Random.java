package utility;

public class Random {
	
    public static int rand(int i, int j) {
    	
        java.util.Random rnd = new java.util.Random();
        int nombre = i + rnd.nextInt(j - i + 1);
        return nombre;
    }
}
