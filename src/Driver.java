
public class Driver {
	

	public static PaisBridge getBridge(){
		//when the real application will be ready, need to replace Proxy with Real
		return new PaisProxyBridge(); //PaisRealBridge();
	}
	
}
