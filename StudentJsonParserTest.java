import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StudentJsonParserTest {

    @Test
    public void shouldTakeUserInput() {
        StudentJsonParser studentJsonParser = new StudentJsonParser();

        String input = "{\"report\":[{\"enrollment\":\"rit2011001\",\"name\":\"Julia\",\"subject\":[{\"code\":\"DSA\",\"grade\":\"A\"}]},{\"enrollment\":\"rit2011020\",\"name\":\"Samantha\",\"subject\":[{\"code\":\"COM\",\"grade\":\"B\"},{\"code\":\"DSA\",\"grade\":\"A\"}]}]}";
        String output = "COM B rit2011020 Samantha\nDSA A rit2011001 Julia\nDSA A rit2011020 Samantha";
        assertEquals(output, studentJsonParser.generateSortedReport(input));
    }

    @Test
    public void shouldTakeUserInputWithFGrade() {
        StudentJsonParser studentJsonParser = new StudentJsonParser();

        String input = "{\"report\":[{\"enrollment\":\"rit2011001\",\"name\":\"Julia\",\"subject\":[{\"code\":\"DSA\",\"grade\":\"A\"},{\"code\":\"COM\",\"grade\":\"F\"}]}," +
                "{\"enrollment\":\"rit2011020\",\"name\":\"Samantha\",\"subject\":[{\"code\":\"COM\",\"grade\":\"B\"},{\"code\":\"DSA\",\"grade\":\"A\"}]}]}";
        String output = "COM B rit2011020 Samantha\nDSA A rit2011001 Julia\nDSA A rit2011020 Samantha";
        assertEquals(output, studentJsonParser.generateSortedReport(input));
    }

    @Test
    public void shouldTakeUserInputWithSameGrade() {
        StudentJsonParser studentJsonParser = new StudentJsonParser();

        String input = "{\"report\":[{\"enrollment\":\"rit2011001\",\"name\":\"Julia\",\"subject\":[{\"code\":\"DSA\",\"grade\":\"A\"},{\"code\":\"COM\",\"grade\":\"B\"}]}," +
                "{\"enrollment\":\"rit2011020\",\"name\":\"Samantha\",\"subject\":[{\"code\":\"COM\",\"grade\":\"B\"},{\"code\":\"DSA\",\"grade\":\"A\"}]}]}";
        String output = "COM B rit2011001 Julia\nCOM B rit2011020 Samantha\nDSA A rit2011001 Julia\nDSA A rit2011020 Samantha";
        assertEquals(output, studentJsonParser.generateSortedReport(input));
    }

    @Test
    public void shouldTakeUserInputWithOutPassed() {
        StudentJsonParser studentJsonParser = new StudentJsonParser();

        String input = "{\"report\":[{\"enrollment\":\"rit2011001\",\"name\":\"Julia\",\"subject\":[{\"code\":\"DSA\",\"grade\":\"F\"},{\"code\":\"COM\",\"grade\":\"F\"}]}," +
                "{\"enrollment\":\"rit2011020\",\"name\":\"Samantha\",\"subject\":[{\"code\":\"COM\",\"grade\":\"F\"},{\"code\":\"DSA\",\"grade\":\"F\"}]}]}";
        String output = "";
        assertEquals(output, studentJsonParser.generateSortedReport(input));
    }
}
