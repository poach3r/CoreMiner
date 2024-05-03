package Library.Misc;

public class GameLoop {
    private float ups;
    private float fps;
    private boolean running;
    private final Runnable logicUpdate;
    private final Runnable renderUpdate;

    public GameLoop(Runnable logicUpdate, Runnable renderUpdate) {
        ups = 20;
        fps = 60;
        this.logicUpdate = logicUpdate;
        this.renderUpdate = renderUpdate;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }

    public float getFps() {
        return fps;
    }

    public void setUps(float ups) {
        this.ups = ups;
    }

    public float getUps() {
        return ups;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
        System.out.println("game loop ended");
    }

    public void run() {
        System.out.println("game loop started");
        running = true;
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / ups;
        final double timeF = 1000000000 / fps;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                logicUpdate.run();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                renderUpdate.run();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }
}
