/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package philippbugayevskiy.example.com.domain.usecase;

import com.amatkivskiy.result.Result;
import com.fernandocejas.arrow.checks.Preconditions;
import philippbugayevskiy.example.com.domain.executor.PostExecutionThread;
import philippbugayevskiy.example.com.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * @param <T> type of the returned successful result.
 * @param <E> type of the returned error result.
 */
public abstract class UseCase<T, E> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * {@link UseCase}'s business logic should be implemented here. That is what this {@link
     * UseCase} is designed for.
     *
     * For example: perform login or get available VODs.
     * <p>
     * This method should be called only if you need {@link Observable} without configured {@link
     * Schedulers} (Useful when building chains of {@link Observable}s).
     *
     * @return {@link Observable} which encapsulates business logic.
     */
    public abstract Observable<Result<T, E>> getRawObservable();

    /**
     * Returns {@link UseCase#getRawObservable()} but with configured {@link Schedulers}.
     *
     * @return {@link Observable} with configured {@link Observable#subscribeOn(Scheduler)} and {@link
     * Observable#observeOn(Scheduler)}.
     */
    public Observable<Result<T, E>> getConfiguredObservable() {
        Preconditions.checkNotNull(threadExecutor, "'threadExecutor' should not be null");
        Preconditions.checkNotNull(postExecutionThread, "'postExecutionThread' should not be null");

        return getRawObservable().subscribeOn(Schedulers.from(threadExecutor))
                                 .observeOn(postExecutionThread.getScheduler());
    }

}
