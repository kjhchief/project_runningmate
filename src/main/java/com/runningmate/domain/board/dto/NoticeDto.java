package com.runningmate.domain.board.dto;

import java.util.Date;

import lombok.Data;


@Data

public class NoticeDto {
    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeDate;
    private int noticeHit;

    public NoticeDto() {}

    public NoticeDto(int noticeId, String noticeTitle, String noticeContent, Date noticeDate, int noticeHit) {
        super();
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeDate = noticeDate;
        this.noticeHit = noticeHit;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public int getNoticeHit() {
        return noticeHit;
    }

    public void setNoticeHit(int noticeHit) {
        this.noticeHit = noticeHit;
    }

    @Override
    public String toString() {
        return "NoticeDto [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
                + ", noticeDate=" + noticeDate + ", noticeHit=" + noticeHit + "]";
    }
}
