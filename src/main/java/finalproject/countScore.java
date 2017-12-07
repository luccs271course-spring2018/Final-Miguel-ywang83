package finalproject;

public class countScore {
    public static int score(int n) {
        if (n == 3)
            return 0;
        if (n == 4)
            return 10;
        else
            return (n-3)*10+ score(n-1);
    }
}

