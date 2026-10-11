public class Hilo extends Thread{
   
        public void run(){
            for(int i=10;i>=0;i--){
                try{
                Thread.sleep(1000);
                System.out.println(i);
                }catch(Exception e){}
            }
        }
       
        public static void main(String[] args) {
            Hilo h = new Hilo();
            h.start();
        }
}