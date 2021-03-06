package bbangkeMonster.stage;

import bbangkeMonster.GameManager;

public class TitleStage implements Stage {

    GameManager gm = GameManager.getInstance();

    @Override
    public void init() {

    }

    @Override
    public boolean update() {
        System.out.println("\n" +
                "______ ______                       _           ___  ___                    _               \n" +
                "| ___ \\| ___ \\                     | |          |  \\/  |                   | |              \n" +
                "| |_/ /| |_/ /  __ _  _ __    __ _ | | __  ___  | .  . |  ___   _ __   ___ | |_   ___  _ __ \n" +
                "| ___ \\| ___ \\ / _` || '_ \\  / _` || |/ / / _ \\ | |\\/| | / _ \\ | '_ \\ / __|| __| / _ \\| '__|\n" +
                "| |_/ /| |_/ /| (_| || | | || (_| ||   < |  __/ | |  | || (_) || | | |\\__ \\| |_ |  __/| |   \n" +
                "\\____/ \\____/  \\__,_||_| |_| \\__, ||_|\\_\\ \\___| \\_|  |_/ \\___/ |_| |_||___/ \\__| \\___||_|   \n" +
                "                              __/ |                                                         \n" +
                "                             |___/                                                          \n");
        System.out.println("[PRESS ANY BUTTON]");
        String txt = gm.scan.next();
        if (txt != null) {
            gm.nextStageName = "LOBBY";
            return true;
        }
        return false;
    }
}