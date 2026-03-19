package com.main.controller;

import com.main.model.NotificationChannel;
import com.main.model.NotificationEvent;
import com.main.model.NotificationTemplate;
import com.main.model.TemplateStatus;
import com.main.service.NotificationTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationTemplateController {

    private final NotificationTemplateService notificationTemplateService;

    public NotificationTemplateController(NotificationTemplateService notificationTemplateService) {
        this.notificationTemplateService = notificationTemplateService;
    }

    @GetMapping("/admin/notification-templates")
    public String showTemplateList(Model model) {
        model.addAttribute("templates", notificationTemplateService.getAllTemplates());
        return "notification-template-list";
    }

    @GetMapping("/admin/notification-templates/add")
    public String showAddTemplateForm(Model model) {
        model.addAttribute("template", new NotificationTemplate());
        model.addAttribute("isEdit", false);
        return "notification-template-form";
    }

    @PostMapping("/admin/notification-templates/add")
    public String addTemplate(@ModelAttribute("template") NotificationTemplate template) {
        notificationTemplateService.addTemplate(template);
        return "redirect:/admin/notification-templates";
    }

    @GetMapping("/admin/notification-templates/{id}/edit")
    public String showEditTemplateForm(@PathVariable Long id, Model model) {
        model.addAttribute("template", notificationTemplateService.getTemplateById(id));
        model.addAttribute("isEdit", true);
        return "notification-template-form";
    }

    @PostMapping("/admin/notification-templates/{id}/edit")
    public String editTemplate(@PathVariable Long id, @ModelAttribute("template") NotificationTemplate template) {
        notificationTemplateService.updateTemplate(id, template);
        return "redirect:/admin/notification-templates";
    }

    @ModelAttribute("channels")
    public NotificationChannel[] channels() {
        return NotificationChannel.values();
    }

    @ModelAttribute("events")
    public NotificationEvent[] events() {
        return NotificationEvent.values();
    }

    @ModelAttribute("templateStatuses")
    public TemplateStatus[] templateStatuses() {
        return TemplateStatus.values();
    }
}
