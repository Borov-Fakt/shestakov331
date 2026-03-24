import org.example.calculator.CalculatorService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    CalculatorService service = new CalculatorService();

    @Test
    void testOperations() {
        assertEquals(10, service.add(6, 4));
        assertEquals(5, service.subtract(10, 5));
        assertEquals(12, service.multiply(3, 4));
        assertEquals(2, service.divide(10, 5));
    }

    @Test
    void testAdvanced() {
        assertEquals(8, service.power(2, 3));
        assertEquals(5, service.sqrt(25));
        assertEquals(120, service.factorial(5));
    }

    @Test
    void testMemory() {
        service.memSave(50);
        service.memAdd(10);
        assertEquals(60, service.memRead());
        service.memClear();
        assertEquals(0, service.memRead());
    }

    @Test
    void testErrors() {
        assertThrows(ArithmeticException.class, () -> service.divide(10, 0));
        assertThrows(ArithmeticException.class, () -> service.sqrt(-1));
    }
}
