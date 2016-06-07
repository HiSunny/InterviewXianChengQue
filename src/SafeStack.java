
public class SafeStack implements StackInterface {
    private int top = 0;
    private int[] values = new int[10];
    private boolean dataAvailable = false;
	@Override
	public void push(int n) {
		// TODO Auto-generated method stub
		synchronized (this) {
            while (dataAvailable) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
            values[top] = n;
            System.out.println("压入数字" + n + "步骤1完成");
            top++;
            dataAvailable = true;
            notifyAll();
            System.out.println("压入数字完成");
        }
	}
	@Override
	public int[] pop() {
		// TODO Auto-generated method stub
		 synchronized (this) {
	            while (!dataAvailable) {
	                try {
	                    wait();
	                } catch (InterruptedException e) {

	                }
	            }
	            System.out.println("弹出");
	            top--;
	            int[] test = {values[top],top};
	            dataAvailable = false;
	            //唤醒正在等待压入数据的线程
	            notifyAll();
	            return test;
	        }
	}  
}
