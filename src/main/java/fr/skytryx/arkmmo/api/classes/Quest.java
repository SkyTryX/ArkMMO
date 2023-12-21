package fr.skytryx.arkmmo.api.classes;

import fr.skytryx.arkmmo.api.enums.QuestType;
import fr.skytryx.arkmmo.api.enums.RewardType;

public class Quest {

    public String name;
    public String description;
    public QuestType questType;
    public Object questObject;
    public int amount;
    public RewardType rewardType;
    public Object reward;

    public Quest(String n, String d, QuestType qt, Object qo, Integer a, RewardType rt, Object r){
        name = n;
        description = d;
        questType = qt;
        questObject = qo;
        amount = a;
        rewardType = rt;
        reward = r;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public QuestType getQuestType(){
        return questType;
    }

    public Object getQuestObject(){
        return questObject;
    }

    public Integer getAmount(){
        return amount;
    }

    public RewardType getRewardType(){
        return rewardType;
    }

    public Object getReward(){
        return reward;
    }
}
