package voiceTransfer;

public class Main {

    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    public static void main(String[] args) {
        VoicePlayer player = new VoicePlayer(HZ, BITS, MONO);
        VoiceListener listener = new VoiceListener(HZ, BITS, MONO);
        Thread thread = new Thread();

        player.start();
        listener.start();
        thread.start();

        System.out.println("開始");

        int count = 0;
        while (true) {
            try {
                player.setVoice(listener.getVoice());

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            if (count > 10 * 10) {
                player.end();
                listener.end();
            }

            if (!player.g_bPlayer && !listener.g_bListener) break;
        }

        System.out.println("終了");
        System.exit(0);
    }
}