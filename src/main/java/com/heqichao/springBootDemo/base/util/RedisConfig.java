package com.heqichao.springBootDemo.base.util;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;

@Configuration
@EnableCaching // 开启缓存支持
@AutoConfigureAfter(RedisAutoConfiguration.class)
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

//    @Resource
//    private  RedisProperties redisProperties;
//
//
//    public RedisConfig(RedisProperties redisProperties) {
//        this.redisProperties = redisProperties;
//    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(getRedisStandaloneConfiguration(), getLettucePoolingClientConfiguration());
//        lettuceConnectionFactory.setDatabase(redisProperties.getDatabase());
//        return lettuceConnectionFactory;
//    }

    /**
     * LettuceConnectionFactory 配置
     *
     * @return LettuceConnectionFactory
     */

//    private RedisStandaloneConfiguration getRedisStandaloneConfiguration(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration =new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
//        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
//        redisStandaloneConfiguration.setPort(redisProperties.getPort());
//        redisStandaloneConfiguration.setPassword( RedisPassword.of(redisProperties.getPassword()));
//        return redisStandaloneConfiguration;
//    }

    /**
     * lettuce 连接池配置
     *
     * @return LettucePoolingClientConfiguration
     */
//    private LettuceClientConfiguration getLettucePoolingClientConfiguration() {
//        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
//        genericObjectPoolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
//        genericObjectPoolConfig.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().getSeconds()*1000L);
//        genericObjectPoolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
//        builder.poolConfig(genericObjectPoolConfig);
//        builder.commandTimeout(redisProperties.getTimeout());
//        builder.shutdownTimeout(Duration.ofMillis(1000));
//        return builder.build();
//    }

    /**
     * RedisTemplate配置
     */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {  //
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        template.setConnectionFactory(factory);
        //RedisConnectionFactory newFac =redisConnectionFactory();
        //template.setConnectionFactory(newFac);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
