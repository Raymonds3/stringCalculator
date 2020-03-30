import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

public class StringCalculatorTest {
    @Test
    public void parameterEmptyZero() throws Exception {
        Assert.assertThat(StringCalculator.add(""), is(0));
    }
    @Test
    public void parameterWithOneOrTweNumbers() throws Exception {
        Assert.assertThat(StringCalculator.add("1"), is(1));
        Assert.assertThat(StringCalculator.add("1,1"), is(2));
    }
    @Test
    public void parameterWithComa() throws Exception {
        Assert.assertThat(StringCalculator.add("1,2,3,4"), is(10));
    }
    @Test
    public void parameterWithComaOrNewLine() throws Exception {
        Assert.assertThat(StringCalculator.add("1\n2,3"), is(6));
    }
    @Test
    public void parameterWithDelimiters() throws Exception {
        Assert.assertThat(StringCalculator.add("//;\n1;2"), is(3));
        Assert.assertThat(StringCalculator.add("//4\n142"), is(3));
    }
    @Test(expected = Exception.class)
    public void handleNegativeIntegers() throws Exception{
        String negative = "ERROR: negatives not allowed [-1, -2]";
        Assert.assertThat(StringCalculator.add("-1,-2,3,4"), is(negative));
    }
    @Test
    public void ignoreIntegerGreaterThan1000() throws Exception {
        Assert.assertThat(StringCalculator.add("//;\n1000,1;2"), is(3));
    }
    @Test
    public void delimitersOfAnyLength() throws Exception{
        Assert.assertThat(StringCalculator.add("//***\n1***2***3"), is(6));
    }
    @Test
    public void supportDifferentDelimiters() throws Exception{
        Assert.assertThat(StringCalculator.add("//[:D][%]\n1:D2%3"), is(6));
        Assert.assertThat(StringCalculator.add("//[***][%%%]\n1***2%%%3"), is(6));
        Assert.assertThat(StringCalculator.add("//[(-_-')][%]\n1(-_-')2%3"), is(6));
        Assert.assertThat(StringCalculator.add("//[abc][777][:(]\n1abc27773:(1"), is(7));
    }
}
