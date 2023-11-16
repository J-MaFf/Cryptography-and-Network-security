import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class passwordCracker {
    public static void main(String[] args) {
        String target1hash = "7b0ca5c95a9398a2f32613d987428180";

        ArrayList<String> possiblePasswords1 = new ArrayList<String>() {
            {
                add("19891989");
                add("1985Bob");
                add("JaneBob");
                add("BobOrange");
                add("CharlieBob");
                add("Alice1989");
                add("BobCharlie");
                add("1985Orange");
                add("AliceBob");
                add("Jane1989");
                add("BobBob");
                add("19851985");
                add("BobSeattle");
                add("1985Jane");
                add("Jane1985");
                add("AliceJane");
                add("1989Jane");
                add("JaneJane");
                add("AliceAlice");
                add("Orange1989");
                add("1985Alice");
                add("1989Alice");
                add("Orange1985");
                add("Bob1989");
                add("BobMadison");
                add("SeattleBob");
                add("Alice1985");
                add("19891985");
                add("OrangeBob");
                add("JaneAlice");
                add("BobAlice");
                add("JaneOrange");
                add("OrangeJane");
                add("MadisonBob");
                add("BobJane");
                add("19851989");
                add("1989Bob");
                add("1989Orange");
                add("Bob1985");
                add("Orange85");
                add("85Orange");
                add("JaneBob");
                add("89Seattle");
                add("BobOrange");
                add("CharlieBob");
                add("85Bob");
                add("Charlie89");
                add("BobCharlie");
                add("85Jane");
                add("85Madison");
                add("85Alice");
                add("AliceBob");
                add("89Charlie");
                add("89Bob");
                add("BobBob");
                add("BobSeattle");
                add("AliceJane");
                add("Jane89");
                add("Bob85");
                add("Madison85");
                add("Seattle85");
                add("JaneJane");
                add("AliceAlice");
                add("89Jane");
                add("Charlie85");
                add("85Seattle");
                add("Bob89");
                add("BobMadison");
                add("SeattleBob");
                add("Alice85");
                add("89Alice");
                add("OrangeBob");
                add("85Charlie");
                add("JaneAlice");
                add("BobAlice");
                add("Madison89");
                add("Seattle89");
                add("Jane85");
                add("JaneOrange");
                add("OrangeJane");
                add("89Madison");
                add("CharlieJane");
                add("MadisonBob");
            }
        };

        String password1 = new passwordCracker().crackPassword(target1hash, possiblePasswords1);

        System.out.println("The password is: " + password1 + "\n\n");

        String target2hash = "7e985df169d043112b23508a81e16538";

        ArrayList<String> personalData = new ArrayList<String>() {
            {
                add("Name: Dr. Alice Pearson");
                add("Date of Birth: November 11, 1983");
                add("Born: Fort Atkinson, WI");
                add("Residence: Whitewater, WI");
                add("Employer: University of Wisconsin- Whitewater");
                add("Occupation: Cybersecurity Instructor & Volleyball Coach");
                add("Favorite Color: Purple");
                add("Publicly-known information from the university newsletter: Travels frequently for cybersecurity conferences and volleyball tournaments");
                add("Very involved with the university recruitment committee");
                add("Publicly-visible social media feed includes several examples of: Recreational fishing in Lake Michigan and the Mississippi River");
                add("Reunion events with former students");
                add("Anything related to The Beatles");
            }
        };
        // List<String> possiblePasswords2 = generatePossiblePasswords(personalData); //
        // dosent work
        ArrayList<String> possiblePasswords2 = new ArrayList<String>() {
            {
                add("AlicePearson");
                add("AliceBeatles");
                add("AliceWhitewater");
                add("1983Whitewater");
                add("PearsonAlice");
                add("PearsonPurple");
                add("PearsonVolley");
                add("PearsonBeatles");
                add("PearsonCyber");
                add("PearsonWhitewater");
                add("PurplePearson");
                add("PurpleVolley");
                add("PurpleBeatles");
                add("PurpleWhitewater");
                add("VolleyPearson");
                add("VolleyPurple");
                add("VolleyBeatles");
                add("VolleyWhitewater");
                add("FishWhitewater");
                add("BeatlesAlice");
                add("BeatlesPearson");
                add("BeatlesPurple");
                add("BeatlesVolley");
                add("BeatlesCyber");
                add("BeatlesWhitewater");
                add("UWWWhitewater");
                add("CyberPearson");
                add("CyberBeatles");
                add("CyberWhitewater");
                add("WhitewaterAlice");
                add("Whitewater1983");
                add("WhitewaterPearson");
                add("WhitewaterPurple");
                add("WhitewaterVolley");
                add("WhitewaterFish");
                add("WhitewaterBeatles");
                add("WhitewaterUWW");
                add("WhitewaterCyber");
                add("Whitewater11");
                add("Whitewater83");
                add("11Whitewater");
                add("83Whitewater");
            }
        };
        String password2 = new passwordCracker().crackPassword(target2hash, possiblePasswords2);

        System.out.println("The password is: " + password2 + "\n\n");

    }

    public String crackPassword(String targetHash) { // Brute force method
        RainbowTable[] tables = new RainbowTable[6]; // Array of rainbow tables

        for (int i = 0; i < tables.length; i++) { // Initialize rainbow tables
            tables[i] = new RainbowTable(i + 5);
        }

        for (int i = 0; i < tables.length; i++) { // Iterate through rainbow tables
            for (String key : tables[i].getTable().keySet()) { // Iterate through keys in rainbow table
                if (tables[i].getTable().get(key).equals(targetHash)) { // Check if hash matches target hash
                    return key; // Return password
                }
            }
        }
        return "Password not found.";
    }

    public String crackPassword(String targetHash, List<String> possiblePasswords) { // Rainbow table method
        RainbowTable table = new RainbowTable(possiblePasswords); // Initialize rainbow table
        table.print(); // Print rainbow table

        for (String key : table.getTable().keySet()) { // Iterate through keys in rainbow table
            if (table.getTable().get(key).equals(targetHash)) { // Check if hash matches target hash
                return key; // Return password
            }
        }
        return "Password not found. Here are the passwords that were tried:\n" + possiblePasswords.toString() + "\n";
    }

    public static List<String> generatePossiblePasswords(List<String> personalData) {
        List<String> possiblePasswords = new ArrayList<>();
        // RainbowTable table = new RainbowTable(20);
        // table.print();

        for (String data : personalData) {
            // Check if the data length is within the old password constraints
            if (data.length() >= 5 && data.length() <= 10 && data.matches("[a-zA-Z0-9]+")) {
                // Generate extensions of the old password to meet the new constraints
                for (int i = 12; i <= 20; i++) {
                    StringBuilder extendedPassword = new StringBuilder(data);
                    while (extendedPassword.length() < i) {
                        extendedPassword.append(data);
                    }
                    possiblePasswords.add(extendedPassword.toString());
                }
            }
        }

        return possiblePasswords;
    }

}
