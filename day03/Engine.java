import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Engine {
    Vector<String> schematic = new Vector<String>();

    Byte[][] mask = {{-1,-1},{0,-1},{1,-1},{-1,0},{0,0},{1,0},{-1,1},{0,1},{1,1}};
    public Engine(File input) {
        try {
            Scanner fin = new Scanner(input);
            // Load each line of file into vector
            while (fin.hasNextLine()) {
                schematic.add(fin.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        process();
    }
    private Vector<Integer> findNumbers(int x, int y){
        Vector<Integer> found = new Vector<Integer>();
        for(int i = 0; i < 9; i++){
            Byte[] offset = mask[i];
            try{
                String currentLine = schematic.get(y+offset[1]);
                char currentCharacter = currentLine.charAt(x+offset[0]);

                // define starting position in row
                int position = x+offset[0];
                // default values are illegal
                int beginning = -1;
                int end = -1;

                // this could be better:
                // find first index of number
                for(int j = position; j>=0; j--){
                    char temp = currentLine.charAt(j);
                    if(temp >= 48 && temp <=57) beginning = j;
                    else break;
                }
                // find last index of number
                for(int j = position; j<currentLine.length();j++){
                    char temp = currentLine.charAt(j);
                    if(temp >= 48 && temp <=57) end = j;
                    else break;
                }

                // extract number using beginning and end indices
                String number = currentLine.substring(beginning,end+1);
                int tonari = Integer.parseInt(number);

                /* prepare replacement string to prevent multiple "discoveries" of the same number */
                String replacement = "";
                for(int j = 0; j<currentLine.length();j++){
                    if(j>=beginning && j<=end){
                        // replace found number with ignored '.' characters
                        replacement+='.';
                    } else {
                        // fill in remainder
                        replacement+=currentLine.charAt(j);
                    }
                }
                /* Replace row of schematic & add to found list*/
                schematic.set(y+offset[1], replacement);

                //for(int j = 0; j<number.length();j++)
                //    replacement+=".";
                //schematic.set(y+offset[1],currentLine.replaceFirst(number, replacement));

                found.add(tonari);
            } catch (Exception e){}
        }
        return found;
    }

    /*
    555197 too low
    552391 fuck
    556057 correct

    I think the `replaceFirst` was messing up bigger numbers that showed up earlier in the line.
    as an example it'd replace the first '9' with a '.' of an earlier appearing 999 rather than
    the later individual 9.
    ................*....
    ...999..........9....
    ......*..............
    becomes
    ................*....
    ....99..........9....
    ......*..............
    and 99 is read instead of 999 for the * on row 3 */

    public void process(){
        int out = 0;
        int gear = 0;
        for(int i = 0; i < schematic.size(); i++) {
            for (int j = 0; j < schematic.get(i).length(); j++){
                char c = schematic.get(i).charAt(j);
                // is current character a symbol?
                if ((c < 0x30 || c > 0x39) && c != 0x2e) {
                    Vector<Integer> tonari = findNumbers(j, i);
                    for(int v : tonari) out+=v;

                    // Finding gear sum is simple as:
                    if(c == 0x2a){
                        if(tonari.size() == 2) gear+=(tonari.get(0)*tonari.get(1));
                    }
                }
            }
        }
        System.out.println("PART SUM: "+ out);
        System.out.println("GEAR SUM: "+ gear);
    }
}
