package com.genius.gitget.challenge.myChallenge.dto;

import com.genius.gitget.challenge.instance.domain.Instance;
import com.genius.gitget.challenge.participant.domain.JoinResult;
import com.genius.gitget.challenge.participant.domain.Participant;
import com.genius.gitget.challenge.participant.domain.RewardStatus;
import com.genius.gitget.global.file.dto.FileResponse;
import lombok.Builder;

@Builder
public record DoneResponse(
        Long instanceId,
        String title,
        int pointPerPerson,
        JoinResult joinResult,
        boolean canGetReward,
        int numOfPointItem,
        int rewardedPoints,
        double achievementRate,
        FileResponse fileResponse
) {
    public static DoneResponse createNotRewarded(Instance instance,
                                                 Participant participant,
                                                 int numOfPointItem) {
        return DoneResponse.builder()
                .title(instance.getTitle())
                .instanceId(instance.getId())
                .pointPerPerson(instance.getPointPerPerson())
                .joinResult(participant.getJoinResult())
                .canGetReward(canGetReward(participant))
                .numOfPointItem(numOfPointItem)
                .fileResponse(FileResponse.create(instance.getFiles()))
                .build();
    }

    public static DoneResponse createRewarded(Instance instance, Participant participant,
                                              double achievementRate) {
        return DoneResponse.builder()
                .title(instance.getTitle())
                .instanceId(instance.getId())
                .pointPerPerson(instance.getPointPerPerson())
                .joinResult(participant.getJoinResult())
                .canGetReward(false)
                .rewardedPoints(participant.getRewardPoints())
                .achievementRate(achievementRate)
                .fileResponse(FileResponse.create(instance.getFiles()))
                .build();
    }

    private static boolean canGetReward(Participant participant) {
        return (participant.getRewardStatus() == RewardStatus.NO) &&
                (participant.getJoinResult() == JoinResult.SUCCESS);
    }
}