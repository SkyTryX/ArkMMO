package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.enums.QuestType;
import fr.skytryx.arkmmo.api.enums.RewardType;

public class Quest {

    String name;
    String description;
    QuestType questType;
    Object questObject;
    Integer amount;
    RewardType rewardType;
    Object reward;

    public Quest(String n, String d, QuestType qt, Object qo, Integer a, RewardType rt, Object r){
        this.name = n;
        this.description = d;
        this.questType = qt;
        this.questObject = qo;
        this.amount = a;
        this.rewardType = rt;
        this.reward = r;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public QuestType getQuestType(){
        return this.questType;
    }

    public Object getQuestObject(){
        return this.questObject;
    }

    public Integer getAmount(){
        return this.amount;
    }

    public RewardType getRewardType(){
        return this.rewardType;
    }

    public Object getReward() {
        return this.reward;
    }
}
