package processor;

import com.smartservice.nomina.processor.concept.impl.SalaryConcept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.testng.AssertJUnit.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SalaryConceptTest {



    @Test
    public void processTest(){
        SalaryConcept salaryConcept = new SalaryConcept(8000000,15);
        long salary = salaryConcept.process();
        assertEquals(salary,4000000);
    }

    @Test
    public void integerTest(){
        int num = 1234567;
        int[]digits = Integer.toString(num).chars().map(c -> c-='0').toArray();
            System.out.print(digits.toString());
    }

}
