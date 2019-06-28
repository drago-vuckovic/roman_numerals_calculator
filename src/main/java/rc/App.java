package rc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args){


        if (args.length > 0) {
            System.out.println("The command line"+ " arguments are:");

            for (String val:args)
                System.out.println(val);

            String romanFirst = args[0];
            String romanSecond = args[1];

            int arabicFirst = romanToArabic(romanFirst);
            int arabicSecond = romanToArabic(romanSecond);

            int sumOfTwoArabs =addTwoArabs(arabicFirst, arabicSecond);
            System.out.println("Sum of two arabs: " + sumOfTwoArabs);

            System.out.println("Arab As Roman: " + arabicToRoman(sumOfTwoArabs));
        } else {
            System.out.println("No command line " + "arguments found.");
        }


    }


    enum RomanNumeral {

        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }


    public static int romanToArabic(String p_romanInput) {

        int result = 0;

        String romanNumeralUpperCaseInput = p_romanInput.toUpperCase();

        List<RomanNumeral> romanNumeralList = RomanNumeral.getReverseSortedValues();

        int i=0;

        while(romanNumeralUpperCaseInput.length()>0 && (i<romanNumeralList.size())){

            RomanNumeral currentRomanSymbol = romanNumeralList.get(i);

            if(romanNumeralUpperCaseInput.startsWith(currentRomanSymbol.name())) {

                result += currentRomanSymbol.getValue();
                romanNumeralUpperCaseInput = romanNumeralUpperCaseInput.substring(currentRomanSymbol.name().length());

            } else {
                i++;
            }
        }

        if(romanNumeralUpperCaseInput.length()>0){
            throw new IllegalArgumentException(p_romanInput + " cannot be converted to Roman Numeral");
        }

        return result;
    }


    public static String arabicToRoman(int p_arabInput) {

        if ((p_arabInput <= 0) || (p_arabInput > 4000)) {
            throw new IllegalArgumentException(p_arabInput + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumeralList = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((p_arabInput > 0) && (i < romanNumeralList.size())) {

            RomanNumeral currentRomanSymbol = romanNumeralList.get(i);

            if (currentRomanSymbol.getValue() <= p_arabInput) {
                sb.append(currentRomanSymbol.name());
                p_arabInput -= currentRomanSymbol.getValue();

            } else {
                i++;
            }
        }

        return sb.toString();
    }


    public static int addTwoArabs(int p_arabFirst, int p_arabSecond){
        return p_arabFirst + p_arabSecond;
    }

    public static int subtractTwoArabs(int p_arabFirst, int p_arabSecond){
        return p_arabFirst - p_arabSecond;
    }

}
