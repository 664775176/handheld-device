import com.zhangchao.HandledServiceMain;

/**
 * @author Charsel zhang
 * @version 0.0.1
 * @classNane:Test4ErrorFile
 * @description: TODO
 * @date 2019/6/13 19:44
 */
public class Test4NotEnoughInventory {
    //文件不存在
    @org.junit.Test
    public void testError(){
        HandledServiceMain.main(new String[]{"/demo4NotEnoughInventory.txt"});
    }
}
