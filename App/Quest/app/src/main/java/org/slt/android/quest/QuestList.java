package org.slt.android.quest;

public class QuestList {

    int image;
    String date, questname, questamount;

    public QuestList(int image, String date, String questname, String questamount) {
        this.image = image;
        this.date = date;
        this.questname = questname;
        this.questamount = questamount;
    }

    public int getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getQuestname() {
        return questname;
    }

    public String getQuestamount() {
        return questamount;
    }
}
