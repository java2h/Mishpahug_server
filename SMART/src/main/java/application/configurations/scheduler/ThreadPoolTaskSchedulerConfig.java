package application.configurations.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class ThreadPoolTaskSchedulerConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(64);
        threadPoolTaskScheduler.setThreadNamePrefix("SMART-ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
 /*   public boolean isReachable(int timeout)
            throws IOException
    Test whether that address is reachable. Best effort is made by the implementation to try to reach the host,
    but firewalls and server configuration may block requests resulting in a unreachable status while some specific
    ports may be accessible. A typical implementation will use ICMP ECHO REQUESTs if the privilege can be obtained,
    otherwise it will try to establish a TCP connection on port 7 (Echo) of the destination host.
        The timeout value, in milliseconds, indicates the maximum amount of time the try should take. If the
        operation times out before getting an answer, the host is deemed unreachable. A negative value will
        result in an IllegalArgumentException being thrown.

        Parameters:
        timeout - the time, in milliseconds, before the call aborts
        Returns:
        a boolean indicating if the address is reachable.
        Throws:
        IOException - if a network error occurs
        IllegalArgumentException - if timeout is negative.
        Since:
        1.5*/