public class Day9Tasks {

    public static void main(String[] args) {

        String fileName = "src\\main\\resources\\Day9.txt";

        ElfComputer boostComputer1 = new ElfComputer(fileName);
        String input = "1";

        boostComputer1.setUpInput(input);
        for (long out : boostComputer1.executeProgram()) {
            System.out.println("PO: " + out);
        }

    }




}
