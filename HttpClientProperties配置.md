## HttpClientProperties配置

|配置项|说明|默认值|
|---|---|---|
|spring.cloud.gateway.httpclient.connect-timeout|全局连接超时时间设置|默认45秒|
|spring.cloud.gateway.httpclient.response-timeout|全局响应超时时间设置|无|
|spring.cloud.gateway.httpclient.pool.type|线程池类型，ELASTIC弹性的，FIXED固定的，DISABLED禁用 |ELASTIC弹性的|
|spring.cloud.gateway.httpclient.pool.max-connections|最大连接数，当线程池类型是FIXED才有效|Math.max(Runtime.getRuntime().availableProcessors(), 8) * 2) 最小值是16|
|spring.cloud.gateway.httpclient.pool.acquire-timeout|等待获取连接的超时时间，当线程池类型是FIXED才有效||
