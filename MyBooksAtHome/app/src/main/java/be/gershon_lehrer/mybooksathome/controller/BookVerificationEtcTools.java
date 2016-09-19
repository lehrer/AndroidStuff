package be.gershon_lehrer.mybooksathome.controller;

/**
 * Created by gershonlehrer on 14/07/16.
 */
public class BookVerificationEtcTools {

    //source http://stackoverflow.com/questions/4270657/is-there-a-way-to-check-if-an-isbn-number-is-a-valid-number-before-storing-into
    public static boolean is_valid_isbn(char isbn[]) {
        int sum = 0;
        if(isbn.length == 10) {
            for(int i = 0; i < 10; i++) {
                sum += i * isbn[i]; //asuming this is 0..9, not '0'..'9'
            }

            if(isbn[9] == sum % 11) return true;
        } else if(isbn.length == 13) {

            for(int i = 0; i < 12; i++) {
                if(i % 2 == 0) {
                    sum += isbn[i]; //asuming this is 0..9, not '0'..'9'
                } else {
                    sum += isbn[i] * 3;
                }
            }

            if(isbn[12] == 10 - (sum % 10)) return true;
        }

        return false;
    }

    //source http://stackoverflow.com/questions/17108621/converting-isbn10-to-isbn13
    public static String convertISBN10toISBN13( String ISBN10 ) {
        String ISBN13  = ISBN10;
        ISBN13 = "978" + ISBN13.substring(0,9);
        //if (LOG_D) Log.d(TAG, "ISBN13 without sum" + ISBN13);
        int d;

        int sum = 0;
        for (int i = 0; i < ISBN13.length(); i++) {
            d = ((i % 2 == 0) ? 1 : 3);
            sum += ((((int) ISBN13.charAt(i)) - 48) * d);
            //if (LOG_D) Log.d(TAG, "adding " + ISBN13.charAt(i) + "x" + d + "=" + ((((int) ISBN13.charAt(i)) - 48) * d));
        }
        sum = 10 - (sum % 10);
        ISBN13 += sum;

        return ISBN13;
    }


    //source http://weblogs.asp.net/fmarguerie/isbn-13-to-isbn-10
    public static String Isbn13to10(String isbn13)
    {
        if (isbn13!=null || isbn13.length()==0)
            throw new IllegalArgumentException("isbn13");
        isbn13 = isbn13.replace("-", "").replace(" ", "");
        if (isbn13.length() != 13)
            throw new IllegalArgumentException("The ISBN doesn't contain 13 characters.");

        String isbn10 = isbn13.substring(3, 9);
        int checksum = 0;
        int weight = 10;

        for (char c:isbn10.toCharArray())
        {
            checksum += Integer.getInteger(String.valueOf(c)) * weight;
            weight--;
        }

        checksum = 11-(checksum % 11);
        if (checksum == 10)
            isbn10 += "X";
        else if (checksum == 11)
            isbn10 += "0";
        else
            isbn10 += checksum;

        return isbn10;
    }


}
