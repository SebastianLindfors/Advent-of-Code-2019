import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElfComputerTest {

    @Test
    public void basicFunctionalityTesting1() {

        long[] program = {1,0,0,0,99};
        long[] expected = {2,0,0,0,99};
        ElfComputer testComputer = new ElfComputer(program);

        testComputer.executeProgram();

        assertArrayEquals(expected, testComputer.getProgramMemory());

    }

    @Test
    public void basicFunctionalityTesting2() {

        long[] program = {2,3,0,3,99};
        long[] expected = {2,3,0,6,99};
        ElfComputer testComputer = new ElfComputer(program);

        testComputer.executeProgram();

        assertArrayEquals(expected, testComputer.getProgramMemory());

    }

    @Test
    public void basicFunctionalityTesting3() {

        long[] program = {2,4,4,5,99,0};
        long[] expected = {2,4,4,5,99,9801};
        ElfComputer testComputer = new ElfComputer(program);

        testComputer.executeProgram();

        assertArrayEquals(expected, testComputer.getProgramMemory());

    }

    @Test
    public void basicFunctionalityTesting4() {

        long[] program = {1,1,1,4,99,5,6,0,99};
        long[] expected = {30,1,1,4,2,5,6,0,99};
        ElfComputer testComputer = new ElfComputer(program);

        testComputer.executeProgram();

        assertArrayEquals(expected, testComputer.getProgramMemory());
    }

    @Test
    public void InputHandlingTest1_expects1() {

        long[] program = {3,9,8,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("8");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void InputHandlingTest1_expects0() {

        long[] program = {3,9,8,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void InputHandlingTest2_expects1() {

        long[] program = {3,9,7,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void InputHandlingTest2_expects0() {

        long[] program = {3,9,7,9,10,9,4,9,99,-1,8};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("10");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void PositionModeTest1_expects1() {

        long[] program = {3,3,1108,-1,8,3,4,3,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("8");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void PositionModeTest1_expects0() {

        long[] program = {3,3,1108,-1,8,3,4,3,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void PositionModeTest2_expects1() {

        long[] program = {3,3,1107,-1,8,3,4,3,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void PositionModeTest2_expects0() {

        long[] program = {3,3,1107,-1,8,3,4,3,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("10");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void jumpPositionModeTest_expects1() {

        long[] program = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void jumpPositionModeTest_expects0() {

        long[] program = {3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("0");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void jumpImmediateModeTest_expects1() {

        long[] program = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("1");

        List<Long> expected = new ArrayList<>();
        expected.add(1L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void jumpImmediateModeTest_expects0() {

        long[] program = {3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("0");

        List<Long> expected = new ArrayList<>();
        expected.add(0L);

        assertEquals(expected, testComputer.executeProgram());

    }


    @Test
    public void relativeModeTest1_expectsCopyOfInputProgram() {

        long[] program = {109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99};
        ElfComputer testComputer = new ElfComputer(program);

        List<Long> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(109L, 1L, 204L, -1L, 1001L, 100L, 1L, 100L, 1008L, 100L, 16L, 101L, 1006L, 101L, 0L, 99L));

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void longTest1_expects() {
        long[] program = {1102,34915192,34915192,7,4,7,99,0};
        ElfComputer testComputer = new ElfComputer(program);

        List<Long> expected = new ArrayList<>();
        expected.add(34915192L * 34915192L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void longTest2_expects() {
        long[] program = {104,1125899906842624L,99};
        ElfComputer testComputer = new ElfComputer(program);

        List<Long> expected = new ArrayList<>();
        expected.add(1125899906842624L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void largeTest1_expects999() {

        long[] program = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("0");

        List<Long> expected = new ArrayList<>();
        expected.add(999L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void largeTest1_expects1000() {

        long[] program = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("8");

        List<Long> expected = new ArrayList<>();
        expected.add(1000L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void largeTest1_expects1001() {

        long[] program = {3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        ElfComputer testComputer = new ElfComputer(program);
        testComputer.setUpInput("15");

        List<Long> expected = new ArrayList<>();
        expected.add(1001L);

        assertEquals(expected, testComputer.executeProgram());
    }

    @Test
    public void solveDay2Part1Test_expects9706670() {

        String fileName = "src\\test\\resources\\Day2.txt";
        ElfComputer testComputer = new ElfComputer(fileName);
        testComputer.directMemoryEdit(1,12);
        testComputer.directMemoryEdit(2,2);

        testComputer.executeProgram();
        long[] extractedMemory = testComputer.getProgramMemory();
        long expected = 9706670;

        assertEquals(expected, extractedMemory[0]);

    }

    @Test
    public void solveDay2Part2Test_expects2552() {

        String fileName = "src\\test\\resources\\Day2.txt";
        ElfComputer testComputer = new ElfComputer(fileName);

        long endCondition = 19690720;
        boolean breakCondition = false;

        int noun = 0, verb = 0;
        for (noun = 0; noun < 100; noun++) {
            for (verb = 0; verb < 100; verb++) {
                testComputer.directMemoryEdit(1,noun);
                testComputer.directMemoryEdit(2,verb);

                testComputer.executeProgram();
                long[] extractedMemory = testComputer.getProgramMemory();
                if (extractedMemory[0] == endCondition) {
                    breakCondition = true;
                    break;
                }
                testComputer.resetElfComputer();

            }
            if (breakCondition) {
                break;
            }
        }

        long expected = 2552L;

        assertEquals(expected, ((noun*100) + verb));

    }

    @Test
    public void solveDay5Part1Test_expects5044655() {

        String fileName = "src\\test\\resources\\Day5.txt";
        ElfComputer testComputer = new ElfComputer(fileName);
        testComputer.setUpInput("1");


        List<Long> expected = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            expected.add(0L);
        }
        expected.add(5044655L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void solveDay5Part2Test_expects7408802() {

        String fileName = "src\\test\\resources\\Day5.txt";
        ElfComputer testComputer = new ElfComputer(fileName);
        testComputer.setUpInput("5");


        List<Long> expected = new ArrayList<>();
        expected.add(7408802L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void solveDay9Part1Test_expects3533056970() {

        String fileName = "src\\test\\resources\\Day9.txt";
        ElfComputer testComputer = new ElfComputer(fileName);
        testComputer.setUpInput("1");


        List<Long> expected = new ArrayList<>();
        expected.add(3533056970L);

        assertEquals(expected, testComputer.executeProgram());

    }

    @Test
    public void solveDay9Part2Test_expects72852() {

        String fileName = "src\\test\\resources\\Day9.txt";
        ElfComputer testComputer = new ElfComputer(fileName);
        testComputer.setUpInput("2");


        List<Long> expected = new ArrayList<>();
        expected.add(72852L);

        assertEquals(expected, testComputer.executeProgram());

    }










}