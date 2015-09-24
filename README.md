# turing-machine
*A Java application for simulating a Turing machine in the terminal.*

### Overview

Usage: `java TuringMachine table tape [interval]`

* `table` is a txt file specifying the intial state, halting states, and state table. States should be specified as characters. A character on the first line specifies the inital state and characters on the second line specify the halting states. Each following line corresponds to a row in the state table, the current state, read symbol,symbol to write, read/write head movement (L or R), and new state.
* `tape` specifies the initial symbols on the tape. This is given as a string of characters, each character representing a symbol on the tape.
* `interval` may be used to specify the time interval (in milliseconds) between changes to the tape.

A Turing machine is simulated by performing carriage returns in the terminal to print any movements of the read/write head and changes to the tape. The tape is extended as necessary, with blank cells represented by _.

### palindrome.txt

The state table for checking if a binary string is a palindrome or not. `true` is printed on the tape if it is a palindrome and `false` is printed if it is not.
