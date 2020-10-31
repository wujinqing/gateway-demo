package com.jin.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import rx.Observable;
import rx.functions.Action1;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wu.jinqing
 * @date 2019年12月01日
 */
public class CommandHelloWorld extends HystrixCommand<String> {
    public final String name;

    public CommandHelloWorld(String name)
    {
//        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("MyCommonKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThreadPoolKey"))
        );
        this.name = name;
    }
    @Override
    protected String run() throws Exception {
//        throw new RuntimeException("e");
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "this is fallback.";
    }

    public static void main(String[] args) {

        System.out.println(new CommandHelloWorld("haha").getCommandKey());
        // 同步方式执行
        String s = new CommandHelloWorld("zhang san").execute();
        System.out.println(s);

        // 异步方式执行
        Future<String> fs = new CommandHelloWorld("World").queue();

        try {
            String s2 = fs.get();
            System.out.println(s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Observable<String> ob = new CommandObservableHelloWorld("lisi").observe();

        ob.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s + "==");
            }
        });
    }
}
