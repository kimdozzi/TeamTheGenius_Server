package com.genius.gitget.slack.service;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

import com.genius.gitget.challenge.certification.util.DateUtil;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.TextObject;
import java.util.ArrayList;
import java.util.List;

public class SlackMessageUtil {

    private static final String ERROR_TITLE = "*Exception 발생 시각:* ";
    private static final String ERROR_MESSAGE = "*Exception Message:*\n";
    private static final String ERROR_STACK = "*Exception Stack:*\n";
    private static final String FILTER_STRING = "gitget";


    public static String createErrorTitle() {
        return ERROR_TITLE + DateUtil.getKstLocalTime();
    }

    public static List<Attachment> createAttachments(String color, List<LayoutBlock> data) {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        attachment.setColor(color);
        attachment.setBlocks(data);
        attachments.add(attachment);
        return attachments;
    }

    public static List<LayoutBlock> createProdErrorMessage(Exception exception) {
        StackTraceElement[] stacks = exception.getStackTrace();

        List<LayoutBlock> layoutBlockList = new ArrayList<>();

        List<TextObject> sectionInFields = new ArrayList<>();
        sectionInFields.add(markdownText(ERROR_MESSAGE + exception.getMessage()));
        sectionInFields.add(markdownText(ERROR_STACK + exception));
        layoutBlockList.add(section(section -> section.fields(sectionInFields)));

        layoutBlockList.add(divider());
        layoutBlockList.add(section(section -> section.text(markdownText(filterErrorStack(stacks)))));
        return layoutBlockList;
    }

    private static String filterErrorStack(StackTraceElement[] stacks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("```");
        for (StackTraceElement stack : stacks) {
            if (stack.toString().contains(FILTER_STRING)) {
                stringBuilder.append(stack).append("\n");
            }
        }
        stringBuilder.append("```");
        return stringBuilder.toString();
    }
}
