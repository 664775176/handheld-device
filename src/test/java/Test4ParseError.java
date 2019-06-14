import com.zhangchao.HandledServiceMain;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Test4ErrorParse
 * @description: TODO
 * @date 2019/6/14 13:37
 */
public class Test4ParseError {
    //文件不存在
    @org.junit.Test
    public void testError(){
        HandledServiceMain.main(new String[]{"/demo4ParseError.txt"});
    }
}
