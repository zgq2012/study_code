package learn.leecode.arr;

import java.util.Comparator;

/**
 * TODO
 *
 * @author zgq
 * @version v4.2.0
 * @since 2021/12/1
 **/
public class ChineseSortCp implements Comparator<char[]> {

    static class Int {
        public int i;
    }

    public int findDigitEnd(char[] arrChar, Int at) {
        int k = at.i;
        char c = arrChar[k];
        boolean bFirstZero = (c == '0');
        while (k < arrChar.length) {
            c = arrChar[k];

            if (c > '9' || c < '0') {
                break;
            } else if (bFirstZero && c == '0') {
                at.i++;
            }
            k++;
        }
        return k;
    }

    @Override
    public int compare(char[] a, char[] b) {
        if (a != null || b != null) {
            Int aNonzeroIndex = new Int();
            Int bNonzeroIndex = new Int();
            int aIndex = 0, bIndex = 0, aComparedUnitTailIndex, bComparedUnitTailIndex;

            while (aIndex < a.length && bIndex < b.length) {
                aNonzeroIndex.i = aIndex;
                bNonzeroIndex.i = bIndex;
                aComparedUnitTailIndex = findDigitEnd(a, aNonzeroIndex);
                bComparedUnitTailIndex = findDigitEnd(b, bNonzeroIndex);

                if (aComparedUnitTailIndex > aIndex && bComparedUnitTailIndex > bIndex) {
                    int aDigitIndex = aNonzeroIndex.i;
                    int bDigitIndex = bNonzeroIndex.i;
                    int aDigit = aComparedUnitTailIndex - aDigitIndex;
                    int bDigit = bComparedUnitTailIndex - bDigitIndex;

                    if (aDigit != bDigit) {
                        return aDigit - bDigit;
                    }

                    while (aDigitIndex < aComparedUnitTailIndex) {
                        if (a[aDigitIndex] != b[bDigitIndex]) {
                            return a[aDigitIndex] - b[bDigitIndex];
                        }
                        aDigitIndex++;
                        bDigitIndex++;
                    }

                    aDigit = aNonzeroIndex.i - aIndex;
                    bDigit = bNonzeroIndex.i - bIndex;
                    if (aDigit != bDigit) {
                        return aDigit - bDigit;
                    }
                    aIndex = aComparedUnitTailIndex;
                    bIndex = bComparedUnitTailIndex;
                } else {
                    if (a[aIndex] != b[bIndex]) {
                        return a[aIndex] - b[bIndex];
                    }
                    aIndex++;
                    bIndex++;
                }

            }

        }
        return a.length - b.length;
    }
}
