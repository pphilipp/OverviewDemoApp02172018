package test.example.com.domain.usecase;

import com.amatkivskiy.result.Result;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import test.example.com.domain.executor.PostExecutionThread;
import test.example.com.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import test.example.com.domain.usecase.UseCase;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTests {

    private TestUseCase useCase;
    TestScheduler testScheduler;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Before public void setUp() {
        testScheduler = new TestScheduler();
        MockitoAnnotations.initMocks(this);

        // Setup schedulers to run in the same thread where tests are run.
        doAnswer(new Answer<Void>() {
            @Override public Void answer(InvocationOnMock invocation) throws Throwable {
                Runnable arg = invocation.getArgument(0);
                testScheduler.scheduleDirect(arg);
                return null;
            }
        }).when(mockThreadExecutor)
                .execute(any(Runnable.class));
        given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.useCase = new TestUseCase(mockThreadExecutor, mockPostExecutionThread);
    }

    @Test public void testGetRawObservableReturnCorrectResult() {
        useCase.getRawObservable()
                .test()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete();
    }

    @Test public void testConfiguredObservableReturnCorrectResult() throws InterruptedException {
        TestObserver<Result<Object, Exception>> observer = useCase.getConfiguredObservable()
                .test();

        // Trigger all scheduled actions.
        testScheduler.triggerActions();

        observer.await()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete();

        // Verify that executors threads were used  when configuring observable schedulers
        verify(mockPostExecutionThread, times(1)).getScheduler();
        verify(mockThreadExecutor, times(1)).execute(any(Runnable.class));
    }

    @Test public void testNullThreadExecutorFails() throws InterruptedException {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(is("'threadExecutor' should not be null"));

        new TestUseCase(null, mockPostExecutionThread).getConfiguredObservable();
    }

    @Test public void testNullPostThreadExecutorFails() throws InterruptedException {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(is("'postExecutionThread' should not be null"));

        new TestUseCase(mockThreadExecutor, null).getConfiguredObservable();
    }

    private static class TestUseCase extends UseCase<Object, Exception> {
        TestUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override public Observable<Result<Object, Exception>> getRawObservable() {
            return Observable.just(Result.<Object, Exception>success(new Object()));
        }
    }
}
