import java.util.regex.Pattern;

public class Day4 {

    public static void main(String[] args) {

        int lowerBound = 136760;
        int upperBound = 595730;

        int numberOfCandidates = 0;

        for (int i = lowerBound; i < upperBound + 1; i++) {

            if (LikeAdjacentToOneLike(String.valueOf(i)) && onlyIncreasingNumbers(i)) {
                numberOfCandidates++;
                System.out.println("Candidate: " + i);
            }
        }

        System.out.println("The number of potentially correct passwords are: " + numberOfCandidates);




    }

    public static boolean LikeAdjacentToLike(String s) {
        String pattern = "^.*(\\d)(\\1).*$";
        return s.matches(pattern);
    }

    public static boolean LikeAdjacentToOneLike(String s) {
        String pattern = "^(\\d)*(?!\\1)(\\d)\\2(?!\\2).*$";
        return s.matches(pattern);
    }

    public static boolean onlyIncreasingNumbers(int number) {

        int last = 10;
        int digit = 10;

        while (number > 10) {

           digit = number % 10;
           number = number / 10;

           if (!(digit <= last)) {
               return false;
           }
           last = digit;

        }
        return (number <= last);
    }





}
