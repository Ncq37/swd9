package com.main.service;

import com.main.model.NotificationChannel;
import com.main.model.NotificationEvent;
import com.main.model.NotificationTemplate;
import com.main.model.TemplateStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationTemplateService {

    private final List<NotificationTemplate> templates = new ArrayList<>();

    public NotificationTemplateService() {
        templates.add(new NotificationTemplate(
                1L,
                "Test Drive Confirmation",
                NotificationChannel.EMAIL,
                NotificationEvent.TEST_DRIVE_REGISTRATION,
                TemplateStatus.ACTIVE,
                "Your test drive booking is confirmed",
                "Hello {{customerName}}, your test drive for {{carModel}} is confirmed on {{appointmentDate}} at {{showroomName}}."
        ));
        templates.add(new NotificationTemplate(
                2L,
                "Quotation Follow-up SMS",
                NotificationChannel.SMS,
                NotificationEvent.QUOTATION_SENT,
                TemplateStatus.ACTIVE,
                "Quotation sent",
                "Dear {{customerName}}, your quotation for {{carModel}} has been sent. Please contact {{salesName}} for support."
        ));
        templates.add(new NotificationTemplate(
                3L,
                "Contract Signed Notice",
                NotificationChannel.SYSTEM,
                NotificationEvent.CONTRACT_SIGNED,
                TemplateStatus.DRAFT,
                "Contract signed successfully",
                "Contract {{contractCode}} for {{customerName}} has been signed. Accounting and sales teams can proceed with the next steps."
        ));
        templates.add(new NotificationTemplate(
                4L,
                "Payment Confirmation",
                NotificationChannel.EMAIL,
                NotificationEvent.PAYMENT_CONFIRMED,
                TemplateStatus.ACTIVE,
                "Payment received successfully",
                "Hello {{customerName}}, we have received your payment for {{carModel}}. Thank you for completing your transaction."
        ));
    }

    public List<NotificationTemplate> getAllTemplates() {
        return templates;
    }

    public NotificationTemplate getTemplateById(Long id) {
        return templates.stream()
                .filter(template -> template.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Template not found with id: " + id));
    }

    public void addTemplate(NotificationTemplate template) {
        long nextId = templates.stream()
                .map(NotificationTemplate::getId)
                .max(Comparator.naturalOrder())
                .orElse(0L) + 1;
        template.setId(nextId);
        templates.add(template);
    }

    public void updateTemplate(Long id, NotificationTemplate updatedTemplate) {
        NotificationTemplate existingTemplate = getTemplateById(id);
        existingTemplate.setTemplateName(updatedTemplate.getTemplateName());
        existingTemplate.setChannel(updatedTemplate.getChannel());
        existingTemplate.setEvent(updatedTemplate.getEvent());
        existingTemplate.setStatus(updatedTemplate.getStatus());
        existingTemplate.setSubject(updatedTemplate.getSubject());
        existingTemplate.setMessageBody(updatedTemplate.getMessageBody());
    }
}
