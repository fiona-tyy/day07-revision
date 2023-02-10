package revision;

public class MyRunnableImplementation implements Runnable{

    private String taskName;

    public MyRunnableImplementation(String tName){
        this.taskName = tName;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        for (int i = 0; i < 5; i++){
            System.out.println(this.taskName + ": " + i);
        }
        
    }

    
}
