//TODO create file comment
package com.chaoticcognitions.aenigma.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Arrays;

/**
 * TODO finish class comment
 *
 * @author Dan Cassidy
 */
public class Plugboard implements Parcelable {
    private static final char NULL_CHAR = '\0';
    private static final char CHAR_OFFSET = 'A';

    private int numPairs = 0;
    private char[] plugSettings = new char[26];

    //region Constructors.
    //TODO create method comment
    public Plugboard() {
        // Nothing to do.
    }

    //TODO create method comment
    public Plugboard(Plugboard plugboard) {
        this.numPairs = plugboard.numPairs;
        System.arraycopy(plugboard.plugSettings, 0, plugSettings, 0, plugSettings.length);
    }
    //endregion Constructors.

    //TODO create method comment
    public void addPlugPair(char char1, char char2) {
        // Gateway checks.
        if (char1 < 'A' || char1 > 'Z' || char2 < 'A' || char2 > 'Z' || char1 == char2)
            // At least one of the arguments is not a valid (between 'A' and 'Z') character, or the
            // arguments are equal.
            throw new IllegalArgumentException("Invalid characters for plugboard pair.");
        else if (plugSettings[char1 - CHAR_OFFSET] != NULL_CHAR || plugSettings[char2 - CHAR_OFFSET]
                != NULL_CHAR)
            // At least one of the two characters are paired already.
            return;

        plugSettings[char1 - 'A'] = char2;
        plugSettings[char2 - 'A'] = char1;
        numPairs++;
    }

    //TODO create method comment
    public String getPlugPairDump() {
        String toReturn = "";
        for (int index = 0; index < plugSettings.length; index++)
            if (plugSettings[index] != NULL_CHAR && toReturn.indexOf(plugSettings[index]) == -1)
                toReturn += (toReturn.isEmpty() ? "" : " ") + ((char) (index + CHAR_OFFSET)) + plugSettings[index];
        return toReturn;
    }

    //TODO create method comment
    public char encode(char inputChar) {
        Log.i("Plugboard", "Plugboard encoding '" + inputChar + "' to '" + (plugSettings[inputChar
                - CHAR_OFFSET] == NULL_CHAR ? inputChar : plugSettings[inputChar - CHAR_OFFSET])
                + "'");
        return (plugSettings[inputChar - CHAR_OFFSET] == NULL_CHAR
                ? inputChar
                : plugSettings[inputChar - CHAR_OFFSET]);
    }

    //region Parcelable Implementation
    // This region implements the methods for the Parcelable interface so this object can be passed
    // between activities.
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(numPairs);
        out.writeCharArray(plugSettings);
    }

    public static final Parcelable.Creator<Plugboard> CREATOR
            = new Parcelable.Creator<Plugboard>() {
        public Plugboard createFromParcel(Parcel in) {
            return new Plugboard(in);
        }

        public Plugboard[] newArray(int size) {
            return new Plugboard[size];
        }
    };

    private Plugboard(Parcel in) {
        numPairs = in.readInt();
        in.readCharArray(plugSettings);
    }
    //endregion Parcelable Implementation
}
