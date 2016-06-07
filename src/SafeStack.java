
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
            System.out.println("ѹ������" + n + "����1���");
            top++;
            dataAvailable = true;
            notifyAll();
            System.out.println("ѹ���������");
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
	            System.out.println("����");
	            top--;
	            int[] test = {values[top],top};
	            dataAvailable = false;
	            //�������ڵȴ�ѹ�����ݵ��߳�
	            notifyAll();
	            return test;
	        }
	}  
}
