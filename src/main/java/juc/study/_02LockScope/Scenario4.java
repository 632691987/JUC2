package juc.study._02LockScope;

import java.util.concurrent.TimeUnit;

/**
 * 这里考察的是，锁是属于对象的, 如果有两个对象，那么当然是互相不影响了
 */
public class Scenario4 {

    private static class Phone {

        public synchronized void sendSMS() throws InterruptedException {
            TimeUnit.SECONDS.sleep(4); // sleep 不会释放锁
            System.out.println("------sendSMS");
        }

        public synchronized void sendEmail() {
            System.out.println("------sendEmail");
        }

    }

    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

}
