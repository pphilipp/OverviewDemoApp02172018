package test.example.com.presentation.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import test.example.com.presentation.utils.Preconditions;

import static org.hamcrest.CoreMatchers.is;

public class PreconditionsTests {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test public void testCheckNotNullOnNullFails() throws Exception {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(is("'sword' should not be null"));

        Preconditions.checkNotNull(null, "sword");
    }

    @Test public void testCheckNotNullOnNotNullCorrect() throws Exception {
        Preconditions.checkNotNull("my", "sword");
    }

    @Test public void testCheckNotNullOnNullMessageFails() throws Exception {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(is("'null' should not be null"));

        Preconditions.checkNotNull(null, null);
    }
}

