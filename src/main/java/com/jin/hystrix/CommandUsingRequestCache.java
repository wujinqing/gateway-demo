package com.jin.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * @author wu.jinqing
 * @date 2019年12月01日
 */
public class CommandUsingRequestCache extends HystrixCommand<Boolean> {
    private final int value;
    public CommandUsingRequestCache(int value)
    {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroupKeyName"));
        this.value = value;
    }
    @Override
    protected Boolean run() throws Exception {
        return value == 0 || value % 2 == 0;
    }

    @Override
    protected String getCacheKey() {
        System.out.println("from cache");
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            Boolean b1 = new CommandUsingRequestCache(2).execute();
            System.out.println("b1" + b1);
            Boolean b2 = new CommandUsingRequestCache(1).execute();

            System.out.println("b2" + b2);
            Boolean b3 = new CommandUsingRequestCache(0).execute();

            System.out.println("b3" + b3);
            Boolean b4 = new CommandUsingRequestCache(58672).execute();

            System.out.println("b4" + b4);


            System.out.println("==============================");
            context = HystrixRequestContext.initializeContext();
            CommandUsingRequestCache command1 = new CommandUsingRequestCache(2);

            System.out.println("111" +command1.execute());
            System.out.println("222" +command1.isResponseFromCache());

            CommandUsingRequestCache command2 = new CommandUsingRequestCache(2);

            System.out.println("333" +command2.execute());
            System.out.println("444" +command2.isResponseFromCache());

            context = HystrixRequestContext.initializeContext();
            CommandUsingRequestCache command3 = new CommandUsingRequestCache(2);

            System.out.println("555" +command3.execute());
            System.out.println("666" +command3.isResponseFromCache());

        }finally {
            context.shutdown();
        }
    }
}
