package seedu.jabot.util;
import org.junit.jupiter.api.Test;
import seedu.jabot.exceptions.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    @Test
    public void convertDate_testReturnValue() throws DukeException {
        //check converting string to localDate in ddmmyyyy
        assertNotNull(new Parser().convertDate("20112023"));
        //check converting string to localDate in dd-mm-yyyy
        assertNotNull(new Parser().convertDate("20-11-2023"));
        //check converting string to localDate in dd/mm/yyyy
        assertNotNull(new Parser().convertDate("20/11/2023"));
    }

    @Test
    public void convertDate_testThrowException(){
        try{
            //inputting random number in date format and test if exception is thrown
            new Parser().convertDate("50789999");
        } catch(DukeException e){
            assertEquals("Invalid date format! Please input date-time in the format of dd/MM/yyyy (24hr format)", e.getMessage());
        }
    }

}
