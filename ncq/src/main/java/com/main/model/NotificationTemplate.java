package com.main.model;

public class NotificationTemplate {
    private Long id;
    private String templateName;
    private NotificationChannel channel;
    private NotificationEvent event;
    private TemplateStatus status;
    private String subject;
    private String messageBody;

    public NotificationTemplate() {
    }

    public NotificationTemplate(Long id, String templateName, NotificationChannel channel, NotificationEvent event,
                                TemplateStatus status, String subject, String messageBody) {
        this.id = id;
        this.templateName = templateName;
        this.channel = channel;
        this.event = event;
        this.status = status;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    public NotificationEvent getEvent() {
        return event;
    }

    public void setEvent(NotificationEvent event) {
        this.event = event;
    }

    public TemplateStatus getStatus() {
        return status;
    }

    public void setStatus(TemplateStatus status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
