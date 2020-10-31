package com.jin.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * @author wu.jinqing
 * @date 2019年12月01日
 */
public class CommandObservableHelloWorld extends HystrixObservableCommand<String> {
    private final String name;

    public CommandObservableHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed())
                    {
                        subscriber.onNext("Hello");
                        subscriber.onNext(name + "!");
                        subscriber.onCompleted();
                    }
                }catch (Exception e)
                {
                    subscriber.onError(e);
                }
            }
        });
    }
}
