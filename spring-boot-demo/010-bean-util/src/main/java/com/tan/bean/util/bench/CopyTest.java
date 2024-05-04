package com.tan.bean.util.bench;

import com.tan.bean.util.copier.*;
import com.tan.bean.util.model.Source;
import com.tan.bean.util.model.Target;
import com.tan.bean.util.model.Target2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

/**
 * @author tanjezh
 * @create 2024-05-03 21:35
 */
@Component
public class CopyTest {

    @Autowired
    private ApacheCopier apacheCopier;
    @Autowired
    private SpringCglibCopier springCglibCopier;
    @Autowired
    private PureCglibCopier pureCglibCopier;
    @Autowired
    private HutoolCopier hutoolCopier;
    @Autowired
    private SpringBeanCopier springBeanCopier;
    @Autowired
    private MapsCopier mapsCopier;
    @Autowired
    private OrikaCopier orikaCopier;

    private Random random = new Random();

    public Source genSource(){
        Source source = new Source();
        source.setId(random.nextInt());
        source.setIds(Arrays.asList(random.nextLong(),random.nextLong(), random.nextLong()));
        source.setPrice(random.nextDouble(120) / 10.0d);
        source.setMarketPrice(new BigDecimal(random.nextFloat()));
        source.setUser_name("谈");
        return source;
    }
    
    public void test() throws Exception{
        copyTest();
        camelParse();
        System.out.println("==================== Check time ====================");
        autoCheck(Target.class,10000);

    }

    private void copyTest() throws Exception {
        Source s = genSource();
        Target ta = apacheCopier.copy(s, Target.class);
        Target ts = springBeanCopier.copy(s, Target.class);
        Target tc = springCglibCopier.copy(s, Target.class);
        Target tp = pureCglibCopier.copy(s, Target.class);
        Target th = hutoolCopier.copy(s, Target.class);
        Target tm = mapsCopier.copy(s, Target.class);
        Target to = orikaCopier.copy(s, Target.class);
        System.out.println("source:\t" + s + "\napache:\t" + ta + "\nspring:\t" + ts
                + "\nsCglib:\t" + tc + "\npCglib:\t" + tp + "\nhuTool:\t" + th + "\nmapStruct:\t" + tm + "\norika:\t" + to);
    }

    private <T> void autoCheck(Class<T> tClass, int size) throws Exception{
        StopWatch stopWatch = new StopWatch();
        runCopier(stopWatch,"apache",size,s -> apacheCopier.copy(s,tClass));
        runCopier(stopWatch,"spring",size,s -> springBeanCopier.copy(s,tClass));
        runCopier(stopWatch,"springCglib",size,s -> springCglibCopier.copy(s,tClass));
        runCopier(stopWatch,"pureCglib",size,s -> pureCglibCopier.copy(s,tClass));
        runCopier(stopWatch,"hutool",size,s -> hutoolCopier.copy(s,tClass));
        runCopier(stopWatch,"mapStruct",size,s -> mapsCopier.copy(s,tClass));
        runCopier(stopWatch,"orika",size,s -> orikaCopier.copy(s,tClass));
        System.out.println((size / 10000)+" w ------- cost: " + stopWatch.prettyPrint() );

    }

    private <T> void autoCheck2(Class<T> tClass, int size) throws Exception{
        StopWatch stopWatch = new StopWatch();
        runCopier(stopWatch,"apache",size,s -> apacheCopier.copy(s,tClass));
        runCopier(stopWatch,"spring",size,s -> springCglibCopier.copy(s,tClass));
        runCopier(stopWatch,"springCglib",size,s -> springCglibCopier.copyAndParse(s,tClass));
        runCopier(stopWatch,"pureCglib",size,s -> pureCglibCopier.copyAndParse(s,tClass));
        runCopier(stopWatch,"hutool",size,s -> hutoolCopier.copyAndParse(s,tClass));
        runCopier(stopWatch,"mapStruct",size,s -> mapsCopier.copyAndParse(s,tClass));
        runCopier(stopWatch,"orika",size,s -> orikaCopier.copy(s,tClass));
        System.out.println((size / 10000)+" w ------- cost: " + stopWatch.prettyPrint() );

    }

    private <T> void runCopier(StopWatch stopWatch, String key, int size, CopierFunc func) throws Exception{
        stopWatch.start(key);
        for (int i = 0; i < size; i++) {
            Source s = genSource();
            func.apply(s);
        }
        stopWatch.stop();
    }

    @FunctionalInterface
    public interface CopierFunc<T>{
        T apply(Source s) throws Exception;
    }

    private void camelParse() throws Exception {
        Source s = genSource();
        Target2 tsParse = springCglibCopier.copyAndParse(s, Target2.class);
        Target2 tpParse = pureCglibCopier.copyAndParse(s, Target2.class);
        Target2 thParse = hutoolCopier.copyAndParse(s, Target2.class);
        Target2 tmParse = mapsCopier.copyAndParse(s, Target2.class);
        Target2 toParse = orikaCopier.copy(s, Target2.class);
        System.out.println("source:\t" + s + "\nspring:\t" + tsParse + "\nsCglib:\t"
                + tpParse + "\nhuTool:\t" + thParse + "\nmapStruct:\t" + tmParse + "\norika:\t" + toParse);

    }

}
