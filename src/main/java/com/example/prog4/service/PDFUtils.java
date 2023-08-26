package com.example.prog4.service;

import com.example.prog4.config.constant.CompanyConf;

import com.example.prog4.model.Employee;
import com.example.prog4.model.exception.InternalServerErrorException;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@AllArgsConstructor
public class PDFUtils {
    public static String parseThymeleafTemplate(Employee employee) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("employee", employee);
        context.setVariable("companyConf", new CompanyConf());

        return templateEngine.process("employee_file", context);
    }

    public static byte[] HtmltoPdf(com.example.prog4.model.Employee employee) {
        String html = parseThymeleafTemplate(employee);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();

        try {
            renderer.createPDF(outputStream);
        } catch (DocumentException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return outputStream.toByteArray();
    }
}
