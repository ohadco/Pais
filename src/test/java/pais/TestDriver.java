package pais;

/**
 * Created by lidan on 15/12/14.
 */
public class TestDriver {
    public static PaisBridge getTestBridge() {
        return new PaisProxyBridge();
    }
}
