package com.solano.jcstress;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @author WGR
 * @create 2020/12/22 -- 20:48
 */
@JCStressTest
@Outcome(id = {"1","4"}, expect = Expect.ACCEPTABLE,desc = "ok")
@Outcome(id = "0",expect = Expect.ACCEPTABLE_INTERESTING,desc = "danger")
@State
public class Test03Orderliness {
    int num = 0;
    boolean ready = false;
    // 线程一执行的代码
    @Actor
    public void actor1(I_Result r) {
        if(ready) {
            r.r1 = num + num;
        } else {
            r.r1 = 1;
        }
    }
    @Actor
    public void actor2(I_Result r) {
        num = 2;
        ready = true;
    }
}

