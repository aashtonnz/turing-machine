import java.util.*;
import java.io.*;

/**
 * Simulates a Turing machine in the terminal. The first argument is a txt file
 * specifying the intial state, halting states, and state table. The second
 * argument specifies the initial symbols on the tape. A third optional argument
 * may specify the time interval between changes to the tape.
 *
 * @author Andrew Ashton
 */
public class TuringMachine {

    /*
     * Handles the command line arguments and then runs the Turing machine.
     *
     * @param args txt file, initial symbols, and time interval (optional)
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length > 1) {
            try {
                Scanner input = new Scanner(new FileReader(args[0]));
                // default interval is 200 ms
                int interval = 200;

                if (args.length > 1) {
                    interval = Math.abs(Integer.parseInt(args[2]));
                }
                // the initial state
                char state = input.nextLine().charAt(0);

                ArrayList<Character> tape = new ArrayList<Character>();
                // tape must have a blank leftmost cell
                tape.add('_');

                for (int i = 0; i < args[2].length(); i++) {
                    tape.add(args[2].charAt(i));
                }
                ArrayList<Character> halts = new ArrayList<Character>();
                line = input.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    halts.add(line.charAt(i));
                }
                ArrayList<char[]> table = new ArrayList<char[]>();

                while (input.hasNextLine()) {
                    line = input.nextLine();
                    for (int i = 0; i < line.length(); i++) {
                        table.add(new char[]{line.charAt(0), line.charAt(1),
                                             line.charAt(2), line.charAt(3),
                                             line.charAt(4)});
                    }
                }
                run(state, table, halts, tape, interval);

            } catch (FileNotFoundException|NoSuchElementException|
                     IllegalStateException|NumberFormatException e) {
                System.err.println(e);
            }
        }
    }

    /*
     * Run the Turing machine simulation with the given initial state, table,
     * halting states, and tape.
     *
     * @param state the initial state
     * @param table the state table
     * @param halts the halting states
     * @param tape the tape
     */
    private static void run(char state, ArrayList<char[]> table,
                            ArrayList<Character> halts,
                            ArrayList<Character> tape, int interval) throws
                                InterruptedException {
        // pointer starts in second cell
        int pointer = 1;

        // ensure the tape has a second cell
        if (pointer == tape.size()) {
            tape.add('_');
        }
        print(tape, pointer);

        while (!halts.contains(state)) {
            for (char[] row : table) {
                if (row[0] == state && row[1] == tape.get(pointer)) {
                    // write the symbol
                    tape.set(pointer, row[2]);

                    Thread.sleep(interval);
                    print(tape, pointer);

                    // move pointer right or left
                    pointer += row[3] == 'r' ? 1 : -1;

                    // extend tape if pointer moves off it
                    if (pointer == tape.size()) {
                        tape.add('_');
                    }
                    Thread.sleep(interval);
                    print(tape, pointer);

                    state = row[4];
                    break;
                }
            }
        }
        Thread.sleep(interval);
        System.out.println();
    }

    /*
     * Prints the current symbols on the tape and the position of the pointer.
     */
    private static void print(ArrayList<Character> tape, int pointer) {
        // perform a carriage return to print on the same line
        System.out.print("\r ");

        for (int i = 0; i < tape.size(); i++) {
            System.out.print(pointer == i ? "\b(" + tape.get(i) + ")"
                              : tape.get(i) + " ");
        }
    }

}
