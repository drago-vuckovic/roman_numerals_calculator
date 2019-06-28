package rc;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
public class RomanArabicConverterUnitTest {

    @Test
      public void roman_1234_convertedToArabicReturns_1234() {
        String roman_1234 = "MCCXXXIV";

        int result = App.romanToArabic(roman_1234);

        assertThat(result).isEqualTo(1234);
    }

    @Test
    public void arabic_3987_convertedToRomanReturns_MMMCMLXXXVII() {

        int arabic_3987 = 3987;

        String result = App.arabicToRoman(arabic_3987);

        assertThat(result).isEqualTo("MMMCMLXXXVII");
    }

}
